package com.rawad.gamelauncher.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.rawad.ballsimulator.client.Client;
import com.rawad.ballsimulator.game.BallSimulator;
import com.rawad.gamehelpers.game.Game;
import com.rawad.gamehelpers.game.GameManager;
import com.rawad.gamehelpers.resources.ResourceManager;
import com.rawad.gamehelpers.utils.Util;
import com.rawad.gamelauncher.gui.GameIcon;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameLauncherStart extends Application {
	
	private static final GameManager gameManager = GameManager.instance();
	
	private static final Client client = new Client();
	
	private JFrame frmGameLauncher;
	private JMenuBar menuBar;
	private JMenu mnOptions;
	private JMenuItem mntmExit;
	
	private MenuItemActionListener mntmActionListener;
	private JPanel gameIconHolder;
	
	public static void main(String[] args) {
		
		ResourceManager.init(Util.parseCommandLineArguments(args));
		
		final GameLauncherStart window = new GameLauncherStart();
		
//		Util.invokeAndWait(new Runnable() {// So that frame is initialized for later use.
//			public void run() {
				
				try {
					
//					DisplayManager.init("");
					
					window.initialize();
					
//					ResourceManager.loadAllTextures();
					
					window.frmGameLauncher.pack();
					window.frmGameLauncher.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
//			}
//		});
		
	}
	
	public GameLauncherStart() {

		gameManager.registerGame(new BallSimulator());
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		
	}
	
	private void initialize() {
		
		frmGameLauncher = new JFrame();
		frmGameLauncher.addWindowListener(new WindowCloseListener());
		frmGameLauncher.getContentPane().setPreferredSize(
				new Dimension(500, 400));
		frmGameLauncher.getContentPane().setLayout(new BorderLayout(0, 0));
		
		gameIconHolder = new JPanel();
		frmGameLauncher.getContentPane().add(gameIconHolder,
				BorderLayout.CENTER);
		frmGameLauncher.setTitle("Game Launcher");
		frmGameLauncher.setLocationRelativeTo(null);
		frmGameLauncher.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frmGameLauncher.setJMenuBar(menuBar);
		
		mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		mntmActionListener = new MenuItemActionListener();
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(mntmActionListener);
		mnOptions.add(mntmExit);
		
		addGameIcons(gameManager.getGames());
		
	}

	private void addGameIcons(Game[] games) {

		GameLaunchListener mouseListener = new GameLaunchListener();
		
		for(Game game: games) {
			
			game.registerTextures();
			
			GameIcon gi = new GameIcon(game);
			
			gi.addMouseListener(mouseListener);
			gameIconHolder.add(gi);

		}
		
		frmGameLauncher.revalidate();
		
	}
	
	public void onClose() {
		
//		gameManager.getCurrentGame().requestStop();// Really think game should stay open.
		
		frmGameLauncher.dispose();
		
//		System.exit(0);

	}

	private class MenuItemActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();

			if (item.equals(mntmExit)) {
				onClose();
			}

		}

	}

	private class GameLaunchListener extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			Game gameToLaunch = ((GameIcon) e.getSource()).getGame();
			
			frmGameLauncher.setState(Frame.ICONIFIED);
			
//			DisplayManager.setDisplayTitle(gameToLaunch.toString());
			
			gameManager.launchGame(gameToLaunch, client);
			
		}
		
	}
	
	private class WindowCloseListener extends WindowAdapter {
		
		@Override
		public void windowClosing(WindowEvent e) {
			onClose();
		}
		
	}
	
}
