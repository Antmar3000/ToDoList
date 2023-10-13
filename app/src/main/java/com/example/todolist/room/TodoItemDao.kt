package com.example.todolist.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.TodoItem
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoItemDao {

    @Query ("SELECT * FROM todo_item_table ORDER BY id ASC")
    fun allTodoItems () : Flow<List<TodoItem>>

    @Query ("SELECT * FROM todo_item_table WHERE type = 0 ORDER BY id ASC")
    fun typeMainTodoItems () : Flow<List<TodoItem>>

    @Query ("SELECT * FROM todo_item_table WHERE type = 1 ORDER BY id ASC")
    fun typeWorkTodoItems () : Flow<List<TodoItem>>

    @Query ("SELECT * FROM todo_item_table WHERE type = 2 ORDER BY id ASC")
    fun typeFunTodoItems () : Flow<List<TodoItem>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodoItem (todoItem: TodoItem)


    @Update
    suspend fun updateTodoItem (todoItem: TodoItem)


    @Delete
    suspend fun deleteTodoItem (todoItem: TodoItem)

}
