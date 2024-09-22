package insyncwithfoo.taplo

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.extensions.PluginId
import com.intellij.platform.lsp.api.LspServerSupportProvider


internal val lsp4ijIsAvailable: Boolean
    get() {
        val pluginID = PluginId.getId("com.redhat.devtools.lsp4ij")
        return PluginManagerCore.run { isPluginInstalled(pluginID) && !isDisabled(pluginID) }
    }


@Suppress("UnstableApiUsage")
internal val lspIsAvailable by lazy {
    try {
        LspServerSupportProvider
        true
    } catch (_: NoClassDefFoundError) {
        false
    }
}
