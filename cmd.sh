#!/bin/bash
mkdir dockerfiles/glassfish/autodeploy
mv target/company dockerfiles/glassfish/autodeploy/company
docker-compose up