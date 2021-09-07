package com.example.iwallet.features.resume

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.iwallet.R
import com.example.iwallet.databinding.ProfileDialogBinding
import com.example.iwallet.features.resume.adapter.ListOptionsProfileAdapter
import com.example.iwallet.utils.model.resume.ItemOptionProfile

class ProfileDialog : DialogFragment(), ListOptionsProfileAdapter.ClickedProfileListener {

    private var _binding: ProfileDialogBinding? = null
    private val binding: ProfileDialogBinding get() = _binding!!
    private val listOptionsProfile: List<ItemOptionProfile> by lazy {
        listOf(
            ItemOptionProfile(
                getString(R.string.option_menu_dialog_1),
                R.drawable.ic_profile
            ),
            ItemOptionProfile(
                getString(R.string.option_menu_dialog_2),
                R.drawable.ic_help
            ),
            ItemOptionProfile(
                getString(R.string.option_menu_dialog_3),
                R.drawable.ic_security
            ),
            ItemOptionProfile(
                getString(R.string.option_menu_dialog_4),
                R.drawable.ic_about
            ),
            ItemOptionProfile(
                getString(R.string.option_menu_dialog_5),
                R.drawable.ic_exit_login
            ),
            ItemOptionProfile(
                getString(R.string.option_menu_dialog_6),
                R.drawable.ic_trash
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileDialogBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listOptionsProfile.adapter =
            ListOptionsProfileAdapter(listOptionsProfile, this@ProfileDialog)

    }

    override fun clickProfileListener(positionRecyclerView: Int) {
        when (positionRecyclerView) {
            CHATBOT -> {
                openWebPage(getString(R.string.link_chatbot))
            }
            ABOUT_APP -> {
                openWebPage(getString(R.string.link_readme))
            }
            EXIT_APP -> {
                AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.title_profile_dialog))
                    .setMessage("")
                    .setNegativeButton(
                        getString(
                            R.string.option_profile_dialog_1
                        )
                    ) { _, _ -> }
                    .setPositiveButton(
                        getString(R.string.option_profile_dialog_2)
                    ) { _, _ -> requireActivity().finish() }
                    .create()
                    .show()
            }
        }
    }

    fun openWebPage(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    companion object {
        private const val CHATBOT = 1
        private const val ABOUT_APP = 3
        private const val EXIT_APP = 4
    }

}
