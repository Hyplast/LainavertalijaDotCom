package fi.infinitygrow.lainavertalijacom

import platform.Foundation.NSString
import platform.Foundation.stringWithFormat
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

//actual fun getPlatform(): Platform = IOSPlatform()

actual fun formatCurrency(value: Double): String {
    return NSString.stringWithFormat("%.2f", value)
}