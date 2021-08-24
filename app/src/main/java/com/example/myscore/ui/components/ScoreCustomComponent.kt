package com.example.myscore.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myscore.R
import kotlinx.android.synthetic.main.view_score_custom_component.view.*

class ScoreCustomComponent@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
)
    :  ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.view_score_custom_component, this, true)
    }
    fun setScoreTitle(availableLimit: String?) {
        progress_text.text = availableLimit
    }

    fun setMaxProgress(maxProgressValue: Int?) {
        progress_bar.max = maxProgressValue ?: 0
    }
    fun setProgress(progressValue: Int?) {
        progress_bar.progress = progressValue ?: 0
    }
}