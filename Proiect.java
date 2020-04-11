
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.VPos;
import javafx.scene.Group;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;


import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Proiect extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Connection connection = DBAConn.getConnection();
        primaryStage.setTitle("BookLine");
        primaryStage.setWidth(550);
        primaryStage.setHeight(500);
        Group group = new Group();
        primaryStage.getIcons().add(new Image("file:321.png"));


        TextField textField = new TextField();
        group.getChildren().add(textField);

        textField.setLayoutX(190);
        textField.setLayoutY(180);

        PasswordField textField2 = new PasswordField();
        group.getChildren().add(textField2);

        textField2.setLayoutX(190);
        textField2.setLayoutY(250);


        Light.Point light = new Light.Point();
        light.setX(80);
        light.setY(210);
        light.setZ(150);

        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);

        Text text = new Text();
        text.setText("Username");
        text.setFill(Color.SADDLEBROWN);
        text.setFont(Font.font(null, FontWeight.BOLD, 20));
        text.setX(60.0);
        text.setY(180.0);
        text.setTextOrigin(VPos.TOP);

        Rectangle rect = new Rectangle(250, 150);
        rect.setFill(Color.SADDLEBROWN);
        rect.setEffect(lighting);
        text.setEffect(lighting);
        group.getChildren().add(text);


        Lighting lighting1 = new Lighting();
        lighting1.setLight(light);
        lighting1.setSurfaceScale(5.0);

        Text text2 = new Text();
        text2.setText("Password");
        text2.setFill(Color.SADDLEBROWN);
        text2.setFont(Font.font(null, FontWeight.BOLD, 20));
        text2.setX(60.0);
        text2.setY(250.0);
        text2.setTextOrigin(VPos.TOP);

        Rectangle rect1 = new Rectangle(250, 150);
        rect1.setFill(Color.SADDLEBROWN);
        rect1.setEffect(lighting);
        text.setEffect(lighting);
        group.getChildren().add(text2);

        Label labelres = new Label();

        Button button2 = new Button("Logare");
        group.getChildren().add(button2);
        button2.setOnMouseClicked(e-> {
            try {
                Statement st= DBAConn.getConnection().createStatement();
                String query =String.format("select count (*) as nr from user where email ='%s' and password = '%s'",
                  textField.getText(),
                  textField2.getText()
          );
                ResultSet rsLogin = st.executeQuery(query);
                if (rsLogin.next()) {
                int userid=  rsLogin.getInt("idlogare1");
            }else {
            labelres.setText("nu e corect");


            }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        button2.setTextFill(Color.BLUE);
        button2.setLayoutX(180);
        button2.setLayoutY(340);

        Button button3 = new Button("Inregistrare");
        group.getChildren().add(button3);
        button3.setOnMouseClicked(e -> {
            RegisterPage registerPage = new RegisterPage();
            registerPage.build(primaryStage);
        });


        button3.setTextFill(Color.BLUE);
        button3.setLayoutX(310);
        button3.setLayoutY(340);


        group.getChildren().add(labelres);
        labelres.setLayoutX(10);
        labelres.setLayoutY(300);
        button2.setOnMouseClicked(e -> {
            String user = textField.getText();
            String pass = textField2.getText();
            Group group1 = new Group();
            Group group2 = new Group();

            if (user.equals("") && pass.equals("")) {
                primaryStage.setHeight(800);
                primaryStage.setWidth(1400);



                Label labelTitle2 = new Label("Biblioteca online");
                labelTitle2.setCache(true);
                labelTitle2.setFont(Font.font(null, FontWeight.BOLD, 30));
                Image image = new Image("books.png", 80, 80, true, true);
                labelTitle2.setGraphic(new ImageView(image));

                labelTitle2.setTextFill(Color.web("#0076a3"));



                TabPane tabPane = new TabPane();
                Tab tab = new Tab("new tab");
                tab.setContent(new Label("Please help"));
                tabPane.getTabs().add(tab);
                tab.setOnCloseRequest(l -> e.consume());

                TextField textField3 = new TextField();
                textField3.setLayoutX(30);
                textField3.setLayoutY(80);
                Label labelTitle3 = new Label("");




                VBox vbox = new VBox();
                vbox.getChildren().addAll(labelTitle2, labelTitle3, tabPane);

                Scene scene = new Scene(vbox, 700, 500);


                primaryStage.setScene(scene);
                primaryStage.show();


            } else {
                labelres.setText("Incercati din nou!");
            }

        });
        Scene scene = new Scene(group);


        Reflection reflection = new Reflection();
        reflection.setFraction(0.7);

        Text text1 = new Text();
        text1.setX(8.0);
        text1.setY(70.0);
        text1.setCache(true);
        text1.setText("Introduceti datele de logare");
        text1.setFill(Color.web("0x3b596d"));
        text1.setFont(Font.font(null, FontWeight.BOLD, 30));
        text1.setEffect(reflection);
        group.getChildren().add(text1);


        primaryStage.show();
        primaryStage.setScene(scene);


    }


}


