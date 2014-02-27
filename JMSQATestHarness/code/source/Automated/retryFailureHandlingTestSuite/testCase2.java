package Automated.retryFailureHandlingTestSuite;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2007-06-03 15:15:38 IST
// -----( ON-HOST: CPAI-D620

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.jms.consumer.*;
// --- <<IS-END-IMPORTS>> ---

public final class testCase2

{
	// ---( internal utility methods )---

	final static testCase2 _instance = new testCase2();

	static testCase2 _newInstance() { return new testCase2(); }

	static testCase2 _cast(Object o) { return (testCase2)o; }

	// ---( server methods )---




	public static final void checkIfSuspended (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(checkIfSuspended)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:required triggerNames
		// [o] field:0:required isSuspended
		IDataCursor pipelineCursor = pipeline.getCursor();
		String[] triggerNames = IDataUtil.getStringArray( pipelineCursor, "triggerNames" );
		
		boolean isSuspended = false;
		
		try {
		    Thread.sleep(1000);
		}catch(Exception ex) {}
		
		try {
		    JMSTriggerFacade facade = new JMSTriggerFacade(triggerNames[0]);
		    if (facade.getState() == 2) { // 2 = suspended
		        isSuspended = true;
		    }
		}catch(Exception ex) {
		    throw new ServiceException(ex);
		}
		
		IDataUtil.put(pipelineCursor, "isSuspended", String.valueOf(isSuspended));
		pipelineCursor.destroy();
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
		/*
		 *  Trigger Retry Feature
		 */
		
		String testName            = "TestCase2"; 
		String description         = "Trigger Retry Test - For Topics";
		String[] triggerNames      = new String[] {"Automated.retryFailureHandlingTestSuite.testCase2:triggerTest2"};
		String connectionAliasName = "BrokerA_NT_T"; //tx mode: none
		String destinationName     = "Topic1"; //Shared State: true; Shared State Ordering: none]
		int count                  = 1;
		int timeout                = 65000;
		
		// enable trigger
		try {
		    JMSTriggerFacade facade = new JMSTriggerFacade(triggerNames[0]);
		    facade.enableTrigger();
		}catch(Exception ex) {
		    throw new ServiceException(ex);
		}
		
		// reset dummyAdapter
		Automated.retryFailureHandlingTestSuite.backend.resetTest(null);
		 
		// set test input
		IDataCursor ic = pipeline.getCursor();
		IDataUtil.put(ic, "testName", testName);
		IDataUtil.put(ic, "description", description);
		IDataUtil.put(ic, "connectionAliasName", connectionAliasName);
		IDataUtil.put(ic, "destinationName", destinationName);
		IDataUtil.put(ic, "count", count);
		IDataUtil.put(ic, "timeout", timeout);
		IDataUtil.put(ic, "triggerNames", triggerNames);
		// --- <<IS-END>> ---

                
	}
}

