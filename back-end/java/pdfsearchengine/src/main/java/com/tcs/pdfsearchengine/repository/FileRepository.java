package com.tcs.pdfsearchengine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.pdfsearchengine.model.FileModel;

@Repository
public interface FileRepository extends JpaRepository<FileModel, Long>{
		
	Optional<FileModel> findByName(String name);
}