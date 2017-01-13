import traceback
import sys
from suds.client import Client
from suds.transport.http import HttpAuthenticated
import os, shutil
from contextlib import closing
import zipfile
from datetime import datetime
from random import randint
import time
import ConfigParser

################################
# TESTS
################################
class Test():
	#Any buffer will be optimized beacuse of SEU Temp, It creates an experiment with 6 measurements [B,S,B,S,B]
	def test1(self):
		samples = "["
		samples = samples + "{'plate': '2', 'row': 1, 'well': '9',  'macromolecule': 'Buffer-1',   'buffername': '1', 'type': 'Buffer', 'concentration': 0.0, 'flow': True, 'transmission': 100.0, 'viscosity': 'high', 'SEUtemperature': 12.0,  'volume': 40,  'code': 'bsa', 'waittime': 0.0, 'comments': 'bsa buffer'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '9',  'macromolecule': 'Buffer-2',   'buffername': '2', 'type': 'Buffer', 'concentration': 0.0, 'flow': True, 'transmission': 80.0, 'viscosity': 'Low', 'SEUtemperature': 15.0,  'volume': 50,  'code': 'bsa', 'waittime': 0.0, 'comments': 'bsa buffer'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '1',  'macromolecule': 'BSA', 'buffername': '1', 'type': 'Sample', 'concentration': 4.5, 'flow': True, 'transmission': 100.0, 'viscosity': 'high', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '2',  'macromolecule': 'BSA', 'buffername': '2', 'type': 'Sample', 'concentration': 2,   'flow': True, 'transmission': 80.0, 'viscosity': 'Low', 'SEUtemperature': 15.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'}"
		return samples + "]"
	
	#A buffer will be optimized, It creates an experiment with 6 measurements [B,S,B,B,S,B]
	def test2(self):
		
		samples = "["
		samples = samples + "{'plate': '2', 'row': 1, 'well': '9',  'macromolecule': 'Buffer',   'buffername': '1', 		'type': 'Buffer', 'concentration': 0.0,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 4.0,  'volume': 50,  'code': 'bsa', 'waittime': 0.0, 'comments': 'bsa buffer'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '1',  'macromolecule': 'BSA', 	 'buffername': '1', 		'type': 'Sample', 'concentration': 4.5,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '2',  'macromolecule': 'BSA', 	 'buffername': '1', 		'type': 'Sample', 'concentration': 2,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'}"
		return samples + "]"
	
	def test3(self):
		
		samples = "["
		samples = samples + "{'plate': '2', 'row': 1, 'well': '9',  'macromolecule': '',   		'buffername': 'BUFFER', 		'type': 'Buffer', 'concentration': 0.0,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 4.0,  'volume': 50,  'code': 'bsa', 'waittime': 0.0, 'comments': 'bsa buffer'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '1',  'macromolecule': 'BSA', 	 'buffername': 'BUFFER', 		'type': 'Sample', 'concentration': 4.5,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '2',  'macromolecule': 'BSA', 	 'buffername': 'BUFFER', 		'type': 'Sample', 'concentration': 2,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'}"
		return samples + "]"
	
	def test4(self):
		samples = "["
		samples = samples + "{'plate': '2', 'row': 1, 'well': '9',  'macromolecule': 'Buffer',   'buffername': '1', 		'type': 'Buffer', 'concentration': 0.0,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 4.0,  'volume': 50,  'code': 'bsa', 'waittime': 0.0, 'comments': 'bsa buffer'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '10', 'macromolecule': 'Buffer',   'buffername': '2', 		'type': 'Buffer', 'concentration': 0.0,		'flow': True, 'transmission': 80.0, 'viscosity': 'Low', 'SEUtemperature': 4.0,  'volume': 50,  'code': 'bsa', 'waittime': 0.0, 'comments': 'bsa buffer'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '1',  'macromolecule': 'BSA', 	 'buffername': '1', 		'type': 'Sample', 'concentration': 4.5,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '2',  'macromolecule': 'BSA', 	 'buffername': '1', 		'type': 'Sample', 'concentration': 2,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '2',  'macromolecule': 'BSA', 	 'buffername': '2', 		'type': 'Sample', 'concentration': 2,		'flow': True, 'transmission': 80.0, 'viscosity': 'Low', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '2',  'macromolecule': 'BSA', 	 'buffername': '2', 		'type': 'Sample', 'concentration': 2,		'flow': True, 'transmission': 60.0, 'viscosity': 'Low', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '2',  'macromolecule': 'BSA', 	 'buffername': '1', 		'type': 'Sample', 'concentration': 1,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'}"
		return samples + "]"
	
	def test5(self):
		samples = "["
		samples = samples + "{'plate': '2', 'row': 1, 'well': '9',  'macromolecule': 'Buffer',   'buffername': '1', 		'type': 'Buffer', 'concentration': 0.0,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 4.0,  'volume': 50,  'code': 'bsa', 'waittime': 0.0, 'comments': 'bsa buffer'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '1',  'macromolecule': 'BSA', 	 'buffername': '1', 		'type': 'Sample', 'concentration': 4.5,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '1',  'macromolecule': 'BSA', 	 'buffername': '1', 		'type': 'Sample', 'concentration': 4.5,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 14.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '1',  'macromolecule': 'BSA', 	 'buffername': '1', 		'type': 'Sample', 'concentration': 4.5,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 15.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '1',  'macromolecule': 'BSA', 	 'buffername': '1', 		'type': 'Sample', 'concentration': 4.5,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 18.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'}"
		return samples + "]"
	
	def test6(self):
		samples = "["
		samples = samples + "{'plate': '2', 'row': 1, 'well': '9',  'macromolecule': 'Buffer',   'buffername': '1', 		'type': 'Buffer', 'concentration': 0.0,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 4.0,  'volume': 50,  'code': 'bsa', 'waittime': 0.0, 'comments': 'bsa buffer'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '1',  'macromolecule': 'BSA', 	 'buffername': '1', 		'type': 'Sample', 'concentration': 0.20,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '2',  'macromolecule': 'BSA', 	 'buffername': '1', 		'type': 'Sample', 'concentration': 0.40,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '3',  'macromolecule': 'BSA', 	 'buffername': '1', 		'type': 'Sample', 'concentration': 0.5,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '4',  'macromolecule': 'BSA', 	 'buffername': '1', 		'type': 'Sample', 'concentration': 0.6,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'}"
		return samples + "]"
	
	def test7(self):
		samples = "["
		samples = samples + "{'plate': '2', 'row': 1, 'well': '9',  'macromolecule': 'Buffer-1',   'buffername': '1', 		'type': 'Buffer', 'concentration': 0.0,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 4.0,  'volume': 50,  'code': 'bsa', 'waittime': 0.0, 'comments': 'bsa buffer'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '1',  'macromolecule': 'Buffer-2',   'buffername': '2', 		'type': 'Buffer', 'concentration': 0.20,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '2',  'macromolecule': 'BSA', 	   'buffername': '1', 		'type': 'Sample', 'concentration': 0.40,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '3',  'macromolecule': 'BSA', 	   'buffername': '2', 		'type': 'Sample', 'concentration': 0.5,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'}"
		return samples + "]"
	
	def test8(self):
		samples = "["
		samples = samples + "{'plate': '2', 'row': 1, 'well': '9',  'macromolecule': 'Buffer-1',   'buffername': '1', 		'type': 'Buffer', 'concentration': 0.0,		'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 4.0,  'volume': 50,  'code': 'bsa', 'waittime': 0.0, 'comments': 'bsa buffer'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '2',  'macromolecule': 'BSA', 	   'buffername': '1', 		'type': 'Sample', 'concentration': 0.50,	'flow': True, 'transmission': 100.0, 'viscosity': 'Low', 'SEUtemperature': 12.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'},"
		samples = samples + "{'plate': '2', 'row': 1, 'well': '3',  'macromolecule': 'BSA', 	   'buffername': '1', 		'type': 'Sample', 'concentration': 0.5,		'flow': True, 'transmission': 100.0, 'viscosity': 'high', 'SEUtemperature': 14.0, 'volume': 50,   'code': 'bsa', 'waittime': 0.0, 'comments': '[1] bsa'}"
		return samples + "]"

################################
# SOAP CLIENT SUDS
################################	
class BiosaxsClient( ):

	def __init__(self, user, password, url):
		self.user = user
		self.password = password
		self.url = url
                self.runNumber = 1

	def getClient(self):
		timeout = 20
		httpAuthenticatedToolsForAutoprocessingWebService = HttpAuthenticated( username = self.user, password = self.password ) 	
		return Client( self.url, transport = httpAuthenticatedToolsForAutoprocessingWebService, cache = None, timeout = timeout )
	
	
		
	## Create a new experiment
	def createExperiment(self, samples, name):
		proposalType = 'opd'
		proposalNumber = '29'
		storageTemperature = '12.0'
		bufferMode = 'BeforeAndAfter'
		extraFlowTime = '10'
		experimentType = 'STATIC'
		sourceFile = '/data/pyarch/bm29/testing/opd29/__ID__/BSA.xml'
		return self.getClient().service.createExperiment( proposalType, proposalNumber, samples, storageTemperature, bufferMode, extraFlowTime, experimentType, sourceFile, name )
		
	## Get an experiment
	def getExperimentById(self, experimentId):
		return self.getClient().service.getExperimentById( experimentId)

		

	def simulateFrameCollection(self, experimentId, measurementId, order, sampleCode):
		##order,
		mode = "sample"
		if (order == 1):
			mode = "before"
		if (order == 3):
			mode = "after"
			
		exposureTemperature = randint(4,60)
		storageTemperature = randint(4,60)
		timePerFrame = randint(1,10)
		timeStart = datetime.now()
		timeEnd = datetime.now()
		energy = randint(1,1000)
		detectorDistance =  randint(1,1000)
		edfFileArray = "['/data/file1.dat', '/data/file2.dat']"
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
		self.getClient().service.saveFrame(
										mode,
										experimentId,
										measurementId,
										sampleCode,
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

	def simulateModelAnalysis(self,  measurementId):
		self.id = 'id'
		models = "[{pdbFile: '/data/../dammif1.pdb', rg: '1.23', dMax: '232', I0: '12121'}, {pdbFile: '/data/../dammif2.pdb', rg: '2.23', dMax: '232', I0: '12121'}]"
		dammaver = "{pdbFile: '/data/../dammaver" + str(measurementId) + ".pdb', rg: '1.23', dMax: '232', I0: '12121'}"
		dammif = "{pdbFile: '/data/../dammif" + str(measurementId) + ".pdb', rg: '1.23', dMax: '232', I0: '12121'}"
		damming = "{pdbFile: '/data/../dammin"+ str(measurementId) + ".pdb', rg: '1.23', dMax: '232', I0: '12121'}"
		nsdPlot = '/data/../nsd.png'
		chi2plot = '/data/../chi2plot.png'
		self.getClient().service.storeAbInitioModels( "[" + str(measurementId) + "]",  models, dammaver, dammif, damming, nsdPlot, chi2plot)  
																							
	def simulate1DCurveAnalysis(self, experimentId, measurementId, order, sampleCode):
		filename = 'fileName'
		code = "code"
		concentration =  randint(1,100)
		framesAverage =  randint(20,20)
		framesMerged =  randint(1,20)
		curveParam =   "/data/pyarch/bm29/opd29/600/1d/" + str(measurementId) +"_00001.dat, /data/pyarch/bm29/opd29/600/1d/" + str(measurementId) +"_00002.dat, /data/pyarch/bm29/opd29/600/1d/" + str(measurementId) +"_00003.dat, /data/pyarch/bm29/opd29/600/1d/" + str(measurementId) +"_ave.dat"
		dataCollectionOrderParam = order
		
		rg = None
		rgStdev =  None
		i0 =  None
		i0Stdev =  None
		firstPointUsed =  None
		lastPointUsed =  None
		quality =  None
		isagregated =  None
		rgGuinier=  None
		rgGnom=  None
		dmax= None
		total=  None
		volume=  None
		gnomFile = None
		bestBufferFilePath =  None
		scatteringFilePath = None
		guinierFilePath = None
		kratkyFilePath = None
		densityPlot = None
			
		#Only when last measure has been done is when it get results
		if (order == 3):
			rg =  randint(1,1000)
			rgStdev =  randint(1,1000)
			i0 =  randint(1,1000)
			i0Stdev =  randint(1,1000)
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
			bestBufferFilePath =  "/data/averageBuffer.dat"
			scatteringFilePath = "/data/scattering.png"
			guinierFilePath = "/data/guinier.png"
			kratkyFilePath = "/data/krakty.png"
			densityPlot = "/data/density.png"
		
		self.getClient().service.storeDataAnalysisResultByMeasurementId(
			measurementId,
			filename,
			rg,
			rgStdev,
			i0,
			i0Stdev,
			firstPointUsed,
			lastPointUsed,
			quality,
			isagregated,
			code,
			concentration,
			gnomFile,
			rgGuinier,
			rgGnom,
			dmax,
			total,
			volume,
			framesAverage,
			framesMerged,
			curveParam,
			dataCollectionOrderParam - 1,
			bestBufferFilePath,
			scatteringFilePath,
			guinierFilePath,
			kratkyFilePath,
			densityPlot)
		
	def play(self, jsonSamples, title):
			print title
			experiment =  biosaxs.createExperiment(jsonSamples, title)
			print str(experiment)
			experimentId = experiment.experimentId
			experiment = Experiment(self.getExperimentById(experimentId))
			for measurement in experiment.getMeasurementSequence():
				print "\t\tMeasuring: " + str(experiment.getMeasurementById(measurement.measurementId).comment + " " +  str(experiment.getMeasurementById(measurement.measurementId).measurementId))
				print "\t\t\t Data Acquisition"
				self.runNumber = self.runNumber + 1
				print(str(experiment.getMeasurementById(measurement.measurementId)))
				biosaxs.simulateFrameCollection(experimentId, experiment.getSampleMeasurementIdByDataCollectionId(measurement.dataCollectionId).measurementId, measurement.dataCollectionOrder, self.runNumber)
				print "\t\t\t Analysis"
				biosaxs.simulate1DCurveAnalysis(experimentId, experiment.getSampleMeasurementIdByDataCollectionId(measurement.dataCollectionId).measurementId, measurement.dataCollectionOrder, self.runNumber)
				if (measurement.dataCollectionOrder == 3):
				 	print "\t\t\t Abinitio Models"
				 	biosaxs.simulateModelAnalysis(experiment.getSampleMeasurementIdByDataCollectionId(measurement.dataCollectionId).measurementId)
				time.sleep(1)
		    	
		    	
################################
# EXPERIMENT CLASS (OPTIONAL)
################################
class Experiment:
    def __init__( self, experiment ):
        self.experiment = experiment

	
    def getMeasurementById( self, measurementId ):
    	measurements = self.getMeasurements()
    	for measurement in measurements:
    		if measurement.measurementId == measurementId:
    			return measurement


	
    def getBuffers( self ):
    	if hasattr(self.experiment, 'buffer3VOs'):
        	return self.experiment.buffer3VOs
		return None

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
	
    		
    def getPlates( self ):
        return self.experiment.samplePlate3VOs

    def getPlateGroups( self ):
        plates = self.getPlates()
        dict = {}
        plateGroups = []
        for plate in plates:
            if hasattr( plate, 'plategroup3VO' ):
                if not dict.has_key( plate.plategroup3VO.name ):
                    if plate.plategroup3VO is not None:
                        plateGroups.append( plate.plategroup3VO )
                    dict[plate.plategroup3VO.name] = True
        return plateGroups



if __name__ == "__main__":
    config = ConfigParser.ConfigParser()
    config.read('ispyb.properties')

    ################################
    # CONNECTION PARAMETERS
    ################################
    biosaxs = BiosaxsClient( config.get('Connection', 'user'), config.get('Connection', 'password'), config.get('Connection', 'url'))
    print(str(config.get('Connection', 'user')))
    print(str(config.get('Connection', 'password')))
    print(str(config.get('Connection', 'url')))
    biosaxs.play(Test().test1(), "TEST1:  Any buffer will be optimized beacuse of SEU Temp")
    biosaxs.play(Test().test2(), "TEST2:  A buffer will be optimized")
    biosaxs.play(Test().test3(), "TEST3:   Backward compatibility")
    biosaxs.play(Test().test4(), "TEST4:  3 samples 2 buffers")
    biosaxs.play(Test().test5(), "TEST5:  Temperature Series")
    biosaxs.play(Test().test6(), "TEST6:  Concentration Series")
    biosaxs.play(Test().test7(), "TEST7:  Two buffers Concentration Series")
    biosaxs.play(Test().test8(), "TEST8:  Temperature Series same buffer")
