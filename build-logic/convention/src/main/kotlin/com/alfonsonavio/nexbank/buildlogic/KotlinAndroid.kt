package com.alfonsonavio.nexbank.buildlogic

import com.android.build.api.dsl.CommonExtension

/**
 * Configuration shared by application and library modules.
 *
 * AGP 9 compiles kotlin itself (built-in Kotlin), so the kotlin-android plugin is not applied.
 * Kotlin's jvmTarget defaults to compileOptions.targetCompatibility, so setting the Java
 * version here keeps Java and Kotlin bytecode aligned
 */
internal fun configureKotlinAndroid(commonExtension: CommonExtension) {
    commonExtension.apply {
        compileSdk = NexBankBuildConfig.COMPILE_SDK

        defaultConfig.apply {
            minSdk = NexBankBuildConfig.MIN_SDK
        }

        compileOptions.apply {
            sourceCompatibility = NexBankBuildConfig.JAVA_VERSION
            targetCompatibility = NexBankBuildConfig.JAVA_VERSION
        }
    }
}
