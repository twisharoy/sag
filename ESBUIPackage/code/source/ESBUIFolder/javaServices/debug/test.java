package ESBUIFolder.javaServices.debug;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2013-03-28 11:58:06 IST
// -----( ON-HOST: VMREGRESSION01.eur.ad.sag

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.data.IData;
import com.wm.data.IDataCursor;
// --- <<IS-END-IMPORTS>> ---

public final class test

{
	// ---( internal utility methods )---

	final static test _instance = new test();

	static test _newInstance() { return new test(); }

	static test _cast(Object o) { return (test)o; }

	// ---( server methods )---




	public static final void testjs (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(testjs)>> ---
		// @sigtype java 3.5
		// [i] field:0:required num1
		// [i] field:0:required num2
		// [o] field:0:required result
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	num1 = IDataUtil.getString( pipelineCursor, "num1" );
			String	num2 = IDataUtil.getString( pipelineCursor, "num2" );
		pipelineCursor.destroy();
		int x = Integer.parseInt(num1);
		int y = Integer.parseInt(num2);
		int z = add(x,y);
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "result", z );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void testjs_1 (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(testjs_1)>> ---
		// @sigtype java 3.5
		// [o] field:0:required result
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	num1 = "13";//IDataUtil.getString( pipelineCursor, "num1" );
			String	num2 = "12";//IDataUtil.getString( pipelineCursor, "num2" );
		pipelineCursor.destroy();
		int x = Integer.parseInt(num1);
		int y = Integer.parseInt(num2);
		int z = add(x,y);
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "result", z );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void testjs_2 (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(testjs_2)>> ---
		// @sigtype java 3.5
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	num1 = "13";//IDataUtil.getString( pipelineCursor, "num1" );
			String	num2 = "12";//IDataUtil.getString( pipelineCursor, "num2" );
		pipelineCursor.destroy();
		int x = Integer.parseInt(num1);
		int y = Integer.parseInt(num2);
		int z = add(x,y);
		// pipeline
		/*IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "result", z );
		pipelineCursor_1.destroy();*/
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private static int add(int a, int b){
		int c = 0;
		c=a+b;
		return c;
	}
	// --- <<IS-END-SHARED>> ---
}

