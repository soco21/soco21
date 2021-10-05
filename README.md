# Repository to Work on the exercises of "Software Construction" in fall 2021 at University of Zurich



<p align="center">
  <a href="https://github.com/soco21/soco21-group8/actions/workflows/java.yml?query=branch%3Amain+">
    <img src="https://github.com/soco21/soco21-group8/actions/workflows/java.yml/badge.svg?branch=main" alt="java">
  </a>
</p>

This is the repository of Group 8.

## Setup

Tooling:
* Java: Version 17, build provided by the eclipse foundation under the name: 'temurin'
* Maven: Version 3.6.3

### Structure
* [./parent/pom.xml](parent/pom.xml)  
Contains the shared dependencies over all exercises, plus the plugins
for code formatting (spotless-maven-plugin), test execution (maven-surefire-plugin)
and execution of a main class (maven-exec-plugin)

* assignment-*/exercise* e.g. [./assignment-0/exercise1](./assignment-0/exercise1)  
Contain the code for the exercises. They should define a main class in there pom that the maven-exec plugin works.

* [reactor.pom.template](reactor.pom.template) and [aggregate-modules.sh](aggregate-modules.sh)  
Used for the github actions, that the github actions don't need to reference every task.
[aggregate-modules.sh](aggregate-modules.sh) creates with [reactor.pom.template](reactor.pom.template) a reactor.pom
which references all the pom.xml in this repo. If you run a maven target on the reactor.pom, it will run the target over
all maven projects.

# How to set up a new task

1. Create a new maven project with the groupid ch.uzh.soco21.group8
and the artifact name `assignment-${number}-exercise${number}` and the parent [./parent/pom.xml](parent/pom.xml).
2. Look that the pom looks similar to the one in [./assignment-0/exercise1/pom.xml](./assignment-0/exercise1/pom.xml).
3. Add a main class with the package ch.uzh.soco21.group8.assignment${number}.exercise${number}.
4. Reference this main class in your pom.xml.
5. Copy [./assignment-0/exercise1/.mvn](./assignment-0/exercise1/.mvn) to your exercise.  
This step is needed, because the spotless-maven-plugin uses internal java api, which
is not exported by default since java 16.
