package br.com.wgbn.pm_20160328_trabalho_prova;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import br.com.wgbn.pm_20160328_trabalho_prova.componentes.DividerItemDecoration;
import br.com.wgbn.pm_20160328_trabalho_prova.utils.ClienteCollection;
import br.com.wgbn.pm_20160328_trabalho_prova.utils.ClientesAdapter;

public class ListarActivity extends AppCompatActivity {

    private RecyclerView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_listar);

        getSupportActionBar().setTitle(R.string.tituloListar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lista = (RecyclerView)findViewById(R.id.lstClientes);
        lista.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.abc_list_divider_mtrl_alpha)));
        lista.setItemAnimator(new DefaultItemAnimator());
        lista.setLayoutManager(new LinearLayoutManager(this));

        carregaClientes();
    }

    private void voltaMain(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        setResult(3, intent);
        finish();
    }

    private void carregaClientes(){
        lista.setAdapter(new ClientesAdapter(this, ClienteCollection.getInstance().getClientes()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            voltaMain();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        voltaMain();
    }
}
