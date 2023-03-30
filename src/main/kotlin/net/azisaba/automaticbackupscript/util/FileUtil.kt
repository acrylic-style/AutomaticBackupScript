package net.azisaba.automaticbackupscript.util

import java.io.File

inline fun <R> File.use(action: (File) -> R): R {
    try {
        return action(this)
    } finally {
        deleteRecursively()
    }
}
