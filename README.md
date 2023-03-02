# CapstoneProducer
Example Producer App connecting to Apache Kafka broker
This application functions as a producer to our Kafka broker, it is a GET request that takes in a message 
and pushes it to the Kafka topic that the consumer is listening to: the URL to the prosucer is:
http://34.198.166.4/message/{message}
Where '{message}' is whatever you please.
If you receive a 500 error it is likely that our AWS instance that is running the broker is stopped, as it costs 
a decent amount to keep running 24/7,so we shut it down when not developing or testing.

**Release Notes**
Version 1.0.0-SNAPSHOT:
In this version our prodcuer is mapped as a GET request that takes in a message String and pushes that string onto our broker's topic

Version 1.0.1-SNAPSHOT: 
Coming soon.
