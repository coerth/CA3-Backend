package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.FuelDto;
import entities.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class FuelResourceTest {
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    Fuel f1, f2;

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

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();


        f1 = new Fuel("Rugbrødsmotor");
        f2 = new Fuel("Diesel");

        try{
            em.getTransaction().begin();
            em.createNamedQuery("Trip.deleteAllRows").executeUpdate();
            em.createNamedQuery("Fuel.deleteAllRows").executeUpdate();
            em.createNamedQuery("Transportation.deleteAllRows").executeUpdate();
            em.createNamedQuery("Journey.deleteAllRows").executeUpdate();
            em.createNamedQuery("JourneyType.deleteAllRows").executeUpdate();
            em.createNamedQuery("Profile.deleteAllRows").executeUpdate();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.persist(f1);
            em.persist(f2);
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
    public void getFuelById(){
        given()
                .contentType(ContentType.JSON)
                .get("/fuel/{id}", f1.getId())
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", Matchers.equalTo(f1.getId()));
    }

    @Test
    public void getAllFuel() {
        List<FuelDto> fuelDtos;

        fuelDtos =  given()
                .contentType("application/json")
                .when()
                .get("/fuel")
                .then()
                .extract().body().jsonPath().getList("", FuelDto.class);

        FuelDto fuelDto = new FuelDto(f1);
        FuelDto fuelDto2 = new FuelDto(f2);
        assertThat(fuelDtos, containsInAnyOrder(fuelDto, fuelDto2));
    }


}
