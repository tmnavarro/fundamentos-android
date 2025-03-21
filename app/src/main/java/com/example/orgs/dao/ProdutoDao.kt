package com.example.orgs.dao

import com.example.orgs.model.Produto
import java.math.BigDecimal

class ProdutoDao {

    // Teste

    fun adiciona(produto: Produto) {
        Companion.produtos.add(produto)
    }

    fun buscaTodosProdutos(): List<Produto> {
        return Companion.produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto(
                titulo = "Meu produto",
                descricao = "A descrição que eu quiser",
                valor = BigDecimal("16.89")
            )
        )
    }
}