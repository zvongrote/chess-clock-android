package com.zachvg.chessclock.clock

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.zachvg.chessclock.R
import com.zachvg.chessclock.databinding.ClockButtonBinding

class ClockButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    private val binding: ClockButtonBinding

    var timeText: String
        get() = binding.timeText.text.toString()
        set(value) { binding.timeText.text = value }

    var movesText: String
        get() = binding.movesText.text.toString()
        set(value) { binding.movesText.text = value }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ClockButtonBinding.inflate(inflater, this, true)
        setBackgroundResource(R.drawable.clock_button_background)
    }
}

@BindingAdapter("time_text")
fun ClockButton.setTimeText(time: String) {
    timeText = time
}

@BindingAdapter("moves_text")
fun ClockButton.setMovesText(moves: String) {
    movesText = moves
}