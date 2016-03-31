package br.com.wgbn.pm_20160328_trabalho_prova;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import br.com.wgbn.pm_20160328_trabalho_prova.componentes.WgbnMaskEditText;
import br.com.wgbn.pm_20160328_trabalho_prova.pojo.ClientePojo;
import br.com.wgbn.pm_20160328_trabalho_prova.utils.ClienteCollection;

public class CadastroActivity extends AppCompatActivity {

    private EditText txtNome;
    private WgbnMaskEditText txtCpf;
    private WgbnMaskEditText txtTelefone;
    private EditText txtEndereco;
    private RadioGroup rdSexo;
    private Switch onoffAtivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_cadastro);

        txtNome = (EditText)findViewById(R.id.txtNome);
        txtCpf = (WgbnMaskEditText)findViewById(R.id.txtCpf);
        txtTelefone = (WgbnMaskEditText)findViewById(R.id.txtTelefone);
        txtEndereco = (EditText)findViewById(R.id.txtEndereco);
        rdSexo = (RadioGroup)findViewById(R.id.rdSexo);
        onoffAtivo = (Switch)findViewById(R.id.onoffAtivo);

        getSupportActionBar().setTitle(R.string.tituloCadastrar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private boolean validarCampos(){
        if (!txtNome.getText().toString().isEmpty() &&
                !txtCpf.getText().toString().isEmpty() &&
                !txtTelefone.getText().toString().isEmpty() &&
                !txtEndereco.getText().toString().isEmpty() &&
                rdSexo.getCheckedRadioButtonId() > -1
                )
            return true;
        return false;
    }

    private void salvarCliente(){
        if (validarCampos()){
            char sexo = getResources().getResourceEntryName(rdSexo.getCheckedRadioButtonId()).equals("rdSexoM") ? 'M':'F';
            ClientePojo cliente = new ClientePojo(onoffAtivo.isChecked(),
                    txtCpf.getText().toString(),
                    txtEndereco.getText().toString(),
                    txtNome.getText().toString(),
                    sexo,
                    txtTelefone.getText().toString());

            if (ClienteCollection.getInstance().adicionaCliente(cliente)) {
                Toast.makeText(this, "Cliente cadastrado com sucesso.", Toast.LENGTH_LONG).show();
                voltaMain();
            } else
                Toast.makeText(this, "Houve um erro ao salvar o cliente.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
        }
    }

    private void voltaMain(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        setResult(1, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastro_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btnSalvar) {
            salvarCliente();
            return true;
        } else if (id == android.R.id.home){
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
