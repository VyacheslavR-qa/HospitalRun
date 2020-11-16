1. Import project from git
2. Add Maven (Idea: right mouse click - Add framework support)
3. Change local driver path in scr/main/java/DriverPath class
4. To launch tests on Chrome, use `mvn test -Dbrowser=chrome`,
on Firefox - `mvn test -Dbrowser=ff` (or =firefox).
`mvn test` launches tests on Chrome by default.
5. To see Allure reports, make sure it's in .pom file and use the command:
`allure serve /home/path/to/project/target/surefire-reports/`
More instructions at https://docs.qameta.io/allure/