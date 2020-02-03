package netcommand.timo.de.netcommand;

import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientConnection extends AsyncTask<String, Void, Boolean> {

    private String ip;
    private Socket socket;

    public ClientConnection(String ip) {
        this.ip = ip;
    }

    @Override
    protected Boolean doInBackground(String... params) {

        String command = params[0];
        try {
            if (socket == null || socket.isClosed()) {

                socket = new Socket(ip, 1337);
            }

            OutputStream os = socket.getOutputStream();

            OutputStreamWriter writer = new OutputStreamWriter(os);
            BufferedWriter br = new BufferedWriter(writer);

            writer.write(command + "\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}