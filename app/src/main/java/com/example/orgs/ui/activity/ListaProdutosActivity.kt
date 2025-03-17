package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.ui.recyclerview.adpter.ListaProdutoAdpter
import com.example.orgs.R
import com.example.orgs.dao.ProdutoDao
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaProdutosActivity : AppCompatActivity(R.layout.activity_lista_produtos) {
    private val produtoDao = ProdutoDao()
    private val adpter =
        ListaProdutoAdpter(context = this, produtos = produtoDao.buscaTodosProdutos())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraFab()
        configuraRecycleView()
    }

    private fun configuraFab() {
        val botaoNovoProduto = findViewById<FloatingActionButton>(R.id.botao_criar_produto)

        botaoNovoProduto.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        adpter.atualiza(produtoDao.buscaTodosProdutos())
    }

    private fun configuraRecycleView() {
        val recyclerViewListaProdutos = findViewById<RecyclerView>(R.id.recyclerViewListaProdutos)
        recyclerViewListaProdutos.adapter = adpter

    }


}