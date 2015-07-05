Simple Producer for Kafka
----------------------------------------------------------------------------------------------
1.- Setup Java Project

mvn archetype:generate -DgroupId=cl.fr.poc.kafka.simpleproducer -DartifactId=simple-producer -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

mvn exec:java -Dexec.mainClass="cl.fr.poc.kafka.simpleproducer.App" -Dexec.args="arg0 arg1 arg2" 

1.- Execute the simple producer App

 mvn exec:java -Dexec.mainClass="cl.fr.poc.kafka.simpleproducer.SimpleAppProducer" -Dexec.args="bgd-lab:9092 kafkatopic MySampleMessagereset5"

 WARNING: The hostname declare into local host config and the name of the server must be the same.
