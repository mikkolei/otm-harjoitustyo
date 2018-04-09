
package opintoapp.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import opintoapp.dao.*;
import opintoapp.domain.*;

public class Main extends Application {
    
    private Database db;
    private StudyService studyService;
    private Scene loginScene;
    private Scene newUserScene;
    private Label menuLabel = new Label();
    
    public static void main(String[] args) {
        launch(args);
        System.out.println("Hello world");
        
    }
    
    @Override
    public void init() throws Exception {
        this.db = new Database("jdbc:sqlite:opintoApp.db");
        SQLUserDao sqlUserDao = new SQLUserDao();
//        SQLCourseDao sqlCourseDao = new SQLCourseDao();
//        this.studyService = new StudyService(sqlUserDao, );
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // login scene
//        VBox emptyPane = new VBox(10);
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        HBox passwordPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        
        Label loginLabel = new Label("username");
        TextField usernameInput = new TextField();
        inputPane.getChildren().addAll(loginLabel, usernameInput);
        
        Label passwordLabel = new Label("password");
        PasswordField userPassword = new PasswordField();
        passwordPane.getChildren().addAll(passwordLabel, userPassword);
        

        Label loginMessage = new Label();
        
        Button loginButton = new Button("login");
        Button createUserButton = new Button("create new user");
        
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            menuLabel.setText(username + " logged in.");
//            if()
        });
        
        createUserButton.setOnAction(e -> {
            usernameInput.setText("");
            primaryStage.setScene(newUserScene);
        });
        loginPane.getChildren().addAll(loginMessage, inputPane, passwordPane, loginButton, createUserButton);
        loginScene = new Scene(loginPane, 500, 250);
        
//        newUserScene = new Scene(emptyPane, 300, 250);
        
        primaryStage.setTitle("OpintoApp");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        
    }
    
}
