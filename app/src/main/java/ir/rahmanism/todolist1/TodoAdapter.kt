package ir.rahmanism.todolist1

import android.view.LayoutInflater
import android.view.ViewGroup
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

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.bind(curTodo)
    }
}