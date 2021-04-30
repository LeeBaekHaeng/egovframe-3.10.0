PATH=$PATH:/APP/was/eGovCI/eGovCI-3.10.0/bin/jdk8u292-b10/bin

export PATH

rm -rf /APP/web/target/codegen-web.web

mkdir -p /APP/web/target/codegen-web.web

cd /APP/web/target/codegen-web.web

jar xvf /APP/web/upload/codegen-web.web.war

pwd

ls -l
