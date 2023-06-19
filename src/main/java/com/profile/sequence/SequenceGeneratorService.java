package com.profile.sequence;


import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.profile.model.DbSequence;


@Service
public class SequenceGeneratorService {

	@Autowired
	private MongoOperations operation;
	
	public long generateSequence(String sequence) {
		
		Query q = new Query(Criteria.where("id").is(sequence));
		Update update = new Update().inc("seq", 1);

		
		DbSequence counter = operation.findAndModify(q,
				update, 
				DbSequence.class);
		
		return !Objects.isNull(counter)?counter.getSeq():1;
	}

}
