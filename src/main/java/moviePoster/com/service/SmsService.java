package moviePoster.com.service;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {


    @Value("${nexmo.api.key}")
    private String apiKey;

    @Value("${nexmo.api.secret}")
    private String apiSecret;

    @Value("${nexmo.from.number}")
    private String fromNumber;

    private VonageClient client;

    @PostConstruct
    public void init() {
        client = VonageClient.builder()
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .build();
    }


    public boolean sendSms(String toNumber, String message) {
        TextMessage sms = new TextMessage(fromNumber, toNumber, message);

        try {
            SmsSubmissionResponse response = client.getSmsClient().submitMessage(sms);
            return response.getMessages().get(0).getStatus() == MessageStatus.OK;
        } catch (Exception e) {
            System.out.println("Ошибка при отправке SMS: " + e.getMessage());
            return false;
        }
    }
}
