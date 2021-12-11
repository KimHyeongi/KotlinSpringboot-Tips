package com.tistory.eclipse4j.batch.example

import mu.KotlinLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.support.ListItemReader
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnProperty(name = ["job.name"], havingValue = "ExampleBatchBatchJob")
class ExampleBatchConfiguration(
    val jobBuilderFactory: JobBuilderFactory,
    val stepBuilderFactory: StepBuilderFactory
) {

    private val logger = KotlinLogging.logger {}

    companion object {
        const val JOB_NAME = "ExampleBatchBatchJob"
        const val STEP_NAME = "$JOB_NAME-step"
        const val CHUNK_SIZE = 1000
    }

    @Bean(name = [JOB_NAME])
    fun job(): Job =
        jobBuilderFactory.get(JOB_NAME)
            .start(step())
            .build()

    @JobScope
    @Bean(name = [STEP_NAME])
    fun step(): Step =
        stepBuilderFactory.get(STEP_NAME)
            .chunk<String, String>(CHUNK_SIZE)
            .reader(reader())
            .writer(writer())
            .build()

    @StepScope
    @Bean(name = ["$STEP_NAME-reader"])
    fun reader(): ItemReader<String> =
        ListItemReader(listOf("Hello", "IU", "World"))

    @StepScope
    @Bean(name = ["$STEP_NAME-writer"])
    fun writer(): ItemWriter<String> =
        ItemWriter { items ->
            items.forEach {
                println("=================================================================")
                println(it)
                println("=================================================================")
            }
        }
}
