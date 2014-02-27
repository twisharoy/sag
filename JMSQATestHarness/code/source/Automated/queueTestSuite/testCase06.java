package Automated.queueTestSuite;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2007-06-02 21:04:39 IST
// -----( ON-HOST: CPAI-D620

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.jms.consumer.*;
// --- <<IS-END-IMPORTS>> ---

public final class testCase06

{
	// ---( internal utility methods )---

	final static testCase06 _instance = new testCase06();

	static testCase06 _newInstance() { return new testCase06(); }

	static testCase06 _cast(Object o) { return (testCase06)o; }

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
		// [o] field:0:required destinationNameA
		// [o] field:0:required destinationNameB
		// [o] field:0:required repeat
		/*
		 * Send message to Queue6 and Queue7 using Serial Trigger w/ XOR Join
		 */
		
		String testName            = "TestCase6";
		String description         = "Basic Queue / Join test";
		String[] triggerNames      = new String[] {"Automated.queueTestSuite.testCase06:triggerTest06"};
		String connectionAliasName = "BrokerA_NT_Q"; //tx mode: none
		String destinationNameA    = "Queue6"; //Shared State: true; Shared State Ordering: none
		String destinationNameB    = "Queue7"; //Shared State: true; Shared State Ordering: none
		int count                  = 1;
		String repeat              = "1";
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
		IDataUtil.put(ic, "destinationNameA", destinationNameA);
		IDataUtil.put(ic, "destinationNameB", destinationNameB);
		IDataUtil.put(ic, "count", count);
		IDataUtil.put(ic, "repeat", repeat);
		IDataUtil.put(ic, "timeout", timeout);
		IDataUtil.put(ic, "triggerNames", triggerNames);
		// --- <<IS-END>> ---

                
	}
}

