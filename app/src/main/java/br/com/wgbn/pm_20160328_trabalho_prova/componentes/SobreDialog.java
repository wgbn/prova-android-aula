package br.com.wgbn.pm_20160328_trabalho_prova.componentes;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import br.com.wgbn.pm_20160328_trabalho_prova.R;

public class SobreDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle bundle){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.layout_dialog, null));
                /*.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SobreDialog.this.getDialog().cancel();
                    }
                });*/
        return builder.create();
    }

}