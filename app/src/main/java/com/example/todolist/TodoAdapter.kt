package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TodoItemBinding

//class TodoAdapter(private val todoItems: List<TodoItem>, private val listener: OnClickListener) :
//    RecyclerView.Adapter<TodoViewHolder>() {


class TodoAdapter(private val listener: OnClickListener) :
    ListAdapter<TodoItem, TodoViewHolder>(DiffUtil()) {


    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<TodoItem>() {
        override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding, listener)
    }

//    override fun getItemCount(): Int {
//        return todoItems.size
//    }


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bindItem(getItem(position))

    }

}