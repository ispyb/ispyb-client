# python-client

## User Portal Ingester

### Ingester.py

It will ingest data from the json files (json/labcontact.json, json/labcontacts.json, json/proposers.json, json/samples.json, json/scientists.json, json/sessions.json) into ISPyB
 
#### Requirements:

credentials.properties should exist (someone can copy credentials.properties.example)
```
[Credential]
user=opd29	
password=*****

```

ispyb.properties
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
