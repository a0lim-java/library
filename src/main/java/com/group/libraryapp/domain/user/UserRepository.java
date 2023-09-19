package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { // JpaRepository를 상속받음. User과 id의 타입.
    User findByName(String name);

}
