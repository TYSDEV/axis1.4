package org.apache.axis2.management.manager;

import org.apache.axis2.engine.AxisFault;

import java.rmi.registry.LocateRegistry;

import javax.management.MBeanServer;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;


public class JMXManagerR{

	private static JMXManagerR jmxManager = null;

	private Class registryClass = null;
	private Object registry = null;
	private Method registerComponent = null;

	public static JMXManagerR getJMXManagerR() throws AxisFault{

		if(jmxManager != null){
			return jmxManager;
		}
		else{
			jmxManager = new JMXManagerR();
			return jmxManager;
		}

	}


	private JMXManagerR() throws AxisFault{

		try{
			initModeler();
			publishRMI();
		}catch(Exception e){
			throw AxisFault.makeFault(e);
		}

	}


	public boolean initModeler() throws Exception{

		try {
			registryClass = Class.forName("org.apache.commons.modeler.Registry");
		} catch (ClassNotFoundException e) {
			registry = null;
			return false;
		}

		try {
			Class[] getRegistryArgs = new Class[]{Object.class, Object.class,};
			Method getRegistry = registryClass.getMethod("getRegistry", getRegistryArgs);
			Object[] getRegistryOptions = new Object[]{null, null};
			registry = getRegistry.invoke(null, getRegistryOptions);

			Class[] registerComponentArgs = new Class[]{Object.class, String.class,	String.class};
			registerComponent = registryClass.getMethod("registerComponent", registerComponentArgs);

		} catch (IllegalAccessException e) {
			throw e;
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (InvocationTargetException e) {
			throw e;
		} catch (NoSuchMethodException e) {
			throw e;
		}

		return true;
	}


	 public boolean registerMBean(Object mbean, String mbeanName) throws Exception{

		 	String context = "Axis2";

			if (registry != null) {
				Object args[] = new Object[]{mbean, mbeanName, context};
				try {
					registerComponent.invoke(registry, args);
				} catch (IllegalAccessException e) {
					return false;
				} catch (IllegalArgumentException e) {
					return false;
				} catch (InvocationTargetException e) {
					return false;
				}
				return true;
			} else {
				return false;
			}
        }


        public void publishRMI() throws Exception{

			java.rmi.registry.Registry reg=null;	// RMI registry
			JMXConnectorServer cs = null;

			// create RMI registry on port 9995
			try {
				if( reg==null )
					reg=LocateRegistry.createRegistry(9995);
			 } catch(Exception e) {
					throw new AxisFault(e.getMessage());
			 }

			// publish MBeanServer in RMI registry
			Method getMBeanServer = registryClass.getMethod("getMBeanServer", null);
			MBeanServer mbs = (MBeanServer)getMBeanServer.invoke(registry, null);

			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9995/axis2");
			cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
			cs.start();

		}


}