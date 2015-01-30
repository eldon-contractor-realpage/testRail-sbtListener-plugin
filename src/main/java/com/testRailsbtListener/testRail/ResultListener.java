package com.testRailsbtListener.testRail;

import com.testRailsbtListener.TestSetUp;

import java.io.IOException;
import java.net.MalformedURLException;



public class ResultListener  {

    public void updateCaseStatus(Integer status) {
       // if (System.getProperty("automation.testrail.api").toLowerCase().equals("true")) {
            TestSetUp.prepareTestCase();
            String statusString = (status == 1) ? "PASS" : "FAIL";
            Integer runId = Integer.valueOf(System.getProperty("automation.testrail.run.id"));
            Integer caseId = Integer.valueOf(System.getProperty("automation.testrail.case.id"));
            if (runId > 0 && caseId > 0) {
                TestRailHelper testRail = TestRailHelper.getInstance();

                String testRailUsername = System.getProperty("automation.testrail.username");
                String testRailPassword = System.getProperty("automation.testrail.password");
                String testRailUrl = System.getProperty("automation.testrail.url");

                testRail.authenticate(testRailUsername, testRailPassword,
                        testRailUrl);

                try {
                    testRail.updateCaseStatus(runId, caseId, status, "");
                } catch (MalformedURLException e) {
                    System.out
                            .println(String
                                    .format("[TestRail] There was a problem updating the test case #%d on run #%d",
                                            caseId, runId));
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out
                            .println(String
                                    .format("[TestRail] There was a problem updating the test case #%d on run #%d",
                                            caseId, runId));
                    e.printStackTrace();
                } catch (APIException e) {
                    System.out
                            .println(String
                                    .format("[TestRail] There was a problem updating the test case #%d on run #%d",
                                            caseId, runId));
                    e.printStackTrace();
                } finally {
                    System.out
                            .println(String
                                    .format("[TestRail] Test case #%d was updated to %s in run #%d",
                                            caseId, statusString, runId));
                }
            }
       // }
    }
}