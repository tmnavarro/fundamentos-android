package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.ui.recyclerview.adpter.ListaProdutoAdpter
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityListaProdutosBinding

class ListaProdutosActivity : AppCompatActivity() {

    private val adapter =
        ListaProdutoAdpter(context = this)

    // coloca o nome que quiser usa o shift+F6 para renomear
    // caso necessário
    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Minhas plantas"
        setContentView(binding.root)
        configBtnNovoProduto()
        configuraRecycleView()
    }

    private fun configBtnNovoProduto() {
        val botaoSalvar = binding.botaoCriarProduto

        botaoSalvar.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.getInstance(this)

        val produtoDao = db.produtoDao()

        adapter.atualiza(produtoDao.getAll())
    }

    private fun configuraRecycleView() {
        val recyclerView = binding.recyclerViewListaProdutos
        recyclerView.adapter = adapter
        adapter.acessaDetalhesProduto = {
            val intent = Intent(
                this,
                DetalhesProdutoActivity::class.java
            ).apply {
                putExtra(CHAVE_PRODUTO, it)
            }
            startActivity(intent)
        }

        adapter.deletarProduto = {
            println("DELETAR PRODUTO")
        }

        adapter.editarProduto = {
            println("EDITAR PRODUTO")
        }
    }
}