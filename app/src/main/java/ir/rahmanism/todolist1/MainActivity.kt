package ir.rahmanism.todolist1

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import ir.rahmanism.todolist1.databinding.ActivityMainBinding

// val TAG: String = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMain = ActivityMainBinding.inflate(LayoutInflater.from(this.applicationContext))
        setContentView(activityMain.root)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "todolist1.db"
        )
            // https://stackoverflow.com/a/76103274/3144631
            // Room Database does not allow you to execute a database IO operation
            // in the Main thread so we add the following line
            .allowMainThreadQueries()
            .build()

        todoAdapter = TodoAdapter(db)

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