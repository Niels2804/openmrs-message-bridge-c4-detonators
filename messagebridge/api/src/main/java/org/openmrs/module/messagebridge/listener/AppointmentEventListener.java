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
		
		if (event.getClass().getSimpleName().equals("AppointmentBookingEvent")) {
			
			try {
				// roept getAppointment() aan via reflection
				Object appointment = event.getClass().getMethod("getAppointment").invoke(event);
				
				// roept getUuid() aan op appointment
				String uuid = (String) appointment.getClass().getMethod("getUuid").invoke(appointment);
				
				AppointmentWebhookPayload payload = new AppointmentWebhookPayload(uuid, "APPOINTMENT_EVENT");
				
				webhookSender.send(payload);
				
				log.warn("UUID via reflection: " + uuid);
				
			}
			catch (Exception e) {
				log.error("Failed to extract appointment UUID", e);
			}
		}
	}
}
