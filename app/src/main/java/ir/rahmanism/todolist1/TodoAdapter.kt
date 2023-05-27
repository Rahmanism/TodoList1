package ir.rahmanism.todolist1

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.rahmanism.todolist1.databinding.TodoItemBinding

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

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
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isDone
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.bind(curTodo)
        holder.itemBinding.apply {
            toggleStrikeThrough(todoTitleTv, curTodo.isDone)
            isDoneCb.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(todoTitleTv, isChecked)
                curTodo.isDone = !curTodo.isDone
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