package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.DBConnector;
import model.User;

import java.net.URL;
import java.sql.*;
import java.util.Map;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private Stage primaryStage;

    private Map<Button, Pane> panes;

    @FXML
    private Pane pn_home, pn_login, pn_signin;

    @FXML
    private TextField tb_login_email, tb_login_pass, tb_signin_email, tb_signin_pass, tb_signin_pass_r;
    
    @FXML
    private Button btn_login, btn_signin, btn_newprofil, btn_goback;

    @FXML
    private void buttonAction(ActionEvent event) {
        for (Map.Entry<Button, Pane> entry : panes.entrySet()) {
            if (event.getSource() == entry.getKey()) {
                entry.getValue().setVisible(true);
            } else {
                entry.getValue().setVisible(false);
            }
        }
    }

    @FXML
    private void signin(ActionEvent event) {
        pn_login.setVisible(false);
        pn_signin.setVisible(true);
    }

    @FXML
    private void login() {
        try {
            String pass = tb_login_pass.getText();
            String email = tb_login_email.getText();

            try {
                Connection con = DBConnector.getConnection();
                Statement statement = con.createStatement();
                ResultSet rs;

                String query = "SELECT * FROM uspProject.user WHERE user.name LIKE '" + email +"' AND user.password LIKE '" + pass +"' ;";
                rs = statement.executeQuery(query);

                if(rs.next()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/homepage.fxml"));
                    Parent root = loader.load();

                    HomeController homeController = new HomeController();
                    loader.setController(homeController);

                    primaryStage.setScene(new Scene(root));
                    primaryStage.show();
                }else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Wrong email or password !! ");
                    alert.showAndWait();
                }

            }catch (SQLException e){

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void goBack(ActionEvent event) {
        pn_signin.setVisible(false);
        pn_login.setVisible(true);
    }


    public void initialize(URL location, ResourceBundle resources) {
        btn_newprofil.setOnAction(event -> {

            String email = tb_signin_email.getText();
            String pass = tb_signin_pass.getText();
            String pass_r = tb_signin_pass_r.getText();

            if (pass.equals(pass_r) && pass.length()>8) {
                Connection con = DBConnector.getConnection();
                try {
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.user VALUES (?, ?)");
                    st.setString(1, email);
                    st.setString(2, pass);
                    st.executeUpdate();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("You create successfully account !");
                    alert.showAndWait();

                }catch (SQLException e){

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error ");
                    alert.showAndWait();

                    e.printStackTrace();
                }

            }
            pn_signin.setVisible(false);
            pn_login.setVisible(true);
        });

    }


}
