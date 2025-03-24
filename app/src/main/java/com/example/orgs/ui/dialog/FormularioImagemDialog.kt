package com.example.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.example.orgs.databinding.FormularioImagemBinding
import com.example.orgs.extensions.tentaCarregarImagem

class FormularioImagemDialo(val context: Context) {

    fun mostra(quandoCarregarImagem: (imagem: String) -> Unit) {
        val binding = FormularioImagemBinding.inflate(LayoutInflater.from(context))

        val formularioImagemBotaoCarregar =
            binding.formularioImagemBotaoCarregar

        formularioImagemBotaoCarregar.setOnClickListener {
            val url = binding.formularioImagemUrl.text.toString()
            binding.formularioImagemImageView.tentaCarregarImagem(url)
        }

        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Confirmar") { _, _ ->
                val url = binding.formularioImagemUrl.text.toString()
                quandoCarregarImagem(url)
            }
            .setNegativeButton("Cancelar") { _, _ ->

            }
            .show()
    }
}