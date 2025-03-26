package com.example.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.orgs.databinding.ActivityFormularioImagemBinding
import com.example.orgs.extensions.tentaCarregarImagem

class FormularioImagemDialo(val context: Context) {

    fun mostra(
        urlPadrao: String? = null,
        quandoCarregarImagem: (imagem: String) -> Unit
    ) {
        ActivityFormularioImagemBinding.inflate(LayoutInflater.from(context)).apply {
            urlPadrao?.let {
                formularioImagemImageView.tentaCarregarImagem(it)
                formularioImagemUrl.setText(it)
            }
            val formularioImagemBotaoCarregar =
                formularioImagemBotaoCarregar

            formularioImagemBotaoCarregar.setOnClickListener {
                val url = formularioImagemUrl.text.toString()
                formularioImagemImageView.tentaCarregarImagem(url)
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = formularioImagemUrl.text.toString()
                    quandoCarregarImagem(url)
                }
                .setNegativeButton("Cancelar") { _, _ ->

                }
                .show()
        }
    }
}