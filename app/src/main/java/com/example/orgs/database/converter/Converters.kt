package com.example.orgs.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {

    @TypeConverter
    fun doubleToBigDecimal(value: Double?): BigDecimal {
        return value?.let {
             BigDecimal(value.toString())
        } ?: run {
             BigDecimal.ZERO
        }
    }

    @TypeConverter
    fun bigDecimalToDouble(value: BigDecimal?): Double? {
        return value?.let { value.toDouble()}
    }

}