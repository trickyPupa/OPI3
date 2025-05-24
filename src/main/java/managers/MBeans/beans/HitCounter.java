package managers.MBeans.beans;

import managers.DebugTool;
import managers.MBeans.interfaces.HitCounterMBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@ApplicationScoped
public class HitCounter implements HitCounterMBean {

    DebugTool log = new DebugTool();
    private int totalHits = 0;
    private int missedHits = 0;

    @PostConstruct
    public void registerMBean() {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("com.papa.johns:type=HitCounter");
            mbs.registerMBean(this, name);
        } catch (Exception e) {
            log.error("Failed to register MBean: hitCounter");
        }
    }

    @Override
    public int getTotalHits() {
        return totalHits;
    }

    @Override
    public int getMissedHits() {
        return missedHits;
    }

    @Override
    public void incrementTotalHits() {
        totalHits++;
    }

    @Override
    public void incrementMissedHits() {
        missedHits++;
    }
}