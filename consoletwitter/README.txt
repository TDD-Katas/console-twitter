        CONSOLE TWITTER KATA

COMPILING AND RUNNING

The project uses Maven for build and requires at least Java 6. 
To build the project you should run:
> mvn clean install
This command will compile and run all tests.

To run the project use:
> mvn exec:java

THE TESTS

The project has three types of tests: unit, integration and acceptance. 
Apart from testing the system, they have also the role of being a living documentation.

*Unit tests*
The unit tests describe the smallest behavior of the application. 
All the unit test are isolated from others using stubs.
The unit tests can be found in the folder ./src/test/java and their filename ends with *Test.java 
These tests can be run using:
> mvn test

*Integration test*
Because the unit test use stubs, the default constructors are usually not touched by them. 
The integration tests are responsible for testing the default bindings between objects.
The Integration tests can be found in the folder ./src/test/java  and their filename ends with *IT.java 
These tests can be run using:
> mvn verify

*Acceptance test*
These test show that the system does what is supposed to do from the business point of view. 
They can be viewed as testing by example. While unit tests and integration test are aware of the internal implementation, acceptance test only care about inputs and output.
The Acceptance tests tests can be found in the folder ./src/test/specs
These tests can be run using:
> mvn verify
The acceptance test have been implemented using Concordion. The output of running the test is an .html file located at:
/target/specs/ro/ghionoiu/twitter/ConsoleTwitter.html

