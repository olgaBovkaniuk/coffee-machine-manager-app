# coffee-machine-manager-app

Instructions for run the application:

1. Clone the current repository to your local repository.
2. Do mvn clean install for pom.xml(coffee-machine-parent).
3. Application's properties:
   - Coffee machine manager application runs on 8087 port. For changing application's properties go to the:
     coffee-machine-manager-app/coffeemachinemanager/src/main/resources/application.properties;
   - Event writer module runs on 8088 port. For changing application's properties go to the:
     coffee-machine-manager-app/eventwriter/src/main/resources/application.properties;
   - Coffee machine web app runs on 8080 port. For changing application's database properties go to the:
     coffee-machine-manager-app/coffeemachinewebapp/src/main/resources/database.properties;
4. After clean install, you should run three modules independently:
   - two modules are SpringBoot applications - coffee-machine-manager and event-writer - just run wich of them separately;
   - one module is Spring MVC application - coffee-machine-web-app - and it requires Tomcat servlet container.
   
   You can find diagrams with explanations of how the whole project works below:

![image](/coffeemachinewebapp/CMApp.jpg)
![image](/coffeemachinewebapp/ReadApdater.jpg)
