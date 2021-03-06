package server.client;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import Splash.Server_View;
import commons.ChatMsg;
import commons.JoinMsg;
import commons.Message;
import commons.StringMsg;
import javafx.concurrent.Task;
import server_Models.Player;

public class Client_Chat {
	private Socket socket;
	private String playerName;
	Server server;
	Server_View serverView;
	ServerHandler sh;
	Player player;
	
	public Client_Chat(Server server,Socket socket) {
		this.server = server;
		this.socket = socket;
		sh = new ServerHandler(server); 
		new Thread(messageThread).start();
	}

	final Task<Void> messageThread = new Task<Void>() {
		@Override
		protected Void call() throws Exception {
			while(true) {
			Message msg = Message.receive(socket);
			if (msg instanceof ChatMsg) {
				server.broadcast((ChatMsg) msg);
			} else if (msg instanceof JoinMsg) {
				Client_Chat.this.playerName = ((JoinMsg) msg).getPlayerName();
			} else if (msg instanceof StringMsg) {
				String message = ((StringMsg) msg).getContent();
				System.out.println(message);
				if(message.substring(0, 5).equals("lobby")) {
					playerName = message.substring(5);
					sh.addPlayerToList(playerName);
				}else {
					sh.getMessageFromServer(message);
				}
			}
			}
		}
	};

	public void sendChatMsg(ChatMsg msg) {
		msg.send(socket);
	}
	
	public void sendStringMsgToClient(String msg) {
		Message message = new StringMsg(playerName, msg);
		message.send(socket);
	}

	public String getPlayername() {
		return playerName;
	}
	
	public void stop() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
