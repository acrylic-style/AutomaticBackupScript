package net.azisaba.automaticbackupscript

import com.github.ajalt.clikt.core.CoreCliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import net.azisaba.automaticbackupscript.command.BackupCommand

object Main {
    init {
        System.setProperty("org.slf4j.simpleLogger.logFile", "System.out")
    }

    var configFile: String = "config/core.json"

    private class AutomaticBackupScriptCommand : CoreCliktCommand(name = "AutomaticBackupScript") {
        private val coreConfigFile by option("--config-file", "-c", help = "Config file (core)").default("config/core.json")
        override val invokeWithoutSubcommand: Boolean = true

        override fun run() {
            configFile = coreConfigFile
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        AutomaticBackupScriptCommand()
            .subcommands(BackupCommand)
            .main(args)
    }
}
