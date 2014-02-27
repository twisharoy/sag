package Automated.topicTestSuite;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2007-06-02 23:59:06 IST
// -----( ON-HOST: CPAI-D620

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.jms.consumer.*;
// --- <<IS-END-IMPORTS>> ---

public final class testCase08

{
	// ---( internal utility methods )---

	final static testCase08 _instance = new testCase08();

	static testCase08 _newInstance() { return new testCase08(); }

	static testCase08 _cast(Object o) { return (testCase08)o; }

	// ---( server methods )---




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
		 * Send 10 messages to Topic using a Message Selectors
		 *
		 *  Note: The trick is that we have two triggers running with two different message 
		 *        selectors, but we only want the first trigger to pick up the messages.
		 */
		
		String testName            = "TestCase8";
		String description         = "Basic Topic / Message Selectors Test";
		String[] triggerNames      = new String[] {"Automated.topicTestSuite.testCase08:triggerTest08a", "Automated.topicTestSuite.testCase08:triggerTest08b"};
		String connectionAliasName = "BrokerA_NT_T"; //tx mode: none
		String destinationName     = "Topic6"; //Shared State: true; Shared State Ordering: none
		int count                  = 10;
		int timeout                = 3000;
		
		// enable trigger
		
		for (int i=0; i<triggerNames.length; i++) {
		  try {
		      JMSTriggerFacade facade = new JMSTriggerFacade(triggerNames[i]);
		      facade.enableTrigger(); 
		  }catch(Exception ex) {
		      throw new ServiceException(ex);
		  }
		}
		
		// set test input
		
		IDataCursor ic = pipeline.getCursor();
		IDataUtil.put(ic, "testName", testName);
		IDataUtil.put(ic, "description", description);
		IDataUtil.put(ic, "connectionAliasName", connectionAliasName);
		IDataUtil.put(ic, "destinationName", destinationName);
		IDataUtil.put(ic, "count", count);
		IDataUtil.put(ic, "repeat", String.valueOf(count - 1));
		IDataUtil.put(ic, "timeout", timeout);
		IDataUtil.put(ic, "triggerNames", triggerNames);
		// --- <<IS-END>> ---

                
	}
}

