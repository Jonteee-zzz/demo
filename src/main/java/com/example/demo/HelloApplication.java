package com.example.demo;

import javafx.application.Application;
import javafx.css.Size;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.awt.Point;
import javafx.scene.image.Image;



import javafx.scene.canvas.Canvas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    private static final int  WIDTH = 800;
    private static final int   HEIGHT = 850;
    private static final int   ROWS = 20;
    private static final int   COLUMNS = ROWS;
    private static final int   SQUARE_SIZE = WIDTH/ROWS;
    private static final String[] FOOD_IMAGE = new String[]{

            "img/ic_apple.png",
            "img/ic_berry.png",
            "img/ic_cherry.png",
            "img/ic_coconut_.png",
            "img/ic_peach.png",
            "img/ic_watermelon.png",
            "img/ic_pomegranate.png",
            "img/ic_tomato.png"
    };

//konstanter , bredd höjd , fönster , samt bilder


    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
//riktiningsmoment , tangentbordsläsare
    private GraphicsContext gc;
    private List<Point> snakeBody = new ArrayList();
    private Point snakeHead;
    private Image foodImage;

    private int foodX;
    private int foodY;

    private boolean gameOver;

    private int currentDirection;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Snake");
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();
        //Denna rad hämtar grafikkontexten för canvasen, vilket möjliggör att rita grafik på canvasen
        for(int i=0; i<3; i++){
            snakeBody.add(new Point(5, ROWS/2));
        }

        //Denna loop initialiserar ormens kropp med tre punkter, alla belägna på samma rad men på olika kolumner.
        generateFood();
        run();


    }
    private void run() {
        drawBackground(gc);
        drawFood(gc);
        //Denna rad anropar metoden generateFood() för att slumpmässigt generera maten på spelrutnätet.
    }




    //Denna metod ritar bakgrunden på spelrutnätet genom att fylla varje ruta med alternerande färger.
    private void drawBackground(GraphicsContext gc){
        for (int i=0; i<ROWS; i++ ){
            for (int j=0; j<COLUMNS; j++){
                if ((i+j) %2 == 0  ){
                    gc.setFill(Color.web("AAD751"));
                } else {
                    gc.setFill(Color.web("A2D149"));
                }
                gc.fillRect( i * SQUARE_SIZE ,  j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void generateFood(){
        start:
        while (true){
            foodX = (int)(Math.random() * ROWS );
            foodY = (int)(Math.random() * COLUMNS );
            for (Point snake: snakeBody){
                if(snake.getX() == foodX && snake.getY() == foodY){
                    continue start;
                }
            }
            foodImage = new Image( FOOD_IMAGE[(int)(Math.random() * FOOD_IMAGE.length)]);
            break;

            //Denna metod genererar maten på en slumpmässig position på spelrutnätet, och ser till att den inte överlappar med ormens kropp.
        }
    }
    private void drawFood(GraphicsContext gc){
        gc.drawImage(foodImage, foodX * SQUARE_SIZE, foodY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        //Denna metod ritar maten på canvasen på dess nuvarande position.
    }

    public static void main(String[] args) {
        launch();
    }
}