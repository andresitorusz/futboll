package andresitorusz.futboll.utils

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers


open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
}