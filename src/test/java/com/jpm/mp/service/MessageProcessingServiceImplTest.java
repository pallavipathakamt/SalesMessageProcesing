package com.jpm.mp.service;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpm.mp.message.Message;
import com.jpm.mp.service.impl.MessageProcessingServiceImpl;

public class MessageProcessingServiceImplTest {

	@Test
	public void processMessage() throws Exception{
		
		ObjectMapper mapper = new ObjectMapper();
		// Create test data
        //String testType1 = "{\"messageType\" : \"TYPE1\",\"productType\":\"apple\", \"value\":10 }";

    	MessageProcessingService messageService = new MessageProcessingServiceImpl();
        
      //JSON from file to Object
        List<Message> testMessages = mapper.readValue(new File(ClassLoader.getSystemResource("notification.json").getFile()), new TypeReference<List<Message>>(){});
        for(Message message : testMessages){
        	
    		messageService.processMessage(message);
        }       
		
	}
}
