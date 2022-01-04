package com.dakshsemwal.newsnow.presentation.ui.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.dakshsemwal.newsnow.domain.model.Article

class NewsDC : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(
        oldItem: Article,
        newItem: Article
    ): Boolean = oldItem.title == newItem.title

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: Article,
        newItem: Article
    ): Boolean = newItem == oldItem
}