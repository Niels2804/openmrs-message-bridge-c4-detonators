package org.openmrs.module.messagebridge.api.impl;

import org.openmrs.module.messagebridge.model.AppointmentWebhookPayload;
import org.openmrs.module.messagebridge.util.WebhookSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageBridgeServiceImpl {
	
	private final WebhookSender webhookSender;
	
	@Autowired
	public MessageBridgeServiceImpl(WebhookSender webhookSender) {
		this.webhookSender = webhookSender;
	}
	
	public void sendAppointmentCreated(String uuid) {
		
		AppointmentWebhookPayload payload = new AppointmentWebhookPayload(uuid, "APPOINTMENT_CREATED");
		
		webhookSender.send(payload);
	}
}
