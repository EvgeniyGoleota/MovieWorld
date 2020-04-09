package com.escorp.movieworld.core.ui.feature.recoverable

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.escorp.movieworld.core.R
import java.util.*

class RecoverableDialogFragment : DialogFragment() {

    private lateinit var throwable: Throwable

    private val recoverable: Recoverable?
        get() = targetFragment?.let { fragment ->
            RecoverableViewModel.get(fragment)
        }

    private val message: String
        get() = when (throwable) {
            // todo implement DataException
            else -> {
                getString(R.string.dialog_recoverable_message_generic, throwable.message.orEmpty())
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        throwable = requireNotNull(arguments?.getSerializable(ARG_THROWABLE) as Throwable)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setTitle(R.string.dialog_recoverable_title)
            .setMessage(message)
            .setPositiveButton(R.string.all_retry) { dialog, _ ->
                dialog.dismiss()
                recoverable?.sendEvent(Recoverable.Event.Retry(throwable))
            }
            .setNegativeButton(R.string.all_cancel) { dialog, _ ->
                dialog.dismiss()
                recoverable?.sendEvent(Recoverable.Event.Cancel(throwable))
            }
            .create()
            .apply {
                setCanceledOnTouchOutside(false)
            }
    }

    companion object {

        private const val ARG_THROWABLE = "ARG_THROWABLE"

        fun show(fragment: Fragment, throwable: Throwable) {
            RecoverableDialogFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_THROWABLE, throwable)
                }
                setTargetFragment(fragment, 0)
                show(
                    fragment.fragmentManager!!,
                    "${RecoverableDialogFragment::class.java.simpleName}-${UUID.randomUUID()}"
                )
            }
        }
    }
}