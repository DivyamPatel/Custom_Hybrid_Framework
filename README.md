# Custom Hybrid Framework (Mobile & Web)

# TECHNOLOGIES USED
 -  Cucumber – BDD Framework 
 -  Maven – Build Tool 
 -  TestNG 
 -  POM - Framework(Custom)
 -  JAVA - Coding language
 
# NEW FEATURES!
  - User can do Mobile Automation along with Web Automation (Functional).
  - Remove hard coded configuration values and create Customize configuration file.

# CONFIGURATION FILE
We have properties file from where we can configure the environment for execution
1. RUN_ON: Here we provide the device the user wants to run the test - Mobile_Android/Mobile_IOS/Mobile_Browser/Web
2. SERVER_IP:Here the Server IP where the Appium is running is provided. (e.g. 127.0.0.1(default))
3. PORT: Here the Port number of the appium is provided. (e.g. 4723 (default))
4. DEVICE_NAME: Connect your real mobile device with the system and open cmd. Type “adb devices” and note the device number. The number is mentioned here.
5. PLATFORM_NAME: Platform the user wants to execute the test – Android/ IOS
6. PLATFORM_VERSION: Platform version the user wants to execute the test – (e.g. 6.0.1)
7. APP_PATH: The path where the .apk or .ipa file resides
8. APP_PACKAGE: The name of the application package
9. APP_ACTIVITY: The name of the application main activity
10. BASE_URL: The url of the web application
11. BROWSER_NAME: The name of the browser user wants to execute the test (chrome, firefox, ie)
12. CHROME_DRIVER_PATH: The path of the chromedriver executable file
13. FIREFOX_DRIVER_PATH: The path of the geckodriver executable file
14. REPORT_CONFIG_PATH: The path of the extent-report.xml file
15. REPORT_PATH: The path where the user wants to generate report and screenshots
16. LAST_RUN: The number maintained for the execution
17. REPORT_TEMPLATE: The path of the html template sent via mail

# CREATE A NEW TESTCASE
1.	Create a .feature file 
2.	Create a .java file