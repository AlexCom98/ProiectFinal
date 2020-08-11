import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.sql.Statement;


public class RegisterPage {
    public void build (Stage s){
        Label labelTitle2 = new Label();
        Image image = new Image("login.png", 80, 80, true, true);
        labelTitle2.setGraphic(new ImageView(image));


        GridPane gridPane = new GridPane();
        gridPane.setHgap(90);
        gridPane.setVgap(30);
        gridPane.setAlignment(Pos.CENTER);
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
        PasswordField textpassword = new PasswordField();
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
                Statement st = DBAConn.connection.createStatement();
                String query=
                        String.format("insert into logare1 values(null, '%s', '%s','%s');",
                                textField.getText(),
                                textpassword.getText(),
                                textemail.getText()
                        );
                st.execute(query);


                Proiect loginPage = new Proiect();
                loginPage.start(s);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
         labelTitle2.setLayoutX(200);
         labelTitle2.setLayoutY(10);
        gridPane.add(button, 0,3);
        gridPane.add(button1,1,3);
        gridPane.setLayoutX(100);
        gridPane.setLayoutY(100);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(labelTitle2,gridPane);


        Scene scene = new Scene(anchorPane);
        s.setScene(scene);



    }
}
