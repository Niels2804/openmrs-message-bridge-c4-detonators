package org.openmrs.module.messagebridge.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openmrs.module.messagebridge.model.AppointmentWebhookPayload;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebhookSender {
	
	private static final String WEBHOOK_URL = "http://localhost:8080/webhook/appointment-created";
	
	public static void send(AppointmentWebhookPayload payload) {
        try {
            URL url = new URL(WEBHOOK_URL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(payload);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Webhook response code: " + responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
