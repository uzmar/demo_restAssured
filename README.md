### Framework.utils
### BaseTest
It will have functions to set header and URLs

### BusinessUtils
It has methods to read/modify the json files .gitignore

### userUtils
To read specific requests from testData.

### ( Prerequisite)
1. Install Java or check if Java is installed on your machine.
2.  Install IDE like Eclipse, IntelliJ
3.  Install Build tool - Maven from here https://maven.apache.org/download.cgi. 

### Creating Maven project in Eclipse
1. In IntelliJ go to Project > New Project > Build System > Select Maven 

### Importing existing project:
Clone the Repo using>  git clone <repo-url>

### To follow better what is going on.
mvn -X -e clean test

### To run the tests
$> mvn clean test -DsuiteXmlFile=testSuite.xml -DEnv=Dev

