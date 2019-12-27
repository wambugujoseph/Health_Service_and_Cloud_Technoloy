package com.cloud.health.mainservice.repository;

import com.cloud.health.mainservice.model.entity.AccessContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 12/23/2019
 * Time: 12:43 PM
 * Project: cloudHealthMainService
 */

public interface AccessGrantsRepository extends JpaRepository<AccessContractEntity, Integer> {

   List<AccessContractEntity> findByUserByUserId(String userId);
   AccessContractEntity findByTokenIsContaining(String token);
//   @Query("update access_contract SET access_contract.active = t")
//   boolean activateAccessContract();
}
