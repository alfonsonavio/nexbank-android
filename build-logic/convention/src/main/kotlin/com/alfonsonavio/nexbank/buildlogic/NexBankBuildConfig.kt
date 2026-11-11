package com.alfonsonavio.nexbank.buildlogic

import org.gradle.api.JavaVersion

/**
 * Single source of truth for SDK levels and the Java/Kotlin bytecode target.
 * Every module gets these values through the convention plugins.
 */
internal object NexBankBuildConfig {
    const val COMPILE_SDK = 37
    const val TARGET_SDK = 37
    const val MIN_SDK = 26
    val JAVA_VERSION: JavaVersion = JavaVersion.VERSION_17
}