package com.tistory.eclipse4j.domain.rest.api.doc.annotation

@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class RestDocClass(val path: String = "")
