package managers.MBeans.beans;

import managers.DebugTool;
import managers.MBeans.interfaces.HitRatioMBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@ApplicationScoped
public class HitRatio implements HitRatioMBean {
    @Inject
    private HitCounter hitCounter;

    private double procent = 0.0;

    DebugTool log = new DebugTool();

    @PostConstruct
    public void registerMBean() {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("com.papa.johns:type=HitRatio");
            mbs.registerMBean(this, name);
        } catch (Exception e) {
            log.error("Failed to register MBean: hitRatio");
        }
    }


    @Override
    public double getProcent() {
        if (hitCounter.getTotalHits() == 0) return 0.0;
        this.procent = ((double) (hitCounter.getTotalHits() - hitCounter.getMissedHits())
                / hitCounter.getTotalHits()) * 100;
        return procent;
    }

    public double poschitatProcent(){
        return getProcent();
    }

}