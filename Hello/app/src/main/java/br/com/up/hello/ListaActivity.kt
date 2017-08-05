package br.com.up.hello

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_lista.*

class ListaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        listview.adapter = HelloAdapter(PessoaService.getPessoas())

        /*Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");

        TextView text = findViewById(R.id.text);
        text.setText("Nome: " + nome);*/
    }
}
