import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class GUIUserPage {

    public static void display(User logInUser) {

        ArrayList<String> tempArrayListOfUsersPost = new ArrayList<>();
        ArrayList<User> tempArrayListOfUserFriends = logInUser.getArrayListOfUserFriends();
        ArrayList<User> tempArrayListOfSuggFriends;
        boolean flag = false;

        /**
         * Window create
         */
        Stage window = new Stage();

        /**
         * Labels
         */
        Label userNameLabel        = new Label(logInUser.getName() + "  " + logInUser.getEmail());
        Label recentPostLabel      = new Label("Recent Posts by Friends");
        Label suggestedFriendLabel = new Label("Suggested friend");

        /**
         * Text Field
         */
        TextField suggestedFriendTextField = new TextField();

        if(!tempArrayListOfUserFriends.isEmpty()) {
            for(User u : logInUser.getArrayListOfUserFriends()) {
                tempArrayListOfSuggFriends = u.getArrayListOfUserFriends();
                for (int i = 0; i < tempArrayListOfSuggFriends.size() ; i++) {
                    if(!tempArrayListOfSuggFriends.get(i).isHeMyFriend(logInUser) && !logInUser.getName().equals(tempArrayListOfSuggFriends.get(i).getName())){
                        suggestedFriendTextField.setText(tempArrayListOfSuggFriends.get(i).getName());
                        flag = true;
                        break;
                    }
                }
                if(flag)
                    break;
            }

        }
        else
            suggestedFriendTextField.setText("You have no friends");


        /**
         * Text Area
         */
        TextArea textArea  = new TextArea();
        TextArea textArea2 = new TextArea();

        textArea.setPromptText("Enter your post here");
        textArea.setWrapText(true);
        textArea.setPrefWidth(250);
        textArea.setPrefHeight(200);

        textArea2.setWrapText(true);
        textArea2.setPrefWidth(250);
        textArea2.setPrefHeight(200);

        String message = "";

        for(int i = 0; i < logInUser.getArrayListOfUserPost().size(); i++)
            tempArrayListOfUsersPost.add(logInUser.getArrayListOfUserPost().get(i));

        if(logInUser.getArrayListOfUserFriends() != null){
            for(User u : logInUser.getArrayListOfUserFriends())
                for(int j = 0; j < u.getArrayListOfUserPost().size(); j++)
                    tempArrayListOfUsersPost.add(u.getArrayListOfUserPost().get(j));
        }

        Collections.sort(tempArrayListOfUsersPost);

        for(String s : tempArrayListOfUsersPost)
            message += s +"\n";

        textArea2.setText(message);

        /**
         * Buttons
         */
        Button closeButton = new Button("Back to Main Page");
        Button postButton  = new Button("Post");

        /**
         * Date & Time
         */
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        /**
         * Button Actions
         */
        closeButton.setOnAction(event -> {
            tempArrayListOfUsersPost.clear();
            window.close();
        });


        postButton.setOnAction(event -> {

            logInUser.addToArrayListOfUserPost(dateFormat.format(date) + ", " + logInUser.getName() + " \n" + textArea.getText());
            tempArrayListOfUsersPost.add(dateFormat.format(date) + ", " + logInUser.getName() + " \n" + textArea.getText());

            Collections.sort(tempArrayListOfUsersPost);

            String m = "";

            for(String s : tempArrayListOfUsersPost)
                m += s.toString() +"\n";

            textArea2.setText(m);
        });

        /**
         * Panel
         */
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(8,8,8,8));
        gridPane.setHgap(8);
        gridPane.setVgap(8);

        GridPane.setConstraints(userNameLabel,0,0);;
        GridPane.setConstraints(closeButton,1,0);
        GridPane.setConstraints(textArea,0,1);
        GridPane.setConstraints(postButton,1,1);
        GridPane.setConstraints(textArea2, 0,2);
        GridPane.setConstraints(recentPostLabel, 1,2);
        GridPane.setConstraints(suggestedFriendLabel, 0, 3);
        GridPane.setConstraints(suggestedFriendTextField,1, 3);

        gridPane.getChildren().addAll(suggestedFriendLabel, suggestedFriendTextField, recentPostLabel,textArea2, postButton, textArea, userNameLabel, closeButton);

        /**
         * Scene
         */
        Scene scene = new Scene(gridPane, 430, 500);

        /**
         * Window settings
         */
        recentPostLabel.requestFocus();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("User Page");
        window.getIcons().add(new Image("uom.gif"));
        window.centerOnScreen();
        window.setScene(scene);
        window.showAndWait();
    }

}
