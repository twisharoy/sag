package pub;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-02-27 15:04:38 IST
// -----( ON-HOST: VMSuiteCSI.eur.ad.sag

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.qa.testHarness.*;
import java.util.logging.*;
// --- <<IS-END-IMPORTS>> ---

public final class testHarness1

{
	// ---( internal utility methods )---

	final static testHarness1 _instance = new testHarness1();

	static testHarness1 _newInstance() { return new testHarness1(); }

	static testHarness1 _cast(Object o) { return (testHarness1)o; }

	// ---( server methods )---




	public static final void countDown (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(countDown)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required testName
		IDataCursor ic = pipeline.getCursor();
		String testName = IDataUtil.getString(ic, "testName");
		ic.destroy();
		
		if (testName == null || testName.length() < 1) {
		    throw new ServiceException("missing required field: testName");
		}
		 
		
		IntegrationTest test = IntegrationTestMap.getInstance().getIntegrationTest(testName);
		if (test != null) {
		    test.countDown();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void countDownAndReportError (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(countDownAndReportError)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required testName
		// [i] field:0:required errorMsg
		IDataCursor ic = pipeline.getCursor();
		String testName = IDataUtil.getString(ic, "testName");
		String errorMsg = IDataUtil.getString(ic, "errorMsg");
		ic.destroy();
		 
		
		if (testName == null || testName.length() < 1) {
		    throw new ServiceException("missing required field: testName");
		}
		
		IntegrationTest test = IntegrationTestMap.getInstance().getIntegrationTest(testName);
		if (test != null) {
		    test.countDownAndReportError(errorMsg);
		}
		// --- <<IS-END>> ---

                
	}



	public static final void countDownAndValidate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(countDownAndValidate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required testName
		// [i] field:0:required results
		IDataCursor ic = pipeline.getCursor();
		String testName = IDataUtil.getString(ic, "testName");
		String results = IDataUtil.getString(ic, "results");
		ic.destroy();
		
		if (testName == null || testName.length() < 1) {
		    throw new ServiceException("missing required field: testName");
		}
		
		IntegrationTest test = IntegrationTestMap.getInstance().getIntegrationTest(testName);
		if (test != null) {
		    test.countDownAndValidate(results);
		}
		// --- <<IS-END>> ---

                
	}



	public static final void endTest (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(endTest)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required testName
		// [i] object:0:required timeout
		// [o] record:0:required endTestOutput
		// [o] - record:0:required IntegrationTestResults
		// [o] -- field:0:required name
		// [o] -- field:0:required description
		// [o] -- field:0:required status
		// [o] -- field:0:required statusMessage
		// [o] -- record:0:required statistics
		// [o] --- object:0:required totalTime
		// [o] --- object:0:required completedTests
		// [o] --- object:0:required expectedTests
		IDataCursor ic = pipeline.getCursor();
		String testName = IDataUtil.getString(ic, "testName");
		int timeout = IDataUtil.getInt( ic, "timeout", 1000 );
		
		ic.destroy();
		
		if (testName == null || testName.length() < 1) {
		    throw new ServiceException("missing required field: testName");
		}
		
		IntegrationTest test = IntegrationTestMap.getInstance().getIntegrationTest(testName);
		
		if (test != null) {
		    IntegrationTestResults results = test.endTest();
		    
		IDataUtil.put(ic, "endTestOutput", results.getAsData());
		}
		
		
		// --- <<IS-END>> ---

                
	}



	public static final void startAsynchronousTest (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(startAsynchronousTest)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required testName
		// [i] field:0:required description
		// [i] field:0:required count
		// [i] field:0:required timeout
		IDataCursor pipelineCursor = pipeline.getCursor();
		String testName = IDataUtil.getString( pipelineCursor, "testName" );
		String description = IDataUtil.getString( pipelineCursor, "description" );
		int    count = IDataUtil.getInt( pipelineCursor, "count", 1);
		int    timeout = IDataUtil.getInt( pipelineCursor, "timeout", 1000 );
		pipelineCursor.destroy();
		
		AsynchronousIntegrationTest test = new AsynchronousIntegrationTest(testName, description, count, timeout, null);
		IntegrationTestMap.getInstance().addIntegrationTest(test);
		test.startTest();
		// --- <<IS-END>> ---

                
	}



	public static final void startSynchronousTest (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(startSynchronousTest)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required testName
		// [i] field:0:required description
		// [i] field:0:required count
		// [i] field:0:optional expectedResults
		IDataCursor pipelineCursor = pipeline.getCursor();
		String testName = IDataUtil.getString( pipelineCursor, "testName" );
		String description = IDataUtil.getString( pipelineCursor, "description" );
		int count = IDataUtil.getInt( pipelineCursor, "count", 1);
		String expectedResults = IDataUtil.getString( pipelineCursor, "expectedResults" );
		pipelineCursor.destroy();
		
		IntegrationTest test = new IntegrationTest(testName, description, count, expectedResults);
		IntegrationTestMap.getInstance().addIntegrationTest(test);
		test.startTest();
		
		// --- <<IS-END>> ---

                
	}
}

