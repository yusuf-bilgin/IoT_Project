package com.nht.activitytrackerserver.repository;

import com.nht.activitytrackerserver.constants.Role;
import com.nht.activitytrackerserver.entity.AppUser;
import com.nht.activitytrackerserver.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findUserRoleByRole(Role role);
}
