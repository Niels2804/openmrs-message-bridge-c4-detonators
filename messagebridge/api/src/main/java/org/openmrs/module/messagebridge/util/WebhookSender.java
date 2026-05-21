package org.openmrs.module.messagebridge.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.messagebridge.model.AppointmentWebhookPayload;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class WebhookSender {
	
	private final Log log = LogFactory.getLog(getClass());
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	public void send(AppointmentWebhookPayload payload) {
        try {
            String urlString = Context.getAdministrationService()
                    .getGlobalProperty(
                            "messagebridge.webhook.url",
                            "http://localhost:8080/webhook/appointment"
                    );

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(5000);

            String json = mapper.writeValueAsString(payload);

            try (OutputStream os = connection.getOutputStream()) {
                os.write(json.getBytes("utf-8"));
            }

            int responseCode = connection.getResponseCode();

            if (responseCode >= 200 && responseCode < 300) {
                log.info("Webhook sent successfully: " + responseCode);
            } else {
                log.warn("Webhook failed: " + responseCode);
            }

        } catch (Exception e) {
            log.error("Failed to send webhook", e);
        }
    }
}
