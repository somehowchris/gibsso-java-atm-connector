package ch.bbzsogr.rest.services;

import ch.bbzsogr.bi.decorators.Service;
import ch.bbzsogr.bi.exceptions.EntitySaveException;
import ch.bbzsogr.bi.exceptions.EntityUpdateException;
import ch.bbzsogr.bi.interfaces.services.BancomatServiceInterface;
import ch.bbzsogr.bi.models.Bancomat;
import ch.bbzsogr.bi.models.BillCollection;
import ch.bbzsogr.bi.models.enums.ApiType;
import ch.bbzsogr.rest.abstracts.RestService;
import com.squareup.moshi.Types;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

@Service(api = ApiType.REST)
public class BancomatRestService extends RestService implements BancomatServiceInterface {

  @Override
  public List<BillCollection> getAvailableBills(String bancomatId) throws Exception {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("bancomats")
              .addPathSegment("bills")
              .addPathSegment(bancomatId)
              .build()
          ).build()
      ).execute();

    if (response.isSuccessful())
      return (List<BillCollection>) getMoshiAdapter()
        .adapter(Types.newParameterizedType(List.class, BillCollection.class))
        .fromJson(response.body().source());

    // TODO create exception
    throw new Exception("Nope");
  }

  @Override
  public Bancomat registerBancomat(String location) throws EntitySaveException, IOException {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("bancomats")
              .build()
          )
          .post(
            RequestBody.create(
              location,
              JSON
            )
          )
          .build()
      ).execute();

    if (response.isSuccessful())
      return getMoshiAdapter()
        .adapter(Bancomat.class)
        .fromJson(response.body().source());

    throw new EntitySaveException(Bancomat.class);
  }

  @Override
  public Bancomat updateBancomat(Bancomat bancomat) throws EntityUpdateException, IOException {
    Response response = this.getClient()
      .newCall(
        this.createRequestBuilder()
          .url(
            createUrl()
              .addPathSegment("bancomats")
              .addPathSegment(bancomat.getId())
              .build()
          )
          .put(
            RequestBody.create(
              getMoshiAdapter()
                .adapter(Bancomat.class)
                .toJson(bancomat),
              JSON
            )
          )
          .build()
      ).execute();

    if (response.isSuccessful())
      return getMoshiAdapter()
        .adapter(Bancomat.class)
        .fromJson(response.body().source());

    throw new EntityUpdateException(Bancomat.class);
  }

}
