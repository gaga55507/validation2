package com.edencoding.controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import com.edencoding.E1204;
import com.edencoding.E1224;
import com.edencoding.SimpleTesting;
import com.edencoding.pdfs;
public class MainViewController implements Initializable {
    private Button handleExitButtonClicked2;
      @FXML
    private Button GEN1;

    @FXML
    private Button GEN2;
    @FXML
    public Label TOTAL;
    @FXML
    public Label ENCOURS;
    @FXML
    private Button SIGNER;
    @FXML
    public ProgressBar Progressbar;
    @FXML
    private Button SUPPRIMER;
    String selectedOption;
    @FXML
    private ChoiceBox<String> USER;
    private static MainViewController instance;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        String s1[] = { "M07GBLA", "M07JCHA", "M07GVAC","M07LVAL" }; 
        USER.getItems().addAll(s1);
        System.out.println("test");
        USER.setOnAction(event -> {
            selectedOption = USER.getValue();
            System.out.println("L'utilisateur a sélectionné l'option : " + selectedOption);
        });
     
    }

    @FXML
    private void handleExitButtonClicked(ActionEvent event) {
        Platform.exit();
        event.consume();
    }
    @FXML
    void ACTGEN1(ActionEvent event) throws InvalidPasswordException, IOException {
        Progressbar.setProgress(0);
     
    E1204.main(null);

    }

    
   
    @FXML
    void ACTGEN2(ActionEvent event) throws InvalidPasswordException, IOException {
    Progressbar.setProgress(0);
    E1224.main(null);
    }

    @FXML
    
    void ACTSIGNE(ActionEvent event) {
        if (selectedOption != null){
        pdfs.main(selectedOption);
        }
        
        
    }

    @FXML
    void ACTSUPP(ActionEvent event) {
        SimpleTesting.main();
    }
    @FXML
    private void handleExitButtonClicked2(ActionEvent event)  {
        Node source = (Node)  event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
        event.consume();
    }
    @FXML
    private void handleGitButtonClicked(ActionEvent event) {
        new Application() {
            @Override
            public void start(Stage stage) {
            }
        }.getHostServices().showDocument("https://github.com/edencoding/javafx-ui/");
        event.consume();
    }
    public ProgressBar getProgressBar() {
        return Progressbar;
    }
       public Label getTotal() {
        return TOTAL;
    }
    public Label getEncours() {
        return ENCOURS;
    }

    public static MainViewController getInstance() {
        return instance;
    }
    public static class Order {
        IntegerProperty id;
        StringProperty state;
        StringProperty city;

        public Order(Integer id, String state, String city) {
            this.id = new SimpleIntegerProperty(id);
            this.state = new SimpleStringProperty(state);
            this.city = new SimpleStringProperty(city);
        }

        public int getId() {
            return id.get();
        }

        public void setId(int id) {
            this.id.set(id);
        }

        public IntegerProperty idProperty() {
            return id;
        }

        public String getState() {
            return state.get();
        }

        public void setState(String state) {
            this.state.set(state);
        }

        public StringProperty stateProperty() {
            return state;
        }

        public String getCity() {
            return city.get();
        }

        public void setCity(String city) {
            this.city.set(city);
        }

        public StringProperty cityProperty() {
            return city;
        }
    }
}
