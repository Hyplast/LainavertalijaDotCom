package fi.infinitygrow.lainavertalijacom

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class UrlManager {
    // iOS doesn't need context, so we just ignore the context parameter
    actual fun openLink(url: String) {
        val nsUrl = NSURL.URLWithString(url)
        if (nsUrl != null) {
            UIApplication.sharedApplication.openURL(nsUrl)
        }
    }
}