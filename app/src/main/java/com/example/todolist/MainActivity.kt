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

        val adapter = TodoAdapter(listener)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        todoViewModel.todoItems.observe(this) {
            adapter.submitList(it)
        }

//        setRecyclerView()

    }
//    private fun setRecyclerView() {
//        val adapter = TodoAdapter(listener)
//        binding.recyclerView.adapter = adapter
//        todoViewModel.todoItems.observe(this) {
//            adapter.submitList(it)
//            binding.recyclerView.apply {
//                layoutManager = LinearLayoutManager(applicationContext)
//            }
//
//        }
//    }


    private val listener = object : OnClickListener {
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