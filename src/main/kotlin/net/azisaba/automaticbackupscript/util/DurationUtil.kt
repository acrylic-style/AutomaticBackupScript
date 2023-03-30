package net.azisaba.automaticbackupscript.util

import kotlin.time.Duration

fun Duration.toLocaleString(locale: DurationLocale): String {
    var string = ""
    if (inWholeHours > 0) {
        string += "${inWholeHours}${if (inWholeHours != 1L) locale.hours else locale.hour}"
    }
    if ((inWholeMinutes % 60) > 0) {
        string += "${inWholeMinutes % 60}${if ((inWholeMinutes % 60) != 1L) locale.minutes else locale.minute}"
    }
    if ((inWholeSeconds % 60) > 0 || string.isEmpty()) {
        string += "${inWholeSeconds % 60}${if ((inWholeSeconds % 60) != 1L) locale.seconds else locale.second}"
    }
    return string.trim()
}

enum class DurationLocale(
    val hours: String,
    val hour: String,
    val minutes: String,
    val minute: String,
    val seconds: String,
    val second: String,
) {
    JAPANESE("時間", "時間", "分", "分", "秒", "秒"),
    ENGLISH(" hours ", " hour ", " minutes ", " minute ", " seconds ", " second "),
}
