package LLDProjects.LogLevelProcessor;


public class DebugLogLevelProcessor extends LogLevelProcessor{

    DebugLogLevelProcessor(LogLevelProcessor nextLevelProcessor) {
        super(nextLevelProcessor);
    }

    public void log(int loglevel, String message) {
        if(loglevel == DEBUG) {
            System.out.println("DEBUG : " + message);
        }
        else {
            super.log(loglevel, message);
        }
    }
}
