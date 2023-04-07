package nolambda.techstack.app.home

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class HomeScreenState(
    val items: List<AppItem> = emptyList(),
    val isLoading: Boolean = true,
    val error: Throwable? = null,
) {
    fun setContent(items: List<AppItem>) = copy(items = items, isLoading = false)
    fun setError(error: Throwable) = copy(error = error, isLoading = false)
}

class HomeScreenModel : StateScreenModel<HomeScreenState>(HomeScreenState()) {

    init {
        coroutineScope.launch {
            delay(2000)
            loadData()
        }
    }

    private fun loadData() {
        mutableState.value = mutableState.value.setContent(
            items = listOf(
                AppItem(
                    appName = "App Name",
                    appId = "com.nolambda.app",
                    appIcon = "https://99designs-blog.imgix.net/blog/wp-content/uploads/2017/04/attachment_82290822-e1492536097660.png?auto=format&q=60&fit=max&w=930",
                    versionName = "1.0.0",
                    versionCode = "1",
                    architecture = "x86",
                    nativeLibs = "lib1.so, lib2.so",
                    targetSdk = "29",
                ),
                AppItem(
                    appName = "Tiket",
                    appId = "com.nolambda.app",
                    appIcon = "https://99designs-blog.imgix.net/blog/wp-content/uploads/2017/04/attachment_82290822-e1492536097660.png?auto=format&q=60&fit=max&w=930",
                    versionName = "1.0.0",
                    versionCode = "1",
                    architecture = "32 Bit",
                    nativeLibs = "lib1.so, lib2.so",
                    targetSdk = "33",
                ),
                AppItem(
                    appName = "Tiket",
                    appId = "com.nolambda.app",
                    appIcon = "https://99designs-blog.imgix.net/blog/wp-content/uploads/2017/04/attachment_82290822-e1492536097660.png?auto=format&q=60&fit=max&w=930",
                    versionName = "1.0.0",
                    versionCode = "1",
                    architecture = "32 Bit",
                    nativeLibs = "lib1.so, lib2.so",
                    targetSdk = "33",
                ),
            )
        )
    }

}
