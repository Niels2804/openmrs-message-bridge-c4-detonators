package org.openmrs.module.messagebridge;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.BaseModuleActivator;
import org.openmrs.module.ModuleActivator;
import org.openmrs.module.ModuleException;

public class DefaultActivator extends BaseModuleActivator implements ModuleActivator {
	
	private final Log log = LogFactory.getLog(getClass());
	
	@Override
	public void willRefreshContext() throws ModuleException {
		log.info("messagebridge: willRefreshContext");
	}
	
	@Override
	public void contextRefreshed() {
		log.info("messagebridge: contextRefreshed");
	}
	
	@Override
	public void started() {
		System.out.println(">>> messagebridge STARTED (SYSTEM.OUT)");
		log.info("messagebridge STARTED (LOG)");
	}
	
	@Override
	public void willStop() throws ModuleException {
		log.info("messagebridge: willStop");
	}
	
	@Override
	public void stopped() {
		System.out.println(">>> messagebridge STOPPED (SYSTEM.OUT)");
		log.info("messagebridge STOPPED (LOG)");
	}
}
