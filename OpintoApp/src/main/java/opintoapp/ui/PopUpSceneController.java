
package opintoapp.ui;

import java.net.URL;
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

/**
 * Controller class for the mark done popup window
 */
public class PopUpSceneController implements Initializable {
    
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
    
    
    /**
     * Method to set the scene of the popup window
     * @param title title of the popup window
     * @param message message of the popup window
     * @param root1 parent scene
     */
    public void display(String title, String message, Parent root1) {
        stage = new Stage();
        stage.setTitle(title);
        infoLabel.setText(message);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        
    }
    /**
     * Method to get the value of the grade spinner
     * @return grade value
     */
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
        gradeValue = -1;
        stage.close();
    }
    
    /**
     * Set the features for the scene
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> gradeValues = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 5);
        gradeSpinner.setValueFactory(gradeValues);
    }    
    
}
