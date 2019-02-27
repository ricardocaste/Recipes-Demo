package com.rmedina.recipesdemo.presentation.main.activities

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.rmedina.recipesdemo.R
import com.rmedina.recipesdemo.domain.Recipe
import com.rmedina.recipesdemo.presentation.base.utils.hide
import com.rmedina.recipesdemo.presentation.base.utils.show
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        fillDetails(intent.getSerializableExtra("recipe") as Recipe)
    }

    private fun fillDetails(recipe: Recipe) {
        Glide.with(this)
            .load(recipe.thumbnail)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    TODO("Load failed case")
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    tvTitleDetail.text = recipe.title
                    tvIngredients.text = recipe.ingredients
                    tvWeb.text = recipe.href
                    detailContainer.show()
                    loadingViewDetail.hide()
                    return false
                }
            })
            .into(ivLogoDetail)
    }
}
