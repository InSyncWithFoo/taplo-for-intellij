package insyncwithfoo.taplo

import com.intellij.openapi.vfs.VirtualFile


// https://github.com/JetBrains/intellij-community/blob/d3bf2e0e/plugins/toml/resources/META-INF/plugin.xml#L28
private val knownTOMLFiles = listOf(
    "Cargo.lock", "Cargo.toml.orig", "Gopkg.lock",
    "Pipfile", "poetry.lock", "uv.lock"
)


internal val VirtualFile.isTOMLFile: Boolean
    get() = extension == "toml" || name in knownTOMLFiles


internal val VirtualFile.isTaploToml: Boolean
    get() = name == "taplo.toml" || name == ".taplo.toml"
