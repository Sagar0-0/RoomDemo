import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import database.getDao

fun MainViewController() = ComposeUIViewController {

    val dao = remember { getDao() }
    App(dao)
}