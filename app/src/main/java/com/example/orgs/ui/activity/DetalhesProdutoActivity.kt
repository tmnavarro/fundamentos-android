package com.example.orgs.ui.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.databinding.ActivityDetalhesProdutoBinding
import com.example.orgs.extensions.formataMoedaPtBr
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.model.Produto

class DetalhesProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        carregaProduto()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun carregaProduto() {
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            preencherDadosProduto(produtoCarregado)
        } ?: finish()
    }

    private fun preencherDadosProduto(produto: Produto) {
        with(binding) {
            detalhesProdutoImageView.tentaCarregarImagem(produto.imagem)
            detalhesProdutoTitulo.text = produto.titulo
            detalhesProdutoDescricao.text = produto.descricao
            detalhesProdutoValor.text = produto.valor.formataMoedaPtBr()
        }
    }
}