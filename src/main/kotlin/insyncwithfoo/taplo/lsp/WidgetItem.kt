package insyncwithfoo.taplo.lsp

import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServer
import com.intellij.platform.lsp.api.lsWidget.LspServerWidgetItem
import insyncwithfoo.taplo.Icon
import insyncwithfoo.taplo.configurations.Configurable


@Suppress("UnstableApiUsage")
internal class WidgetItem(lspServer: LspServer, currentFile: VirtualFile?) :
    LspServerWidgetItem(lspServer, currentFile, Icon.TINY_16, Configurable::class.java)
