package com.prices.products.productsprices;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.Voto;

public class ListCellProdutos extends BaseAdapter {

    List<Voto> produtos;
    private final Activity context;

    public ListCellProdutos(Activity context, List<Voto> listProdutos) {
        this.context = context;
        this.produtos = listProdutos;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position,final View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_cell_listas, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.id);
        TextView txtId = (TextView) rowView.findViewById(R.id.id);

        txtTitle.setText(produtos.get(position).getNome());
        txtId.setText(produtos.get(position).getId());

        return rowView;
    }

    public Filter getFilter() {

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                produtos = (List<Voto>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<Voto> FilteredArrayNames = new ArrayList<Voto>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < produtos.size(); i++) {
                    Voto dataNames = produtos.get(i);
                    if (dataNames.getNome().toLowerCase().contains(constraint.toString()))  {
                        FilteredArrayNames.add(dataNames);
                    }
                }

                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;

                return results;
            }
        };

        return filter;
    }
}