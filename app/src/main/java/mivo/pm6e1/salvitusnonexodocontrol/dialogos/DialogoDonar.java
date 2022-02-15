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

public class DialogoDonar {

    private DialogoDonar.Final interfaz;
    public Button btnAceptar;
    public EditText etRegistro;

    public interface Final{
        void donacion(String registro, int opc);
    }

    public DialogoDonar(final Context context, Final actividad, final int opc){
        interfaz= actividad;
        final Dialog dialogo = new Dialog(context);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialogo.setCancelable(false);
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogo.setContentView(R.layout.dialogodonar);

        btnAceptar= (Button) dialogo.findViewById(R.id.btnAceptar);
        etRegistro= (EditText) dialogo.findViewById(R.id.etRegistro);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etRegistro.getText().toString().equals("")||etRegistro.getText().toString().equals(null)) {
                    Toast.makeText(v.getContext(),"El campo de texto esta vacio, por favor escribe tu registro",Toast.LENGTH_LONG).show();
                }else {
                    if(opc==1){
                        interfaz.donacion(etRegistro.getText().toString(), opc);
                        dialogo.dismiss();
                    }else{
                        interfaz.donacion(etRegistro.getText().toString(), opc);
                        dialogo.dismiss();
                    }
                }
            }
        });

        dialogo.show();
    }
}
