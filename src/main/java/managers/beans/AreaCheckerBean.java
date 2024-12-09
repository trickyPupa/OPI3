package managers.beans;

import managers.AreaCheckerService;
import managers.IllegalParameterException;
import managers.PointsDAO;
import managers.dataModels.Dot;
import managers.dataModels.Result;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named("areaCheckerBean")
@RequestScoped
public class AreaCheckerBean {

    @Inject
    private AreaCheckerService areaCheckService;
    @Inject
    private ErrorControllerBean errorControllerBean;
    @Inject
    private PointsDAO saver;
    @Inject
    private DatabaseControllerBean db;

    private Result result;

    public void checkArea(Dot dot) {
        try {
            result = areaCheckService.processDot(dot);
            saver.addResult(result);
            db.saveResult(result);
        }catch (IllegalParameterException e) {
            errorControllerBean.send400Error(e.getMessage());
        }
    }

    public Result getResult() {
        return result;
    }
}

