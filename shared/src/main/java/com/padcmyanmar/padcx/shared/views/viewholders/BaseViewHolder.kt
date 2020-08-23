package com.padcmyanmar.padcx.shared.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/22/2020.
 */
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var mData: T? = null

    abstract fun bindData(data: T)

}