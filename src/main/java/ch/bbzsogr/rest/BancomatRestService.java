package ch.bbzsogr.rest;

import ch.bbzsogr.bi.decorators.Service;
import ch.bbzsogr.bi.interfaces.services.BancomatServiceInterface;
import ch.bbzsogr.bi.models.enums.ApiType;

@Service(api = ApiType.REST)
public class BancomatRestService implements BancomatServiceInterface {
}
