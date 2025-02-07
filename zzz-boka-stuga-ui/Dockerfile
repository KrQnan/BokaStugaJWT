# syntax=docker/dockerfile:1
FROM image-registry.openshift-image-registry.svc:5000/cap-template/fk-httpd:2.4-230-fk1
ARG DIST_DIR=build/

COPY ${DIST_DIR} /var/www/html/
ENTRYPOINT ["run-httpd"]
EXPOSE 8080