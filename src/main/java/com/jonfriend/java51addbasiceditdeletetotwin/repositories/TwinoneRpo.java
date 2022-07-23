package com.jonfriend.java51addbasiceditdeletetotwin.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonfriend.java51addbasiceditdeletetotwin.models.TwintwoMdl;
import com.jonfriend.java51addbasiceditdeletetotwin.models.TwinoneMdl;

@Repository
public interface TwinoneRpo extends CrudRepository<TwinoneMdl, Long> {
	
	List<TwinoneMdl> findAll();
	
	TwinoneMdl findByIdIs(Long id);
	
//	List<TwinoneMdl> findAllByCategories(TwintwoMdl twintwoMdl);
	List<TwinoneMdl> findAllByTwintwoMdl(TwintwoMdl twintwoMdl);
	
//	List<TwinoneMdl> findByCategoriesNotContains(TwintwoMdl twintwoMdl);
	List<TwinoneMdl> findByTwintwoMdlNotContains(TwintwoMdl twintwoMdl);
}
