package com.rawad.gamelauncher.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
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
import com.rawad.ballsimulator.main.BallSimulator;
import com.rawad.gamehelpers.display.DisplayManager;
import com.rawad.gamehelpers.game.Game;
import com.rawad.gamehelpers.game.GameManager;
import com.rawad.gamehelpers.resources.ResourceManager;
import com.rawad.gamelauncher.gui.GameIcon;

public class GameLauncherStart {
	
	private static final GameManager gameManager = GameManager.instance();
	
	private static final Client client = new Client();
	
	private JFrame frmGameLauncher;
	private JMenuBar menuBar;
	private JMenu mnOptions;
	private JMenuItem mntmExit;
	
	private MenuItemActionListener mntmActionListener;
	private JPanel gameIconHolder;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		ResourceManager.init(args);
		
		final GameLauncherStart window = new GameLauncherStart();
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					
					DisplayManager.init();
					
					window.initialize();
					window.frmGameLauncher.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	/**
	 * Create the application.
	 */
	public GameLauncherStart() {

		gameManager.registerGame(new BallSimulator());
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
		frmGameLauncher.pack();
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

		for (Game game : games) {
			
			GameIcon gi = new GameIcon(game);
			
			gi.addMouseListener(mouseListener);
			gameIconHolder.add(gi);

		}

	}
	
	public void onClose() {
		
		gameManager.getCurrentGame().requestStop();
		
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
			
			initGameFrame(gameToLaunch);
			
			gameManager.launchGame(gameToLaunch, client);
			
		}
		
	}
	
	private void initGameFrame(Game game) {
		
	}
	
	private class WindowCloseListener extends WindowAdapter {
		
		@Override
		public void windowClosing(WindowEvent e) {
			onClose();
		}
		
	}
	
}
