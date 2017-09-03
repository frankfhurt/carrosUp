package br.com.up.carrosup.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.com.up.carrosup.R
import br.com.up.carrosup.domain.Carro
import br.com.up.carrosup.domain.FavoritosService
import br.com.up.carrosup.extensions.setupToolbar
import br.com.up.carrosup.extensions.toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_carro.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CarroActivity : BaseActivity(), OnMapReadyCallback {

    // Objeto que controla o Google Maps
    private var map: GoogleMap? = null

    val carro: Carro by lazy { intent.getSerializableExtra("carro") as Carro }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)
        setupToolbar(R.id.toolbar)

        car_name.text = carro.nome

        // Inicia o Mapa
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap) {
        // O método onMapReady(map) é chamado quando a inicialização do mapa estiver Ok
        this.map = map

        // Tipo do mapa: normal, satélite, terreno ou híbrido
        map.mapType = GoogleMap.MAP_TYPE_NORMAL

        // Vamos mostrar a localização do usuário apenas para carros com lat/lng=0.
        if (carro.latitude.toDouble() != 0.0) {
            // Cria o objeto lat/lng com a coordenada da fábrica
            val location = LatLng(carro.latitude.toDouble(),
                    carro.longitude.toDouble())

            // Posiciona o mapa na coordenada da fábrica (zoom = 13)
            val update = CameraUpdateFactory.newLatLngZoom(location, 13f)
            map.moveCamera(update)

            // Marcador no local da fábrica
            map.addMarker(MarkerOptions()
                    .title(carro.nome)
                    .snippet(carro.desc)
                    .position(location))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_carro, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_favoritar) {
            doAsync {
                FavoritosService.favoritar(carro)
                uiThread {
                    toast("Carro ${carro.nome} favoritado!")
                }
            }
        } else if (item?.itemId == R.id.action_video) {
            val url = carro.urlVideo
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(url), "video/*")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
