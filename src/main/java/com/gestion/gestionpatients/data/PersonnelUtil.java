package com.gestion.gestionpatients.data;

import com.gestion.gestionpatients.data.models.Personnel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.sql.*;

public class PersonnelUtil {
    private static String dernierTitreErreur = "";
    private static String dernierMessageErreur = "";

    public static String getDernierTitreErreur() {
        return dernierTitreErreur;
    }

    public static void setDernierTitreErreur(String dernierTitreErreur) {
        PersonnelUtil.dernierTitreErreur = dernierTitreErreur;
    }

    public static String getDernierMessageErreur() {
        return dernierMessageErreur;
    }

    public static void setDernierMessageErreur(String dernierMessageErreur) {
        PersonnelUtil.dernierMessageErreur = dernierMessageErreur;
    }

    public static ObservableList<Personnel> getPersonnels() {
        ObservableList<Personnel> liste = FXCollections.observableArrayList();
        try {
            Connection conn = connectToDB.getConnection();
            String sql = "select * from personnel order by cin";
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                Personnel liv = new Personnel(res.getString("cin"), res.getString("nom"), res.getString("prenom"), res.getString("login"), res.getString("password"), res.getString("fonction"));
                liste.add(liv);
            }
        } catch (SQLException e) {
            System.out.println("this exception occured in the getPersonnels() method :" + e.getMessage());
        }
        //System.out.println(liste);
        return liste;
    }

    public static ObservableList<Personnel> getPersonnelByloginPassword(String login, String password) {
        ObservableList<Personnel> liste = FXCollections.observableArrayList();
        try {
            Connection conn = connectToDB.getConnection();
            String sql = "select * from personnel where login= ? and password= ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,login);
            stm.setString(2,password);
            ResultSet res = stm.executeQuery();
            while(res.next()) {
                Personnel personnel = new Personnel(res.getString("cin"),res.getString("nom"),res.getString("prenom"),res.getString("login"),res.getString("password"),res.getString("fonction"));
                liste.add(personnel);
            }
            System.out.println(liste);
        }catch (SQLException e) {
            System.out.println("this exception occured in the getPersonnelByLoginPassword() method: "+e.getMessage());
        }
        return liste;
    }

    public static boolean ajouterPersonnel(String cin, String nom, String prenom, String login, String password, String fonction) {
        try {
            Connection conn = connectToDB.getConnection();
            String sql = "insert into personnel values (?,?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,cin);
            pstm.setString(2,nom);
            pstm.setString(3,prenom);
            pstm.setString(4,login);
            pstm.setString(5,password);
            pstm.setString(6,fonction);
            pstm.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.out.println("ajouterPersonnel(): "+e.getMessage());
        }
        return false;
    }

//    public static boolean modifierPersonnel(int id, String titre, String couleur, int nbPage) {
//        try {
//            Connection conn = getConnection();
//            String sql = "update livre set titre= ?, couleur= ?, nbPages= ? where id= ?";
//            PreparedStatement pstm= conn.prepareStatement(sql);
//            pstm.setString(1, titre);
//            pstm.setString(2, couleur);
//            pstm.setInt(3, nbPage);
//            pstm.setInt(4, id);
//            pstm.executeUpdate();
//            return true;
//        } catch(SQLException e) {
//            System.out.println("exception occured in the modifierLivre() method: "+e.getMessage());
//        }
//        return false;
//    }

//    public static ObservableList<Livre> rechercherLivres(String titreR) {
//        ObservableList<Livre> liste = FXCollections.observableArrayList();
//        try {
//            Connection conn = getConnection();
//            String sql = "select * from livre where titre like ? order by id";
//            PreparedStatement pstm = conn.prepareStatement(sql);
//            pstm.setString(1,"%"+titreR+"%");
//            ResultSet res = pstm.executeQuery();
//            while(res.next()) {
//                Livre liv = new Livre(res.getInt("id"),res.getString("titre"), Color.web(res.getString("couleur")), res.getInt("nbPages"));
//                liste.add(liv);
//            }
//            return liste;
//        }catch(SQLException e) {
//            System.out.println("error occured in the rechercherLivres() method: "+e.getMessage());
//        }
//        return liste;
//    }
//
//    public static boolean supprimerTout() {
//        try {
//            Connection conn = getConnection();
//            String sql = "delete from livre";
//            Statement stm = conn.createStatement();
//            stm.executeUpdate(sql);
//            return true;
//        } catch(SQLException e) {
//            System.out.println("exception occured in the supprimerTous() method: "+e.getMessage());
//        }
//        return false;
//    }
//
//    public static boolean supprimerLivre(int id) {
//        try {
//            Connection conn = getConnection();
//            String sql = "delete from livre where id= ?";
//            PreparedStatement pstm = conn.prepareStatement(sql);
//            pstm.setInt(1,id);
//            pstm.executeUpdate();
//            return true;
//        } catch(SQLException e) {
//            System.out.println("exception occured in the supprimerLivre() method: "+e.getMessage());
//        }
//        return false;
//    }
}
