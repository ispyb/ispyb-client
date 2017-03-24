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
import myLogger

def user_yes_no_query(question):
    sys.stdout.write('%s [y/n]\n' % question)
    while True:
        try:
            return strtobool(raw_input().lower())
        except ValueError:
            sys.stdout.write('Please respond with \'y\' or \'n\'.\n')	
	



if __name__ == "__main__":


	config = ConfigParser.ConfigParser()
	config.read('../ispyb.properties')

	# File that contains an user and password with permissions on ISPyB should be defined
	credentialsConfig = ConfigParser.ConfigParser()
	credentialsConfig.read('../credentials.properties')

	proposal = str(credentialsConfig.get('Credential', 'user'))
	password = str(credentialsConfig.get('Credential', 'password'))
	url = str(config.get('Connection', 'url'))

	myLogger.printConfiguration(proposal, password, url)
	

	if (user_yes_no_query("Are these values OK?") == False):
		print "Exit"
		sys.exit()

	# Creating SUDS client
	timeout = 20
	httpAuthenticatedToolsForAutoprocessingWebService = HttpAuthenticated( username = proposal, password = password ) 	
	client = Client( url, transport = httpAuthenticatedToolsForAutoprocessingWebService, cache = None, timeout = timeout )

	######################################
	# Creating an experiment from Robot
	######################################
	proposalType = str(config.get('Proposal', 'type'))
	proposalNumber = str(config.get('Proposal', 'number'))
	storageTemperature = '12.0'
	bufferMode = 'BeforeAndAfter'
	extraFlowTime = '10'
	experimentType = 'STATIC'
	sourceFile = '/data/pyarch/bm29/testing/opd29/__ID__/BSA.xml'
	name = 'My Static Experiment'
	samples = str(config.get('ESRFSampleChanger', 'samples'))
	
	experiment = client.service.createExperiment( "opd", "29", samples, storageTemperature, bufferMode, extraFlowTime, experimentType, sourceFile, name )

	measurements = myLogger.getMeasurements(experiment)
	myLogger.printExperiment(experiment)
	


	if (user_yes_no_query("\nDo you want to Continue?") == False):
		print "Exit"
		sys.exit()
	
	######################################
	# Saving Frames
	######################################
	i = 0
	sampleMeasurementId = measurements[1].measurementId
	for measurement in measurements :
		i = i +1
		
		if (i == 1):
			mode = 'BEFORE'
		elif (i == 2) :
			mode = 'SAMPLE'
		else :
			mode = 'AFTER'

		experimentId = experiment.experimentId
		measurementId = sampleMeasurementId
		runNumber = i
		exposureTemperature = randint(4,60)
		storageTemperature = randint(4,60)
		timePerFrame = randint(1,10)
		timeStart = datetime.now()
		timeEnd = datetime.now()
		energy = randint(1,1000)
		detectorDistance =  randint(1,1000)
		edfFileArray = str(config.get('ESRFSampleChanger', 'edfArray'))
		snapshotCapillary =  randint(1,1000)
		currentMachine =  randint(1,1000)
		tocollect = None
		pars = None
		beamCenterX =  randint(1,1000)
		beamCenterY =  randint(1,1000)
		radiationRelative =  randint(1,1000)
		radiationAbsolute =  randint(1,1000)
		pixelSizeX =  randint(1,1000)
		pixelSizeY =  randint(1,1000)
		normalization =  randint(1,1000)
		transmission =  randint(1,1000)
		client.service.saveFrame(
											mode,
											experimentId,
											measurementId,
											runNumber,
											exposureTemperature,
											storageTemperature,
											timePerFrame,
											timeStart,
											timeEnd,
											energy,
											detectorDistance,
											edfFileArray,
											snapshotCapillary,
											currentMachine,
											tocollect,
											pars,
											beamCenterX,
											beamCenterY,
											radiationRelative,
											radiationAbsolute,
											pixelSizeX,
											pixelSizeY,
											normalization,
											transmission)
	

		averaged = "[{'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00001.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00002.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00003.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00004.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00005.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00006.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00007.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00008.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00009.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00010.dat'}]"
		averagedFile = "/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_ave.dat"
		discarded = "[]"
		client.service.addAveraged(sampleMeasurementId, (i-1), averaged, discarded, averagedFile)




	rg =  randint(1,1000)
	rgStdev =  randint(1,1000)
	i0 =  randint(1,1000)
	i0Stdev =  randint(1,1000)
	concentration = 5.5
	firstPointUsed =  randint(1,100)
	lastPointUsed =  randint(200,1000)
	quality =  randint(1,100)
	isagregated =  False
	rgGuinier=  randint(1,100)
	rgGnom=  randint(1,100)
	dmax=  randint(1,100)
	total=  randint(1,100)
	volume=  randint(1,100)
	gnomFile = "/data/gnomfile.png"			
	scatteringFilePath = ("/data/pyarch/bm29/testing/mx1484/2126/1d/GETC2_040_00974_sub_aver_993-scattering.png")
	guinierFilePath = ("/data/pyarch/bm29/testing/mx1484/2126/1d/GETC2_040_00974_sub_aver_993-Guinier.png")
	kratkyFilePath = ("/data/pyarch/bm29/testing/mx1484/2126/1d/GETC2_040_00974_sub_aver_993-Kratky.png")
	densityPlot = ("/data/pyarch/bm29/testing/mx1484/2126/1d/GETC2_040_00974_sub_aver_993-density.png")

	sampleOneDimensionalFiles =  (config.get('ESRFSampleChanger', 'sampleOneDimensionalFiles')) 
	bufferOneDimensionalFiles =  (config.get('ESRFSampleChanger', 'sampleOneDimensionalFiles')) 
	sampleAverageFilePath = str(config.get('ESRFSampleChanger', 'sampleAverageFilePath'))
	bufferAverageFilePath = str(config.get('ESRFSampleChanger', 'bufferAverageFilePath'))
	subtractedFilePath = str(config.get('ESRFSampleChanger', 'bufferAverageFilePath'))

	client.service.addSubtraction(  sampleMeasurementId,
					rg,
					rgStdev,
					i0,
					i0Stdev,
					firstPointUsed,
					lastPointUsed,
					quality,
					isagregated,					
					gnomFile,					
					dmax,
					total,
					volume,
					sampleOneDimensionalFiles,
					bufferOneDimensionalFiles,
					sampleAverageFilePath,
					bufferAverageFilePath,
					subtractedFilePath,
					scatteringFilePath,
					guinierFilePath,
					kratkyFilePath,
					densityPlot)


	# Adding abinitio
	models = "[{pdbFile: '/data/../dammif1.pdb', rg: '1.23', dmax: '232', chiSqrt: '1', rfactor: '2'},{pdbFile: '/data/../dammif2.pdb', rg: '2.23', chiSqrt: '232', dmax: '3', rfactor: '4'}]"	
	damaver = "{'pdbFile' :'/data/pyarch/bm29/mx1431/1140/1d/damaver.pdb'}" 
	damfilt = "{'pdbFile' :'/data/pyarch/bm29/mx1431/1140/1d/damfilt.pdb'}"
	damin = "{fitFile:'/data/pyarch/bm29/mx1469/1774/29219/dammin.fit', firFile:'/data/pyarch/bm29/mx1469/1774/29219/dammin.fir', 'pdbFile' :'/data/pyarch/bm29/mx1431/1140/1d/dammin.pdb'}"
	nsdPlot = "/data/pyarch/bm29/mx1431/1140/1d/nsd.png"
	chi2plot ="/data/pyarch/bm29/mx1431/1140/1d/chi2_R.png"

	client.service.storeAbInitioModels( "[" + str(sampleMeasurementId) + "]",  models, str(damaver), str(damfilt), str(damin), nsdPlot, chi2plot)


