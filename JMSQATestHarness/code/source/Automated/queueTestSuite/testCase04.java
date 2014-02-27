package Automated.queueTestSuite;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2007-06-02 21:04:01 IST
// -----( ON-HOST: CPAI-D620

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.jms.consumer.*;
// --- <<IS-END-IMPORTS>> ---

public final class testCase04

{
	// ---( internal utility methods )---

	final static testCase04 _instance = new testCase04();

	static testCase04 _newInstance() { return new testCase04(); }

	static testCase04 _cast(Object o) { return (testCase04)o; }

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
		 * Send 5 messages to Queue using Serial Trigger - IDOC Message Type
		 */
		 
		String testName            = "TestCase4"; 
		String description         = "Basic Queue test.";
		String[] triggerNames      = new String[] {"Automated.queueTestSuite.testCase04:triggerTest04"};
		String connectionAliasName = "BrokerA_Native_NT_Q";
		String destinationName     = "Queue2"; //Shared State: true; Shared State Ordering: none]
		int count                  = 5;
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
		IDataUtil.put(ic, "repeat", String.valueOf(count - 1));
		IDataUtil.put(ic, "timeout", timeout);
		IDataUtil.put(ic, "triggerNames", triggerNames);
		// --- <<IS-END>> ---

                
	}
}

