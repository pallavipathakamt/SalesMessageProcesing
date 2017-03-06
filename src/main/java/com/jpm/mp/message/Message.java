package com.jpm.mp.message;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
public class Message {

	private MessageType messageType;
	private String productType;
	private BigInteger value;
	private int occurrences = 1;
	private BigInteger adjustment;
	private OperationType operationType;
	
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public BigInteger getValue() {
		return value;
	}
	public void setValue(BigInteger value) {
		this.value = value;
	}
	public int getOccurrences() {
		return occurrences;
	}
	public void setOccurrences(int occurrences) {
		this.occurrences = occurrences;
	}
	public BigInteger getAdjustment() {
		return adjustment;
	}
	public void setAdjustment(BigInteger adjustment) {
		this.adjustment = adjustment;
	}
	public OperationType getOperationType() {
		return operationType;
	}
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
	
	@Override
	public String toString() {
		return "Message [productType=" + productType + ", value=" + value + ", occurrences=" + occurrences
				+ ", adjustment=" + adjustment + ", operationType=" + operationType + "]";
	}
	
	
}
