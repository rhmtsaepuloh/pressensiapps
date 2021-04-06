package com.folkatech.pressensiapps.ui.fragment.beranda.adapter

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.folkatech.pressensi.model.Informasi
import com.folkatech.pressensiapps.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.pengumuman_item.view.*


/**
 * Created by Rahmat Saefulloh on 17/03/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
class PengumumanItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    internal var crd_pengumuman = itemView.cv_pengumuman

    internal fun bind(mData: Informasi) {
        setupTextJudul(itemView.tv_judul, mData)
        setupTextContent(itemView.tv_description, mData)
    }

    private fun setupTextJudul(textView: TextView, Data: Informasi) {
        val judul = Data.judulPengumuman
        if (!TextUtils.isEmpty(judul)) {
            textView.text = judul
        }
    }

    private fun setupTextDate(textView: TextView, Data: Informasi) {
        val date = Data.tanggal
        if (!TextUtils.isEmpty(date)) {
            textView.text = date
        }
    }

    private fun setupTextContent(textView: TextView, Data: Informasi) {
        val content = Data.deskripsiPengumuman
        if (!TextUtils.isEmpty(content)) {
            textView.text = content
        }
    }

    private fun setupImageContent(fotoContent: ImageView, Data: Informasi) {
        val thumbnailUrl = Data.urlFilePengumuman
        Glide.with(fotoContent.context)
            .load(thumbnailUrl)
            .placeholder(R.drawable.no_image)
            .error(R.drawable.no_image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(fotoContent)
    }

    private fun setupImageChannel(
        fotoChannel: CircleImageView,
        Data: Informasi
    ) {
        val thumbnailUrl = Data.fotoChannel
        if (!TextUtils.isEmpty(thumbnailUrl)) {
            Glide.with(fotoChannel.context)
                .load(thumbnailUrl)
                .error(R.drawable.image_default_profile)
                .override(80, 80)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(fotoChannel)
        }
    }
}