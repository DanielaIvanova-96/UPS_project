package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.DBConnector;
import model.Projection;


import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class HomeController implements Initializable {

    @FXML
    private Pane pn_homepage, pn_ticket;

    @FXML
    private TextField tb_standart, tb_student, tb_kid;

    @FXML
    private Button btn_standart_plus, btn_standart_minus, btn_student_plus, btn_student_minus, btn_kid_plus, btn_kid_minus, btn_back , btn_config, btn_popular;

    @FXML Button btn_dragon_11, btn_dragon_15, btn_lion_10, btn_lion_15 , btn_endgame_12, btn_endgame_18;

    @FXML
    private Button btn_1_1 = new Button();
    @FXML
    private Button btn_1_2 = new Button();
    @FXML
    private Button btn_1_3 = new Button();
    @FXML
    private Button btn_1_4 = new Button();
    @FXML
    private Button btn_1_5 = new Button();
    @FXML
    private Button btn_1_6 = new Button();
    @FXML
    private Button btn_2_1 = new Button();
    @FXML
    private Button btn_2_2 = new Button();
    @FXML
    private Button btn_2_3 = new Button();
    @FXML
    private Button btn_2_4 = new Button();
    @FXML
    private Button btn_2_5 = new Button();
    @FXML
    private Button btn_2_6 = new Button();
    @FXML
    private Button btn_3_1 = new Button();
    @FXML
    private Button btn_3_2 = new Button();
    @FXML
    private Button btn_3_3 = new Button();
    @FXML
    private Button btn_3_4 = new Button();
    @FXML
    private Button btn_3_5 = new Button();
    @FXML
    private Button btn_3_6 = new Button();

    @FXML
    public void buttonAction(){
        btn_standart_plus.setOnAction(event -> {
            int value = Integer.parseInt(tb_standart.getText());
            if(value==0 || value>0) {
                value += 1;
            }
            tb_standart.setText(String.valueOf(value));
        });

        btn_standart_minus.setOnAction(event -> {
            int value = Integer.parseInt(tb_standart.getText());
            if (value > 0) {
                value = value - 1;
            }
            if(value == 0){
                value=value;
            }
            tb_standart.setText(String.valueOf(value));
        });

        btn_student_plus.setOnAction(event -> {
            int value = Integer.parseInt(tb_student.getText());
            if(value==0 || value>0) {
                value += 1;
            }
            tb_student.setText(String.valueOf(value));
        });

        btn_student_minus.setOnAction(event -> {
            int value = Integer.parseInt(tb_student.getText());
            if (value > 0) {
                value = value - 1;
            }
            if(value == 0){
                value=value;
            }
            tb_student.setText(String.valueOf(value));
        });

        btn_kid_plus.setOnAction(event -> {
            int value = Integer.parseInt(tb_kid.getText());
            if(value==0 || value>0) {
                value += 1;
            }
            tb_kid.setText(String.valueOf(value));
        });

        btn_kid_minus.setOnAction(event -> {
            int value = Integer.parseInt(tb_kid.getText());
            if (value > 0) {
                value = value - 1;
            }
            if(value == 0){
                value=value;
            }
            tb_kid.setText(String.valueOf(value));
        });

    }

    @FXML
    private List<Button> list = new ArrayList<Button>();

    Projection proj = new Projection();

    @FXML
    public void unableButton(Button button){
        if(button.isDisable()) {
            button.setDisable(false);
        } else {
            button.setDisable(true);
            list.add(button);
        }
    }

    public void initialize(URL location, ResourceBundle resources){
        btn_1_1.setOnAction(event -> unableButton(btn_1_1));
        btn_1_2.setOnAction(event -> unableButton(btn_1_2));
        btn_1_3.setOnAction(event -> unableButton(btn_1_3));
        btn_1_4.setOnAction(event -> unableButton(btn_1_4));
        btn_1_5.setOnAction(event -> unableButton(btn_1_5));
        btn_1_6.setOnAction(event -> unableButton(btn_1_6));

        btn_2_1.setOnAction(event -> unableButton(btn_2_1));
        btn_2_2.setOnAction(event -> unableButton(btn_2_2));
        btn_2_3.setOnAction(event -> unableButton(btn_2_3));
        btn_2_4.setOnAction(event -> unableButton(btn_2_4));
        btn_2_5.setOnAction(event -> unableButton(btn_2_5));
        btn_2_6.setOnAction(event -> unableButton(btn_2_6));

        btn_3_1.setOnAction(event -> unableButton(btn_3_1));
        btn_3_2.setOnAction(event -> unableButton(btn_3_2));
        btn_3_3.setOnAction(event -> unableButton(btn_3_3));
        btn_3_4.setOnAction(event -> unableButton(btn_3_4));
        btn_3_5.setOnAction(event -> unableButton(btn_3_5));
        btn_3_6.setOnAction(event -> unableButton(btn_3_6));

        btn_back.setOnAction(event -> {
            pn_ticket.setVisible(false);
            pn_homepage.setVisible(true);
        });

        btn_dragon_11.setOnAction(event -> {

            pn_homepage.setVisible(false);
            pn_ticket.setVisible(true);

            proj.setId_movie(1);
            proj.setId_projection(1);

            reset();
            list.clear();

            try{
                Connection con = DBConnector.getConnection();
                Statement st = con.createStatement();
                ResultSet rs;
                String query = "SELECT seat.row, seat.colum FROM uspProject.seat " +
                        "JOIN projection on seat.id_projection = projection.projection_id " +
                        "WHERE projection.movie_id = " + proj.getId_movie() +" and seat.id_projection = " + proj.getId_projection() +";";
                rs = st.executeQuery(query);

                while(rs.next()){
                    int r = rs.getInt("row");
                    int c = rs.getInt("colum");

                    if(r == 1 && c == 1){
                        btn_1_1.setDisable(true);
                    }
                    if(r == 1 && c == 2){
                        btn_1_2.setDisable(true);
                    }
                    if(r == 1 && c == 3){
                        btn_1_3.setDisable(true);
                    }
                    if(r == 1 && c == 4){
                        btn_1_4.setDisable(true);
                    }
                    if(r == 1 && c == 5){
                        btn_1_5.setDisable(true);
                    }
                    if(r == 1 && c == 6){
                        btn_1_6.setDisable(true);
                    }
                    if(r == 2 && c == 1){
                        btn_2_1.setDisable(true);
                    }
                    if(r == 2 && c == 2){
                        btn_2_2.setDisable(true);
                    }
                    if(r == 2 && c == 3){
                        btn_2_3.setDisable(true);
                    }
                    if(r == 2 && c == 4){
                        btn_2_4.setDisable(true);
                    }
                    if(r == 2 && c == 5){
                        btn_2_5.setDisable(true);
                    }
                    if(r == 2 && c == 6){
                        btn_2_6.setDisable(true);
                    }
                    if(r == 3 && c == 1){
                        btn_3_1.setDisable(true);
                    }
                    if(r == 3 && c == 2){
                        btn_3_2.setDisable(true);
                    }
                    if(r == 3 && c == 3){
                        btn_3_3.setDisable(true);
                    }
                    if(r == 3 && c == 4){
                        btn_3_4.setDisable(true);
                    }
                    if(r == 3 && c == 5){
                        btn_3_5.setDisable(true);
                    }
                    if(r == 3 && c == 6){
                        btn_3_6.setDisable(true);
                    }
                }
            }catch (SQLException e){
            }

            btn_config.setOnAction(event1 -> {
                proj.setId_projection(1);
                config();
            });

        });

        btn_dragon_15.setOnAction(event -> {
            pn_homepage.setVisible(false);
            pn_ticket.setVisible(true);

            proj.setId_movie(1);
            proj.setId_projection(2);

            reset();
            list.clear();

            try{
                Connection con = DBConnector.getConnection();
                Statement st = con.createStatement();
                ResultSet rs;
                String query = "SELECT seat.row, seat.colum FROM uspProject.seat " +
                        "JOIN projection on seat.id_projection = projection.projection_id " +
                        "WHERE projection.movie_id = " + proj.getId_movie() +" and seat.id_projection = " + proj.getId_projection() + ";";
                rs = st.executeQuery(query);

                while (rs.next()){
                    int r = rs.getInt("row");
                    int c = rs.getInt("colum");

                    if(r == 1 && c == 1){
                        btn_1_1.setDisable(true);
                    }
                    if(r == 1 && c == 2){
                        btn_1_2.setDisable(true);
                    }
                    if(r == 1 && c == 3){
                        btn_1_3.setDisable(true);
                    }
                    if(r == 1 && c == 4){
                        btn_1_4.setDisable(true);
                    }
                    if(r == 1 && c == 5){
                        btn_1_5.setDisable(true);
                    }
                    if(r == 1 && c == 6){
                        btn_1_6.setDisable(true);
                    }
                    if(r == 2 && c == 1){
                        btn_2_1.setDisable(true);
                    }
                    if(r == 2 && c == 2){
                        btn_2_2.setDisable(true);
                    }
                    if(r == 2 && c == 3){
                        btn_2_3.setDisable(true);
                    }
                    if(r == 2 && c == 4){
                        btn_2_4.setDisable(true);
                    }
                    if(r == 2 && c == 5){
                        btn_2_5.setDisable(true);
                    }
                    if(r == 2 && c == 6){
                        btn_2_6.setDisable(true);
                    }
                    if(r == 3 && c == 1){
                        btn_3_1.setDisable(true);
                    }
                    if(r == 3 && c == 2){
                        btn_3_2.setDisable(true);
                    }
                    if(r == 3 && c == 3){
                        btn_3_3.setDisable(true);
                    }
                    if(r == 3 && c == 4){
                        btn_3_4.setDisable(true);
                    }
                    if(r == 3 && c == 5){
                        btn_3_5.setDisable(true);
                    }
                    if(r == 3 && c == 6){
                        btn_3_6.setDisable(true);
                    }
                }
            }catch (SQLException e){
            }

            btn_config.setOnAction(event1 -> {
                proj.setId_projection(2);
                config();
            });
        });

        btn_lion_10.setOnAction(event -> {
            pn_homepage.setVisible(false);
            pn_ticket.setVisible(true);

            proj.setId_movie(2);
            proj.setId_projection(4);

            reset();
            list.clear();

            try{
                Connection con = DBConnector.getConnection();
                Statement st = con.createStatement();
                ResultSet rs;
                String query = "SELECT seat.row, seat.colum FROM uspProject.seat " +
                        "JOIN projection on seat.id_projection = projection.projection_id " +
                        "WHERE projection.movie_id = " + proj.getId_movie() +" and seat.id_projection = " + proj.getId_projection() + ";";
                rs = st.executeQuery(query);

                while (rs.next()){
                    int r = rs.getInt("row");
                    int c = rs.getInt("colum");

                    if(r == 1 && c == 1){
                        btn_1_1.setDisable(true);
                    }
                    if(r == 1 && c == 2){
                        btn_1_2.setDisable(true);
                    }
                    if(r == 1 && c == 3){
                        btn_1_3.setDisable(true);
                    }
                    if(r == 1 && c == 4){
                        btn_1_4.setDisable(true);
                    }
                    if(r == 1 && c == 5){
                        btn_1_5.setDisable(true);
                    }
                    if(r == 1 && c == 6){
                        btn_1_6.setDisable(true);
                    }
                    if(r == 2 && c == 1){
                        btn_2_1.setDisable(true);
                    }
                    if(r == 2 && c == 2){
                        btn_2_2.setDisable(true);
                    }
                    if(r == 2 && c == 3){
                        btn_2_3.setDisable(true);
                    }
                    if(r == 2 && c == 4){
                        btn_2_4.setDisable(true);
                    }
                    if(r == 2 && c == 5){
                        btn_2_5.setDisable(true);
                    }
                    if(r == 2 && c == 6){
                        btn_2_6.setDisable(true);
                    }
                    if(r == 3 && c == 1){
                        btn_3_1.setDisable(true);
                    }
                    if(r == 3 && c == 2){
                        btn_3_2.setDisable(true);
                    }
                    if(r == 3 && c == 3){
                        btn_3_3.setDisable(true);
                    }
                    if(r == 3 && c == 4){
                        btn_3_4.setDisable(true);
                    }
                    if(r == 3 && c == 5){
                        btn_3_5.setDisable(true);
                    }
                    if(r == 3 && c == 6){
                        btn_3_6.setDisable(true);
                    }
                }
            }catch (SQLException e){
            }

            btn_config.setOnAction(event1 -> {
                proj.setId_projection(4);
                config();
            });
        });

        btn_lion_15.setOnAction(event -> {
            pn_homepage.setVisible(false);
            pn_ticket.setVisible(true);

            proj.setId_movie(2);
            proj.setId_projection(5);

            reset();
            list.clear();

            try{
                Connection con = DBConnector.getConnection();
                Statement st = con.createStatement();
                ResultSet rs;
                String query = "SELECT seat.row, seat.colum FROM uspProject.seat " +
                        "JOIN projection on seat.id_projection = projection.projection_id " +
                        "WHERE projection.movie_id = " + proj.getId_movie() +" and seat.id_projection = " + proj.getId_projection() + ";";
                rs = st.executeQuery(query);

                while (rs.next()){
                    int r = rs.getInt("row");
                    int c = rs.getInt("colum");

                    if(r == 1 && c == 1){
                        btn_1_1.setDisable(true);
                    }
                    if(r == 1 && c == 2){
                        btn_1_2.setDisable(true);
                    }
                    if(r == 1 && c == 3){
                        btn_1_3.setDisable(true);
                    }
                    if(r == 1 && c == 4){
                        btn_1_4.setDisable(true);
                    }
                    if(r == 1 && c == 5){
                        btn_1_5.setDisable(true);
                    }
                    if(r == 1 && c == 6){
                        btn_1_6.setDisable(true);
                    }
                    if(r == 2 && c == 1){
                        btn_2_1.setDisable(true);
                    }
                    if(r == 2 && c == 2){
                        btn_2_2.setDisable(true);
                    }
                    if(r == 2 && c == 3){
                        btn_2_3.setDisable(true);
                    }
                    if(r == 2 && c == 4){
                        btn_2_4.setDisable(true);
                    }
                    if(r == 2 && c == 5){
                        btn_2_5.setDisable(true);
                    }
                    if(r == 2 && c == 6){
                        btn_2_6.setDisable(true);
                    }
                    if(r == 3 && c == 1){
                        btn_3_1.setDisable(true);
                    }
                    if(r == 3 && c == 2){
                        btn_3_2.setDisable(true);
                    }
                    if(r == 3 && c == 3){
                        btn_3_3.setDisable(true);
                    }
                    if(r == 3 && c == 4){
                        btn_3_4.setDisable(true);
                    }
                    if(r == 3 && c == 5){
                        btn_3_5.setDisable(true);
                    }
                    if(r == 3 && c == 6){
                        btn_3_6.setDisable(true);
                    }
                }
            }catch (SQLException e){
            }

            btn_config.setOnAction(event1 -> {
                proj.setId_projection(5);
                config();
            });
        });

        btn_endgame_12.setOnAction(event -> {
            pn_homepage.setVisible(false);
            pn_ticket.setVisible(true);

            proj.setId_movie(3);
            proj.setId_projection(6);

            reset();
            list.clear();

            try{
                Connection con = DBConnector.getConnection();
                Statement st = con.createStatement();
                ResultSet rs;
                String query = "SELECT seat.row, seat.colum FROM uspProject.seat " +
                        "JOIN projection on seat.id_projection = projection.projection_id " +
                        "WHERE projection.movie_id = " + proj.getId_movie() +" and seat.id_projection = " + proj.getId_projection() + ";";
                rs = st.executeQuery(query);

                while (rs.next()){
                    int r = rs.getInt("row");
                    int c = rs.getInt("colum");

                    if(r == 1 && c == 1){
                        btn_1_1.setDisable(true);
                    }
                    if(r == 1 && c == 2){
                        btn_1_2.setDisable(true);
                    }
                    if(r == 1 && c == 3){
                        btn_1_3.setDisable(true);
                    }
                    if(r == 1 && c == 4){
                        btn_1_4.setDisable(true);
                    }
                    if(r == 1 && c == 5){
                        btn_1_5.setDisable(true);
                    }
                    if(r == 1 && c == 6){
                        btn_1_6.setDisable(true);
                    }
                    if(r == 2 && c == 1){
                        btn_2_1.setDisable(true);
                    }
                    if(r == 2 && c == 2){
                        btn_2_2.setDisable(true);
                    }
                    if(r == 2 && c == 3){
                        btn_2_3.setDisable(true);
                    }
                    if(r == 2 && c == 4){
                        btn_2_4.setDisable(true);
                    }
                    if(r == 2 && c == 5){
                        btn_2_5.setDisable(true);
                    }
                    if(r == 2 && c == 6){
                        btn_2_6.setDisable(true);
                    }
                    if(r == 3 && c == 1){
                        btn_3_1.setDisable(true);
                    }
                    if(r == 3 && c == 2){
                        btn_3_2.setDisable(true);
                    }
                    if(r == 3 && c == 3){
                        btn_3_3.setDisable(true);
                    }
                    if(r == 3 && c == 4){
                        btn_3_4.setDisable(true);
                    }
                    if(r == 3 && c == 5){
                        btn_3_5.setDisable(true);
                    }
                    if(r == 3 && c == 6){
                        btn_3_6.setDisable(true);
                    }
                }
            }catch (SQLException e){
            }

            btn_config.setOnAction(event1 -> {
                proj.setId_projection(6);
                config();
            });
        });

        btn_endgame_18.setOnAction(event -> {
            pn_homepage.setVisible(false);
            pn_ticket.setVisible(true);

            proj.setId_movie(3);
            proj.setId_projection(7);

            reset();
            list.clear();

            try{
                Connection con = DBConnector.getConnection();
                Statement st = con.createStatement();
                ResultSet rs;
                String query = "SELECT seat.row, seat.colum FROM uspProject.seat " +
                        "JOIN projection on seat.id_projection = projection.projection_id " +
                        "WHERE projection.movie_id = " + proj.getId_movie() +" and seat.id_projection = " + proj.getId_projection() + ";";
                rs = st.executeQuery(query);

                while (rs.next()){
                    int r = rs.getInt("row");
                    int c = rs.getInt("colum");

                    if(r == 1 && c == 1){
                        btn_1_1.setDisable(true);
                    }
                    if(r == 1 && c == 2){
                        btn_1_2.setDisable(true);
                    }
                    if(r == 1 && c == 3){
                        btn_1_3.setDisable(true);
                    }
                    if(r == 1 && c == 4){
                        btn_1_4.setDisable(true);
                    }
                    if(r == 1 && c == 5){
                        btn_1_5.setDisable(true);
                    }
                    if(r == 1 && c == 6){
                        btn_1_6.setDisable(true);
                    }
                    if(r == 2 && c == 1){
                        btn_2_1.setDisable(true);
                    }
                    if(r == 2 && c == 2){
                        btn_2_2.setDisable(true);
                    }
                    if(r == 2 && c == 3){
                        btn_2_3.setDisable(true);
                    }
                    if(r == 2 && c == 4){
                        btn_2_4.setDisable(true);
                    }
                    if(r == 2 && c == 5){
                        btn_2_5.setDisable(true);
                    }
                    if(r == 2 && c == 6){
                        btn_2_6.setDisable(true);
                    }
                    if(r == 3 && c == 1){
                        btn_3_1.setDisable(true);
                    }
                    if(r == 3 && c == 2){
                        btn_3_2.setDisable(true);
                    }
                    if(r == 3 && c == 3){
                        btn_3_3.setDisable(true);
                    }
                    if(r == 3 && c == 4){
                        btn_3_4.setDisable(true);
                    }
                    if(r == 3 && c == 5){
                        btn_3_5.setDisable(true);
                    }
                    if(r == 3 && c == 6){
                        btn_3_6.setDisable(true);
                    }
                }
            }catch (SQLException e){
            }

            btn_config.setOnAction(event1 -> {
                proj.setId_projection(7);
                config();
            });
        });

        btn_popular.setOnAction(event -> {
            try {
                Connection con = DBConnector.getConnection();
                Statement st = con.createStatement();
                ResultSet rs;
                int r1 = 0;
                int r2 = 0;
                int r3 = 0;
                String query = "SELECT COUNT(seat.id_seat) as taken From seat \n" +
                        "join projection on seat.id_projection = projection.projection_id\n" +
                        "join movie on projection.movie_id = movie.id_movie\n" +
                        "where movie.id_movie = 1;";
                rs = st.executeQuery(query);
                if(rs.next()){
                    r1 = rs.getInt("taken");
                }

                query = "SELECT COUNT(seat.id_seat) as taken From seat \n" +
                        "join projection on seat.id_projection = projection.projection_id\n" +
                        "join movie on projection.movie_id = movie.id_movie\n" +
                        "where movie.id_movie = 2;";
                rs = st.executeQuery(query);
                if(rs.next()){
                    r2 = rs.getInt("taken");
                }

                query = "SELECT COUNT(seat.id_seat) as taken  From seat \n" +
                        "join projection on seat.id_projection = projection.projection_id\n" +
                        "join movie on projection.movie_id = movie.id_movie\n" +
                        "where movie.id_movie = 3;";
                rs = st.executeQuery(query);
                if(rs.next()){
                    r3 = rs.getInt("taken");
                }
                if(Collections.max(Arrays.asList(r1, r2, r3)) == r1){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("The most popular film is 'How to train your dragon'!! ");
                    alert.showAndWait();
                } else if(Collections.max(Arrays.asList(r1, r2, r3)) == r2){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("The most popular film is 'The Lion King'!! ");
                    alert.showAndWait();
                } else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("The most popular film is 'Avengers: Endgame'!! ");
                    alert.showAndWait();
                }
            }catch (SQLException e){

            }
        });
    }

    @FXML
    public void reset(){
        btn_1_1.setDisable(false);
        btn_1_2.setDisable(false);
        btn_1_3.setDisable(false);
        btn_1_4.setDisable(false);
        btn_1_5.setDisable(false);
        btn_1_6.setDisable(false);
        btn_2_1.setDisable(false);
        btn_2_2.setDisable(false);
        btn_2_3.setDisable(false);
        btn_2_4.setDisable(false);
        btn_2_5.setDisable(false);
        btn_2_6.setDisable(false);
        btn_3_1.setDisable(false);
        btn_3_2.setDisable(false);
        btn_3_3.setDisable(false);
        btn_3_4.setDisable(false);
        btn_3_5.setDisable(false);
        btn_3_6.setDisable(false);
    }

    @FXML
    public void config(){

        int value = Integer.parseInt(tb_standart.getText());
        int val = Integer.parseInt(tb_student.getText());
        int v = Integer.parseInt(tb_kid.getText());

        int sum = value + val + v;

        int row;
        int colum;

        for (Button elem: list
        ) {
            if(elem == btn_1_1){
                row = 1;
                colum = 1;

                try {
                    Connection con = DBConnector.getConnection();
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.seat VALUES (default , ?, ?, ?);");
                    st.setInt(1, proj.getId_projection());
                    st.setInt(2, row);
                    st.setInt(3, colum);
                    st.executeUpdate();
                }catch (SQLException e){

                }
            }
            if(elem == btn_1_2){
                row = 1;
                colum = 2;

                try {
                    Connection con = DBConnector.getConnection();
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.seat VALUES (default , ?, ?, ?);");
                    st.setInt(1, proj.getId_projection());
                    st.setInt(2, row);
                    st.setInt(3, colum);
                    st.executeUpdate();
                }catch (SQLException e){

                }
            }
            if(elem == btn_1_3){
                row = 1;
                colum = 3;

                try {
                    Connection con = DBConnector.getConnection();
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.seat VALUES (default , ?, ?, ?);");
                    st.setInt(1, proj.getId_projection());
                    st.setInt(2, row);
                    st.setInt(3, colum);
                    st.executeUpdate();
                }catch (SQLException e){

                }
            }
            if(elem == btn_1_4){
                row = 1;
                colum = 4;

                try {
                    Connection con = DBConnector.getConnection();
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.seat VALUES (default , ?, ?, ?);");
                    st.setInt(1, proj.getId_projection());
                    st.setInt(2, row);
                    st.setInt(3, colum);
                    st.executeUpdate();
                }catch (SQLException e){

                }
            }
            if(elem == btn_1_5){
                row = 1;
                colum = 5;

                try {
                    Connection con = DBConnector.getConnection();
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.seat VALUES (default , ?, ?, ?);");
                    st.setInt(1, proj.getId_projection());
                    st.setInt(2, row);
                    st.setInt(3, colum);
                    st.executeUpdate();
                }catch (SQLException e){

                }
            }
            if(elem == btn_1_6){
                row = 1;
                colum = 6;

                try {
                    Connection con = DBConnector.getConnection();
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.seat VALUES (default , ?, ?, ?);");
                    st.setInt(1, proj.getId_projection());
                    st.setInt(2, row);
                    st.setInt(3, colum);
                    st.executeUpdate();
                }catch (SQLException e){

                }
            }
            if(elem == btn_2_1){
                row = 2;
                colum = 1;

                try {
                    Connection con = DBConnector.getConnection();
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.seat VALUES (default , ?, ?, ?);");
                    st.setInt(1, proj.getId_projection());
                    st.setInt(2, row);
                    st.setInt(3, colum);
                    st.executeUpdate();
                }catch (SQLException e){

                }
            }
            if(elem == btn_2_2){
                row = 2;
                colum = 2;

                try {
                    Connection con = DBConnector.getConnection();
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.seat VALUES (default , ?, ?, ?);");
                    st.setInt(1, proj.getId_projection());
                    st.setInt(2, row);
                    st.setInt(3, colum);
                    st.executeUpdate();
                }catch (SQLException e){

                }
            }
            if(elem == btn_2_3){
                row = 2;
                colum = 3;

                try {
                    Connection con = DBConnector.getConnection();
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.seat VALUES (default , ?, ?, ?);");
                    st.setInt(1, proj.getId_projection());
                    st.setInt(2, row);
                    st.setInt(3, colum);
                    st.executeUpdate();
                }catch (SQLException e){

                }
            }
            if(elem == btn_2_4){
                row = 2;
                colum = 4;

                try {
                    Connection con = DBConnector.getConnection();
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.seat VALUES (default , ?, ?, ?);");
                    st.setInt(1, proj.getId_projection());
                    st.setInt(2, row);
                    st.setInt(3, colum);
                    st.executeUpdate();
                }catch (SQLException e){

                }
            }
            if(elem == btn_2_5){
                row = 2;
                colum = 5;

                try {
                    Connection con = DBConnector.getConnection();
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.seat VALUES (default , ?, ?, ?);");
                    st.setInt(1, proj.getId_projection());
                    st.setInt(2, row);
                    st.setInt(3, colum);
                    st.executeUpdate();
                }catch (SQLException e){

                }
            }
            if(elem == btn_2_6){
                row = 2;
                colum = 6;

                try {
                    Connection con = DBConnector.getConnection();
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.seat VALUES (default , ?, ?, ?);");
                    st.setInt(1, proj.getId_projection());
                    st.setInt(2, row);
                    st.setInt(3, colum);
                    st.executeUpdate();
                }catch (SQLException e){

                }
            }
            if(elem == btn_3_1){
                row = 3;
                colum = 1;

                try {
                    Connection con = DBConnector.getConnection();
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.seat VALUES (default , ?, ?, ?);");
                    st.setInt(1, proj.getId_projection());
                    st.setInt(2, row);
                    st.setInt(3, colum);
                    st.executeUpdate();
                }catch (SQLException e){

                }
            }
            if(elem == btn_3_2){
                row = 3;
                colum = 2;

                try {
                    Connection con = DBConnector.getConnection();
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.seat VALUES (default , ?, ?, ?);");
                    st.setInt(1, proj.getId_projection());
                    st.setInt(2, row);
                    st.setInt(3, colum);
                    st.executeUpdate();
                }catch (SQLException e){

                }
            }
            if(elem == btn_3_3){
                row = 3;
                colum = 3;

                try {
                    Connection con = DBConnector.getConnection();
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.seat VALUES (default , ?, ?, ?);");
                    st.setInt(1, proj.getId_projection());
                    st.setInt(2, row);
                    st.setInt(3, colum);
                    st.executeUpdate();
                }catch (SQLException e){

                }
            }
            if(elem == btn_3_4){
                row = 3;
                colum = 4;

                try {
                    Connection con = DBConnector.getConnection();
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.seat VALUES (default , ?, ?, ?);");
                    st.setInt(1, proj.getId_projection());
                    st.setInt(2, row);
                    st.setInt(3, colum);
                    st.executeUpdate();
                }catch (SQLException e){

                }
            }
            if(elem == btn_3_5){
                row = 3;
                colum = 5;

                try {
                    Connection con = DBConnector.getConnection();
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.seat VALUES (default , ?, ?, ?);");
                    st.setInt(1, proj.getId_projection());
                    st.setInt(2, row);
                    st.setInt(3, colum);
                    st.executeUpdate();
                }catch (SQLException e){

                }
            }
            if(elem == btn_3_6){
                row = 3;
                colum = 6;

                try {
                    Connection con = DBConnector.getConnection();
                    PreparedStatement st = con.prepareStatement("INSERT INTO uspProject.seat VALUES (default , ?, ?, ?);");
                    st.setInt(1, proj.getId_projection());
                    st.setInt(2, row);
                    st.setInt(3, colum);
                    st.executeUpdate();
                }catch (SQLException e){

                }
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("You buy " + sum + " ticket/s successflly!");
        alert.showAndWait();

        pn_ticket.setVisible(false);
        pn_homepage.setVisible(true);

    }
}
