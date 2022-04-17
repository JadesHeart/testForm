cd C:\
java -Dwebdriver.chrome.driver="C:\chromedriver.exe" -jar selenium-server-standalone-3.5.0.jar -role webdriver -hub http://192.168.0.11:4444/grid/register -port 5556