package insyncwithfoo.taplo

import com.intellij.execution.configurations.PathEnvironmentVariableUtil
import com.intellij.openapi.util.SystemInfo
import com.intellij.openapi.vfs.VirtualFile
import java.nio.file.InvalidPathException
import java.nio.file.Path
import kotlin.io.path.exists


private val Path.isEmpty: Boolean
    get() = this.toString() == ""


internal fun String.toPathOrNull() =
    try {
        Path.of(this).takeUnless { it.isEmpty }
    } catch (_: InvalidPathException) {
        null
    }


internal fun String.toPathIfItExists() =
    this.toPathOrNull()?.normalize()?.toNullIfNotExists()


internal fun String.toOSDependentFileName() = when {
    SystemInfo.isWindows -> "$this.exe"
    else -> this
}


internal fun Path.toNullIfNotExists() =
    this.takeIf { it.exists() }


internal fun findExecutableInPath(name: String): Path? {
    return PathEnvironmentVariableUtil.findInPath(name.toOSDependentFileName())?.toPath()
}


// https://github.com/JetBrains/intellij-community/blob/d3bf2e0e/plugins/toml/resources/META-INF/plugin.xml#L28
private val knownTOMLFiles = listOf(
    "Cargo.lock", "Cargo.toml.orig",
    "Pipfile", "Gopkg.lock", "poetry.lock", "uv.lock"
)


internal val VirtualFile.isTOMLFile: Boolean
    get() = extension == "toml" || name in knownTOMLFiles
