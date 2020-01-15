package ch.bbzsogr.rest;

import ch.bbzsogr.bi.decorators.Service;
import ch.bbzsogr.bi.interfaces.services.PeopleServiceInterface;
import ch.bbzsogr.bi.models.enums.ApiType;

@Service(api = ApiType.REST)
public class PeopleRestService implements PeopleServiceInterface {
}
