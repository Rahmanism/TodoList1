package ir.rahmanism.todolist1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
    @Query("select * from todo")
    fun getAll(): MutableList<Todo>

    @Query("select * from todo where uid In (:todoIds)")
    fun loadAllByIds(todoIds: IntArray): MutableList<Todo>

    @Query("select * from todo where title like :title limit 1")
    fun findByTitle(title: String): Todo

    @Insert
    fun insertAll(vararg todos: Todo)

    @Delete
    fun delete(todo: Todo)
}