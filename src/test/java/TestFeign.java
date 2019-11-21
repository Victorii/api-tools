import OpenFeign.FeignWrapper;
import org.testng.annotations.Test;

public class TestFeign {


    @Test(description = "Get Repositories")
    public void getRepositories()  {
        FeignWrapper feignWrapper = new FeignWrapper();
        feignWrapper.setURL("https://api.github.com/repositories");
        feignWrapper.getResponse();

    }

}
