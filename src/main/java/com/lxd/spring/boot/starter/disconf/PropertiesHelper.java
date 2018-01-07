package com.lxd.spring.boot.starter.disconf;

public class PropertiesHelper {

	private static DisconfProperties disconfProperties;

	static DisconfProperties disConfProperties() {
		return disconfProperties;
	}

	static void set(DisconfProperties properties) {
		disconfProperties = properties;
	}

}
