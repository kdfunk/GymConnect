plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.connectgym"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.connectgym"
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
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("org.jsoup:jsoup:1.14.3")
    implementation("androidx.work:work-runtime:2.7.1")
    implementation ("androidx.work:work-runtime-ktx:2.7.1")
    implementation ("androidx.recyclerview:recyclerview:1.2.1")

}