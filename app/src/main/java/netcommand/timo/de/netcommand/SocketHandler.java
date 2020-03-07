package netcommand.timo.de.netcommand;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

class SocketHandler {

    private static Socket socket;

    static Socket getSocket() {
        return SocketHandler.socket;
    }

    static void setSocket(Socket socket) {
        SocketHandler.socket = socket;
    }
}
