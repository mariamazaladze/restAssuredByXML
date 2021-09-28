import java.util.Arrays;
import java.util.List;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.mapper.ObjectMapperType;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;

public class TestXML {
    @BeforeClass
    public static void setup() {
        RestAssured.given()
                .contentType("text/xml")
                .accept(ContentType.XML);
        baseURI = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName";
    }


    @Test
    void countNames() {
        int countNames = RestAssured
                .get("/ListOfContinentsByName")
                .then().extract()
                .path("ArrayOftContinent.tContinent.sName.size()");

        Assert.assertEquals(6, countNames);
        System.out.println("countName : "+ countNames );
    }

    @Test
    void getAllNamesValue() {
        NodeChildrenImpl namesList =RestAssured
                .get("/ListOfContinentsByName")
                .then().extract()
                .path("ArrayOftContinent.tContinent.sName");

        List<String> constantList= Arrays.asList("Africa","Antarctica","Asia","Europe","Ocenania","The Americas");
        Assert.assertEquals(constantList,namesList);
        System.out.println(namesList.getNodeList());
    }

    @Test
    public void getName_AN() {
        String nameAN = RestAssured
                .get("/ListOfContinentsByName")
                .then().extract()
                .path("ArrayOftContinent.tContinent.findAll{it.sCode=='AN'}.sName");

        Assert.assertEquals("Antarctica", nameAN);
        System.out.println(nameAN);
    }

    @Test
    public void lastContinetnName() {
        String lastName = RestAssured
                .get("/ListOfContinentsByName")
                .then().extract()
                .path("ArrayOftContinent.tContinent[-1].sName");
        Assert.assertEquals("The Americas", lastName);
        System.out.println(lastName);

    }

}
