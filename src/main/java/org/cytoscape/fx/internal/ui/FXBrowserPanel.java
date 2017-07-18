package org.cytoscape.fx.internal.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;

import com.sun.javafx.application.PlatformImpl;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class FXBrowserPanel extends JPanel implements CytoPanelComponent {

	private Stage stage;
	private WebView browser;
	private JFXPanel jfxPanel;
	private WebEngine webEngine;
	
	private String url;
	private JTextField urlText;
	private JButton loadButton;

	public FXBrowserPanel() {
		initComponents();
	}

	private void initComponents() {

		this.setPreferredSize(new Dimension(800, 700));

		jfxPanel = new JFXPanel();

		createScene();

		setLayout(new BorderLayout());

		loadButton = new JButton();
		loadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						String newUrl = urlText.getText();
						webEngine.load(newUrl);
					}
				});
			}
		});
		loadButton.setText("load");

		this.setSize(800, 800);
		this.setPreferredSize(new Dimension(800, 800));
		add(jfxPanel, BorderLayout.CENTER);

		JPanel urlPanel = new JPanel();
		// URL text area
		this.urlText = new JTextField();
		urlPanel.setLayout(new BorderLayout());
		urlPanel.add(urlText, BorderLayout.CENTER);
		urlPanel.add(loadButton, BorderLayout.EAST);

		this.add(urlPanel, BorderLayout.NORTH);

	}

	private void createScene() {

		PlatformImpl.startup(new Runnable() {

			@Override
			public void run() {

				stage = new Stage();

				stage.setTitle("FX Browser");
				stage.setResizable(true);

				final Group root = new Group();
				Scene scene = new Scene(root, 800, 700);
				stage.setScene(scene);

				// Set up the embedded browser:
				browser = new WebView();
				webEngine = browser.getEngine();
				webEngine.load("https://html5test.com/");

				ObservableList<Node> children = root.getChildren();
				children.add(browser);

				jfxPanel.setScene(scene);
			}
		});
	}

	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public CytoPanelName getCytoPanelName() {
		return CytoPanelName.WEST;
	}

	@Override
	public String getTitle() {
		return "JavaFX Browser Sample";
	}

	@Override
	public Icon getIcon() {
		return null;
	}

}
