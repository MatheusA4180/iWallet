package com.example.iwallet.features.resume.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.example.iwallet.R
import com.example.iwallet.databinding.FragmentThemeNewsBinding
import com.example.iwallet.features.resume.fragments.ResumeFragment.Companion.RESULT_CODE
import com.example.iwallet.features.resume.viewmodel.ThemesNewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ThemesNewsFragment : Fragment() {

    private var _binding: FragmentThemeNewsBinding? = null
    private val binding: FragmentThemeNewsBinding get() = _binding!!
    private val viewModel: ThemesNewsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThemeNewsBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.requestThemeSelected()

        binding.toolbarThemesNews.setNavigationOnClickListener {
            requireActivity().finish()
        }

        binding.themesRadioGroup.setOnCheckedChangeListener { radioGroup, _ ->
            when(radioGroup.checkedRadioButtonId){
                R.id.cripto_radio ->
                    selectRadioButtonId(CRIPTO_RADIO)
                R.id.stocks_radio ->
                    selectRadioButtonId(STOCKS_RADIO)
                R.id.reits_radio ->
                    selectRadioButtonId(REITS_RADIO)
                R.id.welfare_radio ->
                    selectRadioButtonId(WELFARE_RADIO)
            }
        }

        viewModel.selectedTheme.observe(viewLifecycleOwner,{
            binding.themesRadioGroup.check(it)
        })

    }

    private fun selectRadioButtonId(theme: String) {
        viewModel.selectedThemeNews(theme)
        requireActivity().setResult(
            RESULT_CODE,
            Intent().apply {
                putExtra(THEME_NEWS, theme)
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val THEME_NEWS = "themesNews"
        const val CRIPTO_RADIO = "criptoativos"
        const val STOCKS_RADIO = "mercado de ações"
        const val REITS_RADIO = "FIIs"
        const val WELFARE_RADIO = "Previdencia"
    }

}
