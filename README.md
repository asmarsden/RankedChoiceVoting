# RankedChoiceVoting

This is currently hosted live at https://rankedchoicevoting.herokuapp.com/

In order to run the angular side of this project locally, follow these steps:

* Clone the repository
* Switch to the angularapp branch
* Import the project into Eclipse IDE as a maven project 
* Navigate to the folder /src/main/resources/angularFrontend/
* Via the command line, run ng build --prod (this may require you to install npm if you do not have it already)
* In Eclipse, run Maven clean, then Maven install (or, if using the CLI, use the maven clean install command)
* After that, run the project as a Spring Boot Application
* Navigate to localhost port 8080 to see the Angular side of the application

* Note: Due to the fact that this is designed for the production website hosted by Heroku, a couple of changes may need to be made locally to make the Angular program work. In the files ballot.service.ts and poll.service.ts, the base url will have to change to reflect the localhost url rather than the live url. 


In order to run the android side of this project locally, follow these steps: 
