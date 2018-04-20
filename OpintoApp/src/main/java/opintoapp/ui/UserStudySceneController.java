
package opintoapp.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import opintoapp.domain.Course;
import opintoapp.domain.StudyService;


public class UserStudySceneController implements Initializable {

    private StudyService studyService;
    private Main application;
    private Course c;
    
    
    @FXML
    private Label label;
    
    @FXML
    private TextField courseName;
    
    @FXML
    private Spinner credits;
    
    @FXML
    private Label errorMessage;
    
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
        String name = courseName.getText();
        int crts = (int) credits.getValue();
        c = new Course(this.studyService.getLoggedIn(), name, crts);
//        c = new Course("", 0, this.studyService.getLoggedIn());
        
        if (name.length() < 2 || name.length() > 100) {
            errorMessage.setText("name length must be between 3-100 characters long");
            errorMessage.setMaxHeight(50);
            errorMessage.setTextFill(Color.RED);
            
        } else if (studyService.createCourse(c)) {
            errorMessage.setText("New course created");
            errorMessage.setTextFill(Color.GREEN);
            courseName.setText("");
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> creditsValues = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, 5);
        credits.setValueFactory(creditsValues);
        // TODO
    }    
    
}
