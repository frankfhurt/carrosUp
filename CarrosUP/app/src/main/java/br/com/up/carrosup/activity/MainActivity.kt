package br.com.up.carrosup.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import br.com.up.carrosup.R
import br.com.up.carrosup.TabsAdapter
import br.com.up.carrosup.domain.TipoCarro
import br.com.up.carrosup.extensions.addFragment
import br.com.up.carrosup.extensions.setupToolbar
import br.com.up.carrosup.extensions.toast
import br.com.up.carrosup.fragment.CarrosFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar(R.id.toolbar, "Carros UP")

        setupNavDrawer()

        setupViewPagerTabs()
    }

    private fun setupViewPagerTabs() {
        // Configura o ViewPager + Tabs
        // As variáveis viewPager e tabLayout são geradas automaticamente pelo Kotlin Extensions
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = TabsAdapter(context, supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
        // Cor branca no texto (o fundo azul é definido no layout)
        val cor = ContextCompat.getColor(context, R.color.white)
        tabLayout.setTabTextColors(cor, cor)
    }

    private fun setupNavDrawer() {
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_item_carros_todos -> {
                toast("Clicou em carros")
            }
            R.id.nav_item_carros_classicos -> {
                toast("Clicou em carros clássicos")
                // startActivity<CarrosActivity>("tipo" to TipoCarro.classicos)

                showCarros(TipoCarro.classicos)
            }
            R.id.nav_item_carros_esportivos -> {
                toast("Clicou em carros esportivos")
                // startActivity<CarrosActivity>("tipo" to TipoCarro.esportivos)
                showCarros(TipoCarro.esportivos)
            }
            R.id.nav_item_carros_luxo -> {
                toast("Clicou em carros luxo")
                // startActivity<CarrosActivity>("tipo" to TipoCarro.luxo)
                showCarros(TipoCarro.luxo)
            }
            R.id.nav_item_site_livro -> {
                toast("Clicou em site do livro")
            }
            R.id.nav_item_settings -> {
                toast("Clicou em configurações")
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showCarros(tipo: TipoCarro) {
        val carros = CarrosFragment()
        val bundle = Bundle()
        bundle.putSerializable("tipo", tipo)
        carros.arguments = bundle
        addFragment(R.id.layoutFrag, carros)
    }

    fun addCar(v: View) {
        toast("Clicou no botãzinho para adicionar!")
        startActivity<AdicionarCarroActivity>()
    }
}
