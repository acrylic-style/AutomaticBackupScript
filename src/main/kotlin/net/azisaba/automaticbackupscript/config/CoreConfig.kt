package net.azisaba.automaticbackupscript.config

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.azisaba.automaticbackupscript.Main
import net.azisaba.automaticbackupscript.util.ProcessExecutor
import java.io.File

@Serializable
data class CoreConfig(
    val preExecuteScript: List<List<String>> = listOf(listOf("echo", "pre-execute script!"))
) {
    companion object {
        @OptIn(ExperimentalSerializationApi::class)
        val json = Json {
            encodeDefaults = true
            prettyPrint = true
            prettyPrintIndent = "  "
            ignoreUnknownKeys = true
        }

        val config: CoreConfig = File(Main.configFile).let { file ->
            if (!file.parentFile.exists()) file.parentFile.mkdirs()
            if (!file.exists()) file.writeText(json.encodeToString(CoreConfig()))
            json.decodeFromString(serializer(), file.readText())
        }
    }

    fun executePreExecuteScript() {
        preExecuteScript.forEach { script ->
            ProcessExecutor.executeCommandStreamedOutput(File("."), *script.toTypedArray())
        }
    }
}
