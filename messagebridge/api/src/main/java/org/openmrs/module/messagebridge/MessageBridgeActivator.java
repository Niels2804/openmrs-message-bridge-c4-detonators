package org.openmrs.module.messagebridge;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.BaseModuleActivator;

public class MessageBridgeActivator extends BaseModuleActivator {
	
	protected Log log = LogFactory.getLog(getClass());
	
	@Override
	public void started() {
		log.info("Message Bridge module started");
	}
	
	@Override
	public void stopped() {
		log.info("Message Bridge module stopped");
	}
}
