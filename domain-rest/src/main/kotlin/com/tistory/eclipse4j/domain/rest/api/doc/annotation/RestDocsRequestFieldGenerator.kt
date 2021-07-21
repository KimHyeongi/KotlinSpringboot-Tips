package com.tistory.eclipse4j.domain.rest.api.doc.annotation

import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.RequestDocumentation
import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType
import java.util.function.Consumer

class RestDocsRequestFieldGenerator {

    private val descriptors: MutableList<ParameterDescriptor> = mutableListOf()

    private fun addFieldWithPath(clz: Class<*>) {
        clz.getDeclaredAnnotation(RestDocClass::class.java) ?: return
        val filedArray = clz.declaredFields
        val fields: List<Field> = filedArray.toMutableList()
        fields.forEach(
            Consumer { f: Field ->
                val docProperty = f.getDeclaredAnnotation(RestDocProperty::class.java)
                descriptors.add(RequestDocumentation.parameterWithName(f.name).optional().attributes(RestDocsUtils.optional(), RestDocsUtils.emptyFormat()).description(docProperty.description))
                if (DocsTypeUtils.isMemberType(f.type)) {
                    addFieldWithPath(f.type)
                }
                if (DocsTypeUtils.isListType(f.type)) {
                    val integerListType: ParameterizedType = f.genericType as ParameterizedType
                    addFieldWithPath(integerListType.actualTypeArguments[0] as Class<*>)
                }
            }
        )
    }

    fun buildObjectResponse(clz: Class<*>?): MutableList<ParameterDescriptor> {
        addFieldWithPath(clz!!)
        return descriptors
    }
}
