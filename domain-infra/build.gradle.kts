plugins {
}

dependencies {
    implementation(project(Projects.commons))
    implementation(Libraries.kotlin_logging)

    implementation(Libraries.spring_cloud_starter_aws)
    implementation(Libraries.spring_cloud_starter_aws_messaging)
//    implementation(Libraries.spring_cloud_starter_sleuth)

    implementation(Libraries.aws_java_sdk_s3)
    implementation(Libraries.aws_java_sdk_core)
    implementation(Libraries.aws_java_sdk_sns)
    implementation(Libraries.aws_java_sdk_sqs)

    implementation(Libraries.spring_boot_starter_webflux)
    implementation(Libraries.jackson_module_kotlin)
    implementation(Libraries.jackson_datatype_jsr310)
    implementation("com.sun.xml.bind:jaxb-osgi:3.0.0-M5")
}
