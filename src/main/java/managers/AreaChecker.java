package managers;

import managers.IllegalParameterException;
import managers.beans.ErrorControllerBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("areaChecker")
@ApplicationScoped
public class AreaChecker {

    DebugTool log = new DebugTool();

    @Inject
    private ErrorControllerBean err;

    public boolean checkSpot(double x, double y, double r) throws IllegalParameterException {
        return checkAxis(x, y, r);
    }

    private boolean checkAxis(double x, double y, double r) throws IllegalParameterException {
        if (!(x >= -4 && x <= 4 && y >= -5 && y <= 5 && r >= 2 && r <= 5)) {
            log.warning("Wrong parameters");
            throw new IllegalParameterException("Wrong parameters");
        } else {
            if (x * r >= 0 && y * r >= 0) {
                return checkRectangle(x, y, r);
            } else if (x * r <= 0 && y * r >= 0) {
                return false;
            } else if (x * r <= 0 && y * r <= 0) {
                return checkTriangle(x, y, r);
            } else if (x * r >= 0 && y * r <= 0) {
                return checkCircle(x, y, r);
            } else return x == 0 && y == 0;
        }
    }

    private boolean checkTriangle(double x, double y, double r) {
        return (y>= -x+-0.5*r);
    }

    private boolean checkRectangle(double x, double y, double r) {
        return (x <= r && y <= r);
    }

    private boolean checkCircle(double x, double y, double r) {
        return (x * x + y * y) <= r * r;
    }
}
