import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

class AlertBox {
    private static String message = "";
    private static String title = "";

    public static void displayNotFound(String nameTag) {
        title = "Erreur";
        message = nameTag + " n'existe pas!";
        createWindow();
    }

    public static void displayDel(String nameTag) {
        title = "Client suprimer";
        message = nameTag + " a été supprimer!";
        createWindow();
    }

    public static void displayAdd(String nameTag) {
        title = "Client ajouter";
        message = nameTag + " a été ajouter!";
        createWindow();
    }

    public static void displayAlreadyExists(String nameTag) {
        title = "Erreur";
        message = nameTag + " existe déja!";
        createWindow();
    }

    public static void displayInvalidName() {
        title = "Erreur";
        message = "SVP entrer un nom valide (Sans espace)";
        createWindow();
    }

    public static void displayInvalidMilles(int index) {
        title = "Erreur";
        message = "SVP entrer un entier positive pour la semaine " + index;
        createWindow();
    }

    private static void createWindow() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(200);
        window.setMinHeight(100);

        window.setTitle(title);

        StackPane layout = new StackPane(new Text(message));
        window.setScene(new Scene(layout));

        window.showAndWait();
    }
}
