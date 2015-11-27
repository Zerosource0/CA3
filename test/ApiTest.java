
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.authentication.FormAuthConfig;
import static com.jayway.restassured.authentication.FormAuthConfig.formAuthConfig;
import com.jayway.restassured.authentication.OAuthSignature;
import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;
import javax.ws.rs.core.MediaType;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author williambech
 */
public class ApiTest {

    @BeforeClass
    public static void setUpBeforeClass() {
        baseURI = "http://localhost:8080/semesterSeedSP";
        defaultParser = Parser.JSON;
        basePath = "/api";
    }

    @Test
    public void getUsersTest_401() {
        final String uri = "demoadmin/users";
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/demoadmin/users").
                then().
                statusCode(401);
    }

    @Test
    public void logInTest_200() {
        String token = given().
                contentType("application/json").
                body("{\"username\":\"admin\",\"password\":\"test\"}").
                when().
                post("/login").
                then().
                statusCode(200).
                extract().
                path("token");
        
           given().
                log().all().
                headers("Authorization", "Bearer "+token).
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/demoadmin/users").
                then().
                statusCode(200);
    }

//    @Test
//    public void getUsersLoggedInTest_200() {
//        final String uri = "demoadmin/users";
//        given().
//                log().all().
//                headers("Authorization", "Bearer "+token).
//                contentType(MediaType.APPLICATION_JSON).
//                when().
//                get("/demoadmin/users").
//                then().
//                statusCode(200);
//    }
}
