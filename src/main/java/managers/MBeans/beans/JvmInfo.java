package managers.MBeans.beans;

import managers.DebugTool;
import managers.MBeans.interfaces.JvmInfoMBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@ApplicationScoped
public class JvmInfo implements JvmInfoMBean {
    DebugTool log = new DebugTool();
    @PostConstruct
    public void registerMBean() {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("com.papa.johns:type=JvmInfo");
            mbs.registerMBean(this, name);
        } catch (Exception e) {
            log.error("Failed to register MBean: jvmInfo");
        }
    }


    @Override
    public String getJvmName() {
        return System.getProperty("java.vm.name");
    }

    @Override
    public String getJvmVersion() {
        return System.getProperty("java.vm.version");
    }

    @Override
    public String getJvmVendor() {
        return System.getProperty("java.vm.vendor");
    }

    @Override
    public String getJvmSpecVersion() {
        return System.getProperty("java.vm.specification.version");
    }
}