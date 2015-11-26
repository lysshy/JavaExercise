package lysshy.socket.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import lysshy.socket.common.ConfigConst;
import lysshy.socket.common.UserDecoder;
import lysshy.socket.common.UserEncoder;

public class SocketClient {
	
	private String userName;
	
	public SocketClient(String userName) {
		this.userName = userName;
		start();
	}
	
	private void start(){
		IoConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new UserEncoder(), new UserDecoder()));
    
		connector.setHandler(new MyClientHandler(userName));
		
		ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1", ConfigConst.PORT));
//		future.awaitUninterruptibly();
		System.out.println("client " + userName + "started.");
		
	}
	
	
}
