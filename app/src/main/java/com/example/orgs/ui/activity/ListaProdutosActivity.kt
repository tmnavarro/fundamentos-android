package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.ui.recyclerview.adpter.ListaProdutoAdpter
import com.example.orgs.R
import com.example.orgs.dao.ProdutoDao
import com.example.orgs.databinding.ActivityListaProdutosBinding

class ListaProdutosActivity : AppCompatActivity() {
    private val produtoDao = ProdutoDao()
    private val adpter =
        ListaProdutoAdpter(context = this, produtos = produtoDao.buscaTodosProdutos())

    // coloca o nome que quiser usa o shift+F6 para renomear
    // caso necessário
    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        adpter.atualiza(produtoDao.buscaTodosProdutos())
    }

    private fun configuraRecycleView() {
        val recyclerViewListaProdutos = findViewById<RecyclerView>(R.id.recyclerViewListaProdutos)
        recyclerViewListaProdutos.adapter = adpter

    }
}