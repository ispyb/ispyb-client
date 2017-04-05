package webservice.ispyb.autoproc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import generated.ws.mx.autoproc.ImageQualityIndicatorsWS3VO;
import generated.ws.mx.autoproc.IspybWS;
import generated.ws.mx.autoproc.ModelBuildingWS3VO;
import generated.ws.mx.autoproc.PhasingAnalysis3VO;
import generated.ws.mx.autoproc.PhasingData;
import generated.ws.mx.autoproc.PhasingHasScalingWS3VO;
import generated.ws.mx.autoproc.PhasingProgramAttachmentWS3VO;
import generated.ws.mx.autoproc.PhasingProgramRun3VO;
import generated.ws.mx.autoproc.PhasingStatisticsWS3VO;
import generated.ws.mx.autoproc.PhasingWS3VO;
import generated.ws.mx.autoproc.PreparePhasingDataWS3VO;
import generated.ws.mx.autoproc.SubstructureDeterminationWS3VO;
import generated.ws.mx.autoproc.ToolsForAutoprocessingWebService;
import webservice.UtilsDoNotCommit;

public class TestAutoprocessingToolsWebService {


	protected static generated.ws.mx.autoproc.IspybWS iws;
	protected static ToolsForAutoprocessingWebService ws;

	private static void initWebService() throws Exception {
		
		iws = new IspybWS();	

		System.out.println("-----------------------------------------------------------");

		ws=iws.getToolsForAutoprocessingWebServicePort();
		BindingProvider bindingProvider = (BindingProvider)ws;
		Map requestContext = bindingProvider.getRequestContext();
		
		requestContext.put(BindingProvider.USERNAME_PROPERTY, UtilsDoNotCommit.ISPYBU);
		requestContext.put(BindingProvider.PASSWORD_PROPERTY, UtilsDoNotCommit.ISPYBP);

		//ws.setTimeout(3 * 1000);// 15

	}

	public static void main(String args[]) {
		try {
			System.out.println("*************** testAutoprocessingWebServices ***************");
			initWebService();

			 testStoreOrUpdateAutoProc();
			 testStoreOrUpdateAutoProcProgram();
			 testStoreOrUpdateAutoProcScaling();
			 testStoreOrUpdateAutoProcIntegration();
			 testStoreOrUpdateAutoProcScalingHasInt();
			 testStoreOrUpdateAutoProcScalingStatistics();
			 
			 testStoreOrUpdateAutoProcProgramAttachment();

			 testStoreImageQualityIndicators();
			 testStoreOrUpdateAutoProcStatus();
			 testStoreOrUpdatePhasingProgramRun();
			 testStoreOrUpdatePhasingAnalysis();
			 testStoreOrUpdatePreparePhasingData();
			 testStoreOrUpdateSubstructureDetermination();
			 testStoreOrUpdatePhasing();
			 testStoreOrUpdateModelBuilding();
			 testStoreOrUpdatePhasingProgramAttachment();
			 testStoreOrUpdatePhasingHasScaling();
			 testStoreOrUpdatePhasingStatistics();
			 testGetSpaceGroupId();
			 testGetAutoProcScalingIdList();
			 testStoreOrUpdateImageQualityIndicators();
			 testStoreOrUpdateImageQualityIndicatorsForFileName();
			 testStoreOrUpdateImageQualityIndicatorsForFileNames();
			 testStorePhasingData();

		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}
	}

	private static void testStoreOrUpdateAutoProc() throws Exception {
		System.out.println("*************** testStoreOrUpdateAutoProc ***************");
		Integer ret = -1;

		Integer autoProcId = null;
		Integer autoProcProgramId = 0;
		String spaceGroup = "spaceGroup";
		Float refinedCellA = (float) 1;
		Float refinedCellB = (float) 2;
		Float refinedCellC = (float) 3;
		Float refinedCellAlpha = (float) 4;
		Float refinedCellBeta = (float) 5;
		Float refinedCellGamma = (float) 6;
		Calendar recordTimeStamp = Calendar.getInstance();

		ret = ws.storeOrUpdateAutoProc(autoProcId, autoProcProgramId, spaceGroup, refinedCellA, refinedCellB,
				refinedCellC, refinedCellAlpha, refinedCellBeta, refinedCellGamma, recordTimeStamp);
		System.out.println("This is what I got as a response : autoProcId  = " + ret + "  \n");
	}

	private static void testStoreOrUpdateAutoProcScaling() throws Exception {
		System.out.println("*************** testStoreOrUpdateAutoProcScaling ***************");
		Integer ret = -1;

		Integer autoProcScalingId = null;
		Integer autoProcId = 1;//125615;
		Calendar recordTimeStamp = Calendar.getInstance();

		ret = ws.storeOrUpdateAutoProcScaling(autoProcScalingId, autoProcId, recordTimeStamp);
		System.out.println("C This is what I got as a response : autoProcScalingId  = " + ret + "  \n");

		// foreign key null
		ret = ws.storeOrUpdateAutoProcScaling(autoProcScalingId, null, recordTimeStamp);
		System.out.println("F This is what I got as a response : autoProcScalingId  = " + ret + "  \n");

	}

	private static void testStoreOrUpdateAutoProcScalingHasInt() throws Exception {
		System.out.println("*************** testStoreOrUpdateAutoProcScalingHasInt ***************");
		Integer ret = -1;

		Integer autoProcScalingHasIntId = null;
		Integer autoProcIntegrationId = 1;//125638;
		Integer autoProcScalingId = 1;//125626;
		Calendar recordTimeStamp = Calendar.getInstance();

		ret = ws.storeOrUpdateAutoProcScalingHasInt(autoProcScalingHasIntId, autoProcIntegrationId, autoProcScalingId,
				recordTimeStamp);
		System.out.println("C This is what I got as a response : autoProcScalingHasIntId  = " + ret + "  \n");

		// foreign keys null
		Integer ret2 = ws.storeOrUpdateAutoProcScalingHasInt(autoProcScalingHasIntId, null, autoProcScalingId,
				recordTimeStamp);
		System.out.println("F This is what I got as a response : autoProcScalingHasIntId  = " + ret2 + "  \n");

		ret2 = ws.storeOrUpdateAutoProcScalingHasInt(autoProcScalingHasIntId, autoProcIntegrationId, null,
				recordTimeStamp);
		System.out.println("F This is what I got as a response : autoProcScalingHasIntId  = " + ret2 + "  \n");

		// update
		autoProcScalingHasIntId = ret;
		ret = ws.storeOrUpdateAutoProcScalingHasInt(autoProcScalingHasIntId, autoProcIntegrationId, autoProcScalingId,
				recordTimeStamp);
		System.out.println("This is what I got as a response : autoProcScalingHasIntId  = " + ret + "  \n");
	}

	private static void testStoreOrUpdateAutoProcScalingStatistics() throws Exception {
		System.out.println("*************** testStoreOrUpdateAutoProcScalingStatistics ***************");
		Integer ret = -1;

		Integer autoProcScalingStatisticsId = null;
		String scalingStatisticsType = "overall";
		String comments = "comments";
		Float resolutionLimitLow = (float) 0.1;
		Float resolutionLimitHigh = (float) 0.2;
		Float rmerge = (float) 0.3;
		Float rmeasWithinIplusIminus = (float) 0.4;
		Float rmeasAllIplusIminus = (float) 0.5;
		Float rpimWithinIplusIminus = (float) 0.6;
		Float rpimAllIplusIminus = (float) 0.7;
		Float fractionalPartialBias = (float) 0.8;
		Integer nTotalObservations = 0;
		Integer nTotalUniqueObservations = 0;
		Float meanIoverSigI = (float) 0.9;
		Float completeness = (float) 0.01;
		Float multiplicity = (float) 0.02;
		Float anomalousCompleteness = (float) 0.03;
		Float anomalousMultiplicity = (float) 0.04;
		Calendar recordTimeStamp = null;
		Boolean anomalous = true;
		Integer autoProcScalingId = 1;//125627;
		Float ccHalf = (float) 1.4;
		Float sigAno= (float) 1.4;
		Float  ccAno= (float) 1.4;
		Float isa= (float) 1.4;

		ret = ws.storeOrUpdateAutoProcScalingStatistics(autoProcScalingStatisticsId, scalingStatisticsType, comments,
				resolutionLimitLow, resolutionLimitHigh, rmerge, rmeasWithinIplusIminus, rmeasAllIplusIminus,
				rpimWithinIplusIminus, rpimAllIplusIminus, fractionalPartialBias, nTotalObservations,
				nTotalUniqueObservations, meanIoverSigI, completeness, multiplicity, anomalousCompleteness,
				anomalousMultiplicity, recordTimeStamp, anomalous, autoProcScalingId, ccHalf, sigAno, ccAno, isa);
		System.out.println("C This is what I got as a response : autoProcScalingStatisticId  = " + ret + "  \n");

		// foreign key null
		Integer ret2 = ws.storeOrUpdateAutoProcScalingStatistics(autoProcScalingStatisticsId, scalingStatisticsType,
				comments, resolutionLimitLow, resolutionLimitHigh, rmerge, rmeasWithinIplusIminus, rmeasAllIplusIminus,
				rpimWithinIplusIminus, rpimAllIplusIminus, fractionalPartialBias, nTotalObservations,
				nTotalUniqueObservations, meanIoverSigI, completeness, multiplicity, anomalousCompleteness,
				anomalousMultiplicity, recordTimeStamp, anomalous, null, ccHalf, sigAno, ccAno, isa);
		System.out.println("F This is what I got as a response : autoProcScalingStatisticId  = " + ret2 + "  \n");

		// update
		autoProcScalingStatisticsId = ret;
		ret = ws.storeOrUpdateAutoProcScalingStatistics(autoProcScalingStatisticsId, scalingStatisticsType, comments,
				resolutionLimitLow, resolutionLimitHigh, rmerge, rmeasWithinIplusIminus, rmeasAllIplusIminus,
				rpimWithinIplusIminus, rpimAllIplusIminus, fractionalPartialBias, nTotalObservations,
				nTotalUniqueObservations, meanIoverSigI, completeness, multiplicity, anomalousCompleteness,
				anomalousMultiplicity, recordTimeStamp, anomalous, autoProcScalingId, ccHalf, sigAno, ccAno, isa);
		System.out.println("U his is what I got as a response : autoProcScalingStatisticId  = " + ret + "  \n");
	}

	private static void testStoreOrUpdateAutoProcIntegration() throws Exception {
		System.out.println("*************** testStoreOrUpdateAutoProcIntegration ***************");
		Integer ret = -1;

		Integer autoProcIntegrationId = null;
		Integer autoProcProgramId = 1;
		Integer startImageNumber = 1;
		Integer endImageNumber = 2;
		Float refinedDetectorDistance = (float) 2.1;
		Float refinedXbeam = (float) 2.2;
		Float refinedYbeam = (float) 2.3;
		Float rotationAxisX = (float) 2.4;
		Float rotationAxisY = (float) 2.5;
		Float rotationAxisZ = (float) 2.6;
		Float beamVectorX = (float) 2.7;
		Float beamVectorY = (float) 2.8;
		Float beamVectorZ = (float) 2.9;
		Float cellA = (float) 3.1;
		Float cellB = (float) 3.2;
		Float cellC = (float) 3.3;
		Float cellAlpha = (float) 4.1;
		Float cellBeta = (float) 4.2;
		Float cellGamma = (float) 4.3;
		Calendar recordTimeStamp = Calendar.getInstance();
		Integer dataCollectionId = 1;//19570;
		Boolean anomalous = true;

		// foreign key null
		ret = ws.storeOrUpdateAutoProcIntegration(autoProcIntegrationId, null, startImageNumber, endImageNumber,
				refinedDetectorDistance, refinedXbeam, refinedYbeam, rotationAxisX, rotationAxisY, rotationAxisZ,
				beamVectorX, beamVectorY, beamVectorZ, cellA, cellB, cellC, cellAlpha, cellBeta, cellGamma,
				recordTimeStamp, anomalous, dataCollectionId);
		System.out.println("F This is what I got as a response : autoProcIntegration  = " + ret + "  \n");

		ret = ws.storeOrUpdateAutoProcIntegration(autoProcIntegrationId, autoProcProgramId, startImageNumber,
				endImageNumber, refinedDetectorDistance, refinedXbeam, refinedYbeam, rotationAxisX, rotationAxisY,
				rotationAxisZ, beamVectorX, beamVectorY, beamVectorZ, cellA, cellB, cellC, cellAlpha, cellBeta,
				cellGamma, recordTimeStamp, anomalous, null);
		System.out.println("F This is what I got as a response : autoProcIntegration  = " + ret + "  \n");

		// creation
		ret = ws.storeOrUpdateAutoProcIntegration(autoProcIntegrationId, autoProcProgramId, startImageNumber,
				endImageNumber, refinedDetectorDistance, refinedXbeam, refinedYbeam, rotationAxisX, rotationAxisY,
				rotationAxisZ, beamVectorX, beamVectorY, beamVectorZ, cellA, cellB, cellC, cellAlpha, cellBeta,
				cellGamma, recordTimeStamp, anomalous, dataCollectionId);
		System.out.println("This is what I got as a response : autoProcIntegration  = " + ret + "  \n");

		// update
		autoProcIntegrationId = ret;
		ret = ws.storeOrUpdateAutoProcIntegration(autoProcIntegrationId, autoProcProgramId, startImageNumber,
				endImageNumber, refinedDetectorDistance, refinedXbeam, refinedYbeam, rotationAxisX, rotationAxisY,
				rotationAxisZ, beamVectorX, beamVectorY, beamVectorZ, cellA, cellB, cellC, cellAlpha, cellBeta,
				cellGamma, recordTimeStamp, anomalous, dataCollectionId);
		System.out.println("This is what I got as a response : autoProcIntegration  = " + ret + "  \n");
	}

	private static void testStoreOrUpdateAutoProcProgramAttachment() throws Exception {
		System.out.println("*************** testStoreOrUpdateAutoProcProgramAttachment ***************");
		Integer ret = -1;

		Integer autoProcProgramAttachmentId = null;
		String fileType = "Log";
		String fileName = "fileName";
		String filePath = "filePath";
		Calendar recordTimeStamp = Calendar.getInstance();
		Integer autoProcProgramId = 1;

		// foreign key null
		ret = ws.storeOrUpdateAutoProcProgramAttachment(autoProcProgramAttachmentId, fileType, fileName, filePath,
				recordTimeStamp, null);
		System.out.println("F This is what I got as a response : autoProcAttachmentId  = " + ret + "  \n");

		// create
		ret = ws.storeOrUpdateAutoProcProgramAttachment(autoProcProgramAttachmentId, fileType, fileName, filePath,
				recordTimeStamp, autoProcProgramId);
		System.out.println("C This is what I got as a response : autoProcAttachmentId  = " + ret + "  \n");

		// update
		autoProcProgramAttachmentId = ret;
		ret = ws.storeOrUpdateAutoProcProgramAttachment(autoProcProgramAttachmentId, fileType, fileName, filePath,
				recordTimeStamp, autoProcProgramId);
		System.out.println("U This is what I got as a response : autoProcAttachmentId  = " + ret + "  \n");
	}

	private static void testStoreOrUpdateAutoProcProgram() throws Exception {
		System.out.println("*************** testStoreOrUpdateAutoProcProgram ***************");
		Integer ret = -1;

		Integer autoProcProgramId = null;
		String processingCommandLine = "processingCommandLine";
		String processingPrograms = "processingPrograms";
		Boolean processingStatus = true;
		String processingMessage = "processingMessage";
		Calendar processingStartTime = Calendar.getInstance();
		Calendar processingEndTime = Calendar.getInstance();
		String processingEnvironment = "processingEnvironment";
		Calendar recordTimeStamp = Calendar.getInstance();

		ret = ws.storeOrUpdateAutoProcProgram(autoProcProgramId, processingCommandLine, processingPrograms,
				processingStatus, processingMessage, processingStartTime, processingEndTime, processingEnvironment,
				recordTimeStamp);
		System.out.println("This is what I got as a response : autoProcProgramId  = " + ret + "  \n");
	}

	private static void testStoreImageQualityIndicators() throws Exception {
		System.out.println("*************** testStoreImageQualityIndicators ***************");
		Integer ret = -1;

		String fileLocation = "/data/visitor/mx1416/id23eh2/20130312/RAW_DATA/ljo/d1/b04/c03_collect/";
		String fileName = "ljod1b4c03_2_0121.mccd";
		Integer imageId = 43388996;
		Integer autoProcProgramId = 1;
		Integer spotTotal = 1;
		Integer inResTotal = 2;
		Integer goodBraggCandidates = 3;
		Integer iceRings = 4;
		Float method1Res = (float) 0.1;
		Float method2Res = (float) 0.2;
		Float maxUnitCell = (float) 0.3;
		Float pctSaturationTop50Peaks = (float) 0.4;
		Integer inResolutionOvrlSpots = 5;
		Float binPopCutOffMethod2Res = (float) 0.5;
		Double totalIntegratedSignal = 0.3;
		Double dozor_score = 0.4;
		Calendar recordTimeStamp = Calendar.getInstance();

		// foreign keys null
		ret = ws.storeImageQualityIndicators(fileLocation, fileName, null, autoProcProgramId, spotTotal, inResTotal,
				goodBraggCandidates, iceRings, method1Res, method2Res, maxUnitCell, pctSaturationTop50Peaks,
				inResolutionOvrlSpots, binPopCutOffMethod2Res, recordTimeStamp, totalIntegratedSignal, dozor_score);
		System.out.println("F This is what I got as a response : imageQualityIndicators  = " + ret + "  \n");

		ret = ws.storeImageQualityIndicators(fileLocation, fileName, imageId, null, spotTotal, inResTotal,
				goodBraggCandidates, iceRings, method1Res, method2Res, maxUnitCell, pctSaturationTop50Peaks,
				inResolutionOvrlSpots, binPopCutOffMethod2Res, recordTimeStamp, totalIntegratedSignal, dozor_score);
		System.out.println("F This is what I got as a response : imageQualityIndicators  = " + ret + "  \n");

		// create
		ret = ws.storeImageQualityIndicators(fileLocation, fileName, imageId, autoProcProgramId, spotTotal, inResTotal,
				goodBraggCandidates, iceRings, method1Res, method2Res, maxUnitCell, pctSaturationTop50Peaks,
				inResolutionOvrlSpots, binPopCutOffMethod2Res, recordTimeStamp, totalIntegratedSignal, dozor_score);
		System.out.println("C This is what I got as a response : imageQualityIndicators  = " + ret + "  \n");

	}

	// private static void testStoreOrUpdateImageQualityIndicatorsValue() throws Exception {
	// System.out.println("*************** testStoreOrUpdateImageQualityIndicators ***************");
	// Integer ret = -1;
	//
	// Integer imageQualityIndicatorsId = null;
	// Integer imageId = 26463026;
	// Integer autoProcProgramId = 1;
	// Integer spotTotal = 1;
	// Integer inResTotal = 2;
	// Integer goodBraggCandidates = 3;
	// Integer iceRings = 4;
	// Float method1Res = (float) 0.1;
	// Float method2Res = (float) 0.2;
	// Float maxUnitCell = (float) 0.3;
	// Float pctSaturationTop50Peaks = (float) 0.4;
	// Integer inResolutionOvrlSpots = 5;
	// Float binPopCutOffMethod2Res = (float) 0.5;
	// Calendar recordTimeStamp = Calendar.getInstance();
	//
	// ImageQualityIndicatorsWS3VO value = new ImageQualityIndicatorsWS3VO();
	// value.setImageQualityIndicatorsId(imageQualityIndicatorsId);
	// value.setImageId(imageId);
	// value.setAutoProcProgramId(autoProcProgramId);
	// value.setSpotTotal(spotTotal);
	// value.setInResTotal(inResTotal);
	// value.setGoodBraggCandidates(goodBraggCandidates);
	// value.setIceRings(iceRings);
	// value.setMethod1Res(method1Res);
	// value.setMethod2Res(method2Res);
	// value.setMaxUnitCell(maxUnitCell);
	// value.setPctSaturationTop50Peaks(pctSaturationTop50Peaks);
	// value.setInResolutionOvrlSpots(inResolutionOvrlSpots);
	// value.setBinPopCutOffMethod2Res(binPopCutOffMethod2Res);
	// value.setRecordTimeStamp(recordTimeStamp);
	//
	// // create
	// ret = ws.storeOrUpdateImageQualityIndicators(value);
	// System.out.println("C This is what I got as a response : imageQualityIndicators = " + ret + "  \n");
	//
	// // update
	// value.setImageQualityIndicatorsId(ret);
	// ret = ws.storeOrUpdateImageQualityIndicators(value);
	// System.out.println("U This is what I got as a response : imageQualityIndicators = " + ret + "  \n");
	//
	// // foreign key null
	// value.setAutoProcProgramId(null);
	// ret = ws.storeOrUpdateImageQualityIndicators(value);
	// System.out.println("F This is what I got as a response : imageQualityIndicators = " + ret + "  \n");
	//
	// value.setAutoProcProgramId(1);
	// value.setImageId(null);
	// ret = ws.storeOrUpdateImageQualityIndicators(value);
	// System.out.println("F This is what I got as a response : imageQualityIndicators = " + ret + "  \n");
	//
	// }

	private static void testStoreOrUpdateAutoProcStatus() throws Exception {
		System.out.println("*************** testStoreOrUpdateAutoProcStatus ***************");
		Integer ret = -1;

		Integer autoProcStatusId = null;
		Integer autoProcIntegrationId = 1;//125836;
		String step = "Indexing";
		String status = "Launched";
		String comments = null;
		Calendar blTimeStamp = Calendar.getInstance();

		// foreign key null
		ret = ws.storeOrUpdateAutoProcStatus(autoProcStatusId, null, step, status, comments, blTimeStamp);
		System.out.println("F This is what I got as a response : autoProcStatusId  = " + ret + "  \n");

		// create
		ret = ws.storeOrUpdateAutoProcStatus(autoProcStatusId, autoProcIntegrationId, step, status, comments,
				blTimeStamp);
		System.out.println("C This is what I got as a response : autoProcStatusId  = " + ret + "  \n");

		// update
		autoProcStatusId = ret;
		ret = ws.storeOrUpdateAutoProcStatus(autoProcStatusId, autoProcIntegrationId, step, status, comments,
				blTimeStamp);
		System.out.println("U This is what I got as a response : autoProcStatusId  = " + ret + "  \n");
	}
	
	private static void testStoreOrUpdatePhasingProgramRun() throws Exception {
		System.out.println("*************** testStoreOrUpdatePhasingProgramRun ***************");
		Integer ret = -1;

		Integer phasingProgramRunId = null;
		String phasingCommandLine = "phasingCommandLine";
		String phasingPrograms = "phasingPrograms";
		Boolean phasingStatus = true;
		String phasingMessage = "phasingMessage";
		Calendar phasingStartTime = Calendar.getInstance();
		Calendar phasingEndTime =  Calendar.getInstance();
		String phasingEnvironment = "phasingEnvironment";
		Calendar recordTimeStamp =  Calendar.getInstance();
        PhasingProgramRun3VO vo = new PhasingProgramRun3VO();

		vo.setPhasingProgramRunId(phasingProgramRunId) ;
		vo.setPhasingCommandLine(phasingCommandLine);
		vo.setPhasingPrograms(phasingPrograms);
		vo.setPhasingStatus(phasingStatus);
		vo.setPhasingMessage(phasingMessage);
		vo.setPhasingStartTime(phasingStartTime);
		vo.setPhasingEndTime(phasingEndTime);
		vo.setPhasingEnvironment(phasingEnvironment);
		vo.setRecordTimeStamp(recordTimeStamp) ;
		
		// create
		ret = ws.storeOrUpdatePhasingProgramRun(vo);
		System.out.println("C This is what I got as a response : phasingProgramRunId  = " + ret + "  \n");

		// update
		phasingProgramRunId = ret;
		vo.setPhasingProgramRunId(phasingProgramRunId);
		ret = ws.storeOrUpdatePhasingProgramRun(vo);
		System.out.println("U This is what I got as a response : phasingProgramRunId  = " + ret + "  \n");
	}
	
	private static void testStoreOrUpdatePhasingAnalysis() throws Exception {
		System.out.println("*************** testStoreOrUpdatePhasingAnalysis ***************");
		Integer ret = -1;

		Integer phasingAnalysisId = null;
		Calendar recordTimeStamp =  Calendar.getInstance();
		
		PhasingAnalysis3VO vo = new PhasingAnalysis3VO();
		vo.setPhasingAnalysisId(phasingAnalysisId) ;
		vo.setRecordTimeStamp(recordTimeStamp) ;
		
		// foreign key null
		ret = ws.storeOrUpdatePhasingAnalysis(vo);
		System.out.println("F This is what I got as a response : phasingAnalysisId  = " + ret + "  \n");

		
		// update
		phasingAnalysisId = ret;
		vo.setPhasingAnalysisId(phasingAnalysisId);
		ret = ws.storeOrUpdatePhasingAnalysis(vo);
		System.out.println("U This is what I got as a response : phasingAnalysisId  = " + ret + "  \n");
	}
	
	private static void testStoreOrUpdatePreparePhasingData() throws Exception {
		System.out.println("*************** testStoreOrUpdatePreparePhasingData ***************");
		Integer ret = -1;

		Integer preparePhasingDataId = null;
		Integer phasingAnalysisId = null;
		Integer phasingProgramRunId = null;
		Integer spaceGroupId = 221;
		Double lowRes = 0.3;
		Double highRes = 0.4;
		Calendar recordTimeStamp =  Calendar.getInstance();
		
		PreparePhasingDataWS3VO vo = new PreparePhasingDataWS3VO();
		vo.setPreparePhasingDataId(preparePhasingDataId) ;
		vo.setPhasingAnalysisId(phasingAnalysisId);
		vo.setPhasingProgramRunId(phasingProgramRunId) ;
		vo.setSpaceGroupId(spaceGroupId);
		vo.setLowRes(lowRes);
		vo.setHighRes(highRes);
		vo.setRecordTimeStamp(recordTimeStamp) ;
		
		// foreign key null
		ret = ws.storeOrUpdatePreparePhasingData(vo);
		System.out.println("F This is what I got as a response : preparePhasingDataId  = " + ret + "  \n");

		// create
		phasingProgramRunId = 1;
		phasingAnalysisId = 1;
		vo.setPhasingAnalysisId(phasingAnalysisId);
		vo.setPhasingProgramRunId(phasingProgramRunId) ;
		ret = ws.storeOrUpdatePreparePhasingData(vo);
		System.out.println("C This is what I got as a response : preparePhasingDataId  = " + ret + "  \n");

		// update
		preparePhasingDataId = ret;
		vo.setPreparePhasingDataId(preparePhasingDataId);
		ret = ws.storeOrUpdatePreparePhasingData(vo);
		System.out.println("U This is what I got as a response : preparePhasingDataId  = " + ret + "  \n");
	}
	
	private static void testStoreOrUpdateSubstructureDetermination() throws Exception {
		System.out.println("*************** testStoreOrUpdateSubstructureDetermination ***************");
		Integer ret = -1;

		Integer substructureDeterminationId = null;
		Integer phasingAnalysisId = null;
		Integer phasingProgramRunId = null;
		Integer spaceGroupId = 221;
		String method = "MAD";
		Double lowRes = 0.3;
		Double highRes = 0.4;
		Calendar recordTimeStamp =  Calendar.getInstance();
		
		SubstructureDeterminationWS3VO vo = new SubstructureDeterminationWS3VO();
		vo.setSubstructureDeterminationId(substructureDeterminationId) ;
		vo.setPhasingAnalysisId(phasingAnalysisId);
		vo.setPhasingProgramRunId(phasingProgramRunId) ;
		vo.setSpaceGroupId(spaceGroupId);
		vo.setMethod(method);
		vo.setLowRes(lowRes);
		vo.setHighRes(highRes);
		vo.setRecordTimeStamp(recordTimeStamp) ;
		
		// foreign key null
		ret = ws.storeOrUpdateSubstructureDetermination(vo);
		System.out.println("F This is what I got as a response : substructureDeterminationId  = " + ret + "  \n");

		// create
		phasingProgramRunId = 1;
		phasingAnalysisId = 1;
		vo.setPhasingAnalysisId(phasingAnalysisId);
		vo.setPhasingProgramRunId(phasingProgramRunId) ;
		ret = ws.storeOrUpdateSubstructureDetermination(vo);
		System.out.println("C This is what I got as a response : substructureDeterminationId  = " + ret + "  \n");

		// update
		substructureDeterminationId = ret;
		vo.setSubstructureDeterminationId(substructureDeterminationId) ;
		ret = ws.storeOrUpdateSubstructureDetermination(vo);
		System.out.println("U This is what I got as a response : substructureDeterminationId  = " + ret + "  \n");
	}
	
	
	private static void testStoreOrUpdatePhasing() throws Exception {
		System.out.println("*************** testStoreOrUpdatePhasing ***************");
		Integer ret = -1;

		Integer phasingId = null;
		Integer phasingAnalysisId = null;
		Integer phasingProgramRunId = null;
		Integer spaceGroupId = 221;
		String method = "solvent flattening";
		Double solventContent = 0.2;
		Boolean enantiomorph = false;
		Double lowRes = 0.3;
		Double highRes = 0.4;
		Calendar recordTimeStamp =  Calendar.getInstance();
		
		PhasingWS3VO vo = new PhasingWS3VO();
		vo.setPhasingId(phasingId) ;
		vo.setPhasingAnalysisId(phasingAnalysisId);
		vo.setPhasingProgramRunId(phasingProgramRunId) ;
		vo.setSpaceGroupId(spaceGroupId);
		vo.setMethod(method);
		vo.setSolventContent(solventContent);
		vo.setLowRes(lowRes);
		vo.setHighRes(highRes);
		vo.setEnantiomorph(enantiomorph);
		vo.setRecordTimeStamp(recordTimeStamp) ;
		
		// foreign key null
		ret = ws.storeOrUpdatePhasing(vo);
		System.out.println("F This is what I got as a response : phasingId  = " + ret + "  \n");

		// create
		phasingProgramRunId = 1;
		phasingAnalysisId = 1;
		vo.setPhasingAnalysisId(phasingAnalysisId);
		vo.setPhasingProgramRunId(phasingProgramRunId) ;
		ret = ws.storeOrUpdatePhasing(vo);
		System.out.println("C This is what I got as a response : phasingId  = " + ret + "  \n");

		// update
		phasingId = ret;
		vo.setPhasingId(phasingId);
		ret = ws.storeOrUpdatePhasing(vo);
		System.out.println("U This is what I got as a response : phasingId  = " + ret + "  \n");
	}
	
	private static void testStoreOrUpdateModelBuilding() throws Exception {
		System.out.println("*************** testStoreOrUpdateModelBuilding ***************");
		Integer ret = -1;

		Integer modelBuildingId = null;
		Integer phasingAnalysisId = null;
		Integer phasingProgramRunId = null;
		Integer spaceGroupId = 221;
		Double lowRes = 0.3;
		Double highRes = 0.4;
		Calendar recordTimeStamp =  Calendar.getInstance();
		
		ModelBuildingWS3VO vo = new ModelBuildingWS3VO();
		vo.setModelBuildingId(modelBuildingId) ;
		vo.setPhasingAnalysisId(phasingAnalysisId);
		vo.setPhasingProgramRunId(phasingProgramRunId) ;
		vo.setSpaceGroupId(spaceGroupId);
		vo.setLowRes(lowRes);
		vo.setHighRes(highRes);
		vo.setRecordTimeStamp(recordTimeStamp) ;
		
		// foreign key null
		ret = ws.storeOrUpdateModelBuilding(vo);
		System.out.println("F This is what I got as a response : modelBuildingId  = " + ret + "  \n");

		// create
		phasingProgramRunId = 1;
		phasingAnalysisId = 1;
		vo.setPhasingAnalysisId(phasingAnalysisId);
		vo.setPhasingProgramRunId(phasingProgramRunId) ;
		ret = ws.storeOrUpdateModelBuilding(vo);
		System.out.println("C This is what I got as a response : modelBuildingId  = " + ret + "  \n");

		// update
		modelBuildingId = ret;
		vo.setModelBuildingId(modelBuildingId);
		ret = ws.storeOrUpdateModelBuilding(vo);
		System.out.println("U This is what I got as a response : modelBuildingId  = " + ret + "  \n");
	}
	
	private static void testStoreOrUpdatePhasingProgramAttachment() throws Exception {
		System.out.println("*************** testStoreOrUpdatePhasingProgramAttachment ***************");
		Integer ret = -1;

		Integer phasingProgramAttachmentId = null;
		Integer phasingProgramRunId = null;
		String fileType = "Map";
		String fileName = "fileName";
		String filePath = "filePath";
		Calendar recordTimeStamp =  Calendar.getInstance();
		
		PhasingProgramAttachmentWS3VO vo = new PhasingProgramAttachmentWS3VO();
		vo.setPhasingProgramAttachmentId(phasingProgramAttachmentId) ;
		vo.setPhasingProgramRunId(phasingProgramRunId) ;
		vo.setFileType(fileType);
		vo.setFileName(fileName);
		vo.setFilePath(filePath);
		vo.setRecordTimeStamp(recordTimeStamp) ;
		
		// foreign key null
		ret = ws.storeOrUpdatePhasingProgramAttachment(vo);
		System.out.println("F This is what I got as a response : phasingProgramAttachmentId  = " + ret + "  \n");

		// create
		phasingProgramRunId = 1;
		vo.setPhasingProgramRunId(phasingProgramRunId) ;
		ret = ws.storeOrUpdatePhasingProgramAttachment(vo);
		System.out.println("C This is what I got as a response : phasingProgramAttachmentId  = " + ret + "  \n");

		// update
		phasingProgramAttachmentId = ret;
		vo.setPhasingProgramAttachmentId(phasingProgramAttachmentId) ;
		ret = ws.storeOrUpdatePhasingProgramAttachment(vo);
		System.out.println("U This is what I got as a response : phasingProgramAttachmentId  = " + ret + "  \n");
	}
	
	private static void testStoreOrUpdatePhasingHasScaling() throws Exception {
		System.out.println("*************** testStoreOrUpdatePhasingHasScaling ***************");
		Integer ret = -1;

		Integer phasingHasScalingId = null;
		Integer phasingAnalysisId = null;
		Integer autoProcScalingId = null;
		Integer datasetNumber = 1;
		Calendar recordTimeStamp =  Calendar.getInstance();
		
		PhasingHasScalingWS3VO vo = new PhasingHasScalingWS3VO();
		vo.setPhasingHasScalingId(phasingHasScalingId) ;
		vo.setPhasingAnalysisId(phasingAnalysisId) ;
		vo.setAutoProcScalingId(autoProcScalingId) ;
		vo.setDatasetNumber(datasetNumber);
		vo.setRecordTimeStamp(recordTimeStamp) ;
		
		// foreign key null
		ret = ws.storeOrUpdatePhasingHasScaling(vo);
		System.out.println("F This is what I got as a response : phasingHasScalingId  = " + ret + "  \n");

		// foreign key null
		phasingAnalysisId = 1;
		vo.setPhasingAnalysisId(phasingAnalysisId) ;
		ret = ws.storeOrUpdatePhasingHasScaling(vo);
		System.out.println("F This is what I got as a response : phasingHasScalingId  = " + ret + "  \n");

		// create
		autoProcScalingId= 1;
		vo.setAutoProcScalingId(autoProcScalingId) ;
		ret = ws.storeOrUpdatePhasingHasScaling(vo);
		System.out.println("C This is what I got as a response : phasingHasScalingId  = " + ret + "  \n");

		// update
		phasingHasScalingId = ret;
		vo.setPhasingHasScalingId(phasingHasScalingId) ;
		ret = ws.storeOrUpdatePhasingHasScaling(vo);
		System.out.println("U This is what I got as a response : phasingHasScalingId  = " + ret + "  \n");
	}
	
	private static void testStoreOrUpdatePhasingStatistics() throws Exception {
		System.out.println("*************** testStoreOrUpdatePhasingStatistics ***************");
		Integer ret = -1;

		Integer phasingStatisticsId = null;
		Integer phasingHasScalingId1 = null;
		Integer phasingHasScalingId2 = null;
		Integer numberOfBins = 10;
		Integer binNumber = 1;
		Double lowRes = 0.2;
		Double highRes = 0.3;
		String metric = "Rcullis";
		Double statisticsValue = 0.4;
		Integer nReflections = 2;
		Calendar recordTimeStamp =  Calendar.getInstance();
		
		PhasingStatisticsWS3VO vo = new PhasingStatisticsWS3VO();
		vo.setPhasingStatisticsId(phasingStatisticsId) ;
		vo.setPhasingHasScalingId1(phasingHasScalingId1) ;
		vo.setPhasingHasScalingId2(phasingHasScalingId2) ;
		vo.setNumberOfBins(numberOfBins);
		vo.setBinNumber(binNumber);
		vo.setLowRes(lowRes);
		vo.setHighRes(highRes);
		vo.setMetric(metric);
		vo.setStatisticsValue(statisticsValue);
		vo.setNReflections(nReflections);
		vo.setRecordTimeStamp(recordTimeStamp) ;
		
		// foreign key null
		ret = ws.storeOrUpdatePhasingStatistics(vo);
		System.out.println("F This is what I got as a response : phasingStatisticsId  = " + ret + "  \n");

		
		// create
		phasingHasScalingId1= 1;
		vo.setPhasingHasScalingId1(phasingHasScalingId1) ;
		ret = ws.storeOrUpdatePhasingStatistics(vo);
		System.out.println("C This is what I got as a response : phasingStatisticsId  = " + ret + "  \n");

		// update
		phasingStatisticsId = ret;
		vo.setPhasingStatisticsId(phasingStatisticsId) ;
		ret = ws.storeOrUpdatePhasingStatistics(vo);
		System.out.println("U This is what I got as a response : phasingStatisticsId  = " + ret + "  \n");
	}
	

	private static void testGetSpaceGroupId() throws Exception {
		System.out.println("*************** testGetSpaceGroupId ***************");
		Integer ret = -1;
		String spaceGroupName= "P 421 2";
		
		// foreign key null
		ret = ws.getSpaceGroupId(spaceGroupName);
		System.out.println("F This is what I got as a response : spaceGroupId  = " + ret + "  \n");

	}
	
	private static void testGetAutoProcScalingIdList() throws Exception {
		System.out.println("*************** testGetAutoProcScalingIdList ***************");
		Integer autoProcIntegrationId = 220666;
		
		List<Integer> autoProcScalingIdList  = ws.getAutoProcScalingIdList(autoProcIntegrationId);
		System.out.println("F This is what I got as a response : autoProcScalingIdList  = {" + (autoProcScalingIdList.toString()) + "} \n");

	}
	
	private static String tabToString(int[] list){
		String s = "";
		if(list == null)
			return s;
		for(int k=0; k<list.length-1; k++){
			s += list[k]+", ";
		}
		s += list[list.length-1];
		return s;
	}
	
	private static void testStoreOrUpdateImageQualityIndicators() throws Exception {
		System.out.println("*************** testStoreOrUpdateImageQualityIndicators ***************");
		Integer ret = -1;

		Integer imageQualityIndicatorsId = null;
		Integer imageId = null;
		Integer autoProcProgramId = null;
		Integer spotTotal =1;
		Integer inResTotal = 2;
		Integer goodBraggCandidates = 3;
		Integer iceRings = 4;
		Float method1Res = (float) 5.1;
		Float method2Res = (float) 5.2;
		Float maxUnitCell = (float) 5.3;
		Float pctSaturationTop50Peaks= (float) 5.4;
		Integer inResolutionOvrlSpots = 6;
		Float binPopCutOffMethod2Res= (float) 7.1;
		Double totalIntegratedSignal = 0.4;
		Double dozor_score = 11.3;
		
		ImageQualityIndicatorsWS3VO vo = new ImageQualityIndicatorsWS3VO();
		vo.setImageQualityIndicatorsId(imageQualityIndicatorsId) ;
		vo.setImageId(imageId);
		vo.setAutoProcProgramId(autoProcProgramId);
		vo.setSpotTotal(spotTotal);
		vo.setInResTotal(inResTotal);
		vo.setGoodBraggCandidates(goodBraggCandidates);
		vo.setIceRings(iceRings);
		vo.setMethod1Res(method1Res);
		vo.setMethod2Res(method2Res);
		vo.setMaxUnitCell(maxUnitCell);
		vo.setPctSaturationTop50Peaks(pctSaturationTop50Peaks);
		vo.setInResolutionOvrlSpots(inResolutionOvrlSpots);
		vo.setBinPopCutOffMethod2Res(binPopCutOffMethod2Res);
		vo.setTotalIntegratedSignal(totalIntegratedSignal);
		//vo.setDozor_score(dozor_score);
		
		// foreign key null
		ret = ws.storeOrUpdateImageQualityIndicators(vo);
		System.out.println("F This is what I got as a response : imageQualityIndicatorsId  = " + ret + "  \n");

		// foreign key null
		imageId = 424235;
		vo.setImageId(imageId);
		ret = ws.storeOrUpdateImageQualityIndicators(vo);
		System.out.println("F This is what I got as a response : imageQualityIndicatorsId  = " + ret + "  \n");

		// create
		autoProcProgramId= 1;
		vo.setAutoProcProgramId(autoProcProgramId);
		ret = ws.storeOrUpdateImageQualityIndicators(vo);
		System.out.println("C This is what I got as a response : imageQualityIndicatorsId  = " + ret + "  \n");

		// update
		imageQualityIndicatorsId = ret;
		vo.setImageQualityIndicatorsId(imageQualityIndicatorsId) ;
		ret = ws.storeOrUpdateImageQualityIndicators(vo);
		System.out.println("U This is what I got as a response : imageQualityIndicatorsId  = " + ret + "  \n");
	}
	
	private static void testStoreOrUpdateImageQualityIndicatorsForFileName() throws Exception {
		System.out.println("*************** testStoreOrUpdateImageQualityIndicatorsForFileName ***************");
		Integer ret = -1;

		String fileLocation = "/data/id14eh1/external/fx9/280703/IR01/444";
		String fileName = "ir01-444a_2_003.img";
		Integer imageId = null;
		Integer autoProcProgramId = null;
		Integer spotTotal =2;
		Integer inResTotal = 3;
		Integer goodBraggCandidates = 4;
		Integer iceRings = 5;
		Float method1Res = (float) 6.5;
		Float method2Res = (float) 6.6;
		Float maxUnitCell = (float) 6.7;
		Float pctSaturationTop50Peaks= (float) 6.8;
		Integer inResolutionOvrlSpots = 7;
		Float binPopCutOffMethod2Res= (float) 8.3;
		Double totalIntegratedSignal = 9.4;
		Double dozor_score = 11.2;
		
		
		// foreign key null
		ret = ws.storeOrUpdateImageQualityIndicatorsForFileName(fileLocation, fileName, imageId, autoProcProgramId, spotTotal, inResTotal, goodBraggCandidates, iceRings, method1Res, method2Res, maxUnitCell, pctSaturationTop50Peaks, inResolutionOvrlSpots, binPopCutOffMethod2Res, totalIntegratedSignal, dozor_score);
		System.out.println("F This is what I got as a response : imageQualityIndicatorsId  = " + ret + "  \n");

		// store or update
		autoProcProgramId= 1;
		ret = ws.storeOrUpdateImageQualityIndicatorsForFileName(fileLocation, fileName, imageId, autoProcProgramId, spotTotal, inResTotal, goodBraggCandidates, iceRings, method1Res, method2Res, maxUnitCell, pctSaturationTop50Peaks, inResolutionOvrlSpots, binPopCutOffMethod2Res, totalIntegratedSignal, dozor_score);
		System.out.println("This is what I got as a response : imageQualityIndicatorsId  = " + ret + "  \n");

	}
	
	private static void testStoreOrUpdateImageQualityIndicatorsForFileNames() throws Exception {
		System.out.println("*************** testStoreOrUpdateImageQualityIndicatorsForFileNames ***************");
		
		String fileLocation = "/data/id29/inhouse/opid291/20130521/RAW_DATA/BRAZIL/G52m2";
		String fileName = "ref-x2_1_0001.cbf";
		Integer imageId = null;
		Integer autoProcProgramId = 1;
		Integer spotTotal =2;
		Integer inResTotal = 3;
		Integer goodBraggCandidates = 4;
		Integer iceRings = 5;
		Float method1Res = (float) 6.5;
		Float method2Res = (float) 6.6;
		Float maxUnitCell = (float) 6.7;
		Float pctSaturationTop50Peaks= (float) 6.8;
		Integer inResolutionOvrlSpots = 7;
		Float binPopCutOffMethod2Res= (float) 8.3;
		Double totalIntegratedSignal = 9.4;
		Double dozor_score = 11.2;
		
		ImageQualityIndicatorsWS3VO vo = new ImageQualityIndicatorsWS3VO();
		vo.setImageId(imageId);
		vo.setAutoProcProgramId(autoProcProgramId);
		vo.setSpotTotal(spotTotal);
		vo.setInResTotal(inResTotal);
		vo.setGoodBraggCandidates(goodBraggCandidates);
		vo.setIceRings(iceRings);
		vo.setMethod1Res(method1Res);
		vo.setMethod2Res(method2Res);
		vo.setMaxUnitCell(maxUnitCell);
		vo.setPctSaturationTop50Peaks(pctSaturationTop50Peaks);
		vo.setInResolutionOvrlSpots(inResolutionOvrlSpots);
		vo.setBinPopCutOffMethod2Res(binPopCutOffMethod2Res);
		vo.setTotalIntegratedSignal(totalIntegratedSignal);
		//vo.setDozor_score(dozor_score);
		vo.setFileLocation(fileLocation);
		vo.setFileName(fileName);
		
		//2
		String fileLocation2 = "/data/id29/inhouse/opid291/20130521/RAW_DATA/BRAZIL/G52m2";
		String fileName2 = "ref-x2_1_0004.cbf";
		Integer imageId2 = null;
		Integer autoProcProgramId2 = 1;
		Integer spotTotal2 =2;
		Integer inResTotal2 = 3;
		Integer goodBraggCandidates2 = 4;
		Integer iceRings2 = 5;
		Float method1Res2 = (float) 6.5;
		Float method2Res2 = (float) 6.6;
		Float maxUnitCell2 = (float) 6.7;
		Float pctSaturationTop50Peaks2= (float) 6.8;
		Integer inResolutionOvrlSpots2 = 7;
		Float binPopCutOffMethod2Res2= (float) 8.3;
		Double totalIntegratedSignal2 = 9.4;
		Double dozor_score2 = 11.5;
		
		ImageQualityIndicatorsWS3VO vo2 = new ImageQualityIndicatorsWS3VO();
		vo2.setImageId(imageId2);
		vo2.setAutoProcProgramId(autoProcProgramId2);
		vo2.setSpotTotal(spotTotal2);
		vo2.setInResTotal(inResTotal2);
		vo2.setGoodBraggCandidates(goodBraggCandidates2);
		vo2.setIceRings(iceRings2);
		vo2.setMethod1Res(method1Res2);
		vo2.setMethod2Res(method2Res2);
		vo2.setMaxUnitCell(maxUnitCell2);
		vo2.setPctSaturationTop50Peaks(pctSaturationTop50Peaks2);
		vo2.setInResolutionOvrlSpots(inResolutionOvrlSpots2);
		vo2.setBinPopCutOffMethod2Res(binPopCutOffMethod2Res2);
		vo2.setTotalIntegratedSignal(totalIntegratedSignal2);
		//vo2.setDozor_score(dozor_score2);
		vo2.setFileLocation(fileLocation2);
		vo2.setFileName(fileName2);
		
		// list
		List<ImageQualityIndicatorsWS3VO> list = new ArrayList();
		list.add(vo);
		list.add(vo2);
		
		// store or update
		autoProcProgramId= 1;
		vo.setAutoProcProgramId(autoProcProgramId);
		List<Integer>ret =  ws.storeOrUpdateImageQualityIndicatorsForFileNames(list);
		String s = "";
		if (ret != null){
			for (Iterator iterator = ret.iterator(); iterator.hasNext();) {
				Integer integer = (Integer) iterator.next();
				s += integer+", ";
			}
		}
		System.out.println("This is what I got as a response : imageQualityIndicatorsId  = " + s + "  \n");

	}
	
	private static void testStorePhasingData() throws Exception {
		System.out.println("*************** testStorePhasingData ***************");
		Integer ret = -1;

		Integer autoProcScalingId = 1;
		Integer datasetNumber = 1;
		String spaceGroup = "C222";
		Double lowRes = 0.1;
		Double highRes = 0.2;
		String phasingCommandLine = "phasingCommandLine";
		String phasingPrograms = "phasingPrograms";
		Boolean phasingStatus = true;
		String phasingMessage = "phasingMessage";
		Calendar phasingStartTime = Calendar.getInstance();
		Calendar phasingEndTime =  Calendar.getInstance();
		String phasingEnvironment = "phasingEnvironment";
		PhasingProgramRun3VO phasingProgramRun = new PhasingProgramRun3VO(); 
		phasingProgramRun.setPhasingProgramRunId(null) ;
		phasingProgramRun.setPhasingCommandLine(phasingCommandLine);
		phasingProgramRun.setPhasingPrograms(phasingPrograms);
		phasingProgramRun.setPhasingStatus(phasingStatus);
		phasingProgramRun.setPhasingMessage(phasingMessage);
		phasingProgramRun.setPhasingStartTime(phasingStartTime);
		phasingProgramRun.setPhasingEndTime(phasingEndTime);
		phasingProgramRun.setPhasingEnvironment(phasingEnvironment);
		PhasingProgramAttachmentWS3VO[] listPhasingProgramAttachment = new PhasingProgramAttachmentWS3VO[1];
		String fileType = "Map";
		String fileName = "fileName";
		String filePath = "filePath";
		PhasingProgramAttachmentWS3VO att1 = new PhasingProgramAttachmentWS3VO();
		att1.setPhasingProgramAttachmentId(null) ;
		att1.setPhasingProgramRunId(null) ;
		att1.setFileType(fileType);
		att1.setFileName(fileName);
		att1.setFilePath(filePath);
		listPhasingProgramAttachment[0] = att1;
		PhasingStatisticsWS3VO[] listPhasingStatistics = new PhasingStatisticsWS3VO[2];
		Integer numberOfBins = 10;
		Integer binNumber = 1;
		Double lowResS = 0.4;
		Double highResS = 0.5;
		String metric = "Rcullis";
		Double statisticsValue = 0.4;
		Integer nReflections = 2;
		PhasingStatisticsWS3VO stat1 = new PhasingStatisticsWS3VO();
		stat1.setPhasingStatisticsId(null) ;
		stat1.setPhasingHasScalingId1(null) ;
		stat1.setPhasingHasScalingId2(null) ;
		stat1.setNumberOfBins(numberOfBins);
		stat1.setBinNumber(binNumber);
		stat1.setLowRes(lowResS);
		stat1.setHighRes(highResS);
		stat1.setMetric(metric);
		stat1.setStatisticsValue(statisticsValue);
		stat1.setNReflections(nReflections);
		listPhasingStatistics[0] = stat1;
		PhasingStatisticsWS3VO stat2 = new PhasingStatisticsWS3VO();
		Integer numberOfBins2 = 11;
		Integer binNumber2 = 2;
		Double lowResS2 = 0.5;
		Double highResS2 = 0.6;
		String metric2 = "<d\"/sig>";
		Double statisticsValue2 = 0.5;
		Integer nReflections2 = 3;
		stat2.setPhasingStatisticsId(null) ;
		stat2.setPhasingHasScalingId1(null) ;
		stat2.setPhasingHasScalingId2(null) ;
		stat2.setNumberOfBins(numberOfBins2);
		stat2.setBinNumber(binNumber2);
		stat2.setLowRes(lowResS2);
		stat2.setHighRes(highResS2);
		stat2.setMetric(metric2);
		stat2.setStatisticsValue(statisticsValue2);
		stat2.setNReflections(nReflections2);
		listPhasingStatistics[1] = stat2;
		//
		String method = "SAD";
		Double solventContent = 0.3;
		Boolean enantiomorph = true;
		
		// preparePhasingData
		datasetNumber = 1;
		PhasingData preparePhasingData = new PhasingData();
		preparePhasingData.setStep(1);
		preparePhasingData.setAutoProcScalingId(autoProcScalingId);
		preparePhasingData.setDatasetNumber(datasetNumber);
		preparePhasingData.setSpaceGroup(spaceGroup);
		preparePhasingData.setPhasingProgramRun(phasingProgramRun);
		//preparePhasingData.setListPhasingProgramAttachment(listPhasingProgramAttachment);
		//preparePhasingData.setListPhasingStatistics(listPhasingStatistics);
		preparePhasingData.setLowRes(lowRes);
		preparePhasingData.setHighRes(highRes);
		ret = ws.storePhasingData(preparePhasingData);
		System.out.println("This is what I got as a response : preparePhasingDataId  = " + ret + "  \n");
		
		// subStructureDetermination
		datasetNumber = 2;
		PhasingData subStructureDeterminationData = new PhasingData();
		subStructureDeterminationData.setStep(2);
		subStructureDeterminationData.setAutoProcScalingId(autoProcScalingId);
		subStructureDeterminationData.setDatasetNumber(datasetNumber);
		subStructureDeterminationData.setSpaceGroup(spaceGroup);
		subStructureDeterminationData.setPhasingProgramRun(phasingProgramRun);
		//subStructureDeterminationData.setListPhasingProgramAttachment(listPhasingProgramAttachment);
		//subStructureDeterminationData.setListPhasingStatistics(listPhasingStatistics);
		subStructureDeterminationData.setLowRes(lowRes);
		subStructureDeterminationData.setHighRes(highRes);
		subStructureDeterminationData.setMethod(method);
		ret = ws.storePhasingData(subStructureDeterminationData);
		System.out.println("This is what I got as a response : subStructureDeterminationDataId  = " + ret + "  \n");
		
		// phasing
		datasetNumber = 3;
		method = "solvent flattening";
		PhasingData phasingData = new PhasingData();
		phasingData.setStep(3);
		phasingData.setAutoProcScalingId(autoProcScalingId);
		phasingData.setDatasetNumber(datasetNumber);
		phasingData.setSpaceGroup(spaceGroup);
		phasingData.setPhasingProgramRun(phasingProgramRun);
		//phasingData.setListPhasingProgramAttachment(listPhasingProgramAttachment);
		//phasingData.setListPhasingStatistics(listPhasingStatistics);
		phasingData.setLowRes(lowRes);
		phasingData.setHighRes(highRes);
		phasingData.setMethod(method);
		phasingData.setSolventContent(solventContent);
		phasingData.setEnantiomorph(enantiomorph);
		ret = ws.storePhasingData(phasingData);
		System.out.println("This is what I got as a response : phasingDataId  = " + ret + "  \n");
		
		// model Building
		datasetNumber = 4;
		PhasingData modelBuildingData = new PhasingData();
		modelBuildingData.setStep(4);
		modelBuildingData.setAutoProcScalingId(autoProcScalingId);
		modelBuildingData.setDatasetNumber(datasetNumber);
		modelBuildingData.setSpaceGroup(spaceGroup);
		modelBuildingData.setPhasingProgramRun(phasingProgramRun);
		//modelBuildingData.setListPhasingProgramAttachment(listPhasingProgramAttachment);
		//modelBuildingData.setListPhasingStatistics(listPhasingStatistics);
		modelBuildingData.setLowRes(lowRes);
		modelBuildingData.setHighRes(highRes);
		ret = ws.storePhasingData(modelBuildingData);
		System.out.println("This is what I got as a response : modelBuildingDataId  = " + ret + "  \n");
	}
}
