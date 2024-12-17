@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package fi.infinitygrow.lainavertalijacom

//actual class UrlManager {
//    actual fun openLink(url: String) {
//    }
//}

import kotlin.system.exitProcess


actual class UrlManager {
    actual fun openLink(url: String) {
        val os = System.getProperty("os.name").lowercase()

        try {
            when {
                os.contains("win") -> {
                    ProcessBuilder("cmd", "/c", "start", url).start()
                }
                os.contains("mac") -> {
                    ProcessBuilder("open", url).start()
                }
                os.contains("nix") || os.contains("nux") -> {
                    ProcessBuilder("xdg-open", url).start()
                }
                else -> {
                    println("Unsupported operating system: $os")
                }
            }
        } catch (e: Exception) {
            println("Failed to open URL: ${e.message}")
        }
    }
}



//actual class UrlManager2 {
//    actual fun openLink(url: String) {
//        val os = System.getProperty("os.name").lowercase()
//
//        try {
//            when {
//                os.contains("win") -> {
//                    Runtime.getRuntime().exec("cmd /c start $url")
//                }
//                os.contains("mac") -> {
//                    Runtime.getRuntime().exec("open $url")
//                }
//                os.contains("nix") || os.contains("nux") -> {
//                    Runtime.getRuntime().exec("xdg-open $url")
//                }
//                else -> {
//                    println("Unsupported operating system: $os")
//                }
//            }
//        } catch (e: Exception) {
//            println("Failed to open URL: ${e.message}")
//        }
//    }
//}
