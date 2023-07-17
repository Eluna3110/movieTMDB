package io.software.tmdbmovie.movie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.software.servicecoordinator.domain.response.Movie
import io.software.tmdbmovie.databinding.MovieFragmentBinding
import io.software.tmdbmovie.movie.MovieError
import io.software.tmdbmovie.movie.viewmodel.MovieViewModel

class MovieFragment : Fragment(), IMovieListener
{
    private lateinit var binding: MovieFragmentBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        binding.viewModel = viewModel

        viewModel.init(this)
        viewModel.getMovieListPopular()
        viewModel.getMovieListRated()

        viewModel.listMoviePopular.observe(viewLifecycleOwner, Observer { listMovie ->
            viewModel.saveMoviePopularDB()
            viewModel.updateDataPopular(listMovie)
        })

        viewModel.listMovieRated.observe(viewLifecycleOwner, Observer { listMovie ->
            viewModel.saveMovieRatedDB()
            viewModel.updateDataRated(listMovie)
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            if (error.equals(MovieError.ERROR_NETWORK)){
                viewModel.getInfoDB()
            }
        })
    }

    override fun onMovieDetail(movie: Movie.Results) {
    }

    private fun initViewModel() {
        viewModel = activity?.run { ViewModelProvider(this)[MovieViewModel::class.java] } ?: throw  Exception("Invalid Activity")
    }
}