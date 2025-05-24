package managers.beans;

import managers.dataModels.Result;
import managers.databaseManager.DatabaseQueryManager;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DatabaseControllerBean {

    @Inject
    private DatabaseQueryManager queriesDirector;

    public void saveResult(Result result) throws Exception {
        String query = "INSERT INTO results (x, y, r, status, currentTime, time_of_calculating) VALUES (?, ?, ?, ?, ?, ?)";
        queriesDirector.executeInsertQuery(
                query,
                result.getDot().getX(),
                result.getDot().getY(),
                result.getDot().getR(),
                result.getStatus(),
                result.getCurrentTime(),
                result.getTimeOfCalculating()
        );
    }

    public void executeQuery(String query, Object... parameters) throws Exception {
        queriesDirector.executeInsertQuery(query, parameters);

    }
}
