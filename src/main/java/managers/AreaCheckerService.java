package managers;


import managers.dataModels.Dot;
import managers.dataModels.Result;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Stateless
public class AreaCheckerService {
    @Inject
    private AreaChecker areaChecker;

    public Result processDot(Dot dot) throws IllegalParameterException {
        long start = System.nanoTime();
        boolean status = areaChecker.checkSpot(dot.getX(), dot.getY(), dot.getR());
        double timeOfCalculating = (double) (System.nanoTime() - start) / 1_000_000;
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return Result.createResult(dot, status, timeOfCalculating, currentTime);
    }
}

