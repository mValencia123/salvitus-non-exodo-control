package mivo.pm6e1.salvitusnonexodocontrol.Activitys;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import mivo.pm6e1.salvitusnonexodocontrol.Activitys.MainActivity;
import mivo.pm6e1.salvitusnonexodocontrol.R;

public class ConexionBluetooth extends AppCompatActivity {

    private ArrayAdapter<String> mArrayAdapter;
    private BluetoothAdapter mBluetoothAdapter;
    private ArrayList<BluetoothDevice> btDeviceArray = new ArrayList<BluetoothDevice>();
    private String[] listaNombres= new String[1000];
    private ListView lista;
    private int i;
    private Context context=this;
    private Bundle parmetros=new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion_bluetooth);

        lista = (ListView)findViewById(R.id.lista);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        lista = (ListView)findViewById(R.id.lista);
        mArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        lista.setAdapter(mArrayAdapter);
        Set<BluetoothDevice> pariedDevices = mBluetoothAdapter.getBondedDevices();
        if (pariedDevices.size() > 0) {
            for (BluetoothDevice device : pariedDevices) {
                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                btDeviceArray.add(device);
                listaNombres[i]= device.getAddress();
                i++;
            }
        }

        if(mBluetoothAdapter == null){
            Toast.makeText(getApplicationContext(), "Bluetooth no soportado", Toast.LENGTH_SHORT).show();
            //finish();
        }

        // Check Bluetooth enabled
        if(!mBluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "" + listaNombres[position], Toast.LENGTH_LONG).show();

                parmetros.putString("datos", listaNombres[position]);
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtras(parmetros);
                context.startActivity(intent);
            }
        });
    
    }
}
