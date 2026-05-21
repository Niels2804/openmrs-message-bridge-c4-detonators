package org.openmrs.module.messagebridge.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.messagebridge.model.AppointmentWebhookPayload;
import org.openmrs.module.messagebridge.util.WebhookSender;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppointmentEventListener {
	
	private final Log log = LogFactory.getLog(getClass());
	
	private final WebhookSender webhookSender;
	
	public AppointmentEventListener(WebhookSender webhookSender) {
		this.webhookSender = webhookSender;
	}
	
	@EventListener
	public void handleEvent(Object event) {
		
		log.info("MessageBridge received event: " + event.getClass().getName());
		
		if (event.toString().toLowerCase().contains("appointment")) {
			
			AppointmentWebhookPayload payload = new AppointmentWebhookPayload("unknown", "APPOINTMENT_EVENT");
			
			webhookSender.send(payload);
			
			log.info("Webhook triggered");
		}
	}
}
