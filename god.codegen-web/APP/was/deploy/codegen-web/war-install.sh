PATH=$PATH:/APP/was/eGovCI/eGovCI-3.10.0/bin/jdk8u292-b10/bin

export PATH

rm -rf /APP/was/target/codegen-web.was

mkdir -p /APP/was/target/codegen-web.was

cd /APP/was/target/codegen-web.was

jar xvf /APP/was/upload/codegen-web.was.war

pwd

ls -l
