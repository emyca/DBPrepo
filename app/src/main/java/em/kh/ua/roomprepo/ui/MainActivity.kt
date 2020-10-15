package em.kh.ua.roomprepo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import em.kh.ua.roomprepo.R
import em.kh.ua.roomprepo.adapter.MainAdapter
import em.kh.ua.roomprepo.database.Fruit
import em.kh.ua.roomprepo.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var adapter: MainAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setupToolbar()
        setupRecyclerView()
        getViewModel()
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu.findItem(R.id.menu_search)
        val searchView =
            menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty() || TextUtils.isEmpty(newText)) {
                    getViewModel()
                } else {
                    //val searchText = "%"+ newText.trim { it <= ' ' } + "%"
                    val searchText = newText.trim { it <= ' ' } + "%"
                    getSearchViewModel(searchText)
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> {
                getViewModel()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.fruit_list)
        linearLayoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.setHasFixedSize(true)
        adapter = MainAdapter(ArrayList(), this)
        recyclerView?.adapter = adapter
        val dividerItemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(
            recyclerView?.context,
            linearLayoutManager.orientation
        )
        recyclerView?.addItemDecoration(dividerItemDecoration)
        adapter.notifyDataSetChanged()
    }

    private fun getViewModel() {
        viewModel?.readAll()?.observe(this, {
                fruits -> adapter.showListItems(fruits)})
    }

    private fun getSearchViewModel(searchText: String) {
        viewModel?.readSearch(searchText)?.observe(this, {
                fruits -> adapter.showListItems(fruits) })
    }

    override fun onClick(v: View?) {
        val fruit = v?.tag as Fruit
        val intent = Intent(this, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("fruit_detail", fruit)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
