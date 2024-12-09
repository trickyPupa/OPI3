package managers;

import managers.dataModels.Result;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("pointsDAO")
@SessionScoped
public class PointsDAO implements Serializable {
    private final List<Result> results = new ArrayList<>();

    public void addResult(Result result) {
        results.add(result);
    }

    public List<Result> getResults() {
        return results;
    }
}
