package fi.infinitygrow.lainavertalijacom

import android.os.Build
import java.util.Locale

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun formatCurrency(value: Double): String {
    return String.format(Locale.US, "%.2f", value)
}
