
package opintoapp.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import opintoapp.domain.StudyService;


public class UserStudySceneController {

    private StudyService studyService;
    private Main application;
    
    
    @FXML
    private Label label;
    
    @FXML
    private Region region;
    
    public void setApplication(Main application) {
        this.application = application;
    }
    public void setStudyService(StudyService studyService) {
        this.studyService = studyService;
    }
    
    public void setLabel() {
        label.setText("Welcome " + this.studyService.getLoggedIn().getUsername());
    }
    
    @FXML
    private void handleLogoutButton(ActionEvent event) {
        try {
            this.studyService.logout();
            this.application.setLoginScene();
        } catch (Exception e) {
            
        }
    }
    
    @FXML
    private void handleAddNewButton(ActionEvent event) {
        
    }
    
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        label.setText("Welcome");
//        // TODO
//    }    
    
}
