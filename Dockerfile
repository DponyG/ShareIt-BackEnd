FROM airhacks/glassfish
COPY ./target/penguinCompany.war ${DEPLOYMENT_DIR}
