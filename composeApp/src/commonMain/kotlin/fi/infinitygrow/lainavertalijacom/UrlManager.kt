package fi.infinitygrow.lainavertalijacom

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class UrlManager {
    // Expect function with a context argument for Android, no need for iOS to use it
    fun openLink(url: String)
}