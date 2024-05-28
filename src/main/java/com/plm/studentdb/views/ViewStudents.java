package com.plm.studentdb.views;

import com.plm.studentdb.database.DBView;
import com.plm.studentdb.models.Student;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ViewStudents {
    @FXML public TableView<Student> tbvStudents = new TableView<>();
    @FXML public Parent studentsAddView;
    @FXML public Parent studentsMessageView;
    @FXML public Label btnViewStudentsAdd;

    @FXML public StudentsAdd studentsAddViewController;
    @FXML public StudentsMessage studentsMessageViewController;

    public static ObservableList<Student> studentsListTable = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Table
        tbvStudents.setEditable(true);

        TableColumn<Student, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setPrefWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        idColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.17));

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setPrefWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.27));

        TableColumn<Student, String> courseColumn = new TableColumn<>("Course");
        courseColumn.setPrefWidth(100);
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        courseColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.2));

        TableColumn<Student, Integer> yearColumn = new TableColumn<>("Year");
        yearColumn.setPrefWidth(100);
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.07));

        TableColumn<Student, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setPrefWidth(100);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.19));

        tbvStudents.getColumns().addAll(idColumn, nameColumn, courseColumn, yearColumn, statusColumn);
        tbvStudents.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        tbvStudents.getColumns().forEach(studentTableColumn -> {
            studentTableColumn.setReorderable(false);
            studentTableColumn.setResizable(false);
        });
        tbvStudents.getSelectionModel().clearSelection();

        tbvStudents.setItems(studentsListTable);
        getData();

        // Add Button
        btnViewStudentsAdd.setOnMouseClicked(ev -> {
            studentsAddViewController.anpStudentsAddView.setVisible(true);
        });
        studentsAddViewController.studentsMessageViewController = studentsMessageViewController;
    }

    private void getData() {
        System.out.println("Getting Records");
        studentsListTable.addAll(DBView.viewStudentRecord());
    }


    public void showFormEditor(double delay) {
        studentsAddView.setOpacity(0);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.25), studentsAddView);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setDelay(Duration.seconds(delay));

        ScaleTransition scaleUpTransition = new ScaleTransition(Duration.seconds(0.25), studentsAddView);
        scaleUpTransition.setFromX(0);
        scaleUpTransition.setFromY(0);
        scaleUpTransition.setToX(1.1);
        scaleUpTransition.setToY(1.1);
        scaleUpTransition.setInterpolator(Interpolator.EASE_OUT);
        scaleUpTransition.setDelay(Duration.seconds(delay));

        ScaleTransition scaleDownTransition = new ScaleTransition(Duration.seconds(0.15), studentsAddView);
        scaleDownTransition.setFromX(1.1);
        scaleDownTransition.setFromY(1.1);
        scaleDownTransition.setToX(1);
        scaleDownTransition.setToY(1);
        scaleDownTransition.setInterpolator(Interpolator.EASE_OUT);

        SequentialTransition scaleTransition = new SequentialTransition(scaleUpTransition, scaleDownTransition);

        fadeTransition.play();
        scaleTransition.play();

        studentsAddView.setVisible(true);
    }
}
