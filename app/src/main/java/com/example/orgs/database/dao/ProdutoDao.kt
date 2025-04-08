package com.example.orgs.database.dao

import android.util.Log
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.orgs.model.Produto


@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun getAll(): List<Produto>


    @Query("SELECT * FROM Produto WHERE id = :id")
    fun findOne(id: Long): Produto?

    @Insert
    fun insert(produto: Produto)

    @Insert
    fun insertAll(vararg produto: Produto)

    @Delete
    fun delete(vararg produto: Produto)

    @Update
    fun update(vararg produto: Produto)

}