package org.openmrs.module.messagebridge.api.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AppointmentServiceAdvice {
	
	private final Log log = LogFactory.getLog(getClass());
	
	public void afterCreate(Object returnValue) {
		log.error("🔥 APPOINTMENT CREATE INTERCEPTED");
		
		if (returnValue != null) {
			log.error("Returned: " + returnValue.getClass().getName());
		}
	}
}
