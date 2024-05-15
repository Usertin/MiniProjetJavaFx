module com.gestion.gestionpatients {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.gestion.gestionpatients.data.models to javafx.base;
    opens com.gestion.gestionpatients to javafx.fxml;
    exports com.gestion.gestionpatients;
}