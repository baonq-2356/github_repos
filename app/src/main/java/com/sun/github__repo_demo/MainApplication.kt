package com.sun.github__repo_demo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Use Hilt must contain an Application class that is annotated with @HiltAndroidApp
 * @HiltAndroidApp triggers Hilt's code generation
 */
@HiltAndroidApp
class MainApplication : Application()
