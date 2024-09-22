package insyncwithfoo.taplo.configurations

import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.dsl.builder.Cell
import com.intellij.ui.dsl.builder.Row
import com.intellij.ui.dsl.builder.panel
import insyncwithfoo.taplo.bindText
import insyncwithfoo.taplo.emptyText
import insyncwithfoo.taplo.findExecutableInPath
import insyncwithfoo.taplo.makeFlexible
import insyncwithfoo.taplo.message
import insyncwithfoo.taplo.singleFileTextField


private fun Row.executableInput(block: Cell<TextFieldWithBrowseButton>.() -> Unit) =
    singleFileTextField().makeFlexible().apply(block)


internal fun makePanel(state: TaploConfigurations) = panel {
    row(message("configurations.executable.label")) {
        executableInput {
            val detected = findExecutableInPath("taplo")?.toString()
            
            bindText(state::executable) { detected.orEmpty() }
            emptyText = detected.orEmpty()
        }
    }
}
