package com.ttd.wanandroid.ui.fragment

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import android.view.View
import com.ttd.wanandroid.R
import com.ttd.wanandroid.adapter.AttendanceFragmentPagerAdapter
import com.ttd.wanandroid.base.BaseMVPCompatFragment
import com.ttd.wanandroid.bean.ProjectBean
import com.ttd.wanandroid.contract.project.ProjectContract
import com.ttd.wanandroid.presenter.BasePresenter
import com.ttd.wanandroid.presenter.project.ProjectPresenter

/**
 * Created by wt on 2018/7/2.
 */
class ProjectFragment : BaseMVPCompatFragment<ProjectPresenter, ProjectContract.IProjectModel>(), ProjectContract.IProjectView {
    override fun showModelessLoading() {
    }

    override fun dismissModelessLoading() {
    }

    override fun showTabs(projectBean: ProjectBean?) {

        for (project: ProjectBean.Project in projectBean!!.projects) {
            tlProject!!.addTab(tlProject!!.newTab().setText(project.name))
            val fragment = ProjectListFragment()
            val data = Bundle()
            data.putSerializable(ProjectBean.Project::class.java.simpleName, project)
            fragment.arguments = data
            fragments.add(fragment)
            titles.add(project.name)
        }
        vpProject!!.adapter = AttendanceFragmentPagerAdapter(childFragmentManager, fragments, titles.toTypedArray())
        tlProject!!.setupWithViewPager(vpProject)
    }

    var tlProject: TabLayout? = null
    var vpProject: androidx.viewpager.widget.ViewPager? = null
    var fragments: MutableList<androidx.fragment.app.Fragment> = mutableListOf()
    var titles: MutableList<String> = mutableListOf()

    override fun initPresenter(): BasePresenter<*, *> {
        return ProjectPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun initUI(view: View?, savedInstanceState: Bundle?) {
        tlProject = view!!.findViewById(R.id.tl_project)
        vpProject = view.findViewById(R.id.vp_project)
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)

        mPresenter.loadProjectClassify()
    }
}