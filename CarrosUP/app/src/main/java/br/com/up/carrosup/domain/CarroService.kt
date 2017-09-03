package br.com.up.carrosup.domain

import android.content.Context
import android.util.Log
import br.com.up.carrosup.dao.DatabaseManager
import br.com.up.carrosup.extensions.fromJson
import br.com.up.carrosup.extensions.toJson
import br.com.up.carrosup.utils.HttpHelper

/**
 * Created by Franklyn on 19/08/2017.
 */
object CarroService {

    private val BASE_URL = "http://livrowebservices.com.br/rest/carros"

    fun getCarros(context: Context, tipo: TipoCarro): List<Carro> {
        when (tipo) {
            TipoCarro.favoritos -> return DatabaseManager.getCarroDAO().findAll()
            else -> {
                val url = "$BASE_URL/tipo/${tipo.name}"
                val json = HttpHelper.get(url)
                Log.d("up", json)

                val tipoString = context.getString(tipo.string)

                return fromJson(json)
            }
        }
    }

    fun save(carro: Carro): Response {
        val json = HttpHelper.post(BASE_URL, carro.toJson())
        val response = fromJson<Response>(json)

        return response
    }
}