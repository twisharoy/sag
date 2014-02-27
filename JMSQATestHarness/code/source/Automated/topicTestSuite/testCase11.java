package Automated.topicTestSuite;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2007-06-03 00:07:47 IST
// -----( ON-HOST: CPAI-D620

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.jms.consumer.*;
// --- <<IS-END-IMPORTS>> ---

public final class testCase11

{
	// ---( internal utility methods )---

	final static testCase11 _instance = new testCase11();

	static testCase11 _newInstance() { return new testCase11(); }

	static testCase11 _cast(Object o) { return (testCase11)o; }

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
		 * Send and wait for 1 message to/from Topic4 using no destination (i.e. It will use the Temp Queue)
		 */
		
		String testName            = "TestCase11";
		String description         = "Basic Topic / Request Reply";
		int count                  = 1;
		int timeout                = 3000;
		String[] triggerNames      = new String[] {"Automated.topicTestSuite.testCase11:triggerTest11"};
		String connectionAliasName = "BrokerA_NT_T"; //tx mode: none
		String destinationName     = "Topic4"; //Shared State: true; Shared State Ordering: none
		String myMessage           = "This is my unique message - " + System.currentTimeMillis();
		
		try {
		    JMSTriggerFacade facade = new JMSTriggerFacade(triggerNames[0]);
		    facade.enableTrigger(); 
		}catch(Exception ex) {
		    throw new ServiceException(ex);
		}
		
		IDataCursor ic = pipeline.getCursor();
		IDataUtil.put(ic, "testName", testName);
		IDataUtil.put(ic, "description", description);
		IDataUtil.put(ic, "connectionAliasName", connectionAliasName);
		IDataUtil.put(ic, "destinationName", destinationName);
		IDataUtil.put(ic, "count", count);
		IDataUtil.put(ic, "repeat", String.valueOf(count - 1));
		IDataUtil.put(ic, "timeout", timeout);
		IDataUtil.put(ic, "triggerNames", triggerNames);
		IDataUtil.put(ic, "myMessage", myMessage);
		
		ic.destroy();
		// --- <<IS-END>> ---

                
	}
}

