
import javax.swing.*;
import java.io.*;
import java.net.*;
//import javafx.stage.FileChooser;

public class ChatPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Socket socket = null;
	BufferedReader bf = null;
	DataOutputStream os = null;
	OutputThread t = null;
	String sender;
	String receiver;
	public File file;
	String staffName;
	String fileToSend;
	FileInputStream fis = null;
	BufferedInputStream bis = null;
	OutputStream Os = null;
	ServerSocket servsock = null;
	Socket sock = null;
	static InputStream in;
	static OutputStream out;

	public ChatPanel(Socket s, String sender, String receiver) {
		initComponents();
		socket = s;
		this.sender = sender;
		this.receiver = receiver;
		try {
			bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new DataOutputStream(socket.getOutputStream());
			t = new OutputThread(s, txtMessages, sender, receiver);
			t.start();
		} catch (Exception e) {
		}
	}

	public JTextArea getTxtMessages() {
		return this.txtMessages;
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		panelMessage = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		txtMessage = new javax.swing.JTextArea();
		jPanel1 = new javax.swing.JPanel();
		btnSend = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		txtMessages = new javax.swing.JTextArea();
		jScrollPane4 = new javax.swing.JScrollPane();

		setLayout(new java.awt.BorderLayout());

		panelMessage.setLayout(new java.awt.GridLayout(1, 0));

		txtMessage.setColumns(20);
		txtMessage.setRows(5);
		jScrollPane1.setViewportView(txtMessage);

		panelMessage.add(jScrollPane1);

		jPanel1.setLayout(new java.awt.GridLayout(3, 0));

		btnSend.setText("Send");
		btnSend.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSendActionPerformed(evt);
			}
		});

		jPanel2.setLayout(null);
		jPanel3.setLayout(null);

		jPanel2.add(jPanel3);
		jPanel3.setBounds(0, 0, 200, 32);
		panelMessage.add(btnSend);

		add(panelMessage, java.awt.BorderLayout.PAGE_END);

		txtMessages.setColumns(20);
		txtMessages.setRows(5);
		jScrollPane2.setViewportView(txtMessages);

		add(jScrollPane2, java.awt.BorderLayout.CENTER);

	}// </editor-fold>

	private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {
		if (txtMessage.getText().trim().length() == 0) {
			return;
		}
		try {
			os.writeBytes(txtMessage.getText());
			os.write(13);
			os.write(10);
			os.flush();
			this.txtMessages.append("\n" + sender + ": " + txtMessage.getText());
			txtMessage.setText("");
		} catch (Exception e) {
		}
	}

	// Variables declaration - do not modify
	private javax.swing.JButton btnSend;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JPanel panelMessage;
	private javax.swing.JTextArea txtMessage;
	private javax.swing.JTextArea txtMessages;
	// End of variables declaration
}
