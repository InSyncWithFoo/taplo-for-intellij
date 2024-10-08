package insyncwithfoo.taplo.misc

import com.intellij.ide.FileIconProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import insyncwithfoo.taplo.Icon
import insyncwithfoo.taplo.isTaploToml


internal class TaploFileIconProvider : FileIconProvider {
    
    override fun getIcon(file: VirtualFile, flags: Int, project: Project?) =
        when {
            file.isTaploToml -> Icon.TINY_16
            else -> null
        }
    
}
