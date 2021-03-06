package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main_Class.ServiceLocator;
import server_Models.Translator;

public class Lobby_View {
	
	public static Stage stage;
	
	BorderPane mainPane; 
	
	//connected Players and online players area
	public TextArea txtConnectedPlayers = new TextArea();
	TextArea txtOnlinePlayers = new TextArea();
	
	//history area
	TextArea txtHistory = new TextArea();
	
	// Chat area and its controlls
	public TextArea txtChatArea = new TextArea();
	public TextField txtChatMessage = new TextField();
	public Button btnSend = new Button("Send");
	
	//controls button
	public Button btnTest;
	public Button btnLeaveGame;
	public Button btnStartGame;

	public Lobby_View(Stage stage){
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		Translator t = sl.getTranslator();
		Lobby_View.stage = stage; 
//		stage.setResizable(false);
		stage.setTitle("Dominion Lobby");
		
		 btnTest = new Button(t.getString("dominion.lobby.btn.test"));
		 btnLeaveGame = new Button(t.getString("dominion.lobby.btn.quit"));
		 btnStartGame = new Button(t.getString("dominion.lobby.btn.start"));
		
		txtConnectedPlayers.setEditable(false);
		
		HBox bottomChatBox = new HBox(txtChatMessage,btnSend);
		bottomChatBox.getStyleClass().add("hboxBottom");
		HBox.setHgrow(txtChatMessage, Priority.ALWAYS);
		
		btnTest.getStyleClass().add("btn");
		btnLeaveGame.getStyleClass().add("btn");
		btnStartGame.getStyleClass().add("btn");
		
		HBox topBox = new HBox(btnTest, btnStartGame, btnLeaveGame);
		topBox.getStyleClass().add("topBox");
		
		VBox rightBox = new VBox(topBox, txtHistory,txtChatArea, bottomChatBox);
		rightBox.getStyleClass().add("rightBox");
		VBox.setVgrow(txtChatArea, Priority.ALWAYS);
		
		VBox leftBox = new VBox(txtConnectedPlayers, txtOnlinePlayers);
		leftBox.getStyleClass().add("leftBox");
		VBox.setVgrow(txtConnectedPlayers, Priority.ALWAYS);
		VBox.setVgrow(txtOnlinePlayers, Priority.ALWAYS);
		
		mainPane = new BorderPane(); 
		mainPane.setLeft(leftBox);
		mainPane.setCenter(rightBox);
		mainPane.getStyleClass().add("mainPane");
		
		Scene scene = new Scene(mainPane);
		scene.getStylesheets().add(getClass().getResource("Lobby.css").toExternalForm());
		stage.setScene(scene);
		stage.setFullScreen(true);
	}
	
	public void start() {
		stage.setFullScreen(true);
		stage.show();
	}
	
	public static void stop() {
		stage.hide();
	}
	
	public Stage getStage() {
		return Lobby_View.stage;
	}
	
}


