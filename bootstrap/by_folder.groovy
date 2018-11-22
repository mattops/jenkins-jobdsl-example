import static groovy.io.FileType.FILES
import javaposse.jobdsl.dsl.*
import javaposse.jobdsl.plugin.JenkinsJobManagement

def jobs_dir = "${WORKSPACE}/${SERVER_ENVIRONMENT}"
def workspace = new File('.')
def jobManagement = new JenkinsJobManagement(System.out, [:], workspace)

new File(jobs_dir).eachDir() { dir ->
  println "Processing directory: ${dir.getPath()}"

  def items = [];
  new File(dir.getPath()).eachFileMatch(~/.*.groovy$/) {
    file -> items.add(file.getName().replaceAll(~/.groovy$/, ""))
    println "Loading jobdsl in file: ${file.getName()}"
  }

  for (job in items) {
    def dslLoader = new DslScriptLoader(jobManagement)
    dslLoader.runScript(readFileFromWorkspace("${dir}/${job}.groovy").toString())
  }

}
