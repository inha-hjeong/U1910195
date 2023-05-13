package com.algorist.noteapp.screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.algorist.noteapp.R
import com.algorist.noteapp.component.NoteButton
import com.algorist.noteapp.component.NoteInputText
import com.algorist.noteapp.component.NoteRow
import com.algorist.noteapp.model.Note
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit,
){
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon")
            },
            backgroundColor = Color(android.graphics.Color.BLUE)
        )
        // Content
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            NoteInputText(
                text = title,
                label = "Title",
                onTextChange = {
                               if (it.all { c -> c.isLetterOrDigit() || c.isWhitespace() }){
                                   title = it
                               }
                },
                modifier = Modifier.padding(8.dp)
            )
            NoteInputText(
                text = description,
                label = "Description",
                onTextChange = {
                               if (it.all { c -> c.isLetterOrDigit() || c.isWhitespace() }){
                                   description = it
                               }
                },
                modifier = Modifier.padding(9.dp)
            )
            NoteButton(text = "Save", onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    onAddNote(
                        Note(
                            title = title,
                            descriptor = description,
                        )
                    )
                    Toast
                        .makeText(context, "Note $title is created", Toast.LENGTH_SHORT)
                        .show()
                    title = ""
                    description = ""
                } else {
                    val message = if (title.isEmpty()){
                        "empty title is not allowed"
                    } else {
                        "empty description is not allowed"
                    }
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            })
        }
        Divider(modifier = Modifier.padding(7.dp))
        LazyColumn {
            items(notes) {note ->
                NoteRow(
                    note = note,
                    onNoteClicked = {
                        onRemoveNote(it)
                        Toast
                            .makeText(
                                context,
                                "Note ${it.title} is removed",
                                Toast.LENGTH_SHORT,
                            )
                            .show()
                    },
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen(notes = emptyList(), onAddNote = {}, onRemoveNote = {})
}