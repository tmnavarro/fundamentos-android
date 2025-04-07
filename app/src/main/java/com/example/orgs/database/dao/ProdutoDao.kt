package com.example.orgs.database.dao

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.orgs.model.Produto


@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun getAll(): List<Produto>

    @Insert
    fun insert(produto: Produto)

    @Insert
    fun insertAll(vararg produto: Produto)

}