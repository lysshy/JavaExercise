package lysshy.socket.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import lysshy.socket.common.entity.UserMessage;

public class MyClientHandler extends IoHandlerAdapter{
	
	private String userName;
	
	public MyClientHandler(String userName) {
		this.userName = userName;
	}

	@Override
    public void exceptionCaught( IoSession session, Throwable cause ) throws Exception
    {
        cause.printStackTrace();
    }
	
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("client " + userName + " open");
		String msg = "hello";
		UserMessage uMsg = new UserMessage();
		uMsg.setMsg(msg);
		uMsg.setUserName(userName);
		session.write(uMsg);
		
	}

    @Override
    public void messageReceived( IoSession session, Object message ) throws Exception
    {
        String str = message.toString();
        
        System.out.println("receive message " + str);
    }

    @Override
    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception
    {
        System.out.println( "IDLE " + session.getIdleCount( status ));
    }
}
