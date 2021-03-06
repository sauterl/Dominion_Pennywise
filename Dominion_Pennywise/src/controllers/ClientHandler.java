package controllers;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.stage.Stage;
import view.Board_View;
import view.Lobby_View;
import view.Login_View;

public class ClientHandler {
	Lobby_Controller lobbyC;
	Login_Controller loginC;
	Board_Controller boardC;
	Result_Controller resultC;

	public static ArrayList<String> tempHandCard = new ArrayList<String>();

	Login_View loginV;

	static String message;
	static Board_View boardview;
	Lobby_View lobbyV;

	public static String phase;
	
	public void initializeLobbyController(Lobby_View lobbyView) {
		this.lobbyV = lobbyView;
		lobbyC = new Lobby_Controller(this.lobbyV);
	}

	public static void getMessageFromClient(String msg) {
		message = msg;

		// Get message to set the player hand view
		if (message.length() > 4 && message.substring(0, 4).equals("hand")) {
			message = message.substring(4);

			if (!message.equals("end")) {
				tempHandCard.add(message);
			}

			if (message.equals("end")) {
				Platform.runLater(() -> {
					boardview.setHand();
				});
			}

		}

		switch (message) {
		case "openboardview":
			Platform.runLater(() -> {
				openBoardView();
			});
			break;
		case "buy":
			phase = "buy";
			break;
		case "action":
			phase = "action";
			break;
		}

							// DELETE THIS AGAIN
		phase = "buy";

	}

	public static void openBoardView() {
		// lobbyV.stop();
		Lobby_View.stop();
		boardview = new Board_View(new Stage());
		Board_Controller boardController = new Board_Controller(boardview);
		boardview.start();
	}

}
