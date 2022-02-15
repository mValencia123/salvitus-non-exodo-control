package mivo.pm6e1.salvitusnonexodocontrol.dialogos;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import mivo.pm6e1.salvitusnonexodocontrol.R;

public class Ayuda {

    private final Button btnSiguiente;
    private final Button btnAtras;
    private final ImageView imagen;
    private final TextView tvMensaje;
    private final ImageView vector[]= new ImageView[4];
    private int i;

    public Ayuda(final Context context){

        final Dialog dialogo = new Dialog(context);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialogo.setContentView(R.layout.ayuda);

        btnSiguiente= (Button) dialogo.findViewById(R.id.btnSig);
        btnAtras= (Button) dialogo.findViewById(R.id.btnAtras);
        imagen= dialogo.findViewById(R.id.imgen);
        tvMensaje= dialogo.findViewById(R.id.tvMensaje);
        i=1;
        imagen.setBackgroundResource(R.drawable.botellaayuda);
        tvMensaje.setText(R.string.botellaAyuda);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=i+1;
                if(i>=1&&i<=7){
                     switch (i) {
                        case 2:
                            imagen.setBackgroundResource(R.drawable.depositaayuda);
                            tvMensaje.setText(R.string.depositaAyuda);
                        break;
                        case 3:
                            imagen.setBackgroundResource(R.drawable.encuesta);
                            tvMensaje.setText(R.string.Ayuda3);
                        break;
                         case 4:
                             imagen.setBackgroundResource(R.drawable.dyr);
                             tvMensaje.setText(R.string.Ayuda1);
                             break;
                         case 5:
                             imagen.setBackgroundResource(R.drawable.rec);
                             tvMensaje.setText(R.string.Ayuda4);
                             break;
                         case 6:
                             imagen.setBackgroundResource(R.drawable.don);
                             tvMensaje.setText(R.string.Ayuda2);
                             break;
                         case 7:
                             imagen.setBackgroundResource(R.drawable.administrador);
                             tvMensaje.setText(R.string.Admin);
                             break;
                     }
                }else if(i==8){
                    dialogo.dismiss();
                }
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=i-1;
                if(i>=1&&i<=7){
                    switch (i) {
                        case 1:
                            imagen.setBackgroundResource(R.drawable.botellaayuda);
                            tvMensaje.setText(R.string.botellaAyuda);
                            break;
                        case 2:
                            imagen.setBackgroundResource(R.drawable.depositaayuda);
                            tvMensaje.setText(R.string.depositaAyuda);
                            break;
                        case 3:
                            imagen.setBackgroundResource(R.drawable.encuesta);
                            tvMensaje.setText(R.string.Ayuda2);
                            break;
                        case 4:
                            imagen.setBackgroundResource(R.drawable.dyr);
                            tvMensaje.setText(R.string.Ayuda1);
                            break;
                        case 5:
                            imagen.setBackgroundResource(R.drawable.rec);
                            tvMensaje.setText(R.string.Ayuda4);
                            break;
                        case 6:
                            imagen.setBackgroundResource(R.drawable.don);
                            tvMensaje.setText(R.string.Ayuda2);
                            break;
                        case 7:
                            imagen.setBackgroundResource(R.drawable.administrador);
                            tvMensaje.setText(R.string.Admin);
                            break;
                    }
                }else if(i==0){
                    dialogo.dismiss();
                }
            }
        });

        dialogo.show();
    }
}
