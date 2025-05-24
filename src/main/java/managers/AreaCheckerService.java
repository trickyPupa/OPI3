package managers;

import managers.MBeans.beans.HitCounter;
import managers.MBeans.beans.HitRatio;
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

    @Inject
    private HitCounter hitCounter;

    @Inject
    private HitRatio hitRatio;

    private final DebugTool log = new DebugTool();

    public Result processDot(Dot dot)  {
        long start = System.nanoTime();
        boolean status = areaChecker.checkSpot(dot.getX(), dot.getY(), dot.getR());
        hitCounter.incrementTotalHits();
        if (!status) {
            hitCounter.incrementMissedHits();
        }
        hitRatio.poschitatProcent();
        double timeOfCalculating = (double) (System.nanoTime() - start) / 1_000_000;
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        log.info("\nDot:{" +
                "\n\tx:"+dot.getX() +
                ",\n\ty:" + dot.getY() +
                ",\n\tr:" + dot.getR() + "\n},\n" +
                "status:" + status +",\n"+
                "current time:" + currentTime + ",\n"+
                "time:" + timeOfCalculating);

        return Result.createResult(dot, status, timeOfCalculating, currentTime);
    }
}

