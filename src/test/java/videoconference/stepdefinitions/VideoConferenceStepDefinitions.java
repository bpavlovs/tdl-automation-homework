package videoconference.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import videoconference.pages.BaseFunc;
import videoconference.pages.ConferencePage;
import videoconference.pages.JoinConferencePage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class VideoConferenceStepDefinitions {

    private final String HOME_PAGE_URL = "https://demos.openvidu.io/basic-videoconference/";

    private HashMap<String, BaseFunc> baseFuncs = new HashMap<>();
    private HashMap<String, JoinConferencePage> joinConferencePages = new HashMap<>();
    private HashMap<String, ConferencePage> conferencePages = new HashMap<>();

    @Given("I navigate to main page with {string}")
    public void open_main_page(String username) {
        baseFuncs.put(username, new BaseFunc());
        baseFuncs.get(username).openUrl(HOME_PAGE_URL);
        joinConferencePages.put(username, new JoinConferencePage(baseFuncs.get(username)));
    }

    @When("I join a call for {string}")
    public void join_video_conference(String username) {
        assertFalse(joinConferencePages.isEmpty(), "Can't access 'Join Conference' pages!");
        conferencePages.put(username, joinConferencePages.get(username).joinConference(username));
    }

    @Then("I validate that the {string} is properly presented for {string}")
    public void check_main_page(String pageName, String username) throws IOException {
        BufferedImage expectedImage = baseFuncs.get(username).getExpectedImage(pageName);
        BufferedImage actualImage = joinConferencePages.get(username).getLogoImage();
        assertFalse(baseFuncs.get(username).compareImages(actualImage, expectedImage), "Images do not match");
    }

    @Then("I validate that I am in a call")
    public void check_conference_page() {
        assertFalse(conferencePages.isEmpty(), "Can't access 'Conference' pages!");
        for (Map.Entry<String, ConferencePage> entry : conferencePages.entrySet()) {
            entry.getValue().checkVideoPresence();
        }
    }

    @Then("I end the call")
    public void end_call() {
        assertFalse(conferencePages.isEmpty(), "Can't access 'Conference' pages!");
        for (Map.Entry<String, ConferencePage> entry : conferencePages.entrySet()) {
            entry.getValue().leaveConference();
        }
    }

    @After
    public void closeBrowser() {
        for (Map.Entry<String, BaseFunc> entry : baseFuncs.entrySet()) {
            entry.getValue().closeBrowser();
        }
    }
}

