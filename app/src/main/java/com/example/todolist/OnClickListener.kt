package com.example.todolist

interface OnClickListener
{
    fun editTodoItem (todoItem: TodoItem)
    fun expireTodoItem (todoItem: TodoItem)
    fun removeTodoItem (position : Int)
}