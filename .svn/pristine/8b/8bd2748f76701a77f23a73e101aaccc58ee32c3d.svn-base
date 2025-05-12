package managers;

import managers.dataModels.Result;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Named("pointsRep")
@SessionScoped
public class PointsRepository implements Serializable {


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
