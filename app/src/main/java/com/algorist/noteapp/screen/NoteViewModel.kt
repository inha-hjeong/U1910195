package com.algorist.noteapp.screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.algorist.noteapp.model.Note
import com.algorist.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository): ViewModel() {
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.getAllNotes()
                .distinctUntilChanged()
                .collect {listOfNotes ->
                    if (listOfNotes.isEmpty()) {
                        Log.d("init notes", "list of notes is empty or null: ")
                    } else {
                        _noteList.value = listOfNotes
                    }
                }
        }
    }

    fun addNote(note: Note) = viewModelScope.launch { noteRepository.addNote(note) }

    fun removeNote(note: Note) = viewModelScope.launch { noteRepository.deleteNote(note) }

    fun updateNote(note: Note) = viewModelScope.launch { noteRepository.updateNote(note) }

    fun getNoteById(id: UUID) = viewModelScope.launch { noteRepository.getNoteById(id) }

    fun deleteAllNotes() = viewModelScope.launch { noteRepository.deleteAllNotes() }

    fun deleteNote(note: Note) = viewModelScope.launch { noteRepository.deleteNote(note) }
}