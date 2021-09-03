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
                "Central de ajuda",
                R.drawable.ic_help
            ),
            ListOptionsProfileAdapter.ItemOptionProfile(
                "Segurança",
                R.drawable.ic_security
            ),
            ListOptionsProfileAdapter.ItemOptionProfile(
                "Sobre",
                R.drawable.ic_about
            ),
            ListOptionsProfileAdapter.ItemOptionProfile(
                "Sair da Conta",
                R.drawable.ic_exit_login
            ),
            ListOptionsProfileAdapter.ItemOptionProfile(
                "Excluir Conta",
                R.drawable.ic_trash
            )
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
        if(positionRecyclerView == 1){
            openWebPage("https://chat.blip.ai/?appKey=YXNzaXN0ZW50ZXZpcnR1YWx0ZWNub2Jhbms6MjEyNDY4M2QtYmMzMS00ZmUyLTlkMDQtYWRmODhmMDU4YWMx")
        }else if (positionRecyclerView == 4) {
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