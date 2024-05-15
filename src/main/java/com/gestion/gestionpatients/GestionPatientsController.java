package com.gestion.gestionpatients;

import com.gestion.gestionpatients.data.PatientUtil;
import com.gestion.gestionpatients.data.models.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GestionPatientsController implements Initializable {

    @FXML
    private Button btnImprimer;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    @FXML
    private TableView<Patient> tabPatients;

    @FXML
    private ToggleGroup radioBtnGroup;

    @FXML
    private TextField tfCin;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfPrenom;

    @FXML
    private TextField tfTel;

    @FXML
    private RadioButton radioF;

    @FXML
    private RadioButton radioM;

    @FXML
    private TableColumn<Patient, String> colNom;
    @FXML
    private TableColumn<Patient, String> colPrenom;
    @FXML
    private TableColumn<Patient, String> colTel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        remplirTab();
        ecouteurs();
    }

    public void ecouteurs() {
        btnImprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("ok");
            }
        });
        btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("ok");
            }
        });
        btnModifier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("ok");
            }
        });
        btnSupprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSelected();
            }
        });
//        tabPatients.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//
////                if (event.getClickCount() == 2)
////                    afficherPatient();
//            }
//        });
    }

    private void showSelected() {
        TablePosition position = tabPatients.getSelectionModel().getSelectedCells().get(0);
        System.out.println(position);
        if (position.getRow() >= 0) {
            Patient patientSelectionne = tabPatients.getItems().get(position.getRow());
            tfCin.setText(patientSelectionne.getCin());
            tfNom.setText(patientSelectionne.getNom());
            tfPrenom.setText(patientSelectionne.getPrenom());
            tfTel.setText(patientSelectionne.getTel());
            if(patientSelectionne.getSexe().equals("masculin")) {
                radioM.setSelected(true);
            }else {
                radioF.setSelected(true);
            }
        }
    }

    public void remplirTab() {
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));

        ObservableList<Patient> listePatients= PatientUtil.getPatients();
        System.out.println(listePatients);
        tabPatients.setItems(listePatients);
    }
}