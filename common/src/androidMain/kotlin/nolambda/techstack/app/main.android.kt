package nolambda.techstack.app

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.cache.memory.maxSizePercent
import com.seiko.imageloader.component.setupDefaultComponents
import nolambda.techstack.app.utils.commonConfig
import okio.Path.Companion.toOkioPath

@Composable
fun Application() {
    val context = LocalContext.current
    CompositionLocalProvider(LocalImageLoader provides generateImageLoader(context)) {
        App()
    }
}

private fun generateImageLoader(context: Context): ImageLoader {
    val appContext = context.applicationContext

    return ImageLoader {
        commonConfig()
        components {
            setupDefaultComponents(appContext)
        }
        interceptor {
            memoryCacheConfig {
                // Set the max size to 25% of the app's available memory.
                maxSizePercent(appContext, 0.25)
            }
            diskCacheConfig {
                directory(context.cacheDir.resolve("image_cache").toOkioPath())
                maxSizeBytes(512L * 1024 * 1024) // 512MB
            }
        }
    }
}
