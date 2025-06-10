package LLDProjects.LogLevelProcessor;

public class ErrorLogLevelProcessor extends LogLevelProcessor{

    ErrorLogLevelProcessor(LogLevelProcessor nextLevelProcessor) {
        super(nextLevelProcessor);
    }

    public void log(int loglevel, String message) {
        if(loglevel == ERROR) {
            System.out.println("ERROR : " + message);
        }
        else {
            super.log(loglevel, message);
        }
    }
}

