package mivo.pm6e1.salvitusnonexodocontrol.dialogos;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mivo.pm6e1.salvitusnonexodocontrol.R;

public class DialogoAdministrador {
    private DialogoAdministrador.Final interfaz;
    public Button btnFin, btnCalibrar;

    public interface Final{
        void finDeDia(int i);
    }

    public DialogoAdministrador(final Context context, DialogoAdministrador.Final actividad){
        interfaz= actividad;
        final Dialog dialogo = new Dialog(context);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(true);
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialogo.setContentView(R.layout.dialogo_admin);

        btnFin= (Button) dialogo.findViewById(R.id.btnFin);
        btnCalibrar= (Button) dialogo.findViewById(R.id.btnCalibrar);

        btnFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int i=1;
                interfaz.finDeDia(i);
            }
        });

        btnCalibrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=2;
                interfaz.finDeDia(i);
            }
        });

        dialogo.show();
    }
}
