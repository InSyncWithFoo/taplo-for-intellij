package insyncwithfoo.taplo.configurations

import com.intellij.openapi.components.BaseState
import com.intellij.util.xmlb.XmlSerializerUtil
import insyncwithfoo.taplo.Labeled
import insyncwithfoo.taplo.message


internal fun <S : BaseState> S.copy(): S =
    XmlSerializerUtil.createCopy(this)


internal enum class RunningMode(override val label: String) : Labeled {
    DISABLED(message("configurations.runningMode.disabled")),
    LSP4IJ(message("configurations.runningMode.lsp4ij")),
    LSP(message("configurations.runningMode.lsp"));
}


internal class TaploConfigurations : BaseState() {
    var executable by string(null)
    var runningMode by enum(RunningMode.LSP)
}
