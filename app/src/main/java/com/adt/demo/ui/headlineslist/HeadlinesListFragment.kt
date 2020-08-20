package com.adt.demo.ui.headlineslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.adt.demo.R
import com.adt.demo.data.model.ResponseState
import com.adt.demo.di.vm.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_headlines_list.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HeadlinesListFragment : DaggerFragment() {

    //Injection of the view model
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<HeadlinesListViewModel>

    private val headlinesListViewModel : HeadlinesListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HeadlinesListViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_headlines_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        headlinesListViewModel.getHeadlineListData()

        val headlinesListRecyclerViewAdapter = HeadlinesListRecyclerViewAdapter()

        ad_list.apply {
            adapter = headlinesListRecyclerViewAdapter
        }

        //The View will react to the state it gets from the view model i.e loading, success, ...
        headlinesListViewModel.headlineResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResponseState.Success -> {
                    ad_list.visibility = View.VISIBLE
                    progress_circular.visibility = View.GONE
                    headlinesListRecyclerViewAdapter.updateList(it.list)
                }
                is ResponseState.Error -> {
                    Snackbar.make(ad_list, it.error.toString(), Snackbar.LENGTH_LONG).show()
                    progress_circular.visibility = View.GONE
                }
                is ResponseState.Loading -> {
                    progress_circular.visibility = View.VISIBLE
                    ad_list.visibility  = View.GONE
                }
                is ResponseState.Empty -> {
                    Snackbar.make(ad_list, getString(R.string.empty_list_text),
                        Snackbar.LENGTH_INDEFINITE).setAction(R.string.retry) {
                        headlinesListViewModel.getHeadlineListData()
                    }.show()

                    progress_circular.visibility = View.GONE
                }
            }
        })
    }

}