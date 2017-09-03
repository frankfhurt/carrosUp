package br.com.up.carrosup.fragment

import br.com.up.carrosup.CarroAdapter
import br.com.up.carrosup.activity.CarroActivity
import br.com.up.carrosup.domain.FavoritosService
import br.com.up.carrosup.extensions.toast
import kotlinx.android.synthetic.main.fragment_carros.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class FavoritosFragment : CarrosFragment() {

    override fun taskCarros() {
        doAsync {
            // Busca os carros
            carros = FavoritosService.getCarros()
            uiThread {
                recyclerView.adapter = CarroAdapter(carros) {
                    activity.toast("@Clicou no carro ${it.nome}")
                    activity.startActivity<CarroActivity>("carro" to it)
                }

                // Termina a animação do Swipe to Refresh
                // swipeToRefresh.isRefreshing = false
            }
        }
    }
}