package com.anurag.springbootapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.anurag.springbootapp.domain.User;
import com.anurag.springbootapp.domain.UserAddress;

@Repository
@Transactional
public class UserJpaDAO {
	
	//entityManager is an interface to the Persistence Context
	@PersistenceContext
	protected EntityManager entityManager;

	public long insertUser(User user){
		
		//User user1=new User("Sushant","bollywood");
		//User user2=new User("NepotisticChild", "bollywood");
		
		
		//now, user instance is persisted and managed by entity manager
		//i.e. entityManger persists user in database and starts tracking it
		// within the persistence context
		entityManager.persist(user);
		
		//Note: entity manager only tracks those objects in persistence context which are persisted through it
		//so it will track user1 but not user2 !!
		//user1.setDepartment("heaven");
		//user2.setDepartment("hell");
		return user.getEid();
	}

	public User findUser(Long eId) {
		return entityManager.find(User.class, eId);
	}
	
	public User updateUser(User user) {
		return entityManager.merge(user);
	}

	public void insertUserAddress(UserAddress address) {
		entityManager.persist(address);
	}
	public void mergeUserAddress(UserAddress address) {
		entityManager.merge(address);
	}

	public List findAllData() {
		Query query=entityManager.createNativeQuery("select * from user");
		return query.getResultList();
	}
	

		
}
