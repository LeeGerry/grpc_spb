package com.me.user.repository;

import com.me.user.entity.GUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<GUser, String> {
}
