package managers.MBeans.interfaces;
import javax.management.MXBean;

@MXBean
public interface JvmInfoMBean {
    String getJvmName();
    String getJvmVersion();
    String getJvmVendor();
    String getJvmSpecVersion();
}
