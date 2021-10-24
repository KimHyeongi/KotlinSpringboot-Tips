package com.tistory.eclipse4j.domain.study

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import mu.KotlinLogging

internal class RunCatchingTest() : StringSpec() {

    private val log = KotlinLogging.logger { }

    init {
        "runCatching throw" {
            val sut = RunCatchingService()
            shouldThrow<RuntimeException> {
                sut.getAsis()
            }
        }

        "runCatching 0B RuntimeException 발생 그대로 throw" {
            val sut = RunCatchingService()
            shouldThrow<RuntimeException> {
                sut.get0B()
            }
        }

        "runCatching 1B RuntimeException 발생을 NumberFormatException으로 onFailure에서 변경. getOrThrow" {
            val sut = RunCatchingService()
            shouldThrow<NumberFormatException> {
                sut.get1B()
            }
        }

        "runCatching 2B 정상적으로 1000 반환후 타입변환" {
            val sut = RunCatchingService()
            val num = sut.get2B()
            num shouldBe 1000
        }
    }
}

internal class RunCatchingService {

    private val log = KotlinLogging.logger { }

    fun getAsis() : Int {
        val textNumber = TextNumber()
        try {
            return textNumber.getThrow().toInt()
        }catch (e: RuntimeException){
            log.error { "$e" }
            throw RuntimeException()
        }
    }

    fun get0B() : Int {
        val textNumber = TextNumber()
        return runCatching {
            textNumber.getThrow()
        }.map {
            it.toInt()
        }.getOrThrow()

    }

    fun get1B() : Int {
        val textNumber = TextNumber()
        return runCatching {
            textNumber.getThrow()
        }.map {
            it.toInt()
        }.onFailure {
            throw NumberFormatException()
        }.getOrThrow()

    }

    fun get2B() : Int {
        val textNumber = TextNumber()
        return runCatching {
            textNumber.getStringNumber()
        }.map {
            it.toInt()
        }.onFailure {
            throw NumberFormatException()
        }.getOrThrow()

    }
}

internal class TextNumber {
    // 임의 Throw
    fun getThrow() : String {
        throw RuntimeException()
    }

    // 정상반환
    fun getStringNumber() : String = "1000"
}