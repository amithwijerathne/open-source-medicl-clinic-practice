package lk.ijse.dep9.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.dep9.clinic.security.SecurityContexHolder;
import lk.ijse.dep9.clinic.security.User;
import lk.ijse.dep9.clinic.security.UserRole;

import java.io.IOException;
import java.sql.*;

public class LoginFormController {

    public TextField txtUserName;
    public TextField txtPassword;
    public Button btnLogIn;

    public void initialize(){
        btnLogIn.setDefaultButton(true);

    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws ClassNotFoundException, IOException {
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        if (username.isBlank()) {
            new Alert(Alert.AlertType.ERROR, "username cant be empty").show();
            txtUserName.requestFocus();
            txtUserName.selectAll();
            return;
        } else if (password.isBlank()) {
            new Alert(Alert.AlertType.ERROR, "password cant be empty").show();
            txtPassword.requestFocus();
            txtPassword.selectAll();
            return;
        }

//        else if (!username.matches("^[A-Za-z0-9] + $")) {
//            new Alert(Alert.AlertType.ERROR, "invalid login credentials ").show();
//            txtUserName.requestFocus();
//            txtUserName.selectAll();
//        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_clinic", "root", "ch1993@AM");){
            String sql = "SELECT role FROM User WHERE username = '%s' AND password = '%s'";
            sql = String.format(sql,username,password);

            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery(sql);
            if (rst.next()){
                String role = rst.getString("role");
                SecurityContexHolder.setPrinciple(new User(username, UserRole.valueOf(role)));
                System.out.println(role);
                Scene scene = null;
                switch (role){
                    case "Admin":
                        scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/AdminForm.fxml")));
                        break;
                    case "Doctor":
                        scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/DoctorDashboardForm.fxml")));
                        break;
                    default:
                        scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/ReceptionistDashboardForm.fxml")));
                }
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                stage.centerOnScreen();
                btnLogIn.getScene().getWindow().hide();

            }else {
                new Alert(Alert.AlertType.ERROR, "invalid login credentials ").show();
                txtUserName.requestFocus();
                txtUserName.selectAll();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"failed to connect with the database").show();
        }

    }

//    public boolean isUserName(String userName){
//        char[] chars = userName.toCharArray();
//        for (char aChar : chars) {
//            if (Character.isLetter(aChar)){
//                return false;
//            }
//        }
//        return true;
//    }
}
