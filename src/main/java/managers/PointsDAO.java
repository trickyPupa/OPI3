package managers;

import managers.dataModels.Result;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Named("pointsDAO")
@SessionScoped
public class PointsDAO implements Serializable {


    private final List<Result> results = new CopyOnWriteArrayList<>();
    private final DebugTool logger = new DebugTool();

    public void addResult(Result result) {
        results.add(result);
        logger.info("Added result");
    }

    public List<Result> getResults() {
        return this.results;
    }
}
