package com.jpm.mp.service;

import com.jpm.mp.message.Message;

public interface MessageProcessingService {

	/**
	 * Process the JSON String message and store in memory
	 * 
	 * @param message
	 */
	public void processMessage(Message message);
}
