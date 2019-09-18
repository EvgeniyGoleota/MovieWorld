package com.escorp.movieworld.ui.detailScreen.movieDetail.movieCast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.escorp.movieworld.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.ui.detailScreen.DetailActivity
import com.escorp.movieworld.utils.ViewModelFactory
import com.escorp.movieworld.utils.interfaces.RecyclerViewOnItemClickListener
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import javax.inject.Inject

class MovieCastFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var movieCastListAdapter: MovieCastListAdapter

    private lateinit var viewModel: MovieCastViewModel
    private lateinit var binding: FragmentRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.recyclerView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private  fun initView() {
        movieCastListAdapter.onItemClickListener = object : RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int) {

            }
        }
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieCastListAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieCastViewModel::class.java)

        viewModel.getMovieCast((activity as DetailActivity).id!!).observe(this, Observer { list ->
            if (list.isNotEmpty()) {
                list.toMutableList().sort()
                movieCastListAdapter.setItems(list)
            }
        })
    }
}