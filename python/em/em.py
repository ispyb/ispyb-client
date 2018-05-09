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


if __name__ == "__main__":

	config = ConfigParser.ConfigParser()
	credentialsConfig = ConfigParser.ConfigParser()

	# Configuration files
	config.read('ispyb.properties')	
	credentialsConfig.read('credentials.properties')


	username = str(credentialsConfig.get('Credential', 'user'))
	password = str(credentialsConfig.get('Credential', 'password'))
	url = str(config.get('Connection', 'url'))


	# Authentication
	httpAuthenticatedToolsForAutoprocessingWebService = HttpAuthenticated(username = username, password = password ) 
	client = Client( url, transport = httpAuthenticatedToolsForAutoprocessingWebService, cache = None, timeout = 15 )
					  
	# Proposal parameters
	proposalCode = config.get('Proposal', 'type')
	proposalNumber = config.get('Proposal', 'number')

        # This changes the grid               
        proteinAcronym = "EMPROTEIN"
        sampleAcronym = "EMPROTEIN_sample2"
	# This changes the grid square 
	movieDirectory = "/data/directory/" + sampleAcronym +"/grid2"

        proposal = proposalCode + proposalNumber,
	movieNumber = 1
	movieFullPath = "/data/directory/" + str(movieNumber) + "/movie.mrc"
	micrographFullPath = "/data/directory/" + str(movieNumber) + "/movie.mrc"
	micrographSnapshotFullPath = "/data/directory/" + str(movieNumber) + "/movie.mrc"
	xmlMetaDataFullPath = "/data/directory/" + str(movieNumber) + "/movie.xml"
	voltage = 1
	sphericalAberration = 2
	amplitudeContrast = 3
	magnification = 4
	scannedPixelSize = 5
	imagesCount = 28
	dosePerImage = 5
	positionX = 1
	positionY = 1
	beamlineName = "CM01"
	gridSquareSnapshotFullPath = "/data/directory/1/movie.mrc"
   
        client.service.addMovie( proposal,
                        proteinAcronym,
			sampleAcronym,
			movieDirectory,
			movieFullPath,
			movieNumber,
			micrographFullPath,
			micrographSnapshotFullPath,
			xmlMetaDataFullPath,
			voltage,
			sphericalAberration,
			amplitudeContrast,
			magnification,
			scannedPixelSize,
			imagesCount,
			dosePerImage,
			positionX,
			positionY,
			beamlineName,
			gridSquareSnapshotFullPath)
         

       
