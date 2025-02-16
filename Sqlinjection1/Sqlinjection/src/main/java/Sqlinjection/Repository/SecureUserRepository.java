package Sqlinjection.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Sqlinjection.Entity.User;

import java.util.List;

@Repository
public interface SecureUserRepository extends JpaRepository<User, Long> {


    List<User> findByUsername(String username);
}