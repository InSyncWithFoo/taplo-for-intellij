# Taplo for IntelliJ

[![Build](https://github.com/InSyncWithFoo/taplo-for-intellij/actions/workflows/build.yaml/badge.svg)][4]
[![Docs](https://github.com/InSyncWithFoo/taplo-for-intellij/actions/workflows/docs.yaml/badge.svg)][5]
[![Version](https://img.shields.io/jetbrains/plugin/v/25387)][6]
[![Rating](https://img.shields.io/jetbrains/plugin/r/rating/25387)][7]
[![Downloads](https://img.shields.io/jetbrains/plugin/d/25387)][8]

<!-- Plugin description -->
[Taplo][1], also known as "[Even better TOML][2]",
is a language server for TOML.


## Usage

You must [have Taplo installed][3] to use this plugin.
Note that the installation must not come from NPM.
The executable will then be detected automatically.

Additionally, if you are using IntelliJ IDEA or PyCharm Community Edition,
the <i>LSP4IJ</i> plugin must be installed and enabled.
Once it is installed, change the running mode to <i>LSP4IJ</i>.


## Logging

You are strongly encouraged to enable language server logging.
This will allow corresponding logs to be recorded in `idea.log`
for further analysis should a problem arises.

Add the following line to the <b>Help</b> |
<b>Diagnostic Tools</b> | <b>Debug Log Settings</b> panel:

```text
com.intellij.platform.lsp
```


  [1]: https://github.com/tamasfe/taplo
  [2]: https://marketplace.visualstudio.com/items?itemName=tamasfe.even-better-toml
  [3]: https://taplo.tamasfe.dev/cli/installation/binary.html
<!-- Plugin description end -->


## Installation

This plugin is [available on the Marketplace][6].
You can also download the ZIP files manually from [the <i>Releases</i> tab][9],
[the `build` branch][10] or [the <i>Actions</i> tab][11]
and follow the instructions described [here][12].

Currently supported versions:
2024.1 (build 241.14494.241) - 2024.3.* (build 243.*).


## Credits

Parts of this repository were taken or derived from:

* [@tamasfe/taplo][1]
* [@JetBrains/intellij-community][13]
* [@JetBrains/intellij-platform-plugin-template][14]
* [@koxudaxi/ruff-pycharm-plugin][15]


  [4]: https://github.com/InSyncWithFoo/taplo-for-intellij/actions/workflows/build.yaml
  [5]: https://github.com/InSyncWithFoo/taplo-for-intellij/actions/workflows/docs.yaml
  [6]: https://plugins.jetbrains.com/plugin/25387/versions
  [7]: https://plugins.jetbrains.com/plugin/25387/reviews
  [8]: https://plugins.jetbrains.com/plugin/25387
  [9]: https://github.com/InSyncWithFoo/taplo-for-intellij/releases
  [10]: https://github.com/InSyncWithFoo/taplo-for-intellij/tree/build
  [11]: https://github.com/InSyncWithFoo/taplo-for-intellij/actions/workflows/build.yaml
  [12]: https://www.jetbrains.com/help/pycharm/managing-plugins.html#install_plugin_from_disk
  [13]: https://github.com/JetBrains/intellij-community
  [14]: https://github.com/JetBrains/intellij-platform-plugin-template
  [15]: https://github.com/koxudaxi/ruff-pycharm-plugin
