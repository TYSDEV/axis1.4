package org.apache.axis2.management.manager;

import org.apache.axis2.engine.AxisFault;

import java.rmi.registry.LocateRegistry;
import org.apache.commons.modeler.Registry;

import javax.management.MBeanServer;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

public class JMXManagerM{

	private Registry registry;
	private static JMXManagerM jmxManager = null;

	JMXConnectorServer cs;

	public static JMXManagerM getJMXManager(){

		if(jmxManager != null){
			return jmxManager;
		}
		else{
			jmxManager = new JMXManagerM();
			return jmxManager;
		}

	}

	private JMXManagerM(){

		try{
			initModeler();
			publishRMI();
		}catch(Exception e){
		}
	}



	public void initModeler() throws Exception{

		Registry registry = Registry.getRegistry(null, null);

	}


	public void registerMBeans(Object mbean, String mbeanName) throws Exception{

		registry.registerComponent(mbean, mbeanName, "Axis2Manager");
	}



	public void publishRMI() throws Exception{

		// RMI registry
		java.rmi.registry.Registry reg=null;

		try {
			if( reg==null )
				reg=LocateRegistry.createRegistry(9995);
		 } catch(Exception e) {
				throw new AxisFault(e.getMessage());
		 }

		MBeanServer mbs = registry.getMBeanServer();
		JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9995/axis2");
		cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
		cs.start();
	}

}