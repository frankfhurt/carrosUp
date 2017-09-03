package br.com.up.carrosup

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.up.carrosup.domain.Carro
import br.com.up.carrosup.extensions.loadUrl
import kotlinx.android.synthetic.main.adapter_carro.view.*

/**
 * Created by Franklyn on 19/08/2017.
 */
class CarroAdapter (val carros: List<Carro>, val onClick: (Carro) -> Unit) :
        RecyclerView.Adapter<CarroAdapter.CarrosViewHolder>() {

    // ViewHolder com as views
    class CarrosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
/*        var tNome: TextView
        var img: ImageView
        var progress: ProgressBar
        var cardView: CardView

        init {
            // Salva as views no ViewHolder
            tNome = view.findViewById<TextView>(R.id.tNome)
            img = view.findViewById<ImageView>(R.id.img)
            progress = view.findViewById<ProgressBar>(R.id.progress)
            cardView = view.findViewById<CardView>(R.id.card_view)
        }*/
    }

    // Retorna a quantidade de carros na lista
    override fun getItemCount() = this.carros.size

    // Infla o layout do adapter e retorna o ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrosViewHolder {
        // Infla a view do adapter
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_carro, parent, false)
        // Retorna o ViewHolder que contém todas as views
        val holder = CarrosViewHolder(view)
        return holder
    }

    // Faz o bind para atualizar o valor das views com os dados do Carro
    override fun onBindViewHolder(holder: CarrosViewHolder, position: Int) {
        val context = holder.itemView.context
        // Recupera o objeto carro
        val carro = carros[position]

        val view = holder.itemView

        with(view) {
            // Atualiza os dados do carro
            tNome.text = carro.nome
            progress.visibility = View.VISIBLE
            // Faz o download da foto e mostra o ProgressBar
            img.loadUrl(carro.urlFoto, progress)
            // Adiciona o evento de clique na linha
            setOnClickListener { onClick(carro) }
        }
    }
}