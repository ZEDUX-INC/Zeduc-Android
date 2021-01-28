package com.zedux.adapters

import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.bold
import androidx.recyclerview.widget.RecyclerView
import com.zedux.R
import com.zedux.data.OnboardItem
import com.zedux.databinding.OnboardingItemContainerBinding

class OnboardingItemAdapter(private val onboardingItems: List<OnboardItem>) :
    RecyclerView.Adapter<OnboardingItemAdapter.OnboardingItemViewHolder>() {

    private lateinit var binding: OnboardingItemContainerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        binding = OnboardingItemContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return OnboardingItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {


        Log.d("Dapo", "$position")
        holder.bind(onboardingItems.get(position))
    }

    override fun getItemCount(): Int {
        return onboardingItems.size;
    }

    inner class OnboardingItemViewHolder(binding: OnboardingItemContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(onboardItem: OnboardItem) {

            binding.apply {
                imageViewTwo.setImageResource(onboardItem.onboardImage)

                val text = SpannableStringBuilder()
                    .bold { append(onboardItem.descBold) }
                    .append(" ")
                    .append(onboardItem.desc)

                tvDesc.setText(text)
            }

        }

    }

}