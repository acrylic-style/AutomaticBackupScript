package net.azisaba.automaticbackupscript.command

import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.core.CoreCliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import kotlinx.coroutines.runBlocking
import net.azisaba.automaticbackupscript.Application
import net.azisaba.automaticbackupscript.config.BackupConfig
import net.azisaba.automaticbackupscript.config.CoreConfig
import java.io.File

object BackupCommand : CoreCliktCommand(name = "backup") {
    private val configPath by option("--config-file", "-f", help = "Configuration file").default("config/backup.json")

    override fun help(context: Context): String = "Backup files"

    override fun run() {
        CoreConfig.config.executePreExecuteScript()
        val configFile = File(configPath)
        BackupConfig.load(configFile)
        if (BackupConfig.config.webhookUrl == "insert url here") {
            System.err.println("Please edit ${configFile.absolutePath} and run the application!")
            return
        }
        runBlocking {
            Application().backup()
        }
    }
}
