

// -----( IS Java Code Template v1.2
// -----( CREATED: 2011-12-07 14:20:49 IST
// -----( ON-HOST: mcrtomi01.eur.ad.sag

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.Date;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;
import com.wm.util.Table;
// --- <<IS-END-IMPORTS>> ---

public final class DebugTable

{
	// ---( internal utility methods )---

	final static DebugTable _instance = new DebugTable();

	static DebugTable _newInstance() { return new DebugTable(); }

	static DebugTable _cast(Object o) { return (DebugTable)o; }

	// ---( server methods )---




	public static final void js123 (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(js123)>> ---
		// @sigtype java 3.5
		// [i] field:0:required str1
		// [o] object:0:required result
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	str1 = IDataUtil.getString( pipelineCursor, "str1" );
		pipelineCursor.destroy();
		String[] keys = {"a","b","c","d"};
		Table vt = new Table("myTable",keys);
		Values row1 = new Values();
		row1.put("a","a1");
		row1.put("b","b1");
		row1.put("c","c1");
		row1.put("d",new Object());
		Values row2 = new Values();
		row2.put("a","a2");
		row2.put("b","b2");
		row2.put("c","c2");
		row2.put("d",new Object());
		Values row4 = new Values();
		row4.put("a","a2");
		row4.put("b","b2");
		row4.put("c","c2");
		Date date = new Date(86,12,19); 
		row4.put("e",date);
		
		vt.addRow(row1);
		vt.addRow(row2);
		vt.addRow(row4);
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		Object result = new Object();
		IDataUtil.put( pipelineCursor_1, "result", vt );
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}
}

