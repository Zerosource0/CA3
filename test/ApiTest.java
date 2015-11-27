

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.authentication.FormAuthConfig.formAuthConfig;
import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
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
    SessionFilter sf = new SessionFilter();
    
    @BeforeClass
    public static void setUpBeforeClass(){
        baseURI = "http://localhost:8080/semesterSeedSP";
        defaultParser = Parser.JSON;
        basePath = "/api";
    }
    
    @Test
    public void getUsersTest_401(){
        final String uri ="demoadmin/users";
               given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/demoadmin/users").
                then().
                    statusCode(401)
                ;
    }
    
    
    @Test
    public void logInTest_200(){
        given().
                auth().form("user", "test").filter(sf).
        when().
                get("/index").
        then().
                statusCode(200);
        
    }
      @Test
    public void getUsersLoggedInTest_200(){
        final String uri ="demoadmin/users";
               given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/demoadmin/users").
                then().
                    statusCode(200)
                ;
    }
    
//    @Test
//    public void loginTest(){
//        final String uri ="http://localhost:8080/semesterSeedSP/api/demoadmin/users";
//        final requestSepcification = RestAssured.get(uri).
//    }
    
}
