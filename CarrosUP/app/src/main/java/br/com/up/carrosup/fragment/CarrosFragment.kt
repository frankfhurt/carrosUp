package br.com.up.carrosup.fragment

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.up.carrosup.CarroAdapter
import br.com.up.carrosup.R
import br.com.up.carrosup.activity.CarroActivity
import br.com.up.carrosup.domain.Carro
import br.com.up.carrosup.domain.CarroService
import br.com.up.carrosup.domain.TipoCarro
import br.com.up.carrosup.extensions.toast
import kotlinx.android.synthetic.main.fragment_carros.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

open class CarrosFragment : BaseFragment() {

    private var tipo = TipoCarro.classicos
    var carros = listOf<Carro>()

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        tipo = arguments.getSerializable("tipo") as TipoCarro
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, icicle: Bundle?): View? {
        // Retorna a view /res/layout/fragment_carros.xml
        val view = inflater?.inflate(R.layout.fragment_carros, container, false)
        return view
    }

    override fun onViewCreated(view: View?, icicle: Bundle?) {
        super.onViewCreated(view, icicle)
        // Views
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        taskCarros()
    }

    open fun taskCarros() {
        doAsync {
            carros = CarroService.getCarros(context, tipo)
            Log.d("up", "Carros: $carros")

            uiThread {
                    recyclerView.adapter = CarroAdapter(carros) {
                        activity.toast("@Clicou no carro ${it.nome}")
                        activity.startActivity<CarroActivity>("carro" to it)
                    }
            }
        }
    }

/*    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_carros, container, false)

        val tipo = arguments.getSerializable("tipo") as TipoCarro

        val textView = view?.findViewById<TextView>(R.id.text)
        val tipoString = getString(tipo.string)
        textView?.text = "Fragment $tipoString"

        val carros:List<Carro> = CarroService.getCarros(activity, tipo)

        carros.forEach({c -> Log.d("CarrosFragment", c.nome)})

        return view
    }*/
}