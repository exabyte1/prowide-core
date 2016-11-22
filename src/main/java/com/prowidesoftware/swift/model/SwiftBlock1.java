/*******************************************************************************
 * Copyright (c) 2016 Prowide Inc.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as 
 *     published by the Free Software Foundation, either version 3 of the 
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 *     
 *     Check the LGPL at <http://www.gnu.org/licenses/> for more details.
 *******************************************************************************/
package com.prowidesoftware.swift.model;

import java.io.Serializable;
import java.util.logging.Level;

import org.apache.commons.lang.Validate;

import com.prowidesoftware.swift.model.mt.ServiceIdType;

/**
 * Base class for SWIFT <b>Basic Header Block (block 1)</b>.
 * It contains information about the source of the message.<br>
 * 
 * The basic header block is fixed-length and continuous with 
 * no field delimiters. This class contains its 
 * elements as individual attributes for easier management
 * of the block value.<br>
 * This block is mandatory for all SWIFT messages.
 * 
 * @author www.prowidesoftware.com
 * @since 4.0
 */
//TODO: add parameter checks (Validate.*) and complete javadocs 
public class SwiftBlock1 extends SwiftValueBlock implements Serializable {
	private static final transient java.util.logging.Logger log = java.util.logging.Logger.getLogger(SwiftBlock1.class.getName());
	private static final long serialVersionUID = 4229511645041690763L;

	/**
	 * Constant for FIN messages in application id
	 * @since 4.1
	 */
	public static final transient String APPLICATION_ID_FIN = "F";

	/**
	 * Constant for GPA (General Purpose Application) messages in application id
	 * @since 4.1
	 */
	public static final transient String APPLICATION_ID_GPA = "A";

	/**
	 * Constant for Logins and so messages in application id
	 * @since 4.1
	 */
	public static final transient String APPLICATION_ID_LOGINS = "L";

	/**
	 * String of 1 character containing the Application ID as follows:<br>
	 * F = FIN (financial application)<br>
	 * A = GPA (general purpose application)<br>
	 * L = GPA (for logins, and so on)<br>
	 * This designates the application that has established the 
	 * association used to convey the message. You always use F for FIN messages.
	 * It is set by default to F (FIN messages).
	 */
	private String applicationId = "F";

	/**
	 * String of 2 characters containing Service ID as follows:<br>
	 * 01 = GPA/FIN	Message (system and user-to-user)<br>
	 * 02 = GPA	Login<br>
	 * 03 = GPA	Select<br>
	 * 05 = FIN	Quit<br>
	 * 06 = GPA	Logout<br>
	 * 12 = GPA	System Remove AP Request<br>
	 * 13 = GPA	System Abort AP Confirmation<br>
	 * 14 = GPA	System Remove LT Request<br>
	 * 15 = GPA	System Abort LT Confirmation<br>
	 * 21 = GPA/FIN	Message (ACK/NAK/UAK/UNK)<br>
	 * 22 = GPA	Login ACK (LAK)<br>
	 * 23 = GPA	Select ACK (SAK)<br>
	 * 25 = FIN	Quit ACK<br>
	 * 26 = GPA	Logout ACK<br>
	 * 33 = GPA	User Abort AP Request<br>
	 * 35 = GPA	User Abort LT Request<br>
	 * 42 = GPA	Login NAK (LNK)<br>
	 * 43 = GPA	Select NAK (SNK)
	 * It is set by default to 01 (FIN messages).
	 */
	private String serviceId = "01";

	/**
	 * The Logical Terminal address of the sender for messages 
	 * sent or the receiver for messages received from the 
	 * SWIFT network.<br> 
	 * Identifies a logical channel connection to SWIFT, and the network uses it
	 * for addressing. It is composed by the BIC code, an optional terminal
	 * identifier (A, B or C) if the institution has more than one terminal or an X,
	 * and the branch code (padded with "X" if no branch is used). 
	 * For example BFOOARBSAXXX or BFOOARBSXXXX.
	 * .
	 */
	private String logicalTerminal;

	/**
	 * Session number. 4 characters. It is generated by the user's computer. 
	 * As appropriate, the current application session number based 
	 * on the Login. It is padded with zeros.
	 */
	private String sessionNumber = "0000";

	/**
	 * Sequence number is a 6 characters string that is generated by the 
	 * user's computer.<br>
	 * For all FIN messages with a Service Identifier of 01 or 05, 
	 * this number is the next expected sequence number appropriate to 
	 * the direction of the transmission.<br>
	 * For FIN messages with a Service Identifier of 21 or 25, 
	 * the sequence number is that of the acknowledged service message.<br>
	 * It is padded with zeros.
	 */
	private String sequenceNumber = "000000";

	/**
	 * Constructor for specific values
	 * 
	 * @param applicationId the application id
	 * @param serviceId the service id
	 * @param logicalTerminal the logical terminal name
	 * @param sessionNumber the session number
	 * @param sequenceNumber the message sequence number
	 */
	public SwiftBlock1(final String applicationId, final String serviceId, final String logicalTerminal, final String sessionNumber, final String sequenceNumber) {
		this.applicationId = applicationId;
		this.serviceId = serviceId;
		this.logicalTerminal = logicalTerminal;
		this.sessionNumber = sessionNumber;
		this.sequenceNumber = sequenceNumber;
	}
	
	/**
	 * Default constructor
	 */
	public SwiftBlock1() {
		super();
	}

	/**
	 * Creates the block with lenient false, meaning it expects a fixed length value.
	 * Example of supported values:<br> 
	 * "F01BANKBEBBXXXX2222123456" or "1:F01BANKBEBBAXXX2222123456"
	 * 
	 * @param value a fixed length string of 25 or 27 (which must start with '1:') characters containing the blocks value
	 * @throws IllegalArgumentException if parameter is not 25 or 27 characters 
	 * @see #SwiftBlock1(String, boolean)
	 */
	public SwiftBlock1(final String value) {
		this(value, false);
	}
	
	/**
	 * Creates a block 1 object setting attributes by parsing the fixed string argument;<br>
	 * 
	 * @param value string containing the entire blocks value
	 * @param lenient if true the value will be parsed with a best effort heuristic, if false it will throw a IllegalArgumentException if the value has an invalid total size
	 * @see #setValue(String, boolean)
	 * @since 7.7
	 */
	public SwiftBlock1(final String value, boolean lenient) {
		super();
		this.setValue(value, lenient);
	}

	/**
	 * Sets the block number.
	 * @param blockNumber the block number to set
	 * @throws IllegalArgumentException if parameter blockName is not the integer 1
	 * @since 5.0
	 */
	protected void setBlockNumber(final Integer blockNumber) {

		// sanity check
		Validate.notNull(blockNumber, "parameter 'blockNumber' cannot be null");
		Validate.isTrue(blockNumber.intValue() == 1, "blockNumber must be 1");
	}

	/**
	 * Sets the block name. Will cause an exception unless setting block number to 1.
	 * @param blockName the block name to set
	 * @throws IllegalArgumentException if parameter blockName is not the string "1"
	 * @since 5.0
	 */
	protected void setBlockName(final String blockName) {

		// sanity check
		Validate.notNull(blockName, "parameter 'blockName' cannot be null");
		Validate.isTrue(blockName.compareTo("1") == 0, "blockName must be string '1'");
	}

	/**
	 * Returns the block number (the value 1 as an integer)
	 * @return Integer containing the block's number
	 */
	public Integer getNumber() {
		return new Integer(1);
	}

	/**
	 * Returns the block name (the value 1 as a string)
	 * @return block name
	 * 
	 * @since 5.0
	 */
	public String getName() {
		return "1";
	}

	/**
	 * Sets the applicationId
	 * 
	 * @param applicationId String of 1 character containing the Application ID (F, A or L)
	 */
	public void setApplicationId(final String applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * Gets the application ID field in block 1
	 * @return application ID field in block 1
	 */
	public String getApplicationId() {
		return applicationId;
	}

	/**
	 * Sets the Service ID
	 * 
	 * @param serviceId string of 2 characters containing Service ID (01, 02, 03, etc...)
	 */
	public void setServiceId(final String serviceId) {
		this.serviceId = serviceId;
	}
	
	/**
	 * Gets the service ID field in block 1
	 * @return service ID field in block 1
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * Sets the The Logical Terminal address with the parameter as it is given.
	 * 
	 * @param logicalTerminal it is fixed at 12 characters; it must not have X in position 9 (padded with "X" if no branch is required).
	 */
	public void setLogicalTerminal(final String logicalTerminal) {
		this.logicalTerminal = logicalTerminal;
	}
	
	/**
	 * Sets the LT address.<br />
	 * @see LogicalTerminalAddress#getSenderLogicalTerminalAddress()
	 * 
	 * @param logicalTerminal
	 * @since 7.6
	 */
	public void setLogicalTerminal(final LogicalTerminalAddress logicalTerminal) {
		this.logicalTerminal = logicalTerminal.getSenderLogicalTerminalAddress();
	}
	
	/**
	 * Creates and sets a full LT address using the parameter BIC code and a default LT identifier.
	 * 
	 * @see #setLogicalTerminal(LogicalTerminalAddress)
	 * @param bic
	 * @since 7.6
	 */
	public void setLogicalTerminal(final BIC bic) {
		setLogicalTerminal(new LogicalTerminalAddress(bic.getBic11()));
	}

	/**
	 * Completes if necessary and sets the LT address.<br />
 	 * The sender addresses will be filled with proper default LT identifier and branch codes if not provided.
	 * 
	 * @see #setLogicalTerminal(LogicalTerminalAddress)
	 * @since 6.4
	 */
	public void setSender(final String sender) {
		setLogicalTerminal(new LogicalTerminalAddress(sender));
	}
	
	/**
	 * Gets the BIC code from the LT address.<br />
	 * 
	 * @return the BIC object 
	 * @since 7.6
	 */
	public BIC getBIC() {
		return new BIC(this.logicalTerminal);
	}

	/**
	 * Gets the The Logical Terminal address of the sender for messages 
	 * sent or the receiver for messages received from the SWIFT network.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
	 * 
	 * @return the 12 characters logical terminal address
	 */
	public String getLogicalTerminal() {
		return logicalTerminal;
	}

	/**
	 * Sets the Session number. It is generated by the user's computer. 
	 * As appropriate, the current application session number based 
	 * on the Login. It is padded with zeros.
	 * 
	 * @param sessionNumber 4 numbers.
	 */
	public void setSessionNumber(final String sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	/**
	 * Gets the session number in block 1
	 * @return session number in block 1
	 */
	public String getSessionNumber() {
		return sessionNumber;
	}

	/**
	 * Sets the Sequence number that is generated by the 
	 * user's computer.<br>
	 * For all FIN messages with a Service Identifier of 01 or 05, 
	 * this number is the next expected sequence number appropriate to 
	 * the direction of the transmission.<br>
	 * For FIN messages with a Service Identifier of 21 or 25, 
	 * the sequence number is that of the acknowledged service message.<br>
	 * It is padded with zeros.
	 * 
	 * @param sequenceNumber 6 numbers
	 */
	public void setSequenceNumber(final String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * Gets the sequence number field in block 1
	 * @return sequence number field in block 1
	 */
	public String getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * Tell if this block is empty or not.
	 * This block is considered to be empty if all its attributes are set to <code>null</code>.
	 * @return <code>true</code> if all fields are <code>null</code> and false in other case
	 */
	public boolean isEmpty() {
		return applicationId == null && serviceId == null && logicalTerminal == null && sessionNumber == null && sequenceNumber == null;
	}

	/**
	 * Gets the fixed length block 1 value, as a result of
	 * concatenating its individual elements as follow:<br>
	 * Application ID Service ID +
	 * Logical terminal (LT) address +
	 * Session number +
	 * Sequence number.<br>
	 * Notice that this method does not return the "1:" string.
	 */
	public String getValue() {
		if (isEmpty()) {
			return null;
		}
		final StringBuilder v = new StringBuilder();
		if (applicationId != null) {
			v.append(applicationId);
		}
		if (serviceId != null) {
			v.append(serviceId);
		}
		if (logicalTerminal != null) {
			v.append(logicalTerminal);
		}
		if (sessionNumber != null) {
			v.append(sessionNumber);
		}
		if (sequenceNumber != null) {
			v.append(sequenceNumber);
		}
		return v.toString();
	}

	/**
	 * @see #getValue()
	 */
	public String getBlockValue() {
		return getValue();
	}

	/**
	 * Sets the block's attributes by parsing the fixed length string argument.
	 * 
	 * @param value a fixed length string containing the blocks' value (25 or 24 characters when '1:' is not indicated; 26 or 27 characters when starting string '1:' is included)
	 * @throws IllegalArgumentException if parameter is not between 24 and 27 characters.
	 * @see #setValue(String, boolean) 
	 */
	public void setValue(final String value) {
		setValue(value, false);
	}
	
	/**
	 * Sets the block's attributes by parsing string argument with its content<br>
	 * This value can be in different flavors because some fields are optional.<br />
	 * For example "F01BANKBEBBAXXX2222123456" or "1:F01BANKBEBBAXXX2222123456".
	 * 
	 * @param value string containing the entire blocks value
	 * @param lenient if true the value will be parsed with a best effort heuristic, if false it will throw a IllegalArgumentException if the value has an invalid total size
	 */
	public void setValue(final String value, boolean lenient) {
		if (lenient) {
			//leave all attributes as null (cleaning defaults)
			clean();
		} else {
    		// check parameters
    		Validate.notNull(value, "value must not be null");
		}

		if (value != null) {
    		int offset = 0;
    		int slen = value.length();
    
    		// figure out the starting point
    		if (value.startsWith("1")) {
    			if (!lenient) {
        			Validate.isTrue(value.startsWith("1:"), "expected '1:' at the beginning of value and found '" + value.substring(0, 1) + "'");
        			Validate.isTrue(slen == 26 || slen == 27, "block value "+value+" cannot be parsed because it has an invalid size, expected 26 or 27 and found "+ value.length());
    			}
    			offset = 2;
    		} else {
    			if (!lenient) {
    				Validate.isTrue(slen == 24 || slen == 25, "block value "+value+" cannot be parsed because it has an invalid size, expected 24 or 25 and found "+ value.length());
    			}
    		}
    		
    		// separate value fragments
    		int len = 1;
    		this.setApplicationId(this.getValuePart(value, offset, len));
    		offset += len;
    		
    		len = 2;
    		this.setServiceId(this.getValuePart(value, offset, len));
    		offset += len;
    		
    		//LT address must be fixed to 12 characters padding both the LT id and the branch with X if necessary
    		len = 12;
    		this.setLogicalTerminal(this.getValuePart(value, offset, len));
    		offset += len;
    		
    		len = 4;
    		this.setSessionNumber(this.getValuePart(value, offset, len));
    		offset += len;
    		
        	if (lenient) {
        		//get all remaining text
        		this.setSequenceNumber(this.getValuePart(value, offset));
        	} else {
        		//get text between size boundaries
        		len = 6;
            	this.setSequenceNumber(this.getValuePart(value, offset, len));
        	}
		}
	}

	/**
	 * @see #setValue(String)
	 */
	public void setBlockValue(final String value) {
		setValue(value);
	}
	
	/**
	 * Sets all attributes to null
	 * @since 6.4
	 */
	public void clean() {
		applicationId = null;
		serviceId = null;
		logicalTerminal = null;
		sessionNumber = null;
		sequenceNumber = null;
	}

	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((applicationId == null) ? 0 : applicationId.hashCode());
		result = prime * result + ((logicalTerminal == null) ? 0 : logicalTerminal.hashCode());
		result = prime * result + ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
		result = prime * result + ((serviceId == null) ? 0 : serviceId.hashCode());
		result = prime * result + ((sessionNumber == null) ? 0 : sessionNumber.hashCode());
		return result;
	}

	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final SwiftBlock1 other = (SwiftBlock1) obj;
		if (applicationId == null) {
			if (other.applicationId != null) {
				return false;
			}
		} else if (!applicationId.equals(other.applicationId)) {
			return false;
		}
		if (logicalTerminal == null) {
			if (other.logicalTerminal != null) {
				return false;
			}
		} else if (!logicalTerminal.equals(other.logicalTerminal)) {
			return false;
		}
		if (sequenceNumber == null) {
			if (other.sequenceNumber != null) {
				return false;
			}
		} else if (!sequenceNumber.equals(other.sequenceNumber)) {
			return false;
		}
		if (serviceId == null) {
			if (other.serviceId != null) {
				return false;
			}
		} else if (!serviceId.equals(other.serviceId)) {
			return false;
		}
		if (sessionNumber == null) {
			if (other.sessionNumber != null) {
				return false;
			}
		} else if (!sessionNumber.equals(other.sessionNumber)) {
			return false;
		}
		return true;
	}

	/**
	 * @since 7.5
	 */
	public String toJson() {
		final StringBuilder sb = new StringBuilder();
		sb.append("{ \n");
		sb.append( "\"applicationId\" : ").append("\"").append(applicationId).append("\", \n");
		sb.append( "\"serviceId\" : ").append("\"").append(serviceId).append("\", \n");
		sb.append( "\"logicalTerminal\" : \"").append(logicalTerminal).append("\", \n");
		sb.append( "\"sessionNumber\" : \"").append(sessionNumber).append("\", \n");
		sb.append( "\"sequenceNumber\" : \"").append(sequenceNumber).append("\" \n");
		sb.append("} ");
		return sb.toString();
	}
	
	/**
	 * Generic getter for block attributes based on qualified names from {@link SwiftBlock1Field}
	 * @param field field to get
	 * @return field value or <code>null</code> if attribute is not set
	 * @since 7.7
	 */
	public String field(SwiftBlock1Field field) {
		switch (field) {
			case ApplicationId:
				return getApplicationId();
			case ServiceId:
				return getServiceId();
			case LogicalTerminal:
				return getLogicalTerminal();
			case SessionNumber:
				return getSessionNumber();
			case SequenceNumber:
				return getSequenceNumber();
			default:
				return null;
		}
	}
	
	/**
	 * Generic setter for block attributes based on qualified names from {@link SwiftBlock1Field}
	 * @param field field to set
	 * @param value content to set
	 * @since 7.8
	 */
	public void setField(SwiftBlock1Field field, final String value) {
		switch (field) {
			case ApplicationId:
				setApplicationId(value);
				break;
			case ServiceId:
				setServiceId(value);
				break;
			case LogicalTerminal:
				setLogicalTerminal(value);
				break;
			case SessionNumber:
				setSessionNumber(value);
				break;
			case SequenceNumber:
				setSequenceNumber(value);
				break;
			default:
				log.warning("don't know how to set "+field +" to block1");
				break;
		}
	}

	/**
	 * Maps the service id to the service id enumeration
	 * @return the mapped enumeration or null if service id not present or cannot be mapped
	 * @since 7.8.3
	 */
	public ServiceIdType getServiceIdType() {
		try {
			return ServiceIdType.valueOf("_"+this.serviceId);
		} catch (Exception e) {
			log.log(Level.WARNING, "Block1 serviceId contains an invalid value ["+ this.serviceId +"]. The expected values are "+ServiceIdType.values(), e);
			return null;
		}
	}
}
