package com.testRailsbtListener

import sbt._
import sbt.Keys._
import com.testRailsbtListener.testRail.{ConfigurationHelper, ResultListener}


object TestRailTestReporting extends Plugin {
  override def settings = Seq(
    testListeners ++= TestRailTestListener.ifRunningUnderTestRail
  )
}

class TestRailTestListener extends TestReportListener {


  val testRailListener = new ResultListener()

  /** called for each class or equivalent grouping */
  def startGroup(name: String) {
  }


  /** called for each test method or equivalent */
  def testEvent(event: TestEvent) {
    event.result.get match {
      case TestResult.Passed => testRailListener.updateCaseStatus(1)
      case TestResult.Failed => testRailListener.updateCaseStatus(5)
      case _ =>
    }

  }

  /** called if there was an error during test */
  def endGroup(name: String, t: Throwable) {}

  /** called if test completed */
  def endGroup(name: String, result: TestResult.Value) {}
}


 object TestRailTestListener {
   System.setProperty("automation.testrail.api", "true")
    private lazy val testRailRunName = Option(ConfigurationHelper.getProperty("automation.testrail.api"))
    lazy val ifRunningUnderTestRail = testRailRunName.map(ignore => new TestRailTestListener).toSeq
  }





