package steps;

import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import pages.AmazonPage;
import pages.MapsPage;
import pojo.PlaceDetails;
import resources.ApiResources;
import settings.DriverSetup;
import settings.Utils;

import javax.rmi.CORBA.Util;
import java.io.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class MapsSteps extends DriverSetup {
    private MapsPage mapsPage = new MapsPage(driver);
    private AmazonPage amazonPage = new AmazonPage(driver);
    public static String coordinates;
    private Response response;
    private RequestSpecification request;
    private static String place_id;
    private PlaceDetails placeDetails;
    private String photo_reference;

    @Given("I navigate to google maps landing page")
    public void iNavigateToGoogleMaps(){
        mapsPage.goToMaps();
    }

    @When("I search for {string}")
    public void iSearchForPlace(String place){
        mapsPage.enterTextOnSearchBox(place);
    }

    @Then("I save the coordinates of the place")
    public void iSaveTheCoordinates(){
        coordinates = mapsPage.getCoordinatesOfPlace();
    }

    @Then("The API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(int status) {
        Assert.assertEquals(response.getStatusCode(),status);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String expected) {
        Assert.assertEquals(Utils.getResponseValue(response,key).toUpperCase(),expected.toUpperCase());
    }

    @When("I want to search {string} near to me")
    public void searchNearToMe(String place) throws FileNotFoundException {
        request = given().spec(Utils.requestSpecification())
                .queryParam("location",coordinates)
                .queryParam("radius",1000)
                .queryParam("keyword",place);
    }

    @When("I call {string} API with {string} http method")
    public void iCallResourceWithHttpMethod(String resource,String method){
        ApiResources apiResources = ApiResources.valueOf(resource);
        switch (method.toUpperCase()){
            case "GET":
                    response = request.when().get(apiResources.getResource());
                break;
            case "POST":
                    response = request.when().post(apiResources.getResource());
                break;
            case "DELETE":
                    response = request.when().delete(apiResources.getResource());
                break;
        }
    }

    @When("I validate {string} is displayed in search results")
    public void iValidatePlaceDisplayedInResults(String placeName){
        JsonPath js = new JsonPath(response.asString());
        int placesNumber = js.getInt("results.size()");
        boolean flag=false;

        for(int i=0;i<placesNumber;i++){
            System.out.println(Utils.getResponseValue(response,"results["+i+"].name"));
            if(placeName.equalsIgnoreCase(Utils.getResponseValue(response,"results["+i+"].name"))){
                place_id = Utils.getResponseValue(response,"results["+i+"].place_id");
                flag=true;
                break;
            }
        }
        Assert.assertTrue(flag,"Place was never found.");
    }

    @When("I add place id to request")
    public void iAddPlaceIdToReques() throws FileNotFoundException {
        request = given().spec(Utils.requestSpecification())
                .queryParam("place_id",place_id)
                .queryParam("fields","name,rating,formatted_phone_number,formatted_address,photos");
    }

    @When("I deserialize response body into PlaceDetails class")
    public void deserializeResponseBodyIntoClass() throws FileNotFoundException {
        placeDetails = response.as(PlaceDetails.class);
    }

    @When("I add reference of photo at index {int}")
    public void iAddReferenceOfPhotoAtIndex(int index) throws FileNotFoundException {
        photo_reference = placeDetails.getResult().getPhotos().get(index).getPhoto_reference();
        request = given().spec(Utils.requestSpecification())
                .queryParam("maxwidth",400)
                .queryParam("photoreference", photo_reference);
    }

    @Then("I download the photo of the place")
    public void iDownloadPhotoOfThePlace() throws IOException {
        //Create the folder
        File outputPath = new File("downloads");
        outputPath.mkdirs();

        //Create file name
        String downloadFileName = "downloadedFile_" + System.currentTimeMillis() + "_.png";
        File outputFile = new File(outputPath.getPath(), downloadFileName);

        //Get API body
        byte[] fileContents = response.getBody().asByteArray();

        //Save the image
        OutputStream outStream = new FileOutputStream(outputFile);
        outStream.write(fileContents);
        outStream.close();
    }

    @Given("goes to amazon")
    public void goesToAmazon(){
        amazonPage.goesToAmazon();
    }
}
