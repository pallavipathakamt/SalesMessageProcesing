package com.jpm.mp.service;

import java.util.List;
import java.util.Map;

import com.jpm.mp.message.Message;

public interface MessagePersistingService {

	/**
	 * Store each message in memory
	 * 
	 * @param message
	 */
	public void persist(Message message);
	
	/**
	 * Retrieve stored messages
	 * 
	 * @return
	 */
	public Map<String, List<Message>> getMessages();
}
