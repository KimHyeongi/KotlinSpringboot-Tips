package com.tistory.eclipse4j.domain.rest.api.doc.annotation

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.snippet.Attributes
import java.util.Arrays
import java.util.function.Function
import java.util.stream.Collectors

object RestDocsUtils {
    fun preprocessRequest(): OperationRequestPreprocessor? {
        return Preprocessors.preprocessRequest(
            Preprocessors.modifyUris().scheme("https").host("api.myhost.com").removePort(),
            Preprocessors.prettyPrint()
        )
    }

    fun preprocessResponse(): OperationResponsePreprocessor? {
        return Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
    }

    fun required(): Attributes.Attribute? {
        return Attributes.key("required").value("true")
    }

    fun optional(): Attributes.Attribute? {
        return Attributes.key("required").value("false")
    }

    fun defaultValue(defaultValue: String?): Attributes.Attribute? {
        return Attributes.key("defaultValue").value(defaultValue)
    }

    fun emptyDefaultValue(): Attributes.Attribute? {
        return Attributes.key("defaultValue").value("")
    }

    fun customFormat(custom: String?): Attributes.Attribute? {
        return Attributes.key("format").value(custom)
    }

    fun emptyFormat(): Attributes.Attribute? {
        return Attributes.key("format").value("")
    }

    fun dateTimeFormat(): Attributes.Attribute? {
        return Attributes.key("format").value("YYYY-MM-DD HH:MM:SS")
    }

    fun dateFormat(): Attributes.Attribute? {
        return Attributes.key("format").value("YYYY-MM-DD")
    }

    fun dateFormatWithPattern(pattern: String?): Attributes.Attribute? {
        return Attributes.key("format").value(pattern)
    }

    fun <T : Enum<T>?> enumFormat(enums: Class<T>): Attributes.Attribute? {
        return Attributes.key("format")
            .value(Arrays.stream(enums.enumConstants).map { obj: T -> obj!!.name }.collect(Collectors.joining("\n")))
    }

    fun <T : Enum<T>?> enumFormat(enums: Collection<T>): Attributes.Attribute? {
        return Attributes.key("format")
            .value(enums.stream().map { obj: T -> obj!!.name }.collect(Collectors.joining("\n")))
    }

    fun <T : Enum<T>?> enumFormat(enums: Array<T>?, detailFcuntion: Function<T, String>): Attributes.Attribute? {
        return Attributes.key("format").value(
            Arrays.stream(enums).collect(Collectors.toList()).stream()
                .map { en: T -> en!!.name + "(" + detailFcuntion.apply(en) + ")" }.collect(Collectors.joining("\n"))
        )
    }

    fun <T : Enum<T>?> enumFormat(enums: Collection<T>, detailFcuntion: Function<T, String>): Attributes.Attribute? {
        return Attributes.key("format")
            .value(
                enums.stream().map { en: T -> en!!.name + "(" + detailFcuntion.apply(en) + ")" }
                    .collect(Collectors.joining("\n"))
            )
    }
}
