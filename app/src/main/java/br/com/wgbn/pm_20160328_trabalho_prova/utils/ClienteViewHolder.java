package br.com.wgbn.pm_20160328_trabalho_prova.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.wgbn.pm_20160328_trabalho_prova.R;

public class ClienteViewHolder extends RecyclerView.ViewHolder {

    private Context context;

    public TextView txtNomeCliente;
    public TextView txtCpfCliente;
    public TextView txtTelefoneCliente;

    public ClienteViewHolder(View itemView, final Context context) {
        super(itemView);
        this.context = context;
        txtNomeCliente = (TextView) itemView.findViewById(R.id.txtNomeCliente);
        txtCpfCliente = (TextView) itemView.findViewById(R.id.txtCpfCliente);
        txtTelefoneCliente = (TextView) itemView.findViewById(R.id.txtTelefoneCliente);
    }
}