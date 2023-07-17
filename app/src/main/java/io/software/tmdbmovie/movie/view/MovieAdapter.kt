package io.software.tmdbmovie.movie.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.software.servicecoordinator.domain.response.Movie
import io.software.tmdbmovie.R
import io.software.tmdbmovie.databinding.ItemMovieBinding

class MovieAdapter(var data: MutableList<Movie.Results>, var contextPopular : MovieFragment, var listener : IMovieListener) :  RecyclerView.Adapter<MovieAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(inflater, R.layout.item_movie, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class ViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root)
    {
        private val baseUrl = "https://image.tmdb.org/t/p/w500"
        fun bind(item: Movie.Results) {
            binding.movie = item
            binding.movieListener = listener

            Picasso.with(contextPopular.context)
                .load(baseUrl + item.poster_path)
                .into(binding.imageView)

            binding.executePendingBindings()
        }
    }

    fun updateDataSet(newData: MutableList<Movie.Results>) {
        this.data = newData
        notifyDataSetChanged()
    }
}