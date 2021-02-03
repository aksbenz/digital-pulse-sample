package digitalpulse;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    World w;
    public Hooks(World w){
        this.w = w;
    }

    @Before()
    public void setup(Scenario sc){
        w.init(sc);
    }

    @After()
    public void closeBrowser(Scenario sc){
        if (sc.isFailed())
            w.attachScreenshot();

        if (w.driver != null)
            w.driver.quit();
    }
}
