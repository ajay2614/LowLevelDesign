package LLDProjects.LogLevelProcessor;

public class InfoLogLevelProcessor extends LogLevelProcessor{

    InfoLogLevelProcessor(LogLevelProcessor nextLevelProcessor) {
        super(nextLevelProcessor);
    }

    public void log(int loglevel, String message) {
        if(loglevel == INFO) {
            System.out.println("INFO : " + message);
        }
        else {
            super.log(loglevel, message);
        }
    }
}
