package netcommand.timo.de.netcommand;

import android.os.AsyncTask;

import java.net.Socket;

public class SetSocketTask extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... strings) {
        try {
            Socket socket = new Socket(strings[0], 1337);
            SocketHandler.setSocket(socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
