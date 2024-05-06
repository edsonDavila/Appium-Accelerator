package extentTestNGSetup;

import com.aventstack.extentreports.ExtentTest;
import common.csvUtils.CsvUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestForReports extends ExtentSetup {
    private List<Map<String, String>> csvData;
    private CsvUtils csvUtils;
    private ExtentTest testNode;
    private ExtentTest test;

    @BeforeClass
    public void setUpClass() throws IOException {
        String currentDir = Paths.get(System.getProperty("user.dir"), "/CsvFiles", "/google", "/google.csv").toFile().getPath();
        csvUtils = new CsvUtils();
        csvData = csvUtils.getCsvData(currentDir);
        test = extentReports.createTest("Test For Reports");
    }

    @DataProvider
    public Iterator<Object[]> getTestData() {
        ArrayList<Object[]> dataIteration = csvUtils.fillDataProviderIterations();
        return dataIteration.iterator();
    }

    @Test(dataProvider = "getTestData")
    public void testWithNodes1(int iteration) {
        if (iteration == 0)
            testNode = test.createNode("Test With nodes 1");
        testCase = testNode.createNode("This is the TC for iteration " + (iteration + 1));
    }

    @Test
    public void testWithNodes2() throws Exception {
        testNode = test.createNode("Test With nodes 2");
        testCase = testNode.createNode("This is the TC");
        throw new Exception("Exception To check fails");
    }

    @Test
    public void testWithNodes3() {
        testNode = test.createNode("Test With nodes 3");
        testCase = testNode.createNode("This is the TC");
        Assert.assertTrue(false);
    }
}
