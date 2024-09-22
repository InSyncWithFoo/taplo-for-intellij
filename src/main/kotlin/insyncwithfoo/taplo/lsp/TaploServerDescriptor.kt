package insyncwithfoo.taplo.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import com.intellij.platform.navbar.frontend.message
import insyncwithfoo.taplo.configurations.taploConfigurations
import insyncwithfoo.taplo.isTOMLFile
import insyncwithfoo.taplo.path
import java.nio.file.Path


@Suppress("UnstableApiUsage")
internal class TaploServerDescriptor(project: Project, private val executable: Path) :
    ProjectWideLspServerDescriptor(project, PRESENTABLE_NAME) {
    
    val configurations = taploConfigurations
    
    override fun isSupportedFile(file: VirtualFile) =
        file.isTOMLFile
    
    override fun createCommandLine() = GeneralCommandLine().apply {
        withWorkingDirectory(project.path)
        withCharset(Charsets.UTF_8)
        
        withExePath(executable.toString())
        addParameters("lsp", "stdio")
    }
    
    companion object {
        private val PRESENTABLE_NAME = message("languageServer.presentableName")
    }
    
}
