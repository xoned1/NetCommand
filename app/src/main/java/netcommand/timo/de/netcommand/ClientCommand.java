package netcommand.timo.de.netcommand;

import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientCommand extends AsyncTask<String, Void, Boolean> {

    private Socket socket = SocketHandler.getSocket();
    ;

    ClientCommand() {
    }

    @Override
    protected Boolean doInBackground(String... params) {

        String command = params[0];
        try {
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(os);
            BufferedWriter bw  = new BufferedWriter(writer);

            bw.write(command + "\n");
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}