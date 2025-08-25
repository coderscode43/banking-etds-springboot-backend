//package domain.in.rjsa.dao.impl;
//
//import java.util.Date;
//import java.util.HashMap;
//
//import javax.persistence.Query;
//import javax.persistence.criteria.CriteriaQuery;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//import org.springframework.stereotype.Repository;
//
//import domain.in.rjsa.dao.AbstractDao;
//import domain.in.rjsa.model.form.PersistentLogin;
//
//@Repository("tokenRepositoryDao")
//public class HibernateTokenRepositoryImpl extends AbstractDao<String, PersistentLogin>
//implements PersistentTokenRepository{
//	
//	
//	
// 
//    static final Logger logger = LoggerFactory.getLogger(HibernateTokenRepositoryImpl.class);
// 
//    @Override
//    public void createNewToken(PersistentRememberMeToken token) {
//        logger.info("Creating Token for user : {}", token.getUsername());
//        PersistentLogin persistentLogin = new PersistentLogin();
//        persistentLogin.setUsername(token.getUsername());
//        persistentLogin.setSeries(token.getSeries());
//        persistentLogin.setToken(token.getTokenValue());
//        persistentLogin.setLast_used(token.getDate());
//        getSession().persist(persistentLogin);
// 
//    }
// 
//    @Override
//    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
//        logger.info("Fetch Token if any for seriesId : {}", seriesId);
//        try {
//        	HashMap<String, Object> hMap = new HashMap<>();
//        	hMap.put("series", seriesId);
//        	CriteriaQuery criteria = createEntityCriteria(hMap);
//        	criteria.distinct(true);
//    		Query query = getSession().createQuery(criteria);
//            PersistentLogin persistentLogin = (PersistentLogin) query.getSingleResult();;
// 
//            return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
//                    persistentLogin.getToken(), persistentLogin.getLast_used());
//        } catch (Exception e) {
//            logger.info("Token not found...");
//            return null;
//        }
//    }
// 
//    @Override
//    public void removeUserTokens(String username) {
//        logger.info("Removing Token if any for user : {}", username);
//        HashMap<String, Object> hMap = new HashMap<>();
//    	hMap.put("username", username);
//    	CriteriaQuery criteria = createEntityCriteria(hMap);
//    	criteria.distinct(true);
//		Query query = getSession().createQuery(criteria);
//		PersistentLogin persistentLogin = (PersistentLogin) query.getSingleResult();
//        
//        if (persistentLogin != null) {
//            logger.info("rememberMe was selected");
//            getSession().delete(persistentLogin);
//        }
// 
//    }
// 
//    @Override
//    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
//        logger.info("Updating Token for seriesId : {}", seriesId);
//        HashMap<String,Object> propertyNameValues = new HashMap<String, Object>();
//		propertyNameValues.put("series", seriesId);
//		CriteriaQuery criteria = createEntityCriteria(propertyNameValues);
//    	criteria.distinct(true);
//		Query query = getSession().createQuery(criteria);
//		PersistentLogin persistentLogin = (PersistentLogin) query.getSingleResult();
//        persistentLogin.setToken(tokenValue);
//        persistentLogin.setLast_used(lastUsed);
//        update(persistentLogin);
//    }
// 
//}

package domain.in.rjsa.dao.impl;

import java.util.Date;
import java.util.HashMap;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDao;
import domain.in.rjsa.model.form.PersistentLogin;

@Repository("tokenRepositoryDao")
public class HibernateTokenRepositoryImpl extends AbstractDao<String, PersistentLogin>
        implements PersistentTokenRepository {

    static final Logger logger = LoggerFactory.getLogger(HibernateTokenRepositoryImpl.class);

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        logger.info("Creating Token for user : {}", token.getUsername());
        PersistentLogin persistentLogin = new PersistentLogin();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLast_used(token.getDate());
        getEntityManager().persist(persistentLogin);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        logger.info("Fetch Token if any for seriesId : {}", seriesId);
        try {
            HashMap<String, Object> hMap = new HashMap<>();
            hMap.put("series", seriesId);

            CriteriaQuery<PersistentLogin> criteria = createEntityCriteria(hMap);
            criteria.distinct(true);

            TypedQuery<PersistentLogin> query = getEntityManager().createQuery(criteria);
            PersistentLogin persistentLogin = query.getSingleResult();

            return new PersistentRememberMeToken(
                    persistentLogin.getUsername(),
                    persistentLogin.getSeries(),
                    persistentLogin.getToken(),
                    persistentLogin.getLast_used()
            );
        } catch (Exception e) {
            logger.info("Token not found...");
            return null;
        }
    }

    @Override
    public void removeUserTokens(String username) {
        logger.info("Removing Token if any for user : {}", username);
        HashMap<String, Object> hMap = new HashMap<>();
        hMap.put("username", username);

        CriteriaQuery<PersistentLogin> criteria = createEntityCriteria(hMap);
        criteria.distinct(true);

        TypedQuery<PersistentLogin> query = getEntityManager().createQuery(criteria);
        PersistentLogin persistentLogin = query.getSingleResult();

        if (persistentLogin != null) {
            logger.info("rememberMe was selected");
            getEntityManager().remove(persistentLogin);
        }
    }

    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        logger.info("Updating Token for seriesId : {}", seriesId);

        HashMap<String, Object> propertyNameValues = new HashMap<>();
        propertyNameValues.put("series", seriesId);

        CriteriaQuery<PersistentLogin> criteria = createEntityCriteria(propertyNameValues);
        criteria.distinct(true);

        TypedQuery<PersistentLogin> query = getEntityManager().createQuery(criteria);
        PersistentLogin persistentLogin = query.getSingleResult();

        persistentLogin.setToken(tokenValue);
        persistentLogin.setLast_used(lastUsed);

        update(persistentLogin);
    }
}

