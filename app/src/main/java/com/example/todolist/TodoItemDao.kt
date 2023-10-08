package com.example.todolist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoItemDao {

    @Query ("SELECT * FROM todo_item_table ORDER BY id ASC")
    fun allTodoItems () : Flow<List<TodoItem>>

//    @Query ("SELECT * FROM todo_item_table WHERE isChecked ORDER BY id ASC")
//    fun checkedTodoItems () : Flow<List<TodoItem>>


    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodoItem (todoItem: TodoItem)


    @Update
    suspend fun updateTodoItem (todoItem: TodoItem)


    @Delete
    suspend fun deleteTodoItem (todoItem: TodoItem)

}
