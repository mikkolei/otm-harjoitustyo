
package opintoapp.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import opintoapp.domain.Course;
import opintoapp.domain.StudyService;


public class UserStudySceneController implements Initializable {

    private StudyService studyService;
    private Main application;
    private Course c;
    private ObservableList<Course> undoneCourses;
    private ObservableList<Course> doneCourses;
    
    @FXML 
    private Label label;
    
    @FXML 
    private TextField courseName;
    
    @FXML 
    private Spinner credits;
    
    @FXML 
    private Label errorMessage;
    
    @FXML 
    private TableView<Course> table;
    
    
    
    
    public void setApplication(Main application) {
        this.application = application;
    }
    public void setStudyService(StudyService studyService) {
        this.studyService = studyService;
    }
    
    public void setLabel() {
        label.setText("Welcome " + this.studyService.getLoggedIn().getUsername());
    }
    
    public void setUndoneCourseList() throws SQLException {
//        this.undoneCourses = FXCollections.observableArrayList(this.studyService.getUndoneCourses());
//        table.setItems(undoneCourses);
//        TableColumn<Course, String> nameColumn = new TableColumn<>("Name");
//        nameColumn.setMinWidth(200);
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        
//        TableColumn<Course, Integer> creditColumn = new TableColumn<>("Credits");
//        creditColumn.setMinWidth(100);
//        creditColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));
//        
//        TableColumn<Course, Integer> user_idColumn = new TableColumn<>("User");
//        nameColumn.setMinWidth(200);
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));
//        
//        TableColumn<Course, Integer> idColumn = new TableColumn<>("Id");
//        nameColumn.setMinWidth(200);
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//        
//        TableColumn<Course, Boolean> doneColumn = new TableColumn<>("Done");
//        nameColumn.setMinWidth(200);
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("done"));
//        
//        TableColumn<Course, Integer> gradeColumn = new TableColumn<>("Grade");
//        nameColumn.setMinWidth(200);
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
//        table = new TableView<>();

//        table.getColumns().addAll(nameColumn, creditColumn, user_idColumn, idColumn, doneColumn, gradeColumn);
        
    }
//    public Node createCourseNode(Course course) {
//        HBox box = new HBox(10);
//        Label label = new Label(course.getName());
//        label.setMinHeight(28);
//        Button doneButton = new Button("done");
//        Region spacer = new Region();
//        HBox.setHgrow(spacer, Priority.ALWAYS);
//        box.setPadding(new Insets(0, 5, 0, 5));
//        box.getChildren().addAll(label, spacer, doneButton);
//        return box;
//    }
    
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
        
        if (name.length() < 2 || name.length() > 100) {
            errorMessage.setText("name length must be\n"
                    + "between 3-100 characters\n"
                    + "long");
            errorMessage.setTextFill(Color.RED);
        } else if (studyService.createCourse(c)) {
            errorMessage.setText("New course created");
            errorMessage.setTextFill(Color.GREEN);
            courseName.setText("");
        }
    }
//    @FXML
//    public void drawUndoneCourses() {
//        
//    } 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> creditsValues = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, 5);
        credits.setValueFactory(creditsValues);
        // TODO
    }    
    
}
