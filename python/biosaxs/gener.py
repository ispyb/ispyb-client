import traceback
import sys
from suds.client import Client
from suds.transport.http import HttpAuthenticated
import os, shutil
from contextlib import closing

from datetime import datetime
from random import randint
import time
import base64
import ConfigParser

################################
# EXPERIMENT'S GETTERS
################################		
class Experiment:
    def __init__( self, experiment ):
        self.experiment = experiment

	
    def getMeasurementById( self, measurementId ):
    	measurements = self.getMeasurements()
    	for measurement in measurements:
    		if measurement.measurementId == measurementId:
    			return measurement


    def getSpecimens( self ):
    	return self.experiment.samples
    
    def getDataCollections( self ):
		return self.experiment.dataCollections

	
    def getSampleMeasurementIdByDataCollectionId( self, dataCollectionId ):
    	dcs = self.getDataCollections()
    	for dc in dcs:
    		if (dc.dataCollectionId == dataCollectionId):
    			for measurement in dc.measurementtodatacollection3VOs:
    				if measurement.dataCollectionOrder == 2:
    					return self.getMeasurementById(measurement.measurementId)
	return None;
	

	# Beamline Software Control Simulator. Return the sequence of measurements to do. 
	# Note: ISPyB doesn't care about the order
    def getMeasurementSequence( self ):
    	sequence = []
    	dcs = sorted(self.getDataCollections(), key=lambda dataCollection: dataCollection.dataCollectionId)
    	for dataCollection in dcs:
			measurements = sorted(dataCollection.measurementtodatacollection3VOs, key=lambda measurement: measurement.dataCollectionOrder)
			for measurement in measurements:
				if len(sequence) > 0 :
					#Checking that we are not measuring the same twice
					if (sequence[len(sequence) - 1].measurementId == measurement.measurementId):
						continue;
	 			sequence.append(measurement)
    	return sequence
		
	
    def getMeasurements( self ):
		specimens = self.getSpecimens()
		measurements = []
		if specimens is not None:
			for specimen in specimens:
				if specimen.measurements is not None:
					for measurement in specimen.measurements:
						measurements.append(measurement)
		return measurements
	
   		
  
################################
# SOAP CLIENT SUDS
################################	
class BiosaxsClient( ):
	def __init__(self, user, password, url):
		self.user = user
		self.password = password
		self.url = url

	def getClient(self):
		#URL = 'http://ispyb.embl-hamburg.de/ispyb-ejb3/ispybWS/GenericSampleChangerBiosaxsWebService?wsdl'
		#URL = 'http://localhost:8080/ispyb-ejb3/ispybWS/GenericSampleChangerBiosaxsWebService?wsdl'
		timeout = 20
		httpAuthenticatedToolsForAutoprocessingWebService = HttpAuthenticated( username = self.user, password = self.password ) 	
		print self.user
		print self.password
		return Client( self.url, transport = httpAuthenticatedToolsForAutoprocessingWebService, cache = None, timeout = timeout)
	
	def getExperimentById(self, experimentId):
		return self.getClient().service.getExperimentById( experimentId)
	
		
					    	
if __name__ == "__main__":
    config = ConfigParser.ConfigParser()
    config.read('ispyb.properties')

    ################################
    # CONNECTION PARAMETERS
    ################################
    biosaxs = BiosaxsClient( config.get('Connection', 'user'), config.get('Connection', 'password'), config.get('Connection', 'url'))

    print "Client Created"
    ################################
    # EXPERIMENTS DEFINITION
    ################################
    buffer1 = dict({
			'type': 'Buffer', 
			'backgroundId': '1', 
			'name': 'HEPES',   
			'plate': '2', 
			'row': 1, 
			'well': '9',  
			'concentration': 0.0, 
			'flow': True, 
			'transmission': 100.0, 
			'viscosity': 'high', 
			'SEUtemperature': 12.0,  
			'volume': 20,   
			'waittime': 0.0, 
			'comments': 'This is a buffer with HEPES'}) 
    buffer2 = dict({
			'type': 'Buffer', 
			'backgroundId': '2', 
			'name': 'TAPS',   
			'plate': '2', 
			'row': 2, 
			'well': '9',  
			'concentration': 0.0, 
			'flow': True, 
			'transmission': 100.0, 
			'viscosity': 'medium', 
			'SEUtemperature': 12.0,  
			'volume': 15,   
			'waittime': 0.0, 
			'comments': 'No comment here'}) 
    sample1 = dict({
			'type': 'Sample', 
			'backgroundId': '1', 
			'name': 'A',   
			'plate': '2', 
			'row': 1, 
			'well': '1',  
			'concentration': 3.2, 
			'flow': True, 
			'transmission': 100.0, 
			'viscosity': 'high', 
			'SEUtemperature': 12.0,  
			'volume': 20,  
			'waittime': 0.0, 
			'comments': 'This is a macromolecule with the buffer'}) 
    sample2 = dict({
			'type': 'Sample', 
			'backgroundId': '1', 
			'name': 'AB',   
			'plate': '2', 
			'row': 2, 
			'well': '1',  
			'concentration': 5.3, 
			'flow': True, 
			'transmission': 100.0, 
			'viscosity': 'medium', 
			'SEUtemperature': 12.0,  
			'volume': 15,  
			'waittime': 0.0, 
			'comments': 'AB on TAPS'}) 
    sample3 = dict({
			'type': 'Sample', 
			'backgroundId': '2', 
			'name': 'AB',   
			'plate': '2', 
			'row': 2, 
			'well': '1',  
			'concentration': 5.3, 
			'flow': True, 
			'transmission': 100.0, 
			'viscosity': 'medium', 
			'SEUtemperature': 12.0,  
			'volume': 15,  
			'waittime': 0.0, 
			'comments': 'AB on TAPS'}) 
   
    ################################
    # CREATING EXPERIMENT
    ################################
    experimentName = 'Experiment Test'
    storageTemperature = '12.0'
    bufferMode = 'BeforeAndAfter'
    extraFlowTime = '10'
    experimentType = 'STATIC'
    sourceFile = '/data/p12/testing/opd29/__ID__/BSA.xml'

    print("ECHO--------------------------")
    print(biosaxs.getClient().service.echo())
    print "----------------------"
    experiment = biosaxs.getClient().service.createExperiment( config.get('Proposal', 'type'), config.get('Proposal', 'number'), str([buffer1, buffer2, sample1, sample2, sample3]), storageTemperature, bufferMode, extraFlowTime, experimentType, sourceFile, experimentName )

    ################################
    # RETRIEVING THE EXPERIMENT
    ################################
    print str(experiment)
    experimentId = experiment.experimentId
    experiment = Experiment(biosaxs.getExperimentById(experimentId))

    ################################
    # COLLECTING
    ################################
	
    run = 0 # this run number is just to link with the appropiate 1d files
    optimizedBuffers = 1 # The same, this is only to link files
    susbtractionId=[27098,27101,27104]

    for measurement in experiment.getMeasurementSequence():
				
				run=run + 1
				################################
				# ADD RUN
				################################
				exposureTemperature = experiment.getSampleMeasurementIdByDataCollectionId(measurement.dataCollectionId).exposureTemperature
				storageTemperature = randint(4,60)
				timePerFrame = 10
				timeStart = datetime.now()
				timeEnd = datetime.now()
				energy = randint(1,1000)
				detectorDistance =  randint(1,1000)
				snapshotCapillary =  randint(1,1000)
				currentMachine =  randint(1,1000)
				beamCenterX =  randint(1,1000)
				beamCenterY =  randint(1,1000)
				radiationRelative =  randint(1,1000)
				radiationAbsolute =  randint(1,1000)
				pixelSizeX =  randint(1,1000)
				pixelSizeY =  randint(1,1000)
				normalization =  randint(1,1000)
				transmission =  randint(1,1000)

				biosaxs.getClient().service.addRun(
						measurement.measurementId,
						experimentId,
						exposureTemperature,
						storageTemperature,
						timePerFrame,
						timeStart,
						timeEnd,
						energy,
						detectorDistance,
						snapshotCapillary,
						currentMachine,
						beamCenterX,
						beamCenterY,
						radiationRelative,
						radiationAbsolute,
						pixelSizeX,
						pixelSizeY,
						normalization,
						transmission)

				################################
				# ADD MERGE
				################################
				framesAverage = 8
				framesMerged = randint(1,5) + 3
				oneDimensionalDataFiles = ""
				oneDimensionalDataFilesFolder = (config.get('Experiment', 'oneDimensionalDataFilesFolder')) + "/1d" 
				for i in range(1, framesAverage):
				    oneDimensionalDataFiles = oneDimensionalDataFiles + ("%s/MG386sh_00%s_0000%s.dat," % (oneDimensionalDataFilesFolder, run, i))
				
				oneDimensionalDataFiles = oneDimensionalDataFiles + ("%s/MG386sh_00%s_ave.dat" % (oneDimensionalDataFilesFolder, run))
				
				discardedFrames = None
				biosaxs.getClient().service.addMerge(measurement.measurementId, framesAverage, framesMerged, oneDimensionalDataFiles, discardedFrames)

				################################
				# ADD SUBTRACTION
				################################
				#Last item of the data collection
				if (measurement.dataCollectionOrder == 3):
					optimizedBuffers = optimizedBuffers + 1
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

					aux = (int(run) - int(optimizedBuffers)) + 1
					print aux
					scatteringFilePath = ("%s/MG386sh_00%s_sub-scattering.png" % (oneDimensionalDataFilesFolder, aux))
					guinierFilePath = ("%s/MG386sh_00%s_sub-Guinier.png" % (oneDimensionalDataFilesFolder, aux))
					kratkyFilePath = ("%s/MG386sh_00%s_sub-Kratky" % (oneDimensionalDataFilesFolder, aux))
					densityPlot = ("%s/MG386sh_00%s_sub-density.png" % (oneDimensionalDataFilesFolder, aux))

					print densityPlot
					biosaxs.getClient().service.addSubtraction(measurement.measurementId,
												rg,
												rgStdev,
												i0,
												i0Stdev,
												firstPointUsed,
												lastPointUsed,
												quality,
												isagregated,
												concentration,
												gnomFile,
												rgGuinier,
												rgGnom,
												dmax,
												total,
												volume,
												framesAverage,
												framesMerged,
												oneDimensionalDataFiles,
												bestBufferFilePath,
												scatteringFilePath,
												guinierFilePath,
												kratkyFilePath,
												densityPlot)


					################################
					# ADD ABINITIO
					################################
					models = config.get('Experiment', 'models') 

					folder = (config.get('Experiment', 'oneDimensionalDataFilesFolder')) + "/" + str(susbtractionId[optimizedBuffers-2])
					damaver = "{'pdbFile' :'%s/damaver.pdb'}" % folder
					damfilt = "{'pdbFile' :'%s/damfilt.pdb'}" % folder
					damin = "{'pdbFile' :'%s/dammin.pdb', 'firFile':'%s/dammin.fir','fitFile':'%s/dammin.fit'}" % (folder,folder,folder)
					nsdPlot = folder + "/nsd.png"
					chi2plot = folder+ "/chi2_R.png"

					biosaxs.getClient().service.addAbInitio( "[" + str(measurement.measurementId) + "]",  models, str(damaver), str(damfilt), str(damin), nsdPlot, chi2plot) 

					################################
					# SET STATUS TO FINISHED
					################################
					biosaxs.getClient().service.updateStatus(experimentId, "FINISHED")   
