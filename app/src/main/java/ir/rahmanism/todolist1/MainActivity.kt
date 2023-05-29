package ir.rahmanism.todolist1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import ir.rahmanism.todolist1.databinding.ActivityMainBinding

val TAG : String = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, ">>>>>before super.OnCreate")
        super.onCreate(savedInstanceState)
        Log.d(TAG, ">>>>>after super.OnCreate")
        var activityMain = ActivityMainBinding.inflate(LayoutInflater.from(this.applicationContext))
        setContentView(activityMain.root)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "todolist1"
        ).build()

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