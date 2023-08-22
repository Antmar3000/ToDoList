package com.example.todolist

import android.graphics.Color
import android.graphics.Paint
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TodoItemBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TodoViewHolder(
    private val binding: TodoItemBinding,
    private val clickListener: OnClickListener
) : RecyclerView.ViewHolder(binding.root) {


    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
    private val dateFormat = DateTimeFormatter.ofPattern("dd-MM-yy")

    fun bindItem(todoItem: TodoItem) = with(binding) {
        titleTextView.text = todoItem.title
        descriptionTextView.text = todoItem.description
        if (todoItem.isChecked) {
            checkBox.isChecked = true
            titleTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            descriptionTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            buttonDestroy.visibility = View.VISIBLE
        } else {
            checkBox.isChecked = false
            titleTextView.paintFlags = 0
            descriptionTextView.paintFlags = 0
            buttonDestroy.visibility = View.INVISIBLE
        }

        binding.apply {
            buttonDestroy.setOnClickListener { clickListener.deleteTodoItem(todoItem) }
            itemCardView.setOnClickListener { clickListener.editTodoItem(todoItem) }
            buttonExpire.setOnClickListener { clickListener.expireTodoItem(todoItem) }
        }


        binding.itemCardView.apply {
            todoItem.expirationDate()?.let {
                if (it.isBefore(LocalDate.now()) || it.isEqual(LocalDate.now()))
                    setCardBackgroundColor(Color.GRAY)
            }
        }

        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) with(binding) {
                clickListener.setChecked(todoItem, isChecked)
            } else with(binding) {
                clickListener.setChecked(todoItem, isChecked)
            }
        }

        binding.timeTextView.text = todoItem.targetTime()?.let { timeFormat.format(it) }.orEmpty()

        binding.dateTextView.text =
            todoItem.expirationDate()?.let { dateFormat.format(it) }.orEmpty()

    }

}