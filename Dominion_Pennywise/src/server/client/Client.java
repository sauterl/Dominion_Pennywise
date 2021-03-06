package server.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import commons.ChatMsg;
import commons.JoinMsg;
import commons.Message;
import commons.StringMsg;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import java.util.ArrayList;
import controllers.ClientHandler;

public class Client {

	Socket socket;

	DataOutputStream output;
	DataInputStream input;
	String info;
	Server server;

	ArrayList<String> deck = new ArrayList<String>();

	public SimpleStringProperty newestMessage = new SimpleStringProperty();
	protected String playerName;

	public Client(String playerName) {
		this.playerName = playerName;
	}

	public void run() {
			new Thread(startClient).start();
	}

	final Task<Void> startClient = new Task<Void>() {
		@Override
		protected Void call() throws Exception {
			try {
				socket = new Socket("localhost", 2303);
				System.out.println("Player " + playerName + " is connected");
				sendToServer("lobby" + playerName);

				//Kommentar und so
				// Chat
				while (true) {
					Message msg = Message.receive(socket);
					if (msg instanceof ChatMsg) {
						ChatMsg chatMsg = (ChatMsg) msg;
						Platform.runLater(() -> {
							newestMessage.set(chatMsg.getPlayerName() + ": " + chatMsg.getContent());
						});
					} else if (msg instanceof StringMsg) {
						String message = ((StringMsg) msg).getContent();
						Platform.runLater(() -> {
						ClientHandler.getMessageFromClient(message);
						});
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Verbindung fehlgeschlagen");
//			} finally {
//				socket.close();
//				System.out.println("Client closed");
			}
			Message jmsgForServer = new JoinMsg(playerName);
			jmsgForServer.send(socket);
			return null;
		}

	};

	public void sendToServer(String msg) {
		Message stringMsg = new StringMsg(playerName, msg);
		stringMsg.send(socket);
	}

	public void sendChatMessage(String message) {
		Message msg = new ChatMsg(playerName, message);
		msg.send(socket);
	}

	public String receiveMessage() {
		return newestMessage.get();
	}

	public void disconnectClient() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getPlayerName() {
		return playerName;
	}
}
