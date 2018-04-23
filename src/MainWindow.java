import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainWindow extends Application {

    private final TextField tfFirstName = new TextField();
    private final TextField tfLastName = new TextField();
    private String firstName;
    private String lastName;

    private final TextField[] tfSemaines = new TextField[4];
    private String[] txtSemaines = new String[4];

    private final List<Client> clientList = new ArrayList<>();

    private final VBox displayContent = new VBox(5);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        clientList.add(new Client("Jaime", "Voyager", new int[]{330, 1700, 1500, 220}));
        clientList.add(new Client("Paul", "Avion", new int[]{750, 1700, 1500, 800}));

        primaryStage.setTitle("Fidélisation");

        Scene scene = new Scene(createMainLayout(), 450, 500);
        primaryStage.setScene(scene);

        showClients();
        primaryStage.show();
    }

    private VBox createMainLayout() {
        VBox mainLayout = new VBox(15);
        mainLayout.setPadding(new Insets(10, 10, 10, 10));

        mainLayout.getChildren().addAll(
                createMainTitle(),
                createNameLayout(),
                createMiddleLayout(),
                createDisplayLayout()
        );

        return mainLayout;
    }

    private StackPane createMainTitle() {
        Text mainTitle = new Text("Les milles de récompense");
        mainTitle.setFont(Font.font(null, FontWeight.BOLD, 20));

        return new StackPane(mainTitle);
    }

    private HBox createNameLayout() {
        return new HBox(8, new Text("Prénom : "), tfFirstName, new Text("Nom : "), tfLastName);
    }

    private BorderPane createMiddleLayout() {
        BorderPane borderPane = new BorderPane();

        borderPane.setTop(new Text("Points obtenus :"));
        borderPane.setLeft(createSemaineLayout());
        borderPane.setRight(createButtonLayout());

        return borderPane;
    }

    private ScrollPane createDisplayLayout() {
        ScrollPane spDisplayLayout = new ScrollPane();
        spDisplayLayout.setPrefHeight(250);
        spDisplayLayout.setContent(displayContent);
        spDisplayLayout.setFitToWidth(true);

        return spDisplayLayout;
    }

    private GridPane createSemaineLayout() {
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-border-color: black;");
        gridPane.setPadding(new Insets(5, 5, 5, 5));
        gridPane.setVgap(5);
        gridPane.setHgap(2);

        for (int i = 0; i < 4; i++) {
            tfSemaines[i] = new TextField();
            gridPane.addRow(i, new Text("Semaine " + (i + 1) + " : "), tfSemaines[i]);
        }

        return gridPane;
    }

    private VBox createButtonLayout() {
        Button btnAddClient = new Button("Ajouter un adhérent");
        btnAddClient.setOnAction(event -> addClient());

        Button btnDelClient = new Button("Supprime un adhérent");
        btnDelClient.setOnAction(event -> delClient());

        Button btnShowClients = new Button("Afficher tous les adhérents");
        btnShowClients.setOnAction(event -> showClients());

        Button btnTotalCLientMilles = new Button("Total des milles de l'adhérent");
        btnTotalCLientMilles.setTooltip(new Tooltip(
                "Inscrire un prénom et un nom\n" +
                        "avant d'appuyer sur ce bouton"
        ));
        btnTotalCLientMilles.setOnAction(event -> showClientMilles());

        return new VBox(10, btnAddClient, btnDelClient, btnShowClients, btnTotalCLientMilles);
    }

    private void addClient() {
        getName();
        getSemaines();

        if (checkValidName()) {
            Client client = getClient();
            if (client != null) {
                AlertBox.displayAlreadyExists(client.getNameTag());
                return;
            }

            if (checkValidSemaine()) {
                int[] milles = new int[4];
                for (int i = 0; i < 4; i++) milles[i] = Integer.parseInt(txtSemaines[i]);

                Client newClient = new Client(firstName, lastName, milles);
                clientList.add(newClient);
                AlertBox.displayAdd(newClient.getNameTag());

                showClients();
            }
        }
    }

    private void delClient() {
        getName();

        if (checkValidName()) {

            Client client = getClient();

            if (client == null) {
                AlertBox.displayNotFound(Client.createNameTag(firstName, lastName));
                return;
            }

            clientList.remove(client);
            AlertBox.displayDel(client.getNameTag());

            showClients();
        }
    }

    private void showClients() {
        displayContent.getChildren().clear();
        for (Client client : clientList) displayContent.getChildren().add(new Text(client.getClientTag()));
    }

    private void showClientMilles() {
        getName();

        if (checkValidName()) {
            Client client = getClient();
            if (client != null) {
                displayContent.getChildren().clear();
                displayContent.getChildren().add(new Text(client.getMilleTag()));
            } else {
                AlertBox.displayNotFound(Client.createNameTag(firstName, lastName));
            }
        }
    }

    private boolean checkValidName() {
        if (firstName.isEmpty() || firstName.contains(" ") || lastName.isEmpty() || lastName.contains(" ")) {
            AlertBox.displayInvalidName();
            return false;
        }
        return true;
    }

    private boolean checkValidSemaine() {
        boolean valide = true;
        for (int i = 0; i < 4; i++) {
            int n = 0;

            try {
                n = Integer.parseInt(txtSemaines[i]);
            } catch (NumberFormatException e) {
                valide = false;
            }
            if (n < 0) valide = false;

            if (!valide) {
                AlertBox.displayInvalidMilles(i + 1);
                return false;
            }
        }
        return true;
    }

    private Client getClient() {
        for (Client client : clientList) {
            if (client.getFirstName().equals(firstName) && client.getLastName().equals(lastName)) {
                return client;
            }
        }
        return null;
    }

    private void getName() {
        firstName = tfFirstName.getText();
        lastName = tfLastName.getText();
    }

    private void getSemaines() {
        for (int i = 0; i < 4; i++) txtSemaines[i] = tfSemaines[i].getText();
    }
}
