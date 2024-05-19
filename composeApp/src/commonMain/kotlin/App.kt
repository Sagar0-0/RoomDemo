import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import database.NotesDao
import database.NotesEntity
import kotlinx.coroutines.launch

@Composable
fun App(
    notesDao: NotesDao
) {
    MaterialTheme {
        MainContent(notesDao)
    }
}

@Composable
fun MainContent(notesDao: NotesDao) {

    val notes by notesDao.getAll().collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    val (value, onValueChange) = remember {
        mutableStateOf("")
    }

    Column {
        TextField(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            value = value,
            onValueChange = onValueChange
        )
        Button(onClick = {
            scope.launch {
                notesDao.insert(NotesEntity(value))
            }
        }) {
            Text("Insert")
        }
        LazyColumn {
            items(notes) { item ->
                Text(
                    modifier = Modifier.padding(10.dp).clickable {
                        scope.launch {
                            notesDao.delete(item)
                        }
                    },
                    text = item.note
                )
            }
        }
    }
}