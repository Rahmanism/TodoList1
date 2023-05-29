package ir.rahmanism.todolist1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "is_done")
    var isDone: Boolean = false,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)