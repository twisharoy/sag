package Automated.topicTestSuite;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2007-06-03 00:09:10 IST
// -----( ON-HOST: CPAI-D620

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.jms.producer.*;
import javax.jms.*;
import com.wm.app.b2b.server.jms.consumer.*;
// --- <<IS-END-IMPORTS>> ---

public final class testCase12

{
	// ---( internal utility methods )---

	final static testCase12 _instance = new testCase12();

	static testCase12 _newInstance() { return new testCase12(); }

	static testCase12 _cast(Object o) { return (testCase12)o; }

	// ---( server methods )---




	public static final void target (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(target)>> ---
		// @specification pub.jms:batchTriggerSpec
		// @subtype unknown
		// @sigtype java 3.5
		// Sleep for a second. This will slow down the consumer enough to get more than one
		// message in the queue (i.e. to get a batch of messages).
		
		System.out.println("****JMS-PbSb****");
		try {
		    Thread.sleep(2000);
		}catch(InterruptedException ie) {}
		
		// Check to see if this is a batch of messages. If it is then send the countDown() to
		// end the test (i.e. signal success)! It's OK if this is not a batch, for now. Not all
		// target service invokations need to be a batch. However, if none of the target invokations
		// are a batch then the test will timeout and fail.
		
		IDataCursor pipelineCursor = pipeline.getCursor();
		IData[]	messages = IDataUtil.getIDataArray( pipelineCursor, "JMSMessage" );
		pipelineCursor.destroy();
		
		if ( messages != null && messages.length > 1) {
		
		    IData input = IDataFactory.create();
		    IDataCursor inputCursor = input.getCursor();
		    IDataUtil.put( inputCursor, "testName", "TestCase12" );
		    inputCursor.destroy(); 
		    try{
		        Service.doInvoke( "pub.testHarness", "countDown", input );
		    }catch( Exception e){
		        throw new ServiceException(e);
		    }
		}
		
		
		
		
		
		
		
		
		
		
		
		// --- <<IS-END>> ---

                
	}



	public static final void testCaseSetUp (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(testCaseSetUp)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required testName
		// [o] field:0:required description
		// [o] object:0:required count
		// [o] object:0:required timeout
		// [o] field:1:required triggerNames
		// [o] field:0:required connectionAliasName
		// [o] field:0:required destinationName
		// [o] field:0:required repeat
		/*
		 * Send a batch of 10 messages to a trigger and see if the target service receives
		 * the messages in a batch. Note the target service will not receive the entire batch
		 * of messages in one shot (the consumer is too fast for that). As long as one of the
		 * target invokations gets more than one message then this is a success.
		 */
		
		String testName            = "TestCase12";
		String description         = "Batch Test For Topics";
		String[] triggerNames      = new String[] {"Automated.topicTestSuite.testCase12:triggerTest12"};
		String connectionAliasName = "BrokerA_Native_NT_T"; //tx mode: none
		String destinationName     = "Topic3"; //Shared State: true; Shared State Ordering: none]
		int count                  = 1;
		String repeat              = "5";
		int timeout                = 3000;
		
		// enable trigger
		try {
		    JMSTriggerFacade facade = new JMSTriggerFacade(triggerNames[0]);
		    facade.enableTrigger(); 
		}catch(Exception ex) {
		    throw new ServiceException(ex);
		}
		 
		// set test input
		IDataCursor ic = pipeline.getCursor();
		IDataUtil.put(ic, "testName", testName);
		IDataUtil.put(ic, "description", description);
		IDataUtil.put(ic, "connectionAliasName", connectionAliasName);
		IDataUtil.put(ic, "destinationName", destinationName);
		IDataUtil.put(ic, "count", count);
		IDataUtil.put(ic, "repeat", repeat);
		IDataUtil.put(ic, "timeout", timeout);
		IDataUtil.put(ic, "triggerNames", triggerNames);
		// --- <<IS-END>> ---

                
	}
}

