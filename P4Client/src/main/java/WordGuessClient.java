/**
 *
 * Authors: Huy Truong, Mallika Patil, Maryam Ahmed, Simrah Shaik
 * NetID: thuyng2, mpatil5, mahmed80, sshaik28

 * Description:  Create a server that plays a word guessing game with each client that connects to that server
 * (similar to Wheel of Fortune or Hangman).
 *
 *
 */

import javafx.scene.control.Label;
import javafx.scene.text.Text; 
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Function;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class WordGuessClient extends Application {

	Client clientConnection;
	int numLetter = 0, clientNum, numGuess, numAttempt, charLocation;
	char category, guess;
	boolean win = false, lose = false, letterInWord;

	ArrayList<Character> letters = new ArrayList<>();
	ArrayList<Button> letterButtons = new ArrayList<>();

	Button Letter_A, Letter_B, Letter_C, Letter_D, Letter_E, Letter_F, Letter_G, Letter_H, Letter_I,
			Letter_J, Letter_K, Letter_L, Letter_M, Letter_N, Letter_O, Letter_P, Letter_Q, Letter_R,
			Letter_S, Letter_T, Letter_U, Letter_V, Letter_W, Letter_X, Letter_Y, Letter_Z;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("[Client] Word Guess Game");
		
		/* Images */ 
		Image bigLogo = new Image("logo.png", 100, 100, true, true); 
		Image smallLogo = new Image("logo.png", 35, 35, true, true); 
		Image connect = new Image("connect.png", 25, 25, true, true); 
		Image exit = new Image("exit.png", 25, 25, true, true); 
		Image plus = new Image("plus.png", 25, 25, true, true); 
		Image submit = new Image("submit.png", 25, 25, true, true);
		Image end = new Image("end.png", 25, 25, true, true); 
		Image replay = new Image("replay.png", 25, 25, true, true); 
		Image letter_a = new Image("a.png", 35, 35, true, true); 
		Image letter_b = new Image("b.png", 35, 35, true, true); 
		Image letter_c = new Image("c.png", 35, 35, true, true); 
		Image letter_d = new Image("d.png", 35, 35, true, true); 
		Image letter_e = new Image("e.png", 35, 35, true, true); 
		Image letter_f = new Image("f.png", 35, 35, true, true); 
		Image letter_g = new Image("g.png", 35, 35, true, true); 
		Image letter_h = new Image("h.png", 35, 35, true, true); 
		Image letter_i = new Image("i.png", 35, 35, true, true); 
		Image letter_j = new Image("j.png", 35, 35, true, true); 
		Image letter_k = new Image("k.png", 35, 35, true, true); 
		Image letter_l = new Image("l.png", 35, 35, true, true); 
		Image letter_m = new Image("m.png", 35, 35, true, true); 
		Image letter_n = new Image("n.png", 35, 35, true, true); 
		Image letter_o = new Image("o.png", 35, 35, true, true); 
		Image letter_p = new Image("p.png", 35, 35, true, true); 
		Image letter_q = new Image("q.png", 35, 35, true, true); 
		Image letter_r = new Image("r.png", 35, 35, true, true); 
		Image letter_s = new Image("s.png", 35, 35, true, true); 
		Image letter_t = new Image("t.png", 35, 35, true, true); 
		Image letter_u = new Image("u.png", 35, 35, true, true); 
		Image letter_v = new Image("v.png", 35, 35, true, true); 
		Image letter_w = new Image("w.png", 35, 35, true, true); 
		Image letter_x = new Image("x.png", 35, 35, true, true); 
		Image letter_y = new Image("y.png", 35, 35, true, true); 
		Image letter_z = new Image("z.png", 35, 35, true, true); 
		Image unicorn = new Image("unicorn.png", 35, 35, true, true); 
		Image disease = new Image("disease.png", 35, 35, true, true); 
		Image code = new Image("code.png", 35, 35, true, true); 
		
		/* Buttons */ 
		/* Letter Buttons */ 
		Letter_A = new Button();
		Letter_A.setGraphic(new ImageView(letter_a));
		Letter_A.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_A.setDisable(true);
		
		Letter_B = new Button();
		Letter_B.setGraphic(new ImageView(letter_b));
		Letter_B.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_B.setDisable(true);
		
		Letter_C = new Button();
		Letter_C.setGraphic(new ImageView(letter_c));
		Letter_C.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_C.setDisable(true);
		
		Letter_D = new Button();
		Letter_D.setGraphic(new ImageView(letter_d));
		Letter_D.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_D.setDisable(true);
		
		Letter_E = new Button();
		Letter_E.setGraphic(new ImageView(letter_e));
		Letter_E.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_E.setDisable(true);
		
		Letter_F = new Button();
		Letter_F.setGraphic(new ImageView(letter_f));
		Letter_F.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_F.setDisable(true);
		
		Letter_G = new Button();
		Letter_G.setGraphic(new ImageView(letter_g));
		Letter_G.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_G.setDisable(true);
		
		Letter_H = new Button();
		Letter_H.setGraphic(new ImageView(letter_h));
		Letter_H.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_H.setDisable(true);
		
		Letter_I = new Button();
		Letter_I.setGraphic(new ImageView(letter_i));
		Letter_I.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_I.setDisable(true);
		
		Letter_J = new Button();
		Letter_J.setGraphic(new ImageView(letter_j));
		Letter_J.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_J.setDisable(true);
		
		Letter_K = new Button();
		Letter_K.setGraphic(new ImageView(letter_k));
		Letter_K.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_K.setDisable(true);
		
		Letter_L = new Button();
		Letter_L.setGraphic(new ImageView(letter_l));
		Letter_L.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_L.setDisable(true);
		
		Letter_M = new Button();
		Letter_M.setGraphic(new ImageView(letter_m));
		Letter_M.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_M.setDisable(true);
		
		Letter_N = new Button();
		Letter_N.setGraphic(new ImageView(letter_n));
		Letter_N.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_N.setDisable(true);
		
		Letter_O = new Button();
		Letter_O.setGraphic(new ImageView(letter_o));
		Letter_O.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_O.setDisable(true);
		
		Letter_P = new Button();
		Letter_P.setGraphic(new ImageView(letter_p));
		Letter_P.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_P.setDisable(true);

		Letter_Q = new Button();
		Letter_Q.setGraphic(new ImageView(letter_q));
		Letter_Q.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_Q.setDisable(true);
		
		Letter_R = new Button();
		Letter_R.setGraphic(new ImageView(letter_r));
		Letter_R.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_R.setDisable(true);
		
		Letter_S = new Button();
		Letter_S.setGraphic(new ImageView(letter_s));
		Letter_S.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_S.setDisable(true);
		
		Letter_T = new Button();
		Letter_T.setGraphic(new ImageView(letter_t));
		Letter_T.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_T.setDisable(true);
		
		Letter_U = new Button();
		Letter_U.setGraphic(new ImageView(letter_u));
		Letter_U.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_U.setDisable(true);
		
		Letter_V = new Button();
		Letter_V.setGraphic(new ImageView(letter_v));
		Letter_V.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_V.setDisable(true);
		
		Letter_W = new Button();
		Letter_W.setGraphic(new ImageView(letter_w));
		Letter_W.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_W.setDisable(true);
		
		Letter_X = new Button();
		Letter_X.setGraphic(new ImageView(letter_x));
		Letter_X.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_X.setDisable(true);
		
		Letter_Y = new Button();
		Letter_Y.setGraphic(new ImageView(letter_y));
		Letter_Y.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_Y.setDisable(true);
		
		Letter_Z = new Button();
		Letter_Z.setGraphic(new ImageView(letter_z));
		Letter_Z.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
		Letter_Z.setDisable(true);
		
		/* Functional Buttons */ 
		Button Connect = new Button("Connect");
		Connect.setGraphic(new ImageView(connect));
		Connect.setStyle(
				"-fx-background-radius: 100em;" +
                "-fx-min-height: 35px; " +
                "-fx-max-height: 35px;"
        );
		
		Button Exit = new Button("");
		Exit.setGraphic(new ImageView(exit));
		Exit.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 35px; " +
                "-fx-min-height: 35px; " +
                "-fx-max-width: 35px; " +
                "-fx-max-height: 35px;"
        );
		
		Button Plus = new Button("");
		Plus.setGraphic(new ImageView(plus));
		Plus.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 35px; " +
                "-fx-min-height: 35px; " +
                "-fx-max-width: 35px; " +
                "-fx-max-height: 35px;"
        );
		
		Button Submit = new Button("");
		Submit.setGraphic(new ImageView(submit));
		Submit.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 35px; " +
                "-fx-min-height: 35px; " +
                "-fx-max-width: 35px; " +
                "-fx-max-height: 35px;"
        );
		
		Button End = new Button("End Game");
		End.setGraphic(new ImageView(end));
		End.setStyle(
				"-fx-background-radius: 100em;" +
                "-fx-min-height: 35px; " +
                "-fx-max-height: 35px;"
        );
		
		Button ExitGame = new Button("Exit Game");
		ExitGame.setGraphic(new ImageView(exit));
		ExitGame.setStyle(
				"-fx-background-radius: 100em;" +
                "-fx-min-height: 35px; " +
                "-fx-max-height: 35px;"
        );
		
		Button Replay = new Button("Restart Game");
		Replay.setGraphic(new ImageView(replay));
		Replay.setStyle(
				"-fx-background-radius: 100em;" +
                "-fx-min-height: 35px; " +
                "-fx-max-height: 35px;"
        );
		Button DiseasesButton = new Button("Diseases");
		DiseasesButton.setGraphic(new ImageView(disease));
		DiseasesButton.setStyle(
				"-fx-background-radius: 100em;" +
                "-fx-min-height: 50px; " +
                "-fx-min-width: 120px; " +
                "-fx-max-height: 50px;"
        );
		
		Button MythicalCreaturesButton = new Button("Mythical Creatures");
		MythicalCreaturesButton.setGraphic(new ImageView(unicorn));
		MythicalCreaturesButton.setWrapText(true);
		MythicalCreaturesButton.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-height: 50px; " +
                "-fx-min-width: 120px; " +
                "-fx-max-height: 50px;"
        );
		
		Button ProgrammingLanguagesButton = new Button("Programming Languages");
		ProgrammingLanguagesButton.setGraphic(new ImageView(code));
		ProgrammingLanguagesButton.setWrapText(true);
		ProgrammingLanguagesButton.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-height: 50px; " +
                "-fx-min-width: 130px; " +
                "-fx-max-height: 50px;"
        );
		
		/* Text */ 
		Text ConnectionTitle = new Text("Establish Connection");
		Text GameOverTitle = new Text("Game Over"); 
		
		/* Scene 1: User enter Port Number and IP Address */
		/* Connection Grid */ 
		GridPane ConnectionInfo = new GridPane(); 
		ConnectionInfo.setAlignment(Pos.BOTTOM_CENTER); 
		ConnectionInfo.setHgap(10); 
		ConnectionInfo.setVgap(10); 
		ConnectionInfo.setPadding(new Insets(25, 25, 25, 25)); 
		
		/* Connection Text */ 
		ConnectionInfo.setPadding(new Insets(0, 0, 0, 0)); 
        ConnectionInfo.add(ConnectionTitle, 0, 0, 2, 1); 
        
		Label IPAddress = new Label("IP Address :"); 
		ConnectionInfo.add(IPAddress, 0, 1); 
		
		TextField IP = new TextField(); 
		ConnectionInfo.add(IP, 1, 1);
		
		Label PortNumber = new Label("Port Number :");
		ConnectionInfo.add(PortNumber, 0, 2);
		
		TextField Port = new TextField(); 
		ConnectionInfo.add(Port, 1, 2);
		
		
		/* Connection Button */
		HBox Connection = new HBox(10);
		Connection.setAlignment(Pos.BOTTOM_RIGHT); 
		Connection.getChildren().add(Connect); 
		ConnectionInfo.add(Connection, 1, 4);
		
		HBox Logo = new HBox(10);
		Logo.setAlignment(Pos.BASELINE_CENTER);
		Logo.setPadding(new Insets(5, 0, 0, 0));
		Logo.getChildren().add(new ImageView(bigLogo));

		
		/* Scene 2: Game Scene */ 
		BorderPane GameLayout = new BorderPane();
		
		/* Game Header */
		HBox Header = new HBox(); 
		Header.setPadding(new Insets(5, 12, 5, 12));
		Header.setSpacing(10);
		Header.setStyle("-fx-background-color: #00bdaa");
		Header.setAlignment(Pos.BOTTOM_RIGHT);
		Header.getChildren().addAll(End, Plus, Submit, Exit, new ImageView(smallLogo));
		
		
		/* Game Player Choice */ 
		VBox GameChoices = new VBox(); 
		GameChoices.setPadding(new Insets(10));
		GameChoices.setSpacing(25); 
		GameChoices.setStyle("-fx-background-color: #f6eedf"); 
		
		/* Other Buttons */

		Plus.setOnAction(e -> {
			try {
				start(primaryStage);
			}
			catch (Exception ex) {
				System.out.println("Replay Error");
			}
		});

		Replay.setOnAction(e -> {
			try {
				start(primaryStage);
			}
			catch (Exception ex) {
				System.out.println("Replay Error");
			}
		});
		
		Exit.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Platform.exit(); 
			}
		});
		
		ExitGame.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Platform.exit(); 
			}
		});
		
		HBox Letters_FirstRow = new HBox(); 
		Letters_FirstRow.setSpacing(5);
		Letters_FirstRow.getChildren().addAll(Letter_A, Letter_B, Letter_C, Letter_D, Letter_E);
		HBox Letters_SecondRow = new HBox(); 
		Letters_SecondRow.setSpacing(5);
		Letters_SecondRow.getChildren().addAll(Letter_F, Letter_G, Letter_H, Letter_I, Letter_J); 
		HBox Letters_ThirdRow = new HBox(); 
		Letters_ThirdRow.setSpacing(5);
		Letters_ThirdRow.getChildren().addAll(Letter_K, Letter_L, Letter_M, Letter_N, Letter_O); 
		HBox Letters_FourthRow = new HBox(); 
		Letters_FourthRow.setSpacing(5);
		Letters_FourthRow.getChildren().addAll(Letter_P, Letter_Q, Letter_R, Letter_S, Letter_T);
		HBox Letters_FifthRow = new HBox(); 
		Letters_FifthRow.setSpacing(5);
		Letters_FifthRow.getChildren().addAll( Letter_U, Letter_V, Letter_W, Letter_X, Letter_Y, Letter_Z);
		HBox NumLetters = new HBox();
		NumLetters.setSpacing(5);
		

		/* Server Messages */ 
		VBox Information = new VBox(); 
		Information.setPadding(new Insets(10));
		Information.setSpacing(8); 
		Information.setStyle("-fx-background-color: #d1cebd"); 
		
		/* Display Guess */
		Circle Number = new Circle(); 
		Number.setRadius(35);
		Number.setFill(Color.web("#f4c2c2"));
		StackPane NumberStack = new StackPane();
		Text number = new Text();
		NumberStack.getChildren().addAll(Number, number);
		
		/* Display Guess */
		Circle Guess = new Circle(); 
		Guess.setRadius(35);
		Guess.setFill(Color.web("#BBDEFB"));
		StackPane GuessStack = new StackPane();
		Text guessNum = new Text("6");
		GuessStack.getChildren().addAll(Guess, guessNum);
		
		/* Display Attempt */
		Circle Attempt = new Circle(); 
		Attempt.setRadius(35);
		Attempt.setFill(Color.web("#e1ef7e"));
		StackPane AttemptStack = new StackPane();
		Text attemptNum = new Text("3");
		AttemptStack.getChildren().addAll(Attempt, attemptNum);
		
		/* Game Scores */ 
		VBox ServerMessages = new VBox(); 
		ServerMessages.setPadding(new Insets(10));
		ServerMessages.setSpacing(8); 
		ServerMessages.setPrefWidth(310);
		ServerMessages.setStyle("-fx-background-color: #f6eedf"); 
		
		ListView Messages = new ListView();
		Messages.setPrefWidth(300);
		Messages.setPrefHeight(180);
		
		ServerMessages.getChildren().addAll(new Text("Server Messages:"), Messages, new Text("Categories:"), DiseasesButton, MythicalCreaturesButton, ProgrammingLanguagesButton); 
		
		Information.getChildren().addAll(new Text("Number of Letters:"), NumberStack, new Text("Guesses Left:"), GuessStack, new Text("Attempt Left:"), AttemptStack);
		GameChoices.getChildren().addAll(new Text("Guess Word:"), NumLetters, new Text("Letter Bank:"), Letters_FirstRow, Letters_SecondRow, Letters_ThirdRow, Letters_FourthRow, Letters_FifthRow);
		GameLayout.setTop(Header);
		GameLayout.setLeft(Information);
		GameLayout.setCenter(GameChoices);
		GameLayout.setRight(ServerMessages);

		ScrollPane GameScrollPane = new ScrollPane();
		GameScrollPane.setContent(GameLayout); 
		GameScrollPane.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		GameScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		/* Scene 3: Game Ends */
		
		/* End Grid */ 
		
		GridPane GameOver = new GridPane(); 
		GameOver.setAlignment(Pos.BOTTOM_CENTER); 
		GameOver.setHgap(10); 
		GameOver.setVgap(10); 
		GameOver.setPadding(new Insets(25, 25, 25, 25)); 
		
		/* End Game Text */ 
		
		GameOver.setPadding(new Insets(0, 0, 0, 0)); 
        GameOver.add(GameOverTitle, 0, 0, 2, 1); 
        
		Label GameWinner = new Label("Game Winner :"); 
		GameOver.add(GameWinner, 0, 1); 
		
		TextField GameWinnerName  = new TextField(); 
		GameOver.add(GameWinnerName, 1, 1);
		
		Label PlayerOnePoints = new Label("Player 1 Points :");
		GameOver.add(PlayerOnePoints, 0, 2);
		
		TextField  PlayerOnePointsNumber = new TextField(); 
		GameOver.add(PlayerOnePointsNumber, 1, 2);
		
		Label PlayerTwoPoints = new Label("Your Points :");
		GameOver.add(PlayerTwoPoints, 0, 3);
		
		TextField PlayerTwoPointsNumber = new TextField(); 
		GameOver.add(PlayerTwoPointsNumber, 1, 3);
		
		/* End Game Buttons */
		HBox GameOverOptions = new HBox(10);
		GameOverOptions.setAlignment(Pos.BOTTOM_CENTER); 
		GameOverOptions.getChildren().addAll(Replay, ExitGame); 
		GameOver.add(GameOverOptions, 1, 4);
		
		HBox EndLogo = new HBox(10);
		EndLogo.setAlignment(Pos.BASELINE_CENTER);
		EndLogo.setPadding(new Insets(5, 0, 0, 0));
		EndLogo.getChildren().add(new ImageView(bigLogo));
		
		Scene startScene = new Scene(new VBox(Logo, ConnectionInfo), 300, 275);
		Scene gameScene = new Scene(new VBox(GameLayout, GameScrollPane), 820, 570);
		Scene endScene = new Scene(new VBox(EndLogo, GameOver), 400, 300);
		
		Connect.setOnAction(e -> {

			clientConnection = new Client (data -> {
				Platform.runLater(() -> {

					primaryStage.setScene(gameScene);
					int gameMode = data.gameMode;

					switch (gameMode) {
						case -1:
							Messages.getItems().add("Somebody crashes!");
							break;
						case 0:
							clientNum = data.clientNum;
							Messages.getItems().add("You are player " + clientNum);
							Messages.getItems().add("Please choose one in 3\ncategories below!");
							numGuess = 6;
							numAttempt = 3;
							break;
						case 1:
							numGuess = data.numRemainGuess;
							numAttempt = data.numRemainAttempt;

							numAttempt = 3;

							if (numLetter == 0) {

								Messages.getItems().add("Word has been given!");
								number.setText(Integer.toString(data.numLetter));
								numLetter = data.numLetter;

							}
							else {

								win = data.win;
								lose = data.lose;

								if (win) {
									Messages.getItems().add("You have won!");
									setDisabilityLetterBank(true);
									break;
								}
								else if (lose) {
									Messages.getItems().add("You have lost!");
									setDisabilityLetterBank(true);
									break;
								}

								guessNum.setText(Integer.toString(data.numRemainGuess));
								attemptNum.setText(Integer.toString(data.numRemainAttempt));

								letterInWord = data.letterInWord;
								if (letterInWord) {
									Messages.getItems().add("Guess is correct!");
									charLocation = data.charLocation;
									letters.set(charLocation, guess);
								}
								else {
									Messages.getItems().add("Wrong guess or\nguess has already been made\nNumber of guesses decreased!");
								}

							}
							Messages.getItems().add("Please guess a letter!");
							break;

					}

					NumLetters.getChildren().clear();
					for(int i = 0; i < numLetter; i++) {

						Button letter = new Button();
						letter.setStyle(
								"-fx-background-radius: 100em;" +
										"-fx-min-width: 60px; " +
										"-fx-min-height:60px; " +
										"-fx-max-width: 60px; " +
										"-fx-max-height:60px;"  +
										"-fx-background-color: #add8e6"
						);

						letters.add(' ');
						letterButtons.add(letter);

						letter.setText(Character.toString(letters.get(i)));
						letter.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

						NumLetters.getChildren().add(letter);

					}


				}); }, IP.getText(), Integer.parseInt(Port.getText()));

			clientConnection.start();


		});

		Letter_A.setOnAction(e -> {
			guess = 'A';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_B.setOnAction(e -> {
			guess = 'B';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_C.setOnAction(e -> {
			guess = 'C';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_D.setOnAction(e -> {
			guess = 'D';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_E.setOnAction(e -> {
			guess = 'E';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_F.setOnAction(e -> {
			guess = 'F';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_G.setOnAction(e -> {
			guess = 'G';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_H.setOnAction(e -> {
			guess = 'H';
			Messages.getItems().add("Please hit submit to confirm");
		});
		Letter_I.setOnAction(e -> {
			guess = 'I';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_J.setOnAction(e -> {
			guess = 'J';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_K.setOnAction(e -> {
			guess = 'K';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_L.setOnAction(e -> {
			guess = 'L';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_M.setOnAction(e -> {
			guess = 'M';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_N.setOnAction(e -> {
			guess = 'N';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_O.setOnAction(e -> {
			guess = 'O';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_P.setOnAction(e -> {
			guess = 'P';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_Q.setOnAction(e -> {
			guess = 'Q';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_R.setOnAction(e -> {
			guess = 'R';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_S.setOnAction(e -> {
			guess = 'S';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_T.setOnAction(e -> {
			guess = 'T';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_U.setOnAction(e -> {
			guess = 'U';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_V.setOnAction(e -> {
			guess = 'V';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_W.setOnAction(e -> {
			guess = 'W';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_X.setOnAction(e -> {
			guess = 'X';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_Y.setOnAction(e -> {
			guess = 'Y';
			Messages.getItems().add("Please hit submit to confirm");
		});

		Letter_Z.setOnAction(e -> {
			guess = 'Z';
			Messages.getItems().add("Please hit submit to confirm");
		});

		DiseasesButton.setOnAction(e -> {

			DiseasesButton.setDisable(true);
			MythicalCreaturesButton.setDisable(true);
			ProgrammingLanguagesButton.setDisable(true);
			category = 'D';
			Messages.getItems().add("Chosen Disease");
			setDisabilityLetterBank(false);
			clientConnection.send(new GameInfo(1, clientNum, numLetter, numGuess, numAttempt, guess, category, charLocation, win, lose, letterInWord));

		});

		MythicalCreaturesButton.setOnAction(e -> {

			DiseasesButton.setDisable(true);
			MythicalCreaturesButton.setDisable(true);
			ProgrammingLanguagesButton.setDisable(true);
			category = 'M';
			Messages.getItems().add("Chosen Mythical Creatures");
			setDisabilityLetterBank(false);
			clientConnection.send(new GameInfo(1, clientNum, numLetter, numGuess, numAttempt, guess, category, charLocation, win, lose, letterInWord));

		});

		ProgrammingLanguagesButton.setOnAction(e -> {

			DiseasesButton.setDisable(true);
			MythicalCreaturesButton.setDisable(true);
			ProgrammingLanguagesButton.setDisable(true);
			category = 'P';
			Messages.getItems().add("Chosen Programming Language");
			setDisabilityLetterBank(false);
			clientConnection.send(new GameInfo(1, clientNum, numLetter, numGuess, numAttempt, guess, category, charLocation, win, lose, letterInWord));

		});

		Submit.setOnAction(e -> {


			clientConnection.send(new GameInfo(1, clientNum, numLetter, numGuess, numAttempt, guess, category, charLocation, win, lose, letterInWord));
			Messages.getItems().add("Letter is submitted successfully!");

		});

		End.setOnAction(e-> primaryStage.setScene(endScene));

		primaryStage.setScene(startScene);
		primaryStage.show();
	}

	private void setDisabilityLetterBank (boolean val) {
		Letter_A.setDisable(val);
		Letter_B.setDisable(val);
		Letter_C.setDisable(val);
		Letter_D.setDisable(val);
		Letter_E.setDisable(val);
		Letter_F.setDisable(val);
		Letter_G.setDisable(val);
		Letter_H.setDisable(val);
		Letter_I.setDisable(val);
		Letter_J.setDisable(val);
		Letter_K.setDisable(val);
		Letter_L.setDisable(val);
		Letter_M.setDisable(val);
		Letter_N.setDisable(val);
		Letter_O.setDisable(val);
		Letter_P.setDisable(val);
		Letter_Q.setDisable(val);
		Letter_R.setDisable(val);
		Letter_S.setDisable(val);
		Letter_T.setDisable(val);
		Letter_U.setDisable(val);
		Letter_V.setDisable(val);
		Letter_W.setDisable(val);
		Letter_X.setDisable(val);
		Letter_Y.setDisable(val);
		Letter_Z.setDisable(val);
	}

}