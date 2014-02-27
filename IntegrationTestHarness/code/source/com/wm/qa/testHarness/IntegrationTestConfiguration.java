package com.wm.qa.testHarness;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class IntegrationTestConfiguration {

    public static String LOGGER_NAME = "WmIntegrationTestLogger";
    private boolean loggingEnabled = false;
    private boolean outputEnabled = false;
    
    private static IntegrationTestConfiguration _singleton;
    
    private IntegrationTestConfiguration() {
        
    }
    
    public static synchronized IntegrationTestConfiguration getInstance() {
        
        if (_singleton == null) {
            _singleton = new IntegrationTestConfiguration();
        }
        return _singleton;
    }

    public void enableLogging() throws IOException, SecurityException {
     
        FileHandler fileHandler = new FileHandler(".\\packages\\WmIntegrationTestHarness\\testResults%g.log", 0, 10);
        this.enableLogging(fileHandler);
    }
    
    public void enableLogging(FileHandler fileHandler) throws IOException, SecurityException {

        LogManager logManager = LogManager.getLogManager();
        logManager.reset();
        fileHandler.setFormatter(new BasicLoggingFormatter());      
        Logger logger = Logger.getLogger(LOGGER_NAME);
        logger.addHandler(fileHandler);
        logManager.addLogger(logger);
        loggingEnabled = true;
        
    }
    
    public void disableLogging() {
        
        loggingEnabled = false;
    }
    
    public boolean isLoggingEnabled() {
        
        return loggingEnabled;
    }
    
    public void setOutputEnabled(boolean outputEnabled) {
        
        this.outputEnabled = outputEnabled;
    }
    
    public boolean isOutputEnabled() {
        
        return outputEnabled;
    }
}