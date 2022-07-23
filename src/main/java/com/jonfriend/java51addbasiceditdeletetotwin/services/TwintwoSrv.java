package com.jonfriend.java51addbasiceditdeletetotwin.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jonfriend.java51addbasiceditdeletetotwin.models.TwintwoMdl;
import com.jonfriend.java51addbasiceditdeletetotwin.models.TwinoneMdl;
import com.jonfriend.java51addbasiceditdeletetotwin.repositories.TwintwoRpo;

@Service
public class TwintwoSrv {
	
	// adding the twintwo repository as a dependency
	private final TwintwoRpo twintwoRpo;
	
	public TwintwoSrv (TwintwoRpo twintwoRpo) {this.twintwoRpo = twintwoRpo;}
	
	// creates one twintwo >> rename as createNew
	public TwintwoMdl addTwintwo(TwintwoMdl x) {
		return twintwoRpo.save(x);
	}

	// updates one twintwo >> rename as update
	public TwintwoMdl updateTwintwo(TwintwoMdl x) {
		return twintwoRpo.save(x);
	}
	
	// delete twintwo by id >> rename as delete
	// JRF: this srv is very different from myu publicationSrv.delete.  what gives? 
	public void deleteTwintwo(TwintwoMdl x) {
		twintwoRpo.delete(x);
	}
	
	// returns one twintwo by id >> no need rename
	public TwintwoMdl findById(Long id) {
		Optional<TwintwoMdl> optionalTwintwo = twintwoRpo.findById(id);
		if(optionalTwintwo.isPresent()) {
			return optionalTwintwo.get();
		}else {
			return null;
		}
	}
	
	// returns all twintwo >> RENAME AS returnAll
//	public List<TwintwoMdl> allCategories(){
	public List<TwintwoMdl> returnAll(){
		return twintwoRpo.findAll();
	}
	
	// get all joined twinone >> rename as returnJoinedTwinone
	public List<TwintwoMdl> getAssignedTwinones(TwinoneMdl x){
		return twintwoRpo.findAllByTwinoneMdl(x);
	}
	
	// get all un-joined twinone >> rename as returnNotJoinedTwinone
	public List<TwintwoMdl> getUnassignedTwinones(TwinoneMdl x){
		return twintwoRpo.findByTwinoneMdlNotContains(x);
	}
	
// end srv
}