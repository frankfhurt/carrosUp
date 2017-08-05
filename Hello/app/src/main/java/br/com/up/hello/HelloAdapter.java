package br.com.up.hello;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Franklyn on 05/08/2017.
 */

public class HelloAdapter extends BaseAdapter {

    private List<Pessoa> pessoas;

    public HelloAdapter(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @Override
    public int getCount() {
        return pessoas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        View viewAdapter = LayoutInflater.from(context).inflate(R.layout.adapter_hello, viewGroup, false);

        Pessoa pessoa = pessoas.get(i);

        TextView textView = viewAdapter.findViewById(R.id.text);
        textView.setText(pessoa.getNome());
        TextView textView2 = viewAdapter.findViewById(R.id.text2);
        textView2.setText(pessoa.getFone());

        return viewAdapter;
    }
}
