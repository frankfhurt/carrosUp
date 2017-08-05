package br.com.up.hello

import java.util.ArrayList

/**
 * Created by Franklyn on 05/08/2017.
 */

object PessoaService {

    fun getPessoas(): List<Pessoa> {
        val pessoas = ArrayList<Pessoa>()

        for (i in 0..49) {
            pessoas.add(Pessoa("Pessoa " + i, "Fone " + i))
        }

        return pessoas
    }
}