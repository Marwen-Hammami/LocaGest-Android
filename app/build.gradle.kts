plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // 1 Add plugin kapt
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "tn.sim.locagest"
    compileSdk = 33

    defaultConfig {
        applicationId = "tn.sim.locagest"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {


    implementation ("com.google.android.material:material:1.5.0")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //google.android.material
    implementation ("com.google.android.material:material:1.4.0")

    // Architectural Components
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    // Room
    implementation ("androidx.room:room-runtime:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")

    // Kotlin Extensions and Coroutines support for Room
    implementation ("androidx.room:room-ktx:2.5.0")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.6.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.6.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.5.0")

    // Navigation Components
    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation ("androidx.navigation:navigation-ui-ktx:2.5.3")

    // Glide is an image loading and caching library
    implementation ("com.github.bumptech.glide:glide:4.11.0")
    kapt ("com.github.bumptech.glide:compiler:4.11.0")

    //to display image from url
    implementation ("com.squareup.picasso:picasso:2.71828")

    //Video Call
    implementation ("com.github.ZEGOCLOUD:zego_uikit_prebuilt_call_android:+")    // Add this line to your module-level build.gradle file's dependencies, usually named [app].
    implementation ("com.github.ZEGOCLOUD:zego_uikit_signaling_plugin_android:+")  // Add this line to your module-level build.gradle file's dependencies, usually named [app].

    //User
    implementation ("com.facebook.android:facebook-android-sdk:12.0.0")
    implementation ("com.facebook.android:facebook-login:12.0.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation ("com.google.android.gms:play-services-auth:20.0.0")
    implementation("com.google.android.material:material:1.9.0")

    //SocketIo
    implementation ("io.socket:socket.io-client:2.0.0")

    // Map
    implementation("com.mapbox.maps:android:10.4.3") {
        exclude("group_name", "module_name")
    }
    implementation("com.google.android.gms:play-services-location:18.0.0")
}