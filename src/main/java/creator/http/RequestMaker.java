package creator.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

public class RequestMaker {

    private static final String HOST = "host";
    private static final String PATH = "path";
    private static final String SCHEME = "scheme";
    private static final String JSONRPC = "jsonrpc";
    private static final String METHOD = "method";
    private static final String API_KEY = "api-key";
    private static final String CHARACTERS = "characters";

    public static RandomOrgResponse requestPasswords(int number,
                                                     long length) throws URISyntaxException, IOException {

        ResourceBundle config = ResourceBundle.getBundle("application");
        String host = config.getString(HOST);
        String path = config.getString(PATH);
        String scheme = config.getString(SCHEME);
        String jsonrpc = config.getString(JSONRPC);
        String method = config.getString(METHOD);
        String apiKey = config.getString(API_KEY);
        String chars = config.getString(CHARACTERS);

        URI uri = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .build();

        Params params = new Params();
        params.setApiKey(apiKey);
        params.setCharacters(chars);
        params.setLength(length);
        params.setN(number);
        params.setReplacement(true);

        RandomOrgRequest requestBody = new RandomOrgRequest();
        requestBody.setId(123456);
        requestBody.setJsonrpc(jsonrpc);
        requestBody.setMethod(method);
        requestBody.setParams(params);

        return postRequest(uri, requestBody);
    }

    private static RandomOrgResponse postRequest(final URI uri, final RandomOrgRequest requestBody) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        CloseableHttpClient httpClient = HttpClients.createDefault();

        String requestJSON = mapper.writeValueAsString(requestBody);

        StringEntity entity = new StringEntity(requestJSON, ContentType.APPLICATION_JSON);

        HttpUriRequest postRequest = RequestBuilder.post(uri).setEntity(entity).build();

        CloseableHttpResponse clientResponse = httpClient.execute(postRequest);

        HttpEntity responseEntity = clientResponse.getEntity();

        InputStream content = responseEntity.getContent();

        return mapper.readValue(content, RandomOrgResponse.class);
    }

}
