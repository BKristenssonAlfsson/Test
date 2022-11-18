# Start the project

The project has one endpoint, POST that will send a incoming request through rabbitmq to a postgres db

## clone the repo.
# Import it to Eclipse / IntelliJ
* Write __make dev__
* start the project, I use IntelliJ
* In the webbrowser, head to http://localhost:10000/rest/demo/v1/swagger-ui/index.html
* Execute some POST requests
* check that in psql the messages are stored
  * docker exec -ti postgres psql -U postgres
  * \c demo
  * select * from message;
* Stop the containers
* Execute the tests
* rabbitmq container drops connection before second test has passed.
