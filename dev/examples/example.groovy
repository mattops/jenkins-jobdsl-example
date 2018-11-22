pipelineJob("Example pipeline") {
  description("pipeline example")
  logRotator {
    numToKeep(10)
  }
  concurrentBuild(false)
  parameters {
    stringParam("exampleParam1", "world", "Description")
    booleanParam("exampleParam2", false, "Description")
  }
  definition {
    cps {
      script('echo "hello ${exampleParam1}!"')
      sandbox()
    }
  }
}

listView("Examples") {
  jobs {
    name("Example pipeline")
  }
  columns {
    status()
    weather()
    name()
    lastSuccess()
    lastFailure()
    lastDuration()
    buildButton()
  }
}
