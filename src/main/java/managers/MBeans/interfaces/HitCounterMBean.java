package managers.MBeans.interfaces;

import javax.management.MXBean;

@MXBean
public interface HitCounterMBean {
    int getTotalHits();
    int getMissedHits();
    void incrementTotalHits();
    void incrementMissedHits();
}