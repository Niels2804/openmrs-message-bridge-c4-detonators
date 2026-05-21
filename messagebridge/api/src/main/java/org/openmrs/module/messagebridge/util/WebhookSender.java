package org.openmrs.module.messagebridge.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.messagebridge.model.AppointmentWebhookPayload;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebhookSender {
	
	private static final Log log = LogFactory.getLog(WebhookSender.class);
	
	public static void send(AppointmentWebhookPayload payload) {
        try {
            String urlString = Context.getAdministrationService()
                .getGlobalProperty(
                    "messagebridge.webhook.url",
                    "http://localhost:8080/webhook/appointment-created"
                );

            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            connection.setConnectTimeout(3000);
            connection.setReadTimeout(5000);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(payload);

            try (OutputStream os = connection.getOutputStream()) {
                os.write(json.getBytes("utf-8"));
            }

            int responseCode = connection.getResponseCode();

            if (responseCode >= 200 && responseCode < 300) {
                log.info("Webhook sent successfully");
            } else {
                log.warn("Webhook failed: " + responseCode);
            }

        } catch (Exception e) {
            log.error("Failed to send webhook", e);
        }
    }
}
