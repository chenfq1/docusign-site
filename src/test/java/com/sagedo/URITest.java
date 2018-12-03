package com.sagedo;

import com.docusign.esign.client.ApiClient;
import java.net.URI;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.junit.Test;

/**
 * Created by chenfuqiang on 2018/11/29.
 */
public class URITest {

  @Test
  public void uriTest(){
    ApiClient apiClient = new ApiClient();
    try {
      String uri = apiClient.getAuthorizationUri();
      URI uri1 = URI.create(uri);
      System.out.println(uri1);
    } catch (OAuthSystemException e) {
      e.printStackTrace();
    }
  }

}
