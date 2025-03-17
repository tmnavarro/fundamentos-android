package com.example.orgs.dao

import com.example.orgs.model.Produto

class ProdutoDao {

    fun adiciona(produto: Produto) {
        Companion.produtos.add(produto)
    }

    fun buscaTodosProdutos(): List<Produto>
    {
        return Companion.produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>()
    }
}