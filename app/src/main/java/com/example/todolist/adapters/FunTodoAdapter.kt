package com.example.todolist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.OnClickListener
import com.example.todolist.TodoItem
import com.example.todolist.TodoViewHolder
import com.example.todolist.databinding.TodoItemBinding

class FunTodoAdapter(
    private val funTodoItems: List<TodoItem>,
    private val listener: OnClickListener
) : RecyclerView.Adapter<TodoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return funTodoItems.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bindItem(funTodoItems[position])
    }
}