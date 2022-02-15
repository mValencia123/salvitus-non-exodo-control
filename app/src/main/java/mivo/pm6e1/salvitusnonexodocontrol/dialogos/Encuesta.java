package mivo.pm6e1.salvitusnonexodocontrol.dialogos;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import mivo.pm6e1.salvitusnonexodocontrol.R;

public class Encuesta {
    private RatingBar estrella1, estrella2, estrella3, estrella4, estrella5;
    private Encuesta.Final interfaz;
    public Button btnAceptar;
    public EditText etRegistro;

    public interface Final{
        void encuesta(float estrella1, float estrella2, float estrella3, float estrella4, float estrella5);
    }

    public Encuesta(final Context context, Final actividad){
        interfaz= actividad;
        final Dialog dialogo = new Dialog(context);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialogo.setCancelable(false);
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialogo.setContentView(R.layout.dialogo_encuesta);

        btnAceptar= (Button) dialogo.findViewById(R.id.btnEnviar);
        estrella1= dialogo.findViewById(R.id.estrella1);
        estrella2= dialogo.findViewById(R.id.estrella2);
        estrella3= dialogo.findViewById(R.id.estrella3);
        estrella4= dialogo.findViewById(R.id.estrella4);
        estrella5= dialogo.findViewById(R.id.estrella5);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               interfaz.encuesta(estrella1.getRating(),estrella2.getRating(),estrella3.getRating(),estrella4.getRating(),estrella5.getRating());
               dialogo.dismiss();
            }
        });

        dialogo.show();
    }
}
