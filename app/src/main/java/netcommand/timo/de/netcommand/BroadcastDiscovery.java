package netcommand.timo.de.netcommand;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class BroadcastDiscovery implements Runnable {

    private static DatagramSocket socket = null;
    ArrayAdapter<String> adapter;
    private String message;
    Activity context;

    public BroadcastDiscovery(String message, ArrayAdapter<String> adapter, Activity context) {
        this.message = message;
        this.adapter = adapter;
        this.context = context;
    }

    private void broadcast(String broadcastMessage) throws IOException {

        DatagramSocket socket = new DatagramSocket(8888, InetAddress.getByName("0.0.0.0"));
        socket.setBroadcast(true);

        while (true) {
            byte[] buffer = new byte[message.getBytes().length];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String receivedMsg = new String(packet.getData());
            if (receivedMsg.equals(message)) {
                String serviceIP = packet.getAddress().getHostAddress();
                String hostname = packet.getAddress().getHostName();
                String device = serviceIP + "/" + hostname;

                context.runOnUiThread(() -> {
                    boolean missing = true;
                    for(int i = 0; i < adapter.getCount(); i++) {
                        if(adapter.getItem(i).equals(device))
                            missing = false;
                    }
                    if(missing) {
                        adapter.add(device);
                    }
                    //TODO auch wieder entfernen, wenn lange kein broadcast kam..
                });
            }
        }
    }


    @Override
    public void run() {
        try {
            broadcast(this.message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
