package com.rmedina.recipesdemo.presentation.base

import android.content.Intent
import com.rmedina.recipesdemo.domain.Recipe
import com.rmedina.recipesdemo.presentation.main.activities.DetailActivity

fun BaseActivity.navigateToDetail(item: Recipe) {
    val i = Intent(this, DetailActivity::class.java)
    i.putExtra("recipe", item)
    startActivity(i)
}
