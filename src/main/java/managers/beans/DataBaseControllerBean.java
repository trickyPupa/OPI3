package managers.beans;

import managers.DatabaseQueryManager;
import managers.dataModels.Result;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.SQLException;

@Named
public class DataBaseControllerBean {

    @Inject
    private DatabaseQueryManager queriesDirector;

    @Inject
    private ErrorControllerBean errorControllerBean;

    public void saveResult(Result result) {
        String query = "INSERT INTO results (dot_x, dot_y, dot_r, status, current_time, time_of_calculating) VALUES (?, ?, ?, ?, ?, ?)";
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
        } catch (SQLException e) {
            errorControllerBean.send500Error("Проблемы с запросом в базу данных!");
        }
    }
}
