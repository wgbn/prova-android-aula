package br.com.wgbn.pm_20160328_trabalho_prova.componentes;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import java.util.HashMap;

public class WgbnMaskEditText extends EditText {
    private HashMap<String, String> tipos;
    private AttributeSet atributos;
    private WgbnMaskEditTextChangedListener mascaraListener;
    private String mascara;

    public WgbnMaskEditText(Context context) {
        super(context);
        init();
    }

    public WgbnMaskEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.atributos = attrs;
        init();
    }

    public WgbnMaskEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.atributos = attrs;
        init();
    }

    public WgbnMaskEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.atributos = attrs;
        init();
    }

    private void setMascara(String mascara) {
        this.mascara = mascara;
    }

    private void init(){
        carregaTipos();
        carregaMascara();
        mascaraListener = new WgbnMaskEditTextChangedListener(mascara, this);
        this.addTextChangedListener(mascaraListener);
    }

    private void carregaMascara(){
        String _mascara = "###.###.###-##";
        if (atributos.getAttributeCount() > 0){
            for (int i = 0; i < atributos.getAttributeCount(); i++){
                if (atributos.getAttributeName(i).equals("mascara")) {
                    _mascara = atributos.getAttributeValue(i).toString();
                    Log.v("wgbn", atributos.getAttributeValue(i).toString());
                }
                if (atributos.getAttributeName(i).equals("tipo")){
                    _mascara = tipos.get(atributos.getAttributeValue(i).toString());
                    Log.v("wgbn", tipos.get(atributos.getAttributeValue(i).toString()));
                }
            }
        }
        setMascara(_mascara);
    }

    private void carregaTipos(){
        tipos = new HashMap<>();
        tipos.put("telefone", "(##) ####-#####");
        tipos.put("cpf", "###.###.###-##");
        tipos.put("cnpj", "##.###.###/####-##");
        tipos.put("creditcard", "####-####-####-####");
        tipos.put("data", "##/##/####");
        tipos.put("hora", "##:##:##");
        tipos.put("datahora", "##/##/#### ##:##:##");
        tipos.put("cep", "##.###-###");
    }
}
