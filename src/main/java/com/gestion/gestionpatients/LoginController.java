package com.gestion.gestionpatients;
import com.gestion.gestionpatients.data.PersonnelUtil;
import com.gestion.gestionpatients.data.models.Personnel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField tfLogin, tfPassword;
    @FXML
    private Button btnLogin, btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ecouteurs();
    }

    public void ecouteurs() {
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    handleSubmit();
                }catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleCancel();
            }
        });
    }
    public void handleSubmit() throws IOException {
        if(tfLogin.getText().isEmpty() || tfPassword.getText().isEmpty()) {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("erreur");
            diag.setHeaderText(null);
            diag.setContentText("Champs login et password sont requises");
            diag.showAndWait();
        }
        else {
            ObservableList<Personnel> liste= PersonnelUtil.getPersonnelByloginPassword(tfLogin.getText(), tfPassword.getText());
            liste= PersonnelUtil.getPersonnelByloginPassword(tfLogin.getText(), tfPassword.getText());
            if (liste.isEmpty()) {
                Alert diag = new Alert(Alert.AlertType.ERROR);
                diag.setTitle("erreur");
                diag.setHeaderText(null);
                diag.setContentText("Personnel untrouvable");
                diag.showAndWait();
            }
            else {
                //redirect to home??
                GestionPatientsApplication app = new GestionPatientsApplication();
                app.changeScene("gestion-patients-view.fxml");
            }
        }
    }
    public void handleCancel() {
        tfLogin.setText("");
        tfPassword.setText("");
    }
}
