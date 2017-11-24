package server.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import server_Models.*;

public class Client {
	Socket socket = null;
	ObjectOutputStream output;
	ObjectInputStream input;
	String msg;
	Game_Controller gc;
	String info;
	Server server;
	
	public Client() {
	}

	public void run() {
		try {
			socket = new Socket("localhost", 2303);

			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			
			output.flush();

			gc = new Game_Controller();
		} catch (IOException ioException) {
			ioException.printStackTrace();
			System.out.println("Verbindung fehlgeschlagen");
//		} finally {
//			try {
//				socket.close();
//			} catch (IOException ioException) {
//				ioException.printStackTrace();
//			}
		}
	}
	
	public void sendToServer(String msg) {
		try {
			output.writeObject(msg);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getMsgFromServer() {
		try {
			info = input.readObject().toString();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return info;
	}
	
}
