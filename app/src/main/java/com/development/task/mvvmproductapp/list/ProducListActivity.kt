package com.development.task.mvvmproductapp.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.development.task.mvvmproductapp.R
import com.development.task.mvvmproductapp.constants.Constants
import com.development.task.mvvmproductapp.data.local.ProductModel
import com.development.task.mvvmproductapp.data.local.Products
import com.development.task.mvvmproductapp.list.viewmodel.ListViewModel
import com.development.task.mvvmproductapp.list.viewmodel.ListViewModelFactory
import com.development.task.mvvmproductapp.networking.Outcome
import kotlinx.android.synthetic.main.activity_produc_list.*
import java.io.IOException
import javax.inject.Inject

class ProducListActivity : AppCompatActivity(), ProductListAdapter.Interaction {
    override fun postClicked(post: ProductModel, tvTitle: TextView, tvBody: TextView, ivAvatar: ImageView) {
        val intent = Intent(context, ProductDetailsActivity::class.java)
        intent.putExtra(Constants.SELECTED_POST, post)
        context.startActivity(intent)
    }

    private val component by lazy { ProductDH.listComponent() }

    @Inject
    lateinit var viewModelFactory: ListViewModelFactory

    @Inject
    lateinit var adapter: ProductListAdapter

    private val viewModel: ListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)
    }

    private val context: Context by lazy { this }

    private val TAG = "ListActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produc_list)
        component.inject(this)

        adapter.interaction = this
        rvPosts.adapter = adapter
        srlPosts.setOnRefreshListener { viewModel.refreshPosts() }

        viewModel.getPosts()
        initiateDataListener()
    }

    private fun initiateDataListener() {
        //Observe the outcome and update state of the screen  accordingly
        viewModel.postsOutcome.observe(this, Observer<Outcome<Products>> { outcome ->
            Log.d(TAG, "initiateDataListener: $outcome")
            when (outcome) {

                is Outcome.Progress -> srlPosts.isRefreshing = outcome.loading

                is Outcome.Success -> {
                    Log.d(TAG, "initiateDataListener: Successfully loaded data")
                    adapter.swapData(outcome.data.data)
                }

                is Outcome.Failure -> {

                    if (outcome.e is IOException)

                        Toast.makeText(
                            context,
                            R.string.need_internet_posts,
                            Toast.LENGTH_LONG
                        ).show()
                    else
                        Toast.makeText(
                            context,
                            R.string.failed_post_try_again,
                            Toast.LENGTH_LONG
                        ).show()
                }
            }
        })
    }


}
