package br.com.up.carrosup

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import br.com.up.carrosup.domain.TipoCarro
import br.com.up.carrosup.fragment.CarrosFragment

/**
 * Created by Franklyn on 19/08/2017.
 */
class TabsAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    // Qtde de Tabs
    override fun getCount(): Int = 4

    // Retorna o tipo pela posição
    fun getTipoCarro(position: Int) = when (position) {
        0 -> TipoCarro.classicos
        1 -> TipoCarro.esportivos
        2 -> TipoCarro.luxo
        else -> TipoCarro.favoritos
    }

    // Título da Tab
    override fun getPageTitle(position: Int): CharSequence {
        val tipo = getTipoCarro(position)
        return context.getString(tipo.string)
    }

    // Fragment que vai mostrar a lista de carros
    override fun getItem(position: Int): Fragment {
        val tipo = getTipoCarro(position)
        val f: Fragment = CarrosFragment()
        f.arguments = Bundle()
        f.arguments.putSerializable("tipo", tipo)
        return f
    }
}