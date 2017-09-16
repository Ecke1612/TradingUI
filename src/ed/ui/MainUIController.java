package ed.ui;

import ed.player.Hero;
import ed.tradingObjects.TradingObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class MainUIController {

    @FXML
    BorderPane borderpane;
    @FXML
    VBox vbox_center_up;
    @FXML
    VBox vbox_center_down;
    @FXML
    HBox hbox_bottom_right;

    private Hero hero;
    private ArrayList<TradingObject> tradingList = new ArrayList<>();

    public MainUIController() {
        tradingList.add(new TradingObject("Google", 375, 122, 15, 0, this));
        hero = new Hero(1000);
    }

    public void initialize() {
        //hero.askName();
        hero.setName("Eike");
        hbox_bottom_right.getChildren().add(hero.getUI());
        vbox_center_up.getChildren().clear();
        for(TradingObject tr : tradingList) {
            vbox_center_up.getChildren().add(tr.getUI());
        }
    }

    public void update() {
        hbox_bottom_right.getChildren().clear();
        hbox_bottom_right.getChildren().add(hero.getUI());
        vbox_center_up.getChildren().clear();
        for(TradingObject tr : tradingList) {
            vbox_center_up.getChildren().add(tr.getUI());
        }
    }

    public void buy(TradingObject tr){
        hero.addTradeObject(tr);
        update();
    }

}
