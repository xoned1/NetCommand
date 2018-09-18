package netcommand.timo.de.netcommand;

import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientConnect extends AsyncTask<String, Void, Boolean> {



    @Override
    protected Boolean doInBackground(String... params) {

        String ip = params[0];
        try (Socket socket = new Socket(ip, 1337)) {

            OutputStream os = socket.getOutputStream();

            OutputStreamWriter writer = new OutputStreamWriter(os);
            BufferedWriter br = new BufferedWriter(writer);

            writer.write("rc");
            writer.write("exit");
            writer.close();

            socket.close();


        } catch (IOException e) {
            e.printStackTrace();

        }
        return true;
    }
}