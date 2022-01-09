package com.dakshsemwal.newsnow.presentation.ui.adapters


import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dakshsemwal.newsnow.R
import com.dakshsemwal.newsnow.databinding.ItemCardBinding
import com.dakshsemwal.newsnow.domain.model.Article

class NewsAdapter(
    private val movies: ArrayList<Article>
) : ListAdapter<Article, NewsAdapter.MovieViewHolder>(NewsDC()) {

    private var mItemClickListener: ListItemClickListener? = null

    interface ListItemClickListener {
        fun onItemClick(listItem: Article, position: Int)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val Article = movies[position]
        holder.bind(Article, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    fun SetOnItemClickListener(mItemClickListener: ListItemClickListener) {
        this.mItemClickListener = mItemClickListener
    }

    inner class MovieViewHolder(private val itemMovieBinding: ItemCardBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {

        fun bind(article: Article, position: Int) {
            with(itemMovieBinding) {
                if(article.urlToImage==null) {
                    Glide.with(itemMovieBinding.root.context)
                        .load(R.drawable.ic_news)
                        .error(R.drawable.ic_news)
                        .into(imgBanner)
                }
                else{
                    Glide.with(itemMovieBinding.root.context)
                        .load(article.urlToImage)
                        .error(R.drawable.ic_news)
                        .into(imgBanner)
                }
                itemMovieBinding.title.text = article.title
                itemMovieBinding.description.text = article.description
                itemMovieBinding.root.setOnClickListener {
                    mItemClickListener?.onItemClick(article, position)
                }
            }
        }
    }

    override fun getItemCount(): Int = movies.size

}