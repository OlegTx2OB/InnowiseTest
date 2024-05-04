package com.example.innowisepexelstestapp.presentation.rv

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.databinding.RvHomeItemBinding
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RvPhotoAdapter(private val mListener: ClickListener, private val showAuthorName: Boolean) :
    RecyclerView.Adapter<RvPhotoAdapter.PhotoHolder>() {

    private val photoPexelsArray = mutableListOf<PhotoPexels>()

    inner class PhotoHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RvHomeItemBinding.bind(view)

        init {
            if (showAuthorName) {
                binding.authorName.visibility = View.VISIBLE
            }
        }

        fun bind(photoPexels: PhotoPexels, listener: ClickListener) = with(binding) {
            Picasso.get()
                .load(photoPexels.sources.medium)
                .placeholder(R.drawable.ic_imagestub)
                .into(rvHomeItem)

            authorName.text = photoPexels.photographer

            itemView.setOnClickListener {
                listener.onClickPhoto(photoPexels)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_home_item, parent, false)
        return PhotoHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind(photoPexelsArray[position], mListener)
    }

    override fun getItemCount(): Int {
        return photoPexelsArray.size
    }

    @SuppressLint("CheckResult")
    fun addPhotoPexelsList(photoPexelsList: List<PhotoPexels>) {
        val list = mutableListOf<PhotoPexels>()
        var listSize = 0

        Observable.just(photoPexelsList)
            .subscribeOn(Schedulers.computation())
            .doOnNext {
                list.addAll(it)
                listSize = list.size
                photoPexelsArray.addAll(list)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                notifyItemRangeInserted(photoPexelsArray.size - listSize, listSize)
            }
    }

    interface ClickListener {
        fun onClickPhoto(photoPexels: PhotoPexels)
    }
}