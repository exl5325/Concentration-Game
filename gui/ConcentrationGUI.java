package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import model.ConcentrationModel;

/**
 * Created by Eric LIn on 11/3/2016.
 */
public class ConcentrationGUI extends Application implements Observer{
    private ConcentrationModel model = new ConcentrationModel();
    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<Card> cards = model.getCards();
    private ArrayList<String> images = new ArrayList<>();
    Label alabel = new Label( "Pick a card!" );
    private final Label counter = new Label("Total Moves: 0");



    public ConcentrationGUI(){
    }

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
        alabel.setFont( new Font( "Helvetica", 14 ) );
        alabel.setAlignment( Pos.CENTER_RIGHT );
        b.setTop(alabel);
        b.setBottom(this.makeBottomButtons());
        images.add("img2.png");
        images.add("img3.png");
        images.add("img4.png");
        images.add("img5.png");
        images.add("img6.png");
        images.add("img7.png");
        images.add("img8.png");
        images.add("img9.png");
        Scene scene = new Scene(b);
        primaryStage.setTitle("Concentration");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void init() throws Exception{
        this.model.reset();
        model.addObserver(this);
    }

    private Pane makeButtonPane(){
        GridPane grid = new GridPane();
        for(int rows = 0; rows < 4; rows++ ){
            for(int col = 0; col < 4; col++){
                Button btn = new Button();
                Image img1 = new Image(getClass().getResourceAsStream("img1.png"));
                btn.setGraphic(new ImageView(img1));
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                int n = rows + col * 4;
                System.out.println("N:" + n);
                btn.setOnAction((event) -> {model.selectCard(n);});
                buttons.add(btn);
                grid.add(btn, rows, col);
            }
        }
        return grid;
    }


    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object. (not used)
     * @param arg an argument passed to the <code>notifyObservers</code> (not used)
     */
    @Override
    public void update(Observable o, Object arg) {
        counter.setText(String.format("Total Moves: " + model.getMoveCount()));
        for(Button b:buttons){
            int i = buttons.indexOf(b);
            int j = cards.get(i).getNumber();
            if(j != -1){
                alabel.setText("Pick the second card!");
                switch (j){
                    case 0:
                        Image img1 = new Image(getClass().getResourceAsStream(images.get(0)));
                        b.setGraphic(new ImageView(img1));
                        break;
                    case 1:
                        Image img2 = new Image(getClass().getResourceAsStream(images.get(1)));
                        b.setGraphic(new ImageView(img2));
                        break;
                    case 2:
                        Image img3 = new Image(getClass().getResourceAsStream(images.get(2)));
                        b.setGraphic(new ImageView(img3));
                        break;
                    case 3:
                        Image img4 = new Image(getClass().getResourceAsStream(images.get(3)));
                        b.setGraphic(new ImageView(img4));
                        break;
                    case 4:
                        Image img5 = new Image(getClass().getResourceAsStream(images.get(4)));
                        b.setGraphic(new ImageView(img5));
                        break;
                    case 5:
                        Image img6 = new Image(getClass().getResourceAsStream(images.get(5)));
                        b.setGraphic(new ImageView(img6));
                        break;
                    case 6:
                        Image img7 = new Image(getClass().getResourceAsStream(images.get(6)));
                        b.setGraphic(new ImageView(img7));
                        break;
                    case 7:
                        Image img8 = new Image(getClass().getResourceAsStream(images.get(7)));
                        b.setGraphic(new ImageView(img8));
                        break;
                }
            }
            else{
                Image img1 = new Image(getClass().getResourceAsStream("img1.png"));
                b.setGraphic(new ImageView(img1));
            }
        }
    }
    private Pane makeBottomButtons(){
        HBox grid = new HBox();
        Button undo = new Button("Undo");
        undo.setOnAction((javafx.event.ActionEvent event) -> model.undo());
        Button cheat = new Button("Cheat");
        cheat.setOnAction((javafx.event.ActionEvent event) -> model.cheat());
        Button reset = new Button("Reset");
        reset.setOnAction((javafx.event.ActionEvent event) -> model.reset());
        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);
        grid.getChildren().addAll(undo, cheat, reset, space, counter);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
