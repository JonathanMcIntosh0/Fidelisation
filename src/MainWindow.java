import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import org.jetbrains.annotations.NotNull;

public class MainWindow extends Application {
    private static final String WINDOW_TITLE = "Fidélisation";
    private static final int SCENE_HEIGHT = 500;
    private static final int SCENE_WIDTH = 450;

    private final TextField tfFirstName = new TextField();
    private final TextField tfLastName = new TextField();

    private final TextField[] tfSemaines = new TextField[4];

    private final VBox displayContent = new VBox(5);

    private Database database = new Database(
            tfFirstName,
            tfLastName,
            tfSemaines,
            displayContent
    );


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NotNull Stage primaryStage) {
        primaryStage.setTitle(WINDOW_TITLE);
        primaryStage.setScene(new Scene(createMainLayout(), SCENE_WIDTH, SCENE_HEIGHT));

        database.addDefaultClients();
        database.showClients();

        primaryStage.show();
    }

    @NotNull
    private VBox createMainLayout() {
        VBox mainLayout = new VBox(
                createMainTitle(),
                createNameLayout(),
                createMiddleLayout(),
                createDisplayLayout()
        );

        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setSpacing(15);
        mainLayout.setPadding(new Insets(10));

        return mainLayout;
    }

    @NotNull
    private Node createMainTitle() {
        Text mainTitle = new Text("Les milles de récompense");
        mainTitle.setFont(Font.font(null, FontWeight.BOLD, 20));

        return mainTitle;
    }

    private Node createNameLayout() {
        return new HBox(8,
                new Text("Prénom : "),
                tfFirstName,
                new Text("Nom : "),
                tfLastName
        );
    }

    @NotNull
    private Node createMiddleLayout() {
        BorderPane borderPane = new BorderPane();

        borderPane.setTop(new Text("Points obtenus :"));
        borderPane.setLeft(createSemaineLayout());
        borderPane.setCenter(createButtonLayout());

        return borderPane;
    }

    @NotNull
    private Node createDisplayLayout() {
        ScrollPane spDisplayLayout = new ScrollPane();
        spDisplayLayout.setPrefHeight(250);
        spDisplayLayout.setContent(displayContent);
        spDisplayLayout.setFitToWidth(true);

        return spDisplayLayout;
    }

    @NotNull
    private Node createSemaineLayout() {
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

    private Node createButtonLayout() {
        Button btnAddClient = new Button("Ajouter un adhérent");
        btnAddClient.setOnAction(event -> database.addClient());

        Button btnDelClient = new Button("Supprime un adhérent");
        btnDelClient.setOnAction(event -> database.delClient());

        Button btnShowClients = new Button("Afficher tous les adhérents");
        btnShowClients.setOnAction(event -> database.showClients());

        Button btnTotalCLientMilles = new Button("Total des milles de l'adhérent");
        btnTotalCLientMilles.setTooltip(new Tooltip(
                "Inscrire un prénom et un nom\n" +
                        "avant d'appuyer sur ce bouton"
        ));
        btnTotalCLientMilles.setOnAction(event -> database.showClientMilles());

        return new VBox(10, btnAddClient, btnDelClient, btnShowClients, btnTotalCLientMilles);
    }
}
