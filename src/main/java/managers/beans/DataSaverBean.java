package managers.beans;

import managers.*;
import managers.dataModels.Dot;
import managers.dataModels.Result;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named("areaCheckerBean")
@RequestScoped
public class DataSaverBean {

    @Inject
    private AreaCheckerService areaCheckService;
    @Inject
    private PointsDAO saver;
    @Inject
    private DatabaseControllerBean db;

    private Result result;

    DebugTool log = new DebugTool();

    public void saveData(Dot dot)  {
        try {
        result = areaCheckService.processDot(dot);
        saver.addResult(result);
        db.saveResult(result);
        log.info("Data saved!");
        } catch (IllegalParameterException e) {
            ErrorController.send400Error("Something went wrong");
        }
    }

    public Result getResult() {
        return result;
    }
}

