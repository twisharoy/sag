package Automated.topicTestSuite;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2007-06-02 23:57:48 IST
// -----( ON-HOST: CPAI-D620

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.jms.consumer.*;
// --- <<IS-END-IMPORTS>> ---

public final class testCase07

{
	// ---( internal utility methods )---

	final static testCase07 _instance = new testCase07();

	static testCase07 _newInstance() { return new testCase07(); }

	static testCase07 _cast(Object o) { return (testCase07)o; }

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
		 * Send message to Topic6 and Topic7 using Serial Trigger w/ AND Join
		 */
		
		String testName            = "TestCase7";
		String description         = "Basic Topic AND Join Test";
		String[] triggerNames      = new String[] {"Automated.topicTestSuite.testCase07:triggerTest07"};
		String connectionAliasName = "BrokerA_NT_T"; //tx mode: none
		String destinationNameA    = "Topic6"; //Shared State: true; Shared State Ordering: none
		String destinationNameB    = "Topic7"; //Shared State: true; Shared State Ordering: none
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

