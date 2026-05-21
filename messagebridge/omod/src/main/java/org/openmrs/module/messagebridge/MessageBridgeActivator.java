package org.openmrs.module.messagebridge;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.BaseModuleActivator;

public class MessageBridgeActivator extends BaseModuleActivator {
	
	private final Log log = LogFactory.getLog(getClass());
	
	@Override
	public void started() {
		System.out.println("MessageBridge module started");
	}
	
	@Override
	public void stopped() {
		System.out.println("MessageBridge module stopped");
	}
}
