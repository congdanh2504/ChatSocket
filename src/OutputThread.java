
import javax.swing.*;
import java.io.*;
import java.net.*;

public class OutputThread extends Thread {

	public ObjectOutputStream Out;
	Socket socket;
	JTextArea txt;
	BufferedReader bf;
	String sender;
	String receiver;

	public OutputThread(Socket s, JTextArea txt, String sender, String receiver) {
		super();
		this.socket = s;
		this.txt = txt;
		this.sender = sender;
		this.receiver = receiver;
		try {
			bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Network Error!");
			System.exit(0);
		}
	}

	public void run() {
		while (true) {
			try {
				if (socket != null) {
					String msg = "";
					if ((msg = bf.readLine()) != null && msg.length() > 0) {
						txt.append("\n" + receiver + ": " + msg);
					}
				}
				sleep(1000);
			} catch (Exception e) {
			}
		}
	}
}
