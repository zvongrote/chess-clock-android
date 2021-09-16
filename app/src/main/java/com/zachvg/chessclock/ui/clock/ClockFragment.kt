package com.zachvg.chessclock.ui.clock

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.zachvg.chessclock.R
import com.zachvg.chessclock.databinding.FragmentClockBinding
import com.zachvg.chessclock.domain.ChessClock
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ClockFragment : Fragment() {

    private val viewModel: ClockViewModel by viewModels()
    private lateinit var binding: FragmentClockBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_clock, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        viewModel.player1State.observe(viewLifecycleOwner) { playerState ->
            when (playerState) {
                ChessClock.PlayerState.INACTIVE -> binding.player1Button.state =
                    ClockButton.State.INACTIVE
                ChessClock.PlayerState.ACTIVE -> binding.player1Button.state =
                    ClockButton.State.ACTIVE
                ChessClock.PlayerState.OUT_OF_TIME -> binding.player1Button.state =
                    ClockButton.State.OUT_OF_TIME
                else -> Unit // Do nothing
            }
        }

        viewModel.player2State.observe(viewLifecycleOwner) { playerState ->
            when (playerState) {
                ChessClock.PlayerState.INACTIVE -> binding.player2Button.state =
                    ClockButton.State.INACTIVE
                ChessClock.PlayerState.ACTIVE -> binding.player2Button.state =
                    ClockButton.State.ACTIVE
                ChessClock.PlayerState.OUT_OF_TIME -> binding.player2Button.state =
                    ClockButton.State.OUT_OF_TIME
                else -> Unit // Do nothing
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventFlow.collect {
                    when (it) {
                        ClockViewModel.Event.ShowResetDialog -> showResetDialog()
                    }
                }
            }
        }
    }

    private fun showResetDialog() {
        val positiveDialogListener =
            DialogInterface.OnClickListener { _, _ -> viewModel.resetClock() }

        ResetDialog(positiveDialogListener).show(requireActivity().supportFragmentManager, "reset")
    }
}