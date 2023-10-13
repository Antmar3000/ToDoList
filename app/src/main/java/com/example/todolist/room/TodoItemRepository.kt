package com.example.todolist.room

import androidx.annotation.WorkerThread
import com.example.todolist.TodoItem
import kotlinx.coroutines.flow.Flow

class TodoItemRepository (private val todoItemDao : TodoItemDao) {

    val allTodoItems : Flow<List<TodoItem>> = todoItemDao.allTodoItems()
    val mainTodoItems : Flow<List<TodoItem>> = todoItemDao.typeMainTodoItems()
    val workTodoItems : Flow<List<TodoItem>> = todoItemDao.typeWorkTodoItems()
    val funTodoItem : Flow<List<TodoItem>> = todoItemDao.typeFunTodoItems()


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