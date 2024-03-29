package classwork.day21;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class HttpGetClass {

    public static void httpGet() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder("https://www.nbrb.by/api/exrates/currencies/1");
        HttpGet request = new HttpGet(builder.build());
        HttpResponse httpResponse = client.execute(request);
        System.out.println(EntityUtils.toString(httpResponse.getEntity()));

    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        httpGet();
    }
}
