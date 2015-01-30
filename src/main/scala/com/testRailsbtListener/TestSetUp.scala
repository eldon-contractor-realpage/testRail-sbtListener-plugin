package com.testRailsbtListener

import com.testRailsbtListener.testRail.{ConfigurationHelper, TestRailHelper}

import org.json.simple.JSONObject


object TestSetUp {


  def prepareTestCase() : Unit = {
    var run = new JSONObject
    val testRail = TestRailHelper.getInstance()

    // Force the TestRail API integration (Should be disabled by default)
    ConfigurationHelper.setProperty("automation.testrail.api.enabled", "true")

    if (ConfigurationHelper.isEnabled("automation.testrail.api")) {

      val testRailUsername = ConfigurationHelper.getProperty("automation.testrail.username", "")

      ConfigurationHelper.setPropertyIfEmpty("automation.testrail.username", testRailUsername)

      val testRailPassword = ConfigurationHelper.getProperty("automation.testrail.password", "")

      ConfigurationHelper.setPropertyIfEmpty("automation.testrail.password", testRailPassword)

      val testRailUrl = ConfigurationHelper.getProperty("automation.testrail.url", "")

      ConfigurationHelper.setPropertyIfEmpty("automation.testrail.url", testRailUrl)

      val projectId = Integer.valueOf(ConfigurationHelper.getProperty("automation.testrail.project.id", "11"))

      ConfigurationHelper.setPropertyIfEmpty("automation.testrail.project.id", String.valueOf(projectId))

      val suiteId = Integer.valueOf(ConfigurationHelper.getProperty("automation.testrail.suite.id", "1278"))
      ConfigurationHelper.setPropertyIfEmpty("automation.testrail.suite.id", String.valueOf(suiteId))

      var runId = Integer.valueOf(ConfigurationHelper.getProperty("automation.testrail.run.id", "1461"))
      ConfigurationHelper.setPropertyIfEmpty("automation.testrail.run.id", String.valueOf(runId))

      val runName = ConfigurationHelper.getProperty("automation.testrail.run.name", "automation Test")
      ConfigurationHelper.setPropertyIfEmpty("automation.testrail.run.name", runName)

      val runDescription = ConfigurationHelper.getProperty("automation.testrail.run.description", "Automated Tests Run")
      ConfigurationHelper.setPropertyIfEmpty("automation.testrail.run.description", runDescription)

      testRail.authenticate(testRailUsername, testRailPassword, testRailUrl)


      if (runId < 1) {
        run = testRail.createRun(projectId, suiteId, runName, runDescription)
        runId = Integer.valueOf(String.valueOf(run.get("id").asInstanceOf[Long]))
      }
      else {
        run = testRail.updateRun(runId, runName, runDescription)
        runId = Integer.valueOf(String.valueOf(run.get("id").asInstanceOf[Long]))
      }
      ConfigurationHelper.setProperty("automation.testrail.run.id", String.valueOf(runId))
    }


  }

}

