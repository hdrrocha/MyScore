package com.example.myscore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myscore.R
import com.example.myscore.databinding.ActivityScoreHomeBinding
import com.example.myscore.domain.viewmodel.ScoreViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ScoreHomeActivity : AppCompatActivity() {
    private val scoreViewModel: ScoreViewModel by viewModel()
    private lateinit var binding: ActivityScoreHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityScoreHomeBinding>(
            this, R.layout.activity_score_home
        ).apply {
            lifecycleOwner = this@ScoreHomeActivity
            viewModel = scoreViewModel
        }
        scoreViewModel.loadData()
    }
}