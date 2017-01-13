import traceback
import sys
from suds.client import Client
from suds.transport.http import HttpAuthenticated
import os, shutil
from contextlib import closing
from array import *
from datetime import datetime
from random import randint
import time
import json 
import ConfigParser
import logging


################################
# SOAP CLIENT SUDS
################################
class BiosaxsClient( ):
	def __init__(self, user, password, url):
		self.user = user
		self.password = password
		self.url = url

	def getClient(self):
		timeout = 20
		httpAuthenticatedToolsForAutoprocessingWebService = HttpAuthenticated( username = self.user, password = self.password )
		return Client( self.url, transport = httpAuthenticatedToolsForAutoprocessingWebService, cache = None, timeout = timeout )

	def getExperimentById(self, experimentId):
		return self.getClient().service.getExperimentById( experimentId)

def createSample( biosaxs, experimentId, runNumber, sampleType, plate, row, well, name, bufferName):
    concentration = 0
    SEUtemperature = 20
    viscosity = 'LOW'
    volume = 40
    volumeToLoad = 40
    waitTime = 0
    transmission = 100
    comments = ""
    biosaxs.getClient().service.appendMeasurementToExperiment(
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

if __name__ == "__main__":
    config = ConfigParser.ConfigParser()
    config.read('ispyb.properties')

    print '------------------------------'
    print 'Argument List:', str(sys.argv)

    if len(sys.argv) < 2:
       print "[ERROR] Expecting 1 argument [ExperimentName]"
       sys.exit()

    ################################
    # CONNECTION PARAMETERS
    ################################
    biosaxs = BiosaxsClient( config.get('Connection', 'user'), config.get('Connection', 'password'), config.get('Connection', 'url'))

    ################################
    # CREATING EXPERIMENT
    ################################
    experimentName = sys.argv[1]

    experiment = json.loads(biosaxs.getClient().service.createEmptyExperiment( config.get('Proposal', 'type'), config.get('Proposal', 'number'), experimentName ))
    

    ################################
    # RETRIEVING THE EXPERIMENT
    ################################
    print experiment
    experimentId = experiment['experimentId']
    print "Experiment Id: %s" % experimentId

    runNumbersId = []
    ################################
    # APPEND MEASUREMENTS
    ################################
    runNumber = "001"
    createSample(biosaxs, experimentId, runNumber, "BUFFER", 2,1,9,None, "HEPES")
    runNumbersId.append(runNumber)
    runNumber = "002"
    createSample(biosaxs, experimentId, runNumber, "SAMPLE", 2,1,1,"M", "HEPES")
    runNumbersId.append(runNumber)
    runNumber = "003"
    createSample(biosaxs, experimentId, runNumber, "BUFFER", 2,1,9,None, "HEPES")
    runNumbersId.append(runNumber)
    print "RunNumbers: %s" %  runNumbersId


    ################################
    # ADD RUN
    ################################
    for runNumber in runNumbersId:
        biosaxs.getClient().service.addRun(experimentId, runNumber)

    ################################
    # ADD AVERAGED
    ################################
    run = 0
    for runNumber in runNumbersId:
        frames = []
        run=run + 1
        oneDimensionalDataFiles = ""
        oneDimensionalDataFilesFolder = (config.get('Experiment', 'oneDimensionalDataFilesFolder')) + "/1d"
        framesCount = 8
        framesAveraged = randint(1,5) + 3
        for i in range(1, framesCount):
            filePath =  ('%s/MG386sh_00%s_0000%s.dat') % (oneDimensionalDataFilesFolder, run, i)
            print filePath
            frames.append({'filePath':filePath})
        print str(frames)
        biosaxs.getClient().service.addAveraged(experimentId, runNumber, str(frames), "[]", "/data/file_ave.dat")
    
    ################################
    # ADD SUBTRACTION
    ################################


    rg = randint(1,1000)
    rgStdev =  randint(1,1000)
    i0 =  randint(1,1000)
    i0Stdev =  randint(1,1000)
    concentration = 5.5
    firstPointUsed =  randint(1,100)
    lastPointUsed =  randint(200,1000)
    quality =  randint(1,100)
    isagregated =  False

    rgGnom=  randint(1,100)
    dmax=  randint(1,100)
    total=  randint(1,100)
    volume= randint(1,100)
    aux = 2
    print aux
    experimentalDataPlotFilePath = ("%s/MG386sh_00%s_sub-scattering.png" % (oneDimensionalDataFilesFolder, aux))
    guinierPlotFilePath = ("%s/MG386sh_00%s_sub-Guinier.png" % (oneDimensionalDataFilesFolder, aux))
    kratkyPlotFilePath = ("%s/MG386sh_00%s_sub-Kratky.png" % (oneDimensionalDataFilesFolder, aux))
    #Density Plot = p(r) plot
    densityPlotFilePath = ("%s/MG386sh_00%s_sub-density.png" % (oneDimensionalDataFilesFolder, aux))
    sampleAverageFilePath = ("%s/MG386sh_00%s_ave.dat" % (oneDimensionalDataFilesFolder, aux))
    bufferAverageFilePath =  config.get('Experiment', 'bestBufferFilePath')
    subtractedFilePath = ("%s/MG386sh_00%s_sub.dat" % (oneDimensionalDataFilesFolder, aux))
    gnomOutputFilePath = ("%s/MG386sh_00%s_sub.out" % (oneDimensionalDataFilesFolder, aux))
    
    # File path array which files used to average the sample's frames and get as result sampleAverageFilePath
    sampleAvgOneDimensionalFiles = ["sample1.dat", "sample4.dat"]
    # File path array which files used to average the buffer's frame and get as result bufferAverageFilePath
    bufferAvgOneDimensionalFiles = ["buffer5.dat", "buff8.dat", "buffer7"]
    

    biosaxs.getClient().service.addSubtraction(experimentId, str(runNumbersId),
												rg,
												rgStdev,
												i0,
												i0Stdev,
												firstPointUsed,
												lastPointUsed,
												quality,
												isagregated,
												concentration,
												rgGnom,
												dmax,
												total,
												volume,
												sampleAvgOneDimensionalFiles,
                                                                                                bufferAvgOneDimensionalFiles,
												sampleAverageFilePath,
												bufferAverageFilePath,
												subtractedFilePath,
												experimentalDataPlotFilePath,
												densityPlotFilePath,
												guinierPlotFilePath,
												kratkyPlotFilePath,
                                                                                                gnomOutputFilePath)


