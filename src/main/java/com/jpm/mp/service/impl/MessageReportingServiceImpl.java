package com.jpm.mp.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.jpm.mp.message.Message;
import com.jpm.mp.message.OperationType;
import com.jpm.mp.service.MessageReportingService;

public class MessageReportingServiceImpl implements MessageReportingService{


	@Override
	public void reportNoOfSalesMessage(Map<String, List<Message>> msgMap) {
		
		System.out.println("\n Printing report detailing the number of sales of each product...");
		System.out.println("---------------------------------------------------------------");
		System.out.println("Product		No. Of Sales	Total Value");
		Function<Message, BigInteger> totalMapper = Message -> Message.getValue().multiply(BigInteger.valueOf(Message.getOccurrences()));
		 
		//get List of messages per product type
		msgMap.forEach((k, v) -> {

			//Calculate total No. of sales by adding occurrences
            int totalNumOfSales = v.stream().collect(Collectors.summingInt(Message::getOccurrences));
            
            //Calculate total value of product
            BigInteger totalSaleValue = v.stream().map(totalMapper).reduce(BigInteger.ZERO, BigInteger::add);
            
            System.out.println(k + "		"+ totalNumOfSales+ "		" +totalSaleValue);
            
		});			
		
	}

	@Override
	public void reportOfAdjustments(List<Message> msgList) {
		System.out.println("---------------------------------------------------------------");
		System.out.println("\n Printing report of adjustments...");
		
		List<Message> adjustSales = new ArrayList<Message>();

        for (Message message : msgList) {

            adjustSales.add(message);

            if (message.getAdjustment() != null) {

                // Log report
                System.out.println("Adjustment instruction received: "
                        + message.getOperationType().toString().toLowerCase() + "ing "
                        + message.getAdjustment() + " to each sale recorded for product " + message.getProductType() + "...");

                // Create an adjustment mapper
                Function<Message, BigInteger> adjustmentMapper;

                switch (message.getOperationType()) {
                    case ADD:
                        adjustmentMapper = s -> s.getValue().add(message.getAdjustment()).multiply(BigInteger.valueOf(s.getOccurrences()));
                        break;
                    case SUBTRACT:
                        adjustmentMapper = s -> s.getValue().subtract(message.getAdjustment()).multiply(BigInteger.valueOf(s.getOccurrences()));
                        break;
                    case MULTIPLY:
                        adjustmentMapper = s -> s.getValue().multiply(message.getAdjustment()).multiply(BigInteger.valueOf(s.getOccurrences()));
                        break;
                    default:
                        adjustmentMapper = s -> s.getValue().multiply(BigInteger.valueOf(s.getOccurrences()));
                }

                // Group recorded sales by product type for adjustment
                Map<String, List<Message>> tmpProductSales = adjustSales.stream().collect(Collectors.groupingBy(Message::getProductType));

                System.out.println("Product		No. Of Sales	Total Value");
                // Apply adjustment to each product recorded and log report
                tmpProductSales.get(message.getProductType()).forEach(s -> {

                            // Compute current recorded sale value
                            BigInteger currentValue = s.getValue().multiply(BigInteger.valueOf(s.getOccurrences()));

                            // Apply the adjustment mapper
                            BigInteger adjustedValue = adjustmentMapper.apply(s);
                            
                            System.out.println(s.getProductType() + "		"+ s.getOccurrences()+ "		" +currentValue+"		"+adjustedValue);
                            System.out.println("---------------------------------------------------------------");
                	}
                );

                // Compute total sale value after adjustment for the product
                BigInteger totalAdjustedValue = tmpProductSales.get(message.getProductType()).stream().map(adjustmentMapper).reduce(BigInteger.ZERO, BigInteger::add);

                // Log report
                System.out.println("Total Value of Sales after " + message.getOperationType() + " adjustment for product " + message.getProductType() + ": " + totalAdjustedValue);
                System.out.println("---------------------------------------------------------------");
            }
        }

	
		
	}
}
