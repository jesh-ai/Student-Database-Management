module com.plm.studentdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;
    requires com.dlsc.pdfviewfx;
    requires jasperreports;


    opens com.plm.studentdb to javafx.fxml;
    exports com.plm.studentdb;
    exports com.plm.studentdb.views;
    opens com.plm.studentdb.views to javafx.fxml;
    opens com.plm.studentdb.models to javafx.base;
    exports com.plm.studentdb.models;
}