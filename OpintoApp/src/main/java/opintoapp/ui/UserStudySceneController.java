
package opintoapp.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import opintoapp.domain.Course;
import opintoapp.domain.StudyService;


public class UserStudySceneController implements Initializable {

    private StudyService studyService;
    private Main application;
    private Course c;
    private ObservableList<Course> undoneCourses;
    private ObservableList<Course> doneCourses;
    private boolean showUndone;
    private TableColumn gradeColumn;

//    @FXML
//    private VBox courseNodes;
//    
//    @FXML
//    private ScrollPane scroll;
    
    
    @FXML 
    private Label label;
    
    @FXML 
    private TextField courseName;
    
    @FXML 
    private Spinner credits;
    
    @FXML 
    private Label errorMessage;
    
    @FXML 
    private TableView<Course> tableView;
    
    
    
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
//        scroll.setContent(courseNodes);
        this.undoneCourses = FXCollections.observableArrayList(this.studyService.getUndoneCourses());
        tableView.setItems(undoneCourses);
        showUndone = true;
    }
    
    public void setDoneCourseList() throws SQLException {
        this.doneCourses = FXCollections.observableArrayList(this.studyService.getDoneCourses());
        tableView.setItems(doneCourses);
    }
//    public Node createCourseNode(Course course) {
//        HBox box = new HBox(10);
//        Label lbl = new Label(course.getName());
//        lbl.setMinHeight(28);
//        Button doneButton = new Button("done");
//        Region spacer = new Region();
//        HBox.setHgrow(spacer, Priority.ALWAYS);
//        box.setPadding(new Insets(0, 5, 0, 5));
//        box.getChildren().addAll(lbl, spacer, doneButton);
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
    private void handleAddNewButton(ActionEvent event) throws SQLException {
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
            setUndoneCourseList();
//            drawUndoneCourses();
        }
    }
    
    @FXML
    private void handleSwitchButton(ActionEvent event) throws SQLException {
        if(showUndone) {
            setDoneCourseList();
            gradeColumn = new TableColumn<>("Grade");
            gradeColumn.setCellValueFactory(new PropertyValueFactory("grade"));
            tableView.getColumns().add(gradeColumn);
            showUndone = false;
        } else {
            setUndoneCourseList();
            tableView.getColumns().remove(gradeColumn);
            showUndone = true;
        }
    }
    
    @FXML
    private void handleMarkDoneButton(ActionEvent event) {
        
    }
//    public void drawUndoneCourses() throws SQLException {
//        courseNodes.getChildren().clear();
//        List<Course> undoneCourses = this.studyService.getUndoneCourses();
//        undoneCourses.forEach(course -> {
//            courseNodes.getChildren().add(createCourseNode(course));
//        });
//        setUndoneCourseList();
//    } 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> creditsValues = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, 5);
        credits.setValueFactory(creditsValues);
//        courseNodes = new VBox();
//        scroll = new ScrollPane();
        // TODO
    }    
    
}
