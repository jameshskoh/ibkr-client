package com.jameshskoh.secrets;

import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretVersionName;

import java.io.IOException;

public class SecretClient {

  private static final String projectId = "stocks-450610";

  public String getSecret(SecretType secretType) throws IOException {
    try (SecretManagerServiceClient client = SecretManagerServiceClient.create()) {
      SecretVersionName secretVersionName =
          SecretVersionName.of(projectId, secretType.getLabel(), "latest");

      AccessSecretVersionResponse response = client.accessSecretVersion(secretVersionName);

      return response.getPayload().getData().toStringUtf8();
    }
  }

  public enum SecretType {
    STOCKS_DATABASE_URL("stocks-db-url"),
    STOCKS_DATABASE_USER("stocks-db-user"),
    STOCKS_DATABASE_PASSWORD("stocks-db-password");

    private final String label;

    SecretType(String label) {
      this.label = label;
    }

    public String getLabel() {
      return label;
    }
  }
}
