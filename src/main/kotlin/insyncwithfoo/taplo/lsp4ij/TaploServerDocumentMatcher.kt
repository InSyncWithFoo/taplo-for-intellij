package insyncwithfoo.taplo.lsp4ij

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.redhat.devtools.lsp4ij.AbstractDocumentMatcher
import insyncwithfoo.taplo.isTOMLFile


internal class TaploServerDocumentMatcher : AbstractDocumentMatcher() {
    
    override fun match(file: VirtualFile, project: Project) =
        file.isTOMLFile
    
}
