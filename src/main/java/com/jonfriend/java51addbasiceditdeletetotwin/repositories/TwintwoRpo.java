package com.jonfriend.java51addbasiceditdeletetotwin.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonfriend.java51addbasiceditdeletetotwin.models.TwintwoMdl;
import com.jonfriend.java51addbasiceditdeletetotwin.models.TwinoneMdl;

@Repository
public interface TwintwoRpo extends CrudRepository<TwintwoMdl, Long> {
	
	List<TwintwoMdl> findAll();
	
	TwintwoMdl findByIdIs(Long id);
	
//	List<TwintwoMdl> findAllByTwinones(TwinoneMdl twinoneMdl);
	List<TwintwoMdl> findAllByTwinoneMdl(TwinoneMdl twinoneMdl);
	
//	List<TwintwoMdl> findByTwinonesNotContains(TwinoneMdl twinoneMdl);
	List<TwintwoMdl> findByTwinoneMdlNotContains(TwinoneMdl twinoneMdl);
}
