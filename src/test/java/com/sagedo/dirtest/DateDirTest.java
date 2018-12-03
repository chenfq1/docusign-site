package com.sagedo.dirtest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.Test;

/**
 * Created by chenfuqiang on 2018/12/3.
 */
public class DateDirTest {

  @Test
  public void mkDirByDate(){
    Date date = new Date();
    String s = new SimpleDateFormat("yyyy/MM/dd/").format(date);
//    System.out.println("classPath");
    String path = "src/test/resources/"+s;
    File f = new File(path);
    File fileParent = f.getParentFile();
    //判断是否存在
    if (!fileParent.exists()) {
      //创建父目录文件
      fileParent.mkdirs();
    }
    boolean mkdir = f.mkdir();
    if (mkdir) {
      System.out.println(path);
    }
  }
  @Test
  public void mkDirByDate1(){
    Date date = new Date();
    String s = new SimpleDateFormat("yyyy/MM/dd/").format(date);
//    System.out.println("classPath");
    String path = "src/test/resources/"+s;
    File f = new File(path);
    if (!f.exists()) {
      boolean mkdirs = f.mkdirs();
      if (mkdirs) {
        System.out.println(path);
      }
    }
  }

  @Test
  public void mkDirByDateTomorrow(){
    Date date = new Date();

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);

    String s = new SimpleDateFormat("yyyy/MM/dd/").format(calendar.getTime());
//    System.out.println("classPath");
    String path = "src/test/resources/"+s;
    File f = new File(path);
    if (!f.exists()) {
      boolean mkdirs = f.mkdirs();
      if (mkdirs) {
        System.out.println(path);
      }
    }
  }

  @Test
  public void mkDirByDateLastMonth(){
    Date date = new Date();

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);

    String s = new SimpleDateFormat("yyyy/MM/dd/").format(calendar.getTime());
//    System.out.println("classPath");
    String path = "src/test/resources/"+s;
    File f = new File(path);
    if (!f.exists()) {
      boolean mkdirs = f.mkdirs();
      if (mkdirs) {
        System.out.println(path);
      }
    }
  }

  @Test
  public void saveDemo(){
    Date date = new Date();
    String param = "<DocuSignEnvelopeInformation xmlns=\"http://www.docusign.net/API/3.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
        + "<EnvelopeStatus>\n"
        + "<RecipientStatuses>\n"
        + "<RecipientStatus>\n"
        + "<Type>Signer</Type>\n"
        + "<Email>ZHMxNDUxNjM5MTU4MTAw@mailinator.com</Email>\n"
        + "<UserName>Gene Davidson</UserName>\n"
        + "<RoutingOrder>1</RoutingOrder>\n"
        + "<Sent>2016-01-01T01:06:06.947</Sent>\n"
        + "<Delivered>2016-01-01T01:06:23.367</Delivered>\n"
        + "<Signed>2016-01-01T01:06:43.833</Signed>\n"
        + "<DeclineReason xsi:nil=\"true\"/>\n"
        + "<Status>Completed</Status>\n"
        + "<RecipientIPAddress>87.68.55.196</RecipientIPAddress>\n"
        + "<CustomFields/>\n"
        + "<TabStatuses>...</TabStatuses>\n"
        + "<RecipientAttachment>...</RecipientAttachment>\n"
        + "<AccountStatus>Active</AccountStatus>\n"
        + "<EsignAgreementInformation>...</EsignAgreementInformation>\n"
        + "<FormData>...</FormData>\n"
        + "<RecipientId>f14468b6-c972-4ed2-983e-0d49ae2d7619</RecipientId>\n"
        + "</RecipientStatus>\n"
        + "<RecipientStatus>\n"
        + "<Type>CarbonCopy</Type>\n"
        + "<Email>ZHMxNDUxNjM5MTU4MTAw@mailinator.com</Email>\n"
        + "<UserName>Paul Dupuis</UserName>\n"
        + "<RoutingOrder>2</RoutingOrder>\n"
        + "<Sent>2016-01-01T01:06:44.8</Sent>\n"
        + "<DeclineReason xsi:nil=\"true\"/>\n"
        + "<Status>Completed</Status>\n"
        + "<RecipientIPAddress/>\n"
        + "<CustomFields/>\n"
        + "<AccountStatus>Active</AccountStatus>\n"
        + "<EsignAgreementInformation>\n"
        + "<AccountEsignId>eaaec8c5-3f58-4992-ba83-fec7b875aea4</AccountEsignId>\n"
        + "<UserEsignId>77d5ccb8-8f9e-4dbd-94b3-6458e0afc086</UserEsignId>\n"
        + "<AgreementDate>2016-01-01T01:06:23.367</AgreementDate>\n"
        + "</EsignAgreementInformation>\n"
        + "<RecipientId>c600783d-be44-4ed8-9e1d-b6dd9a4fd81a</RecipientId>\n"
        + "</RecipientStatus>\n"
        + "</RecipientStatuses>\n"
        + "<TimeGenerated>2016-01-01T01:07:04.1479113</TimeGenerated>\n"
        + "<EnvelopeID>7a5828cb-2fe2-4c30-aa19-330a24fe780e</EnvelopeID>\n"
        + "<Subject>Please sign the NDA document</Subject>\n"
        + "<UserName>Recipe Login</UserName>\n"
        + "<Email>temp2+recipe@kluger.com</Email>\n"
        + "<Status>Completed</Status>\n"
        + "<Created>2016-01-01T01:06:06.18</Created>\n"
        + "<Sent>2016-01-01T01:06:44.863</Sent>\n"
        + "<Delivered>2016-01-01T01:06:44.863</Delivered>\n"
        + "<Signed>2016-01-01T01:06:44.863</Signed>\n"
        + "<Completed>2016-01-01T01:06:44.863</Completed>\n"
        + "<ACStatus>Original</ACStatus>\n"
        + "<ACStatusDate>2016-01-01T01:06:06.18</ACStatusDate>\n"
        + "<ACHolder>Recipe Login</ACHolder>\n"
        + "<ACHolderEmail>temp2+recipe@kluger.com</ACHolderEmail>\n"
        + "<ACHolderLocation>DocuSign</ACHolderLocation>\n"
        + "<SigningLocation>Online</SigningLocation>\n"
        + "<SenderIPAddress>54.145.219.211</SenderIPAddress>\n"
        + "<EnvelopePDFHash/>\n"
        + "<CustomFields>...</CustomFields>\n"
        + "<AutoNavigation>true</AutoNavigation>\n"
        + "<EnvelopeIdStamping>true</EnvelopeIdStamping>\n"
        + "<AuthoritativeCopy>false</AuthoritativeCopy>\n"
        + "<DocumentStatuses>...</DocumentStatuses>\n"
        + "</EnvelopeStatus>\n"
        + "<TimeZone>Pacific Standard Time</TimeZone>\n"
        + "<TimeZoneOffset>-8</TimeZoneOffset>\n"
        + "</DocuSignEnvelopeInformation>";
    String s = new SimpleDateFormat("yyyy/MM/dd/").format(date);
//    System.out.println("classPath");
    String path = "src/test/resources/"+s+"status.xml";
    try {
      saveContractFromDocusign(param,path);
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public void saveContractFromDocusign(String param, String filePath) {

    BufferedReader bufferedReader = null;
    BufferedWriter bufferedWriter = null;
    File distFile = null;
    try {
      distFile = new File(filePath);
      if (!distFile.getParentFile().exists()) {
        distFile.getParentFile().mkdirs();
      }
      bufferedReader = new BufferedReader(new StringReader(param));
      bufferedWriter = new BufferedWriter(new FileWriter(distFile));
      char buf[] = new char[1024]; // 字符缓冲区
      int len;
      while ((len = bufferedReader.read(buf)) != -1) {
        bufferedWriter.write(buf, 0, len);
      }
      bufferedWriter.flush();
      bufferedReader.close();
      bufferedWriter.close();
    } catch (Exception e) {
      saveContractFromDocusign(param, filePath);
      e.printStackTrace();
    }
  }

}
