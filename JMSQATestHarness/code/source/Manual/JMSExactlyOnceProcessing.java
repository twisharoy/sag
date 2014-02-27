package Manual;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2008-02-21 11:31:47 IST
// -----( ON-HOST: asaha-opt745.webm.webmethods.com

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class JMSExactlyOnceProcessing

{
	// ---( internal utility methods )---

	final static JMSExactlyOnceProcessing _instance = new JMSExactlyOnceProcessing();

	static JMSExactlyOnceProcessing _newInstance() { return new JMSExactlyOnceProcessing(); }

	static JMSExactlyOnceProcessing _cast(Object o) { return (JMSExactlyOnceProcessing)o; }

	// ---( server methods )---




	public static final void Sleep (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(Sleep)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		Long Time= 60000L;
		System.out.println("Arindam");
		try
		{
		 Thread.sleep(Time);
		}
		 catch (InterruptedException e)
		{
		 e.printStackTrace();
		}
		 System.out.println("Arindam");
		// --- <<IS-END>> ---

                
	}
}

