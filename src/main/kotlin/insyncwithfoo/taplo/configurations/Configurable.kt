package insyncwithfoo.taplo.configurations

import com.intellij.openapi.options.Configurable


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
    }
    
}
