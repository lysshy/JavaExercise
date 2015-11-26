package lysshy.socket.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import lysshy.socket.common.entity.UserMessage;

public class TimeServerHandler  extends IoHandlerAdapter{
	private static List<IServerMsgListener> lsnList = new ArrayList<IServerMsgListener>();

	@Override
    public void exceptionCaught( IoSession session, Throwable cause ) throws Exception
    {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived( IoSession session, Object message ) throws Exception
    {
        UserMessage msg = (UserMessage) message;
        
        System.out.println("receive message " + msg.getPrintString());
        for(IServerMsgListener lsn : lsnList){
        	lsn.onMessage(msg);
        }
        
//        UserMessage smsg = new UserMessage();
//        
//        session.write("Message from server");
    }
    
    public void registerListener(IServerMsgListener lsn){
    	if(lsn != null){
    		lsnList.add(lsn);
    	}
    }

    @Override
    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception
    {
        System.out.println( "IDLE " + session.getIdleCount( status ));
    }

}
