package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ExtentManager {

    private static ExtentReports extent;

    private ExtentManager() {}

    public static synchronized ExtentReports getExtent() {

        if (extent == null) {

            String timeStamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            String reportDir = System.getProperty("user.dir") + "/reports";
            new File(reportDir).mkdirs(); // 🔥 ENSURES folder exists

            String reportPath =
                    reportDir + "/ExtentReport_" + timeStamp + ".html";


            //spark reporter
            ExtentSparkReporter spark =
                    new ExtentSparkReporter(reportPath);

            spark.config().setReportName("UI Automation Test Report");
            spark.config().setDocumentTitle("Automation Execution Report");

            //extent report object initialization
            extent = new ExtentReports();
            //attaching spark reporter to Extent reporter
            extent.attachReporter(spark);

            extent.setSystemInfo("Framework", "Selenium + TestNG");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }

        return extent;
    }
}
