chown -R web:web /APP/web
chown -R was:was /APP/was
chown -R db:db /APP/db

rm -rf /home/web/upload
rm -rf /home/was/upload
rm -rf /home/db/upload

rm -rf /APP/web/upload/*
rm -rf /APP/was/upload/*
rm -rf /APP/db/upload/*

ln -s /APP/web/upload /home/web/upload
ln -s /APP/was/upload /home/was/upload
ln -s /APP/db/upload /home/db/upload

chown -R web:web /home/web/upload
chown -R was:was /home/was/upload
chown -R db:db /home/db/upload

chmod -R 764 /APP/web/deploy/codegen-web/tar-install.sh
chmod -R 764 /APP/web/deploy/codegen-web/war-install.sh
chmod -R 764 /APP/was/deploy/codegen-web/tar-install.sh
chmod -R 764 /APP/was/deploy/codegen-web/war-install.sh
