package com.development.task.mvvmproductapp.list

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.development.task.mvvmproductapp.R
import com.development.task.mvvmproductapp.data.PostWithUser
import com.development.task.mvvmproductapp.list.viewmodel.ListViewModel
import com.development.task.mvvmproductapp.list.viewmodel.ListViewModelFactory
import kotlinx.android.synthetic.main.activity_produc_list.*
import javax.inject.Inject

class ProducListActivity : AppCompatActivity(),PostListAdapter.Interaction {
    override fun postClicked(post: PostWithUser, tvTitle: TextView, tvBody: TextView, ivAvatar: ImageView) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val component by lazy { PostDH.listComponent() }

    @Inject
    lateinit var viewModelFactory: ListViewModelFactory

    @Inject
    lateinit var adapter: PostListAdapter

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
//        viewModel.postsOutcome.observe(this, Observer<Outcome<List<PostWithUser>>> { outcome ->
//            Log.d(TAG, "initiateDataListener: $outcome")
//            when (outcome) {
//
//                is Outcome.Progress -> srlPosts.isRefreshing = outcome.loading
//
//                is Outcome.Success -> {
//                    Log.d(TAG, "initiateDataListener: Successfully loaded data")
//                    adapter.swapData(outcome.data)
//                }
//
//                is Outcome.Failure -> {
//
//                    if (outcome.e is IOException)
//                        Toast.makeText(
//                            context,
//                            R.string.need_internet_posts,
//                            Toast.LENGTH_LONG
//                        ).show()
//                    else
//                        Toast.makeText(
//                            context,
//                            R.string.failed_post_try_again,
//                            Toast.LENGTH_LONG
//                        ).show()
//                }
//
//            }
//        })
    }

//    override fun postClicked(
//        post: PostWithUser,
//        tvTitle: TextView,
//        tvBody: TextView,
//        tvAuthorName: TextView,
//        ivAvatar: ImageView
//    ) {
//        DetailsActivity.start(context, post, tvTitle, tvBody, tvAuthorName, ivAvatar)
//    }
}
