package ir.rahmanism.todolist1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import ir.rahmanism.todolist1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var activityMain = ActivityMainBinding.inflate(LayoutInflater.from(this.applicationContext))
        setContentView(activityMain.root)
        todoAdapter = TodoAdapter(mutableListOf())

        activityMain.apply {
            todoItemsRv.adapter = todoAdapter
            todoItemsRv.layoutManager = LinearLayoutManager(this@MainActivity)

            addTodoBtn.setOnClickListener {
                val todoTitle = todoTitleEt.text.toString()
                if (todoTitle.isNotEmpty()) {
                    val todo = Todo(todoTitle)
                    todoAdapter.addTodo(todo)
                    todoTitleEt.text.clear()
                }
            }

            deleteDoneBtn.setOnClickListener {
                todoAdapter.deleteDoneTodos()
            }
        }
    }
}