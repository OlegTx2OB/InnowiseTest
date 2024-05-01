plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    id("kotlin-kapt")
    id("kotlin-android")
}

android {
    namespace = "com.example.innowisepexelstestapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.innowisepexelstestapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":data"))



    //implementation ("ru.terrakok.cicerone:cicerone:5.1.0")

    implementation("com.github.terrakok:cicerone:7.1")

    implementation("io.reactivex.rxjava3:rxjava:3.1.2")

    implementation("com.google.dagger:dagger:2.51.1")

    kapt("com.google.dagger:dagger-compiler:2.51.1")
    implementation("javax.inject:javax.inject:1")

    implementation ("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.6")

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.fragment.ktx)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}