package opintoapp.ui;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import opintoapp.domain.StudyService;

/**
 * Controller class for the login scene
 */
public class LoginSceneController {

    private StudyService studyService;
    private Main application;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label text;
    
    /**
     * Sets the Main application
     * @param application Main application
     */
    public void setApplication(Main application) {
        this.application = application;
    }
    
    /**
     * Sets the used StudyService
     * @param studyService studyService to be used
     */
    public void setStudyService(StudyService studyService) {
        this.studyService = studyService;
    }

    @FXML
    private void handleLoginButton(ActionEvent event) {
        try {
            if (this.studyService.login(username.getText(), password.getText())) {
                text.setText("");
                username.setText("");
                password.setText("");
                this.application.setUserStudyScene();
            } else {
                text.setText("user does not exist or password is wrong");
                text.setTextFill(Color.RED);
            }
        } catch (SQLException ex) {

        }
    }

    @FXML
    private void handleCreateNewUserButton(ActionEvent event) {
        username.setText("");
        password.setText("");
        text.setText("");
        this.application.setCreateNewUserScene();
    }

}
