package fi.infinitygrow.lainavertalijacom

import android.content.Context
import android.content.Intent
import android.net.Uri

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class UrlManager(
    private val context: Context
) {
    actual fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}