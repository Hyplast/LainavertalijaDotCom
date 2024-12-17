package fi.infinitygrow.lainavertalijacom

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController { App(
    urlManager = UrlManager()
) }