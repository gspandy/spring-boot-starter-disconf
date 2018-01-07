package com.lxd.spring.boot.starter.disconf;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.PriorityOrdered;

import com.baidu.disconf.client.config.DisClientConfig;

public class DisconfMgrPreBean implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private DisconfProperties properties;

	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
		if (this.properties == null) {
			this.properties = PropertiesHelper.disConfProperties();
		}
		this.logger.info("Start config DisClientConfig from properties");
		DisClientConfig config = DisClientConfig.getInstance();
		Class<DisClientConfig> disClientConfigClass = DisClientConfig.class;
		try {
			Field isLoaded = disClientConfigClass.getDeclaredField("isLoaded");
			isLoaded.setAccessible(true);
			isLoaded.setBoolean(config, true);
			isLoaded.setAccessible(false);

			Field conf_server_host = disClientConfigClass.getField("CONF_SERVER_HOST");
			conf_server_host.set(config, this.properties.getConfServerHost());

			Field app = disClientConfigClass.getField("APP");
			app.set(config, this.properties.getApp());

			Field version = disClientConfigClass.getField("VERSION");
			version.set(config, this.properties.getVersion());

			Field main_type = disClientConfigClass.getField("MAIN_TYPE");
			main_type.set(config, this.properties.getMainType());

			Field env = disClientConfigClass.getField("ENV");
			env.set(config, this.properties.getEnv());

			Field enable_disconf = disClientConfigClass.getField("ENABLE_DISCONF");
			enable_disconf.setBoolean(config, this.properties.isEnableDisconf());

			Field debug = disClientConfigClass.getField("DEBUG");
			debug.setBoolean(config, this.properties.isDebug());

			Field ignore_disconf_list = disClientConfigClass.getField("IGNORE_DISCONF_LIST");
			ignore_disconf_list.set(config, this.properties.getIgnoreDisconfList());

			Field conf_server_url_retry_times = disClientConfigClass.getField("CONF_SERVER_URL_RETRY_TIMES");
			conf_server_url_retry_times.setInt(config, this.properties.getConfServerUrlRetryTimes());

			Field userDefineDownloadDir = disClientConfigClass.getField("userDefineDownloadDir");
			userDefineDownloadDir.set(config, this.properties.getUserDefineDownloadDir());

			Field confServerUrlRetrySleepSeconds = disClientConfigClass.getField("confServerUrlRetrySleepSeconds");
			confServerUrlRetrySleepSeconds.setInt(config, this.properties.getConfServerUrlRetrySleepSeconds());

			Field enableLocalDownloadDirInClassPath = disClientConfigClass
					.getField("enableLocalDownloadDirInClassPath");
			enableLocalDownloadDirInClassPath.setBoolean(config, this.properties.isEnableLocalDownloadDirInClassPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory)
			throws BeansException {
	}

	public int getOrder() {
		return -2147483648;
	}
}