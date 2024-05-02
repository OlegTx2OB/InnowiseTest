package com.example.innowisepexelstestapp.presentation.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.domain.model.PhotoPexels
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.databinding.FragmentHomeBinding
import com.example.innowisepexelstestapp.di.injectViewModel
import com.example.innowisepexelstestapp.presentation.rv.HomePhotoAdapter
import com.example.innowisepexelstestapp.presentation.viewmodel.HomeViewModel
import com.makeramen.roundedimageview.RoundedImageView


class HomeFragment : Fragment(R.layout.fragment_home), HomePhotoAdapter.ClickListener {

    private val mBinding by viewBinding(FragmentHomeBinding::bind)
    private val mVm: HomeViewModel by injectViewModel()
    private val mAdapter: HomePhotoAdapter = HomePhotoAdapter(this)
    private val mRecyclerView: RecyclerView by lazy { mBinding.homeRv }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()

        mRecyclerView.layoutManager = StaggeredGridLayoutManager(2,  StaggeredGridLayoutManager.VERTICAL)
        mRecyclerView.adapter = mAdapter
        addPhotosTest()
    }

    private fun addPhotosTest() {
        val x = mutableListOf<PhotoPexels>()
        x.add( PhotoPexels(R.drawable.ic_favorite_active))
        x.add(PhotoPexels(R.drawable.test189))
        x.add(PhotoPexels(R.drawable.test189))
        x.add(PhotoPexels(R.drawable.test43))
        x.add(PhotoPexels(R.drawable.test189))
        x.add(PhotoPexels(R.drawable.test189))
        x.add(PhotoPexels(R.drawable.test43))
        x.add(PhotoPexels(R.drawable.test43))
        x.add(PhotoPexels(R.drawable.test189))
        x.add(PhotoPexels(R.drawable.test189))
        mAdapter.addPhotoPexelsList(x)
    }

    override fun onClickPhoto(view: RoundedImageView, photoPexels: PhotoPexels) {
        mVm.onClickPhoto(view, photoPexels)
    }

    private fun setupListeners() = with(mBinding) {

        bnvFavorite.setOnClickListener {
            mVm.navigateToFavorite()
        }

        searchBarEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(str: Editable?) {
                if (str.toString().trim().isNotEmpty()) {
                    mBinding.searchBarCloseIcon.visibility = View.VISIBLE
                } else {
                    mBinding.searchBarCloseIcon.visibility = View.GONE
                }
            }
        })

        searchBarCloseIcon.setOnClickListener {
            searchBarEditText.text.clear()
            searchBarEditText.clearFocus()
        }

    }


}