package com.prices.products.productsprices;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListCellCandidatos extends BaseAdapter {

    private List<Candidato> candidatos;
    private final Activity context;

    public ListCellCandidatos(Activity context, List<Candidato> list) {
        this.context = context;
        this.candidatos = list;
    }

    @Override
    public int getCount() {
        return candidatos.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, final View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.nome);
        TextView txtId = (TextView) rowView.findViewById(R.id.partido);
        ImageView image = (ImageView) rowView.findViewById(R.id.foto);

        txtTitle.setText(candidatos.get(position).getNome());
        txtId.setText(candidatos.get(position).getId());
        image.setImageBitmap(candidatos.get(position).getFoto());

        return rowView;
    }
}
