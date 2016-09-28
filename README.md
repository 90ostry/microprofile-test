microprofile-test
=================

# Test

Simple manually created test project for microprofile JEE with JAX-RS resource, CDI and JSON-P. It is run and build with usage of wildfly-swarm with only dependency for microprofile.  Contains one test endpoint which simple produce plain text. It is available under address:


	http://127.0.0.1:8080/test



Example contains also JAX-RS resource with current date time, parse using Json-P Object Model API. Resource is available under address:


	http://127.0.0.1:8080/datetime



# Wildfly-swarm Generator

Similar simple project archetype could be generated using wildfly-swarm online generator:

	http://wildfly-swarm.io/generator/


# Usage
To run it use simple maven command. It will create uber-jar with all required dependencies and run it. 


	mvn wildfly-swarm:run

