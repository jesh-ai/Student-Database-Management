package com.plm.studentdb.views;

import com.plm.studentdb.database.DBFind;
import com.plm.studentdb.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class ViewStudents {
    @FXML public TableView<Student> tbvStudents = new TableView<>();
    @FXML public Parent studentsInformation;
    @FXML public Label btnViewStudentsAdd;
    @FXML public TextField txfStudentsSearch;

    @FXML public StudentsInformation studentsInformationController;

    public static ObservableList<Student> studentsListTable = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Table
        tbvStudents.setEditable(true);

        TableColumn<Student, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setPrefWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        idColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.17));

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setPrefWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        nameColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.25));

        TableColumn<Student, String> courseColumn = new TableColumn<>("Program");
        courseColumn.setPrefWidth(100);
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("ProgramID"));
        courseColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.24));

        TableColumn<Student, Integer> yearColumn = new TableColumn<>("Year");
        yearColumn.setPrefWidth(100);
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.12));

        TableColumn<Student, String> statusColumn = new TableColumn<>("Block");
        statusColumn.setPrefWidth(100);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("block"));
        statusColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.15));

        tbvStudents.getColumns().addAll(idColumn, nameColumn, courseColumn, yearColumn, statusColumn);
        tbvStudents.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        tbvStudents.getColumns().forEach(studentTableColumn -> {
            studentTableColumn.setReorderable(false);
            studentTableColumn.setResizable(false);
        });
        tbvStudents.getSelectionModel().clearSelection();
        tbvStudents.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tbvStudents.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Student student = row.getItem();
                    studentsInformationController.showForms(student, 0.2, false, false);
                }
            });
            return row;
        });

        tbvStudents.setItems(studentsListTable);
        getData();
    }

    public void getData() {
        studentsListTable.setAll(DBFind.findStudents(null, null, null, null, null, null, null, null));
    }

    @FXML private void search(KeyEvent event) {
        if (event.getCode() != KeyCode.ENTER) return;
        String input = txfStudentsSearch.getText();
        List<Student> students = DBFind.findStudents(input);
        studentsListTable.setAll(students);
    }

    @FXML private void clearSearch() {
        txfStudentsSearch.setText("");
        getData();
    }

    @FXML private void showInformation() {
        studentsInformationController.showForms(null, 0.2, true, false);
    }

}
