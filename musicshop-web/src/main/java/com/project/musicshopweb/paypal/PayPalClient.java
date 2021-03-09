package com.project.musicshopweb.paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;

public class PayPalClient {

  /**
   *Set up the PayPal Java SDK environment with PayPal access credentials.  
   *This sample uses SandboxEnvironment. In production, use LiveEnvironment.
   */
  private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
    "AZaWRXaNB2mGxt4ZW3W_z0prFSUSZE7-fcwVhnX-VnwCONNEDHHoZRXI7g5sAnz7FsPjsBrmtliNlqZ_",
    "ECIV7LYV2-n67uKuWtzCQ1MZmeGSYcfDVzLpF1lecW8uXJbwIqB0_G0X3CpeKsJqqsVn_vx_QIXKsACd");

  /**
   *PayPal HTTP client instance with environment that has access
   *credentials context. Use to invoke PayPal APIs.
   */
  PayPalHttpClient client = new PayPalHttpClient(environment);

  /**
   *Method to get client object
   *
   *@return PayPalHttpClient client
   */
  public PayPalHttpClient client() {
    return this.client;
  }
}