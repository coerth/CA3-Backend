package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ProfileDto;
import entities.User;
import entities.Role;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
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
        //EntityManager em = emf.createEntityManager();
        /*c1 = new CityInfo("2630", "Værebro");
        c2 = new CityInfo("8880", "Aalborg");
        a1 = new Address( "Bælgevej 16", "Til højre",c1);
        a2 = new Address("Paradisæblevej 111", "Her", c2);
        p1 = new Profile("Arnemail@email.com", "Arne", "Bjarne", a1);
        p2 = new Profile("Johns@email.com", "John", "Larsen", a2);
        try {
            em.getTransaction().begin();
//            em.createNamedQuery("Profile.deleteAllRows").executeUpdate();
//            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
//            em.createNamedQuery("CityInfo.deleteAllRows").executeUpdate();
            em.persist(c1);
            em.persist(c2);
            em.persist(a1);
            em.persist(a2);
            em.persist(p1);
            em.persist(p2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }*/

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
        ProfileDto profileDto = new ProfileDto("morten@koksikoden.dk", "morten", "Morten","spyflue");

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
                //.body("id", notNullValue())
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
}
