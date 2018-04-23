
package opintoapp.ui;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
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
    private Scene createNewUserScene;
    private Scene userStudyScene;
    private Label menuLabel = new Label();
    private Stage stage;
    private UserStudySceneController userStudySceneController;
    
    public static void main(String[] args) {
        launch(args);
        System.out.println("Hello world");
        
    }
    
    @Override
    public void init() throws Exception {
        this.db = new Database("jdbc:sqlite:opintoApp.db");
        SQLUserDao sqlUserDao = new SQLUserDao(db);
        SQLCourseDao sqlCourseDao = new SQLCourseDao(db);
        this.studyService = new StudyService(sqlUserDao, sqlCourseDao);
        
        FXMLLoader loginSceneLoader = new FXMLLoader(getClass().getResource("/fxml/LoginScene.fxml"));
        Parent loginPane = loginSceneLoader.load();
        LoginSceneController loginSceneController = loginSceneLoader.getController();
        loginSceneController.setStudyService(studyService);
        loginSceneController.setApplication(this);
        loginScene = new Scene(loginPane);
        
        FXMLLoader createNewUserSceneLoader = new FXMLLoader(getClass().getResource("/fxml/CreateNewUserScene.fxml"));
        Parent newUserPane = createNewUserSceneLoader.load();
        CreateNewUserSceneController createNewUserController = createNewUserSceneLoader.getController();
        createNewUserController.setStudyService(studyService);
        createNewUserController.setApplication(this);
        createNewUserScene = new Scene(newUserPane);
        
        FXMLLoader userStudySceneLoader = new FXMLLoader(getClass().getResource("/fxml/UserStudyScene.fxml"));
        Parent studyPane = userStudySceneLoader.load();
//        UserStudySceneController 
        userStudySceneController = userStudySceneLoader.getController();
        userStudySceneController.setStudyService(studyService);
        userStudySceneController.setApplication(this);
        userStudyScene = new Scene(studyPane);
    }
    
//    public Node createCourseNode(Course course) {
//        HBox frame = new HBox(20);
//        Label
//        return Node;
//    }

    @Override
    public void start(Stage stage) throws Exception {
        // login scene
        
        this.stage = stage;
        stage.setTitle("OpintoApp");
        setLoginScene();
        stage.show();
        
    }
    public void setLoginScene() {
        stage.setScene(loginScene);
    }
    
    public void setUserStudyScene() throws SQLException {
        stage.setScene(userStudyScene);
        userStudySceneController.setLabel();
        userStudySceneController.setUndoneCourseList();
    }
    
    public void setCreateNewUserScene() {
        stage.setScene(createNewUserScene);
    }
}
