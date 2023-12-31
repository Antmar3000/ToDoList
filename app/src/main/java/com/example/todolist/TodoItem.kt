package com.example.todolist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "todo_item_table")
class TodoItem(

    @ColumnInfo ("title") var title: String,
    @ColumnInfo("description")var description: String,
    @ColumnInfo("targetTimeString")var targetTimeString: String?,
    @ColumnInfo("expirationDate")var expirationDateString: String?,
    @ColumnInfo("isChecked") var isChecked : Boolean,
    @PrimaryKey(true)var id: Int = 0
) {

    fun targetTime(): LocalTime? = if (targetTimeString == null) null else LocalTime.parse(targetTimeString, timeFormatter)
    fun expirationDate() : LocalDate? = if (expirationDateString == null) null else LocalDate.parse(expirationDateString, dateFormatter)

    companion object {
        val timeFormatter : DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val dateFormatter : DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }
}
