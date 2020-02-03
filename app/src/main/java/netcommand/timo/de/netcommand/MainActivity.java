package netcommand.timo.de.netcommand;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>());
        BroadcastDiscovery serviceDiscovery = new BroadcastDiscovery("NetRobot", adapter, this);

        Thread broadcastTread = new Thread((serviceDiscovery));
        broadcastTread.start();


        TextView emptyListText =  (TextView)findViewById(R.id.empty);
        ListView listViewHosts = (ListView)findViewById(R.id.listViewHosts);
        listViewHosts.setAdapter(adapter);
        listViewHosts.setEmptyView(emptyListText);

        adapter.add("192.168.0.1 / xoned");
        adapter.add("192.168.0.2 / bla");

        listViewHosts.setOnItemClickListener((adapterView, view, i, l) -> {

                String host = adapterView.getItemAtPosition(i).toString();
                String ip = host.split("/")[0].trim();
                Intent intent = new Intent(MainActivity.this, CommandActivity.class);
                intent.putExtra("ip", ip);
                startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
