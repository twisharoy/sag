package Automated.topicTestSuite;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2007-06-03 11:48:00 IST
// -----( ON-HOST: CPAI-D620

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.jms.consumer.*;
// --- <<IS-END-IMPORTS>> ---

public final class testCase19

{
	// ---( internal utility methods )---

	final static testCase19 _instance = new testCase19();

	static testCase19 _newInstance() { return new testCase19(); }

	static testCase19 _cast(Object o) { return (testCase19)o; }

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
		 * This Test SHOULD FAIL in case of Topics ordinary topic subscribers 
		 * are not durable & they can only receive messages that are published 
		 * when they are active. In this case we are creating a durable subscriber
		 * When we set Ignore Locally Published Flag to true, The trigger should 
		 * ignore message published using the same connection alias
		 */
		 
		String testName            = "TestCase19"; 
		String description         = "Using Durable Subscriber & isNoLocal = True for Topics [SHOULD FAIL]";
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

