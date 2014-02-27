package Manual;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2007-02-03 20:01:01 IST
// -----( ON-HOST: localhost

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.dispatcher.trigger.*;
// --- <<IS-END-IMPORTS>> ---

public final class triggerRetryFailure

{
	// ---( internal utility methods )---

	final static triggerRetryFailure _instance = new triggerRetryFailure();

	static triggerRetryFailure _newInstance() { return new triggerRetryFailure(); }

	static triggerRetryFailure _cast(Object o) { return (triggerRetryFailure)o; }

	// ---( server methods )---




	public static final void viewMonitor (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(viewMonitor)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		TriggerResourceMonitor trm = TriggerResourceMonitor.getInstance();
		String out = trm.toString();
		IDataCursor ic = pipeline.getCursor();
		IDataUtil.put(ic, "out", out);	
		// --- <<IS-END>> ---

                
	}
}

