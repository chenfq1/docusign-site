package com.sagedo.dom4jTest;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
 * Created by chenfuqiang on 2018/11/28.
 */
public class Dom4jTest {

  @Test
  public void getRootElement() {
    File inputXml = new File("/Users/apple/Desktop/status.xml");
    SAXReader saxReader = new SAXReader();
    try {
      Document document = saxReader.read(inputXml);
      Element root = document.getRootElement();
//      System.out.println(root.getName());

      List<Element> elements = root.elements();
      for(Iterator<Element> it = elements.iterator();it.hasNext();){
        Element element = it.next();
        System.out.println(element.getName()+" : "+element.getTextTrim());
      }
    } catch (DocumentException e) {
      System.out.println(e.getMessage());
    }
    System.out.println("dom4j parserXml");
  }

  /**
   * 获取EnvelopStatus节点的所有元素
   */
  @Test
  public void getEnvelopElement() {
    File inputXml = new File("/Users/apple/Desktop/status.xml");
    SAXReader saxReader = new SAXReader();
    try {
      Document document = saxReader.read(inputXml);
      Element root = document.getRootElement();
//      System.out.println(root.getName());

//      List<Element> elements = root.elements();
      List<Element> envelopeStatus = root.element("EnvelopeStatus").elements();
      for(Iterator<Element> it = envelopeStatus.iterator();it.hasNext();){
        Element element = it.next();
        System.out.println(element.getName()+" : "+element.getTextTrim());
      }
    } catch (DocumentException e) {
      System.out.println(e.getMessage());
    }
    System.out.println("dom4j parserXml");
  }

  /**
   * 获取RecipientStatus节点下所有的元素
   */
  @Test
  public void getRecipientStatusElement() {
    File inputXml = new File("/Users/apple/Desktop/statusDemo.xml");
    SAXReader saxReader = new SAXReader();
    try {
      Document document = saxReader.read(inputXml);
      Element root = document.getRootElement();
//      System.out.println(root.getName());

//      List<Element> elements = root.elements();
      List<Element> envelopeStatus = root.element("EnvelopeStatus")
          .element("RecipientStatuses").elements();
//          .element("RecipientStatus")
//          .elements();
      for(Iterator<Element> it = envelopeStatus.iterator();it.hasNext();){
        List<Element> element = it.next().elements();
        for (Iterator<Element> recipient = element.iterator();recipient.hasNext();) {
          Element element1 = recipient.next();
          System.out.println(element1.getName() + " : " + element1.getTextTrim());
        }
        System.out.println();
      }
    } catch (DocumentException e) {
      System.out.println(e.getMessage());
    }
    System.out.println("dom4j parserXml");
  }

  /**
   * 获取Envelop下的指定元素
   */
  @Test
  public void parserEnveloeStatus() {
    File inputXml = new File("/Users/apple/Desktop/status.xml");
    SAXReader saxReader = new SAXReader();
    try {
      Document document = saxReader.read(inputXml);
      Element root = document.getRootElement();

      Iterator envelopeStatus = root.elementIterator("EnvelopeStatus");

      while (envelopeStatus.hasNext()){
        Element element = (Element) envelopeStatus.next();
        System.out.println("sender Email: " + element.elementText("Email"));
        System.out.println("EnvelopeID: " + element.elementText("EnvelopeID"));
        System.out.println("UserName: " + element.elementText("UserName"));
        System.out.println("Status: " + element.elementText("Status"));
        System.out.println();
      }
    } catch (DocumentException e) {
      System.out.println(e.getMessage());
    }
    System.out.println("dom4j parserXml");
  }

  /**
   * 获取recipients下的指定元素
   */
  @Test
  public void parserRecipientsStatus() {
    File inputXml = new File("/Users/apple/Desktop/statusDemo.xml");
    SAXReader saxReader = new SAXReader();
    try {
      Document document = saxReader.read(inputXml);
      Element root = document.getRootElement();

      Iterator envelopeStatus = root.elementIterator("EnvelopeStatus");

      while (envelopeStatus.hasNext()){
        Element element = (Element) envelopeStatus.next();
        Iterator recipientStatuses = element.elementIterator("RecipientStatuses");
        while (recipientStatuses.hasNext()) {
          Element recipient = (Element) recipientStatuses.next();
          Iterator recipientStatus = recipient.elementIterator("RecipientStatus");
          while (recipientStatus.hasNext()){
            Element recipientInfo = (Element) recipientStatus.next();

            System.out.println("recipient Email: " + recipientInfo.elementText("Email"));
            System.out.println("type: " + recipientInfo.elementText("Type"));
            System.out.println("recipient UserName: " + recipientInfo.elementText("UserName"));
            System.out.println("Status: " + recipientInfo.elementText("Status"));
            System.out.println();
          }
        }
      }
    } catch (DocumentException e) {
      System.out.println(e.getMessage());
    }
    System.out.println("dom4j parserXml");
  }

}
