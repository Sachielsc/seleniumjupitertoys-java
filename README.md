# Selenium-Java reference implementation project for Jupiter Toys
This project is incomplete, it is intended as a guide and template as well as training and assessment purposes.

It is highly recommended that Visual Studio COde with .devcontainer is used when developing Automation projects that will be executed in a CI/CD environment using docker. Devcontainers will give every person in a team the same dev environment as well as the same environment that CI/CD will use to run the code.

## Visual Studio Code devcontainer requirements
* Install the Remote Containers extension
* Run the 'Remote-Containers: Reopen In Container' command
  * Be patient while the first build of .devcontainers docker-compose runs
* To configure environment variables for test execution from within vscode please open the file .vscode/setting.json and edit the env vars there

## Local Requirements
* OpenJDK 11
  * Make sure JAVA_HOME points to the OpenJDK 11 installation dir
  * Make sure that $JAVA_HOME/bin is the only java bin directory in your $PATH
* Junit 5.x
* Selenium 3.141.x
* Maven
* Chromedriver and Geckodriver in your $PATH (%PATH% for Windows)
  * Install on Windows:
    * ```choco install selenium-gecko-driver```
    * ```choco install chromedriver```
  * Install on macOS:
    * ```brew cask install chromedriver```
    * ```brew install geckodriver```

### To execute all tests run: 
```bash
SELENIUM_BROWSER=firefox SELENIUM_WAIT=3 SELENIUM_URL="http://jupiter2.cloud.planittesting.com" SELENIUM_HEADLESS=true mvn test -Dparallel-test=true
```