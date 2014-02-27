

// -----( IS Java Code Template v1.2
// -----( CREATED: 2007-05-27 20:10:20 IST
// -----( ON-HOST: CPAI-D620

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.*;
// --- <<IS-END-IMPORTS>> ---

public final class Manual

{
	// ---( internal utility methods )---

	final static Manual _instance = new Manual();

	static Manual _newInstance() { return new Manual(); }

	static Manual _cast(Object o) { return (Manual)o; }

	// ---( server methods )---




	public static final void ISRunTime (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(ISRunTime)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		throw new com.wm.app.b2b.server.ISRuntimeException();
		// --- <<IS-END>> ---

                
	}



	public static final void dataTypes (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(dataTypes)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] object:0:required object
		// [o] object:0:required bytes
		// [o] record:0:required data
		// [o] field:0:required string
		
		
		String string  = "Welcome to webMethods Messaging World - " + System.currentTimeMillis();
		
		byte[] bytes = string.getBytes();
		
		HashMap map = new HashMap();
		map.put("uniqueString", string);
		
		IData data = IDataFactory.create();
		IDataCursor dataCursor = data.getCursor();
		IDataUtil.put(dataCursor, "string", string); 
		dataCursor.destroy();
		
		
		IDataCursor ic = pipeline.getCursor();
		
		IDataUtil.put(ic, "string", string);
		IDataUtil.put(ic, "bytes", bytes);
		IDataUtil.put(ic, "object", map);
		IDataUtil.put(ic, "data", data);
		
		ic.destroy();
		// --- <<IS-END>> ---

                
	}
}

