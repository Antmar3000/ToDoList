package com.example.todolist

import android.graphics.Color
import android.graphics.Paint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TodoItemBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TodoViewHolder(
    private val binding: TodoItemBinding, private val clickListener: OnClickListener
) : RecyclerView.ViewHolder(binding.root) {


    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
    private val dateFormat = DateTimeFormatter.ofPattern("dd-MM-yy")

    fun bindItem(todoItem: TodoItem) = with(binding) {
        when (todoItem.type) {
            0 -> {typeTextView.setText(R.string.type_main)
            typeTextView.setTextColor(Color.BLACK)}
            1 -> {typeTextView.setText(R.string.type_work)
            typeTextView.setTextColor(Color.RED)}
            2 -> {typeTextView.setText(R.string.type_fun)
            typeTextView.setTextColor(Color.MAGENTA)}
        }
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

        buttonDestroy.setOnClickListener { clickListener.deleteTodoItem(todoItem) }
        itemCardView.setOnClickListener { clickListener.editTodoItem(todoItem) }
        buttonExpire.setOnClickListener { clickListener.expireTodoItem(todoItem) }

        val isExpired = todoItem.expirationDate()?.let { it.isBefore(LocalDate.now()) || it.isEqual(LocalDate.now()) } ?: false
        itemCardView.setCardBackgroundColor(
            if (isExpired) Color.GRAY else Color.WHITE
        )

        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            clickListener.setChecked(todoItem, isChecked)
        }

        timeTextView.text = todoItem.targetTime()?.let { timeFormat.format(it) }.orEmpty()

        dateTextView.text = todoItem.expirationDate()?.let { dateFormat.format(it) }.orEmpty()

    }


}