import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

class AlertBox {
    AlertBox(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(200);
        window.setMinHeight(100);

        window.setTitle(title);

        StackPane layout = new StackPane(new Text(message));
        layout.setPadding(new Insets(8));

        window.setScene(new Scene(layout));

        window.showAndWait();
    }
}
