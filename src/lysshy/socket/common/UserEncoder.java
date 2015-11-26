package lysshy.socket.common;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import lysshy.socket.common.entity.UserMessage;

public class UserEncoder implements ProtocolEncoder{

	@Override
	public void dispose(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encode(IoSession session, Object obj, ProtocolEncoderOutput out) throws Exception {
		UserMessage msg = (UserMessage) obj;
		String userName = msg.getUserName();
		String msgInfo = msg.getMsg();
		int size = userName.getBytes().length + msgInfo.getBytes().length + 8;
		
		IoBuffer buffer = IoBuffer.allocate(size);
		buffer.setAutoExpand(true);
		
		buffer.putInt(userName.getBytes().length);
		buffer.put(userName.getBytes());
		buffer.putInt(msgInfo.getBytes().length);
		buffer.put(msgInfo.getBytes());
		buffer.flip();
		out.write(buffer);
		
	}

}
