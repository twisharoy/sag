package ESBUIFolder.javaServices;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2013-03-28 11:58:09 IST
// -----( ON-HOST: VMREGRESSION01.eur.ad.sag

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.awt.event.MouseAdapter;
import java.util.Date;
// --- <<IS-END-IMPORTS>> ---

public final class verify

	extends MouseAdapter
	implements Comparable

{
	// ---( internal utility methods )---

	final static verify _instance = new verify();

	static verify _newInstance() { return new verify(); }

	static verify _cast(Object o) { return (verify)o; }

	// ---( server methods )---




	public static final void jstest (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(jstest)>> ---
		// @sigtype java 3.5
		System.out.println("Hello World!!!");
		test();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	public static final void test(){
		System.out.println(new Date());
	}
	
	@Override
	public int compareTo(Object o) {
		return 0;
	}
	// --- <<IS-END-SHARED>> ---
}

