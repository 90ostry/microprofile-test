package com.posec.microprofile.test;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.posec.microprofile.test.entity.Token;

@Transactional
@RequestScoped
public class TokenService {

	final static int TOKEN_VALIDATION_SECONDS = 1000;

	@PersistenceContext
	EntityManager em;
	
	public Token generateToken(String deviceId) {
		Date validTo = calculateExpirationDate(new Date());
		String tokenValue = generateToken();
		Token token = new Token(deviceId,tokenValue, validTo );
		return persist(token);
	}
	
	protected Token persist(Token token) {
		return em.merge(token);
	}

	private Date calculateExpirationDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, TOKEN_VALIDATION_SECONDS);
		return cal.getTime();
	}
	
	private String generateToken()
	{
		Random random = new SecureRandom();
		return new BigInteger(130, random).toString(32);	
	}
	
	public Collection<Token> allTokens() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Token> query = builder.createQuery(Token.class);
		Root<Token> root = query.from(Token.class);
		CriteriaQuery<Token> all = query.select(root);
		
		TypedQuery<Token> allQuery = em.createQuery(all);		
		return allQuery.getResultList();
	}

	public Token find(long id) {
		return em.find(Token.class, id);
	}
	
	public void remove(long id)
	{
		Token entity = em.find(Token.class, id);
		em.remove(entity);
	}
	
}
