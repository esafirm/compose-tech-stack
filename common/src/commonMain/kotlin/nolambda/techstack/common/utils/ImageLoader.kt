package nolambda.techstack.common.utils

import com.seiko.imageloader.ImageLoaderConfigBuilder
import com.seiko.imageloader.util.DebugLogger
import com.seiko.imageloader.util.LogPriority

/**
 * Common configuration for [ImageLoaderConfigBuilder]
 *
 * This should be called in all platforms
 */
fun ImageLoaderConfigBuilder.commonConfig() {
    logger = DebugLogger(LogPriority.VERBOSE)
}
