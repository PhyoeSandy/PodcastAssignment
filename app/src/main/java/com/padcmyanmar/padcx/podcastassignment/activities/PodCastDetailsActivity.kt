package com.padcmyanmar.padcx.podcastassignment.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.padcmyanmar.padcx.podcastassignment.R
import com.padcmyanmar.padcx.shared.activities.BaseActivity

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
class PodCastDetailsActivity : BaseActivity() {
    
    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, PodCastDetailsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_podcast_details)
    }
}