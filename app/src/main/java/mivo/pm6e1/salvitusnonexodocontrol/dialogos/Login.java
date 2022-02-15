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

public class Login {

    private Final interfaz;
    public Button btnAceptar;
    public EditText etEmail, etContra;

    public interface Final{
        void resultsdo(String usuario, String contraseña, int opc);

    }

    public Login(final Context context, Final actividad, final int opc){
        interfaz= actividad;
        final Dialog dialogo = new Dialog(context);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialogo.setCancelable(false);
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogo.setContentView(R.layout.login);

        btnAceptar= (Button) dialogo.findViewById(R.id.btnAceptar);
        etContra= (EditText) dialogo.findViewById(R.id.etContra);
        etEmail= (EditText) dialogo.findViewById(R.id.etEmail);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    interfaz.resultsdo(etEmail.getText().toString(), etContra.getText().toString(),opc);
                    dialogo.dismiss();
            }
        });

        dialogo.show();
    }
}
