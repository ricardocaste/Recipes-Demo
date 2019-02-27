package com.rmedina.recipesdemo.presentation.main.activities

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.support.v7.widget.SearchView
import com.rmedina.recipesdemo.R
import com.rmedina.recipesdemo.domain.Recipe
import com.rmedina.recipesdemo.infrastructure.di.component.ViewComponent
import com.rmedina.recipesdemo.presentation.base.BaseActivity
import com.rmedina.recipesdemo.presentation.base.navigateToDetail
import com.rmedina.recipesdemo.presentation.base.utils.hide
import com.rmedina.recipesdemo.presentation.base.utils.onQueryTextChange
import com.rmedina.recipesdemo.presentation.base.utils.show
import com.rmedina.recipesdemo.presentation.main.adapter.RecipeListAdapter
import com.rmedina.recipesdemo.presentation.main.presenter.RecipeListPresenter
import com.rmedina.recipesdemo.presentation.main.view.RecipeListView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), RecipeListView {

    @Inject
    lateinit var presenter: RecipeListPresenter

    private val adapter by lazy { RecipeListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        presenter.onAttach(this)

        adapter.onItemClick = presenter::onClickItem
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    override fun onLoadData(recipeList: List<Recipe>) {
        adapter.swapData(recipeList)
        loadingView.hide()
        errorView.hide()
        recyclerView.show()
    }

    override fun showEmptyView() {
        tvError.show()
        loadingView.hide()
        recyclerView.hide()
        errorView.show()
    }

    override fun onItemClicked(recipe: Recipe) {
        navigateToDetail(recipe)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        configureSearch(searchItem)

        return true
    }

    private fun configureSearch(menuItem: MenuItem?) {
        menuItem?.let {
            val searchView = it.actionView as SearchView
            searchView.onQueryTextChange(presenter::filterRecipeList)
        }
    }

    override fun onFilterRecipes(recipeList: List<Recipe>) {
        adapter.swapData(recipeList)
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }
}
