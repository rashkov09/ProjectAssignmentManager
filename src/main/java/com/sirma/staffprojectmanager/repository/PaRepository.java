package com.sirma.staffprojectmanager.repository;

import com.sirma.staffprojectmanager.model.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaRepository extends JpaRepository<ProjectAssignment,Long> {

}
