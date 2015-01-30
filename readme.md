Provides reporting of test success and failure for tests run by
[simple build tool](https://github.com/harrah/xsbt)
in a format
that TestRail understands.



To use, add the following line to a plugins.sbt file in your project directory:

```
addSbtPlugin("com.testRailsbtListener" % "sbt-com-testrailsbtlistener-testrail-test-reporting-plugin" % "0.1")
```

It will do nothing at all when not running under TestRail, but
when it _is_ running under TestRail (detected by the presence of the `automation.testrail` environment variable)
it will report success and failure of executed tests.


