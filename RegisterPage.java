import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Statement;


public class RegisterPage {
    public void build (Stage s){


        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        gridPane.setPadding(new Insets(30,0,0,0));
        Label labelname= new Label("Nume");
        TextField textField = new TextField();
        gridPane.add(labelname, 0,0);
        gridPane.add(textField,1,0);


        Label labelemail= new Label("Email");
        TextField textemail = new TextField();
        gridPane.add(labelemail, 0,2);
        gridPane.add(textemail,1,2);

        Label labelpassword= new Label("Parola");
        TextField textpassword = new TextField();
        gridPane.add(labelpassword, 0,1);
        gridPane.add(textpassword,1,1);

        Button button = new Button("Inapoi");
        button.setOnMouseClicked(e -> {
            Proiect loginPage = new Proiect();
            loginPage.start(s);
        });
        Button button1 = new Button("Inregistrare");
        button1.setOnMouseClicked(e-> {
            try{
                Statement st = DBAConn.getConnection().createStatement();
                String query=
                        String.format("insert into user values(null, '%s',' %s', '%s')",
               textField.getText(),
               textpassword.getText(),
               textemail.getText()
                        );
           st.executeUpdate(query);
           Proiect loginpage= new Proiect();
           loginpage.start(s);


            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        gridPane.add(button, 0,3);
        gridPane.add(button1,1,3);


        Scene scene = new Scene(gridPane);
        s.setScene(scene);



    }
}
