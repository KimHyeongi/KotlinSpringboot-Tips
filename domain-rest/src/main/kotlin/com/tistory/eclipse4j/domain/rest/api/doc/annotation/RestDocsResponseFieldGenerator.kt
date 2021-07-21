package com.tistory.eclipse4j.domain.rest.api.doc.annotation

import com.google.common.base.Joiner
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType
import java.util.function.Consumer

class RestDocsResponseFieldGenerator {

    private val rootPath: MutableList<String> = mutableListOf()

    private val descriptors: MutableList<FieldDescriptor> = mutableListOf(
        fieldWithPath("timestamp").type(JsonFieldType.NUMBER).attributes(RestDocsUtils.required(), RestDocsUtils.emptyFormat()).description("응답시간"),
        fieldWithPath("errorCode").type(JsonFieldType.STRING).attributes(RestDocsUtils.required(), RestDocsUtils.emptyFormat()).description("에러응답코드"),
        fieldWithPath("statusCode").type(JsonFieldType.STRING).attributes(RestDocsUtils.required(), RestDocsUtils.emptyFormat()).description("성공응답코드"),
        fieldWithPath("statusMessage").type(JsonFieldType.STRING).attributes(RestDocsUtils.required(), RestDocsUtils.emptyFormat()).description("상태메시지")
    )

    fun addFieldWithPath(clz: Class<*>) {
        val filedArray = clz.declaredFields
        val fields: List<Field> = filedArray.toMutableList()
        // class 가 이닌경우 return
        clz.getDeclaredAnnotation(RestDocClass::class.java) ?: return

        val path: String = Joiner.on(".").join(rootPath)
        fields.forEach(
            Consumer { f: Field ->
                val docProperty = f.getDeclaredAnnotation(RestDocProperty::class.java)
                if (DocsTypeUtils.isEnumType(f.type)) {
                    descriptors.add(fieldWithPath(path + "." + f.name).type(JsonFieldType.STRING).attributes(RestDocsUtils.required(), RestDocsUtils.emptyFormat()).description(docProperty.description))
                    return@Consumer
                }
                if (DocsTypeUtils.isNumberType(f.type)) {
                    descriptors.add(fieldWithPath(path + "." + f.name).type(JsonFieldType.NUMBER).attributes(RestDocsUtils.required(), RestDocsUtils.emptyFormat()).description(docProperty.description))
                    return@Consumer
                }
                if (DocsTypeUtils.isStringType(f.type)) {
                    descriptors.add(fieldWithPath(path + "." + f.name).type(JsonFieldType.STRING).attributes(RestDocsUtils.required(), RestDocsUtils.emptyFormat()).description(docProperty.description))
                    return@Consumer
                }
                if (DocsTypeUtils.isBooleanType(f.type)) {
                    descriptors.add(
                        fieldWithPath(path + "." + f.name)
                            .type(JsonFieldType.BOOLEAN).attributes(RestDocsUtils.required(), RestDocsUtils.emptyFormat())
                            .description(docProperty.description)
                    )
                    return@Consumer
                }
                if (DocsTypeUtils.isLocalDateTimeType(f.type)) {
                    descriptors.add(fieldWithPath(path + "." + f.name).type(JsonFieldType.STRING).attributes(RestDocsUtils.optional(), RestDocsUtils.dateTimeFormat()).description(docProperty.description))
                    return@Consumer
                }
                if (DocsTypeUtils.isMemberType(f.type)) {
                    rootPath.add(f.name)
                    addFieldWithPath(f.type)
                }
                if (DocsTypeUtils.isListType(f.type)) {
                    rootPath.add(f.name)
                    val integerListType: ParameterizedType = f.genericType as ParameterizedType
                    addFieldWithPath(integerListType.getActualTypeArguments().get(0) as Class<*>)
                }
            }
        )
    }

    fun buildObjectResponse(clz: Class<*>?): MutableList<FieldDescriptor> {
        addRootFieldWithPath("data")
        addFieldWithPath(clz!!)
        return descriptors
    }

    fun buildListResponse(clz: Class<*>?): MutableList<FieldDescriptor> {
        addRootFieldWithPath("data[]")
        addFieldWithPath(clz!!)
        return descriptors
    }

    fun buildDefaultResponse(): MutableList<FieldDescriptor> {
        descriptors.add(
            fieldWithPath("data")
                .type(JsonFieldType.STRING)
                .attributes(RestDocsUtils.required(), RestDocsUtils.emptyFormat())
                .description("응답시간")
        )
        return descriptors
    }

    private fun addRootFieldWithPath(path: String) {
        rootPath.add(path)
    }
}
