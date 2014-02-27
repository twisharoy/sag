package com.wm.qa.testHarness;

import java.util.ArrayList;
import java.util.Iterator;
import com.wm.data.*;

/**
 * 
 * 
 */
public class IntegrationTestResults {
    
    private String name;
    private String description;
    private ArrayList<String> validationErrors = new ArrayList<String>();
    private long totalTime;
    private long expectedTests;
    private long completedTests;
    private Exception exception;
    private int maxErrors = 5;

    /**
     * 
     */
    public IntegrationTestResults(String name, String description) {
        
        this.name = name;
        this.description = description;
    }
    
    /**
     * 
     */
    public void setTestStats(long expectedTests, long completedTests) {
        
        this.expectedTests = expectedTests;
        this.completedTests = completedTests;
    }
    
    /**
     * 
     */
    public void setMaxErrors(int maxErrors) {
        
        this.maxErrors = maxErrors;
    }
    
    /**
     * 
     */
    public long getExpectedTests() {
        
        return expectedTests;
    }
    
    /**
     * 
     */
    public long getCompletedTests() {
        
        return completedTests;
    }
    
    /**
     * 
     */
    public void setException(Exception exception) {
        
        this.exception = exception;
    }
    
    /**
     * 
     */
    public Throwable getException() {
        
        return exception;
    }
    
    /**
     * 
     */
    public void setTotalTime(long testTime) {
        
        this.totalTime = testTime;
    }
    
    /**
     * 
     */
    public long getTotalTime() {
        
        return totalTime;
    }
    
    /**
     * 
     */
    public void addValidationError(String validationError) {
        
        if (validationErrors.size() < maxErrors) {
            validationErrors.add(validationError);
        }
    }
    
    /**
     * 
     */
    public ArrayList getValidationErrors() {
        
        return validationErrors;
    }
    
    /**
     * 
     */
    public String toString() {
        
        String results = totalTime + " ms " + completedTests + "/" + expectedTests + "] " + name + " - " + description;
        
        if (expectedTests != completedTests || validationErrors.size() > 0) {
            return "[FAILED " + results + " " + getValidationErrorsString();
        
        }else if (exception != null) {
            return "[ECXEPTION " + results + ": " + exception;
        
        }else {
            return "[OK " + results;
        }
    }
    
    /**
     * 
     */
    public IData getAsData() {
        
        String status;
        if (expectedTests != completedTests || validationErrors.size() > 0) {
            status = "FAILED";
        }else if (exception != null) {
            status = "FAILED";
        }else {
            status = "OK";
        }

        IData data = IDataFactory.create();
        IDataCursor inputCursor = data.getCursor();

        IData  IntegrationTestResults = IDataFactory.create();
        IDataCursor IntegrationTestResultsCursor = IntegrationTestResults.getCursor();
        IDataUtil.put( IntegrationTestResultsCursor, "name", name );
        IDataUtil.put( IntegrationTestResultsCursor, "description", description );
        IDataUtil.put( IntegrationTestResultsCursor, "status", status );
        IDataUtil.put( IntegrationTestResultsCursor, "statusMessage", toString() );
        
        IData  statistics = IDataFactory.create();
        IDataCursor statisticsCursor = statistics.getCursor();
        IDataUtil.put( statisticsCursor, "totalTime", totalTime );
        IDataUtil.put( statisticsCursor, "completedTests", completedTests );
        IDataUtil.put( statisticsCursor, "expectedTests", expectedTests );
        statisticsCursor.destroy();
        IDataUtil.put( IntegrationTestResultsCursor, "statistics", statistics );

        IData[] errors = null;
        if (exception != null) {
            errors = new IData[1];
            errors[0] = IDataFactory.create();
            IDataCursor errorsCursor = errors[0].getCursor();
            IDataUtil.put( errorsCursor, "type", "exception" );
            IDataUtil.put( errorsCursor, "errormessage", exception.getLocalizedMessage() );
            errorsCursor.destroy();
            
        }else if(!validationErrors.isEmpty()){
            errors = new IData[validationErrors.size()];
            for (int i=0; i<validationErrors.size(); i++) {
                errors[i] = IDataFactory.create();
                IDataCursor errorsCursor = errors[i].getCursor();
                IDataUtil.put( errorsCursor, "type", "validationError" );
                IDataUtil.put( errorsCursor, "errormessage", validationErrors.get(i) );
                errorsCursor.destroy();
            }
        }
        if (errors != null) {
            IDataUtil.put( IntegrationTestResultsCursor, "errors", errors );
        }
        IntegrationTestResultsCursor.destroy();
        IDataUtil.put( inputCursor, "IntegrationTestResults", IntegrationTestResults );
        inputCursor.destroy();
        
        return data;
    }
    
    /**
     * 
     */
    private String getValidationErrorsString() {
        
        if (validationErrors.isEmpty())
            return "";
        
        StringBuffer sb = new StringBuffer();
        Iterator it = validationErrors.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            
            if (it.hasNext()) {
                sb.append(", ");
            }else {
                sb.append(".");
            }
        }
        return sb.toString();
    }   
}