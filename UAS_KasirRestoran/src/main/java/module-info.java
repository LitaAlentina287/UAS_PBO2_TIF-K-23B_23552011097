module com.mycompany.kasirrestoran {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
   
    opens com.mycompany.kasirrestoran to javafx.fxml;
    exports com.mycompany.kasirrestoran;
}
