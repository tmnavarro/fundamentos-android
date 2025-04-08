package com.example.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityFormularioCadastroProdutosBinding
import com.example.orgs.model.Produto
import java.math.BigDecimal
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.ui.dialog.FormularioImagemDialo

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioCadastroProdutosBinding.inflate(layoutInflater)
    }

    private var url: String? = null
    private var idProduto = 0L

    private val produtoDao by lazy {
        val db = AppDatabase.getInstance(this)
        db.produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastro de Plantas"
        configuraBotaoSalvar()
        binding.formularioProdutosImageView.setOnClickListener {
            FormularioImagemDialo(this).mostra(url) { imagem ->
                url = imagem
                binding.formularioProdutosImageView.tentaCarregarImagem(url)
            }
        }

        carregaProduto()
    }

    override fun onResume() {
        super.onResume()

        produtoDao.findOne(idProduto)?.let {
            title = "Alterar Produto"
            preencheCampos(it)
        }
    }

    fun carregaProduto() {
        idProduto = intent.getLongExtra(CHAVE_PRODUTO_ID, 0)
    }


    fun preencheCampos(produto: Produto) {
        url = produto.imagem
        binding.formularioProdutosImageView.tentaCarregarImagem(produto.imagem)
        binding.inputTitulo.setText(produto.titulo)
        binding.inputDescricao.setText(produto.descricao)
        binding.inputValor.setText(produto.valor.toPlainString())
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.botaoSalvar
        val db = AppDatabase.getInstance(this)
        val produtoDao = db.produtoDao()



        botaoSalvar.setOnClickListener {
            val novoProduto = criaProduto()
            produtoDao.insert(novoProduto)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val inputTitulo = binding.inputTitulo
        val titulo = inputTitulo.text.toString()
        val inputDescricao = binding.inputDescricao
        val descricao = inputDescricao.text.toString()
        val inputValor = binding.inputValor
        val valorString = inputValor.text.toString()

        val valor = if (valorString.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorString)
        }

        return Produto(id = idProduto, titulo = titulo, descricao = descricao, valor = valor, imagem = url)
    }
}