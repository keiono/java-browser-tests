package org.cytoscape.fx.internal.ui;

import java.awt.BorderLayout;
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

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;


public class JXBrowserPanel extends JPanel implements CytoPanelComponent {


	private String url;
	private JTextField urlText;
	private JButton loadButton;
	private Browser browser;
	
	public JXBrowserPanel() {
		
		this.browser = new Browser();
		
		// URL text area
		this.urlText = new JTextField();
		this.loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = urlText.getText();
				browser.loadURL(text);
			}
		});
		
        BrowserView view = new BrowserView(browser);

        this.setLayout(new BorderLayout());
        this.setSize(800, 800);
        
        this.setPreferredSize(new Dimension(800, 800));
        browser.setSize(800, 800);
        this.add(view, BorderLayout.CENTER);
        
        JPanel urlPanel = new JPanel();
        urlPanel.setLayout(new BorderLayout());
        urlPanel.add(urlText, BorderLayout.CENTER);
        urlPanel.add(loadButton, BorderLayout.EAST);
        
        this.add(urlPanel, BorderLayout.NORTH);

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
		return "JXBrowser Sample";
	}

	@Override
	public Icon getIcon() {
		return null;
	}
}