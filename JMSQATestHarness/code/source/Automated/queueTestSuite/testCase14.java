package Automated.queueTestSuite;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2007-08-27 17:26:42 IST
// -----( ON-HOST: cpai-d620.webm.webmethods.com

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.jms.consumer.*;
// --- <<IS-END-IMPORTS>> ---

public final class testCase14

{
	// ---( internal utility methods )---

	final static testCase14 _instance = new testCase14();

	static testCase14 _newInstance() { return new testCase14(); }

	static testCase14 _cast(Object o) { return (testCase14)o; }

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
		 * Send & Receciving Message via Public Service - String Message Type using Message Selectors
		 */
		 
		String testName            = "TestCase14"; 
		String description         = "Basic Queue test.";
		String connectionAliasName = "BrokerA_Native_NT_Q";
		String destinationName     = "Queue9"; //Shared State: true; Shared State Ordering: none]
		int count                  = 1;
		int timeout                = 3000;
		String myMessage           = "This is my unique message for city of LONDON - " + System.currentTimeMillis();
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

