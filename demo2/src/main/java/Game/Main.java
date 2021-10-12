package Game;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends Application {

    GameMenu gamemenu;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Pane root = new Pane();
        root.setPrefSize(900,700);
        InputStream IS = Files.newInputStream(Paths.get("C:\\Users\\alexa\\IdeaProjects\\Frogger\\src\\main\\java\\Images\\Menu.png"));
        Image img = new Image(IS);
        IS.close();
        ImageView imgV = new ImageView(img);
        imgV.setFitHeight(700);
        imgV.setFitWidth(900);

        gamemenu = new GameMenu();
        //menu.setOpacity(0.7);

        root.getChildren().addAll(imgV, gamemenu);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public class GameMenu extends Parent {


        GameMenu(){
            Pane root = new Pane();

            Rectangle rt = new Rectangle(900,700);
            rt.setFill(Color.BLACK);

            root.setPrefSize(900,700);
            VBox menu = new VBox(10);
            menu.setLayoutX(365);
            menu.setLayoutY(550);
            Button START = new Button("START");
            START.setOnMouseClicked(event ->
                    {
                        FadeTransition ft = new FadeTransition(Duration.seconds(0.75), gamemenu);
                        ft.setFromValue(1);
                        ft.setToValue(0);
                        ft.setOnFinished(evt -> gamemenu.setVisible(false));
                        ft.play();
                    }
            );
            menu.getChildren().addAll(START);
            root.getChildren().addAll(menu);
            getChildren().addAll(root);
        }
    }
    public static class Button extends StackPane {
        Button(String titre){
            Text text = new Text(titre);
            text.setFont(Font.font(40));
            text.setFill(Color.BLACK);

            Rectangle r = new Rectangle(250, 30);
            r.setFill(Color.RED);


            getChildren().addAll(r, text);
        }
    }
}