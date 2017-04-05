# python-client

## User Portal Ingester

### Ingester.py

It will ingest data from the json files (json/labcontact.json, json/labcontacts.json, json/proposers.json, json/samples.json, json/scientists.json, json/sessions.json) into ISPyB
 
#### Requirements:

##### credentials.properties 

It should exist (someone can copy credentials.properties.example). Site should be ESRF as the ESRF authenticator will be override to allow dummy authentication.

```
[Credential]
user=mx415	
password=password
site=ESRF

```

##### ispyb.properties

This should point to a valid ISPyB instance

```
[Connection]
url=http://localhost:8085/ispyb/ispyb-ws/rest


[Proxy]
http=
https=

```



#### Run

```
cd python/userportal/
python Ingester.py

```
