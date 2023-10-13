package com.example.todolist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.adapters.AllTodoAdapter
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.FragmentMainListBinding
import com.example.todolist.fragments.AllListFragment
import com.example.todolist.fragments.FunListFragment
import com.example.todolist.fragments.MainListFragment
import com.example.todolist.fragments.NewTodoFragment
import com.example.todolist.fragments.WorkListFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val todoViewModel: TodoViewModel by viewModels {
        TodoItemModelFactory((application as TodoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        todoViewModel.allTodoItems.observe(this) {
            // wtf?
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.placeholder, AllListFragment.newInstance()).commit()

        binding.bottomNavigation.selectedItemId = R.id.item_all

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_all -> {
                    showRecyclerFragment(AllListFragment.newInstance())
                }

                R.id.item_main -> {
                    showRecyclerFragment(MainListFragment.newInstance())
                }

                R.id.item_work -> {
                    showRecyclerFragment(WorkListFragment.newInstance())
                }

                R.id.item_fun -> {
                    showRecyclerFragment(FunListFragment.newInstance())
                }
            }
            true
        }


        binding.buttonAddTodo.setOnClickListener {
            NewTodoFragment(null).show(supportFragmentManager, Constances.NEW_TODO_ITEM_TAG)
        }


//        val adapter = AllTodoAdapter(listener)
//        binding.recyclerView.adapter = adapter
//        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
//        todoViewModel.todoItems.observe(this) {
//            adapter.submitList(it)
//        }

//        setRecyclerView()

    }
//    private fun setRecyclerView() {
//        todoViewModel.allTodoItems.observe(this) {
//            binding.recyclerView.apply {
//                layoutManager = LinearLayoutManager(applicationContext)
//                adapter = AllTodoAdapter (it, listener)
//            }
//
//        }
//    }

    private fun showRecyclerFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.placeholder, fragment).commit()
    }


    val listener = object : OnClickListener {
        override fun editTodoItem(todoItem: TodoItem) {
            NewTodoFragment(todoItem).show(supportFragmentManager, Constances.EDIT_TODO_ITEM_TAG)
        }

        override fun deleteTodoItem(todoItem: TodoItem) {
            todoViewModel.deleteTodoItem(todoItem)
        }

        override fun expireTodoItem(todoItem: TodoItem) {
            todoViewModel.updateTodoItem(
                todoItem.copy(
                    expirationDateString = TodoItem.dateFormatter.format(
                        LocalDate.now()
                    )
                )
            )
        }

        override fun setChecked(todoItem: TodoItem, isChecked: Boolean) {
            todoViewModel.updateTodoItem(todoItem.copy(isChecked = isChecked))
        }

    }


}