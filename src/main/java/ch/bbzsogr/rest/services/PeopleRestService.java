package ch.bbzsogr.rest.services;

import ch.bbzsogr.bi.decorators.Service;
import ch.bbzsogr.bi.exceptions.EntitySaveException;
import ch.bbzsogr.bi.exceptions.PersonUserDetailsUpdateException;
import ch.bbzsogr.bi.interfaces.services.PeopleServiceInterface;
import ch.bbzsogr.bi.models.Person;
import ch.bbzsogr.bi.models.enums.ApiType;
import ch.bbzsogr.rest.abstracts.RestService;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

@Service(api = ApiType.REST)
public class PeopleRestService extends RestService implements PeopleServiceInterface {

  @Override
  public Person getPerson(String id) throws Exception {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("people")
              .addPathSegment(id)
              .build()
          )
          .get()
          .build()
      ).execute();

    if (response.isSuccessful())
      return getMoshiAdapter()
        .adapter(Person.class)
        .fromJson(response.body().source());

    // TODO create exception
    throw new Exception("Nope");
  }

  @Override
  public void deletePerson(Person person) throws Exception {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("people")
              .addPathSegment(person.getId())
              .build()
          )
          .delete()
          .build()
      ).execute();

    if (response.isSuccessful()) return;

    // TODO create exception
    throw new Exception("Nope");
  }

  @Override
  public Person getPersonByMail(String email) throws Exception {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("people")
              .addPathSegment("email")
              .addPathSegment(email)
              .build()
          )
          .get()
          .build()
      ).execute();

    if (response.isSuccessful())
      return getMoshiAdapter()
        .adapter(Person.class)
        .fromJson(response.body().source());

    // TODO create exception
    throw new Exception("Nope");
  }

  @Override
  public Person authenticate(String email, String password) throws Exception {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("people")
              .addPathSegment(email)
              .addPathSegment("authenticate")
              .build()
          )
          .post(
            RequestBody.create(
              password,
              JSON
            )
          )
          .build()
      ).execute();

    if (response.isSuccessful())
      return getMoshiAdapter()
        .adapter(Person.class)
        .fromJson(response.body().source());

    // TODO create exception
    throw new Exception("Nope");
  }

  @Override
  public Person createPerson(Person person) throws EntitySaveException, IOException {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("people")
              .build()
          )
          .post(
            RequestBody.create(
              getMoshiAdapter()
                .adapter(Person.class)
                .toJson(person),
              JSON
            )
          )
          .build()
      ).execute();

    if (response.isSuccessful())
      return getMoshiAdapter()
        .adapter(Person.class)
        .fromJson(response.body().source());

    throw new EntitySaveException(Person.class);
  }

  @Override
  public Person updatePersonalDetails(Person person) throws PersonUserDetailsUpdateException, IOException {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("people")
              .addPathSegment(person.getId())
              .build()
          )
          .put(
            RequestBody.create(
              getMoshiAdapter()
                .adapter(Person.class)
                .toJson(person),
              JSON
            )
          )
          .build()
      ).execute();

    if (response.isSuccessful())
      return getMoshiAdapter()
        .adapter(Person.class)
        .fromJson(response.body().source());

    throw new PersonUserDetailsUpdateException(person);
  }

}
