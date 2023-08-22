package com.example.todolist

interface OnClickListener {
    fun editTodoItem(todoItem: TodoItem)
    fun expireTodoItem(todoItem: TodoItem)
    fun deleteTodoItem(todoItem: TodoItem)
    fun setChecked (todoItem: TodoItem, isChecked : Boolean)
}