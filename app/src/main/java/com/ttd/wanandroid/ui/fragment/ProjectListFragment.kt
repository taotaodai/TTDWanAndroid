package com.ttd.wanandroid.ui.fragment

import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.ttd.wanandroid.R
import com.ttd.wanandroid.base.BaseMVPCompatFragment
import com.ttd.wanandroid.bean.Article
import com.ttd.wanandroid.bean.ArticleBean
import com.ttd.wanandroid.bean.ProjectBean
import com.ttd.wanandroid.contract.project.ProjectListContract
import com.ttd.wanandroid.presenter.BasePresenter
import com.ttd.wanandroid.presenter.project.ProjectListPresenter
import com.ttd.wanandroid.ui.ArticleDetailActivity
import com.ttd.wanandroid.ui.adapter.ArticleItemAdapter

/**
 * author wt
 * createDate 2018/7/30
 */
class ProjectListFragment : BaseMVPCompatFragment<ProjectListContract.ProjectListPresenter, ProjectListContract.IProjectListModel>(), ProjectListContract.IProjectListView {
    override fun showMoreProjects(articleBean: ArticleBean?) {
        adapter!!.loadMoreComplete()
        adapter!!.addData(articleBean!!.articleData.articles)
    }

    override fun showModelessLoading() {
        adapter!!.emptyView = vLoading
    }

    override fun dismissModelessLoading() {
    }

    override fun showProjects(articleBean: ArticleBean?) {
        if (srlProjects!!.isRefreshing) {
            srlProjects!!.isRefreshing = false
        }
        adapter = ArticleItemAdapter(R.layout.adapter_article_item, articleBean!!.articleData.articles)
        adapter!!.setOnLoadMoreListener({
            mPresenter.loadMoreArticles(classify)
        }, rvProjects)

        adapter!!.setOnItemClickListener { _, _, position ->
            val item = adapter!!.data[position]

            val bundle = Bundle()
            item.position = position
            bundle.putSerializable(Article::class.java.simpleName, item)
            startNewActivity(ArticleDetailActivity::class.java, bundle)
        }

        rvProjects!!.adapter = adapter
    }

    override fun initPresenter(): BasePresenter<*, *> {
        return ProjectListPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_project_list
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        showModelessLoading()
        mPresenter.loadProjects(classify)
    }

    var srlProjects: androidx.swiperefreshlayout.widget.SwipeRefreshLayout? = null
    var rvProjects: androidx.recyclerview.widget.RecyclerView? = null
    var adapter: ArticleItemAdapter? = null
    var classify: ProjectBean.Project? = null
    override fun initUI(view: View?, savedInstanceState: Bundle?) {
        srlProjects = view!!.findViewById(R.id.srl_project)
        srlProjects!!.setOnRefreshListener {
            mPresenter.loadProjects(classify)
        }
        rvProjects = view.findViewById(R.id.rv_project)
        rvProjects!!.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)
        adapter = ArticleItemAdapter(R.layout.adapter_article_item)
        rvProjects!!.adapter = adapter
    }

    override fun getBundle(bundle: Bundle?) {
        classify = bundle!!.getSerializable(ProjectBean.Project::class.java.simpleName) as ProjectBean.Project?
    }

}