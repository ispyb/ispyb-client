import time
import socket

from suds.client import Client
from suds.transport.http import HttpAuthenticated
from suds import WebFault
from urllib2 import URLError


if __name__ == "__main__":
    import sys
    try:
        URL_ISPYB_SAMPLE = 'http://localhost:8080/ispyb-ejb3/ispybWS/ToolsForBLSampleWebService?wsdl'
        URL_ISPYB_SHIPPING = 'http://localhost:8080/ispyb-ejb3/ispybWS/ToolsForShippingWebService?wsdl'
        URL_ISPYB_COLLECTION = 'http://localhost:8080/ispyb-ejb3/ispybWS/ToolsForCollectionWebService?wsdl'

        USERNAME = "ispybws1"
        PASSWORD = "!5pybws1"
        
        clientISPYB_COLLECTION = Client(URL_ISPYB_COLLECTION  , username=USERNAME, password=PASSWORD, timeout=3)
        clientISPYB_SAMPLE = Client(URL_ISPYB_SAMPLE  , username=USERNAME, password=PASSWORD, timeout=3)
        clientISPYB_SHIPPING = Client(URL_ISPYB_SHIPPING  , username=USERNAME, password=PASSWORD, timeout=3)
        
        response_samples = []
        for i in range(1):
            atime=time.time()
            response_samples = clientISPYB_SAMPLE.service.findSampleInfoLightForProposal(1170 , "ID14-4")
            btime=time.time()
            difftime = btime - atime
            print "getSample="+str(difftime*1000)+"ms"
        for i in range(1):
            atime=time.time()
            result_xds = clientISPYB_COLLECTION.service.getXDSInfo(971155)
            btime=time.time()
            difftime = btime - atime
            print "getXDS="+str(difftime*1000)+"ms"
        for i in range(1):
            atime=time.time()
            result_xds = clientISPYB_COLLECTION.service.updateDataCollectionStatus(971155, "DataCollection failed !!")
            btime=time.time()
            difftime = btime - atime
            print "updateStatus="+str(difftime*1000)+"ms"
        for i in range(1):
            image = {}
            image["imageId"]= 0
            image["dataCollectionId"]= 19570
            image["imageNumber"]= 2
            image["fileName"]= "fileName"
            image["fileLocation"]= "fileLocation"
            image["measuredIntensity"]= 0.1
            image["jpegFileFullPath"]= "jpegFileFullPath"
            image["jpegThumbnailFileFullPath"]= "jpegThumbnailFileFullPath"
            image["temperature"]= 0.2
            image["cumulativeIntensity"]= 0.3
            image["synchrotronCurrent"]= 0.4
            image["comments"]= "comments"
            image["machineMessage"]= "machineMessage"
            atime=time.time()
            imageId = clientISPYB_COLLECTION.service.storeOrUpdateImage(image)
            btime=time.time()
            difftime = btime - atime
            print "storeImage="+str(difftime*1000)+"ms"
        for i in range(1):
            proposal = "";
            atime=time.time()
            proposal = clientISPYB_SHIPPING.service.findProposal("opid" , "30b")
            btime=time.time()
            difftime = btime - atime
            print "find proposal ="+str(difftime*1000)+"ms: "+str(proposal)
    except WebFault, e:
        print e.reason   
    except URLError, e:
        print e.reason
        print e.code
    except socket.timeout:
        print "Timed out!"
    except ValueError:
        print ValueError
    

