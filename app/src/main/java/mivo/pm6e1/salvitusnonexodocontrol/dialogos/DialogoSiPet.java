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

public class DialogoSiPet {
    private DialogoSiPet.Final interfaz;
    public Button btnAceptar;

    public interface Final{
        void quieroagua(String x);
    }

    public DialogoSiPet(final Context context, final Final actividad){
        interfaz= actividad;
        final Dialog dialogo = new Dialog(context);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialogo.setCancelable(false);
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialogo.setContentView(R.layout.dialogosipet);

        btnAceptar= (Button) dialogo.findViewById(R.id.btnAceptar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actividad.quieroagua("1");
                dialogo.dismiss();
            }
        });

        dialogo.show();
    }
}
