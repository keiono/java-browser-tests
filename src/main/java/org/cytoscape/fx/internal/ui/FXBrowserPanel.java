package org.cytoscape.fx.internal.ui;

import com.sun.javafx.application.PlatformImpl;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;

public class FXBrowserPanel extends JPanel implements CytoPanelComponent {

	private Stage stage;
	private WebView browser;
	private JFXPanel jfxPanel;
	private JButton swingButton;
	private WebEngine webEngine;

	public FXBrowserPanel() {
		initComponents();
	}

	private void initComponents() {

		jfxPanel = new JFXPanel();
		createScene();

		setLayout(new BorderLayout());
		add(jfxPanel, BorderLayout.CENTER);

		swingButton = new JButton();
		swingButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						webEngine.reload();
					}
				});
			}
		});
		swingButton.setText("Reload");

		add(swingButton, BorderLayout.SOUTH);
	}

	private void createScene() {
		PlatformImpl.startup(new Runnable() {
			@Override
			public void run() {

				stage = new Stage();

				stage.setTitle("Hello Java FX");
				stage.setResizable(true);

				Group root = new Group();
				Scene scene = new Scene(root, 80, 20);
				stage.setScene(scene);

				// Set up the embedded browser:
				browser = new WebView();
				webEngine = browser.getEngine();
				webEngine.load("http://deep-cell.ucsd.edu/");

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
