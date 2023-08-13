package com.example.todolist

import android.content.Context
import android.graphics.Paint
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TodoItemBinding
import java.time.format.DateTimeFormatter

class TodoViewHolder (
    private val context : Context,
    private val binding: TodoItemBinding,
    private val clickListener: OnClickListener
    ) : RecyclerView.ViewHolder(binding.root) {



    val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
    val dateFormat = DateTimeFormatter.ofPattern("dd-MM-yy")

        fun bindItem (todoItem: TodoItem) {
            binding.buttonDestroy.visibility = View.INVISIBLE
            binding.titleTextView.text = todoItem.title
            binding.descriptionTextView.text = todoItem.description

            binding.buttonDestroy.setOnClickListener {
                val position = adapterPosition
                clickListener.removeTodoItem(position)
            }

            binding.itemCardView.setOnClickListener {
                clickListener.editTodoItem(todoItem)
            }

            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    binding.titleTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    binding.descriptionTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    binding.buttonDestroy.visibility = View.VISIBLE
                } else {
                    binding.titleTextView.paintFlags = 0
                    binding.descriptionTextView.paintFlags = 0
                    binding.buttonDestroy.visibility = View.INVISIBLE
                }
            }

            if (todoItem.targetTime != null) {
                binding.timeTextView.text = timeFormat.format(todoItem.targetTime)
            }
            else  binding.timeTextView.text = ""

            if (todoItem.expirationDate != null){
                binding.dateTextView.text = dateFormat.format(todoItem.expirationDate)
            }
            else binding.dateTextView.text = ""
        }

}