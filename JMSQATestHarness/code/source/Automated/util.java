package Automated;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2007-06-03 11:26:21 IST
// -----( ON-HOST: CPAI-D620

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.logging.*;
import com.wm.app.b2b.server.jms.consumer.*;
// --- <<IS-END-IMPORTS>> ---

public final class util

{
	// ---( internal utility methods )---

	final static util _instance = new util();

	static util _newInstance() { return new util(); }

	static util _cast(Object o) { return (util)o; }

	// ---( server methods )---




	public static final void logInfo (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(logInfo)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required info
		IDataCursor ic = pipeline.getCursor();
		String info = IDataUtil.getString(ic, "info");
		ic.destroy();
		Logger logger = Logger.getLogger("jmsqa");
		logger.info(info); 
		// --- <<IS-END>> ---

                
	}



	public static final void loopBackTest (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(loopBackTest)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required connectionAliasName
		// [i] field:0:required destinationName
		// [i] record:0:required body
		// [i] - field:0:optional string
		// [i] - object:0:optional bytes
		// [i] - object:0:optional object
		// [i] - record:0:optional data
		// [i] - object:0:optional message
		// [i] object:0:required waitTime
		/*
		 * Set input
		 */
		IDataCursor ic = pipeline.getCursor();
		String connectionAliasName = IDataUtil.getString(ic, "connectionAliasName");
		String destinationName = IDataUtil.getString(ic, "destinationName");
		IData body = IDataUtil.getIData(ic, "body");
		int waitTime = IDataUtil.getInt(ic, "waitTime", 5000);
		ic.destroy();
		 
		
		IData input = IDataFactory.create();
		IDataCursor inputCursor = input.getCursor();
		IDataUtil.put( inputCursor, "connectionAliasName", connectionAliasName );
		IDataUtil.put( inputCursor, "destinationName", destinationName );
		IDataUtil.put( inputCursor, "timeout", waitTime );
		IDataUtil.put( inputCursor, "body", body );
		inputCursor.destroy();
		
		/*
		 * Send and Wait
		 
		 */
		IData output = IDataFactory.create();
		try{
		    output = Service.doInvoke( "pub.jms", "sendAndWait", input );
		}catch( Exception ex){
		    throw new ServiceException("Test Failed: " + ex);
		
		
		}
		
		/*
		 * Validate output
		 */
		IDataCursor outputCursor = output.getCursor();
		IData JMSReplyMessage = IDataUtil.getIData( outputCursor, "JMSReplyMessage" );
		outputCursor.destroy(); 
		
		if ( JMSReplyMessage != null) {
		    IDataCursor JMSReplyMessageCursor = JMSReplyMessage.getCursor();
		    
		    IData bodyOut = IDataUtil.getIData( JMSReplyMessageCursor, "body" );
		    JMSReplyMessageCursor.destroy();
		    if ( bodyOut == null) {
		        throw new ServiceException("Test Failed: No message body was returned.");
		    }
		    
		    if (!IDataUtil.equals(body, bodyOut)) {
		        System.out.println("Log E: Test Failed: The sent message body does not match the reply.\n" +
		                "message body sent = \n" + body + "\n" +
		                "message received = \n" + bodyOut);
		        
		        throw new ServiceException("Test Failed: The sent message body does not match the reply.");   
		    }  
		    
		    System.out.println("Log I: Test Success:\n" +
		            "message body sent = \n" + body + "\n" +
		            "message received = \n" + bodyOut);
		    
		}else {
		    throw new ServiceException("Test Failed: Request timed out.");   
		}
		// --- <<IS-END>> ---

                
	}



	public static final void sleep (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(sleep)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		try {
		    Thread.sleep(3000);
		}catch(InterruptedException ie) {}
		
		// --- <<IS-END>> ---

                
	}



	public static final void testCaseTearDown (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(testCaseTearDown)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:required triggerNames
		IDataCursor ic = pipeline.getCursor();
		String[] triggerNames = IDataUtil.getStringArray(ic, "triggerNames");
		ic.destroy();
		
		if (triggerNames != null) { 
		
		  try { 
		    Thread.sleep(100); // need to sleep for a bit to be sure that the trigger have finished their ack
		  }catch(InterruptedException ex) {}
		
		  for(int i=0; i<triggerNames.length; i++) {
		    try {
		        JMSTriggerFacade facade = new JMSTriggerFacade(triggerNames[i]);
		        facade.disableTrigger();
		    }catch(Exception ex) {
		        throw new ServiceException(ex);
		    }
		  }
		}
		// --- <<IS-END>> ---

                
	}
}

