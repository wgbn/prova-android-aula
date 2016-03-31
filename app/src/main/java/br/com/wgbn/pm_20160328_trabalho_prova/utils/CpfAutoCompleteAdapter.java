package br.com.wgbn.pm_20160328_trabalho_prova.utils;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

public class CpfAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {

    private List<String> listaCompleta;
    private List<String> resultados;
    private Filter meuFiltro;

    public CpfAutoCompleteAdapter(Context context, int layout, List<String> cpfs) {
        super(context, layout, cpfs);
        this.listaCompleta = cpfs;
        this.resultados = listaCompleta;
        this.meuFiltro = new MeuFiltro();
    }

    @Override
    public int getCount() {
        return resultados.size();
    }

    @Override
    public String getItem(int position) {
        if (resultados != null && resultados.size() > 0 && position < resultados.size()) {
            return resultados.get(position);
        } else {
            return null;
        }
    }

    @Override
    public Filter getFilter() {
        return meuFiltro;
    }

    private class MeuFiltro extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();

            ArrayList<String> temp = new ArrayList<String>();

            if (constraint != null) {
                String term = Utils.removeAcentos(constraint.toString().trim().toLowerCase());

                String placeStr;
                for (String p : listaCompleta) {
                    placeStr = Utils.removeAcentos(p.toLowerCase());

                    if (placeStr.indexOf(term) > -1) {
                        temp.add(p);
                    }
                }
            }
            filterResults.values = temp;
            filterResults.count = temp.size();
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            resultados = (ArrayList<String>) filterResults.values;
            notifyDataSetChanged();
        }
    }
}
