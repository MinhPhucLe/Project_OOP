package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TableView<NksChar> table;

    @FXML
    private TableColumn<NksChar, String> nameColumn;

    @FXML
    private TableColumn<NksChar, String> descColumn;

    @FXML
    private TableColumn<NksChar, String> namSinhColumn;

    @FXML
    private TableColumn<NksChar, String> namMatColumn;

    private ObservableList<NksChar> nksCharList;
    private static final String FILE_NAME = "students.json";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*studentList = FXCollections.observableArrayList (
            new Student(1, "Nguyen", "nguyen@gmail.com", 21),
            new Student(2, "Thang", "thang@gmail.com",  20)
        );*/
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("src/main/java/json/nkschar.json"); // Replace with the actual file name or path

            System.out.println(file.getAbsolutePath());
            NksChar[] nksChars = objectMapper.readValue(file, NksChar[].class);
            nksCharList = FXCollections.observableArrayList(nksChars);

            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            descColumn.setCellValueFactory(new PropertyValueFactory<>("desc"));
            namSinhColumn.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
            namMatColumn.setCellValueFactory(new PropertyValueFactory<>("namMat"));

            table.setItems(nksCharList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("desc"));
        namSinhColumn.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
        namMatColumn.setCellValueFactory(new PropertyValueFactory<>("namMat"));
        table.setItems(studentList);*/
    }
}


