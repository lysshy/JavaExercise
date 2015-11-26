package lysshy.socket.server;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import lysshy.socket.common.entity.UserMessage;

public class ServerPanel extends JPanel implements IServerMsgListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2524698297936953057L;
	private JList<String> userList;
	private JTextArea logArea;
	private JButton btn;

	public ServerPanel(){
		initGUI();
	}

	private void initGUI() {
		setLayout(new BorderLayout());
		userList = new JList<String>();
		DefaultListModel<String> model = new DefaultListModel<String>();
		userList.setModel(model);
		add(new JScrollPane(userList), BorderLayout.WEST);
		
		logArea = new JTextArea();
		add(new JScrollPane(logArea), BorderLayout.CENTER);
		
		btn = new JButton("OK");
		add(btn, BorderLayout.SOUTH);
		
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Server");
		frame.setContentPane(new ServerPanel());
		frame.setPreferredSize(new Dimension(500, 500));
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void onMessage(Object msg) {
		UserMessage uMsg = (UserMessage) msg;
		final String userName = uMsg.getUserName();
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				DefaultListModel<String> model = (DefaultListModel<String>) userList.getModel();
				model.addElement(userName);
			}
		});
		
	}
}
