package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityDetalhesProdutoBinding
import com.example.orgs.extensions.formataMoedaPtBr
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.model.Produto

class DetalhesProdutoActivity : AppCompatActivity() {

    private var produto: Produto? = null
    private var idProduto: Long = 0L


    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }

    val produtoDao by lazy {
        val db = AppDatabase.getInstance(this)
        db.produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        carregaProduto()
    }


    override fun onResume() {
        super.onResume()

        buscaProdutoBanco()

    }

    private fun buscaProdutoBanco() {
        produto = produtoDao.findOne(idProduto)

        produto?.let {
            preencherDadosProduto(it)
        } ?: finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_detalhes_produto_remover -> {
                produto?.let {
                    produtoDao.delete(it)
                }
                finish()
            }
            R.id.menu_detalhes_produto_editar -> {
                Intent(this, FormularioProdutoActivity::class.java).apply {
                    putExtra(CHAVE_PRODUTO_ID, idProduto)
                    startActivity(this)
                }

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun carregaProduto() {
        idProduto = intent.getLongExtra(CHAVE_PRODUTO_ID, 0)
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