package Automated.retryFailureHandlingTestSuite;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2007-01-04 17:37:31 IST
// -----( ON-HOST: cpai-opt520.webm.webmethods.com

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class backend

{
	// ---( internal utility methods )---

	final static backend _instance = new backend();

	static backend _newInstance() { return new backend(); }

	static backend _cast(Object o) { return (backend)o; }

	// ---( server methods )---




	public static final void dummyAdapterService (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(dummyAdapterService)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		System.out.println("Invoking Dummy Adapter 1."); 
		if (!isAvailable())
		 {
		    throw new com.wm.app.b2b.server.ISRuntimeException();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void dummyMonitoringService (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(dummyMonitoringService)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required isAvailable
		IDataCursor ic = pipeline.getCursor();
		IDataUtil.put(ic, "isAvailable", String.valueOf(isAvailable()));
		ic.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void resetTest (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(resetTest)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		_startTime = System.currentTimeMillis();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private static int TIME_TO_FAIL = 20 * 1000;
	private static long _startTime;
	
	private static boolean isAvailable() {
	    if (System.currentTimeMillis() > _startTime + TIME_TO_FAIL) {
	        return true;
	    }else {
	        return false;
	    }
	}
	// --- <<IS-END-SHARED>> ---
}

