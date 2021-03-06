package com.zedux.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zedux.databinding.BottomSheetChatBinding

class ChatBottomSheetFragment: BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetChatBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}