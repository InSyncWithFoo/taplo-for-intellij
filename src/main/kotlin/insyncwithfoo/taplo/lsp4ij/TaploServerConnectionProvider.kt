package insyncwithfoo.taplo.lsp4ij

import com.intellij.openapi.project.Project
import com.redhat.devtools.lsp4ij.server.ProcessStreamConnectionProvider
import insyncwithfoo.taplo.configurations.taploExecutable
import insyncwithfoo.taplo.path


internal class TaploServerConnectionProvider(commands: List<String>, workingDirectory: String?) :
    ProcessStreamConnectionProvider(commands, workingDirectory) {
    
    companion object {
        fun create(project: Project): TaploServerConnectionProvider {
            val executable = taploExecutable!!
            
            val fragments: List<String> = listOf(executable.toString(), "lsp", "stdio")
            val workingDirectory = project.path?.toString()
            
            return TaploServerConnectionProvider(fragments, workingDirectory)
        }
    }
    
}
