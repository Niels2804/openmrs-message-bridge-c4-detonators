package org.openmrs.module.messagebridge.api.impl;

import org.openmrs.module.messagebridge.model.AppointmentWebhookPayload;
import org.openmrs.module.messagebridge.util.WebhookSender;

public class MessageBridgeServiceImpl {
	
	public void sendAppointmentCreated(String uuid) {
		
		AppointmentWebhookPayload payload = new AppointmentWebhookPayload(uuid, "APPOINTMENT_CREATED");
		
		WebhookSender.send(payload);
	}
}
