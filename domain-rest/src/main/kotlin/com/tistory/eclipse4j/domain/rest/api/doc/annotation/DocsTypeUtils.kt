package com.tistory.eclipse4j.domain.rest.api.doc.annotation

object DocsTypeUtils {
    fun isNumberType(type: Class<*>): Boolean {
        return type.simpleName.lowercase().contains("long") || type.simpleName.lowercase().contains("int")
    }

    fun isStringType(type: Class<*>): Boolean {
        return type.simpleName.lowercase().contains("string") || isEnumType(type)
    }

    fun isMemberType(type: Class<*>): Boolean {
        return type.isMemberClass
    }

    fun isBooleanType(type: Class<*>): Boolean {
        return type.simpleName.lowercase().contains("boolean")
    }

    fun isEnumType(type: Class<*>): Boolean {
        return type.isEnum
    }

    fun isListType(type: Class<*>): Boolean {
        return type.simpleName.lowercase().contains("list") || type.simpleName.lowercase().contains("set")
    }

    fun isLocalDateTimeType(type: Class<*>): Boolean {
        return type.simpleName.lowercase().contains("localdatetime")
    }
}
