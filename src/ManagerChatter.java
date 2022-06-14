
import java.net.*;
import java.io.*;
import javax.print.attribute.standard.Severity;

public class ManagerChatter extends javax.swing.JFrame implements Runnable {

	static ServerSocket srvSocket = null;
	BufferedReader br = null;
	Thread t;
	public int serverPort;
	static InputStream in;
	static OutputStream out;

	public ManagerChatter() {
		initComponents();
		this.setSize(600, 300);
		serverPort = Integer.parseInt(txtSeverPort.getText());
		try {
			srvSocket = new ServerSocket(serverPort);
			this.IbMessage.setText("Mng. Server is running at the port ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		t = new Thread(this);
		t.start();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		IbMessage = new javax.swing.JLabel();
		txtSeverPort = new javax.swing.JTextField();
		jTabbedPane1 = new javax.swing.JTabbedPane();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setLayout(new java.awt.GridLayout(1, 0));

		IbMessage.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		IbMessage.setText("Manager Port:");
		jPanel1.add(IbMessage);

		txtSeverPort.setText("12345");
		txtSeverPort.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtSeverPortActionPerformed(evt);
			}
		});
		jPanel1.add(txtSeverPort);

		getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);
		getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>

	private void txtSeverPortActionPerformed(java.awt.event.ActionEvent evt) {
	}

	public static void main(String args[]) throws IOException {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ManagerChatter().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JLabel IbMessage;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTextField txtSeverPort;
	// End of variables declaration

	@Override
	public void run() {
		while (true) {
			try {
				Socket aStaffSocket = srvSocket.accept();
				if (aStaffSocket != null) {
					br = new BufferedReader(new InputStreamReader(aStaffSocket.getInputStream()));
					String S = br.readLine();
					int pos = S.indexOf(":");
					String staffName = S.substring(pos + 1);

					ChatPanel p = new ChatPanel(aStaffSocket, "Manager", staffName);
					jTabbedPane1.add(staffName, p);
					p.updateUI();
				}
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
	}
}