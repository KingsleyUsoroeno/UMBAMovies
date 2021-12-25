import Dependencies.Cache
import Dependencies.Compose
import Dependencies.Config
import Dependencies.Coroutines
import Dependencies.DI
import Dependencies.Navigation
import Dependencies.Network
import Dependencies.Test

plugins {
    androidApplication
    kotlin(kotlinAndroid)
    kotlin(kotlinKapt)
    daggerHilt
    kotlinParcelize
}

android {
    defaultConfig {
        applicationId = Config.Android.applicationId
        minSdk = Config.Version.minSdkVersion
        targetSdk = Config.Version.targetSdkVersion
        compileSdkVersion(Config.Version.compileSdkVersion)
        versionCode = Config.Version.versionCode
        versionName = Config.Version.versionName
        multiDexEnabled = Config.isMultiDexEnabled

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        named(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
        }
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Config.Version.composeVersion
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        compose = true
    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementAll(Compose.components)

    /*Dagger Hilt and HiltViewModel*/
    implementation(DI.daggerHiltAndroid)
    kapt(DI.daggerHiltkapt)

    implementAll(Network.components)
    implementAll(Coroutines.components)

    implementation(Cache.room)
    kapt(Cache.roomCompiler)

    implementAll(Navigation.components)
    implementation(DI.hiltLifecycleViewModel)
    implementation(DI.lifeCycleViewModel)
    kapt(DI.hiltViewModelCompiler)

    debugImplementation(Compose.composeTooling)
    androidTestImplementation(Test.composeUiTest)
    androidTestImplementation(Test.espressoCore)
    testImplementation(Test.mockWebServer)
    testImplementation(Test.junit)

    implementation("com.github.skydoves:landscapist-glide:1.4.4")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0-rc02")

}