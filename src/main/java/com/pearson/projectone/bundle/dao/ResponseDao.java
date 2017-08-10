package com.pearson.projectone.bundle.dao;


import com.pearson.projectone.bundle.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseDao extends JpaRepository<Response, String> {

	public Response findByAcronymAndExamineeId(final String acronym, final String examineeId);
}
