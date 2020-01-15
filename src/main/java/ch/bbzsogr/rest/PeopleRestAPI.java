package ch.bbzsogr.rest;

import ch.bbzsogr.bi.decorators.Api;
import ch.bbzsogr.bi.interfaces.services.PeopleServiceInterface;
import ch.bbzsogr.bi.models.enums.ApiType;

@Api(type = ApiType.REST)
public class PeopleRestAPI implements PeopleServiceInterface {
}
