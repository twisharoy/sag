package Manual;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2007-05-27 16:56:17 IST
// -----( ON-HOST: CPAI-D620

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class batchMsg

{
	// ---( internal utility methods )---

	final static batchMsg _instance = new batchMsg();

	static batchMsg _newInstance() { return new batchMsg(); }

	static batchMsg _cast(Object o) { return (batchMsg)o; }

	// ---( server methods )---




	public static final void service (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(service)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		
		System.out.println("Getting Messages in Batch");
		try {
		    Thread.sleep(500);
		}catch(InterruptedException ie) {}
		// --- <<IS-END>> ---

                
	}
}

