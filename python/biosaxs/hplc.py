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
	url = config.get('Connection', 'hplc_esrf_url')
	username = config.get('Connection', 'user')
	password = config.get('Connection', 'password')

	# Authentication
	httpAuthenticatedToolsForAutoprocessingWebService = HttpAuthenticated(username = username, password = password ) 
	client = Client( url, transport = httpAuthenticatedToolsForAutoprocessingWebService, cache = None, timeout = 15 )
					  
	# Proposal parameters
	proposalCode = config.get('Proposal', 'type')
	proposalNumber = config.get('Proposal', 'number')

	# Creating the experiment
	experimentId = client.service.createHPLC(proposalCode, proposalNumber, "HPLC Test")

	# Storing HDF5 file
	client.service.storeHPLC(experimentId, "/data/pyarch/bm29/testing/mx1572/3284/CagA_884_041.h5", None)

		
	files = ["/subtracted_sub.dat"]

	rgStdev= 0.002
	i0 = 62.4
	i0Stdev = 0.89
	firstPointUsed = 10
	lastPointUsed = 25
	quality = 0.5
	isagregated = False
	code = "code" 
	concentration = 3.2
	gnomFile = "gnomFile"
	rgGuinier = 1.5
	rgGnom = 1.2
	dmax = 23
	total= 45
	volume= 1002
	frameStart = 148
	frameEnd = 169
	
	bestBufferFilePath= "/data/bestBufferFilePath.dat"
	scatteringFilePath= "/data/scatteringFilePath.png"
	guinierFilePath= "/data/guinierFilePath.png"
	kratkyFilePath= "/data/kratkyFilePath.png"
	densityPlot= "/data/densityPlot.png"

	# Analysis Info
	client.service.storeHPLCDataAnalysisResult(
							experimentId,
							None,
							None,
							rgStdev,
							i0 ,
							i0Stdev,
							firstPointUsed ,
							lastPointUsed,
							quality,
							isagregated,
							code,
							concentration,
							gnomFile,
							rgGuinier,
							rgGnom ,
							dmax,
							total,
							volume,
							frameStart,
							frameEnd,
							str(files),
							bestBufferFilePath,
							scatteringFilePath,
							guinierFilePath,
							kratkyFilePath,
							densityPlot
							)
