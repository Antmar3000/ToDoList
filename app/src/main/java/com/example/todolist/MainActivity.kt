package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity(), OnClickListener  {

    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val todoViewModel : TodoViewModel by lazy { ViewModelProvider(this).get(TodoViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonAddTodo.setOnClickListener {
            NewTodoFragment(null).show(supportFragmentManager, "newTodoItemTag")
        }

        setRecyclerView()

    }

    private fun setRecyclerView (){
        todoViewModel.todoItems.observe(this){
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TodoAdapter(it, this@MainActivity)

            }
        }
    }


    override fun editTodoItem(todoItem: TodoItem) {
        NewTodoFragment(todoItem).show(supportFragmentManager, "newTodoItemTag")
    }

    override fun expireTodoItem(todoItem: TodoItem) {
        TODO("Not yet implemented")
    }

    override fun removeTodoItem(position : Int) {
        todoViewModel.removeTodoItem(position)
    }


}