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
import myJSONLogger
import myLogger
import json

def user_yes_no_query(question):
    sys.stdout.write('%s [y/n]\n' % question)
    while True:
        try:
            return strtobool(raw_input().lower())
        except ValueError:
            sys.stdout.write('Please respond with \'y\' or \'n\'.\n')	
	
def appendMeasurement(  experimentId, runNumber, sampleType, plate, row, well, name, bufferName):
	concentration = 0
	SEUtemperature = 20
	viscosity = 'LOW'
	volume = 40
	volumeToLoad = 40
	waitTime = 0
	transmission = 100
	comments = ""
	measurement = client.service.appendMeasurementToExperiment(
			experimentId,
			runNumber,
			sampleType,
			plate,
			row,
			well,
			name,
			bufferName,
			concentration,
			SEUtemperature,
			viscosity,
			volume,
			volumeToLoad,
			waitTime,
			transmission,
			comments)
	myLogger.printMeasurement(measurement)



	


if __name__ == "__main__":

	config = ConfigParser.ConfigParser()
	config.read('../ispyb.properties')

	# File that contains an user and password with permissions on ISPyB should be defined
	credentialsConfig = ConfigParser.ConfigParser()
	credentialsConfig.read('../credentials.properties')

	proposal = str(credentialsConfig.get('Credential', 'user'))
	password = str(credentialsConfig.get('Credential', 'password'))
	url = str(config.get('Connection', 'GenericSampleChangerBiosaxsWebService'))

	myLogger.printConfiguration(proposal, password, url)
	

	if (user_yes_no_query("Are these values OK?") == False):
		print "Exit"
		sys.exit()

	# Creating SUDS client
	timeout = 20
	httpAuthenticatedToolsForAutoprocessingWebService = HttpAuthenticated( username = proposal, password = password ) 	
	client = Client( url, transport = httpAuthenticatedToolsForAutoprocessingWebService, cache = None, timeout = timeout )

	######################################
	# Creating an experiment 
	######################################
	experiment = client.service.createEmptyExperiment( config.get('Proposal', 'type'), config.get('Proposal', 'number'), "EMBLSampleChanger Test Experiment" )       


	experiment = json.loads(experiment)
	myJSONLogger.printExperiment(experiment)

	if (user_yes_no_query("\nDo you want to Continue?") == False):
		print "Exit"
		sys.exit()

        #####################################
	# Get Experiment Description
	#####################################
        description = client.service.getDescriptionByExperimentId(11925)
        print "Experiment description is: " + str(description)

	#####################################
	# Append measurements
	#####################################
	runNumbersId = []

        runNumber = 1
	appendMeasurement(experiment['experimentId'], runNumber, "BUFFER", 2,1,9,None, "HEPES")
	runNumbersId.append(runNumber)
	runNumber = 2
	appendMeasurement(experiment['experimentId'], runNumber, "SAMPLE", 2,1,1,"BSA", "HEPES")
	runNumbersId.append(runNumber)
	runNumber = 3
	appendMeasurement(experiment['experimentId'], runNumber, "BUFFER", 2,1,9,None, "HEPES")
	runNumbersId.append(runNumber)


	averaged = "[{'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00001.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00002.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00003.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00004.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00005.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00006.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00007.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00008.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00009.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00010.dat'}]"
	averagedFile = "/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_ave.dat"
	discarded = "[]"

	# Adding Run and averages
	for runNumber in runNumbersId:
		client.service.addRun(experiment['experimentId'], runNumber, 21.90, 22, 1, 'None', 'None', 'energy', 'detectorDistance', 'snapshotCapillary', 'current', 'bmX', 'bmY', 'rR', 'rA', 'pxX', 'pxY', 'normalization', 'transmission')	
		client.service.addAveraged(experiment['experimentId'], runNumber, averaged, discarded, averagedFile)

	#################################
	# ADD SUBTRACTION
	################################

	rg = randint(1,1000)
	rgStdev =  randint(1,1000)
	i0 =  randint(1,1000)
	i0Stdev =  randint(1,1000)
	
	firstPointUsed =  randint(1,100)
	lastPointUsed =  randint(200,1000)
	quality =  randint(1,100)
	isagregated =  False

	rgGnom=  randint(1,100)
	dmax=  randint(1,100)
	total=  randint(1,100)
	volume= randint(1,100)	

	experimentalDataPlotFilePath = ("/data/pyarch/bm29/testing/mx1484/2126/1d/GETC2_040_00974_sub_aver_993-scattering.png")
	guinierPlotFilePath = ("/data/pyarch/bm29/testing/mx1484/2126/1d/GETC2_040_01639_sub_aver_1653-Guinier.png")
	kratkyPlotFilePath = ("/data/pyarch/bm29/testing/mx1484/2126/1d/GETC2_040_00974_sub_aver_993-Kratky.png")
	densityPlotFilePath = ("/data/pyarch/bm29/testing/mx1484/2126/1d/GETC2_040_00974_sub_aver_993-density.png")

	sampleOneDimensionalFiles =  (config.get('ESRFSampleChanger', 'sampleOneDimensionalFiles')) 
	bufferOneDimensionalFiles =  (config.get('ESRFSampleChanger', 'sampleOneDimensionalFiles')) 
	sampleAverageFilePath = str(config.get('ESRFSampleChanger', 'sampleAverageFilePath'))
	bufferAverageFilePath = str(config.get('ESRFSampleChanger', 'bufferAverageFilePath'))
	subtractedFilePath = str(config.get('ESRFSampleChanger', 'bufferAverageFilePath'))
	gnomOutputFilePath = ("gnom_sub.out")


	client.service.addSubtraction(
												experiment['experimentId'],
												str(runNumbersId),
												rg,
												rgStdev,
												i0,
												i0Stdev,
												firstPointUsed,
												lastPointUsed,
												quality,
												isagregated,												
												rgGnom,
												dmax,
												total,
												volume,
												sampleOneDimensionalFiles,
		                                                                                bufferOneDimensionalFiles,
												sampleAverageFilePath,
												bufferAverageFilePath,
												subtractedFilePath,
												experimentalDataPlotFilePath,
												densityPlotFilePath,
												guinierPlotFilePath,
												kratkyPlotFilePath,
		                                                                                gnomOutputFilePath)



	# Adding abinitio models
	models = (config.get('GenericSampleChanger', 'models')) 
	reference = (config.get('GenericSampleChanger', 'reference')) 
	refined =(config.get('GenericSampleChanger', 'refined')) 

	client.service.addAbinitioModelling(experiment['experimentId'], runNumbersId[1],models, reference, refined)

	# Add mixture
	client.service.addMixtureAnalysis(experiment['experimentId'], runNumbersId[1], "/data/pyarch/bm29/testing/mx1569/2724/1/dammin.fit",  "[{'filePath': '/data/pyarch/bm29/testing/mx1641/7922/130082/mode_006.pdb', 'volumeFraction':'0.2' }]")
	


