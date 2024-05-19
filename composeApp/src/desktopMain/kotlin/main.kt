import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import database.getDao

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "RoomDemo",
    ) {
        val dao = remember { getDao() }
        App(dao)
    }
}