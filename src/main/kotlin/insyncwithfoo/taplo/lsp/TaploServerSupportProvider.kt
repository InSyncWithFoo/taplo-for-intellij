package insyncwithfoo.taplo.lsp

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServer
import com.intellij.platform.lsp.api.LspServerSupportProvider
import com.intellij.platform.lsp.api.LspServerSupportProvider.LspServerStarter
import insyncwithfoo.taplo.configurations.RunningMode
import insyncwithfoo.taplo.configurations.taploConfigurations
import insyncwithfoo.taplo.configurations.taploExecutable
import insyncwithfoo.taplo.isTOMLFile


@Suppress("UnstableApiUsage")
internal class TaploServerSupportProvider : LspServerSupportProvider {
    
    override fun createLspServerWidgetItem(lspServer: LspServer, currentFile: VirtualFile?) =
        WidgetItem(lspServer, currentFile)
    
    override fun fileOpened(project: Project, file: VirtualFile, serverStarter: LspServerStarter) {
        val runningMode = taploConfigurations.runningMode
        
        if (runningMode == RunningMode.LSP && file.isTOMLFile) {
            val executable = taploExecutable ?: return
            serverStarter.ensureServerStarted(TaploServerDescriptor(project, executable))
        }
    }
    
}
