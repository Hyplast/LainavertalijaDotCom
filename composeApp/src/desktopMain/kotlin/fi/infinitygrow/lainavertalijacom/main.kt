package fi.infinitygrow.lainavertalijacom

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "LainavertalijaCom"
    ) {
        val urlManager = UrlManager() // Initialize UrlManager or other dependencies here
        App(urlManager = urlManager)
    }
}

//fun main() = application {
//    Window(
//        onCloseRequest = ::exitApplication,
//        title = "LainavertalijaCom",
//    ) {
//        App()
//    }
//}