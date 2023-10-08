package com.example.todolist

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

class TodoItemRepository (private val todoItemDao : TodoItemDao) {

    val allTodoItems : Flow<List<TodoItem>> = todoItemDao.allTodoItems()
//    val checkedTodoItems : Flow<List<TodoItem>> = todoItemDao.checkedTodoItems()

    @WorkerThread
    suspend fun insertTodoItem (todoItem: TodoItem){
        todoItemDao.insertTodoItem(todoItem)
    }

    @WorkerThread
    suspend fun updateTodoItem (todoItem: TodoItem) {
        todoItemDao.updateTodoItem(todoItem)
    }

    @WorkerThread
    suspend fun deleteTodoItem (todoItem: TodoItem) {
        todoItemDao.deleteTodoItem(todoItem)
    }
}