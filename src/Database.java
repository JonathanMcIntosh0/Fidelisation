import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

class Database {

    private final List<Client> clientList = new ArrayList<>();

    private final TextField tfFirstName;
    private final TextField tfLastName;
    private final TextField[] tfSemaines;

    private final VBox displayContent;

    Database(TextField tfFirstName, TextField tfLastName, TextField[] tfSemaines, VBox displayContent) {
        this.tfFirstName = tfFirstName;
        this.tfLastName = tfLastName;
        this.tfSemaines = tfSemaines;
        this.displayContent = displayContent;
    }

    void addClient(){
        Name name = getName();
        String[] strMilles = getStrMilles();

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
        Name name = getName();

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

    void showClientMilles() {
        Name name = getName();

        if (checkValidName(name)) {
            Client client = getClient(name);
            if (client != null) {
                displayContent.getChildren().clear();
                displayContent.getChildren().add(new Text(client.getMilleTag()));
            } else {
                AlertBoxCreator.displayNotFound(name);
            }
        }
    }

    void showClients() {
        displayContent.getChildren().clear();
        for (Client client : clientList) displayContent.getChildren().add(new Text(client.getClientTag()));
    }

    private Name getName() {
        return new Name(tfFirstName.getText(), tfLastName.getText());
    }

    private String[] getStrMilles() {
        String[] strMiles = new String[4];
        for (int i = 0; i < 4; i++) strMiles[i] = tfSemaines[i].getText();
        return strMiles;
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
