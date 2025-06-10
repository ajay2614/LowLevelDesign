package LLDProjects.LogLevelProcessor;

public class LogLevelProcessorMain {

    public static void main(String[] args) {

        LogLevelProcessor logLevelProcessor = new LogLevelProcessor(
                new InfoLogLevelProcessor(
                        new ErrorLogLevelProcessor(
                                new DebugLogLevelProcessor(null)
                        )
                )
        );

        logLevelProcessor.log(LogLevelProcessor.DEBUG, "Debugging");
        logLevelProcessor.log(LogLevelProcessor.ERROR, "Error");
        logLevelProcessor.log(LogLevelProcessor.INFO, "Info");
    }

}
