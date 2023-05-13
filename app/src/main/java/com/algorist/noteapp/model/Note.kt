package com.algorist.noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.algorist.noteapp.util.DateConverter
import com.algorist.noteapp.util.UUIDConverter
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val descriptor: String,
    @ColumnInfo(name = "entry_date")
    val entryDate: Date = Date(),
)
