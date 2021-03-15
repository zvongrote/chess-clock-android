package com.zachvg.chessclock.clock

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.zachvg.chessclock.R
import com.zachvg.chessclock.databinding.ClockButtonBinding

private const val BACKGROUND_COLOR_ACTIVE = 0

class ClockButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    private val binding: ClockButtonBinding

    var state: State = State.INACTIVE
        set(value) {
            field = value

            when (value) {
                State.ACTIVE -> setActiveColors()
                State.INACTIVE -> setInactiveColors()
                State.OUT_OF_TIME -> setOutOfTimeColors()
            }
        }

    var timeText: String
        get() = binding.timeText.text.toString()
        set(value) {
            binding.timeText.text = value
        }

    var movesText: String
        get() = binding.movesText.text.toString()
        set(value) {
            binding.movesText.text = value
        }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ClockButtonBinding.inflate(inflater, this, true)
        setBackgroundResource(R.drawable.clock_button_background_inactive)
    }

    private fun setOutOfTimeColors() {
        setBackgroundResource(R.drawable.clock_button_background_out_of_time)
    }

    private fun setInactiveColors() {
        setBackgroundResource(R.drawable.clock_button_background_inactive)
    }

    private fun setActiveColors() {
        setBackgroundResource(R.drawable.clock_button_background_active)
    }

    enum class State {
        ACTIVE,
        INACTIVE,
        OUT_OF_TIME
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