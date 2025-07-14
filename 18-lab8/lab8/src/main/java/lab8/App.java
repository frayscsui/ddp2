package lab8;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    ChoiceBox<String> genderChoiceBox = new ChoiceBox<>();
    private TextField beratBadanTextField = new TextField();
    private TextField tinggiBadanTextField = new TextField();
    private Label scoreLabel = new Label("0.00");
    private Label scoreDescLabel = new Label("BMI Calculator");
    private Label idealWeightLabel = new Label("Berat Badan Ideal: Belum diketahui");

    @Override
    public void start(Stage stage) throws IOException {
        // Tambahkan input gender beserta labelnya
        Label genderLabel = new Label("Gender");
        // Atur daftar gender dan default valuenya
        genderChoiceBox.getItems().addAll("Laki-Laki", "Perempuan");
        genderChoiceBox.setValue("Laki-Laki");
        // Mengatur lebar maks supaya memenuhi gridpane
        genderChoiceBox.setMaxWidth(Double.POSITIVE_INFINITY);
        // Tambahkan event listener untuk auto-compute
        genderChoiceBox.setOnAction(event -> {
            this.calculateBMI(genderChoiceBox.getValue(), beratBadanTextField.textProperty().get(), tinggiBadanTextField.textProperty().get());
        });
        
        // Tambahkan input berat badan beserta labelnya
        Label beratBadanLabel = new Label("Berat Badan (kg)");
        // Tambahkan event listener untuk auto-compute
        beratBadanTextField.textProperty().addListener((observable, oldVal, newVal) -> {
            this.calculateBMI(genderChoiceBox.getValue(), newVal, tinggiBadanTextField.textProperty().get());
        });
        
        // Tambahkan input tinggi badan beserta labelnya
        Label tinggiBadanLabel = new Label("Tinggi Badan (cm)");
        // Tambahkan event listener untuk auto-compute
        tinggiBadanTextField.textProperty().addListener((observable, oldVal, newVal) -> {
            this.calculateBMI(genderChoiceBox.getValue(), beratBadanTextField.textProperty().get(), newVal);
        });
        
        // Inisiasi GridPane untuk mengatur layout control input
        GridPane inputGridPane = new GridPane();
        inputGridPane.setPadding(new Insets(10));   // Atur padding gridpane
        inputGridPane.setAlignment(Pos.CENTER);     // Atur alignment gridpane
        inputGridPane.setVgap(5);                   // Atur jarak vertikal sebanyak 5px
        inputGridPane.setHgap(5);                   // Atur jarak horizontal sebanyak 5px
        // Atur lebar kolom gridpane
        ColumnConstraints firstConstraints = new ColumnConstraints();
        firstConstraints.setPercentWidth(40);
        ColumnConstraints secondConstraints = new ColumnConstraints();
        secondConstraints.setPercentWidth(30);
        ColumnConstraints thirrdConstraints = new ColumnConstraints();
        secondConstraints.setPercentWidth(30);
        inputGridPane.getColumnConstraints().addAll(firstConstraints, secondConstraints, thirrdConstraints);
        // Tambahkan input gender
        inputGridPane.add(genderLabel, 0, 0);
        inputGridPane.add(genderChoiceBox, 0, 1);
        // Tambahkan input berat badan
        inputGridPane.add(beratBadanLabel, 1, 0);
        inputGridPane.add(beratBadanTextField, 1, 1);
        // Tambahkan input tinggi badan
        inputGridPane.add(tinggiBadanLabel, 2, 0);
        inputGridPane.add(tinggiBadanTextField, 2, 1);

        // Atur label ouput style menggunakan css
        scoreLabel.setId("number");
        scoreDescLabel.setId("label");
        
        // Inisiasi VBox untuk mengatur layout output
        VBox outputVBox = new VBox();
        outputVBox.setPadding(new Insets(10));      // Atur padding vbox
        outputVBox.setAlignment(Pos.CENTER);        // Atur alignment tiap child vbox
        outputVBox.getChildren().addAll(scoreLabel, scoreDescLabel, idealWeightLabel);

        // Inisiasi VBox untuk keseluruhan layout program
        VBox mainVBox = new VBox();
        mainVBox.getChildren().addAll(inputGridPane, outputVBox);
        // Tambhakan Vbox kedalam Scene
        Scene mainScene = new Scene(mainVBox, 500, 150);
        // Implementasikan stylesheets css 
        mainScene.getStylesheets().add("/styles.css");

        // Main application
        stage.setTitle("BMI Calculator");
        stage.setResizable(false);
        stage.setScene(mainScene);
        stage.show();
    }

    private void calculateBMI(String gender, String strWeight, String strHeight) {
        // Inisiasi berat dan tinggi badan
        double weight;
        double height;
        
        // Konversikan ketipe double atau print error ke label output
        try {
            weight = Double.parseDouble(strWeight);
            height = Double.parseDouble(strHeight);
        } catch (NumberFormatException err) {
            scoreLabel.setText("0.00");
            scoreDescLabel.setText("Input tidak valid!");
            scoreDescLabel.setTextFill(Color.BLACK);
            idealWeightLabel.setText("Berat Badan Ideal: Belum diketahui");
            return;
        }
        
        // Hitung nilai BMI dan print ke label output
        double bmiValue = (weight*10000)/(height * height);
        scoreLabel.setText(String.format("%.2f", bmiValue));
        
        // Atur deskripsi tipe BMI berdasarkan nilai BMI
        if (bmiValue < 18.5d) {
            scoreDescLabel.setText("Underweight");
            scoreDescLabel.setTextFill(Color.web("#21618C"));
        } else if (bmiValue >= 18.5d && bmiValue < 25d) {
            scoreDescLabel.setText("Normal");
            scoreDescLabel.setTextFill(Color.web("#1D8348"));
        } else if (bmiValue >= 25d && bmiValue < 30d) {
            scoreDescLabel.setText("Overweight");
            scoreDescLabel.setTextFill(Color.web("#9A7D0A"));
        } else if (bmiValue >= 30d) {
            scoreDescLabel.setText("Obese");
            scoreDescLabel.setTextFill(Color.web("#943126"));
        } else {
            scoreDescLabel.setText("Impossible");
            scoreDescLabel.setTextFill(Color.web("#34495E"));
        }

        // Hitung nilai berat badan ideal berdasarkan gender dan print ke label output
        if (gender == "Laki-Laki") {
            double idealWeight = (height-100) * 0.9d;
            idealWeightLabel.setText(String.format("Berat Badan Ideal: %.2fKG", idealWeight));
        } else if (gender == "Perempuan") {
            double idealWeight = (height-100) * 0.85d;
            idealWeightLabel.setText(String.format("Berat Badan Ideal: %.2fKG", idealWeight));
        } else {
            idealWeightLabel.setText("YOU SHOULD NOT EXISTED!");
        }
    }

    public static void main(String[] args) {
        // Jalankan programnya
        launch();
    }

}