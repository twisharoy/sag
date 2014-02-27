package com.wm.qa.testHarness;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 
 */
public class AsynchronousIntegrationTest extends IntegrationTest {
    
    private CountDownLatch countDownLatch;
    private Object waitLock = new Object();
    private long timeout;
    
    /**
     * 
     */
    public AsynchronousIntegrationTest(String name, String description, int count, long timeout, String regex) {
        
        super(name, description, count, regex);
        this.timeout = timeout;
        countDownLatch = new CountDownLatch(count);
    }
    
    /**
     * 
     */
    public void startTest() {

        super.startTest();
        Thread t = new Thread(new TestExecution());
        t.start();
    }
    
    /**
     * Wait for waitLock. When this is available the test is complete.
     */
    public IntegrationTestResults endTest() {

        synchronized(waitLock) {
            return super.endTest();
        }
    }
    
    /**
     * 
     */
    public void countDown() {
        
        countDownLatch.countDown();
    }

    /**
     * 
     */
    protected long getCompletedTests() {
        
        return (count - countDownLatch.getCount());
    }
    
    /**
     * 
     *
     */
    private class TestExecution implements Runnable {
        
        public void run() {
            
            synchronized(waitLock) {
                try {
                    countDownLatch.await(timeout, TimeUnit.MILLISECONDS); 
                }catch(InterruptedException ie) { 
                    results.setException(ie);
                }
            }
        }
    }
    
    /**
     * 
     */
    public static void main(String[] args) {
        
        AsynchronousIntegrationTest it = new AsynchronousIntegrationTest("MyTest", "This is my async test.", 6, 1000, "cb");
        
        it.startTest();
        try {
            Thread.sleep(1);
        }catch(InterruptedException ie) {}
        
        it.countDown();
        it.countDown();
        it.countDown();
        it.countDown();        
        it.countDown();
        it.countDown();
        IntegrationTestResults itr = it.endTest();
       // System.out.println(itr);
        System.out.println(itr.getAsData());
    }
}