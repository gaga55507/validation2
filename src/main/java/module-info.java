module javafxui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires org.apache.poi.ooxml;
    requires org.apache.poi.poi;
    requires org.apache.commons.lang3;
    requires itextpdf;
    requires pdfbox;
    
    opens com.edencoding to javafx.fxml;
    opens com.edencoding.controllers to javafx.fxml;
   requires javafx.base;
   
    exports com.edencoding;
}