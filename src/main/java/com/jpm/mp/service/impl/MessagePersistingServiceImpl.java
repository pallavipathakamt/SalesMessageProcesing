package com.jpm.mp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpm.mp.message.Message;
import com.jpm.mp.service.MessagePersistingService;

public class MessagePersistingServiceImpl implements MessagePersistingService {

	private static final Map<String, List<Message>> MESSAGE_RECORDS = new HashMap<String, List<Message>>();

	@Override
	public void persist(Message message) {

		List<Message> messageList = new ArrayList<Message>();

		if (MESSAGE_RECORDS != null && !MESSAGE_RECORDS.isEmpty()
				&& MESSAGE_RECORDS.get(message.getProductType()) != null) {
			messageList = MESSAGE_RECORDS.get(message.getProductType());
		}
		messageList.add(message);
		MESSAGE_RECORDS.put(message.getProductType(), messageList);
	}

	@Override
	public Map<String, List<Message>> getMessages() {
		return MESSAGE_RECORDS;
	}

}
