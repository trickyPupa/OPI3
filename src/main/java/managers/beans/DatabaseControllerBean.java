package managers.beans;

import managers.dataModels.Result;
import managers.databaseManager.DatabaseQueryManager;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.SQLException;

@Named
public class DatabaseControllerBean {

    @Inject
    private DatabaseQueryManager queriesDirector;
    @Inject
    private ErrorControllerBean errorControllerBean;

    public void saveResult(Result result) {
        String query = "INSERT INTO results (x, y, r, status, currentTime, time_of_calculating) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            queriesDirector.executeInsertQuery(
                    query,
                    result.getDot().getX(),
                    result.getDot().getY(),
                    result.getDot().getR(),
                    result.getStatus(),
                    result.getCurrentTime(),
                    result.getTimeOfCalculating()
            );
        } catch (Exception e) {
            errorControllerBean.send500Error("Проблемы с запросом в базу данных!");
        }
    }

    public void executeQuery(String query, Object... parameters) {
        try {
            queriesDirector.executeInsertQuery(query, parameters);
        } catch (Exception e) {
            errorControllerBean.send500Error("Ошибка выполнения запроса.");
        }
    }
}
