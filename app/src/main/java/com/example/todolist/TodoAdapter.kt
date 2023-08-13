package com.example.todolist

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TodoItemBinding

class TodoAdapter (private val todoItems : List<TodoItem>, private val listener: com.example.todolist.OnClickListener): RecyclerView.Adapter<TodoViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(parent.context, binding, listener)
    }



    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bindItem(todoItems[position])

    }

    override fun getItemCount(): Int = todoItems.size




}