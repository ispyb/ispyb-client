import sys
from suds.client import Client
from suds.transport.http import HttpAuthenticated
import os, shutil
from pprint import pprint
import ConfigParser


if __name__ == "__main__":
	# Config file
	config = ConfigParser.ConfigParser()
	config.read('ispyb.properties')

	# Connection parameters
	url = 'http://ispyvalid.esrf.fr:8080/ispyb/ispyb-ws/ispybWS/ToolsForCollectionWebService?wsdl'
	username = config.get('Connection', 'user')
	password = config.get('Connection', 'password')

	# Authentication
	httpAuthenticatedToolsForAutoprocessingWebService = HttpAuthenticated(username = username, password = password ) 
	client = Client( url, transport = httpAuthenticatedToolsForAutoprocessingWebService, cache = None, timeout = 15 )
					  
	# Proposal parameters
	proposalCode = config.get('Proposal', 'type')
	proposalNumber = config.get('Proposal', 'number')

	# Creating the experiment
	detector = client.service.findDetectorByParam("", "DECTRIS", "Pilatus_6M_F", "Unbinned")

        print detector

