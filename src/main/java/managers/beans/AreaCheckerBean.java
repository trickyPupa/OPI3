package managers.beans;

import managers.AreaCheckerService;
import managers.dataModels.Dot;
import managers.IllegalParameterException;
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
    private SessionSaverForPointsBean saver;

    @Inject
    private DataBaseControllerBean db;

    private Result result;

    public void checkArea(Dot dot) {
        try {
            result = areaCheckService.processDot(dot);
            saver.addResult(result);
            db.saveResult(result);
        } catch (IllegalParameterException e) {
            errorControllerBean.send400Error("бредик не пишем, проверьте значения");
        }
    }

    public Result getResult() {
        return result;
    }
}

