package opintoapp.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import opintoapp.domain.StudyService;
import javafx.scene.paint.Color;
import opintoapp.domain.User;

public class CreateNewUserSceneController {
    
    private User user;
    private StudyService studyService;
    private Main application;

    @FXML
    private TextField name;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label text;

    public void setApplication(Main application) {
        this.application = application;
    }

    public void setStudyService(StudyService studyService) {
        this.studyService = studyService;
    }

    @FXML
    private void handleReturnButton(ActionEvent event) {
        this.application.setLoginScene();
    }

    @FXML
    private void handleCreateNewUserButton(ActionEvent event) {
        try {
            String usernameHandler = username.getText();
            String nameHandler = name.getText();
            String passwordHandler = password.getText();
            
            user = new User(nameHandler, usernameHandler, passwordHandler);

            if (usernameHandler.length() <= 2 || nameHandler.length() <= 2 || passwordHandler.length() <= 2 || usernameHandler.length() > 50 || nameHandler.length() > 50 || passwordHandler.length() > 50) {
                text.setText("username, name and password must be 3-50 characters long");
                text.setTextFill(Color.RED);
            } else if (studyService.createUser(user)) {
                text.setText("new user created");
                text.setTextFill(Color.GREEN);
            } else {
                text.setText("username must be unique");
                text.setTextFill(Color.RED);
            }

        } catch (SQLException ex) {

        }

    }
}
