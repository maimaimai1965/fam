package ua.mai.fam.util;

import org.springframework.aop.framework.Advised;

public class SpringUtil {

    public SpringUtil() {}

    public static <T> T unproxyBean(T possiblyProxiedObject) {
        if (possiblyProxiedObject instanceof Advised) {
            try {
                return (T) ((Advised) possiblyProxiedObject).getTargetSource().getTarget();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return possiblyProxiedObject;
    }

}
