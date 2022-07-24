package com.jonfriend.java51addbasiceditdeletetotwin.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jonfriend.java51addbasiceditdeletetotwin.models.TwintwoMdl;
import com.jonfriend.java51addbasiceditdeletetotwin.models.TwinoneMdl;
import com.jonfriend.java51addbasiceditdeletetotwin.repositories.TwinoneRpo;

@Service
public class TwinoneSrv {
	
	// adding the twinone repository as a dependency
	private final TwinoneRpo twinoneRpo;
	
	public TwinoneSrv(TwinoneRpo twinoneRpo) {this.twinoneRpo = twinoneRpo;}
	
	// creates one twinone >> rename as createNew
	public TwinoneMdl addTwinone(TwinoneMdl x) {
		return twinoneRpo.save(x);
	}

	// updates one twinone >> rename as update
//	public TwinoneMdl updateTwinone(TwinoneMdl x) {
	public TwinoneMdl update(TwinoneMdl x) {
		return twinoneRpo.save(x);
	}
	
	// delete twinone by id >> rename as delete
	// JRF: this srv is very different from myu publicationSrv.delete.  what gives?
	public void deleteTwinone(TwinoneMdl x) {
		twinoneRpo.delete(x);
	}
	
	// returns one twinone by id >> no need rename
	public TwinoneMdl findById(Long id) {
		Optional<TwinoneMdl> optionalTwinone = twinoneRpo.findById(id);
		if(optionalTwinone.isPresent()) {
			return optionalTwinone.get();
		}else {
			return null;
		}
	}
	
	// returns all twinone >> RENAME AS returnAll
//	public List<TwinoneMdl> allTwinones(){
	public List<TwinoneMdl> returnAll(){
		return twinoneRpo.findAll();
	}
	
	// get all joined twinone >> rename as returnJoinedTwintwo
	public List<TwinoneMdl> getAssignedCategories(TwintwoMdl x){
//		return twinoneRpo.findAllByCategories(x);
		return twinoneRpo.findAllByTwintwoMdl(x);
	}
	
	// get all un-joined twinone >> rename as returnNotJoinedTwinone
	public List<TwinoneMdl> getUnassignedCategories(TwintwoMdl x){
//		return twinoneRpo.findByCategoriesNotContains(x);
		return twinoneRpo.findByTwintwoMdlNotContains(x);
	}
	
	// this is for removing a twinone-twintwo join record/entry
	// we are approaching the targeted bomb site (join entry) from the twinone air force base.
	
	public void removeTwinoneTwintwoJoin(TwintwoMdl c, TwinoneMdl p ) {
		List<TwintwoMdl> twintwoList = p.getTwintwoMdl(); 
		twintwoList.remove(c); 
		this.twinoneRpo.save(p); 
	}
	
	
}