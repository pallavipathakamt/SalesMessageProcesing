package com.jpm.mp.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jpm.mp.message.Message;
import com.jpm.mp.service.MessagePersistingService;
import com.jpm.mp.service.MessageProcessingService;
import com.jpm.mp.service.MessageReportingService;

public class MessageProcessingServiceImpl implements MessageProcessingService {
	
	private static int messagesCount;
	private MessagePersistingService persistSerice;
	private MessageReportingService reportService;
		
	public MessageProcessingServiceImpl() {
		super();
		messagesCount = 0;
		this.persistSerice = new MessagePersistingServiceImpl();
		this.reportService = new MessageReportingServiceImpl();
	}


	@Override
	public void processMessage(Message salesMessage) {
		if(messagesCount >= 51){
			System.out.println("System is pausing..");
			System.out.println("System is not accepting any new mesages. Please contact administrator.");
			System.exit(1);
		}
		
		if(salesMessage == null){
			System.out.println("Invalid Sales message received");
			System.exit(1);
		}

		//Log the records received
		System.out.println(new Date() + " " + salesMessage );
				
		//Message message = this.parseMessage(salesMessage);	
		messagesCount++;
		
		// Store record in memory
		persistSerice.persist(salesMessage);
		
		//After every 10th message log report
		if(messagesCount%10 == 0){
			reportService.reportNoOfSalesMessage(persistSerice.getMessages());
			
		}
		
		//After every 50th message log adjustment report
		if(messagesCount == 50){

			Map<String, List<Message>> msgMap = persistSerice.getMessages();
			List<Message> msgList = new ArrayList<>();
			msgMap.forEach((k, v) -> msgList.addAll(v));
			reportService.reportOfAdjustments(msgList);
		}
		
		
		
	}
	

}
