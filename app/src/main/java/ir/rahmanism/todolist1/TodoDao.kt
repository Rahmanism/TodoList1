package ir.rahmanism.todolist1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    @Query("select * from todo")
    fun getAll(): MutableList<Todo>

    @Query("select * from todo where uid In (:todoIds)")
    fun loadAllByIds(todoIds: List<Int>): MutableList<Todo>

    @Query("select * from todo where title like :title limit 1")
    fun findByTitle(title: String): Todo

    @Insert
    fun insertAll(todos: List<Todo>)

    @Delete
    fun delete(todo: Todo)

    @Delete
    fun deleteTodos(todos: List<Todo>)

    @Update
    fun updateTodo(todo: Todo)
}