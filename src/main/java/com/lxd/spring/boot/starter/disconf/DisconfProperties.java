package com.lxd.spring.boot.starter.disconf;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("lxd.disconf")
public class DisconfProperties {

	private boolean enabled = false;

	private String scanPackage = "com.lxd";
	private String locations;
	private String confServerHost;
	private String app;
	private String version = "DEFAULT_VERSION";
	private String mainType;
	private String env = "DEFAULT_ENV";

	private boolean enableDisconf = false;

	private boolean debug = false;
	private String ignoreDisconfList;
	private int confServerUrlRetryTimes = 3;

	private String userDefineDownloadDir = "./disconf/download";

	private int confServerUrlRetrySleepSeconds = 2;

	private boolean enableLocalDownloadDirInClassPath = true;

	public String getConfServerHost() {
		return this.confServerHost;
	}

	public void setConfServerHost(String confServerHost) {
		this.confServerHost = confServerHost;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMainType() {
		return this.mainType;
	}

	public void setMainType(String mainType) {
		this.mainType = mainType;
	}

	public String getEnv() {
		return this.env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public boolean isEnableDisconf() {
		return this.enableDisconf;
	}

	public void setEnableDisconf(boolean enableDisconf) {
		this.enableDisconf = enableDisconf;
	}

	public boolean isDebug() {
		return this.debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public int getConfServerUrlRetryTimes() {
		return this.confServerUrlRetryTimes;
	}

	public void setConfServerUrlRetryTimes(int confServerUrlRetryTimes) {
		this.confServerUrlRetryTimes = confServerUrlRetryTimes;
	}

	public String getUserDefineDownloadDir() {
		return this.userDefineDownloadDir;
	}

	public void setUserDefineDownloadDir(String userDefineDownloadDir) {
		this.userDefineDownloadDir = userDefineDownloadDir;
	}

	public int getConfServerUrlRetrySleepSeconds() {
		return this.confServerUrlRetrySleepSeconds;
	}

	public void setConfServerUrlRetrySleepSeconds(int confServerUrlRetrySleepSeconds) {
		this.confServerUrlRetrySleepSeconds = confServerUrlRetrySleepSeconds;
	}

	public boolean isEnableLocalDownloadDirInClassPath() {
		return this.enableLocalDownloadDirInClassPath;
	}

	public void setEnableLocalDownloadDirInClassPath(boolean enableLocalDownloadDirInClassPath) {
		this.enableLocalDownloadDirInClassPath = enableLocalDownloadDirInClassPath;
	}

	public String getApp() {
		return this.app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getIgnoreDisconfList() {
		return this.ignoreDisconfList;
	}

	public void setIgnoreDisconfList(String ignoreDisconfList) {
		this.ignoreDisconfList = ignoreDisconfList;
	}

	public String getScanPackage() {
		return this.scanPackage;
	}

	public void setScanPackage(String scanPackage) {
		this.scanPackage = scanPackage;
	}

	public String getLocations() {
		return this.locations;
	}

	public void setLocations(String locations) {
		this.locations = locations;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
