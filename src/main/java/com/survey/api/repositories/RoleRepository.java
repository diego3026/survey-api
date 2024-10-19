package com.survey.api.repositories;

import com.survey.api.models.entities.Role;
import com.survey.api.models.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(ERole role);
}
