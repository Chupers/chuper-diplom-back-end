package com.chuper.diplom.repository;

import com.chuper.diplom.entity.UserFacade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFacadeRepository extends JpaRepository<UserFacade,Long> {

    UserFacade findByUserName(String userName);

}
