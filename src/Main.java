import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;


public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    private Stage window;
    private Scene scene1, scene2;
    private Button           loadPamakBookButton, addFriendButton, findMutualFriendButton, printFriendsButton, addToGroupButton, addToClosedGroupButton, backToMainPageButton, createUserButton, logInButton, savePamakBookButton, editUsersButton;
    private TextField        userNameTextField, mailTextField;
    private ComboBox<String> comboBox1, comboBox2;
    private boolean          flag = false;
    private static ArrayList<User> arrayList;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Console console = new Console();
        console.consoleDisplay();

        /**
         * Initialize Users and Groups
         */
        ArrayList<User> pamakBookUsers = new ArrayList<>();

        User u1 = new User("Makis", "it1698@uom.edu.gr", console);
        User u2 = new User("Petros", "it1624@uom.edu.gr", console);
        User u3 = new User("Maria", "it16112@uom.edu.gr", console);
        User u4 = new User("Gianna", "it16133@uom.edu.gr", console);
        User u5 = new User("Nikos", "it1658@uom.edu.gr", console);
        User u6 = new User("Babis", "it16104@uom.edu.gr", console);

        u2.addToArrayListOfUserPost("06/01/2017 17:14:56, Petros \nΠολύ χιόνισε σήμερα!");
        u3.addToArrayListOfUserPost("05/01/2017 16:15:42, Maria \nΞέρουμε αν θα λειτουργήσει το Πανεπιστήμιο με τέτοιο κρύο;");
        u5.addToArrayListOfUserPost("07/01/2017 16:15:15, Nikos \nΕπιτέλους είδαμε άσπρη μέρα");

        Group g1 = new Group("WebGurus","A group for web passionates");
        Group g2 = new ClosedGroup("ExamSolutions","Solutions to common exam questions");

        pamakBookUsers.add(u1);
        pamakBookUsers.add(u2);
        pamakBookUsers.add(u3);
        pamakBookUsers.add(u4);
        pamakBookUsers.add(u5);
        pamakBookUsers.add(u6);


        window = primaryStage;

        /**
         * Text Fields
         */
        userNameTextField = new TextField();
        mailTextField = new TextField();

        userNameTextField.setPromptText("User name");
        mailTextField.setPromptText("E-mail");

        /**
         * Buttons
         */
        createUserButton    = new Button("Create user");
        logInButton         = new Button("Log in");
        savePamakBookButton = new Button("Save Pamak Book");
        editUsersButton     = new Button("Edit users");
        loadPamakBookButton = new Button("Load Pamak Book");

        /**
         * Button Actions
         */
        createUserButton.setOnAction(event -> {
            CreateUser.display(pamakBookUsers, console);

            comboBox1.getItems().clear();
            comboBox2.getItems().clear();

            for(User u: pamakBookUsers)
                if(!(u.getEmail().equals("break"))) {
                    comboBox1.getItems().add(u.getName());
                    comboBox2.getItems().add(u.getName());
                }
        });


        logInButton.setOnAction(event -> {
            User tempUser = new User();

            String name  = userNameTextField.getText();
            String email = mailTextField.getText();

            for(User u: pamakBookUsers)
                if(u.getName().equals(name) && u.getEmail().equals(email)) {
                    flag = true;
                    tempUser = u;
                }

            if(flag) {
                AlertBox.display("User " + tempUser.getName() + " found");
                UserPage.display(tempUser, pamakBookUsers);
            }
            else
                AlertBox.display("User did not found");
        });


        savePamakBookButton.setOnAction(event -> {
            try {
                FileOutputStream fileOutputStream     = new FileOutputStream("C:\\Users\\Dimitris\\workspace\\Java projects\\PamakBook.v3\\src\\pamakbook.ser");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(pamakBookUsers);
                objectOutputStream.close();
                fileOutputStream.close();
                console.setTextArea("Pamak Book has been saved");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        loadPamakBookButton.setOnAction(event -> {
            try {
                FileInputStream fileInputStream     = new FileInputStream("C:\\Users\\Dimitris\\workspace\\Java projects\\PamakBook.v3\\src\\pamakbook.ser");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                arrayList = (ArrayList<User>) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();

                String text = "Load\n";

                for(User u : arrayList)
                    text += u.getName() + " " + u.getEmail() + "\n";
                console.setTextArea(text);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });


        editUsersButton.setOnAction(event -> {
            window.setScene(scene2);
            window.setTitle("Edit Users Page");
        });

        /**
         * First Panel (Main Page)
         */
        GridPane gridPane1 = new GridPane();

        gridPane1.setPadding(new Insets(8,8,8,8));
        gridPane1.setVgap(8);
        gridPane1.setHgap(8);

        GridPane.setConstraints(createUserButton,0,0);
        GridPane.setConstraints(editUsersButton, 0,1);
        GridPane.setConstraints(savePamakBookButton,0,2);
        GridPane.setConstraints(loadPamakBookButton,0,3);
        GridPane.setConstraints(logInButton,1,0);
        GridPane.setConstraints(userNameTextField,1,1);
        GridPane.setConstraints(mailTextField,1,2);

        gridPane1.getChildren().addAll(loadPamakBookButton, editUsersButton, createUserButton, savePamakBookButton, logInButton, userNameTextField, mailTextField);

        /**
         * First Scene
         */
        scene1 = new Scene(gridPane1, 300, 150);


        /**
         * Second Panel (Edit Users Page)
         */

        /**
         * Buttons
         */
        addFriendButton        = new Button("Add friend");
        findMutualFriendButton = new Button("Find mutual");
        printFriendsButton     = new Button("Print your friends");
        addToGroupButton       = new Button("Add to group");
        addToClosedGroupButton = new Button("Add to closed group");
        backToMainPageButton   = new Button("Back to Main Page");

        Tooltip tooltip  = new Tooltip();
        Tooltip tooltip2 = new Tooltip();

        tooltip.setText("Select from both lists");
        tooltip2.setText("Select from the first list");

        addFriendButton.setTooltip(tooltip);
        findMutualFriendButton.setTooltip(tooltip);
        printFriendsButton.setTooltip(tooltip2);
        addToGroupButton.setTooltip(tooltip2);
        addToClosedGroupButton.setTooltip(tooltip2);

        /**
         * Combo Box
         */
        comboBox1 = new ComboBox<>();
        comboBox2 = new ComboBox<>();

        for(User u: pamakBookUsers) {
            comboBox1.getItems().add(u.getName());
            comboBox2.getItems().add(u.getName());
        }

        /**
         * Labels
         */
        Label label = new Label("Select from both lists");

        /**
         * Button Actions
         */
        addFriendButton.setOnAction(event -> {
            User tempUser = new User();

            for(User u: pamakBookUsers)
                if(u.getName().equals(comboBox1.getValue())) {
                    tempUser = u;
                    break;
                }

            for(User u: pamakBookUsers)
                if(u.getName().equals(comboBox2.getValue())) {
                    tempUser.addFriend(u, console);
                    break;
                }
        });


        backToMainPageButton.setOnAction(event -> {
            window.setScene(scene1);
            window.setTitle("Main Page");
        });


        findMutualFriendButton.setOnAction(event -> {
            User tempUser = new User();
            for(User u: pamakBookUsers)
                if(u.getName().equals(comboBox1.getValue())) {
                    tempUser = u;
                    break;
                }

            for(User u: pamakBookUsers)
                if(u.getName().equals(comboBox2.getValue())) {
                    tempUser.mutualFriends(u, console);
                    break;
                }
        });


        printFriendsButton.setOnAction(event -> {
            User tempUser = new User();
            for(User u: pamakBookUsers) {
                if(u.getName().equals(comboBox1.getValue())) {
                    tempUser = u;
                    break;
                }
            }
            tempUser.printFriends(console);
        });


        addToGroupButton.setOnAction(event -> {
            for(User u: pamakBookUsers)
                if(u.getName().equals(comboBox1.getValue())) {
                    g1.addToGroup(u, console);
                    break;
                }
        });


        addToClosedGroupButton.setOnAction(event -> {
            for(User u: pamakBookUsers)
                if(u.getName().equals(comboBox1.getValue())) {
                    g2.addToGroup(u, console);
                    break;
                }
        });

        /**
         * Second Panel
         */
        GridPane gridPane2 = new GridPane();

        gridPane2.setPadding(new Insets(8,8,8,8));
        gridPane2.setVgap(8);
        gridPane2.setHgap(8);

        GridPane.setConstraints(addFriendButton,1,0);
        GridPane.setConstraints(findMutualFriendButton, 1,1);
        GridPane.setConstraints(printFriendsButton,1,2);
        GridPane.setConstraints(addToGroupButton,1,3);
        GridPane.setConstraints(addToClosedGroupButton,1,4);
        GridPane.setConstraints(backToMainPageButton,0,6);
        GridPane.setConstraints(label,0,0);
        GridPane.setConstraints(comboBox1,0,1);
        GridPane.setConstraints(comboBox2,0,2);

        gridPane2.getChildren().addAll(label,comboBox1, comboBox2, addFriendButton, findMutualFriendButton, printFriendsButton, addToClosedGroupButton, addToGroupButton, backToMainPageButton);

        /**
         * Second Scene
         */
        scene2 = new Scene(gridPane2, 300, 220);

        /**
         * Window settings
         */
        window.show();
        window.setScene(scene1);
        window.setTitle("Main Page");
        window.getIcons().add(new Image("uom.gif"));
        createUserButton.requestFocus();
        window.centerOnScreen();
    }
}
