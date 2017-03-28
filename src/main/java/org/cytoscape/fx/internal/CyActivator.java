package org.cytoscape.fx.internal;

import static org.cytoscape.work.ServiceProperties.ID;
import static org.cytoscape.work.ServiceProperties.MENU_GRAVITY;
import static org.cytoscape.work.ServiceProperties.PREFERRED_MENU;
import static org.cytoscape.work.ServiceProperties.TITLE;

import java.util.Properties;

import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.fx.internal.task.ShowMessageTaskFactory;
import org.cytoscape.fx.internal.ui.FXBrowserPanel;
import org.cytoscape.fx.internal.ui.JXBrowserPanel;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.service.util.CyServiceRegistrar;
import org.cytoscape.task.NetworkTaskFactory;
import org.cytoscape.work.TaskFactory;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CyActivator extends AbstractCyActivator {

	private static final Logger logger = LoggerFactory.getLogger(CyActivator.class);

	public CyActivator() {
		super();
	}

	public void start(BundleContext bc) {

		final NetworkTaskFactory fitContent = getService(bc, NetworkTaskFactory.class, "(title=Fit Content)");
		final CySwingApplication cySwingApplicationServiceRef = getService(bc, CySwingApplication.class);
		final CyServiceRegistrar registrar = getService(bc, CyServiceRegistrar.class);

		final ShowMessageTaskFactory showMessageTaskFactory = new ShowMessageTaskFactory(registrar,
				cySwingApplicationServiceRef, fitContent);
		Properties showMessageTaskFactoryProps = new Properties();
		showMessageTaskFactoryProps.setProperty(ID, "showMessageTaskFactory");
		showMessageTaskFactoryProps.setProperty(PREFERRED_MENU, "Tools");
		showMessageTaskFactoryProps.setProperty(TITLE, "Show JXBrowser Component...");
		showMessageTaskFactoryProps.setProperty(MENU_GRAVITY, "1.0");
		registerService(bc, showMessageTaskFactory, TaskFactory.class, showMessageTaskFactoryProps);

		final JXBrowserPanel panel = new JXBrowserPanel();
		registerService(bc, panel, CytoPanelComponent.class, new Properties());
		
		final FXBrowserPanel panel2 = new FXBrowserPanel();
		registerService(bc, panel2, CytoPanelComponent.class, new Properties());
	}

	@Override
	public void shutDown() {
	}
}