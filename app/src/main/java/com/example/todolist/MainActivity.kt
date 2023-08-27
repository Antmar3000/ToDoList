package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import androidx.activity.viewModels
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val todoViewModel: TodoViewModel by viewModels {
        TodoItemModelFactory((application as TodoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonAddTodo.setOnClickListener {
            NewTodoFragment(null).show(supportFragmentManager, Constances.NEW_TODO_ITEM_TAG)
        }

        setRecyclerView()

    }

    private fun setRecyclerView() {
        todoViewModel.todoItems.observe(this) {
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TodoAdapter(it, clickListener)

            }
        }
    }


    private val clickListener = object : OnClickListener {
        override fun editTodoItem(todoItem: TodoItem) {
            NewTodoFragment(todoItem).show(supportFragmentManager, Constances.EDIT_TODO_ITEM_TAG)
        }

        override fun deleteTodoItem(todoItem: TodoItem) {
            todoViewModel.deleteTodoItem(todoItem)
        }

        override fun expireTodoItem(todoItem: TodoItem) {
            todoItem.expirationDateString = TodoItem.dateFormatter.format(LocalDate.now())
            todoViewModel.updateTodoItem(todoItem)
        }

        override fun setChecked(todoItem: TodoItem, isChecked: Boolean) {
            if (isChecked) {
                todoItem.isChecked = true
                todoViewModel.updateTodoItem(todoItem)
            } else todoItem.isChecked = false
            todoViewModel.updateTodoItem(todoItem)
        }

    }


}