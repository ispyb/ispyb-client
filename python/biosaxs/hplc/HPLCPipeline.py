import sys
from suds.client import Client
from suds.transport.http import HttpAuthenticated
import os, shutil
from pprint import pprint
import ConfigParser
import myLogger

if __name__ == "__main__":

	config = ConfigParser.ConfigParser()
	config.read('../ispyb.properties')

	# File that contains an user and password with permissions on ISPyB should be defined
	credentialsConfig = ConfigParser.ConfigParser()
	credentialsConfig.read('../credentials.properties')

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
	sampleMeasurementId = client.service.storeHPLCDataAnalysisResult(
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

	# Adding abinitio
	models = "[{pdbFile: '/data/../dammif1.pdb', rg: '1.23', dMax: '232', I0: '12121'},{pdbFile: '/data/../dammif2.pdb', rg: '2.23', dMax: '232', I0: '12121'}]"	
	damaver = "{'pdbFile' :'/data/pyarch/bm29/mx1431/1140/1d/damaver.pdb'}" 
	damfilt = "{'pdbFile' :'/data/pyarch/bm29/mx1431/1140/1d/damfilt.pdb'}"
	damin = "{fitFile:'/data/pyarch/bm29/mx1469/1774/29219/dammin.fit', firFile:'/data/pyarch/bm29/mx1469/1774/29219/dammin.fir', 'pdbFile' :'/data/pyarch/bm29/mx1431/1140/1d/dammin.pdb'}"
	nsdPlot = "/data/pyarch/bm29/mx1431/1140/1d/nsd.png"
	chi2plot ="/data/pyarch/bm29/mx1431/1140/1d/chi2_R.png"

	client.service.storeAbInitioModels( "[" + str(sampleMeasurementId) + "]",  models, str(damaver), str(damfilt), str(damin), nsdPlot, chi2plot)

        print "HPLC created on ISPyB"
