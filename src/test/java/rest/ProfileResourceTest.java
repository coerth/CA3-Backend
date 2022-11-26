package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
<<<<<<< HEAD
=======
import dtos.ProfileDto;
import entities.Profile;
import entities.User;
import entities.Role;
>>>>>>> 551e1d3 (delete rest test passed)

import dtos.ProfileDto;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;
//Uncomment the line below, to temporarily disable this test
@Disabled

public class ProfileResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();

    User u1, u2;

    Profile p1;
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        u1 = new User("John", "123");
        u2 = new User("Bertha", "prop");
        p1 = new Profile(1,"a@a.dk", "name",  u1);

        try{
            em.getTransaction().begin();
            em.persist(u1);
            em.persist(u2);
            em.persist(p1);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/profile").then().statusCode(200);
    }
    @Test
    public void testLogRequest() {
        System.out.println("Testing logging request details");
        given().log().all()
                .when().get("/profile")
                .then().statusCode(200);
    }
    @Test
    public void testLogResponse() {
        System.out.println("Testing logging response details");
        given()
                .when().get("/profile")
                .then().log().body().statusCode(200);
    }

    @Test
    public void create() {
        ProfileDto profileDto = new ProfileDto( "morten@koksikoden.dk", "Morten",new ProfileDto.UserDto("Morten", "123"));


        String requestBody = GSON.toJson(profileDto);
        System.out.println(requestBody);

        given()
                .header("Content-type", ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post("/profile")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", notNullValue())
                .body("email", equalTo(profileDto.getEmail()))
                .body("name", equalTo(profileDto.getName()));
                //.body("userName", equalTo(profileDto.getUser().getUserName()));
    }
    /*@Test
    public void updateTest() {
        p1.setEmail("MyNewEmail@email.com");
        ProfileDto ProfileDto = new ProfileDto(p1);
        String requestBody = GSON.toJson(ProfileDto);

        given()
                .header("Content-type", ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/Profile/"+p1.getId())
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(a1.getId()))
                .body("email", equalTo("MyNewEmail@email.com"))
                .body("firstName", equalTo("Arne"));
    }*/

    @Test
    public void delete(){
        given()
                .contentType(ContentType.JSON)
                .delete("/profile/"+ u1.getUserName())
                .then()
                .statusCode(200)
                .extract().response().as(Boolean.class);
    }
}
