package br.com.wgbn.pm_20160328_trabalho_prova;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.com.wgbn.pm_20160328_trabalho_prova.componentes.SobreDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);
    }

    public void cadastrarClick(View v) {
        Intent cadastro = new Intent(this, CadastroActivity.class);
        startActivityForResult(cadastro, 1);
    }

    public void buscarClick(View v){
        Intent buscar = new Intent(this, BuscarActivity.class);
        startActivityForResult(buscar, 2);
    }

    public void listarClick(View v){
        Intent listar = new Intent(this, ListarActivity.class);
        startActivityForResult(listar, 3);
    }

    public void sairClick(View v){
        finish();
    }

    public void gitClick(View v){
        Uri uri = Uri.parse("http://github.com/wgbn");
        Intent web = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(web);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btnAbout) {
            SobreDialog sobre = new SobreDialog();
            sobre.show(getFragmentManager(), "sobre");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}