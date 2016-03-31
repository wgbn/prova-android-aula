package br.com.wgbn.pm_20160328_trabalho_prova.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.wgbn.pm_20160328_trabalho_prova.BuscarActivity;
import br.com.wgbn.pm_20160328_trabalho_prova.R;
import br.com.wgbn.pm_20160328_trabalho_prova.pojo.ClientePojo;

public class ClientesAdapter extends android.support.v7.widget.RecyclerView.Adapter<ClienteViewHolder> {

    private Context context;
    private ArrayList<ClientePojo> itens;
    private ClientePojo selecionado;

    public ClientesAdapter(Context context, ArrayList<ClientePojo> itens) {
        this.context = context;
        this.itens = itens;
    }

    @Override
    public ClienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cliente, parent, false);
        ClienteViewHolder clienteViewHolder = new ClienteViewHolder(view, context);
        return clienteViewHolder;
    }

    @Override
    public void onBindViewHolder(final ClienteViewHolder holder, final int position) {
        ClientePojo cliente = itens.get(position);
        holder.txtNomeCliente.setText(cliente.getNome());
        holder.txtCpfCliente.setText(String.valueOf(cliente.getCpf()));
        holder.txtTelefoneCliente.setText(String.valueOf(cliente.getTelefone()));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                selecionado = itens.get(position);
                Snackbar snack = Snackbar.make(v, context.getResources().getText(R.string.snackDeltext)+" "+selecionado.getNome()+"?", Snackbar.LENGTH_LONG)
                        .setAction(R.string.snackDel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ClienteCollection.getInstance().removeCliente(selecionado);
                                notifyDataSetChanged();
                            }
                        })
                        .setActionTextColor(Color.RED);
                snack.show();
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecionado = itens.get(position);
                Intent intent = new Intent(context, BuscarActivity.class);
                intent.putExtra("cliente", selecionado);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
