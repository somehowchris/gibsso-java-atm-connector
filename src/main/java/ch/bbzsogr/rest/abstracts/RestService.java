package ch.bbzsogr.rest.abstracts;

import ch.bbzsogr.rest.models.RestAPIConfig;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import okhttp3.*;

import javax.annotation.Nullable;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class RestService {


  public static final MediaType JSON
    = MediaType.get("application/json; charset=utf-8");
  private final Moshi moshi = new Moshi.Builder()
    .add(Date.class, new DateAdapter())
    .build();
  private OkHttpClient client;

  public RestService() {
    client = new OkHttpClient.Builder()
      .authenticator(new Authenticator() {
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
          if (response.request().header("Authorization") != null) {
            return null; // Give up, we've already attempted to authenticate.
          }

          String credential = Credentials.basic(RestAPIConfig.getUsername(), RestAPIConfig.getPassword());

          if (credential.equals(response.request().header("Authorization"))) {
            return null; // If we already failed with these credentials, don't retry.
          }

          return response.request().newBuilder()
            .header("Authorization", credential)
            .build();
        }
      })
      .build();
  }

  public Request.Builder createRequestBuilder() throws IOException {
    return new Request.Builder();
  }

  public HttpUrl.Builder createUrl() {
    return HttpUrl.get(RestAPIConfig.getBaseUrl()).newBuilder();
  }

  public OkHttpClient getClient() {
    return client;
  }


  public Moshi getMoshiAdapter() {
    return moshi;
  }

  class DateAdapter extends JsonAdapter<Date> {

    @Nullable
    @Override
    public Date fromJson(JsonReader jsonReader) throws IOException {
      if (jsonReader.peek() != JsonReader.Token.NULL) {
        try {
          return new SimpleDateFormat("yyyy-MM-dd HH:mm a z").parse(jsonReader.nextString());
        } catch (ParseException e) {
          jsonReader.nextName();
          return null;
        }
      }

      jsonReader.nextName();
      return null;
    }

    @Override
    public void toJson(JsonWriter jsonWriter, @Nullable Date localDateTime) throws IOException {
      jsonWriter.value(new SimpleDateFormat("yyyy-MM-dd HH:mm a z").format(localDateTime));
    }
  }
}
