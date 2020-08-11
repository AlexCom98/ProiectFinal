import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.Rating;

import javax.swing.*;
import java.sql.*;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.RED;

public class Proiect extends Application {

    private ObservableList<ObservableList> data;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        Connection connection = DBAConn.getConnection();


        primaryStage.setTitle("Catalog");
        primaryStage.titleProperty();
        primaryStage.setWidth(550);
        primaryStage.setHeight(500);
        Group group = new Group();
        primaryStage.getIcons().add(new Image("321.png"));


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

        Button buttonLog = new Button("Logare");
        group.getChildren().add(buttonLog);

        buttonLog.setOnMouseClicked(e -> {
            try {
                Statement st = DBAConn.connection.createStatement();
                String query = String.format("select * from logare1 where name ='%s' and parola = '%s';",
                        textField.getText(),
                        textField2.getText()
                );
                ResultSet rsLogin = st.executeQuery(query);
                if (rsLogin.next()) {

                    Label labelTitle3 = new Label("");
                    Group group1 = new Group();


                    Label labelTitle2 = new Label("Catalog online");
                    labelTitle2.setCache(true);
                    labelTitle2.setFont(Font.font(null, FontWeight.BOLD, 30));
                    Image image = new Image("Icon/books.png", 80, 80, true, true);
                    labelTitle2.setGraphic(new ImageView(image));

                    labelTitle2.setTextFill(Color.web("#0000FF", 0.5));


                    VBox vbox = new VBox();
                    Label label3 = new Label("");

                    StackPane stackPane1 = new StackPane();
                    stackPane1.setPrefSize(1250, 610);
                    stackPane1.setStyle("-fx-background-color: Gainsboro;-fx-border-color: blue;");
                    stackPane1.alignmentProperty();


                    ScrollPane scroll = new ScrollPane();
                    TableView tableView3 = new TableView();

                    Button button2 = new Button("Clasa I");
                    button2.setPrefSize(150, 80);
                    StackPane stackPane6 = new StackPane();
                    TableView tableView1 = new TableView();


                    Connection c;
                    data = FXCollections.observableArrayList();
                    c = DBAConn.getConnection();

                    String SQL = "select * from actiune";
                    ResultSet rs = c.createStatement().executeQuery(SQL);


                    for (int i = 1; i > rs.getMetaData().getColumnCount(); i++) {

                        TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                        int j = i;
                        col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));
                        tableView1.getColumns().addAll(col);
                        col.setMinWidth(105.8);


                    }

                    button2.setOnMouseClicked(f -> {

                        tableView1.setPrefSize(1250, 605);
                        stackPane1.getChildren().clear();
                        tableView1.getItems().clear();
                        tableView1.setLayoutY(0);
                        tableView1.setLayoutX(0);
                        tableView3.getItems().clear();


                        try {

                            String SQL1 = "select *from ARTproiect.actiune where Clasa LIKE 'I %';";
                            ResultSet rs1 = c.createStatement().executeQuery(SQL1);

                            while (rs1.next()) {

                                ObservableList<String> row = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) {

                                    row.add(rs1.getString(i));
                                  
                                }

                                data.add(row);

                            }
                            tableView1.setItems(data);

                        } catch (Exception r) {
                            r.printStackTrace();
                            System.out.println("Error on Building Data");
                        }


                        AnchorPane anchorPane = new AnchorPane();
                        anchorPane.getChildren().add(tableView1);
                        stackPane1.getChildren().add(anchorPane);
                    });
                    Button button3 = new Button("Clasa II");
                    button3.setPrefSize(150, 80);
                    button3.setMaxHeight(150);

                    button3.setOnMouseClicked(f -> {
                        tableView1.setPrefSize(1250, 605);
                        stackPane1.getChildren().clear();
                        tableView1.getItems().clear();
                        tableView1.setLayoutY(0);
                        tableView1.setLayoutX(0);
                        tableView3.getItems().clear();

                        try {

                            String SQL1 = "select *from ARTproiect.actiune where Clasa LIKE 'II %';";
                            ResultSet rs2 = c.createStatement().executeQuery(SQL1);

                            while (rs2.next()) {

                                ObservableList<String> row = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {

                                    row.add(rs2.getString(i));

                                }

                                data.add(row);

                            }
                            tableView1.setItems(data);


                        } catch (Exception r) {
                            r.printStackTrace();
                            System.out.println("Error on Building Data");
                        }
                        AnchorPane anchorPane = new AnchorPane();
                        anchorPane.getChildren().add(tableView1);
                        stackPane1.getChildren().add(anchorPane);


                    });


                    Button button4 = new Button("Clasa III");
                    button4.setPrefSize(150, 80);
                    button4.setOnMouseClicked(f -> {
                        tableView1.setPrefSize(1250, 605);
                        stackPane1.getChildren().clear();
                        tableView1.getItems().clear();
                        tableView1.setLayoutY(0);
                        tableView1.setLayoutX(0);
                        tableView3.getItems().clear();
                        try {
                            String SQL1 = "select *from ARTproiect.actiune where Clasa LIKE 'III %';";
                            ResultSet rs3 = c.createStatement().executeQuery(SQL1);

                            while (rs3.next()) {

                                ObservableList<String> row = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs3.getMetaData().getColumnCount(); i++) {

                                    row.add(rs3.getString(i));
                                }

                                data.add(row);

                            }
                            tableView1.setItems(data);


                        } catch (Exception r) {
                            r.printStackTrace();
                            System.out.println("Error on Building Data");
                        }
                        AnchorPane anchorPane = new AnchorPane();
                        anchorPane.getChildren().add(tableView1);
                        stackPane1.getChildren().add(anchorPane);


                    });


                    Button button5 = new Button("Clasa IV");
                    button5.setMaxHeight(150);
                    button5.setPrefSize(150, 80);
                    button5.setOnMouseClicked(f -> {
                        tableView1.setPrefSize(1250, 605);
                        stackPane1.getChildren().clear();
                        tableView1.getItems().clear();
                        tableView1.setLayoutY(0);
                        tableView1.setLayoutX(0);
                        tableView3.getItems().clear();

                        try {
                            String SQL1 = "select *from ARTproiect.actiune where Clasa LIKE 'IV %';";
                            ResultSet rs2 = c.createStatement().executeQuery(SQL1);

                            while (rs2.next()) {

                                ObservableList<String> row = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {

                                    row.add(rs2.getString(i));
                                }

                                data.add(row);

                            }
                            tableView1.setItems(data);

                        } catch (Exception r) {
                            r.printStackTrace();
                            System.out.println("Error on Building Data");
                        }
                        AnchorPane anchorPane = new AnchorPane();
                        anchorPane.getChildren().add(tableView1);
                        stackPane1.getChildren().add(anchorPane);


                    });

                    Button button6 = new Button("Clasa V");
                    button6.setPrefSize(150, 80);
                    button6.setOnMouseClicked(f -> {
                        tableView1.setPrefSize(1250, 605);
                        stackPane1.getChildren().clear();
                        tableView1.getItems().clear();
                        tableView1.setLayoutY(0);
                        tableView1.setLayoutX(0);
                        tableView3.getItems().clear();
                        try {
                            String SQL1 = "select *from ARTproiect.actiune where Clasa LIKE 'V %';";
                            ResultSet rs2 = c.createStatement().executeQuery(SQL1);

                            while (rs2.next()) {

                                ObservableList<String> row = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {

                                    row.add(rs2.getString(i));
                                }

                                data.add(row);

                            }
                            tableView1.setItems(data);

                        } catch (Exception r) {
                            r.printStackTrace();
                            System.out.println("Error on Building Data");
                        }
                        AnchorPane anchorPane = new AnchorPane();
                        anchorPane.getChildren().add(tableView1);
                        stackPane1.getChildren().add(anchorPane);


                    });

                    Button button7 = new Button("Clasa VI");
                    button7.setPrefSize(150, 80);
                    button7.setOnMouseClicked(f -> {
                        tableView1.setPrefSize(1250, 605);
                        stackPane1.getChildren().clear();
                        tableView1.getItems().clear();
                        tableView1.setLayoutY(0);
                        tableView1.setLayoutX(0);
                        tableView3.getItems().clear();
                        try {
                            String SQL1 = "select *from ARTproiect.actiune where Clasa LIKE 'VI %';";
                            ResultSet rs2 = c.createStatement().executeQuery(SQL1);

                            while (rs2.next()) {

                                ObservableList<String> row = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {

                                    row.add(rs2.getString(i));
                                }

                                data.add(row);

                            }
                            tableView1.setItems(data);

                        } catch (Exception r) {
                            r.printStackTrace();
                            System.out.println("Error on Building Data");
                        }
                        AnchorPane anchorPane = new AnchorPane();
                        anchorPane.getChildren().add(tableView1);
                        stackPane1.getChildren().add(anchorPane);

                    });

                    Button button8 = new Button("Clasa VII");
                    button8.setPrefSize(150, 80);
                    button8.setOnMouseClicked(f -> {
                        tableView1.setPrefSize(1250, 605);
                        stackPane1.getChildren().clear();
                        tableView1.getItems().clear();
                        tableView1.setLayoutY(0);
                        tableView1.setLayoutX(0);
                        tableView3.getItems().clear();

                        try {
                            String SQL1 = "select *from ARTproiect.actiune where Clasa LIKE 'VII %';";
                            ResultSet rs2 = c.createStatement().executeQuery(SQL1);

                            while (rs2.next()) {

                                ObservableList<String> row = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {

                                    row.add(rs2.getString(i));
                                }

                                data.add(row);

                            }
                            tableView1.setItems(data);

                        } catch (Exception r) {
                            r.printStackTrace();
                            System.out.println("Error on Building Data");
                        }
                        AnchorPane anchorPane = new AnchorPane();
                        anchorPane.getChildren().add(tableView1);
                        stackPane1.getChildren().add(anchorPane);

                    });

                    Button button9 = new Button("Clasa VIII");
                    button9.setPrefSize(150, 80);
                    button9.setOnMouseClicked(f -> {
                        tableView1.setPrefSize(1250, 605);
                        stackPane1.getChildren().clear();
                        tableView1.getItems().clear();
                        tableView1.setLayoutY(0);
                        tableView1.setLayoutX(0);
                        tableView3.getItems().clear();
                        try {
                            String SQL1 = "select *from ARTproiect.actiune where Clasa LIKE 'VIII %';";
                            ResultSet rs2 = c.createStatement().executeQuery(SQL1);


                            while (rs2.next()) {

                                ObservableList<String> row = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {

                                    row.add(rs2.getString(i));
                                }

                                data.add(row);

                            }
                            tableView1.setItems(data);

                        } catch (Exception r) {
                            r.printStackTrace();
                            System.out.println("Error on Building Data");
                        }
                        AnchorPane anchorPane = new AnchorPane();
                        anchorPane.getChildren().add(tableView1);
                        stackPane1.getChildren().add(anchorPane);

                    });

                    Button button10 = new Button("Clasa IX");
                    button10.setPrefSize(150, 80);
                    button10.setOnMouseClicked(f -> {
                        tableView1.setPrefSize(1250, 605);
                        stackPane1.getChildren().clear();
                        tableView1.getItems().clear();
                        tableView1.setLayoutY(0);
                        tableView1.setLayoutX(0);
                        tableView3.getItems().clear();
                        try {
                            String SQL1 = "select *from ARTproiect.actiune where Clasa LIKE 'IX %';";
                            ResultSet rs2 = c.createStatement().executeQuery(SQL1);


                            while (rs2.next()) {

                                ObservableList<String> row = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {

                                    row.add(rs2.getString(i));
                                }

                                data.add(row);

                            }
                            tableView1.setItems(data);

                        } catch (Exception r) {
                            r.printStackTrace();
                            System.out.println("Error on Building Data");
                        }
                        AnchorPane anchorPane = new AnchorPane();
                        anchorPane.getChildren().add(tableView1);
                        stackPane1.getChildren().add(anchorPane);

                    });


                    Button button11 = new Button("Clasa X");
                    button11.setPrefSize(150, 80);
                    button11.setOnMouseClicked(f -> {
                        tableView1.setPrefSize(1250, 605);
                        stackPane1.getChildren().clear();
                        tableView1.getItems().clear();
                        tableView1.setLayoutY(0);
                        tableView1.setLayoutX(0);
                        tableView3.getItems().clear();
                        try {
                            String SQL1 = "select *from ARTproiect.actiune where Clasa LIKE 'X %';";
                            ResultSet rs2 = c.createStatement().executeQuery(SQL1);

                            while (rs2.next()) {

                                ObservableList<String> row = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {

                                    row.add(rs2.getString(i));
                                }

                                data.add(row);

                            }
                            tableView1.setItems(data);

                        } catch (Exception r) {
                            r.printStackTrace();
                            System.out.println("Error on Building Data");
                        }
                        AnchorPane anchorPane = new AnchorPane();
                        anchorPane.getChildren().add(tableView1);
                        stackPane1.getChildren().add(anchorPane);

                    });

                    Button button12 = new Button("Clasa XI");
                    button12.setPrefSize(150, 80);
                    button12.setOnMouseClicked(f -> {
                        tableView1.setPrefSize(1250, 605);
                        stackPane1.getChildren().clear();
                        tableView1.getItems().clear();
                        tableView1.setLayoutY(0);
                        tableView1.setLayoutX(0);
                        tableView3.getItems().clear();
                        try {

                            String SQL1 = "select *from ARTproiect.actiune where Clasa LIKE 'XI %';";
                            ResultSet rs2 = c.createStatement().executeQuery(SQL1);

                            while (rs2.next()) {

                                ObservableList<String> row = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {

                                    row.add(rs2.getString(i));
                                }

                                data.add(row);

                            }
                            tableView1.setItems(data);

                        } catch (Exception r) {
                            r.printStackTrace();
                            System.out.println("Error on Building Data");
                        }
                        AnchorPane anchorPane = new AnchorPane();
                        anchorPane.getChildren().add(tableView1);
                        stackPane1.getChildren().add(anchorPane);

                    });

                    Button button13 = new Button(" Clasa XII");
                    button13.setPrefSize(150, 80);
                    button13.setOnMouseClicked(f -> {
                        tableView1.setPrefSize(1250, 605);
                        stackPane1.getChildren().clear();
                        tableView1.getItems().clear();
                        tableView1.setLayoutY(0);
                        tableView1.setLayoutX(0);
                        tableView3.getItems().clear();
                        try {

                            String SQL1 = "select *from ARTproiect.actiune where Clasa LIKE 'XII %';";
                            ResultSet rs2 = c.createStatement().executeQuery(SQL1);

                            while (rs2.next()) {

                                ObservableList<String> row = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {

                                    row.add(rs2.getString(i));
                                }

                                data.add(row);

                            }
                            tableView1.setItems(data);

                        } catch (Exception r) {
                            r.printStackTrace();
                            System.out.println("Error on Building Data");
                        }
                        AnchorPane anchorPane = new AnchorPane();
                        anchorPane.getChildren().add(tableView1);
                        stackPane1.getChildren().add(anchorPane);


                    });


                    scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                    scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


                    Label label4 = new Label("");

                    Scene scene = new Scene(vbox);
                    HBox hBox = new HBox(button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13);
                    hBox.setSpacing(20);
                    scroll.setContent(hBox);


                    AnchorPane anchorPane = new AnchorPane();
                    StackPane stackPane = new StackPane();
                    stackPane.setPrefSize(300, 570);
                    stackPane.setStyle("-fx-background-color: Gainsboro;-fx-border-color: blue;");


                    Label label5 = new Label("Meniu");
                    label5.setFont(Font.font(null, FontWeight.BOLD, 32));
                    DropShadow dropShadow2 = new DropShadow();
                    label5.setLayoutX(25);
                    dropShadow2.setOffsetY(3.0);
                    dropShadow2.setOffsetX(3.0);
                    dropShadow2.setColor(Color.GREEN);
                    dropShadow2.setBlurType(BlurType.GAUSSIAN);
                    label5.setEffect(dropShadow2);
                    label5.setUnderline(true);


                    Connection c2;
                    data = FXCollections.observableArrayList();
                    c2 = DBAConn.getConnection();

                    String SQL2 = "select * from actiune";
                    ResultSet rs2 = c2.createStatement().executeQuery(SQL2);

                    for (int i = 1; i < rs2.getMetaData().getColumnCount(); i++) {

                        TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                        int j = i;
                        col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));
                        tableView1.getColumns().addAll(col);
                        col.setMinWidth(175.8);


                    }


                    Button button21 = new Button("Cauta elevi");
                    button21.setPrefHeight(79);
                    button21.setPrefWidth(299);
                    button21.setLayoutX(0);
                    button21.setLayoutY(60);
                    button21.setOnMouseClicked(f -> {
                        stackPane1.getChildren().clear();
                        tableView1.getItems().clear();


                        Label label = new Label("Introduceti cuvantul cheie:");
                        label.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 16));
                        label.setLayoutY(40);
                        label.setLayoutX(60);
                        TextField textField1 = new TextField();
                        TextField textField3 = new TextField();
                        TextField textField4 = new TextField();
                        Button button = new Button("Search");
                        button.setLayoutY(550);
                        button.setLayoutX(60);
                        button.setOnMouseClicked(t -> {

                            tableView1.getItems().clear();
                            try {

                                String SQL1 = String.format("SELECT * FROM ARTproiect.actiune WHERE Nume = '%s' ;",
                                        textField1.getText()

                                );

                                ResultSet rs3 = c.createStatement().executeQuery(SQL1);

                                while (rs3.next()) {

                                    ObservableList<String> row = FXCollections.observableArrayList();
                                    for (int i = 1; i <= rs3.getMetaData().getColumnCount(); i++) {

                                        row.add(rs3.getString(i));
                                    }

                                    data.add(row);

                                }
                                tableView1.setItems(data);


                            } catch (Exception r) {
                                r.printStackTrace();
                                System.out.println("Error on Building Data");
                            }


                            try {


                                String SQL1 = String.format("SELECT * FROM ARTproiect.actiune WHERE Prenume = '%s' ;",

                                        textField3.getText()

                                );

                                ResultSet rs3 = c.createStatement().executeQuery(SQL1);

                                while (rs3.next()) {

                                    ObservableList<String> row = FXCollections.observableArrayList();
                                    for (int i = 1; i <= rs3.getMetaData().getColumnCount(); i++) {

                                        row.add(rs3.getString(i));
                                    }

                                    data.add(row);

                                }
                                tableView1.setItems(data);


                            } catch (Exception r) {
                                r.printStackTrace();
                                System.out.println("Error on Building Data");
                            }


                            try {


                                String SQL1 = String.format("SELECT * FROM ARTproiect.actiune WHERE Clasa = '%s' ;",

                                        textField4.getText()
                                );

                                ResultSet rs3 = c.createStatement().executeQuery(SQL1);

                                while (rs3.next()) {

                                    ObservableList<String> row = FXCollections.observableArrayList();
                                    for (int i = 1; i <= rs3.getMetaData().getColumnCount(); i++) {

                                        row.add(rs3.getString(i));
                                    }

                                    data.add(row);

                                }
                                tableView1.setItems(data);


                            } catch (Exception r) {
                                r.printStackTrace();
                                System.out.println("Error on Building Data");
                            }


                        });
                        tableView1.setLayoutY(0);
                        tableView1.setLayoutX(270);
                        tableView1.setPrefSize(970, 600);

                        Label label1 = new Label("Nume");
                        Label label2 = new Label("Prenume");
                        Label label6 = new Label("Clasa");


                        textField1.setLayoutX(60);
                        textField1.setLayoutY(140);
                        textField3.setLayoutX(60);
                        textField3.setLayoutY(260);
                        textField4.setLayoutX(60);
                        textField4.setLayoutY(380);

                        label1.setLayoutX(60);
                        label1.setLayoutY(120);
                        label2.setLayoutX(60);
                        label2.setLayoutY(240);
                        label6.setLayoutX(60);
                        label6.setLayoutY(360);


                        AnchorPane anchorPane2 = new AnchorPane();
                        anchorPane2.getChildren().addAll(label, button, tableView1, label1, label2, label6, textField1, textField3, textField4);


                        stackPane1.getChildren().addAll(anchorPane2);
                    });


                    Button button24 = new Button("Adauga elevi");
                    button24.setPrefHeight(79);
                    button24.setPrefWidth(299);
                    button24.setLayoutX(0);
                    button24.setLayoutY(140);
                    button24.setOnMouseClicked(f -> {
                        stackPane1.getChildren().clear();
                        Label label = new Label("Nume");
                        Label label1 = new Label("Prenume");
                        Label label2 = new Label("Clasa");
                        Label label6 = new Label("Romana");
                        Label label7 = new Label("Matematica");
                        Label label8 = new Label("Biologie");
                        Label label9 = new Label("Chimie");

                        label6.setLayoutY(160);
                        label6.setLayoutX(100);
                        label7.setLayoutY(160);
                        label7.setLayoutX(300);
                        label8.setLayoutY(160);
                        label8.setLayoutX(500);
                        label9.setLayoutY(160);
                        label9.setLayoutX(700);
                        TextField textField6 = new TextField();
                        TextField textField7 = new TextField();
                        TextField textField8 = new TextField();
                        TextField textField9 = new TextField();
                        textField6.setLayoutY(200);
                        textField6.setLayoutX(100);
                        textField7.setLayoutY(200);
                        textField7.setLayoutX(300);
                        textField8.setLayoutY(200);
                        textField8.setLayoutX(500);
                        textField9.setLayoutY(200);
                        textField9.setLayoutX(700);

                        label.setLayoutY(50);
                        label.setLayoutX(100);
                        label1.setLayoutY(50);
                        label1.setLayoutX(300);
                        label2.setLayoutY(50);
                        label2.setLayoutX(500);
                        TextField textField3 = new TextField();
                        TextField textField4 = new TextField();
                        TextField textField5 = new TextField();
                        textField3.setLayoutY(80);
                        textField3.setLayoutX(100);
                        textField4.setLayoutY(80);
                        textField4.setLayoutX(300);
                        textField5.setLayoutY(80);
                        textField5.setLayoutX(500);
                        Button button = new Button("Adauga");
                        button.setLayoutY(80);
                        button.setLayoutX(1000);


                        button.setOnMouseClicked(u -> {
                            try {
                                Statement st1 = DBAConn.connection.createStatement();

                                String query1 =
                                        String.format("insert into actiune values(null, '%s', '%s','%s','%s', '%s' ,'%s','%s');",
                                                textField3.getText(),
                                                textField4.getText(),
                                                textField5.getText(),
                                                textField6.getText(),
                                                textField7.getText(),
                                                textField8.getText(),
                                                textField9.getText()


                                        );
                                st1.execute(query1);

                                textField3.clear();
                                textField4.clear();
                                textField5.clear();
                                textField6.clear();
                                textField7.clear();
                                textField8.clear();
                                textField9.clear();

                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }

                        });
                        AnchorPane anchorPane2 = new AnchorPane();
                        anchorPane2.getChildren().addAll(label, label1, label2, textField3, textField4, textField5, label6, label7, label8, label9, textField6, textField7, textField8, textField9, button);
                        stackPane1.getChildren().add(anchorPane2);

                    });

                    Button button25 = new Button("Recenzii");
                    button25.setPrefHeight(79);
                    button25.setPrefWidth(299);
                    button25.setLayoutX(0);
                    button25.setLayoutY(220);


                    data = FXCollections.observableArrayList();
                    Statement st2 = DBAConn.connection.createStatement();
                    ResultSet rs3 = st2.executeQuery("SELECT * FROM rating");
                    for (int i = 1; i < rs3.getMetaData().getColumnCount(); i++) {
                        final int j = i;
                        TableColumn col = new TableColumn(rs3.getMetaData().getColumnName(i + 1));
                        col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));
                        tableView3.getColumns().addAll(col);
                        col.setMinWidth(266);
                    }


                    button25.setOnMouseClicked(f -> {
                        tableView1.getItems().clear();
                        tableView3.getItems().clear();
                        stackPane1.getChildren().clear();
                        AnchorPane anchorPane2 = new AnchorPane();
                        AnchorPane anchorPane3 = new AnchorPane();


                        tableView3.setPrefSize(800, 605);
                        tableView3.setLayoutY(4);
                        tableView3.setLayoutX(4);


                        try {
                            String SQL1 = "SELECT* FROM rating";
                            ResultSet rs1 = st2.executeQuery(SQL1);


                            while (rs1.next()) {
                                ObservableList<String> row = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) {
                                    row.add(rs1.getString(i));

                                }

                                data.add(row);

                            }

                            tableView3.setItems(data);


                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        }


                        Label label = new Label("Adaugati o recenzie:");
                        Label label2 = new Label();
                        label2.setFont(Font.font(null, FontWeight.BOLD, 30));


                        Label label1 = new Label("Nume:");
                        Rating rating1 = new Rating();
                        TextArea textArea2 = new TextArea();
                        TextArea textArea1 = new TextArea();
                        Button button1 = new Button("Submit");
                        anchorPane2.getChildren().add(rating1);


                        button1.setStyle("-fx-background-color:\n" +
                                "        #c3c4c4,\n" +
                                "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                                "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n" +
                                "    -fx-background-radius: 30;\n" +
                                "    -fx-background-insets: 0,1,1;\n" +
                                "    -fx-text-fill: black;\n" +
                                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");

                        StackPane stackPane2 = new StackPane();
                        StackPane stackPane3 = new StackPane();
                        StackPane stackPane4 = new StackPane();
                        StackPane stackPane5 = new StackPane();

                        stackPane4.getChildren().add(anchorPane2);

                        stackPane2.setPrefSize(800, 605);
                        stackPane3.setPrefSize(250, 190);
                        stackPane4.setPrefSize(250, 50);
                        stackPane5.setPrefSize(250, 50);
                        stackPane5.getChildren().add(textArea2);
                        stackPane2.getChildren().add(tableView3);
                        stackPane3.getChildren().add(textArea1);

                        anchorPane3.getChildren().addAll(stackPane2, stackPane3, stackPane4, button1, label, stackPane5, label1, label2, tableView3);
                        stackPane2.alignmentProperty();
                        label.setLayoutX(850);
                        label.setLayoutY(10);
                        label.setFont(Font.font("Cambria", 32));

                        label1.setLayoutX(900);
                        label1.setLayoutY(120);
                        label2.setLayoutX(1100);
                        label2.setLayoutY(258);
                        label1.setFont(Font.font("Italic", 23));

                        textArea1.setWrapText(true);


                        textArea2.setWrapText(true);

                        button1.setLayoutY(520);
                        button1.setLayoutX(1140);
                        button1.setScaleY(1.5);
                        button1.setScaleX(1.5);
                        stackPane2.setLayoutY(4);
                        stackPane2.setLayoutX(5);
                        stackPane3.setLayoutY(360);
                        stackPane3.setLayoutX(870);
                        stackPane4.setLayoutY(260);
                        stackPane4.setLayoutX(870);
                        stackPane5.setLayoutY(160);
                        stackPane5.setLayoutX(870);
                        label.setPrefSize(350, 50);

                        rating1.setUpdateOnHover(false);
                        rating1.setPartialRating(false);
                        rating1.setMax(5);


                        stackPane1.getChildren().addAll(anchorPane3);

                        stackPane2.setBackground(new Background(new BackgroundFill(BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                        label.setBackground(new Background(new BackgroundFill(RED, CornerRadii.EMPTY, Insets.EMPTY)));
                        rating1.ratingProperty().addListener(new ChangeListener<Number>() {
                            @Override
                            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                                label2.setText("" + newValue);


                            }
                        });


                        button1.setOnMouseClicked(g -> {


                            try {
                                Statement st1 = DBAConn.connection.createStatement();

                                String query1 =
                                        String.format("insert into rating values(null, '%s', '%s','%s');",
                                                textArea2.getText(),
                                                rating1.getRating(),
                                                textArea1.getText()


                                        );
                                st1.execute(query1);


                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            tableView3.setItems(data);


                        });
                        tableView3.getItems();

                    });


                    Button button26 = new Button("Contact");
                    button26.setPrefHeight(79);
                    button26.setPrefWidth(299);
                    button26.setLayoutX(0);
                    button26.setLayoutY(300);
                    button26.setOnMouseClicked(f -> {
                        stackPane1.getChildren().clear();

                        RadioButton radioButton = new RadioButton("Secretariat");
                        RadioButton radioButton1 = new RadioButton("Cancelarie");





                        Label label = new Label("Program");
                        label.setFont(Font.font(null, FontWeight.BOLD, 32));
                        label.setLayoutX(900);
                        label.setLayoutY(50);
                        Label label1 = new Label("L- V : 9:00 - 18:00");

                        label1.setLayoutY(100);
                        label1.setLayoutX(900);
                        Label label2 = new Label("Sambata : 9:00 - 13:00");
                        label2.setLayoutX(900);
                        label2.setLayoutY(150);
                        Label label6 = new Label("Duminica : Indisponibil");
                        label6.setLayoutX(900);
                        label6.setLayoutY(200);
                        Label label7 = new Label("Sediu Central");
                        label7.setFont(Font.font(null, FontWeight.BOLD, 32));
                        label7.setLayoutX(900);
                        label7.setLayoutY(400);
                        Label label8 = new Label("Strada Ion Mincu, Nr. 36 Corp E2, Bucuresti");
                        label8.setLayoutX(900);
                        label8.setLayoutY(450);
                        Label label9 = new Label("FAX: 021 212 212 ");
                        label9.setLayoutY(500);
                        label9.setLayoutX(900);
                        Label label10 = new Label("Telefon Mobil: ");
                        Label label11 = new Label("Telefon Fix: ");
                        Label label12 = new Label("Fax: ");
                        Label label13 = new Label("Mail: ");

                        Line line = new Line();
                        line.setStartY(50);
                        line.setEndY(600);
                        line.setStartX(200);
                        line.setEndX(200);

                        Line line1 = new Line();
                        line1.setStartY(50);
                        line1.setEndY(600);
                        line1.setStartX(850);
                        line1.setEndX(850);

                        label10.setLayoutX(300);
                        label10.setLayoutY(100);
                        label11.setLayoutX(300);
                        label11.setLayoutY(200);

                        label12.setLayoutX(300);
                        label12.setLayoutY(300);
                        label13.setLayoutX(300);
                        label13.setLayoutY(400);

                        radioButton.setLayoutY(100);
                        radioButton.setLayoutX(20);

                        radioButton1.setLayoutY(350);
                        radioButton1.setLayoutX(20);

                        Label label14 = new Label("");
                        Label label15 = new Label("");
                        Label label16 = new Label("");
                        Label label17 = new Label("");
                        radioButton.setOnMouseClicked(r -> {
                            label14.setText("0766921221");
                            label15.setText("0237123456");
                            label16.setText("0233928374");
                            label17.setText("secretariat.nicolae.iorga@yahoo.ro");
                        });
                        radioButton1.setOnMouseClicked(t -> {
                            label14.setText("0769283746");
                            label15.setText("0237182736");
                            label16.setText("0238472638");
                            label17.setText("cancelarie.nicolae.iorga@yahoo.ro");
                        });
                        ToggleGroup group2 = new ToggleGroup();
                        radioButton.setToggleGroup(group2);
                        radioButton1.setToggleGroup(group2);


                        label14.setLayoutX(500);
                        label14.setLayoutY(100);

                        label15.setLayoutX(500);
                        label15.setLayoutY(200);

                        label16.setLayoutX(500);
                        label16.setLayoutY(300);

                        label17.setLayoutX(500);
                        label17.setLayoutY(400);

                        AnchorPane anchorPane4 = new AnchorPane();
                        anchorPane4.getChildren().addAll(radioButton, radioButton1, label, label1, label2, label6, label7, label8, label9, label10, label11, label12, label13, label14, label15, label16, label17, line, line1);


                        stackPane1.getChildren().addAll(anchorPane4);


                    });


                    Button button20 = new Button("Logout");
                    button20.setPrefWidth(140);
                    button20.setPrefHeight(50);
                    button20.setLayoutX(155);
                    button20.setLayoutY(550);

                    button20.setOnMouseClicked(i -> {
                        Proiect loginPage = new Proiect();
                        loginPage.start(primaryStage);
                    });

                    button20.setStyle("-fx-background-color:\n" +
                            "        #c3c4c4,\n" +
                            "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                            "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n" +
                            "    -fx-background-radius: 30;\n" +
                            "    -fx-background-insets: 0,1,1;\n" +
                            "    -fx-text-fill: black;\n" +
                            "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");

                    anchorPane.getChildren().addAll(label5, button20, button21, button24, button25, button26);
                    stackPane.getChildren().add(anchorPane);
                    stackPane1.getChildren().addAll(tableView1);


                    Group group4 = new Group();
                    group4.getChildren().addAll(stackPane1, stackPane6);


                    HBox hbox2 = new HBox();
                    hbox2.getChildren().addAll(group1, group4);
                    hbox2.setSpacing(20);


                    vbox.getChildren().addAll(labelTitle2, labelTitle3, hBox, scroll, label3, label4, hbox2);
                    group1.getChildren().addAll(stackPane);


                    primaryStage.setHeight(900);
                    primaryStage.setWidth(1600);
                    primaryStage.setScene(scene);
                    primaryStage.show();


                } else {
                    labelres.setText("nu e corect");
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        buttonLog.setTextFill(BLUE);
        buttonLog.setLayoutX(180);
        buttonLog.setLayoutY(340);


        Button button3 = new Button("Inregistrare");
        group.getChildren().add(button3);
        button3.setOnMouseClicked(e -> {
            RegisterPage registerPage = new RegisterPage();
            registerPage.build(primaryStage);
        });


        button3.setTextFill(BLUE);
        button3.setLayoutX(310);
        button3.setLayoutY(340);


        group.getChildren().add(labelres);
        labelres.setLayoutX(10);
        labelres.setLayoutY(300);

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

