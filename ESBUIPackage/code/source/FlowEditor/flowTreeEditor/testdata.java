package FlowEditor.flowTreeEditor;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-07-17 12:55:37 IST
// -----( ON-HOST: MCBHRE01.eur.ad.sag

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.Session;
import com.wm.util.JournalLogger;
import com.wm.util.DebugMsg;
// --- <<IS-END-IMPORTS>> ---

public final class testdata

{
	// ---( internal utility methods )---

	final static testdata _instance = new testdata();

	static testdata _newInstance() { return new testdata(); }

	static testdata _cast(Object o) { return (testdata)o; }

	// ---( server methods )---



    public static final Values c1 (Values in)
    {
        Values out = in;
		// --- <<IS-START(c1)>> ---
		// @specification FlowEditor.specs:spec1
		// @subtype c
		// @sigtype java 3.0
    out = cc1(Service.getSession(), in);
		// --- <<IS-END>> ---
        return out;
                
	}



	public static final void js1 (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(js1)>> ---
		// @sigtype java 3.5
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	
	static {
	    try {
	        System.loadLibrary("flowTreeEditor_testdata");
	        JournalLogger.log(DebugMsg.LOG_MSG, JournalLogger.FAC_PACKAGE, JournalLogger.INFO, 
				"Loading native library: flowTreeEditor_testdata");
	    } catch (UnsatisfiedLinkError e) {
	        JournalLogger.logError(DebugMsg.LOG_MSG, JournalLogger.FAC_PACKAGE, 
				e.getMessage());
	    }
	}
	
	native static Values cc1(Session session, Values in);
		
	// --- <<IS-END-SHARED>> ---
}

