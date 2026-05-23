package org.openmrs.module.messagebridge;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.BaseModuleActivator;
import org.openmrs.api.context.Context;
import org.openmrs.api.AdministrationService;

public class MessageBridgeActivator extends BaseModuleActivator {
	
	private final Log log = LogFactory.getLog(getClass());
	
	@Override
	public void started() {
		AdministrationService admin = Context.getAdministrationService();
		
		String key = "messagebridge.webhook.url";
		
		String urlString = admin.getGlobalProperty(key);
		
		log.info("Using webhook URL from environment variable: " + urlString);
		
		if (urlString == null || urlString.isEmpty()) {
			urlString = System.getenv("MESSAGEBRIDGE_WEBHOOK_URL");
			log.info("Using webhook URL from environment variable: " + urlString);
		}
		
		// fallback to default
		if (urlString == null || urlString.isEmpty()) {
			urlString = "http://my-api:8080/webhook/appointment-created"; // I know it's hardcoded, but it's just a default value for testing purposes. In production, users should set this via global properties or environment variables.
			log.info("Using webhook URL from default value: " + urlString);
		}
		
		admin.setGlobalProperty(key, urlString);
		
		log.info("MessageBridge started with webhook URL: " + urlString);
	}
	
	@Override
	public void stopped() {
		log.info("MessageBridge module stopped");
	}
}
