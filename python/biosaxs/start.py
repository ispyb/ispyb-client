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
	config.read('ispyb.properties')

	proposal = str(config.get('Connection', 'user'))
	password = str(config.get('Connection', 'password'))
	url = str(config.get('Connection', 'url'))

	myLogger.printConfiguration(config)
	

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
	proposalType = 'opd'
	proposalNumber = '29'
	storageTemperature = '12.0'
	bufferMode = 'BeforeAndAfter'
	extraFlowTime = '10'
	experimentType = 'STATIC'
	sourceFile = '/data/pyarch/bm29/testing/opd29/__ID__/BSA.xml'
	name = 'My Static Experiment'
	samples = "[{'plate': '2', 'enable': True, 'macromolecule': '', 'title': 'P2-4:10', 'transmission': 100.0, 'well': '10', 'recuperate': False, 'SEUtemperature': 4.0, 'viscosity': 'Low', 'flow': False, 'comments': 'water buffer', 'volume': 10, 'buffername': 'bwater', 'code': 'water', 'typen': 0, 'waittime': 0.0, 'concentration': 0.0, 'type': 'Buffer', 'row': 4}, {'plate': '2', 'enable': True, 'macromolecule': 'WATER', 'buffer': '', 'flow': True, 'recuperate': False, 'viscosity': 'Low', 'typen': 1, 'volume': 50, 'buffername': 'bwater', 'code': 'water', 'concentration': 1.0, 'row': 4, 'waittime': 0.0, 'title': 'P2-4:11', 'transmission': 100.0, 'SEUtemperature': 20.0, 'well': '11', 'comments': '[1] water', 'type': 'Sample'}]"
	
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
		edfFileArray = "['/data/bm29/inhouse/opd29/20160129/raw/empty_007_00001.dat', '/data/bm29/inhouse/opd29/20160129/raw/empty_007_00002.dat', '/data/bm29/inhouse/opd29/20160129/raw/empty_007_00003.dat', '/data/bm29/inhouse/opd29/20160129/raw/empty_007_00004.dat', '/data/bm29/inhouse/opd29/20160129/raw/empty_007_00005.dat', '/data/bm29/inhouse/opd29/20160129/raw/empty_007_00006.dat', '/data/bm29/inhouse/opd29/20160129/raw/empty_007_00007.dat', '/data/bm29/inhouse/opd29/20160129/raw/empty_007_00008.dat', '/data/bm29/inhouse/opd29/20160129/raw/empty_007_00009.dat', '/data/bm29/inhouse/opd29/20160129/raw/empty_007_00010.dat']"
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

	# Add subtraction
	oneDimensionalDataFilesFolder = (config.get('Experiment', 'oneDimensionalDataFilesFolder')) 

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
	bestBufferFilePath =  config.get('Experiment', 'bestBufferFilePath')

	# aux = (int(run) - int(optimizedBuffers)) + 1
	aux = 1
	print aux
	scatteringFilePath = ("%s/MG386sh_00%s_sub-scattering.png" % (oneDimensionalDataFilesFolder, aux))
	guinierFilePath = ("%s/MG386sh_00%s_sub-Guinier.png" % (oneDimensionalDataFilesFolder, aux))
	kratkyFilePath = ("%s/MG386sh_00%s_sub-Kratky" % (oneDimensionalDataFilesFolder, aux))
	densityPlot = ("%s/MG386sh_00%s_sub-density.png" % (oneDimensionalDataFilesFolder, aux))

	sampleOneDimensionalFiles = "[{'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_006_00001.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_006_00002.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_006_00003.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_006_00004.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_006_00005.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_006_00006.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_006_00007.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_006_00008.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_006_00009.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_006_00010.dat'}]"

	bufferOneDimensionalFiles = "[{'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_007_00001.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_007_00002.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_007_00003.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_007_00004.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_007_00005.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_007_00006.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_007_00007.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_007_00008.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_007_00009.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_007_00010.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00001.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00002.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00003.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00004.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00005.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00006.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00007.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00008.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00009.dat'}, {'filePath': '/data/pyarch/bm29/testing/opd29/9937/1d/empty_005_00010.dat'}]"

	sampleAverageFilePath = '/data/pyarch/bm29/testing/opd29/9937/1d/empty_006_ave.dat'
	bufferAverageFilePath = '/data/pyarch/bm29/testing/opd29/9937/1d/empty_006_ave_averbuffer.dat'

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
					scatteringFilePath,
					guinierFilePath,
					kratkyFilePath,
					densityPlot)


	# Adding abinitio
	models = "[{pdbFile: '/data/../dammif1.pdb', rg: '1.23', dMax: '232', I0: '12121'},{pdbFile: '/data/../dammif2.pdb', rg: '2.23', dMax: '232', I0: '12121'}]"
	folder = (config.get('Experiment', 'oneDimensionalDataFilesFolder')) + "/"
	damaver = "{'pdbFile' :'/data/pyarch/bm29/mx1431/1140/1d/damaver.pdb'}" 
	damfilt = "{'pdbFile' :'/data/pyarch/bm29/mx1431/1140/1d/damfilt.pdb'}"
	damin = "{fitFile:'/data/pyarch/bm29/mx1469/1774/29219/dammin.fit', firFile:'/data/pyarch/bm29/mx1469/1774/29219/dammin.fir', 'pdbFile' :'/data/pyarch/bm29/mx1431/1140/1d/dammin.pdb'}"
	nsdPlot = "/data/pyarch/bm29/mx1431/1140/1d/nsd.png"
	chi2plot ="/data/pyarch/bm29/mx1431/1140/1d/chi2_R.png"

	client.service.storeAbInitioModels( "[" + str(sampleMeasurementId) + "]",  models, str(damaver), str(damfilt), str(damin), nsdPlot, chi2plot)


