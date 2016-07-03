package com.rawad.gamelauncher.files;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamSource;

public final class FileLoader {
	
	private static final String ADD_STRING_PROPERTY = "com.sun.xml.internal.bind.xmlHeaders";
	
	private static final File FILE_GAME_LAUNCHER_CONFIGS = new File("res/config.xml");
	private static final String FILE_GAME_PROPERTIES = "properties.xml";
	
	private FileLoader() {}
	
	public static final GameLauncherConfigurations loadGameLauncherConfigs() {
		
		GameLauncherConfigurations gameLauncherConfigs = new GameLauncherConfigurations();
		
		try {
			
			JAXBContext context = JAXBContext.newInstance(GameLauncherConfigurations.class);
			
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			gameLauncherConfigs = unmarshaller.unmarshal(new StreamSource(FILE_GAME_LAUNCHER_CONFIGS), 
					GameLauncherConfigurations.class).getValue();
			
		} catch(JAXBException ex) {
			ex.printStackTrace();
		}
		
		return gameLauncherConfigs;
		
	}
	
	public static final GameProperties loadGameProperties(File gameFile) throws Exception {
		
		GameProperties gameProperties = new GameProperties();
		
		JAXBContext context = JAXBContext.newInstance(GameProperties.class);
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		gameProperties = unmarshaller.unmarshal(new StreamSource(new File(gameFile.getAbsolutePath() 
				+ File.separatorChar + FILE_GAME_PROPERTIES)), GameProperties.class).getValue();
		
		return gameProperties;
		
	}
	
	private static final void saveFile(DataContainer data, File fileToSaveTo) {
		
		try {
			
			JAXBContext context = JAXBContext.newInstance(data.getClass());
			
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(ADD_STRING_PROPERTY, System.lineSeparator() + "<!DOCTYPE " + data
					.getClass().getAnnotation(XmlRootElement.class).name() + ">");
			
			marshaller.marshal(data, fileToSaveTo);
			
		} catch(JAXBException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public static final void saveGameLauncherConfigs(GameLauncherConfigurations gameLauncherConfigs) {
			
		saveFile(gameLauncherConfigs, FILE_GAME_LAUNCHER_CONFIGS);
		
	}
	
	public static final void saveGameProperties(GameProperties gameProperties, File gamePropertiesFile) {
		
		saveFile(gameProperties, new File(gamePropertiesFile.getPath() + File.separatorChar + FILE_GAME_PROPERTIES));
		
	}
	
}
