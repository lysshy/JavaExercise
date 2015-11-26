package lysshy.socket.server;

import java.awt.Dimension;
import java.io.IOException;
import java.net.InetSocketAddress;

import javax.swing.JFrame;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import lysshy.socket.common.ConfigConst;
import lysshy.socket.common.UserDecoder;
import lysshy.socket.common.UserEncoder;

public class MyServer {

	public static void main(String[] args) throws IOException {
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new UserEncoder(), new UserDecoder()));
		TimeServerHandler handler = new TimeServerHandler();
		
		acceptor.setHandler(handler);
		
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		acceptor.bind(new InetSocketAddress(ConfigConst.PORT));
		System.out.println("Server started.");
		
		handler.registerListener(showFrame());
	
	}
	
	private static IServerMsgListener showFrame(){
		JFrame frame = new JFrame("Server");
		ServerPanel panel = new ServerPanel();
		frame.setContentPane(panel);
		frame.setPreferredSize(new Dimension(500, 500));
		frame.pack();
		frame.setVisible(true);
		return panel;
	}
}
