package mivo.pm6e1.salvitusnonexodocontrol.Activitys;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import mivo.pm6e1.salvitusnonexodocontrol.R;
import mivo.pm6e1.salvitusnonexodocontrol.Referencias.Referencias;
import mivo.pm6e1.salvitusnonexodocontrol.SetAndGet.Donacion;
import mivo.pm6e1.salvitusnonexodocontrol.SetAndGet.Envio;
import mivo.pm6e1.salvitusnonexodocontrol.dialogos.Ayuda;
import mivo.pm6e1.salvitusnonexodocontrol.dialogos.DialogoAdministrador;
import mivo.pm6e1.salvitusnonexodocontrol.dialogos.DialogoDonar;
import mivo.pm6e1.salvitusnonexodocontrol.dialogos.DialogoSiPet;
import mivo.pm6e1.salvitusnonexodocontrol.dialogos.Encuesta;
import mivo.pm6e1.salvitusnonexodocontrol.dialogos.Login;

public class MainActivity extends AppCompatActivity implements Login.Final, DialogoDonar.Final, Encuesta.Final, DialogoSiPet.Final, DialogoAdministrador.Final{

    private DatabaseReference SalvitusmyRef;
    private DatabaseReference SalvitusmyRef2;
    private DatabaseReference SalvitusmyRef3;
    private FirebaseAuth mAuth;
    Context context = this;
    Button btnDonRe, btnAyuda, btnDonar;
    private ProgressDialog progressDialog;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothSocket btSocket;
    private boolean btConnected=true;
    private Handler handler;
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private int i=0, m=0, cantidadDePet=0, cantidadDeAgua=0, cantidadDeUsuario=0;
    private ConnectedThread mConnectedThread;
    private ProgressDialog progressDialog1;
    private FirebaseDatabase database;
    private String datos;
    private Bundle parametros=new Bundle();
    private Dialog dialogoDepositaPet;
    private DatabaseReference SalvitusmyRef4;
    private DatabaseReference SalvitusmyRef5;
    private DatabaseReference Agua;
    private DatabaseReference Pet;
    private boolean ban=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAyuda= findViewById(R.id.btnAyuda);
        btnDonRe= findViewById(R.id.btnDonRe);
        btnDonar= findViewById(R.id.btnDonar);

        final Dialog dialogo = new Dialog(context);
        dialogo.setCancelable(false);
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialogo.setContentView(R.layout.dialogosinservicio);

        final Dialog dialogo2 = new Dialog(context);
        dialogo2.setCancelable(false);
        dialogo2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialogo2.setContentView(R.layout.dialogosinservicio);

        final Dialog dialogoNoPet = new Dialog(context);
        dialogoNoPet.setCancelable(true);
        dialogoNoPet.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialogoNoPet.setContentView(R.layout.dialogonopet);
        dialogoDepositaPet = new Dialog(context);
        dialogoDepositaPet.setCancelable(true);
        dialogoDepositaPet.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialogoDepositaPet.setContentView(R.layout.dialogodepositapet);
        parametros = this.getIntent().getExtras();
        datos = parametros.getString("datos");
        progressDialog= new ProgressDialog(this);
        progressDialog1= new ProgressDialog(this);
        final Context context= this;
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        SalvitusmyRef = database.getReference(Referencias.proyecto);
        SalvitusmyRef2 = database.getReference(Referencias.donacion);
        SalvitusmyRef3= database.getReference(Referencias.encuesta);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
         Agua = database.getReference("Estado");

        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
         Pet = database2.getReference("Estado");
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        final int p=0, a=0, p2=1, a2=1;
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == 0) {
                    String readMessage = (String) msg.obj;
                    //Toast.makeText(getApplicationContext(), ""+readMessage, Toast.LENGTH_LONG).show();

                    switch (readMessage){
                        case "0":
                            Agua.child("Agua").setValue(a2);
                            dialogo2.show();
                            break;
                        case "1":
                            Pet.child("Pet").setValue(p2);
                            dialogo.show();
                            break;
                        case "2":
                            Toast.makeText(getApplicationContext(), "Es Pet.", Toast.LENGTH_LONG).show();
                            cantidadDeUsuario=cantidadDeUsuario+1;
                            cantidadDeAgua=cantidadDeAgua+1;
                            cantidadDePet=cantidadDePet+1;
                            new DialogoSiPet(context,MainActivity.this);
                            dialogo.dismiss();
                            break;
                        case "3":
                             Toast.makeText(getApplicationContext(), "No es PET", Toast.LENGTH_LONG).show();

                             dialogoNoPet.show();
                            break;
                        case "4":
                             Toast.makeText(getApplicationContext(), "Es PET, y puedes recibir agua", Toast.LENGTH_LONG).show();
                             cantidadDeUsuario=cantidadDeUsuario+1;
                             cantidadDeAgua=cantidadDeAgua+1;
                             new DialogoSiPet(context,MainActivity.this);
                            break;
                        case "5":
                            Toast.makeText(getApplicationContext(), "Es Pet, pero solo estas donando.", Toast.LENGTH_LONG).show();
                            cantidadDeUsuario=cantidadDeUsuario+1;
                            cantidadDePet=cantidadDePet+1;
                            new DialogoDonar(context, MainActivity.this, 1);
                            break;
                        case "6":
                            Agua.child("Agua").setValue(a);
                            dialogo2.dismiss();
                            break;
                        case "7":
                            Pet.child("Pet").setValue(p);
                            dialogo.dismiss();
                            break;
                    }

                }
                return true;
            }
        });
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(datos);
        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_LONG).show();
        }
        // Establish the Bluetooth socket connection.
        try
        {
            btSocket.connect();
        } catch (IOException e) {
            try
            {
                btSocket.close();
            } catch (IOException e2)
            {
                //insert code to deal with this
            }
        }
        mConnectedThread = new ConnectedThread(btSocket);
        mConnectedThread.start();
    }

    private void cambiar2() {
        SalvitusmyRef4.setValue(1);
    }

    private void cambiar() {
        SalvitusmyRef5.setValue(1);
    }

    @Override
    public void finDeDia(int i) {
        if(i==1){
            new Login(context, MainActivity.this,1);
        }else if(i==2){
            new Login(context, MainActivity.this,2);
        }
    }

    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        //creation of the connect thread
        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                //Create I/O streams for connection
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }


        public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            // Keep looping to listen for received messages
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);        	//read bytes from input buffer
                    String readMessage = new String(buffer, 0, bytes);
                    // Send the obtained bytes to the UI Activity via handler
                    handler.obtainMessage(0, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }
        //write method
        public void write(String input) {
            byte[] msgBuffer = input.getBytes();           //converts entered String into bytes
            try {
                mmOutStream.write(msgBuffer);                //write bytes over BT connection via outstream
            } catch (IOException e) {
                //if you cannot write, close the application
                Toast.makeText(getBaseContext(), "Connection Failure", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        return  device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }

    public void Envio(View v) {
        new DialogoAdministrador(context, MainActivity.this);
    }

    public void DonarRecibir(View v){
        mConnectedThread.write("0");
        dialogoDepositaPet.show();
        Toast.makeText(MainActivity.this, "Tu petición ha sido enviada con éxito.", Toast.LENGTH_SHORT).show();
    }

    public void DonarPET(View v) {
        mConnectedThread.write("1");
        dialogoDepositaPet.show();
        Toast.makeText(MainActivity.this, "Tu petición ha sido enviada con éxito.", Toast.LENGTH_SHORT).show();
    }

    public void ayuda(View v){
        new Ayuda(this);
    }

    public void recibir(View v){
        new DialogoDonar(context, MainActivity.this, 2);
        //Pet.child("Pet").setValue(1);
        //Agua.child("Agua").setValue(1);
    }

    public void Encuesta(View v){
        new Encuesta(context,MainActivity.this);
    }

    //Este metodo es dado por la interfaz entre el dialogo de donar y recibir
    @Override
    public void donacion(String registro, int opc) {
        if(opc==1) {
            //Primero hay que pedir el pet, y ya dependiendo de ahí se abre el cuadro de dialogo, así que aqui se manda un 1
            //y se evalua si es PET, si lo es te regresa un caracter de control y en base a eso se abre el dialogo de abajo.
            progressDialog.setMessage("Guardando registro...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            Donacion donacion= new Donacion(registro);
            SalvitusmyRef2.push().setValue(donacion);
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, "Tu registro se ha guardado exitosamente", Toast.LENGTH_LONG).show();
        }else{
           Query q = SalvitusmyRef2.orderByChild(Referencias.Registros).equalTo(registro);
           q.addListenerForSingleValueEvent(new ValueEventListener() {
               int cont=0;
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   boolean bandera=true;
                   for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                       cont++;
                       Log.e("Valor de la ref", ""+dataSnapshot1.getRef());
                       if(bandera){
                           dataSnapshot1.getRef().removeValue();
                           Log.e("Valor de la ref", ""+dataSnapshot1.getRef());
                           bandera=false;
                       }
                   }
                   if(cont>=1) {
                       Toast.makeText(MainActivity.this, "Tienes "+cont+" botellas", Toast.LENGTH_LONG).show();
                       //dataSnapshot1.getRef().removeValue();//Creo que aqui se elimina la ultima botella, esta por checar eso
                      new DialogoSiPet(context,MainActivity.this);
                   }
                   else Toast.makeText(MainActivity.this, "No tienes botellas", Toast.LENGTH_LONG).show();
               }

               @Override
               public void onCancelled(DatabaseError databaseError) {

               }
           });
        }
    }
    //Este metodo hace referencia a la encuesta.
    @Override
    public void encuesta(float estrella1, float estrella2, float estrella3, float estrella4, float estrella5) {
        progressDialog1.setMessage("Envando los datos...");
        progressDialog1.setCancelable(false);
        progressDialog1.show();
        mivo.pm6e1.salvitusnonexodocontrol.SetAndGet.Encuesta encuesta=
                new mivo.pm6e1.salvitusnonexodocontrol.SetAndGet.Encuesta(estrella1,estrella2,estrella3,estrella4,estrella5);
        SalvitusmyRef3.child(Referencias.Referencia).push().setValue(encuesta);
        progressDialog1.dismiss();
        Toast.makeText(getApplicationContext(), "Se ha enviado la encuesta", Toast.LENGTH_LONG).show();
    }
    //Es el metodo encargado del dialogo de recepcion de agua.
    @Override
    public void quieroagua(String x) {
            Log.e("quieroagua","Llego y ahora envia un 2");
            mConnectedThread.write("2");
            Toast.makeText(MainActivity.this, "Tu petición ha sido enviada con éxito.", Toast.LENGTH_SHORT).show();
    }
    //Este es el metodo del login, envia los datos del dia.
    @Override
    public void resultsdo(String usuario, String contraseña, final int opc) {
        progressDialog1.setMessage("Envando los datos...");
        progressDialog1.setCancelable(false);
        progressDialog1.show();
        mAuth.signInWithEmailAndPassword(usuario, contraseña)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Autenticación exitosa", Toast.LENGTH_SHORT).show();
                            if(opc==1){
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                Date date = new Date();
                                String fecha = dateFormat.format(date);
                                Envio envio = new Envio(fecha,  cantidadDeAgua, cantidadDePet, cantidadDeUsuario);
                                SalvitusmyRef.push().setValue(envio);
                                progressDialog1.dismiss();
                                Toast.makeText(getApplicationContext(), "Se enviaron los datos exitosamente.", Toast.LENGTH_LONG).show();
                            }else if(opc==2){
                                mConnectedThread.write("3");
                                progressDialog1.dismiss();
                                Toast.makeText(getApplicationContext(), "El dato se envió con éxito.", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Tus datos no se encontraron en el sistema, verifica que los datos" +
                                            "sean correctos o el usuario este dado de alta",
                                    Toast.LENGTH_SHORT).show();
                            progressDialog1.dismiss();
                        }
                    }
                });
    }

}