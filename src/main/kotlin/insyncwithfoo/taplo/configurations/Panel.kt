package insyncwithfoo.taplo.configurations

import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.dsl.builder.Cell
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.Row
import com.intellij.ui.dsl.builder.panel
import insyncwithfoo.taplo.bindSelected
import insyncwithfoo.taplo.bindText
import insyncwithfoo.taplo.emptyText
import insyncwithfoo.taplo.findExecutableInPath
import insyncwithfoo.taplo.lsp4ijIsAvailable
import insyncwithfoo.taplo.lspIsAvailable
import insyncwithfoo.taplo.makeFlexible
import insyncwithfoo.taplo.message
import insyncwithfoo.taplo.radioButtonFor
import insyncwithfoo.taplo.singleFileTextField


private fun Row.executableInput(block: Cell<TextFieldWithBrowseButton>.() -> Unit) =
    singleFileTextField().makeFlexible().apply(block)


private fun Panel.runningModeInputGroup(block: Panel.() -> Unit) =
    buttonsGroup(init = block)


internal fun makePanel(state: TaploConfigurations) = panel {
    row(message("configurations.executable.label")) {
        executableInput {
            val detected = findExecutableInPath("taplo")?.toString()
            
            bindText(state::executable) { detected.orEmpty() }
            emptyText = detected.orEmpty()
        }
    }
    
    val runningModeInputGroup = runningModeInputGroup {
        row(message("configurations.runningMode.label")) {
            radioButtonFor(RunningMode.DISABLED)
            radioButtonFor(RunningMode.LSP4IJ) { label ->
                message("configurations.runningMode.unavailable", label).takeUnless { lsp4ijIsAvailable }
            }
            radioButtonFor(RunningMode.LSP) { label ->
                message("configurations.runningMode.unavailable", label).takeUnless { lspIsAvailable }
            }
        }
    }
    runningModeInputGroup.bindSelected(state::runningMode)
}
