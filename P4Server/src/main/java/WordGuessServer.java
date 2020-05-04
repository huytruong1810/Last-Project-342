/**
 *
 * Authors: Huy Truong, Mallika Patil, Maryam Ahmed, Simrah Shaik
 * NetID: thuyng2, mpatil5, mahmed80, sshaik28

 * Description:  Create a server that plays a word guessing game with each client that connects to that server
 * (similar to Wheel of Fortune or Hangman).
 *
 *
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class WordGuessServer extends Application {


    ListView<String> listItems;
    Server serverConnection;
    ArrayList<ClientInfo> clientInfos = new ArrayList<>();

    public static void main(String[] args) {

        launch(args);

    }


    //feel free to remove the starter code from this method
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("[Server] Word Guess Game");

        ImageView bigLogo1 = new ImageView(new Image("logo.png", 100, 100, true, true));
        bigLogo1.setRotate(45);

        ImageView bigLogo2 = new ImageView(new Image("logo.png", 66, 66, true, true));
        bigLogo2.setRotate(90);

        ImageView bigLogo3 = new ImageView(new Image("logo.png", 33, 33, true, true));
        bigLogo3.setRotate(135);

        ImageView bigLogo4 = new ImageView(new Image("logo.png", 66, 66, true, true));
        bigLogo2.setRotate(225);

        HBox logo = new HBox(7, bigLogo3, bigLogo2, bigLogo1, bigLogo4);

        ImageView connect = new ImageView(new Image("connect.png", 25, 25, true, true));
        connect.setFitWidth(25);
        connect.setFitHeight(25);

        Button launch = new Button("Launch");
        launch.setGraphic(connect);
        launch.setStyle(
                "-fx-background-radius: 100em;" +
                        "-fx-min-height: 35px; " +
                        "-fx-max-height: 35px;"
        );

        TextField portField = new TextField();
        portField.setMaxWidth(100);

        launch.setOnAction(e -> {

            ImageView smallLogo1 = new ImageView(new Image("logo.png", 45, 45, true, true));
            ImageView smallLogo2 = new ImageView(new Image("logo.png", 35, 35, true, true));
            ImageView smallLogo3 = new ImageView(new Image("logo.png", 25, 25, true, true));
            ImageView smallLogo4 = new ImageView(new Image("logo.png", 15, 15, true, true));

            smallLogo1.setRotate(45);
            smallLogo2.setRotate(90);
            smallLogo3.setRotate(135);
            smallLogo4.setRotate(225);

            VBox smallLogos = new VBox(10, smallLogo1, smallLogo2, smallLogo3, smallLogo4);

            listItems = new ListView<String>();
            VBox clientArea = new VBox(20);
            ArrayList<Text> statusFields = new ArrayList<>();


            int portNum = Integer.parseInt(portField.getText());

            listItems.getItems().add("Server listens on port " + portNum);

            serverConnection = new Server(data -> {
                Platform.runLater (() -> {

                            int clientNum = data.clientNum;

                            statusFields.clear();
                            clientArea.getChildren().clear();

                            switch (data.gameMode) {
                                case -1:
                                    listItems.getItems().add("Somebody crashes!");
                                    break;
                                case 0:
                                    listItems.getItems().add("Client " + clientNum + " joined!");
                                    clientInfos.add(new ClientInfo(clientNum));
                                    break;
                                case 1:
                                    ClientInfo thisClient = clientInfos.get(clientNum - 1);

                                    thisClient.numGuessLeft = data.numRemainGuess;
                                    thisClient.numAttemptLeft = data.numRemainAttempt;
                                    thisClient.categoryChoice = data.category;

                                    if (thisClient.givenWord.equals("")) {
                                        listItems.getItems().add("Client " + clientNum + " chose " + categoryName(data.category));
                                        listItems.getItems().add("Client " + clientNum + " is given " + WordRepository.holdString);
                                        thisClient.givenWord = WordRepository.holdString;
                                        break;
                                    }
                                    listItems.getItems().add("Client " + clientNum + " guess letter " + data.guess);

                                    int charLocation = thisClient.givenWord.indexOf(data.guess);
                                    if (charLocation == -1) {
                                        listItems.getItems().add("Letter " + data.guess + " is not in " + thisClient.givenWord);
                                        listItems.getItems().add("Client " + clientNum + "'s number of\nguesses is now " + data.numRemainGuess);
                                    }
                                    else {
                                        listItems.getItems().add("Letter " + data.guess + " is in " + thisClient.givenWord);
                                        listItems.getItems().add("Sending confirmation to client " +clientNum);

                                        StringBuilder sb = new StringBuilder(thisClient.givenWord);
                                        sb.replace(charLocation, charLocation + 1, " ");
                                        thisClient.givenWord = sb.toString();
                                    }

                                    break;

                            }

                            for (int i = 0; i < clientInfos.size(); ++i) {

                                ClientInfo theClient = clientInfos.get(i);

                                Text categoryField = new Text("Category: " + theClient.categoryChoice);
                                Text wordField = new Text("Word: " + theClient.givenWord);
                                Text numGuessField = new Text("Number of guesses left: " + theClient.numGuessLeft);
                                Text numAttemptField = new Text("Number of attempt left: " + theClient.numAttemptLeft);
                                Text statusField = new Text("Player is playing...");

                                if (playerHasWon(theClient)) {
                                    statusField.setText("Player won!");
                                }
                                else if (playerHasLost(theClient)) {
                                    statusField.setText("Player lost!");
                                }

                                statusFields.add(statusField);
                                VBox clientBox = new VBox(5, new Text("Player " + theClient.clientNum), categoryField, wordField, numGuessField, numAttemptField, statusField);
                                clientArea.getChildren().add(clientBox);

                            }

                        }
                ); }, portNum);

            HBox layoutBox = new HBox(20, smallLogos, listItems, clientArea);
            layoutBox.setPadding(new Insets(10));

            primaryStage.setScene(new Scene(new ScrollPane(layoutBox),600,400));

        });

        VBox portBox = new VBox(10, logo, new Text("Choose a port to listen on"),
                new HBox(40, new Text("Port number :"), portField), launch);
        portBox.setPadding(new Insets(10));

        // START SCENE
        primaryStage.setScene(new Scene(portBox, 300, 275));
        primaryStage.show();

    }

    boolean playerHasWon (ClientInfo theClient) {
        for (int i = 0; i < theClient.givenWord.length(); ++i) {
            if (theClient.givenWord.charAt(i) != ' ')
                return false;
        }
        return true;
    }

    boolean playerHasLost (ClientInfo theClient) {
        if (theClient.numGuessLeft == 0)
            return true;
        return false;
    }

    String categoryName (char c) {
        if (c == 'D')
            return "Disease";
        if (c == 'M')
            return "Mythical Creature";
        if (c == 'P')
            return "Programming Language";
        return "Error";
    }

}
