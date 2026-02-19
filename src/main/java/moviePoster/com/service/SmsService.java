package moviePoster.com.service;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
public class SmsService {


    @Value("${messaggio.api.url}")
    private String apiUrl; // адрес внешнего сервиса, куда отправляется запрос

    @Value("${messaggio.api.token}")
    private String apiToken; // ключ для доступа к сервису

    @Value("${messaggio.sender}")
    private String sender; // имя отправителя


    private final RestTemplate restTemplate = new RestTemplate();
    /* это встроенный Spring класс, чтобы отправлять HTTP-запросы,
    он позволяет общаться с внешним API (SMS-провайдером) */

    public boolean sendSms(String phone, String message){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiToken);

            Map<String, Object> body = new HashMap<>();
            body.put("to",phone);
            body.put("from",sender);
            body.put("message",message);

            HttpEntity<Map<String,Object>> request = new HttpEntity<>(body,headers);
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl,request,String.class);

            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            System.out.println("Ошибка при отправке SMS: " + e.getMessage());
            return false;
        }
    }
}
