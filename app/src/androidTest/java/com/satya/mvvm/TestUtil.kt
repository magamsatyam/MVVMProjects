package com.satya.mvvm

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.GsonBuilder
import com.google.gson.internal.`$Gson$Types`.arrayOf
import com.satya.mvvm.model.acronym.AcronymItem
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream
import java.lang.reflect.Type

/**
 * Created by Satya
 */

object TestUtil {
    private var mGsonConverter: GsonConverterFactory? = null
    var dataStatus: DataStatus = DataStatus.Success
    var acronymItem: AcronymItem = AcronymItem(arrayListOf(), listOf())
    fun initData(): AcronymItem {
        val moshi = Moshi.Builder()
                .add(gsonConverter)
                .build()
        val type: Type = Types.newParameterizedType(List::class.java, AcronymItem::class.java)
        val adapter: JsonAdapter<List<AcronymItem>> = moshi.adapter(type)
        val jsonString = getJson("acronym.json")
        adapter.fromJson(jsonString)?.let {
            acronymItem = AcronymItem(ArrayList(it), listOf())
            return acronymItem
        }
        return AcronymItem(arrayListOf(), listOf())
    }

    private fun getJson(path: String): String {
        val ctx: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val inputStream: InputStream = ctx.classLoader.getResourceAsStream(path)
        return inputStream.bufferedReader().use { it.readText() }
    }

    private val gsonConverter: GsonConverterFactory
        get() {
            if(mGsonConverter == null){
                mGsonConverter = GsonConverterFactory
                    .create(
                        GsonBuilder()
                            .setLenient()
                            .disableHtmlEscaping()
                            .create())
            }
            return mGsonConverter!!
        }
}
