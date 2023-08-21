package com.example.todolist

import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TodoItemBinding
import java.time.format.DateTimeFormatter

class TodoViewHolder (
    private val binding: TodoItemBinding,
    private val clickListener: OnClickListener
    ) : RecyclerView.ViewHolder(binding.root) {



    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
    private val dateFormat = DateTimeFormatter.ofPattern("dd-MM-yy")

        fun bindItem (todoItem: TodoItem) = with(binding) {
            buttonDestroy.visibility = View.INVISIBLE
            titleTextView.text = todoItem.title
            descriptionTextView.text = todoItem.description


            binding.buttonDestroy.setOnClickListener {
                clickListener.deleteTodoItem(todoItem)
            }

            binding.itemCardView.setOnClickListener {
                clickListener.editTodoItem(todoItem)
            }

            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) with(binding) {
                    titleTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    descriptionTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    buttonDestroy.visibility = View.VISIBLE
                } else with(binding) {
                    titleTextView.paintFlags = 0
                    descriptionTextView.paintFlags = 0
                    buttonDestroy.visibility = View.INVISIBLE
                }
            }

            binding.timeTextView.text = todoItem.targetTime()?.let{timeFormat.format(it)}.orEmpty()

            binding.dateTextView.text = todoItem.expirationDate()?.let {dateFormat.format(it)}.orEmpty()

        }

}