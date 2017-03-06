package com.jpm.mp;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpm.mp.message.Message;
import com.jpm.mp.service.MessageProcessingService;
import com.jpm.mp.service.impl.MessageProcessingServiceImpl;

public class MessageApp {

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		// Create test data
        //String testType1 = "{\"messageType\" : \"TYPE1\",\"productType\":\"apple\", \"value\":10 }";

    	MessageProcessingService messageService = new MessageProcessingServiceImpl();
        
      //JSON from file to Object
        List<Message> testMessages;
		try {
			testMessages = mapper.readValue(new File(ClassLoader.getSystemResource("notification.json").getFile()), new TypeReference<List<Message>>(){});
			for(Message message : testMessages){
	        	
	    		messageService.processMessage(message);
	        }   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}	

}
