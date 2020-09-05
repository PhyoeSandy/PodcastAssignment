package com.padcmyanmar.padcx.shared.adapters

import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.padcx.shared.views.viewholders.BaseViewHolder

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/22/2020.
 */
abstract class BaseRecyclerAdapter<T: BaseViewHolder<W>,W> : RecyclerView.Adapter<T>() {

    var mData: MutableList<W> = arrayListOf()

    override fun getItemCount(): Int {
        return mData.count()
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bindData(mData[position])
    }

    fun setNewData(data: MutableList<W>) {
        mData = data
        notifyDataSetChanged()
    }

    fun appendData(data: List<W>) {
        mData.addAll(data)
        notifyDataSetChanged()
    }
}