package com.ttd.wanandroid.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.ttd.wanandroid.R
import com.ttd.wanandroid.base.BaseMVPCompatFragment
import com.ttd.wanandroid.bean.NavigationBean
import com.ttd.wanandroid.contract.navigation.NavigationContract
import com.ttd.wanandroid.event.NavigationEvent
import com.ttd.wanandroid.presenter.BasePresenter
import com.ttd.wanandroid.presenter.navigation.NavigationPresenter
import com.ttd.wanandroid.ui.adapter.NavigationClassifyAdapter
import com.ttd.wanandroid.ui.adapter.NavigationTagsAdapter
import com.ttd.wanandroid.widget.architecture.PinnedHeaderListView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author wt
 * @createDate 2018/7/20
 */
class NavigationFragment : BaseMVPCompatFragment<NavigationPresenter, NavigationContract.INavigationModel>(), NavigationContract.INavigationView {

    override fun showNavigations(navigationBean: NavigationBean?) {
        adapterClassify = NavigationClassifyAdapter(R.layout.adapter_navigation_classify, navigationBean)
        adapterClassify!!.setOnItemClickListener { _, _, position ->
            classifyChange(position)
            var rightSection = 0
            for (i in 0 until position) {
                rightSection += adapterTags!!.getCountForSection(i) + 1
            }
            lvTags!!.setSelection(rightSection)
        }

        rvClassify!!.adapter = adapterClassify

        adapterTags = NavigationTagsAdapter(mContext, navigationBean!!.classifies, R.layout.adapter_navigation_tags)
        lvTags!!.adapter = adapterTags
    }

    var tbNavigation: Toolbar? = null
    var adapterClassify: NavigationClassifyAdapter? = null
    var adapterTags: NavigationTagsAdapter? = null
    var rvClassify: RecyclerView? = null
    var lvTags: PinnedHeaderListView? = null

    override fun showModelessLoading() {
    }

    override fun dismissModelessLoading() {
    }

    override fun initPresenter(): BasePresenter<*, *> {
        return NavigationPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_navigation
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
    }

    override fun initUI(view: View?, savedInstanceState: Bundle?) {
        EventBus.getDefault().register(this)
        tbNavigation = view!!.findViewById(R.id.tb_navigation)
        rvClassify = view.findViewById(R.id.rv_classify)
        lvTags = view.findViewById(R.id.lv_tags)
        adapterClassify = NavigationClassifyAdapter(R.layout.adapter_navigation_classify)
        rvClassify!!.layoutManager = LinearLayoutManager(mContext)
        rvClassify!!.adapter = adapterClassify
//        (activity as AppCompatActivity).setSupportActionBar(tbNavigation)
//        setHasOptionsMenu(true)
//
        mPresenter.loadNavigationData()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    /**
     * 当tags的头部变化时，更新classify列表选中状态
     */
    fun classifyChange(position:Int){
        adapterClassify!!.notifyItemChanged(adapterClassify!!.navigationBean!!.position)
        mPresenter.navigationBean!!.position = position
        adapterClassify!!.notifyItemChanged(position)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun navigationEvent(event: NavigationEvent) {
        when (event.code) {
            NavigationEvent.CODE_CHANGE_CLASSIFY -> {
                classifyChange(event.headerPosition)
                //这里classify的item可能会超出屏幕，所以列表需要滑动到item选中位置
                rvClassify!!.scrollToPosition(event.headerPosition)
            }
        }
    }
}