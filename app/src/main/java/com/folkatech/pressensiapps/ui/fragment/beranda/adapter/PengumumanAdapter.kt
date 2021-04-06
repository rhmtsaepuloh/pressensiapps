package com.folkatech.pressensiapps.ui.fragment.beranda.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.folkatech.pressensi.model.Informasi
import com.folkatech.pressensiapps.R
import com.folkatech.pressensiapps.common.base.BaseAdapter
import kotlinx.android.synthetic.main.adapter_footer.view.*


/**
 * Created by Rahmat Saefulloh on 17/03/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
class PengumumanAdapter: BaseAdapter<Informasi>() {
    private var mOnItemClickListener: OnPengumumanItemClickListener? = null
    private lateinit var footerHolder: View

    fun setOnItemClickListener(mItemClickListener: OnPengumumanItemClickListener) {
        this.mOnItemClickListener = mItemClickListener
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLastPosition(position) && isFooterAdded) FOOTER else ITEM
    }

    override fun createHeaderViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder? = null

    override fun createItemViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder =
        PengumumanItemHolder(
            LayoutInflater.from(parent!!.context).inflate(
                R.layout.pengumuman_item,
                parent,
                false
            )
        )

    override fun createFooterViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val v =
            LayoutInflater.from(parent!!.context).inflate(R.layout.adapter_footer, parent, false)
        v.reload_btn.setOnClickListener {
            if (onReloadClickListener != null)
                onReloadClickListener.onReloadClick()
        }
        return PengumumanFooterHolder(v)
    }

    override fun bindHeaderViewHolder(viewHolder: RecyclerView.ViewHolder?) {
    }

    override fun bindItemViewHolder(viewHolder: RecyclerView.ViewHolder?, position: Int) {
        val holder = viewHolder as PengumumanItemHolder
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
        holder.crd_pengumuman.setOnClickListener {
            //if (mOnItemClickListener != null)
            mOnItemClickListener!!.onClickItem(it, data, position)
        }
    }

    override fun bindFooterViewHolder(viewHolder: RecyclerView.ViewHolder?) {
//        footerHolder = viewHolder as View
//        footerHolder.loading_timeline_footer.visibility = View.VISIBLE
    }

    override fun displayLoadMoreFooter() {
        footerHolder.error_rl.visibility = View.GONE
        footerHolder.loading_fl.visibility = View.VISIBLE
    }

    override fun displayErrorFooter() {
        footerHolder.loading_fl.visibility = View.GONE
        footerHolder.error_rl.visibility = View.VISIBLE
    }

    override fun addFooter() {
        isFooterAdded = true
        add(Informasi())
    }
}