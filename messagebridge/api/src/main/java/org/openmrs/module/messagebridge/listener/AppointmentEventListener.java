package org.openmrs.module.messagebridge.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.User;
import org.openmrs.api.context.Context;
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
		
		if (!event.getClass().getSimpleName().equals("AppointmentBookingEvent")) {
			return;
		}
		
		try {
			User user = Context.getAuthenticatedUser();
			if (user == null || "daemon".equals(user.getSystemId())) {
				log.warn("MessageBridge skipping event — fired by daemon or no authenticated user.");
				return;
			}
		}
		catch (Exception e) {
			log.info("MessageBridge skipping event — context not available: " + e.getMessage());
			return;
		}
		
		log.info("MessageBridge received event: " + event.getClass().getName());
		
		try {
			Object appointment = event.getClass().getMethod("getAppointment").invoke(event);
			String uuid = (String) appointment.getClass().getMethod("getUuid").invoke(appointment);
			
			AppointmentWebhookPayload payload = new AppointmentWebhookPayload(uuid, "APPOINTMENT_EVENT");
			webhookSender.send(payload);
			
			log.info("MessageBridge sent webhook for appointment UUID: " + uuid);
		}
		catch (Exception e) {
			log.error("MessageBridge failed to extract appointment UUID", e);
		}
	}
}
