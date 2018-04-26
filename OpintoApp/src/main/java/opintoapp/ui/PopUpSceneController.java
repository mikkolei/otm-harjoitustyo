
package opintoapp.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import opintoapp.domain.*;


public class PopUpSceneController implements Initializable {
    
    private StudyService studyService;
    private Main application;
    private Stage stage;
    private int gradeValue;
    
    @FXML 
    private Spinner gradeSpinner;
    
    @FXML
    private Label infoLabel;
    
    @FXML 
    private Button yesButton;
    
    @FXML
    private Button cancelButton;
    
    
    public void display(String title, String message, Parent root1) {
        stage = new Stage();
        stage.setTitle(title);
        infoLabel.setText(message);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        
    }
    
    public int returnGradeValue() {
        return gradeValue;
    }
    
    @FXML
    private void handleYesButton(ActionEvent event) {
        gradeValue = (int) gradeSpinner.getValue();
        stage.close();
    }
    
    @FXML
    private void handleCancelButton(ActionEvent event) {
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> gradeValues = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 5);
        gradeSpinner.setValueFactory(gradeValues);
    }    
    
}
