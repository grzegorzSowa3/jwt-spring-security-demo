package pl.recompiled.jwtspringsecuritydemo;

import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatasourceProxyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) {
        if (bean instanceof DataSource dataSourceBean) {
            return ProxyDataSourceBuilder
                    .create(dataSourceBean)
                    .name("DataSourceProxyLogger")
                    .listener(new QueryLoggingListener())
                    .build();
        }
        return bean;
    }

}