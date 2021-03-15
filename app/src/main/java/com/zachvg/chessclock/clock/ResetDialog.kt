package com.zachvg.chessclock.clock

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.zachvg.chessclock.R

/**
 * Dialog that confirms if the user wants to reset the clock
 */
class ResetDialog(private val onPositiveClickListener: DialogInterface.OnClickListener) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return requireActivity().let {
            val builder = AlertDialog.Builder(it).apply {
                setMessage(R.string.reset_clock)
                setPositiveButton(R.string.yes, onPositiveClickListener)
                setNegativeButton(R.string.no, null)
            }

            builder.create()
        }
    }
}