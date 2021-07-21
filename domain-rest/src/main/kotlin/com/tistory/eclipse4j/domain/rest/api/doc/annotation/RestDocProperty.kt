package com.tistory.eclipse4j.domain.rest.api.doc.annotation

@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.VALUE_PARAMETER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class RestDocProperty(val description: String)
