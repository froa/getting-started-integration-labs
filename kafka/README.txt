Simple Producer for Kafka
----------------------------------------------------------------------------------------------
1.- Setup Java Project

mvn archetype:generate -DgroupId=cl.fr.poc.kafka.simpleproducer -DartifactId=simple-producer -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

mvn exec:java -Dexec.mainClass="cl.fr.poc.kafka.simpleproducer.App" -Dexec.args="arg0 arg1 arg2" 

2.- Execute the simple producer App

 mvn exec:java -Dexec.mainClass="cl.fr.poc.kafka.simpleproducer.SimpleAppProducer" -Dexec.args="bgd-lab:9092 kafkatopic MySampleMessagereset5"

 WARNING: The hostname declare into local host config and the name of the server must be the same.

PoC Oracle Service Bus 12c - Kafka Transport
----------------------------------------------------------------------------------------------

1.- Add the kafka libs to the OSB classpath, in this case, we are going to copy the libs on the $OSB_DOMAI/lib folder as a Best Practice 
recommended by Oracle.

- $KAFKA_HOME/libs/kafka_2.10-0.8.2.1.jar
- $KAFKA_HOME/libs/kafka-clients-0.8.2.1.jar
- $KAFKA_HOME/libs/log4j-1.2.16.jar
- $KAFKA_HOME/libs/metrics-core-2.2.0.jar
- $KAFKA_HOME/libs/scala-library-2.10.4.jar
- $KAFKA_HOME/libs/zkclient-0.3.jar
- $KAFKA_HOME/libs/zookeeper-3.4.6.jar

2.- Download and install the kafka transport: (http://www.ateam-oracle.com/wp-content/uploads/2015/06/kafka-transport-0.3.1.zip)
    WARNING this transport do not have a Oracle support.

    Copy the ear and jar to $MDW_HOME/osb/lib/transport/

    Copy the xml to $MDW_HOME/osb/config/plugins
    
    Finally deploy the jar as a library and ear as and application.



    

