package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
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



    private val produtoDao by lazy {
        val db = AppDatabase.getInstance(this)
        db.produtoDao()
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
        atualizaProdutos()
    }

    private fun atualizaProdutos() {
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
                putExtra(CHAVE_PRODUTO_ID, it.id)
            }
            startActivity(intent)
        }

        adapter.deletarProduto = {
            produtoDao.delete(it)
            atualizaProdutos()
        }

        adapter.editarProduto = {
            Intent(this, FormularioProdutoActivity::class.java).apply {
                putExtra(CHAVE_PRODUTO_ID, it.id)
            }.also {
                startActivity(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista_produtos, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_ordenar_valor -> {
                adapter.atualiza(produtoDao.getAll().sortedBy { it.valor })
            }
            R.id.menu_ordenar_titulo -> {
                adapter.atualiza(produtoDao.getAll().sortedBy { it.titulo })
            }
        }

        return super.onOptionsItemSelected(item)
    }
}