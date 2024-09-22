package insyncwithfoo.taplo.configurations

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.LspServerManager
import com.intellij.util.xmlb.XmlSerializerUtil
import com.redhat.devtools.lsp4ij.LanguageServerManager
import insyncwithfoo.taplo.isNormal
import insyncwithfoo.taplo.lsp.TaploServerSupportProvider
import insyncwithfoo.taplo.lsp4ij.SERVER_ID
import insyncwithfoo.taplo.lsp4ijIsAvailable
import insyncwithfoo.taplo.lspIsAvailable
import insyncwithfoo.taplo.openProjects


@Suppress("UnstableApiUsage")
private val Project.lspServerManager: LspServerManager
    get() = LspServerManager.getInstance(this)


@Suppress("UnstableApiUsage")
private fun Project.restartNativeServersIfSoChoose() {
    if (lspIsAvailable && this.isNormal) {
        lspServerManager.stopAndRestartIfNeeded(TaploServerSupportProvider::class.java)
    }
}


private val Project.languageServerManager: LanguageServerManager
    get() = LanguageServerManager.getInstance(this)


private fun Project.stopLSP4IJServers(disable: Boolean = false) {
    val options = LanguageServerManager.StopOptions().apply {
        isWillDisable = disable
    }
    
    languageServerManager.stop(SERVER_ID, options)
}


private fun Project.startLSP4IJServers(enable: Boolean = false) {
    val options = LanguageServerManager.StartOptions().apply {
        isWillEnable = enable
    }
    
    languageServerManager.start(SERVER_ID, options)
}


private fun Project.toggleLSP4IJServersAccordingly() {
    if (!lsp4ijIsAvailable) {
        return
    }
    
    stopLSP4IJServers()
    
    if (taploConfigurations.runningMode == RunningMode.LSP4IJ) {
        startLSP4IJServers()
    }
}



internal class Configurable : Configurable {
    
    private val service = TaploConfigurationService.getInstance()
    private val state = service.state.copy()
    private val panel by lazy { makePanel(state) }
    
    override fun getDisplayName() = "Taplo"
    
    override fun createComponent() = panel
    
    override fun isModified() = panel.isModified()
    
    override fun reset() {
        panel.reset()
    }
    
    override fun apply() {
        panel.apply()
        XmlSerializerUtil.copyBean(state, service.state)
        
        openProjects.forEach { project ->
            project.restartNativeServersIfSoChoose()
            project.toggleLSP4IJServersAccordingly()
        }
    }
    
}
