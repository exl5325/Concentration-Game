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
public class ConcentrationGUI extends Application implements Observer {
    private ConcentrationModel model;
    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<Button> cheatButtons = new ArrayList<>();
    private ArrayList<Card> cards;
    private ArrayList<Card> cardsClone = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private Label alabel = new Label("Select the first card.");
    private final Label counter = new Label("0 Moves");
    private int cardsFaceUp = 0;

    public ConcentrationGUI() {
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
        alabel.setFont(new Font("Helvetica", 14));
        alabel.setAlignment(Pos.CENTER);
        b.setTop(alabel);
        b.setBottom(this.makeBottomButtons());
        images.add("resources/img2.png");
        images.add("resources/img3.png");
        images.add("resources/img4.png");
        images.add("resources/img5.png");
        images.add("resources/img6.png");
        images.add("resources/img7.png");
        images.add("resources/img8.png");
        images.add("resources/img9.png");
        Scene scene = new Scene(b);
        primaryStage.setTitle("Concentration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        model = new ConcentrationModel();
        this.model.reset();
        model.addObserver(this);
        cards = model.getCards();

    }

    private Pane makeButtonPane() {
        GridPane grid = new GridPane();
        for (int rows = 0; rows < 4; rows++) {
            for (int col = 0; col < 4; col++) {
                Button btn = new Button();
                Image img1 = new Image(getClass().getResourceAsStream("resources/img1.png"));
                btn.setGraphic(new ImageView(img1));
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                int n = col + rows * 4;
                btn.setOnAction((event) -> {
                    model.selectCard(n);
                });
                buttons.add(btn);
                grid.add(btn, col, rows);
            }
        }
        return grid;
    }

    private void graphics(ArrayList<Button> listOfButtons, Boolean isCheat){
        for (Button b : listOfButtons) {
            int i = listOfButtons.indexOf(b);
            int j;
            if(!isCheat){
                j = cards.get(i).getNumber();
            }
            else {
                j = cardsClone.get(i).getNumber();
            }
            if (j != -1) {
                switch (j) {
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
            } else {
                Image img1 = new Image(getClass().getResourceAsStream("resources/img1.png"));
                b.setGraphic(new ImageView(img1));
            }
        }
    }

    private int howManyFaceUp(){
        cardsFaceUp = 0;
        for(Card c:cards){
            if(c.isFaceUp()){
                cardsFaceUp++;
            }
        }
        return cardsFaceUp;
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
        counter.setText(String.format(model.getMoveCount() + " Moves"));

        if(model.howManyCardsUp() == 0){
            alabel.setText("Select the first card.");
            if(howManyFaceUp() == 16){
                alabel.setText("YOU WIN!");
            }
        }else if(model.howManyCardsUp() == 1){
            alabel.setText("Select the second card.");
        }else if(model.howManyCardsUp() == 2){
            alabel.setText("No match: Undo or select a card.");
        }
        graphics(buttons, false);
    }


    private Pane makeBottomButtons() {
        HBox grid = new HBox();
        Button undo = new Button("Undo");
        undo.setOnAction((javafx.event.ActionEvent event) -> model.undo());
        Button cheat = new Button("Cheat");
        cheat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Stage stage = new Stage();
                model.cheat();
                for(Card c:cards){
                    cardsClone.add(new Card(c));
                }
                GridPane cheater = new GridPane();
                for(Card c: cardsClone){
                    c.setFaceUp();
                }
                for (int rows = 0; rows < 4; rows++) {
                    for (int col = 0; col < 4; col++) {
                        Button btn = new Button();
                        cheatButtons.add(btn);
                        cheater.add(btn, col, rows);
                    }
                }
                graphics(cheatButtons, true);
                cardsClone.clear();
                cheatButtons.clear();
                Scene cheats = new Scene(cheater);
                stage.setScene(cheats);
                stage.setTitle("Cheat Window");
                stage.show();
            }
        });
        Button reset = new Button("Reset");
        reset.setOnAction((javafx.event.ActionEvent event) -> {
            model.reset();
            cards = model.getCards();
            alabel.setText("Select the first card.");
            cardsFaceUp = 0;
        });
        Region space = new Region();
        Region anotherSpace = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);
        HBox.setHgrow(anotherSpace, Priority.ALWAYS);
        grid.getChildren().addAll(anotherSpace, undo, cheat, reset, space, counter);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }




    public static void main(String[] args) {
        Application.launch(args);
    }
}
