package managers;

import javax.inject.Named;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DebugTool {

    Logger logger = Logger.getLogger("debugtool");

    public void info(String info) {
        logger.log(Level.INFO, info);
    }

    public void warning(String warning) {
        logger.log(Level.WARNING, warning);
    }

    public void error(String error) {
        logger.log(Level.SEVERE, error);
    }

}
