#!/bin/bash
docker rm -f projectmaster_db_1 projectmaster_web_1 projectmaster_prom_1
docker rmi  projectmaster_db projectmaster_web projectmaster_prom
docker network rm projectmaster_default