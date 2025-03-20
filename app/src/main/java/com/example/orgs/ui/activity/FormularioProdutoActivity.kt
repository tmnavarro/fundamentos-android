package com.example.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.dao.ProdutoDao
import com.example.orgs.databinding.ActivityFormularioCadastroProdutosBinding
import com.example.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioCadastroProdutosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
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

        return Produto(titulo = titulo, descricao = descricao, valor = valor)
    }
}