package org.openmrs.module.messagebridge.model;

public class AppointmentWebhookPayload {
	
	private String appointmentUuid;
	
	private String event;
	
	public AppointmentWebhookPayload() {
	}
	
	public AppointmentWebhookPayload(String appointmentUuid, String event) {
		this.appointmentUuid = appointmentUuid;
		this.event = event;
	}
	
	public String getAppointmentUuid() {
		return appointmentUuid;
	}
	
	public void setAppointmentUuid(String appointmentUuid) {
		this.appointmentUuid = appointmentUuid;
	}
	
	public String getEvent() {
		return event;
	}
	
	public void setEvent(String event) {
		this.event = event;
	}
	
	@Override
	public String toString() {
		return "AppointmentWebhookPayload{" + "appointmentUuid='" + appointmentUuid + '\'' + ", event='" + event + '\''
		        + '}';
	}
}
