package com.jpm.mp.service;

import java.util.List;
import java.util.Map;

import com.jpm.mp.message.Message;

public interface MessageReportingService {

	/**
	 * Generate report detailing the number of sales of each product and their
	 * total value. 
	 * This will be called after every 10th message
	 * 
	 * @param msgMap
	 */
	public void reportNoOfSalesMessage(Map<String, List<Message>> msgMap);

	/**
	 * Logs a report of the adjustments that have been made to each sale type
	 * while the application was running.
	 * This will be called after every 50th message
	 * 
	 * @param msgMap
	 */
	public void reportOfAdjustments(List<Message> msgMap);
}
