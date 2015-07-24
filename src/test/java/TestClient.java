import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lq.kexin_ca.entity.Location;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class TestClient {


    private static final String localURL = "http://localhost:8080/kexin";
    private static final String remoteURL = "http://10.60.150.128:8080/kexin";

    public static void main(String[] args) {
        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();
        try {
            login(httpclient, cookieStore);
            getLocationList(httpclient, cookieStore);
            LocationUpload(httpclient, cookieStore);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void getLocationList(CloseableHttpClient httpclient, BasicCookieStore cookieStore) throws IOException {
        HttpGet locationList = new HttpGet(localURL + "/location/list/1");
        CloseableHttpResponse locationListResponse = httpclient.execute(locationList);
        try {
            HttpEntity entity = locationListResponse.getEntity();

            ObjectMapper objectMapper = new ObjectMapper();

//            JavaType type = objectMapper.getTypeFactory().
//                    constructCollectionType(List.class, Location.class);

            List<Location> locations = objectMapper.readValue(EntityUtils.toString(entity), new TypeReference<List<Location>>() {
            });
            System.out.println(locations);
            showCookies(cookieStore);
        } finally {
            locationListResponse.close();
        }
    }

    private static void login(CloseableHttpClient httpclient, BasicCookieStore cookieStore) throws URISyntaxException, IOException {
        HttpUriRequest login = RequestBuilder.post()
                .setUri(new URI(localURL + "/user/login"))
                .addParameter("user_name", "lq")
                .addParameter("password", "123456")
                .addHeader("Accept", "text/plain")
                .build();
        CloseableHttpResponse response = httpclient.execute(login);
        try {
            HttpEntity entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity));
            showCookies(cookieStore);
        } finally {
            response.close();
        }
    }

    private static void LocationUpload(CloseableHttpClient httpclient, BasicCookieStore cookieStore) throws IOException {
        LocalDateTime time = LocalDateTime.of(2015, 7, 8, 14, 12);
        Location location = new Location(1, Timestamp.valueOf(time), 123.5, 45.5);
        ObjectMapper objectMapper = new ObjectMapper();

        HttpPost post = new HttpPost(localURL + "/location/upload");

        StringEntity postingString = new StringEntity(objectMapper.writeValueAsString(location));//convert your pojo to   json
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = httpclient.execute(post);
        try {
            HttpEntity entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity));

            showCookies(cookieStore);
        } finally {
            response.close();
        }
    }

    private static void showCookies(BasicCookieStore cookieStore) {
        List<Cookie> cookies = cookieStore.getCookies();
        if (cookies.isEmpty()) {
            System.out.println("None");
        } else {
            for (Cookie cookie : cookies) {
                System.out.println("- " + cookie.toString());
            }
        }
    }

    private static void register(CloseableHttpClient httpclient, BasicCookieStore cookieStore) throws URISyntaxException, IOException {
        HttpUriRequest login = RequestBuilder.post()
                .setUri(new URI(localURL + "/user/register"))
                .addParameter("name", "sxs")
                .addParameter("password", "123456")
                .addParameter("email", "123456")
                .addParameter("phoneNumber", "123456")
                .addHeader("Accept", "text/plain")
                .build();
        CloseableHttpResponse response = httpclient.execute(login);
        try {
            HttpEntity entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity));
            showCookies(cookieStore);
        } finally {
            response.close();
        }
    }


}
