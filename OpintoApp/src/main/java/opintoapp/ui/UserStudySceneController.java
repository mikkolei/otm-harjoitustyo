
package opintoapp.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import opintoapp.domain.StudyService;


public class UserStudySceneController implements Initializable {

    private StudyService studyService;
    private Main application;
    
    @FXML
    private TextField name;
    
    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private Text text;
    
    public void setApplication(Main application) {
        this.application = application;
    }
    public void setStudyService(StudyService studyService) {
        this.studyService = studyService;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
