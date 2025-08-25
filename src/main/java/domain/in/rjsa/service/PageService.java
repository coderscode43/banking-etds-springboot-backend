package domain.in.rjsa.service;


public interface PageService {

	String getPage(Long clientId, String entity, String action, String pageParam);
}
