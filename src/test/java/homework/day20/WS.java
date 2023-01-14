package homework.day20;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import homework.day20.testobjects.Response;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileNotFoundException;
import java.io.FileReader;

@RunWith(JUnit4.class)
public class WS {

    RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri("http://178.124.206.46:8001/app/ws/")
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    Gson gson = new Gson();
    //    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    

    @Test
    public void shouldReturnUserByPartialName() throws FileNotFoundException {
        method("src/test/resources/WS/partialNameSearchBody.json");
    }


    @Test
    public void shouldReturnUserByFullName() throws FileNotFoundException {
        method("src/test/resources/WS/fullNameSearchBody.json");
    }

    @Test
    public void shouldReturnUserByPartialRealname() throws FileNotFoundException {
        method("src/test/resources/WS/fullNameSearchBody.json");
    }

    @Test
    public void shouldReturnAllUsers() {

        Response response = getResponse(new Search("", false));

        Assert.assertTrue(response.getData().size() == 6);
        System.out.println(response);

    }

    private Response getResponse(Search searchBody) {
        return RestAssured
                .given()
                .spec(spec)
                .body(searchBody)
                .when()
                .post()
                .then()
                .extract().body().as(Response.class);
    }

    public void method(String file) throws FileNotFoundException {

        Response expectedResponse = gson.fromJson(new JsonReader(new FileReader("src/test/resources/WS/partialNameResponse.json")), Response.class);

//        {
//            try {
//                expectedResponse = gson.fromJson(new JsonReader(new FileReader("src/test/resources/WS/partialNameResponse.json")), Response.class);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//                System.out.println("No test file was found");
//            }
//        }

        Search searchBody = gson.fromJson(new JsonReader(new FileReader(file)), Search.class);
//        try {
//            searchBody = gson.fromJson(new JsonReader(new FileReader(file)), Search.class);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        Response actualResponse = getResponse(searchBody);
        Assert.assertEquals(expectedResponse, actualResponse);

    }
}