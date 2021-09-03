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
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.iwallet.R
import com.example.iwallet.databinding.ProfileDialogBinding
import com.example.iwallet.features.resume.adapter.ListOptionsProfileAdapter

class ProfileDialog: DialogFragment(), ListOptionsProfileAdapter.ClickedProfileListener {

    private var _binding: ProfileDialogBinding? = null
    private val binding: ProfileDialogBinding get() = _binding!!
    private val listOptionsProfile: List<ListOptionsProfileAdapter.ItemOptionProfile> by lazy {
        listOf(
            ListOptionsProfileAdapter.ItemOptionProfile(
                "Meu perfil",
                R.drawable.ic_profile
            ),
            ListOptionsProfileAdapter.ItemOptionProfile(
                "lixo",
                R.drawable.ic_trash
            )
//            ListOptionsProfileAdapter.ItemOptionProfile(
//                getString(R.string.my_profile),
//                R.drawable.ic_user
//            ),
//            ListOptionsProfileAdapter.ItemOptionProfile(
//                getString(R.string.income_tax),
//                R.drawable.ic_money_white
//            ),
//            ListOptionsProfileAdapter.ItemOptionProfile(
//                getString(R.string.benefits_and_tecnobank),
//                R.drawable.ic_star
//            ), ListOptionsProfileAdapter.ItemOptionProfile(
//                getString(R.string.nominate_and_win),
//                R.drawable.ic_nominate_and_win
//            ),
//            ListOptionsProfileAdapter.ItemOptionProfile(
//                getString(R.string.offer),
//                R.drawable.ic_win_oferts
//            ),
//            ListOptionsProfileAdapter.ItemOptionProfile(
//                getString(R.string.limits_pix),
//                R.drawable.ic_limits_pix
//            ), ListOptionsProfileAdapter.ItemOptionProfile(
//                getString(R.string.help_center),
//                R.drawable.ic_help
//            ),
//            ListOptionsProfileAdapter.ItemOptionProfile(
//                getString(R.string.security),
//                R.drawable.ic_secure
//            ),
//            ListOptionsProfileAdapter.ItemOptionProfile(
//                getString(R.string.close_account),
//                R.drawable.ic_close_account
//            ),
//            ListOptionsProfileAdapter.ItemOptionProfile(
//                getString(R.string.exit),
//                R.drawable.ic_exit_login
//            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        if(positionRecyclerView == 6){
            openWebPage("https://chat.blip.ai/?appKey=YXNzaXN0ZW50ZXZpcnR1YWx0ZWNub2Jhbms6MjEyNDY4M2QtYmMzMS00ZmUyLTlkMDQtYWRmODhmMDU4YWMx")
        }else if (positionRecyclerView == 9) {
            AlertDialog.Builder(requireContext())
                .setTitle("Deseja sair do aplicativo?")
                .setMessage("")
                .setNegativeButton("Não") { _, _ -> }
                .setPositiveButton("Sim") { _, _ -> requireActivity().finish() }
                .create()
                .show()
        }
    }

    fun openWebPage(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

}