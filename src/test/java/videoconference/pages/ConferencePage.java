package videoconference.pages;

import org.openqa.selenium.By;

public class ConferencePage {

    private final By LEAVE_CALL_BUTTON = By.id("buttonLeaveSession");
    private final By MAIN_VIDEO = By.id("main-video");

    private BaseFunc baseFunc;

    public ConferencePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void leaveConference() {
        baseFunc.click(LEAVE_CALL_BUTTON);
    }

    public Boolean checkVideoPresence() {
        return (baseFunc.findElements(MAIN_VIDEO).size() != 0);
    }
}
