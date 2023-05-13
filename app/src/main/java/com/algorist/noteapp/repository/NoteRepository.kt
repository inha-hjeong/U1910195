package com.algorist.noteapp.repository

import com.algorist.noteapp.data.NoteDatabaseDao
import com.algorist.noteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import java.util.UUID
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {
    suspend fun addNote(note: Note) = noteDatabaseDao.addNote(note = note)
    suspend fun deleteNote(note: Note) = noteDatabaseDao.deleteNoteById(id = note.id.toString())
    suspend fun updateNote(note: Note) = noteDatabaseDao.updateNote(note = note)
    suspend fun getNoteById(id: UUID) = noteDatabaseDao.getNoteById(id = id.toString())
    suspend fun deleteAllNotes() = noteDatabaseDao.deleteAllNotes()
    fun getAllNotes(): Flow<List<Note>> = noteDatabaseDao.getAllNotes().flowOn(Dispatchers.IO).conflate()
}