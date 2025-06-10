package LLDProjects.LogLevelProcessor;

public class LogLevelProcessor {

    public static int INFO = 1;
    public static int ERROR = 2;
    public static int DEBUG = 3;


    LogLevelProcessor nextLogLevelProcessor;

    LogLevelProcessor(LogLevelProcessor nextLevelProcessor) {
        this.nextLogLevelProcessor = nextLevelProcessor;
    }

    public void log(int loglevel, String message) {
        if(this.nextLogLevelProcessor != null) {
            nextLogLevelProcessor.log(loglevel, message);
        }
    }
}
