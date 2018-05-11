
package opintoapp.ui;

import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import opintoapp.dao.*;
import opintoapp.domain.*;

/**
 * Main class for the application
 */
public class Main extends Application {
    
    private Database db;
    private StudyService studyService;
    private Scene loginScene;
    private Scene createNewUserScene;
    private Scene userStudyScene;
    private Stage stage;
    private UserStudySceneController userStudySceneController;
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Initialize method that sets controllers, scenes, database and DAOs
     * @throws Exception 
     */
    @Override
    public void init() throws Exception {
        this.db = new Database("jdbc:sqlite:opintoApp.db");
        SQLUserDao sqlUserDao = new SQLUserDao(db);
        SQLCourseDao sqlCourseDao = new SQLCourseDao(db, sqlUserDao);
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
        userStudySceneController = userStudySceneLoader.getController();
        userStudySceneController.setStudyService(studyService);
        userStudySceneController.setApplication(this);
        userStudyScene = new Scene(studyPane);
    }
    
    /**
     * Sets the first stage and title and shows the application
     * @param stage first stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("OpintoApp");
        setLoginScene();
        stage.show();
        
        stage.setOnCloseRequest(e-> {
            System.out.println("closing");
            System.out.println(studyService.getLoggedIn());
            if (studyService.getLoggedIn() != null) {
                e.consume();
            }
        });
    }
    
    /**
     * Sets loginScene
     */
    public void setLoginScene() {
        stage.setScene(loginScene);
    }
    
    /**
     * Sets UserStudyScene
     * @throws SQLException 
     */
    public void setUserStudyScene() throws SQLException {
        stage.setScene(userStudyScene);
        userStudySceneController.setLabel();
        userStudySceneController.setUndoneCourseList();
    }
    
    /**
     * Sets createNewUserScene
     */
    public void setCreateNewUserScene() {
        stage.setScene(createNewUserScene);
    }
}
