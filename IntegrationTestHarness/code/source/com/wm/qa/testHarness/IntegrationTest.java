package com.wm.qa.testHarness;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 
 */
public class IntegrationTest {

    private String name;
    private Pattern pattern = null;
    private int countDownInt;
    protected long count;
    protected long startTime;
    protected IntegrationTestResults results;

    /**
     * 
     */
    public IntegrationTest(String name, String description, int count, String regex) {
        
        this.name = name;
        this.count = count;

        results = new IntegrationTestResults(name, description);
        if (regex != null) {
            pattern = Pattern.compile(regex);
        }
        countDownInt = count;
    }
    
    /**
     * 
     */
    public String getName() {
        
        return name;
    }
    
    /**
     *
     */
    public void startTest() {
        
        startTime = System.currentTimeMillis();   
    }
    
    /**
     * 
     */
    public IntegrationTestResults endTest() {
        
        results.setTotalTime(System.currentTimeMillis() - startTime);
        results.setTestStats(count, getCompletedTests());
        return results;
    }
    
    /**
     * 
     */
    public void countDownAndValidate(String testString) {
        
        if (pattern != null) {
            Matcher m = pattern.matcher(testString);
            if (!m.matches()) {
                results.addValidationError("Validation error with message " + getCompletedTests() + ": \"" + testString + "\" does not match pattern \"" + pattern.pattern() + "\"");
            }
        }
        countDown();
    }
    
    /**
     * 
     */
    public void countDownAndReportError(String errorMsg) {
        
        results.addValidationError("Error with message " + getCompletedTests() + ": \"" + errorMsg);
        countDown();
    }
    
    /**
     * 
     */
    public void countDown() {
        
        countDownInt--;
    }
    
    /**
     * 
     */
    protected long getCompletedTests() {
        
        return (count - countDownInt);
    }
    
    /**
     * 
     */
    public static void main(String[] args) {
        
        IntegrationTest it = new IntegrationTest("MyTest", "This is my test.", 6, "cb");
        it.startTest();
        it.countDownAndValidate("a");
        it.countDownAndValidate("cb");
        it.countDownAndReportError("foo bar");
        it.countDownAndValidate("cb");
 
        it.countDownAndValidate("cb");
        IntegrationTestResults itr = it.endTest();
        System.out.println(itr);
    }
}