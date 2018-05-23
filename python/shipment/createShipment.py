import traceback
import sys
from suds.client import Client
from suds.transport.http import HttpAuthenticated
import os, shutil
from contextlib import closing
import zipfile
from datetime import datetime
from random import randint
import time

import json
from pprint import pprint
import ConfigParser


if __name__ == "__main__":
	# Config files
	config = ConfigParser.ConfigParser()
	config.read('ispyb.properties')
        credentialsConfig = ConfigParser.ConfigParser()
        credentialsConfig.read('credentials.properties')

	# Connection and user parameters
	url = config.get('Connection', 'url')
        proxy_http = str(config.get('Proxy', 'http'))
        proxy_https = str(config.get('Proxy', 'https'))

	username = credentialsConfig.get('Credential', 'user')
	password = credentialsConfig.get('Credential', 'password')

        pprint(url)
	# Authentication
	HTTPSoapService = HttpAuthenticated(username = username, password = password ) 
	client = Client( url, transport = HTTPSoapService, cache = None, timeout = 15 )
					  
	# Proposal parameters
	proposalCode = credentialsConfig.get('Proposal', 'type')
	proposalNumber = credentialsConfig.get('Proposal', 'number')

	
	json_data=open('example.json')

	data = json.dumps(json.load(json_data))
	pprint(data)
	json_data.close()

	# Store shipping
	shipment = client.service.storeShipping(proposalCode, proposalNumber, (data))
	print(str(shipment))

	
	
	
