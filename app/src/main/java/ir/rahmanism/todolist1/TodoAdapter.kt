package ir.rahmanism.todolist1

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.rahmanism.todolist1.databinding.TodoItemBinding

class TodoAdapter(
    private val db: AppDatabase
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    val todoDao = db.todoDao()
    val todos: MutableList<Todo> = todoDao.getAll()

    class TodoViewHolder(val itemBinding: TodoItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(todoItem: Todo) {
            itemBinding.apply {
                todoTitleTv.text = todoItem.title
                isDoneCb.isChecked = todoItem.isDone
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            TodoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
        todoDao.insertAll(listOf(todo))
    }

    fun deleteDoneTodos() {
        val toRemove = todos.filter { it.isDone }
        todoDao.deleteTodos(toRemove)
        todos.removeAll { todo ->
            todo.isDone
        }
        notifyDataSetChanged()
    }

    override fun getItemCount() = todos.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.bind(curTodo)
        holder.itemBinding.apply {
            toggleStrikeThrough(todoTitleTv, curTodo.isDone)
            isDoneCb.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(todoTitleTv, isChecked)
                curTodo.isDone = !curTodo.isDone
                todoDao.updateTodo(curTodo)
            }
        }
    }

    private fun toggleStrikeThrough(todoTitleTv: TextView, isDone: Boolean) {
        if (isDone) {
            todoTitleTv.paintFlags = todoTitleTv.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            todoTitleTv.paintFlags = todoTitleTv.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}