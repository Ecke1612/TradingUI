package ed.player;

import ed.main.Main;
import ed.tradingObjects.TradingObject;
import global.Style;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Hero {

    private String name = "";
    private double money;
    private ArrayList<TradingObject> tradingList = new ArrayList<>();
    private Style style = new Style();

    public Hero(double money) {
        this.money = money;
    }

    public void askName() {
        Stage stage = new Stage();
        stage.initOwner(Main.primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(5);

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(5);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);

        Label labelName = new Label("Wie heißt du? ");
        labelName.setFont(style.getMainFont());

        TextField textField = new TextField();

        Button btn_okay = new Button("Okay");
        btn_okay.setFont(style.getMainFont());
        btn_okay.setOnAction(event -> {
            if(textField.getText().equals("")){
                System.out.println("Etwas eingeben!");
            } else {
                name = textField.getText();
                stage.close();
            }
        });

        hbox.getChildren().addAll(labelName, textField);
        vBox.getChildren().addAll(hbox, btn_okay);

        stage.showAndWait();
    }

    public VBox getUI() {

        VBox vboxmain = new VBox();
        VBox vboxAnlagen = new VBox();
        vboxAnlagen.setSpacing(5);

        HBox hboxheroStats = new HBox();
        hboxheroStats.setSpacing(10);
        hboxheroStats.setPadding(new Insets(15));

        //Anlagen Stats-------------------------------------------------------------------------------------------------
        for(TradingObject tr : tradingList) {
            Label label = new Label();
            label.setFont(style.getSemiMainFont());
            label.setText("Name: " + tr.getName() + "; Wert: " + tr.getValue() +
                    "; Kaufwert: " + tr.getHeroBuyValue() + "; Besitz: " + tr.getHeroQuantity() + "; Laufzeit: " + tr.getHeroDealTime());

            vboxAnlagen.getChildren().add(label);
        }
        //--------------------------------------------------------------------------------------------------------------

        //Hero Stats----------------------------------------------------------------------------------------------------
        Label label_name = new Label(name + ":   ");
        label_name.setFont(style.getMainFont());

        Label label_money = new Label("Geld: " + money);
        label_money.setFont(style.getMainFont());

        Label label_moneyInAccount = new Label("Anlagenwert: " + getMoneyInAccount());
        label_moneyInAccount.setFont(style.getMainFont());

        hboxheroStats.getChildren().addAll(label_name, label_money, label_moneyInAccount);
        hboxheroStats.setSpacing(10);
        //--------------------------------------------------------------------------------------------------------------

        vboxmain.getChildren().addAll(vboxAnlagen, hboxheroStats);

        return vboxmain;
    }

    public void addTradeObject(TradingObject tr) {
        if(tradingList.size() > 0) {
            for (TradingObject trList : tradingList) {
                if (trList.getName().equals(tr.getName())) {
                    System.out.println("bereit vorhanden");
                } else {
                    tradingList.add(tr);
                }
            }
        } else {
            tradingList.add(tr);
        }
    }

    private double getMoneyInAccount() {
        double money1 = money;
        return money1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public ArrayList<TradingObject> getTradingList() {
        return tradingList;
    }

    public void setTradingList(ArrayList<TradingObject> tradingList) {
        this.tradingList = tradingList;
    }
}
