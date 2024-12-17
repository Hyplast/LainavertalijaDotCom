package fi.infinitygrow.lainavertalijacom

interface Platform {
    val name: String
}

//expect fun getPlatform(): Platform

expect fun formatCurrency(value: Double): String