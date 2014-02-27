package Automated.topicTestSuite;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2007-06-03 10:23:10 IST
// -----( ON-HOST: CPAI-D620

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.jms.consumer.*;
// --- <<IS-END-IMPORTS>> ---

public final class testCase15

{
	// ---( internal utility methods )---

	final static testCase15 _instance = new testCase15();

	static testCase15 _newInstance() { return new testCase15(); }

	static testCase15 _cast(Object o) { return (testCase15)o; }

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
		 * Send & Receiving Message via Public Service - String Message Type
		 * This Test SHOULD PASS in case of Topics ordinary topic subscribers 
		 * are not durable & they can only receive messages that are published 
		 * when they are active. In this case we are creating a durable subscriber
		 */
		 
		String testName            = "TestCase15"; 
		String description         = "Send/Rec via Public Service Using Durable Subscriber - Topics";
		String connectionAliasName = "BrokerA_NT_T";
		String destinationName     = "Topic3"; //Shared State: true; Shared State Ordering: none]
		int count                  = 1;
		int timeout                = 3000;
		String myMessage           = "This is my unique message - " + System.currentTimeMillis();
		// set test input
		IDataCursor ic = pipeline.getCursor();
		IDataUtil.put(ic, "testName", testName);
		IDataUtil.put(ic, "description", description);
		IDataUtil.put(ic, "connectionAliasName", connectionAliasName);
		IDataUtil.put(ic, "destinationName", destinationName);
		IDataUtil.put(ic, "count", count);
		IDataUtil.put(ic, "repeat", String.valueOf(count - 1));
		IDataUtil.put(ic, "timeout", timeout);
		IDataUtil.put(ic, "myMessage", myMessage);
		// --- <<IS-END>> ---

                
	}
}

