package com.example.todolist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.MainActivity
import com.example.todolist.TodoViewModel
import com.example.todolist.adapters.MainTodoAdapter
import com.example.todolist.databinding.FragmentMainListBinding
import dagger.hilt.android.AndroidEntryPoint

class MainListFragment : Fragment() {

    private lateinit var binding: FragmentMainListBinding
    private val todoViewModel: TodoViewModel by lazy {
        ViewModelProvider(requireActivity()).get(TodoViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

    }

    private fun setRecyclerView() {
        todoViewModel.mainTodoItems.observe(viewLifecycleOwner) {
            binding.mainRecyclerView.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = MainTodoAdapter(it, (requireActivity() as MainActivity).listener)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainListFragment()
    }


}