package com.ArtSeeReal.pro.repository.main;

import com.ArtSeeReal.pro.entity.main.UserObj;
import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserObjRepository extends JpaRepository<UserObj,String>, ModuleRepository {

}
