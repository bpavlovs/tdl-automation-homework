package videoconference.pages;

import org.openqa.selenium.By;

import java.awt.image.BufferedImage;

public class JoinConferencePage {
    private final By PARTICIPANT_INPUT = By.id("userName");
    private final By SESSION_INPUT = By.id("sessionId");
    private final By JOIN_BUTTON = By.xpath(".//input[@name = 'commit']");
    private final By LOGO = By.xpath(".//img[contains(@src, 'openvidu_grey_bg')]");

    private BaseFunc baseFunc;

    public JoinConferencePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public ConferencePage joinConference(String username) {
        baseFunc.type(PARTICIPANT_INPUT, username);
        baseFunc.type(SESSION_INPUT, "SessionA");
        baseFunc.click(JOIN_BUTTON);

        return new ConferencePage(baseFunc);
    }

    public BufferedImage getLogoImage() {
        return baseFunc.getActualImage(baseFunc.findElement(LOGO));
    }
}