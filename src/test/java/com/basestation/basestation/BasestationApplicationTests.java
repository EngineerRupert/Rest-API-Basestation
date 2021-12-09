package com.basestation.basestation;

import com.basestation.basestation.dao.BaseStationDao;
import com.basestation.basestation.dao.MobileStationDao;
import com.basestation.basestation.dao.ReportsDao;
import com.basestation.basestation.model.BaseStation;
import com.basestation.basestation.model.MobileStation;
import com.basestation.basestation.model.Report;
import com.basestation.basestation.service.Reports;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;
import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BasestationApplicationTests {

	@Autowired
	private MobileStationDao mobileStationDao;

	@Autowired
	private BaseStationDao baseStationDao;

	@Autowired
	private ReportsDao reportsDao;

	@Test
	void createMobileStation() {
		MobileStation mobileStation = mobileStationDao.create(4,5);
		Assertions.assertNotNull(mobileStation);
		Assertions.assertEquals(4, mobileStation.getLastKnownX());
		Assertions.assertEquals(5, mobileStation.getLastKnownY());
	}

	@Test
	void findAllMobileStation() {
		MobileStation mobileStation1 = mobileStationDao.create(4,5);
		MobileStation mobileStation2 = mobileStationDao.create(4,5);
		Assertions.assertNotNull(mobileStation1);
		Assertions.assertNotNull(mobileStation2);

		List<MobileStation> listOfMobileStation = mobileStationDao.findAll();
		Assertions.assertEquals(2, listOfMobileStation.size());
		Assertions.assertEquals(mobileStation1, listOfMobileStation.get(0));
		Assertions.assertEquals(mobileStation2, listOfMobileStation.get(1));
	}

	@Test
	void reports() {
		//создание и сверка стационарной станции
		BaseStation baseStation = new BaseStation("BS1", 5, 5, 10);
		Assertions.assertNotNull(baseStation);
		BaseStation baseStationResult = baseStationDao.create(baseStation);
		Assertions.assertNotNull(baseStationResult);
		Assertions.assertEquals(baseStation.getName(), baseStationResult.getName());
		Assertions.assertEquals(baseStation.getX(), baseStationResult.getX());
		Assertions.assertEquals(baseStation.getY(), baseStationResult.getY());
		Assertions.assertEquals(1, baseStationResult.getId());

		//создание и сверка мобильной станции
		MobileStation mobileStation = mobileStationDao.create(6, 6);
		Assertions.assertNotNull(mobileStation);
		Assertions.assertEquals(6, mobileStation.getLastKnownY());
		Assertions.assertEquals(6, mobileStation.getLastKnownX());
		Assertions.assertEquals(2, mobileStation.getId());

		//создание, нахождение и сверка репортов
		String date = String.valueOf(new Date());
		reportsDao.create(mobileStation.getId(), 1.41, date, baseStationResult);
		List<Report> reportsList = reportsDao.findAllReports();
		Assertions.assertEquals(2, reportsList.get(0).getMobileStationId());
		Assertions.assertEquals(baseStationResult.getId(), reportsList.get(0).getBaseStation().getId());
		Assertions.assertEquals((float) 1.41, reportsList.get(0).getDistance());
		Assertions.assertEquals(date, reportsList.get(0).getTimestamp());

		//создание, нахождение и сверка репортов по ид стац. станц.
//		List<Report> reportList = reportsDao.findAllReportsByBaseStationId(baseStation.getId());
//		Assertions.assertEquals(2, reportsList.get(0).getMobileStationId());

//		List<Reports> reports = mobileStationDao.mobileStationPositionReport(mobileStation);
//		Assertions.assertNotNull(reports);
//		reports.stream().forEach(e -> Assertions.assertEquals(1, e.getBaseStationId()));

	}

}
