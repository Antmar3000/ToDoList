package com.example.todolist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.OnClickListener
import com.example.todolist.TodoItem
import com.example.todolist.TodoViewHolder
import com.example.todolist.databinding.TodoItemBinding

class AllTodoAdapter(private val todoItems: List<TodoItem>, private val listener: OnClickListener) :
    RecyclerView.Adapter<TodoViewHolder>() {


//class TodoAdapter(private val listener: OnClickListener) :
//    ListAdapter<TodoItem, TodoViewHolder>(DiffUtil()) {
//
//
//    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<TodoItem>() {
//        override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
//            return oldItem == newItem
//        }
//
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bindItem(todoItems[position])

    }

}