package ch.bbzsogr.rest;

import ch.bbzsogr.bi.decorators.Api;
import ch.bbzsogr.bi.interfaces.services.BancomatServiceInterface;
import ch.bbzsogr.bi.models.enums.ApiType;

@Api(type = ApiType.REST)
public class BancomatRestAPI implements BancomatServiceInterface {
}
