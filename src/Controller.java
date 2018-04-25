import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

class Controller {
    private final TextField tfFirstName;
    private final TextField tfLastName;

    private final TextField[] tfSemaines;

    private final VBox displayContent;

    Controller(TextField tfFirstName, TextField tfLastName, TextField[] tfSemaines, VBox displayContent) {
        this.tfFirstName = tfFirstName;
        this.tfLastName = tfLastName;
        this.tfSemaines = tfSemaines;
        this.displayContent = displayContent;
    }

    Name getName() {
        return new Name(tfFirstName.getText(), tfLastName.getText());
    }

    String[] getStrMilles() {
        String[] strMiles = new String[4];
        for (int i = 0; i < 4; i++) strMiles[i] = tfSemaines[i].getText();
        return strMiles;
    }

    void clearDisplay() {
        displayContent.getChildren().clear();
    }

    void addToDisplay(Node node) {
        displayContent.getChildren().add(node);
    }
}
