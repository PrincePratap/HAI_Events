package com.cody.haievents

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform