
package opintoapp.ui;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
    private Scene studyScene;
    private Label menuLabel = new Label();
    
    public static void main(String[] args) {
        launch(args);
        System.out.println("Hello world");
        
    }
    
    @Override
    public void init() throws Exception {
        this.db = new Database("jdbc:sqlite:opintoApp.db");
        SQLUserDao sqlUserDao = new SQLUserDao(db);
        SQLCourseDao sqlCourseDao = new SQLCourseDao();
        this.studyService = new StudyService(sqlUserDao, sqlCourseDao);
    }
    
//    public Node createCourseNode(Course course) {
//        HBox frame = new HBox(20);
//        Label
//        return Node;
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // login scene
        
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        HBox passwordPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        
        Label loginLabel = new Label("username");
        TextField usernameField = new TextField();
        inputPane.getChildren().addAll(loginLabel, usernameField);
        
        Label passwordLabel = new Label("password");
        PasswordField userPasswordField = new PasswordField();
        passwordPane.getChildren().addAll(passwordLabel, userPasswordField);
        

        Label loginMessage = new Label();
        
        Button loginButton = new Button("login");
        Button createUserButton = new Button("create new user");
        
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = userPasswordField.getText();
            menuLabel.setText(username + " logged in.");
            try {
                if(studyService.login(username, password)) {
                    loginMessage.setText("");
                    primaryStage.setScene(studyScene);
                    usernameField.setText("");
                    userPasswordField.setText("");
//                    redrawcourse list
                    
                } else {
                    loginMessage.setText("user does not exist");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        createUserButton.setOnAction(e -> {
            usernameField.setText("");
            primaryStage.setScene(newUserScene);
        });
        loginPane.getChildren().addAll(loginMessage, inputPane, passwordPane, loginButton, createUserButton);
        loginScene = new Scene(loginPane, 500, 300);
        
        // new createUserScene
        
        VBox newUserPane = new VBox(10);
        
        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        TextField newUsernameField = new TextField();
        Label newUsernameLabel = new Label("username");
        newUsernameLabel.setPrefWidth(100);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameField);
        
        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        TextField newNameField = new TextField();
        Label newNameLabel = new Label("name");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newNameField);
        
        HBox newPasswordPane = new HBox(10);
        newPasswordPane.setPadding(new Insets(10));
        PasswordField newPasswordField = new PasswordField();
        Label newPasswordLabel = new Label("password");
        newPasswordLabel.setPrefWidth(100);
        newPasswordPane.getChildren().addAll(newPasswordLabel, newPasswordField);
        
        Label newUserCreationMessage = new Label();
        
        Button createNewUserButton = new Button("create");
        createNewUserButton.setPadding(new Insets(10));
        
        Button goBackButton = new Button("return");
        goBackButton.setPadding(new Insets(10));
        
        goBackButton.setOnAction(e->{
            primaryStage.setScene(loginScene);
        });
        
        createNewUserButton.setOnAction(e->{
            try {
                String username = newUsernameField.getText();
                String name = newNameField.getText();
                String password = newPasswordField.getText();
                
                if(username.length() <= 2 || name.length() <= 2 || password.length() <= 2 || username.length() > 50 || name.length() > 50 || password.length() > 50) {
                    newUserCreationMessage.setText("username, name and password must be 3-50 characters long");
                } else if (studyService.createUser(name, username, password)) {
                    newUserCreationMessage.setText("");
                    loginMessage.setText("new user created");
                    primaryStage.setScene(loginScene);
                } else {
                    newUserCreationMessage.setText("username must be unique");
                }
                // features missing
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        newUserPane.getChildren().addAll(newUserCreationMessage, newUsernamePane, newNamePane, newPasswordPane, createNewUserButton, goBackButton);
        
        newUserScene = new Scene(newUserPane, 500, 300);
        
        primaryStage.setTitle("OpintoApp");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        
    }
    
}
