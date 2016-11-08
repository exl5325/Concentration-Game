package gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.Card;
/**
 * Created by Eric LIn on 11/3/2016.
 */
public class ConcentrationGUI extends Application implements Observer{
    private Card[][] board = new Card[4][4];
    private Card lastCardFlipped = null;

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane b = new BorderPane();
        b.setCenter(makeButtonPane());
        Label alabel = new Label( "Pick a card!" );
        alabel.setFont( new Font( "Helvetica", 16 ) );
        alabel.setAlignment( Pos.CENTER );
        b.setTop(alabel);
        b.setBottom(this.makeBottomButtons());
        Scene scene = new Scene(b);
        primaryStage.setTitle("Concentration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void init() throws Exception{
        int boardlen = 16;
        int current = 1;
        while(current > boardlen){
            for(int row = 0; row < 4; row++){
                for(int col = 0; col < 4; col++){
                    Card c = new Card(current, false);
                    board[row][col] = c;
                }
            }
            current++;
        }
    }

    private Pane makeButtonPane(){
        GridPane grid = new GridPane();
        int buttons = 16;
        for(int x=1; x < buttons; x++){
            for(int rows = 0; rows < 4; rows++ ){
                for(int col = 0; col < 4; col++){
                    Button btn = new Button();
                    Image img1 = new Image(getClass().getResourceAsStream("img1.png"));
                    btn.setGraphic(new ImageView(img1));
                    btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    grid.add(btn, rows, col);
                }
            }
        }
        return grid;
    }

    private Pane makeBottomButtons(){
        GridPane grid = new GridPane();
        Button cheat = new Button("Cheat");
        Button undo = new Button("Undo");
        Button reset = new Button("Reset");
        grid.add(cheat, 0, 0);
        grid.add(undo, 1, 0);
        grid.add(reset, 2, 0);
        grid.setAlignment(Pos.CENTER);
        return grid;

    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {

    }

    public static void main(String[] args) {
        Application.launch(args);

    }
}
