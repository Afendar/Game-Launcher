package com.rawad.gamelauncher.files;

import java.io.File;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="properties")
public class GameProperties extends DataContainer {
	
//	private static final String KEY_NAME = "name";
//	private static final String KEY_VERSION = "version";
//	private static final String KEY_ICON = "icon";
	
	private String name;
	private String version;
	
	private File jar;
	private File icon;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * @return the jar
	 */
	public File getJar() {
		return jar;
	}
	
	/**
	 * @param jar the jar to set
	 */
	public void setJar(File jar) {
		this.jar = jar;
	}
	
	/**
	 * @return the icon
	 */
	public File getIcon() {
		return icon;
	}
	
	/**
	 * @param icon the icon to set
	 */
	public void setIcon(File icon) {
		this.icon = icon;
	}
	
}
