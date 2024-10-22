import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CatsFactsApp {

    public static void main(String[] args) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");

        try {
            String response = EntityUtils.toString(httpClient.execute(request).getEntity());

            ObjectMapper mapper = new ObjectMapper();
            CatFact[] catFacts = mapper.readValue(response, CatFact[].class);

            List<CatFact> filteredFacts = Arrays.stream(catFacts)
                    .filter(fact -> fact.getUpvotes() != null)
                    .toList();

            filteredFacts.forEach(fact -> System.out.println(fact.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}