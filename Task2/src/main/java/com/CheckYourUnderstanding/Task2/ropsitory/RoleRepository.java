package com.CheckYourUnderstanding.Task2.ropsitory;

import com.CheckYourUnderstanding.Task2.entity.RoleEntity;
import com.CheckYourUnderstanding.Task2.util.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.hibernate.hql.internal.antlr.HqlTokenTypes.FROM;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

@Query(value = "SELECT * FROM role r where r.title=:title",nativeQuery = true)
    Optional<RoleEntity> findRoleEntityByTittle(String title);
}

