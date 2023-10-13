package com.example.todolist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.MainActivity
import com.example.todolist.R
import com.example.todolist.TodoViewModel
import com.example.todolist.adapters.FunTodoAdapter
import com.example.todolist.databinding.FragmentFunListBinding


class FunListFragment : Fragment() {

    private lateinit var binding : FragmentFunListBinding
    private val todoViewModel : TodoViewModel by lazy {
        ViewModelProvider(requireActivity()).get(TodoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFunListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView () {
        todoViewModel.funTodoItems.observe(viewLifecycleOwner) {
            binding.funRecyclerView.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = FunTodoAdapter(it, (requireActivity() as MainActivity).listener)
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = FunListFragment()

    }
}