package netcommand.timo.de.netcommand;

import java.util.HashMap;
import java.util.Map;

public class ClientProvider {

    private static Map<String, ClientConnection> clients = new HashMap<>();

    private void ClientProvider() {
    }

    public static ClientConnection get(String ip) {
        if(!clients.containsKey(ip)) {
            clients.put(ip, new ClientConnection(ip));
        }
        return clients.get(ip);
    }
}
