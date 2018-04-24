import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

class Database {

    private final List<Client> clientList = new ArrayList<>();

    Controller controller;

    Database(Controller controller) {
        this.controller = controller;
    }

    void showClientMilles() {
        Name name = controller.getName();

        if (checkValidName(name)) {
            Client client = getClient(name);
            if (client != null) {
                controller.clearDisplay();
                controller.addToDisplay(new Text(client.getMilleTag()));
            } else {
                AlertBoxCreator.displayNotFound(name);
            }
        }
    }

    void addClient(){
        Name name = controller.getName();
        String[] strMilles = controller.getStrMilles();

        if (checkValidName(name)) {
            Client client = getClient(name);
            if (client != null) {
                AlertBoxCreator.displayAlreadyExists(name);
                return;
            }

            if (checkValidSemaine(strMilles)) {
                int[] milles = new int[4];
                for (int i = 0; i < 4; i++) milles[i] = Integer.parseInt(strMilles[i]);

                Client newClient = new Client(name, milles);
                clientList.add(newClient);
                AlertBoxCreator.displayAdd(name);

                showClients();
            }
        }
    }

    void delClient() {
        Name name = controller.getName();

        if (checkValidName(name)) {

            Client client = getClient(name);

            if (client == null) {
                AlertBoxCreator.displayNotFound(name);
                return;
            }

            clientList.remove(client);
            AlertBoxCreator.displayDel(name);

            showClients();
        }
    }

    void showClients() {
        controller.clearDisplay();
        for (Client client : clientList) controller.addToDisplay(new Text(client.getClientTag()));
    }

    private Client getClient(Name name) {
        for (Client client : clientList) {
            if (client.getName().equals(name)) {
                return client;
            }
        }
        return null;
    }

    private boolean checkValidName(Name name) {
        if (name.firstName.isEmpty() || name.firstName.contains(" ") || name.lastName.isEmpty() || name.lastName.contains(" ")) {
            AlertBoxCreator.displayInvalidName();
            return false;
        }
        return true;
    }

    private boolean checkValidSemaine(String[] strMilles) {
        boolean valide = true;
        for (int i = 0; i < 4; i++) {
            int n = 0;

            try {
                n = Integer.parseInt(strMilles[i]);
            } catch (NumberFormatException e) {
                valide = false;
            }
            if (n < 0) valide = false;

            if (!valide) {
                AlertBoxCreator.displayInvalidMilles(i + 1);
                return false;
            }
        }
        return true;
    }

    void addDefaultClients() {
        clientList.add(new Client(new Name("Jaime", "Voyager"), new int[]{330, 1700, 1500, 220}));
        clientList.add(new Client(new Name("Paul", "Avion"), new int[]{750, 1700, 1500, 800}));
    }
}
