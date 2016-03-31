package br.com.wgbn.pm_20160328_trabalho_prova;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.wgbn.pm_20160328_trabalho_prova.pojo.ClientePojo;
import br.com.wgbn.pm_20160328_trabalho_prova.utils.ClienteCollection;
import br.com.wgbn.pm_20160328_trabalho_prova.utils.CpfAutoCompleteAdapter;

public class BuscarActivity extends AppCompatActivity {

    private ArrayList<ClientePojo> clientes;
    private ArrayList<String> cpfs;
    private AutoCompleteTextView busca;
    private ClientePojo selecionado;

    private TextView lblNome;
    private TextView lblTelefone;
    private TextView lblCpf;
    private TextView lblEndereco;
    private TextView lblSexo;
    private TextView lblAtivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_buscar);

        getSupportActionBar().setTitle(R.string.tituloBuscar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        carregaViews();

        busca.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                selecionado = ClienteCollection.getInstance().getByCpf(busca.getText().toString());
                if (selecionado != null)
                    carregaCliente();
            }
        });

        getClienteDaLista();

        carregaListas();
    }

    private void voltaMain(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        setResult(2, intent);
        finish();
    }

    private void carregaViews(){
        lblNome = (TextView)findViewById(R.id.lblNome);
        lblTelefone = (TextView)findViewById(R.id.lblTelefone);
        lblCpf = (TextView)findViewById(R.id.lblCpf);
        lblEndereco = (TextView)findViewById(R.id.lblEndereco);
        lblSexo = (TextView)findViewById(R.id.lblSexo);
        lblAtivo = (TextView)findViewById(R.id.lblAtivo);
        busca = (AutoCompleteTextView) findViewById(R.id.txtBuscarCpf);
    }

    private void carregaCliente(){
        lblNome.setText(selecionado.getNome());
        lblTelefone.setText(selecionado.getTelefone());
        lblCpf.setText(selecionado.getCpf());
        lblEndereco.setText(selecionado.getEndereco());
        lblSexo.setText(selecionado.getSexo() == 'M' ? "Masculino":"Feminino");
        lblAtivo.setText(selecionado.isAtivo() ? "Cliente Ativo":"Cliente Inativo");
    }

    private void carregaListas(){
        cpfs = new ArrayList<String>();
        clientes = ClienteCollection.getInstance().getClientes();

        for (ClientePojo c : clientes)
            cpfs.add(c.getCpf());

        CpfAutoCompleteAdapter adapter = new CpfAutoCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line, cpfs);
        busca.setAdapter(adapter);
    }

    private void getClienteDaLista(){
        Intent intent = getIntent();

        selecionado = intent.getParcelableExtra("cliente");

        if (selecionado != null) {
            carregaCliente();
            busca.setText(selecionado.getCpf());
        }
    }

    public void telefoneClick(View v){
        Uri uri = Uri.parse("tel:" + selecionado.getTelefone().toString().replaceAll("[^0-9]+",""));
        Intent ligar = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(ligar);
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        if (selecionado != null)
            savedInstanceState.putParcelable("cliente", selecionado);

        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        selecionado = savedInstanceState.getParcelable("cliente");
        if (selecionado != null)
            carregaCliente();
    }
}
