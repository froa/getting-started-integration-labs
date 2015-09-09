#simple instance for develop.
mongod --port 27001 --dbpath ./data  --logpath ./logs/log_sample-camel.log --logappend --oplogSize 50 --fork