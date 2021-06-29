package com.camper.camport;

import com.camper.camport.entity.CampLocation;
import com.camper.camport.entity.Camport;
import com.camper.camport.entity.Member;
import com.camper.camport.repository.camport.CamportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
class CamportApplicationTests {
	@Autowired
	EntityManager em;

	@Autowired
	CamportRepository camportRepository;

	@Commit
	@Test
	void contextLoads() {
		Member hongseok = new Member();
		hongseok.setName("hongseok");
		em.persist(hongseok);

		CampLocation busan = new CampLocation();
		busan.setCampLocationName("busan");
		busan.setCampLocationAddress("부산광역시");
		em.persist(busan);

		Camport camport = new Camport();
		camport.setMember(hongseok);
		camport.setCampLocation(busan);
		em.persist(camport);

		List<Camport> camports = camportRepository.findAll();

		for (Camport camportTmp: camports) {
			System.out.println(camportTmp.getId());
		}
	}

}
