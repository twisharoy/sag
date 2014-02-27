package com.wm.qa.testHarness;

import java.util.HashMap;

/**
 * 
 * 
 */
public class IntegrationTestMap {

    private HashMap<String, IntegrationTest> testMap;
    private static IntegrationTestMap _singleton;
    
    /**
     * 
     */
    private IntegrationTestMap() { 
        
        testMap = new HashMap<String, IntegrationTest>();
    }
    
    /**
     * 
     */
    public synchronized static IntegrationTestMap getInstance() {
        
        if (_singleton == null) {
            _singleton = new IntegrationTestMap();
        }
        return _singleton;
    }
    
    /**
     * 
     */
    public void addIntegrationTest(IntegrationTest test) {
        
        testMap.put(test.getName(), test);
    }
    
    /**
     * 
     */
    public IntegrationTest getIntegrationTest(String name) {
        
        return testMap.get(name);
    }
}