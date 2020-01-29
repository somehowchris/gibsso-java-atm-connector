package ch.bbzsogr.rest.services;

import ch.bbzsogr.bi.decorators.Service;
import ch.bbzsogr.bi.exceptions.EntityUpdateException;
import ch.bbzsogr.bi.interfaces.services.BillCollectionServiceInterface;
import ch.bbzsogr.bi.models.BillCollection;
import ch.bbzsogr.bi.models.enums.ApiType;
import ch.bbzsogr.rest.abstracts.RestService;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

/**
 * The type Bill collection service.
 */
@Service(api = ApiType.REST)
public class BillCollectionService extends RestService implements BillCollectionServiceInterface {

  @Override
  public BillCollection getBillCollection(String id) throws Exception {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("bill-collections")
              .addPathSegment(id)
              .build()
          ).build()
      ).execute();

    if (response.isSuccessful())
      return getMoshiAdapter()
        .adapter(BillCollection.class)
        .fromJson(response.body().source());

    // TODO create exception
    throw new Exception("Nope");
  }

  @Override
  public BillCollection updateBillCollection(BillCollection bills) throws EntityUpdateException, IOException {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("bill-collections")
              .addPathSegment(bills.getId())
              .build()
          )
          .put(
            RequestBody.create(
              getMoshiAdapter()
                .adapter(BillCollection.class)
                .toJson(bills),
              JSON
            )
          )
          .build()
      ).execute();

    if (response.isSuccessful())
      return getMoshiAdapter()
        .adapter(BillCollection.class)
        .fromJson(response.body().source());

    // TODO create exception
    throw new EntityUpdateException(BillCollection.class);
  }

}
