package managers.beans;

import managers.dataModels.Dot;
import managers.dataModels.Result;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("saver")
@SessionScoped
public class SessionSaverForPointsBean implements Serializable {
    private final List<Result> results = new ArrayList<>();

    public void addResult(Result result) {
        results.add(result);
    }

    public List<Result> getResults() {
        return results;
    }
}
