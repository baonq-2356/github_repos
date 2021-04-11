package com.sun.github__repo_demo.data.remote

import javax.inject.Qualifier

/**
 * Used to name a component when declare, can get component by name declared
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class BaseSourceApi(val name: String)
