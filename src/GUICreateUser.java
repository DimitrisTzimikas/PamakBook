import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;

public class GUICreateUser {

    public static void display(ArrayList<User> pamakBookUsers, GUIConsole console) {

        Stage window = new Stage();

        /**
         * Create window
         */
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create User");
        window.setMinHeight(100);
        window.setMinWidth(100);

        /**
         * Text Field
         */
        TextField userNameTextField = new TextField();
        TextField emailTextField    = new TextField();

        userNameTextField.setPromptText("User name");
        emailTextField.setPromptText("E-mail");

        /**
         * Button & Actions
         */
        Button commitButton = new Button("Commit");
        Button closeButton  = new Button("Close");

        closeButton.setOnAction(event -> window.close());
        commitButton.setOnAction(event -> {
            boolean flag = true;

            for (User u : pamakBookUsers) {
                if (userNameTextField.getText().equals(u.getName()))
                    flag = false;
                if (!flag)
                    break;
            }

            if (flag) {
                pamakBookUsers.add(new User(userNameTextField.getText(), emailTextField.getText(), console));
                console.setTextArea("User " + userNameTextField.getText() + " has been created");
            }
        });

        /**
         * Panel
         */
        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(8,8,8,8));
        gridPane.setVgap(8);
        gridPane.setHgap(8);

        GridPane.setConstraints(commitButton,0,0);
        GridPane.setConstraints(userNameTextField,1,0);
        GridPane.setConstraints(emailTextField,1,1);
        GridPane.setConstraints(closeButton,0,1);

        gridPane.getChildren().addAll(closeButton, commitButton,userNameTextField,emailTextField);

        /**
         * Scene
         */
        Scene scene = new Scene(gridPane);

        /**
         * Window settings
         */
        window.setScene(scene);
        window.getIcons().add(new Image("uom.gif"));
        commitButton.requestFocus();
        window.centerOnScreen();
        window.showAndWait();
    }
}
