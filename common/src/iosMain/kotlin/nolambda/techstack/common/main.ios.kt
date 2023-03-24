package nolambda.techstack.common


import androidx.compose.ui.window.Application
import nolambda.techstack.common.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController =
    Application("Example Application") {
        App()
    }
