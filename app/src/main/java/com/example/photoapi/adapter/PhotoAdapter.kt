package com.example.photoapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.photoapi.databinding.ItemPhotoBinding
import com.example.photoapi.model.network.models.Photo

class PhotoAdapter(
    private val photoList: List<Photo>
): RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photoList[position])
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    class PhotoViewHolder(private val binding: ItemPhotoBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {
            with(binding){
                titleTv.text = photo.title
                urlTv.text = photo.url
            }
        }
    }
}