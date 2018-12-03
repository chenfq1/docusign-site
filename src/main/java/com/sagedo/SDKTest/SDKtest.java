package com.sagedo.SDKTest;

import com.docusign.esign.api.*;
import com.docusign.esign.client.*;
import com.docusign.esign.client.auth.AccessTokenListener;
import com.docusign.esign.model.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.annotation.Resource;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.token.BasicOAuthToken;

@Resource
public class SDKtest {


  ///////////////////////////////////////////////////////////////////////////
  public static void main(String[] args) throws IOException, ApiException {

    // integrator keys are created through developer sandbox accounts then migrated
    // to your production account when ready. Note that your integrator key also acts
    // as your client ID during oauth requests
    String IntegratorKey = "02e15e7b-588f-4aff-8462-5e795d30f996";

    // generate a client secret for the integrator key you supply above, again through sandbox admin menu
    String ClientSecret = "56247059-7185-472c-a93f-ba46e3986c49";

    // must match a redirect URI (case-sensitive) you configured on the key
    String RedirectURI = "http://localhost:8080/docusign/callback";

    // use demo authentication server (remove -d for production)
    String AuthServerUrl = "https://account-d.docusign.com";

    // point to the demo (sandbox) environment. For production requests your account sub-domain
    // will vary, you should always use the base URI that is returned from authentication to
    // ensure your integration points to the correct endpoints (in both environments)
    String RestApiUrl = "https://demo.docusign.net/restapi";

    // instantiate the api client and configure auth server
    ApiClient apiClient = new ApiClient(AuthServerUrl, "docusignAccessCode", IntegratorKey, ClientSecret);

    // set the base path for REST API requests
    apiClient.setBasePath(RestApiUrl);

    // configure the authorization flow on the api client
    apiClient.configureAuthorizationFlow(IntegratorKey, ClientSecret, RedirectURI);

    // set as default api client in your configuration
    Configuration.setDefaultApiClient(apiClient);

    ///////////////////////////////////////////////////////////////////////////
    // RUN SAMPLES
    ///////////////////////////////////////////////////////////////////////////

    SDKtest test = new SDKtest();

    // First request an Authorization Code
    test.getAuthCode(apiClient);

    // Once you get an auth code copy into getAuthToken() function and run a sample
//    String accountId = test.getAuthToken(apiClient);

    // Create envelope with embedded recipient
//		test.createEnvelopeWithEmbeddedRecipient(apiClient, accountId);

    // Embedded Recipient View (ie signing URL)
//		test.createRecipientView(apiClient, accountId, envelopeId);

    // Create envelope
//		test.createEnvelope(apiClient, accountId);

    // Create envelope from a template
//		test.createEnvelopeFromTemplate(apiClient, accountId);

    // List and download envelope Documents
//		EnvelopeDocumentsResult docs = test.listEnvelopeDocuments(accountId, envelopeId);
//		test.downloadEnvelopeDocuments(accountId, envelopeId, docs);

    // List envelope recipients
//		test.listRecipients(accountId, envelopeId);

    // Get envelope status and info
//    String envelopeId = "3c756702-ef5b-4b2b-934c-99d031ee80b8";
//		test.getEnvelope(accountId, envelopeId);

    // List status for multiple envelopes
//		test.listMultipleEnvelopeStatuses(accountId);

    // Embedded Sender View (ie the "tag and send" view)
//		test.embeddedSenderView(accountId, envelopeId);

    // Embedded Console View (ie the DocuSign WebApp)
//		test.embeddedConsoleView(accountId);

  }

  ///////////////////////////////////////////////////////////////////////////
  public void getAuthCode(ApiClient apiClient) throws IOException
  {
    try
    {
      // get DocuSign OAuth authorization url
      String oauthLoginUrl = apiClient.getAuthorizationUri();
      // open DocuSign OAuth login in the browser
      System.out.println(oauthLoginUrl);
//      Desktop.getDesktop().browse(URI.create(oauthLoginUrl));

      // 创建HttpClient对象
      HttpClient client = HttpClients.createDefault();

      // 创建POST请求
      HttpGet get = new HttpGet(oauthLoginUrl);

//      // 创建一个List容器，用于存放基本键值对（基本键值对即：参数名-参数值）--> parameters
//      List<NameValuePair> parameters = new ArrayList();
//      parameters.add(new BasicNameValuePair("scope", "all"));
//      parameters.add(new BasicNameValuePair("response_type", "code"));
//      parameters.add(new BasicNameValuePair("redirect_uri", "http://localhost:8080/docusign/callback"));
//      parameters.add(new BasicNameValuePair("client_id","02e15e7b-588f-4aff-8462-5e795d30f996"));
//
//      // 向POST请求中添加消息实体
//      get.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));

      // 得到响应并转化成字符串
      HttpResponse response = client.execute(get);

      //----------判断是否重定向开始
      int code = response.getStatusLine().getStatusCode();
      System.out.println(code);
      System.out.println();
      Header[] header = response.getAllHeaders();
//      String callback = header.getValue();
      for (Header h :
          header) {
        System.out.println(h.getName()+":"+h.getValue());
      }

      System.out.println();
      System.out.println();

      HttpEntity entity = response.getEntity();
      String result = EntityUtils.toString(entity,"UTF-8");
      System.out.println(result);
//      System.out.println(header);

//      if (code == 302) {
//        Header header = response.getFirstHeader("location"); // 跳转的目标地址是在 HTTP-HEAD 中的
//        String callback = header.getValue(); // 这就是跳转后的地址，再向这个地址发出新申请，以便得到跳转后的信息是啥。
//        System.out.println(callback);
//
//        HttpGet redirectGet = new HttpGet(callback);
//        redirectGet.setHeader("Content-Type", "application/json;charset=UTF-8");
//
//        response = client.execute(redirectGet);
//        code = response.getStatusLine().getStatusCode();
//        System.out.println("redirectCode"+code);
//      }

    }
    catch (OAuthSystemException ex)
    {
      System.out.println("Exception: " + ex);
    }
  }

  ///////////////////////////////////////////////////////////////////////////
  //new code
  public String getAuthToken(ApiClient apiClient,String code)
  {
    // IMPORTANT: after the login, DocuSign will send back a fresh authorization code as a query param
    // of the redirect URI. You should set up a route that handles the redirect call to get that code
    // and pass it to token endpoint as shown in the next lines:
    String authCode = code;
    // assign it to the token endpoint
    apiClient.getTokenEndPoint().setCode(authCode);
    // optionally register to get notified when a new token arrives
    apiClient.registerAccessTokenListener(new AccessTokenListener() {
      @Override
      public void notify(BasicOAuthToken token) {
        System.out.println("Got a fresh token: " + token.getAccessToken());
      }
    });
    // following call exchanges the authorization code for an access code and updates
    // the `Authorization: bearer <token>` header on the api client
    apiClient.updateAccessToken();
    // retrieve the base uri for core API service requests
    System.out.println("Getting base_uri...");
    return getBaseUri(apiClient);
  }

  //////////////////////////////////////////////////////////////////////
  //demo get AuthToken code
  public String getAuthToken(ApiClient apiClient)
  {
    // IMPORTANT: after the login, DocuSign will send back a fresh authorization code as a query param
    // of the redirect URI. You should set up a route that handles the redirect call to get that code
    // and pass it to token endpoint as shown in the next lines:
    String code = "[YOUR_AUTHCODE]";
    // assign it to the token endpoint
    apiClient.getTokenEndPoint().setCode(code);
    // optionally register to get notified when a new token arrives
    apiClient.registerAccessTokenListener(new AccessTokenListener() {
      @Override
      public void notify(BasicOAuthToken token) {
        System.out.println("Got a fresh token: " + token.getAccessToken());
      }
    });
    // following call exchanges the authorization code for an access code and updates
    // the `Authorization: bearer <token>` header on the api client
    apiClient.updateAccessToken();
    // retrieve the base uri for core API service requests
    System.out.println("Getting base_uri...");
    return getBaseUri(apiClient);
  }

  ///////////////////////////////////////////////////////////////////////////
  public String getBaseUri(ApiClient apiClient)
  {
    try {
      AuthenticationApi authApi = new AuthenticationApi(apiClient);
      LoginInformation loginInfo = authApi.login();
      // that note user might belong to multiple accounts, here we get first
      String accountId = loginInfo.getLoginAccounts().get(0).getAccountId();
      String baseUrl = loginInfo.getLoginAccounts().get(0).getBaseUrl();
      String[] accountDomain = baseUrl.split("/v2");
      // below code required for production (no effect in demo since all accounts on same domain)
      apiClient.setBasePath(accountDomain[0]);
      Configuration.setDefaultApiClient(apiClient);
      System.out.println("Configuring api client with following base URI: " + accountDomain[0]);
      return accountId;
    }
    catch(Exception ex) {
      System.out.println("Error: " + ex);
      return null;
    }
  }

  ///////////////////////////////////////////////////////////////////////////
  public void createEnvelopeFromTemplate(ApiClient apiClient, String accountId) throws ApiException
  {

    // create a new envelope to manage the signature request
    EnvelopeDefinition envDef = new EnvelopeDefinition();
    envDef.setEmailSubject("DocuSign Java SDK - Sample Signature Request");

    // assign template information including ID and role(s)
    envDef.setTemplateId("ecadcf54-7c65-4377-938f-ff3276cfafc9");

    // create a template role with a valid templateId and roleName and assign signer info
    TemplateRole tRole = new TemplateRole();
    tRole.setRoleName("Recipients1");
    tRole.setName("chenfuqiang");
    tRole.setEmail("1169223843@qq.com");

    // create a list of template roles and add our newly created role
    List<TemplateRole> templateRolesList = new ArrayList<TemplateRole>();
    templateRolesList.add(tRole);

    // assign template role(s) to the envelope
    envDef.setTemplateRoles(templateRolesList);

    // send the envelope by setting |status| to "sent". To save as a draft set to "created"
    envDef.setStatus("sent");

    // instantiate a new EnvelopesApi object
    EnvelopesApi envelopesApi = new EnvelopesApi(apiClient);

    // call the createEnvelope() API
    EnvelopeSummary envelopeSummary = envelopesApi.createEnvelope(accountId, envDef);
    System.out.println("Envelope has been sent to " + tRole.getEmail());
    System.out.println("Envelope summary :"+envelopeSummary);
  }

  ///////////////////////////////////////////////////////////////////////////
  public void createEnvelopeWithEmbeddedRecipient(ApiClient apiClient, String accountId) throws ApiException
  {
    // create a byte array that will hold our document bytes
    byte[] fileBytes = null;
    String pathToDocument = "[PATH/TO/DOCUMENT]";

    try
    {
      String currentDir = System.getProperty("user.dir");

      // read file from a local directory
      Path path = Paths.get(currentDir + pathToDocument);
      fileBytes = Files.readAllBytes(path);
    }
    catch (IOException ioExcp)
    {
      // TODO: handle error
      System.out.println("Exception: " + ioExcp);
      return;
    }

    // create an envelope that will store the document(s), field(s), and recipient(s)
    EnvelopeDefinition envDef = new EnvelopeDefinition();
    envDef.setEmailSubject("Please sign this document sent from Java SDK)");

    // add a document to the envelope
    Document doc = new Document();
    String base64Doc = Base64.getEncoder().encodeToString(fileBytes);
    doc.setDocumentBase64(base64Doc);
    doc.setName("TestFile"); // can be different from actual file name
    doc.setFileExtension(".pdf");
    doc.setDocumentId("1");

    List<Document> docs = new ArrayList<Document>();
    docs.add(doc);
    envDef.setDocuments(docs);

    // add a recipient to sign the document, identified by name and email we used above
    Signer signer = new Signer();
    signer.setEmail("[RECIPIENT_EMAIL]");
    signer.setName("[RECIPIENT_NAME]");
    signer.setRecipientId("1");

    // Must set |clientUserId| to embed the recipient which in turn makes it possible to
    // generate a signing token (link) for them. This is a client defined string value
    // which is also needed to create the recipient view URL in the next step
    signer.setClientUserId("1001");

    // create a |signHere| tab somewhere on the document for the signer to sign
    // default unit of measurement is pixels, can be mms, cms, inches also
    SignHere signHere = new SignHere();
    signHere.setDocumentId("1");
    signHere.setPageNumber("1");
    signHere.setRecipientId("1");
    signHere.setXPosition("100");
    signHere.setYPosition("150");

    // can have multiple tabs, so need to add to envelope as a single element list
    List<SignHere> signHereTabs = new ArrayList<SignHere>();
    signHereTabs.add(signHere);
    Tabs tabs = new Tabs();
    tabs.setSignHereTabs(signHereTabs);
    signer.setTabs(tabs);

    // add recipients (in this case a single signer) to the envelope
    envDef.setRecipients(new Recipients());
    envDef.getRecipients().setSigners(new ArrayList<Signer>());
    envDef.getRecipients().getSigners().add(signer);

    // send the envelope by setting |status| to "sent". To save as a draft set to "created"
    envDef.setStatus("sent");

    try
    {
      // instantiate a new EnvelopesApi object
      EnvelopesApi envelopesApi = new EnvelopesApi();
      // call the createEnvelope() API
      EnvelopeSummary envelopeSummary = envelopesApi.createEnvelope(accountId, envDef);
      System.out.println("EnvelopeSummary: " + envelopeSummary);
    }
    catch (com.docusign.esign.client.ApiException ex)
    {
      System.out.println("Exception: " + ex);
    }

  }

  ///////////////////////////////////////////////////////////////////////////
  public void createRecipientView(ApiClient apiClient, String accountId, String envelopeId) throws ApiException, IOException {

    // instantiate a new EnvelopesApi object
    EnvelopesApi envelopesApi = new EnvelopesApi();

    // set the url where you want the recipient to go once they are done signing
    RecipientViewRequest view = new RecipientViewRequest();
    view.setReturnUrl("https://www.docusign.com");
    view.setAuthenticationMethod("email");

    // recipient information must match embedded recipient info we provided in step #2
    view.setEmail("[RECIPIENT_EMAIL]");
    view.setUserName("[RECIPIENT_NAME]");
    view.setRecipientId("1");
    view.setClientUserId("1001");

    // call the CreateRecipientView API
    ViewUrl recipientView = envelopesApi.createRecipientView(accountId, envelopeId, view);
    System.out.println("Signing URL = " + recipientView.getUrl());
    Desktop.getDesktop().browse(URI.create(recipientView.getUrl()));
  }

  ///////////////////////////////////////////////////////////////////////////
  public void createEnvelope(ApiClient apiClient, String accountId) throws ApiException {

    // create a byte array that will hold our document bytes
    byte[] fileBytes = null;

    String pathToDocument = "[PATH/TO/DOCUMENT]";

    try
    {
      String currentDir = System.getProperty("user.dir");

      // read file from a local directory
      Path path = Paths.get(currentDir + pathToDocument);
      fileBytes = Files.readAllBytes(path);
    }
    catch (IOException ioExcp)
    {
      // TODO: handle error
      System.out.println("Exception: " + ioExcp);
      return;
    }

    // create an envelope that will store the document(s), field(s), and recipient(s)
    EnvelopeDefinition envDef = new EnvelopeDefinition();
    envDef.setEmailSubject("Please sign this document sent from Java SDK)");

    // add a document to the envelope
    Document doc = new Document();
    String base64Doc = Base64.getEncoder().encodeToString(fileBytes);
    doc.setDocumentBase64(base64Doc);
    doc.setName("TestFile"); // can be different from actual file name
    doc.setFileExtension(".pdf");	// update if different extension!
    doc.setDocumentId("1");

    List<Document> docs = new ArrayList<Document>();
    docs.add(doc);
    envDef.setDocuments(docs);

    // add a recipient to sign the document, identified by name and email we used above
    Signer signer = new Signer();
    signer.setEmail("[RECIPIENT_EMAIL]");
    signer.setName("[RECIPIENT_NAME]");
    signer.setRecipientId("1");

    // create a |signHere| tab somewhere on the document for the signer to sign
    // here we arbitrarily place it 100 pixels right, 150 pixels down from top
    // left corner of first page of first envelope document
    SignHere signHere = new SignHere();
    signHere.setDocumentId("1");
    signHere.setPageNumber("1");
    signHere.setRecipientId("1");
    signHere.setXPosition("100");
    signHere.setYPosition("150");

    // can have multiple tabs, so need to add to envelope as a single element list
    List<SignHere> signHereTabs = new ArrayList<SignHere>();
    signHereTabs.add(signHere);
    Tabs tabs = new Tabs();
    tabs.setSignHereTabs(signHereTabs);
    signer.setTabs(tabs);

    // add recipients (in this case a single signer) to the envelope
    envDef.setRecipients(new Recipients());
    envDef.getRecipients().setSigners(new ArrayList<Signer>());
    envDef.getRecipients().getSigners().add(signer);

    // send the envelope by setting |status| to "sent". To save as a draft set to "created" instead
    envDef.setStatus("sent");

    try
    {
      // instantiate a new EnvelopesApi object
      EnvelopesApi envelopesApi = new EnvelopesApi();
      // call the createEnvelope() API
      EnvelopeSummary envelopeSummary = envelopesApi.createEnvelope(accountId, envDef);
      System.out.println("Envelope has been sent to " + signer.getEmail());
      System.out.println("EnvelopeSummary: " + envelopeSummary);
    }
    catch (com.docusign.esign.client.ApiException ex)
    {
      System.out.println("Exception: " + ex);
    }
  }

  ///////////////////////////////////////////////////////////////////////////
  public void embeddedSenderView(String accountId, String envelopeId) throws ApiException {

    // instantiate a new EnvelopesApi object
    EnvelopesApi envelopesApi = new EnvelopesApi();

    // set the url where you want the sender to go once they are done editing/sending the envelope
    ReturnUrlRequest returnUrl = new ReturnUrlRequest();
    returnUrl.setReturnUrl("https://www.docusign.com/devcenter");

    // call the createEnvelope() API
    ViewUrl senderView = envelopesApi.createSenderView(accountId, envelopeId, returnUrl);
    System.out.println("SenderView URL: " + senderView.getUrl());
  }

  ///////////////////////////////////////////////////////////////////////////
  public void embeddedConsoleView(String accountId) throws ApiException {

    // instantiate a new envelopesApi object
    EnvelopesApi envelopesApi = new EnvelopesApi();

    // set the url where you want the user to go once they logout of the Console
    ConsoleViewRequest returnUrl = new ConsoleViewRequest();
    returnUrl.setReturnUrl("https://www.docusign.com/devcenter");

    // call the createConsoleView() API
    ViewUrl consoleView = envelopesApi.createConsoleView(accountId, returnUrl);
    System.out.println("Console URL: " + consoleView.getUrl());
  }

  ///////////////////////////////////////////////////////////////////////////
  public void listMultipleEnvelopeStatuses(String accountId) throws ApiException, UnsupportedEncodingException {

    // instantiate a new EnvelopesApi object
    EnvelopesApi envelopesApi = new EnvelopesApi();

    // the list status changes call requires at least a from_date
    EnvelopesApi.ListStatusChangesOptions options = envelopesApi.new ListStatusChangesOptions();

    // set from date to filter envelopes (ex: Jan 15, 2018)
    String date = "2018/11/23";
    options.setFromDate(date);


    // call the listStatusChanges() API
    EnvelopesInformation envelopes = envelopesApi.listStatusChanges(accountId, options);
//    EnvelopesInformation envelopes = envelopesApi.listStatusChanges(accountId);
    System.out.println("EnvelopesInformation: " + envelopes);
  }

  ///////////////////////////////////////////////////////////////////////////
  public void getEnvelope(String accountId, String envelopeId) throws ApiException {

    // instantiate a new EnvelopesApi object
    EnvelopesApi envelopesApi = new EnvelopesApi();
    // call the getEnvelope() API
    Envelope env = envelopesApi.getEnvelope(accountId, envelopeId);
    System.out.println("EnvelopeInfo = " + env);
  }

  ///////////////////////////////////////////////////////////////////////////
  public void listRecipients(String accountId, String envelopeId) throws ApiException {

    // instantiate a new EnvelopesApi object
    EnvelopesApi envelopesApi = new EnvelopesApi();
    // call the listRecipients() API
    Recipients recips = envelopesApi.listRecipients(accountId, envelopeId);
    System.out.println("EnvelopeRecipients : " + recips);
  }

  ///////////////////////////////////////////////////////////////////////////
  public EnvelopeDocumentsResult listEnvelopeDocuments(String accountId, String envelopeId) throws ApiException {

    // instantiate a new EnvelopesApi object
    EnvelopesApi envelopesApi = new EnvelopesApi();

    // call the listDocuments API to list info about each envelope document
    EnvelopeDocumentsResult docsList = envelopesApi.listDocuments(accountId, envelopeId);
    System.out.println("EnvelopeDocumentsResult: " + docsList);
    return docsList;
  }

  ///////////////////////////////////////////////////////////////////////////
  public void downloadEnvelopeDocuments(String accountId, String envelopeId, EnvelopeDocumentsResult docsList) throws ApiException, IOException {

    // instantiate a new EnvelopesApi object
    EnvelopesApi envelopesApi = new EnvelopesApi();

    // loop through each EnvelopeDocument object
    for( EnvelopeDocument doc: docsList.getEnvelopeDocuments() ) {
      // call the getDocument() API for each document and write to current dir
      Files.write(
          new File(doc.getName()).toPath(),
          envelopesApi.getDocument(accountId, envelopeId, doc.getDocumentId())
      );
    }
  }

}
