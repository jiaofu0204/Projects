package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.models.Acception;
import org.springframework.data.repository.CrudRepository;

//@Repository
public interface AcceptionRepository extends CrudRepository<Acception, Integer> {
}
