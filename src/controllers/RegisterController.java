package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import utils.ConnectionUtil;

public class RegisterController implements Initializable {

    @FXML private TextField txtFirstname;
    @FXML private TextField txtLastname;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private ComboBox<String> txtGender;
    @FXML private DatePicker txtDOB;
    @FXML private Button btnRegister;
    @FXML private Label lblStatus;

    private Connection connection;
    private PreparedStatement preparedStatement;

    public RegisterController() {
        connection = ConnectionUtil.conDB();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtGender.setItems(FXCollections.observableArrayList("Masculino", "Feminino", "Outro"));
        txtGender.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleRegister(MouseEvent event) {
        if (txtFirstname.getText().isEmpty() || txtLastname.getText().isEmpty() ||
            txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty() || txtDOB.getValue() == null) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Preencha todos os campos!");
            return;
        }

        try {
            String sql = "INSERT INTO wip_users (firstname, lastname, email, password, gender, dob) VALUES (?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, txtFirstname.getText());
            preparedStatement.setString(2, txtLastname.getText());
            preparedStatement.setString(3, txtEmail.getText());
            preparedStatement.setString(4, txtPassword.getText());
            preparedStatement.setString(5, txtGender.getValue());
            preparedStatement.setString(6, txtDOB.getValue().toString());

            preparedStatement.executeUpdate();

            lblStatus.setTextFill(Color.GREEN);
            lblStatus.setText("Usuário cadastrado com sucesso!");

            clearFields();

        } catch (SQLException e) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText(e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtFirstname.clear();
        txtLastname.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtDOB.setValue(null);
        txtGender.getSelectionModel().selectFirst();
    }
}
