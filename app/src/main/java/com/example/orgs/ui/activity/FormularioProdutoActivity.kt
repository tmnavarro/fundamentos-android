package com.example.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.orgs.dao.ProdutoDao
import com.example.orgs.databinding.ActivityFormularioCadastroProdutosBinding
import com.example.orgs.model.Produto
import java.math.BigDecimal
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.ui.dialog.FormularioImagemDialo

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioCadastroProdutosBinding.inflate(layoutInflater)
    }

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastro de Plantas"
        configuraBotaoSalvar()
        binding.formularioProdutosImageView.setOnClickListener {
           FormularioImagemDialo(this).mostra(url) {
               imagem ->
               url = imagem
               binding.formularioProdutosImageView.tentaCarregarImagem(url)
           }
        }

    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.botaoSalvar
        val produtoDao = ProdutoDao()
        botaoSalvar.setOnClickListener  {
            val novoProduto = criaProduto()
            produtoDao.adiciona(novoProduto)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val inputTitulo = binding.inputTitulo
        val titulo = inputTitulo.text.toString()
        val inputDescricao = binding.inputDescricao
        val descricao = inputDescricao.text.toString()
        val inputValor = binding.inputValor
        val valorString = inputValor.text.toString()

        val valor = if (valorString.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorString)
        }

        return Produto(titulo = titulo, descricao = descricao, valor = valor, imagem = url)
    }
}