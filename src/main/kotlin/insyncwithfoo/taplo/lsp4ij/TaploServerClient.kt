package insyncwithfoo.taplo.lsp4ij

import com.intellij.openapi.project.Project
import com.redhat.devtools.lsp4ij.client.LanguageClientImpl


internal class TaploServerClient(project: Project) : LanguageClientImpl(project) {
    
    override fun createSettings() = Object()
    
}
