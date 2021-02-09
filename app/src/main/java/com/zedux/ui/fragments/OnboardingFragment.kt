package com.zedux.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.zedux.R
import com.zedux.adapters.OnboardingItemAdapter
import com.zedux.data.OnboardItem
import com.zedux.other.FragmentCallback
import com.zedux.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_onboarding.*
import kotlinx.android.synthetic.main.onboarding_item_container.*

class OnboardingFragment : Fragment() {

    private lateinit var onboardingItemAdapter: OnboardingItemAdapter

    private lateinit var indicators: Array<ImageView>

    private lateinit var onboardingViewPager: ViewPager2

    private var page: Int = 0

    private var callBack: FragmentCallback? = null

    companion object {
        fun newInstance(): OnboardingFragment {
            return OnboardingFragment()
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = context as MainActivity
    }

    override fun onDetach() {
        super.onDetach()
        callBack = null
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(this, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if(onboardingViewPager.currentItem == 0) {
                        if(isEnabled) {
                            isEnabled = false
                            requireActivity().onBackPressed()
                        }
                    } else {
                        onboardingViewPager.currentItem = onboardingViewPager.currentItem - 1
                    }
                }

            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        indicators = arrayOf(indicator_one, indicator_two, indicator_three)

        setOnboardingItems()

        btn_prev.setOnClickListener {
            if (page > 0) {
                page -= 1
                onboardingViewPager.currentItem = page
            }
        }

        btn_next.setOnClickListener {
            if (page == indicators.size - 2) {
                goToNextPage()
            }
            if (page < indicators.size - 1) {
                page += 1
                onboardingViewPager.currentItem = page
            }
        }
    }


    private fun goToNextPage() {
        callBack?.navigateOnboardToContinue()
    }


    private fun setOnboardingItems() {
        updateIndicators(page)
        onboardingItemAdapter = OnboardingItemAdapter(
            listOf(
                OnboardItem(
                    onboardImage = R.drawable.onboard_one,
                    descBold = "Become the best",
                    desc = "among your pairs, one course at a time"
                ),
                OnboardItem(
                    onboardImage = R.drawable.onboard_two,
                    descBold = "Meet tutors and students",
                    desc = "from all over the world and share knowledge"
                ),
            )
        )

        onboardingViewPager = onboarding_container
        onboardingViewPager.adapter = onboardingItemAdapter


        onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)



               btn_prev.visibility = if(position == 0) View.INVISIBLE else View.VISIBLE
                if(position == 2) {
                   goToNextPage()
                }

                page = position
                updateIndicators(page)
            }
        })

    }

    private fun updateIndicators(position: Int) {

        for (i in 0 until indicators.size) {
            if (i == position)
                indicators[i].setBackgroundResource(R.drawable.indicator_selected)
            else
                indicators[i].setBackgroundResource(R.drawable.indicator_default)
        }
    }
}