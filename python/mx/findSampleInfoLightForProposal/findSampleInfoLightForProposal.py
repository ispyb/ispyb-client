import sys
from suds.client import Client
from suds.transport.http import HttpAuthenticated
import os, shutil
from pprint import pprint
import ConfigParser
import myLogger

if __name__ == "__main__":

	config = ConfigParser.ConfigParser()
	config.read('ispyb.properties')

	# File that contains an user and password with permissions on ISPyB should be defined
	credentialsConfig = ConfigParser.ConfigParser()
	credentialsConfig.read('credentials.properties')

	username = str(credentialsConfig.get('Credential', 'user'))
	password = str(credentialsConfig.get('Credential', 'password'))
	url = str(config.get('Connection', 'url'))

	myLogger.printConfiguration(username, password, url)

	# Authentication
	httpAuthenticatedToolsForAutoprocessingWebService = HttpAuthenticated(username = username, password = password ) 
	client = Client( url, transport = httpAuthenticatedToolsForAutoprocessingWebService, cache = None, timeout = 15 )
					  
	# Proposal parameters
	proposalCode = config.get('Proposal', 'type')
	proposalNumber = config.get('Proposal', 'number')

	# Creating the experiment
	res = client.service.findSampleInfoLightForProposal(7, "ID29")

	print(res)


