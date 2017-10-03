import traceback
import sys
from suds.client import Client
from suds.transport.http import HttpAuthenticated
import os, shutil
from contextlib import closing
from distutils.util import strtobool
from datetime import datetime
from random import randint
import time
import base64
import ConfigParser
from operator import itemgetter
import requests
import myLogger
import json

def user_yes_no_query(question):
    sys.stdout.write('%s [y/n]\n' % question)
    while True:
        try:
            return strtobool(raw_input().lower())
        except ValueError:
            sys.stdout.write('Please respond with \'y\' or \'n\'.\n')	

def authenticate(url, user, password, site, proxies):
    r = requests.post(url + '/authenticate?site=' + site, headers={'content-type': 'application/x-www-form-urlencoded'}, proxies=proxies, data={'login': user, 'password': password})
    print(r)
    token = (json.loads(r.text)['token'])
   
    return token
  
def ingest(token ):
    payload = {
"containerId": 333942,
"code": "AA040A",
"containerType": "Spinepuck",
"capacity": 10,
"beamlineLocation": "null",
"sampleChangerLocation": "null",
"containerStatus": "null",
"timeStamp": "Aug 29, 2017 11:18:09 AM",
"barcode": "null",
"sampleVOs": [{
"name": "PDMN_CD020194_B01-3",
"code": "106947525359a0103e5b41a",
"location": 1,
"crystalVO": {
"spaceGroup": "Unknown",
"proteinVO": {
"name": "20170426_Pdomain",
"acronym": "PDMN"
}
},
"diffractionPlanVO": {
"forcedSpaceGroup": "P1",
"requiredMultiplicity": 4,
"preferredBeamDiameter": 50,
"kappaStrategyOption": 0,
"numberOfPositions": 1,
"radiationSensitivity": 0,
"experimentKind": "MXPressE"
}
}, {
"name": "PDMN_CD020194_C01-3",
"code": "157664431459a01047efc4a",
"location": 2,
"crystalVO": {
"spaceGroup": "Unknown",
"proteinVO": {
"name": "20170426_Pdomain",
"acronym": "PDMN"
}
},
"diffractionPlanVO": {
"forcedSpaceGroup": "P1",
"requiredMultiplicity": 4,
"preferredBeamDiameter": 50,
"kappaStrategyOption": 0,
"numberOfPositions": 1,
"radiationSensitivity": 0,
"experimentKind": "MXPressE"
}
}, {
"name": "PDMN_CD020194_D01-3",
"code": "142211348259a0104f4096a",
"location": 3,
"crystalVO": {
"spaceGroup": "Unknown",
"proteinVO": {
"name": "20170426_Pdomain",
"acronym": "PDMN"
}
},
"diffractionPlanVO": {
"forcedSpaceGroup": "P1",
"requiredMultiplicity": 4,
"preferredBeamDiameter": 50,
"kappaStrategyOption": 0,
"numberOfPositions": 1,
"radiationSensitivity": 0,
"experimentKind": "MXPressE"
}
}, {
"name": "PDMN_CD020194_E01-3",
"code": "181281554859a01058ecfc2",
"location": 4,
"crystalVO": {
"spaceGroup": "Unknown",
"proteinVO": {
"name": "20170426_Pdomain",
"acronym": "PDMN"
}
},
"diffractionPlanVO": {
"forcedSpaceGroup": "P1",
"requiredMultiplicity": 4,
"preferredBeamDiameter": 50,
"kappaStrategyOption": 0,
"numberOfPositions": 1,
"radiationSensitivity": 0,
"experimentKind": "MXPressE"
}
}, {
"name": "PDMN_CD020194_F01-3",
"code": "171619487059a0105f9bd6d",
"location": 5,
"crystalVO": {
"spaceGroup": "Unknown",
"proteinVO": {
"name": "20170426_Pdomain",
"acronym": "PDMN"
}
},
"diffractionPlanVO": {
"forcedSpaceGroup": "P1",
"requiredMultiplicity": 4,
"preferredBeamDiameter": 50,
"kappaStrategyOption": 0,
"numberOfPositions": 1,
"radiationSensitivity": 0,
"experimentKind": "MXPressE"
}
}]
}
  
    r = requests.post(url + '/' +token + '/proposal/mx1819/shipping/307177/dewar/310471/puck/333942/save', headers={'content-type': 'application/x-www-form-urlencoded'}, proxies=proxies, data={'puck':json.dumps(payload)})
    print(r.text)





if __name__ == "__main__":

	config = ConfigParser.ConfigParser()
	credentialsConfig = ConfigParser.ConfigParser()

	# Configuration files
	config.read('ispyb.properties')	
	credentialsConfig.read('credentials.properties')

	user = str(credentialsConfig.get('Credential', 'user'))
	password = str(credentialsConfig.get('Credential', 'password'))
        site = str(credentialsConfig.get('Credential', 'site'))

	url = str(config.get('Connection', 'url'))
        proxy_http = str(config.get('Proxy', 'http'))
        proxy_https = str(config.get('Proxy', 'https'))

	myLogger.printConfiguration(user, password, url)
	

        proxies = {
	  'http': proxy_http,
	  'https': proxy_https,
	}

	if (user_yes_no_query("Are these values OK?") == False):
		print "Exit"
		sys.exit()
               
        token = authenticate(url, user, password, site, proxies)
        if token is None:
             print("Token can not be None")
             sys.exit()

        # reading scientists.json
        
        ingest(token)
