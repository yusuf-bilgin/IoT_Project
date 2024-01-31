package com.nht.activitytrackerserver.repository;

import com.nht.activitytrackerserver.entity.AppUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
//    @EntityGraph(attributePaths = "userRoles")
    Optional<AppUser> findByUsername(String username);
}
