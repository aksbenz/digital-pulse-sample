# digital-pulse-sample

Requires maven

Drivers included in src/test/resources/drivers  
 - Chrome driver v88.0.4324.96 
 - Gecko Driver (Firefox) v0.29.0
 
 Replace chromedriver.exe / geckodriver.exe with your version, if required  
 https://chromedriver.chromium.org/downloads  
 https://github.com/mozilla/geckodriver/releases/latest  
 
 Run tests:  
  - Chrome: mvn test
  - Firefox: mvn test -Dbrowser=firefox
