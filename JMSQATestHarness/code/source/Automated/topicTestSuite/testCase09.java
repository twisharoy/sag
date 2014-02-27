package Automated.topicTestSuite;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2007-06-03 00:03:06 IST
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

public final class testCase09

{
	// ---( internal utility methods )---

	final static testCase09 _instance = new testCase09();

	static testCase09 _newInstance() { return new testCase09(); }

	static testCase09 _cast(Object o) { return (testCase09)o; }

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
		 * Tests Message Selectors & Local Filters in conjunction
		 *
		 * 
		 */
		String testName            = "TestCase9";
		String description         = "Message Selector & Local Filter Test For Topics";
		String[] triggerNames      = new String[] {"Automated.topicTestSuite.testCase09:triggerTest09"};
		String connectionAliasName = "BrokerA_NT_T"; //tx mode: none
		String destinationName     = "Topic8"; //Shared State: true; Shared State Ordering: none]
		int count                  = 1;
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

