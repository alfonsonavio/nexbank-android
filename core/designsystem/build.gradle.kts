plugins {
    alias(libs.plugins.nexbank.android.library)
    alias(libs.plugins.nexbank.android.compose)
}

android {
    namespace = "com.alfonsonavio.nexbank.core.designsystem"
}

dependencies {
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.ui)
}
