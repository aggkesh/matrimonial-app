package com.example.matrimonial_app.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matrimonial_app.R
import com.example.matrimonial_app.ui.main.viewmodels.MainScreenState
import com.example.matrimonial_app.ui.main.viewmodels.MainViewModel
import com.example.matrimonial_app.ui.main.viewmodels.ScreenState

/**
 * Created by Keshav Aggarwal 11/4/2020
 *
 * Fragment used to show users data
 */
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            MainViewModel.MainViewModelFactory(requireContext())
        ).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
        val retrybtn = view.findViewById<ImageButton>(R.id.retry_view)
        retrybtn.setOnClickListener { viewModel.loadUsers() }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(requireContext())
        }
        val adaptor = UserRecycleViewAdaptor(emptyList()) { userId: String, selection: Boolean ->
            viewModel.updateUserSelection(userId, selection)
        }
        recyclerView.adapter = adaptor

        viewModel.screenState.observe(viewLifecycleOwner, Observer<MainScreenState> {
            when (it) {
                is ScreenState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    retrybtn.visibility = View.GONE
                }
                is ScreenState.Data -> {
                    progressBar.visibility = View.GONE
                    retrybtn.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    adaptor.setData(it.data)
                    adaptor.notifyDataSetChanged()
                }
                is ScreenState.Error -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    retrybtn.visibility = View.VISIBLE
                }
            }
        })
    }
}
