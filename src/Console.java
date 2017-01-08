import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Console {

    private TextArea textArea = new TextArea();

    public void setTextArea(String textArea) {
        this.textArea.setText(textArea);
    }

    public void consoleDisplay() {

        Stage window = new Stage();
        Scene scene;

        /**
         * Text Area
         */
        textArea.setEditable(false);
        textArea.setText("Welcome to Pamak Book");
        textArea.setWrapText(true);

        /**
         * Panel
         */
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(8,8,8,8));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(textArea);

        scene = new Scene(vBox,300,200);

        /**
         * Window Settings
         */
        window.show();
        window.getIcons().add(new Image("uom.gif"));
        window.setScene(scene);
        window.setTitle("Console display");
        window.setX(200);
        window.setY(190);
    }
}
