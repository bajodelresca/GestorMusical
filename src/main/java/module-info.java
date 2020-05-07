module com.mycompany.mavenproject1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.mavenproject1.controller to javafx.fxml;
    exports com.mycompany.mavenproject1;
}