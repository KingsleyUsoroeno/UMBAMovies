const val kotlinKapt: String = "kapt"
const val kotlinAndroid: String = "android"

object Dependencies {

    interface Libraries {
        val components: List<String>
    }

    object Config {
        object Version {
            const val minSdkVersion: Int = 21
            const val compileSdkVersion: Int = 31
            const val targetSdkVersion: Int = 31
            const val versionName: String = "1.0"
            const val versionCode: Int = 1
            const val composeVersion: String = "1.0.1"
        }

        const val isMultiDexEnabled: Boolean = true

        object Android {
            const val applicationId: String = "com.example.umbaandroid"
        }
    }

    object Compose : Libraries {

        private const val compose = "1.0.0-alpha09"

        private const val composeFoundation = "androidx.compose.foundation:foundation:$compose"
        private const val composeLiveData = "androidx.compose.runtime:runtime-livedata:$compose"
        private const val composeMaterial =
            "androidx.compose.material:material:${Config.Version.composeVersion}"
        private const val composeMaterialIcons =
            "androidx.compose.material:material-icons-core:$compose"
        private const val composeMaterialIconsExtended =
            "androidx.compose.material:material-icons-extended:$compose"
        private const val composeTooling =
            "androidx.compose.ui:ui-tooling:${Config.Version.composeVersion}"
        private const val composePreview =
            "androidx.compose.ui:ui-tooling-preview:${Config.Version.composeVersion}"
        private const val composeUi =
            "androidx.compose.ui:ui:${Config.Version.composeVersion}"
        private const val composeActivity =
            "androidx.activity:activity-compose:${Config.Version.composeVersion}"

        override val components: List<String>
            get() = listOf(
                composeFoundation,
                composeLiveData,
                composeMaterial,
                composeMaterialIcons,
                composeMaterialIconsExtended,
                composeTooling,
                composePreview,
                composeUi,
                composeActivity
            )
    }

    object Coroutines : Libraries {
        private const val core: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        private const val android: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

        override val components: List<String> = listOf(core, android)
    }

    object Test {
        const val junit: String = "junit:junit:${Versions.jUnit}"
        const val runner: String = "androidx.test:runner:${Versions.runner}"
        const val androidXTest: String = "androidx.test.ext:junit:${Versions.testExt}"
        const val truth: String = "com.google.truth:truth:${Versions.truth}"
        const val coroutinesTest: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        const val robolectric: String = "org.robolectric:robolectric:${Versions.robolectric}"
        const val androidXTestCore = "androidx.test:core:${Versions.androidXTestCore}"
        const val mockWebServer: String =
            "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer}"
    }

    object DI {
        private const val hiltViewModelVersion = "1.0.0-alpha03"
        private const val hiltCompilerVersion = "1.0.0"

        object AnnotationProcessor {
            const val jetpackHiltCompiler: String =
                "androidx.hilt:hilt-compiler:${Versions.hiltViewModel}"
        }

        const val javaxInject: String = "javax.inject:javax.inject:${Versions.javaxInject}"
        const val daggerHiltAndroid = "com.google.dagger:hilt-android:${Versions.daggerHiltAndroid}"
        const val daggerHiltkapt =
            "com.google.dagger:hilt-android-compiler:${Versions.daggerHiltAndroid}"

        const val hiltLifecycleViewModel: String =
            "androidx.hilt:hilt-lifecycle-viewmodel:$hiltViewModelVersion"
        const val hiltViewModelCompiler = "androidx.hilt:hilt-compiler:$hiltCompilerVersion"
        const val lifeCycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0"

        const val hiltTesting: String =
            "com.google.dagger:hilt-android-testing:${Versions.daggerHiltAndroid}"


    }

    object Navigation : Libraries {
        private const val navigation = "2.4.0-rc01"

        private const val composeNavigation = "androidx.navigation:navigation-compose:$navigation"
        private const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:1.0.0-rc01"

        override val components: List<String>
            get() = listOf(composeNavigation, hiltNavigation)
    }


    object Network : Libraries {
        private const val okhttp: String = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        private const val loggingInterceptor: String =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        private const val retrofitConverter =
            "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"

        override val components: List<String> = listOf(
            okhttp,
            loggingInterceptor,
            retrofit,
            retrofitConverter
        )
    }

    object Cache {
        private const val roomVersion = "2.3.0"

        const val room = "androidx.room:room-ktx:$roomVersion"
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    }
}