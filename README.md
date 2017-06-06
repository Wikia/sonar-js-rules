[![Build Status](https://api.travis-ci.org/Wikia/sonar-js-rules.svg?branch=master)](https://travis-ci.org/Wikia/sonar-js-rules)

Sonar JS rules
======
Wikia's custom SonarQube rules for analyzing JavaScript files.

## Requirements
- JDK 8

## Getting started
Read the official SonarQube documentation about [creating a plugin](https://docs.sonarqube.org/display/DEV/Build+Plugin)
and [writing custom rules for JavaScript](https://docs.sonarqube.org/display/PLUG/Custom+Rules+for+SonarJS).

Each newly introduced rule should have a corresponding unit test with fixtures that tests registration and actual rule behavior.