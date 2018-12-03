package com.sagedo.resource;

import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.client.Configuration;
import com.sagedo.SDKTest.SDKtest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenfuqiang on 2018/11/23.
 */
@RestController
@RequestMapping("/docusign")
public class AuthController {

  @GetMapping("/callback")
  public String callBack(@RequestParam String code){
    System.out.println("code = "+code);

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
    String accountId = test.getAuthToken(apiClient, code);

    //listMultipleEnvelopeStatuses
//    try {
//      test.listMultipleEnvelopeStatuses(accountId);
//    } catch (ApiException e) {
//      e.printStackTrace();
//    } catch (UnsupportedEncodingException e) {
//      e.printStackTrace();
//    }

    //get one envelop
//    String envelopeId = "3c756702-ef5b-4b2b-934c-99d031ee80b8";
//    try {
//      test.getEnvelope(accountId, envelopeId);
//    } catch (ApiException e) {
//      e.printStackTrace();
//    }

    // createEnvelopeFromTemplate
//    try {
//      test.createEnvelopeFromTemplate(apiClient,accountId);
//    } catch (ApiException e) {
//      e.printStackTrace();
//    }

    return code;
  }

  @GetMapping("/status")
  public String status(){
    System.out.println("status");
    return "ok";
  }

  @PostMapping("/status")
  public String getStatus(@RequestBody String param){
//    System.out.println(param);
    return param;
  }

  @GetMapping("/void")
  public void returnVoid(){
    System.out.println("return void");
  }
}
