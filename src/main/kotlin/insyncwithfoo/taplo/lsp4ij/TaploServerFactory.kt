package insyncwithfoo.taplo.lsp4ij

import com.intellij.openapi.project.Project
import com.redhat.devtools.lsp4ij.LanguageServerEnablementSupport
import com.redhat.devtools.lsp4ij.LanguageServerFactory
import com.redhat.devtools.lsp4ij.client.LanguageClientImpl
import com.redhat.devtools.lsp4ij.server.StreamConnectionProvider
import insyncwithfoo.taplo.configurations.RunningMode
import insyncwithfoo.taplo.configurations.TaploConfigurationService
import insyncwithfoo.taplo.configurations.taploConfigurations
import insyncwithfoo.taplo.configurations.taploExecutable


internal const val SERVER_ID = "taplo"


internal class TaploServerFactory : LanguageServerFactory, LanguageServerEnablementSupport {
    
    override fun isEnabled(project: Project): Boolean {
        val configurations = taploConfigurations
        val executable = taploExecutable
        
        return configurations.runningMode == RunningMode.LSP4IJ && executable != null
    }
    
    override fun setEnabled(enabled: Boolean, project: Project) {
        TaploConfigurationService.getInstance().state.apply {
            runningMode = when {
                enabled -> RunningMode.LSP4IJ
                else -> RunningMode.DISABLED
            }
        }
    }
    
    override fun createConnectionProvider(project: Project): StreamConnectionProvider {
        return TaploServerConnectionProvider.create(project)
    }
    
    override fun createLanguageClient(project: Project): LanguageClientImpl {
        return TaploServerClient(project)
    }
    
}
