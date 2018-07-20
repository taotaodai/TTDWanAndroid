package com.ttd.wanandroid.widget.architecture

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import com.ttd.wanandroid.R
import com.ttd.wanandroid.bean.ArchitectureBean
import com.ttd.wanandroid.event.ArchitectureEvent
import com.ttd.wanandroid.event.BaseEvent
import com.ttd.wanandroid.widget.RecycleViewDivider
import org.greenrobot.eventbus.EventBus

/**
 * Created by wt on 2018/7/17.
 */
class ArchitectureSelectionWindow(context: Context, data: ArchitectureBean) {
    var window: PopupWindow? = null
    var rootView: View? = null
    var context: Context? = context
    var data: ArchitectureBean? = data

    var rvTop: RecyclerView? = null
    var rvSub: RecyclerView? = null
    var adapterTop: ArchitectureTopAdapter? = null
    var adapterSub: ArchitectureSubAdapter? = null

    fun init() {
        rootView = LayoutInflater.from(context).inflate(R.layout.window_architectrue, null)
        rvTop = rootView!!.findViewById(R.id.rv_top)
        rvSub = rootView!!.findViewById(R.id.rv_sub)

        rvTop!!.layoutManager = LinearLayoutManager(context)
        rvTop!!.addItemDecoration(RecycleViewDivider(context, RecyclerView.HORIZONTAL))
        rvSub!!.layoutManager = LinearLayoutManager(context)
        rvSub!!.addItemDecoration(RecycleViewDivider(context, RecyclerView.HORIZONTAL))

        showDataList()

        window = PopupWindow(rootView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true)
        window!!.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        window!!.setBackgroundDrawable(ColorDrawable(0))
        window!!.isFocusable = true
        window!!.isOutsideTouchable = true
    }

    fun show(anchor: View) {
        window!!.showAsDropDown(anchor)
    }

    fun hide(){
        window!!.dismiss()
    }


    fun showDataList() {
        adapterTop = ArchitectureTopAdapter(R.layout.adapter_architecture_classify, data)
        adapterSub = ArchitectureSubAdapter(R.layout.adapter_architecture_tags, data!!.architectureList[data!!.position])

        rvTop!!.adapter = adapterTop
        rvSub!!.adapter = adapterSub

        adapterTop!!.setOnItemClickListener { _, _, position ->
            data!!.position = position
            adapterTop!!.notifyDataSetChanged()
            adapterSub!!.replaceData(data!!.architectureList[data!!.position].children)
        }
        adapterSub!!.setOnItemClickListener { _, _, position ->
            val subData = data!!.architectureList[data!!.position]
            subData.position = position
            adapterSub!!.data = subData
            adapterSub!!.notifyDataSetChanged()

            EventBus.getDefault().post(ArchitectureEvent(BaseEvent.CODE_REFRESH))
//            showDataList()
        }

    }
}