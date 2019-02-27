package com.rmedina.recipesdemo.presentation.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rmedina.recipesdemo.R
import com.rmedina.recipesdemo.domain.Recipe
import com.rmedina.recipesdemo.presentation.base.utils.showCircleImage

class RecipeListAdapter : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {
    private var originalList: MutableList<Recipe> = mutableListOf()
    private var recipeList: MutableList<Recipe> = mutableListOf()
    var onItemClick: ((Recipe) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(recipeList[position])

    override fun getItemCount(): Int = recipeList.size

    fun swapData(userList: List<Recipe>) {
        this.originalList = userList.toMutableList()
        this.recipeList = userList.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tvTitle)
        private val logo: ImageView = itemView.findViewById(R.id.ivLogo)

        fun bind(recipe: Recipe) {
            title.text = recipe.title
            logo.showCircleImage(itemView.context, recipe.thumbnail)

            itemView.setOnClickListener { onItemClick?.invoke(recipe) }
        }

    }

}