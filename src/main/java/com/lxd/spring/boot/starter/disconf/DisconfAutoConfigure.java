package com.lxd.spring.boot.starter.disconf;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.baidu.disconf.client.DisconfMgrBean;
import com.baidu.disconf.client.DisconfMgrBeanSecond;
import com.baidu.disconf.client.addons.properties.ReloadablePropertiesFactoryBean;

/**
 * 对各个属性的赋值
 * @author li_xiaodong
 *
 */
@Configuration
@ConditionalOnProperty(prefix="lxd.disconf", name={"enabled"}, havingValue="true")
@ConditionalOnClass({DisconfMgrPreBean.class})
@EnableConfigurationProperties({DisconfProperties.class})
public class DisconfAutoConfigure
  implements ApplicationContextAware
{
  private ApplicationContext applicationContext;
//  private static final String PREFIX = "lxd.disconf.";

  @Bean
  @ConditionalOnMissingBean
  public DisconfMgrPreBean disconfMgrPreBean()
  {
    return new DisconfMgrPreBean();
  }

  @Bean(destroyMethod="destroy")
  public DisconfMgrBean disconfMgrBean()
  {
    createDisconfProperties();
    DisconfMgrBean disconfMgrBean = new DisconfMgrBean();
    disconfMgrBean.setScanPackage(PropertiesHelper.disConfProperties().getScanPackage());
    return disconfMgrBean;
  }

  @Bean(initMethod="init", destroyMethod="destroy")
  public DisconfMgrBeanSecond disconfMgrBeanSecond() {
    return new DisconfMgrBeanSecond();
  }

  @Bean
  public ReloadablePropertiesFactoryBean reloadablePropertiesFactoryBean() {
    ReloadablePropertiesFactoryBean reloadablePropertiesFactoryBean = new ReloadablePropertiesFactoryBean();
    reloadablePropertiesFactoryBean.setLocations(
      Arrays.asList(PropertiesHelper.disConfProperties().getLocations().split(",")));
    return reloadablePropertiesFactoryBean;
  }

  @Bean
  public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() throws IOException {
    PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
    propertyPlaceholderConfigurer.setIgnoreResourceNotFound(true);
    propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
    Properties[] properties = new Properties[1];
    properties[0] = reloadablePropertiesFactoryBean().getObject();
    propertyPlaceholderConfigurer.setPropertiesArray(properties);
    return propertyPlaceholderConfigurer;
  }

  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
  {
    this.applicationContext = applicationContext;
  }

  private void createDisconfProperties()
  {
    DisconfProperties properties = new DisconfProperties();
    Environment environment = this.applicationContext.getEnvironment();
    properties.setApp(environment.getProperty("lxd.disconf.app"));
    properties.setConfServerHost(environment.getProperty("lxd.disconf.conf-server-host"));
    properties.setConfServerUrlRetrySleepSeconds(Integer.parseInt(environment
      .getProperty("lxd.disconf.conf-server-url-retry-sleep-seconds", "2")));

    properties.setConfServerUrlRetryTimes(Integer.parseInt(environment
      .getProperty("lxd.disconf.conf-server-url-retry-times", "3")));

    properties.setDebug(Boolean.parseBoolean(environment.getProperty("lxd.disconf.debug", "false")));
    properties.setEnableDisconf(Boolean.parseBoolean(environment.getProperty("lxd.disconf.enable-disconf", "false")));
    properties.setEnableLocalDownloadDirInClassPath(Boolean.parseBoolean(environment
      .getProperty("lxd.disconf.enable-local-download-dir-in-class-path", "true")));

    properties.setEnv(environment.getProperty("lxd.disconf.env", "DEFAULT_ENV"));
    properties.setIgnoreDisconfList(environment.getProperty("lxd.disconf.ignore-disconf-list"));
    properties.setMainType(environment.getProperty("lxd.disconf.main-type"));
    properties.setUserDefineDownloadDir(environment.getProperty("lxd.disconf.user-define-download-dir", "./disconf/download"));
    properties.setVersion(environment.getProperty("lxd.disconf.version", "DEFAULT_VERSION"));
    properties.setScanPackage(environment.getProperty("lxd.disconf.scan-package", "com.lxd"));
    properties.setLocations(environment.getProperty("lxd.disconf.locations", "application.properties"));
    PropertiesHelper.set(properties);
  }
}