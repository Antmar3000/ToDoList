package com.example.todolist.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.Constances
import com.example.todolist.R
import com.example.todolist.TodoItem
import com.example.todolist.TodoViewModel
import com.example.todolist.databinding.FragmentNewTodoBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalDate
import java.time.LocalTime

class NewTodoFragment(var todoItem: TodoItem?) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewTodoBinding
    private val todoViewModel: TodoViewModel by lazy {
        ViewModelProvider(requireActivity()).get(
            TodoViewModel::class.java
        )
    }

    private var targetTime: LocalTime = todoItem?.targetTime().let { todoItem?.targetTime() }
        ?: Constances.DEFAULT_TIME
    private var expirationDate: LocalDate =
        todoItem?.expirationDate().let { todoItem?.expirationDate() }
            ?: Constances.DEFAULT_DATE
    private var todoItemType: Int = Constances.DEFAULT_TYPE

    private val openTimePickerVariable = {
        if (targetTime == Constances.DEFAULT_TIME) targetTime = LocalTime.now()
        val listener = TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
            targetTime = LocalTime.of(selectedHour, selectedMinute)
            binding.buttonTime.text = String.format("%02d:%02d", targetTime.hour, targetTime.minute)
        }
        val dialog = TimePickerDialog(
            requireContext(), listener, targetTime.hour, targetTime.minute, true
        )
        dialog.setTitle(R.string.picker_time_title)
        dialog.show()

    }

    private val openDatePickerVariable = {
        if (expirationDate == Constances.DEFAULT_DATE) expirationDate = LocalDate.now()
        val listener =
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                expirationDate = LocalDate.of(selectedYear, selectedMonth, selectedDay)
                binding.buttonDate.text = String.format(
                    "%02d/%02d/%02d",
                    expirationDate.dayOfMonth,
                    expirationDate.monthValue,
                    expirationDate.year
                )
            }
        val dialog = DatePickerDialog(
            requireContext(),
            listener,
            expirationDate.year,
            expirationDate.monthValue,
            expirationDate.dayOfMonth
        )
        dialog.setTitle(R.string.picker_date_title)
        dialog.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.spinnerTodoType.adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.todo_type_array,
            android.R.layout.simple_spinner_dropdown_item
        )

        todoItem?.let {
            binding.apply {
                topTextView.text = getString(R.string.edit_item_name)
                editTitle.setText(it.title)
                editDescription.setText(it.description)
                buttonTime.text = it.targetTimeString ?: getString(R.string.picker_time)
                buttonDate.text = it.expirationDateString ?: getString(R.string.picker_date)
                spinnerTodoType.setSelection(it.type)
            }
        } ?: {
            binding.topTextView.text = getString(R.string.new_item_name)
        }

        binding.apply {
            buttonTime.setOnClickListener { openTimePickerVariable() }
            buttonDate.setOnClickListener { openDatePickerVariable() }
            buttonSave.setOnClickListener { saveData() }
            spinnerTodoType.onItemSelectedListener = spinnerSelectionListener
        }

    }

    private fun saveData() {
        if (binding.editTitle.text.isNullOrEmpty()) {
            Toast.makeText(context, getString(R.string.toast_empty_title), Toast.LENGTH_SHORT)
                .show()
        } else {
            val title = binding.editTitle.text.toString()
            val description = binding.editDescription.text.toString()
            val targetTimeString =
//                if (targetTime == Constances.DEFAULT_TIME) null else TodoItem.timeFormatter.format(
//                    targetTime
//                )
                when (targetTime) {
                    Constances.DEFAULT_TIME -> null
                    todoItem?.targetTime() -> todoItem?.targetTimeString
                    else -> TodoItem.timeFormatter.format(targetTime)
                }

            val expirationDateString =
//                if (expirationDate == Constances.DEFAULT_DATE) null else TodoItem.dateFormatter.format(
//                    expirationDate
//                )
                when (expirationDate) {
                    Constances.DEFAULT_DATE -> null
                    todoItem?.expirationDate() -> todoItem?.expirationDateString
                    else -> TodoItem.dateFormatter.format(expirationDate)
                }
            if (todoItem == null) {
                val newTodo =
                    TodoItem(
                        title,
                        description,
                        targetTimeString,
                        expirationDateString,
                        false,
                        todoItemType
                    )
                todoViewModel.addTodoItem(newTodo)
            } else {
                todoViewModel.updateTodoItem(
                    todoItem!!.copy(
                        title = title,
                        description = description,
                        targetTimeString = targetTimeString,
                        expirationDateString = expirationDateString,
                        type = todoItemType
                    )
                )
            }

            dismiss()
        }
    }

    private val spinnerSelectionListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            todoItemType = p2
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

    }


}