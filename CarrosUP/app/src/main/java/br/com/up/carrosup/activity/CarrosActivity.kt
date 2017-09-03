package br.com.up.carrosup.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.up.carrosup.R
import br.com.up.carrosup.extensions.setupToolbar
import br.com.up.carrosup.fragment.CarrosFragment

class CarrosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carros)

        setupToolbar(R.id.toolbar, "Carros UP", true)

        val carrosFrag = CarrosFragment()

        carrosFrag.arguments = intent.extras

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.layoutFrag, carrosFrag)
        transaction.commit()
    }
}
