

// -----( IS Java Code Template v1.2
// -----( CREATED: 2007-01-04 17:37:42 IST
// -----( ON-HOST: cpai-opt520.webm.webmethods.com

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.qa.testHarness.*;
import java.util.logging.*;
// --- <<IS-END-IMPORTS>> ---

public final class Automated

{
	// ---( internal utility methods )---

	final static Automated _instance = new Automated();

	static Automated _newInstance() { return new Automated(); }

	static Automated _cast(Object o) { return (Automated)o; }

	// ---( server methods )---




	public static final void readme (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(readme)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		/*
		
		JMS JQA Test Harness can be used to run individual test cases (see *.runTestCase) 
		or an entire test suite (see *.runTestSuite). The output of each test will be 
		written to a rotating log file located in the \logs directory. Each test case will 
		also return output in the form of XML so the tests can be called externally (e.g. 
		by JMeter).
		
		JMS JQA Test Harness includes x test suites:
		
		    queueTestSuite - responsible for testing the basic JMS Queue functionality.
		    This test suite requires queue1 - queue7.
		
		    topicTestSuite - responsible for testing the basic JMS Topic functionality.
		
		    ....
		
		*/
		// --- <<IS-END>> ---

                
	}



	public static final void testHarnessSetup (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(testHarnessSetup)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		try {
		    LogManager logManager = LogManager.getLogManager();
		    logManager.reset();
		    FileHandler fileHandler = new FileHandler(".\\packages\\JMSQATestHarness\\jmsqaResults%g.log", 0, 10);
		    //FileHandler fileHandler = new FileHandler(".\\logs\\jmsqaResults%g.log", 0, 10);
		    fileHandler.setFormatter(new BasicLoggingFormatter());      
		    Logger logger = Logger.getLogger("jmsqa");
		    logger.addHandler(fileHandler); 
		    logManager.addLogger(logger);  
		}catch(Exception ex) {
		    throw new ServiceException(ex);
		}  
		// --- <<IS-END>> ---

                
	}
}

