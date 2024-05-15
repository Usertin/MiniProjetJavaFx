package com.gestion.gestionpatients.data;

import com.gestion.gestionpatients.data.models.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientUtil {
    public static ObservableList<Patient> getPatients() {
        ObservableList<Patient> liste = FXCollections.observableArrayList();
        try {
            Connection conn = connectToDB.getConnection();
            String sql = "select * from patient order by cin";
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            while(res.next()) {
                Patient patient =new Patient(res.getString("cin"),res.getString("nom"), res.getString("prenom"), res.getString("sexe"), res.getString("tel"));
                liste.add(patient);
            }
        }catch(SQLException e) {
            System.out.println("this exception occured in the getPatients() method :"+e.getMessage());
        }
        return liste;
    }
}
