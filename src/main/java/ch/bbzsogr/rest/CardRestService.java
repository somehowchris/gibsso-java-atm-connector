package ch.bbzsogr.rest;

import ch.bbzsogr.bi.decorators.Service;
import ch.bbzsogr.bi.interfaces.services.CardServiceInterface;
import ch.bbzsogr.bi.models.enums.ApiType;

@Service(api = ApiType.REST)
public class CardRestService implements CardServiceInterface {
}
