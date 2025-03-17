package com.example.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.dao.ProdutoDao
import com.example.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(
    R.layout.activity_formulario_produto
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        configuraBotaoSalvar()
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = findViewById<Button>(R.id.botao_salvar)
        val produtoDao = ProdutoDao()
        botaoSalvar.setOnClickListener(View.OnClickListener {
            val novoProduto = criaProduto()
            produtoDao.adiciona(novoProduto)
            finish()
        })
    }

    private fun criaProduto(): Produto {
        val inputTitulo = findViewById<EditText>(R.id.input_titulo)
        val titulo = inputTitulo.text.toString()
        val inputDescricao = findViewById<EditText>(R.id.input_descricao)
        val descricao = inputDescricao.text.toString()
        val inputValor = findViewById<EditText>(R.id.input_valor)
        val valorString = inputValor.text.toString()

        val valor = if (valorString.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorString)
        }

        return Produto(titulo = titulo, descricao = descricao, valor = valor)
    }
}