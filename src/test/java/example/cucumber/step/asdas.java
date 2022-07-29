package example.cucumber.step;

import io.restassured.RestAssured.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.io.IOException;


public class asdas {


    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\KP\\IdeaProjects\\kakVPrimrere\\src\\test\\java\\example\\cucumber\\step\\kpfu-test.html");
        Document document = Jsoup.parse(file, "UTF-8", "kpfu.ru");
//        Document document = Jsoup.connect("https://kpfu.ru/").get();
    }
//    @Test
//    public void givenUrl_whenSuccessOnGetsResponseAndJsonHasRequiredKV_thenCorrect() {
//        System.out.println(get("https://ecokarta.tatar.ru/eco/WrecksPorts/labels?rev=1.42.35").then().statusCode(200).assertThat()
//                .body("id",equalTo("Геометрия")));
//    }
}
