package com.example.orgs.ui.recyclerview.adpter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.databinding.ActivityProdutoItemBinding
import com.example.orgs.extensions.formataMoedaPtBr
import com.example.orgs.model.Produto
import java.text.NumberFormat
import java.util.Locale
import com.example.orgs.extensions.tentaCarregarImagem

class ListaProdutoAdpter(
    private val context: Context,
    produtos: List<Produto> = emptyList(),
    var acessaDetalhesProduto: (produto: Produto) -> Unit = {}
) : RecyclerView.Adapter<ListaProdutoAdpter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    inner class ViewHolder(private val binding: ActivityProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var produto: Produto

        init {
            itemView.setOnClickListener {
                if(::produto.isInitialized) {
                    acessaDetalhesProduto(produto)
                }
            }
        }

        fun vincula(produto: Produto) {
            this.produto = produto
            val titulo = binding.produtoItemTitulo
            titulo.text = produto.titulo
            val descricao = binding.produtoItemDescricao
            descricao.text = produto.descricao
            val valor = binding.produtoItemValor
            valor.text = produto.valor.formataMoedaPtBr()

            val visibilidade = if(produto.imagem != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.imageView.visibility = visibilidade;

            binding.imageView.tentaCarregarImagem(produto.imagem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ActivityProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = produtos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
