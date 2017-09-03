package br.com.up.carrosup.activity

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import br.com.up.carrosup.R
import br.com.up.carrosup.domain.Carro
import br.com.up.carrosup.domain.CarroService
import br.com.up.carrosup.domain.TipoCarro
import br.com.up.carrosup.extensions.toast
import kotlinx.android.synthetic.main.activity_adicionar_carro.*
import kotlinx.android.synthetic.main.content_adicionar_carro.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AdicionarCarroActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_carro)
        setSupportActionBar(toolbar)

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/

        spinner_tipo.adapter = ArrayAdapter<TipoCarro>(this, android.R.layout.simple_spinner_item, TipoCarro.values())
    }

    fun cadastrar(v: View) {

        var carro: Carro = Carro()

        carro.nome = nome_carro.text.toString()
        carro.tipo = spinner_tipo.selectedItem.toString()

        toast("Nome: ${carro.nome} \nTipo: ${carro.tipo}")

        doAsync {
            val response = CarroService.save(carro)
            uiThread {
                toast(response.msg)
            }
        }
    }
}
