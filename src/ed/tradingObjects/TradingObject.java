package ed.tradingObjects;

import ed.main.Main;
import ed.ui.MainUIController;
import global.Style;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;

public class TradingObject {

    private String name;
    private double value;
    private double growth;
    private int availablity;
    private int index;
    private int heroQuantity = 0;
    private int heroDealTime = 0;
    private double heroBuyValue = 0;
    private Style style = new Style();
    private MainUIController mainUIController;

    public TradingObject(String name, double value, double growth, int availablity, int index, MainUIController mainUIController) {
        this.name = name;
        this.value = value;
        this.growth = growth;
        this.availablity = availablity;
        this.index = index;
        this.mainUIController = mainUIController;
    }

    public HBox getUI() {
        Insets ins = new Insets(8);

        HBox hboxMain = new HBox();

        Label label_name = new Label(name + ": ");
        label_name.setFont(style.getMainFont());

        Label label_value = new Label("Wert: " + value);
        label_value.setFont(style.getMainFont());

        Label label_growth = new Label("Wachstum: " + growth);
        label_growth.setFont(style.getMainFont());

        Label label_av = new Label("Verfügbarkeit: " + availablity);
        label_av.setFont(style.getMainFont());

        Button btn_buy = new Button("Kaufen");
        //btn_buy.setPadding(ins);
        btn_buy.setFont(style.getMainFont());
        btn_buy.setOnAction(event -> buyUI());

        hboxMain.getChildren().addAll(label_name, label_value, label_growth, label_av, btn_buy);
        hboxMain.setSpacing(15);
        return hboxMain;
    }

    public void buyUI() {
        Stage stageBuy = new Stage();
        stageBuy.initOwner(Main.primaryStage);
        stageBuy.initModality(Modality.APPLICATION_MODAL);

        int space = 5;

        VBox vbox = new VBox();
        vbox.setSpacing(space);
        vbox.setPadding(new Insets(10));

        HBox hboxTop = new HBox();
        hboxTop.setSpacing(space);

        HBox hboxDown = new HBox();
        hboxDown.setSpacing(space);

        HBox hboxBtn = new HBox();
        hboxBtn.setSpacing(space);

        Scene scene = new Scene(vbox);
        stageBuy.setScene(scene);

        Label label_av = new Label("Wieviele:          ");

        Label label_deal = new Label("Wie lange:       ");

        //ChoiceBox für Verfügbarkeit erstellen-------------------------------------------------------------------------
        ArrayList<Integer> avList = new ArrayList<>();
        for(int i = 0; i < availablity; i++) {
            avList.add(i+1);
        }
        ChoiceBox cbAv = new ChoiceBox();
        cbAv.setItems(FXCollections.observableArrayList(avList));
        cbAv.getSelectionModel().select(0);
        //--------------------------------------------------------------------------------------------------------------

        //ChoiceBox für Rundenzeit erstellen-------------------------------------------------------------------------
        ArrayList<Integer> roundCountList = new ArrayList<>();
        for(int i = 2; i <= 10 ; i++) {
            roundCountList.add(i);
        }
        ChoiceBox cbRCL = new ChoiceBox();
        cbRCL.setItems(FXCollections.observableArrayList(roundCountList));
        cbRCL.getSelectionModel().select(0);
        //--------------------------------------------------------------------------------------------------------------

        Button btn_abort = new Button("Abbrechen");

        Button btn_okay = new Button("Kaufen");

        btn_abort.setOnAction(event -> stageBuy.close());

        btn_okay.setOnAction(event -> {
            int heroQuantityTemp = (int)cbAv.getSelectionModel().getSelectedItem();
            heroDealTime = (int)cbRCL.getSelectionModel().getSelectedItem();
            availablity -= heroQuantityTemp;
            heroQuantity += heroQuantityTemp;
            mainUIController.buy(this);
            stageBuy.close();
        });

        hboxTop.getChildren().addAll(label_av, cbAv);
        hboxDown.getChildren().addAll(label_deal, cbRCL);
        hboxBtn.getChildren().addAll(btn_abort, btn_okay);

        vbox.getChildren().addAll(hboxTop, hboxDown, hboxBtn);

        stageBuy.showAndWait();
    }

    private void buy() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getGrowth() {
        return growth;
    }

    public void setGrowth(double growth) {
        this.growth = growth;
    }

    public int getAvailablity() {
        return availablity;
    }

    public void setAvailablity(int availablity) {
        this.availablity = availablity;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getHeroQuantity() {
        return heroQuantity;
    }

    public void setHeroQuantity(int boughtByHero) {
        this.heroQuantity = boughtByHero;
    }

    public int getHeroDealTime() {
        return heroDealTime;
    }

    public void setHeroDealTime(int heroDealTime) {
        this.heroDealTime = heroDealTime;
    }

    public double getHeroBuyValue() {
        return heroBuyValue;
    }

    public void setHeroBuyValue(double heroBuyValue) {
        this.heroBuyValue = heroBuyValue;
    }
}
