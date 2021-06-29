package com.camper.camport.repository.camport;

import com.camper.camport.entity.Camport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CamportRepository extends JpaRepository<Camport, Long>, CamportRepositoryCustom{

}
