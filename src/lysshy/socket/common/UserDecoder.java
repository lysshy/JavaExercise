package lysshy.socket.common;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import lysshy.socket.common.entity.UserMessage;

public class UserDecoder extends CumulativeProtocolDecoder{

	@Override
	protected boolean doDecode(IoSession session, IoBuffer buffer, ProtocolDecoderOutput out) throws Exception {
		UserMessage message = new UserMessage();
		int userNameSize = buffer.getInt();
		byte[] nameBytes = new byte[userNameSize];
		buffer.get(nameBytes);
		message.setUserName(new String(nameBytes));
		
		int msgSize = buffer.getInt();
		byte[] msgBytes = new byte[msgSize];
		buffer.get(msgBytes);
		message.setMsg(new String(msgBytes));
		out.write(message);
		return true;
	}

}
