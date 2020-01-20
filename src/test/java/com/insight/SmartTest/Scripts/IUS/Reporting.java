package com.insight.SmartTest.Scripts.IUS;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.insight.report.BrowserContext;
import com.insight.report.CReporterWeb;
import com.insight.report.ReportXML;
import com.insight.report.ReporterConstants;
import com.insight.report.TestResult;


public class Reporting {
	private static final Logger LOG = Logger.getLogger(Reporting.class);
	private static BrowserContext browserContext = null;
	private static String osName1 = System.getProperty("os.name").toLowerCase();
	private static String browserName = null;
	private static String strDate = null;
	static String sReportPath =null;
	 public void updateXMLForSummary(File summaryFile, List<String> files) throws Throwable {
	        int index = 0, serialNo = 0;
	        
	        Node rootNode = null;
	        String href = "";
	        String htmlFileType = "html";

	        String url = "https://uat1.insight.com/";
	        

	        LOG.info("inside method: updateXMLForSummary");

	        try {
	            //Get Document Builder
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            /*factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
	            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);*/
	            DocumentBuilder builder = factory.newDocumentBuilder();

	            // Get Pass/Fail test cases count
	            Integer passTCCount = TestResult.passCounter.get(browserContext) == null ? 0
	                    : TestResult.passCounter.get(browserContext);
	            Integer failTCCount = TestResult.failCounter
	                    .get(browserContext) == null ? 0
	                    : TestResult.failCounter.get(browserContext);

	            // Iterate through list of detail xml files
	            for (int fileCount = 0; fileCount < files.size(); fileCount++) {
	                Document document = builder.parse(new File(sReportPath + String.valueOf(File.separatorChar) +
	                        files.get(fileCount)));
	                Document doc = builder.newDocument();
	                //Normalize the XML Structure; It's just too important !!
	                document.getDocumentElement().normalize();

	                //Here comes the root node
	                Element root = document.getDocumentElement();
	                LOG.info("root node name : " + root.getNodeName());

	                NodeList node = document.getElementsByTagName("TestCase");
	                Node nodeData = node.item(index);
	                Element tcData = (Element) nodeData;

	                String tcName = tcData.getElementsByTagName("TestCaseName").item(index).getTextContent();
	                String tcDesc = tcData.getElementsByTagName("TestCaseDescription").item(index).getTextContent();
	                String passCount = tcData.getElementsByTagName("PassCount").item(index).getTextContent();
	                String failCount = tcData.getElementsByTagName("FailCount").item(index).getTextContent();
	                String executionTime = tcData.getElementsByTagName("TestCaseExecutionTimeInSeconds").item(index).getTextContent();
	                String status = tcData.getElementsByTagName("Status").item(index).getTextContent();

//	                if(status.equalsIgnoreCase("FAIL")){
//	                    status="<i style=\"width:30px;color:red\" class=\"fa fa-close fa-3x\"></i></a>";
//	                }else{
//	                    status="<i style=\"width:30px;color:green\" class=\"fa fa-check-circle fa-3x\"></i>";
//	                }

	                if (fileCount == 0) {
	                    Element summary = doc.createElement("Summary");
	                    doc.appendChild(summary);
	                    rootNode = doc.getFirstChild();

	                    //populate details in header node
	                    Element header = doc.createElement("Header");
	                    rootNode.appendChild(header);

	                    Element suiteName = doc.createElement("SuiteExecuted");

	                        suiteName.appendChild(doc.createTextNode(ReporterConstants.SUITE_NAME));

	                    header.appendChild(suiteName);

	                    Element browser = doc.createElement("Browser");
	                    if(browserName != null) {
	                        browser.appendChild(doc.createTextNode(browserName));
	                    }else{
	                        browser.appendChild(doc.createTextNode("Null"));
	                    }
	                    header.appendChild(browser);

	                    Element version = doc.createElement("Version");
	                    if(ReporterConstants.BROWSER_VERSION != null) {
	                        version.appendChild(doc.createTextNode(ReporterConstants.BROWSER_VERSION));
	                    }
	                    else
	                    {
	                        version.appendChild(doc.createTextNode("Null"));
	                    }
	                    header.appendChild(version);

	                    // OS name
	                    Element osName = doc.createElement("OS");
	                    if(osName != null) {
	                        osName.appendChild(doc.createTextNode(osName1));
	                    }else
	                    {
	                        osName.appendChild(doc.createTextNode("Null"));
	                    }
	                    header.appendChild(osName);

	                    // Test Case Start time
	                    Element dateTime = doc.createElement("DateTime");
	                    SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
	                    dateTime.appendChild(doc.createTextNode(formatter1.format(new Date())));
	                    header.appendChild(dateTime);

	                    // Host Name
	                    Element hostName = doc.createElement("HostName");
	                    if(InetAddress.getLocalHost().getHostName() !=null) {
	                    hostName.appendChild(doc.createTextNode(InetAddress.getLocalHost().getHostName()));
	                    }
	                    else {
	                    	hostName.appendChild(doc.createTextNode("Null"));
	                    }
	                    header.appendChild(hostName);

	                    Element environment = doc.createElement("Environment");
	                    if(doc.createTextNode(url) !=null) {
	                    environment.appendChild(doc.createTextNode(url));
	                    }
	                    else {
	                    	environment.appendChild(doc.createTextNode("Null"));
	                    }
	                    header.appendChild(environment);

	                    Element passTestCount = doc.createElement("PassTestCases");
	                    if(doc.createTextNode(String.valueOf(passTCCount)) !=null) {
	                    passTestCount.appendChild(doc.createTextNode(String.valueOf(passTCCount)));
	                    }
	                    else {
	                    	passTestCount.appendChild(doc.createTextNode("Null"));
	                    }
	                    header.appendChild(passTestCount);

	                    Element failTestCount = doc.createElement("FailTestCases");
	                    if(doc.createTextNode(String.valueOf(failTCCount)) !=null) {
	                    failTestCount.appendChild(doc.createTextNode(String.valueOf(failTCCount)));
	                    }
	                    else {
	                    	failTestCount.appendChild(doc.createTextNode("Null"));
	                    }
	                    header.appendChild(failTestCount);

	                    double suiteExecTime = TestResult.iSuiteExecutionTime.get(Reporting.browserContext);
	                    String suiteExecTimeInSeconds = String.valueOf((int) suiteExecTime) + " " + "seconds";
	                    Element suiteExecutionTime = doc.createElement("SuiteExecutionTime");
	                    if(doc.createTextNode(suiteExecTimeInSeconds)!=null) {
	                    suiteExecutionTime.appendChild(doc.createTextNode(suiteExecTimeInSeconds));
	                    }
	                    else {
	                    	suiteExecutionTime.appendChild(doc.createTextNode("Null"));	
	                    }
	                    header.appendChild(suiteExecutionTime);
	                } else {
	                    FileInputStream inputStream = new FileInputStream(summaryFile);
	                    InputSource inputSource = new InputSource(inputStream);

	                    //create a new document from input source
	                    doc = builder.parse(inputSource);
	                    rootNode = doc.getFirstChild();
	                }

	                Element testDetails = doc.createElement("TestDetails");
	                rootNode.appendChild(testDetails);

	                Element sno = doc.createElement("SerialNo");
	                serialNo = fileCount + 1;
	                if(doc.createTextNode(String.valueOf(serialNo))!=null) {
	                sno.appendChild(doc.createTextNode(String.valueOf(serialNo)));
	                }
	                else {
	                	sno.appendChild(doc.createTextNode("Null"));
	                }
	                testDetails.appendChild(sno);

	                Element testName = doc.createElement("TestCase");
	                if(doc.createTextNode(tcName)!=null) {
	                testName.appendChild(doc.createTextNode(tcName));
	                }
	                else {
	                	testName.appendChild(doc.createTextNode("Null"));	
	                }
	                testDetails.appendChild(testName);

	                //populate href for each Test case
	                href = tcName + "." + htmlFileType;
	                Element testLink = doc.createElement("href");
	                if(doc.createTextNode(href)!=null) {
	                testLink.appendChild(doc.createTextNode(href));
	                }
	                else{
	                	 testLink.appendChild(doc.createTextNode("Null"));
	                }
	                testDetails.appendChild(testLink);

	                Element testDesc = doc.createElement("Description");
	                if(doc.createTextNode(tcDesc)!=null) {
	                testDesc.appendChild(doc.createTextNode(tcDesc));
	                }
	                else {
	                	 testDesc.appendChild(doc.createTextNode("Null"));
	                }
	                testDetails.appendChild(testDesc);

	                Element passCnt = doc.createElement("PassCount");
	                if(doc.createTextNode(passCount)!=null) {
	                passCnt.appendChild(doc.createTextNode(passCount));
	                }
	                else {
	                	 passCnt.appendChild(doc.createTextNode("Null"));
	                }
	                testDetails.appendChild(passCnt);

	                Element failCnt = doc.createElement("FailCount");
	                if(doc.createTextNode(failCount)!=null) {
	                	failCnt.appendChild(doc.createTextNode(failCount));
	                    }
	                    else {
	                    	failCnt.appendChild(doc.createTextNode("Null"));
	                    }
	                
	                testDetails.appendChild(failCnt);

	                Element time = doc.createElement("Time");
	                if(doc.createTextNode(executionTime)!=null) {
	                	time.appendChild(doc.createTextNode(executionTime));
	                    }
	                    else {
	                    	time.appendChild(doc.createTextNode("Null"));
	                    }
	                
	               
	                testDetails.appendChild(time);

	                Element tcStatus = doc.createElement("Status");
	                if(doc.createTextNode(executionTime)!=null) {
	                	 tcStatus.appendChild(doc.createCDATASection(status));
	                    }
	                    else {
	                    	 tcStatus.appendChild(doc.createCDATASection("NUll"));
	                    }
	                
	               
	                testDetails.appendChild(tcStatus);

	                //write the content to xml file
	                TransformerFactory transformerFactory = TransformerFactory.newInstance();
	                Transformer transformer = transformerFactory.newTransformer();
	                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	                DOMSource source = new DOMSource(doc);

	                StreamResult result = new StreamResult(summaryFile);
	                transformer.transform(source, result);
	                LOG.info("File saved");
	            }
	        } catch (ParserConfigurationException e) {
	            e.printStackTrace();
	        } catch (SAXException e) {
	            e.printStackTrace();
	        } catch (TransformerConfigurationException e) {
	            e.printStackTrace();
	        } catch (TransformerException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 }
	        public List<String> listAllFiles(File folder, String fileType) {
	            LOG.info("in listAllFiles method ");
	            File[] fileNames = folder.listFiles();
	            List<String> fileList = new ArrayList<String>();

	            for (File file : fileNames) {
	                LOG.info("file name : " + file.getName());
	                if (file.getName().endsWith(("." + fileType))) {
	                    fileList.add(file.getName());
	                }
	            }
	            return fileList;
	        }
	        /**
	         * Initialise browserContext
	         *
	         * @param cReporterweb
	         */
	           

public static void main(String[] args) throws Throwable {
	Reporting a = new Reporting();
	Date today = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    //strDate = formatter.format(today).replace(":", "_").replace(" ", "_");
    strDate = formatter.format(today);
    sReportPath= "C:\\xampp\\htdocs\\Report-" + strDate;
	File reportDir = new File(sReportPath);
	String fileType = "xml";
	
	 
	 String summaryXMLName = "SummaryResults";
	List<String> xmlFiles =a.listAllFiles(reportDir, fileType);
	//File file = new File(sReportPath + File.separator + tcName + "." + fileType);
    File summaryFile = new File(sReportPath + File.separator + summaryXMLName + "." + fileType);
	a.updateXMLForSummary(summaryFile, xmlFiles);
}
}