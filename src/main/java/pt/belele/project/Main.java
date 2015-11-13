package pt.belele.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import pt.belele.project.algorithm.ExcelColumnsCalculation;
import pt.belele.project.algorithm.ExcelRow;
import pt.belele.project.algorithm.PlanBRow;
import pt.belele.project.algorithm.ProfRow;
import pt.belele.project.controllers.FixtureController;
import pt.belele.project.controllers.OddController;
import pt.belele.project.controllers.SeasonController;
import pt.belele.project.controllers.StandingController;
import pt.belele.project.controllers.TeamController;
import pt.belele.project.controllers.util.H2H;
import pt.belele.project.controllers.util.ResultCycle;
import pt.belele.project.controllers.util.TeamRating;
import pt.belele.project.entities.Fixture;
import pt.belele.project.entities.Fixture.FixtureStatus;
import pt.belele.project.entities.Fixture.Venue;
import pt.belele.project.entities.Odd;
import pt.belele.project.entities.Result;
import pt.belele.project.entities.Result.ResultType;
import pt.belele.project.entities.Season;
import pt.belele.project.entities.Team;
import pt.belele.project.util.CSVReader;
import pt.belele.project.util.WriteToExcel;

public class Main {

	private static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yy");

	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("database.odb");
		EntityManager em = emf.createEntityManager();
		// generateDatabase(em);
		createExcels_A_B(em);
	}

	private static void generateDatabase(EntityManager em) {

		// // INGLATERRA - PREMIER LEAGUE
		// populateDatabase(em, "E0.csv", "0001", 0);
		// populateDatabase(em, "E0.csv", "0102", 1);
		// populateDatabase(em, "E0.csv", "0203", 2);
		// populateDatabase(em, "E0.csv", "0304", 3);
		// populateDatabase(em, "E0.csv", "0405", 4);
		// populateDatabase(em, "E0.csv", "0506", 5);
		// populateDatabase(em, "E0.csv", "0607", 6);
		// populateDatabase(em, "E0.csv", "0708", 7);
		// populateDatabase(em, "E0.csv", "0809", 8);
		// populateDatabase(em, "E0.csv", "0910", 9);
		// populateDatabase(em, "E0.csv", "1011", 10);
		// populateDatabase(em, "E0.csv", "1112", 11);
		// populateDatabase(em, "E0.csv", "1213", 12);
		// populateDatabase(em, "E0.csv", "1314", 13);
		// populateDatabase(em, "E0.csv", "1415", 14);
		//
		// // INGLATERRA - CHAMPIONSHIP
		// populateDatabase(em, "E1.csv", "0001", 0);
		// populateDatabase(em, "E1.csv", "0102", 1);
		// populateDatabase(em, "E1.csv", "0203", 2);
		// populateDatabase(em, "E1.csv", "0304", 3);
		// populateDatabase(em, "E1.csv", "0405", 4);
		// populateDatabase(em, "E1.csv", "0506", 5);
		// populateDatabase(em, "E1.csv", "0607", 6);
		// populateDatabase(em, "E1.csv", "0708", 7);
		// populateDatabase(em, "E1.csv", "0809", 8);
		// populateDatabase(em, "E1.csv", "0910", 9);
		// populateDatabase(em, "E1.csv", "1011", 10);
		// populateDatabase(em, "E1.csv", "1112", 11);
		// populateDatabase(em, "E1.csv", "1213", 12);
		// populateDatabase(em, "E1.csv", "1314", 13);
		// populateDatabase(em, "E1.csv", "1415", 14);
		//
		// // INGLATERRA - LEAGUE 1
		// populateDatabase(em, "E2.csv", "0001", 0);
		// populateDatabase(em, "E2.csv", "0102", 1);
		// populateDatabase(em, "E2.csv", "0203", 2);
		// populateDatabase(em, "E2.csv", "0304", 3);
		// populateDatabase(em, "E2.csv", "0405", 4);
		// populateDatabase(em, "E2.csv", "0506", 5);
		// populateDatabase(em, "E2.csv", "0607", 6);
		// populateDatabase(em, "E2.csv", "0708", 7);
		// populateDatabase(em, "E2.csv", "0809", 8);
		// populateDatabase(em, "E2.csv", "0910", 9);
		// populateDatabase(em, "E2.csv", "1011", 10);
		// populateDatabase(em, "E2.csv", "1112", 11);
		// populateDatabase(em, "E2.csv", "1213", 12);
		// populateDatabase(em, "E2.csv", "1314", 13);
		// populateDatabase(em, "E2.csv", "1415", 14);
		//
		// // ESCOCIA - PREMIER LEAGUE
		// populateDatabase(em, "SC0.csv", "0001", 0);
		// populateDatabase(em, "SC0.csv", "0102", 1);
		// populateDatabase(em, "SC0.csv", "0203", 2);
		// populateDatabase(em, "SC0.csv", "0304", 3);
		// populateDatabase(em, "SC0.csv", "0405", 4);
		// populateDatabase(em, "SC0.csv", "0506", 5);
		// populateDatabase(em, "SC0.csv", "0607", 6);
		// populateDatabase(em, "SC0.csv", "0708", 7);
		// populateDatabase(em, "SC0.csv", "0809", 8);
		// populateDatabase(em, "SC0.csv", "0910", 9);
		// populateDatabase(em, "SC0.csv", "1011", 10);
		// populateDatabase(em, "SC0.csv", "1112", 11);
		// populateDatabase(em, "SC0.csv", "1213", 12);
		// populateDatabase(em, "SC0.csv", "1314", 13);
		// populateDatabase(em, "SC0.csv", "1415", 14);
		//
		// // ALEMANHA - BUNDESLIGA 1
		// populateDatabase(em, "D1.csv", "0001", 0);
		// populateDatabase(em, "D1.csv", "0102", 1);
		// populateDatabase(em, "D1.csv", "0203", 2);
		// populateDatabase(em, "D1.csv", "0304", 3);
		// populateDatabase(em, "D1.csv", "0405", 4);
		// populateDatabase(em, "D1.csv", "0506", 5);
		// populateDatabase(em, "D1.csv", "0607", 6);
		// populateDatabase(em, "D1.csv", "0708", 7);
		// populateDatabase(em, "D1.csv", "0809", 8);
		// populateDatabase(em, "D1.csv", "0910", 9);
		// populateDatabase(em, "D1.csv", "1011", 10);
		// populateDatabase(em, "D1.csv", "1112", 11);
		// populateDatabase(em, "D1.csv", "1213", 12);
		// populateDatabase(em, "D1.csv", "1314", 13);
		// populateDatabase(em, "D1.csv", "1415", 14);
		//
		// // ALEMANHA - BUNDESLIGA 2
		// populateDatabase(em, "D2.csv", "0001", 0);
		// populateDatabase(em, "D2.csv", "0102", 1);
		// populateDatabase(em, "D2.csv", "0203", 2);
		// populateDatabase(em, "D2.csv", "0304", 3);
		// populateDatabase(em, "D2.csv", "0405", 4);
		// populateDatabase(em, "D2.csv", "0506", 5);
		// populateDatabase(em, "D2.csv", "0607", 6);
		// populateDatabase(em, "D2.csv", "0708", 7);
		// populateDatabase(em, "D2.csv", "0809", 8);
		// populateDatabase(em, "D2.csv", "0910", 9);
		// populateDatabase(em, "D2.csv", "1011", 10);
		// populateDatabase(em, "D2.csv", "1112", 11);
		// populateDatabase(em, "D2.csv", "1213", 12);
		// populateDatabase(em, "D2.csv", "1314", 13);
		// populateDatabase(em, "D2.csv", "1415", 14);
		//
		// ITALIA - SERIE A
		populateDatabase(em, "I1.csv", "0001", 0);
		populateDatabase(em, "I1.csv", "0102", 1);
		populateDatabase(em, "I1.csv", "0203", 2);
		populateDatabase(em, "I1.csv", "0304", 3);
		populateDatabase(em, "I1.csv", "0405", 4);
		populateDatabase(em, "I1.csv", "0506", 5);
		populateDatabase(em, "I1.csv", "0607", 6);
		populateDatabase(em, "I1.csv", "0708", 7);
		populateDatabase(em, "I1.csv", "0809", 8);
		populateDatabase(em, "I1.csv", "0910", 9);
		populateDatabase(em, "I1.csv", "1011", 10);
		populateDatabase(em, "I1.csv", "1112", 11);
		populateDatabase(em, "I1.csv", "1213", 12);
		populateDatabase(em, "I1.csv", "1314", 13);
		populateDatabase(em, "I1.csv", "1415", 14);
		//
		// // ITALIA - SERIE B
		// populateDatabase(em, "I2.csv", "0001", 0);
		// populateDatabase(em, "I2.csv", "0102", 1);
		// populateDatabase(em, "I2.csv", "0203", 2);
		// populateDatabase(em, "I2.csv", "0304", 3);
		// populateDatabase(em, "I2.csv", "0405", 4);
		// populateDatabase(em, "I2.csv", "0506", 5);
		// populateDatabase(em, "I2.csv", "0607", 6);
		// populateDatabase(em, "I2.csv", "0708", 7);
		// populateDatabase(em, "I2.csv", "0809", 8);
		// populateDatabase(em, "I2.csv", "0910", 9);
		// populateDatabase(em, "I2.csv", "1011", 10);
		// populateDatabase(em, "I2.csv", "1112", 11);
		// populateDatabase(em, "I2.csv", "1213", 12);
		// populateDatabase(em, "I2.csv", "1314", 13);
		// populateDatabase(em, "I2.csv", "1415", 14);
		//
		// // ESPANHA - LA LIGA PRIMERA DIVISION
		// populateDatabase(em, "SP1.csv", "0001", 0);
		// populateDatabase(em, "SP1.csv", "0102", 1);
		// populateDatabase(em, "SP1.csv", "0203", 2);
		// populateDatabase(em, "SP1.csv", "0304", 3);
		// populateDatabase(em, "SP1.csv", "0405", 4);
		// populateDatabase(em, "SP1.csv", "0506", 5);
		// populateDatabase(em, "SP1.csv", "0607", 6);
		// populateDatabase(em, "SP1.csv", "0708", 7);
		// populateDatabase(em, "SP1.csv", "0809", 8);
		// populateDatabase(em, "SP1.csv", "0910", 9);
		// populateDatabase(em, "SP1.csv", "1011", 10);
		// populateDatabase(em, "SP1.csv", "1112", 11);
		// populateDatabase(em, "SP1.csv", "1213", 12);
		// populateDatabase(em, "SP1.csv", "1314", 13);
		// populateDatabase(em, "SP1.csv", "1415", 14);
		//
		// // ESPANHA - LA LIGA SEGUNDA DIVISION
		// populateDatabase(em, "SP2.csv", "0001", 0);
		// populateDatabase(em, "SP2.csv", "0102", 1);
		// populateDatabase(em, "SP2.csv", "0203", 2);
		// populateDatabase(em, "SP2.csv", "0304", 3);
		// populateDatabase(em, "SP2.csv", "0405", 4);
		// populateDatabase(em, "SP2.csv", "0506", 5);
		// populateDatabase(em, "SP2.csv", "0607", 6);
		// populateDatabase(em, "SP2.csv", "0708", 7);
		// populateDatabase(em, "SP2.csv", "0809", 8);
		// populateDatabase(em, "SP2.csv", "0910", 9);
		// populateDatabase(em, "SP2.csv", "1011", 10);
		// populateDatabase(em, "SP2.csv", "1112", 11);
		// populateDatabase(em, "SP2.csv", "1213", 12);
		// populateDatabase(em, "SP2.csv", "1314", 13);
		// populateDatabase(em, "SP2.csv", "1415", 14);
		//
		// // FRANÇA - LE CHAMPIONNAT
		// populateDatabase(em, "F1.csv", "0001", 0);
		// populateDatabase(em, "F1.csv", "0102", 1);
		// populateDatabase(em, "F1.csv", "0203", 2);
		// populateDatabase(em, "F1.csv", "0304", 3);
		// populateDatabase(em, "F1.csv", "0405", 4);
		// populateDatabase(em, "F1.csv", "0506", 5);
		// populateDatabase(em, "F1.csv", "0607", 6);
		// populateDatabase(em, "F1.csv", "0708", 7);
		// populateDatabase(em, "F1.csv", "0809", 8);
		// populateDatabase(em, "F1.csv", "0910", 9);
		// populateDatabase(em, "F1.csv", "1011", 10);
		// populateDatabase(em, "F1.csv", "1112", 11);
		// populateDatabase(em, "F1.csv", "1213", 12);
		// populateDatabase(em, "F1.csv", "1314", 13);
		// populateDatabase(em, "F1.csv", "1415", 14);
		//
		// // FRANÇA - DIVISION2
		// populateDatabase(em, "F2.csv", "0001", 0);
		// populateDatabase(em, "F2.csv", "0102", 1);
		// populateDatabase(em, "F2.csv", "0203", 2);
		// populateDatabase(em, "F2.csv", "0304", 3);
		// populateDatabase(em, "F2.csv", "0405", 4);
		// populateDatabase(em, "F2.csv", "0506", 5);
		// populateDatabase(em, "F2.csv", "0607", 6);
		// populateDatabase(em, "F2.csv", "0708", 7);
		// populateDatabase(em, "F2.csv", "0809", 8);
		// populateDatabase(em, "F2.csv", "0910", 9);
		// populateDatabase(em, "F2.csv", "1011", 10);
		// populateDatabase(em, "F2.csv", "1112", 11);
		// populateDatabase(em, "F2.csv", "1213", 12);
		// populateDatabase(em, "F2.csv", "1314", 13);
		// populateDatabase(em, "F2.csv", "1415", 14);
		//
		// // HOLANDA - EREDIVISE
		// populateDatabase(em, "N1.csv", "0001", 0);
		// populateDatabase(em, "N1.csv", "0102", 1);
		// populateDatabase(em, "N1.csv", "0203", 2);
		// populateDatabase(em, "N1.csv", "0304", 3);
		// populateDatabase(em, "N1.csv", "0405", 4);
		// populateDatabase(em, "N1.csv", "0506", 5);
		// populateDatabase(em, "N1.csv", "0607", 6);
		// populateDatabase(em, "N1.csv", "0708", 7);
		// populateDatabase(em, "N1.csv", "0809", 8);
		// populateDatabase(em, "N1.csv", "0910", 9);
		// populateDatabase(em, "N1.csv", "1011", 10);
		// populateDatabase(em, "N1.csv", "1112", 11);
		// populateDatabase(em, "N1.csv", "1213", 12);
		// populateDatabase(em, "N1.csv", "1314", 13);
		// populateDatabase(em, "N1.csv", "1415", 14);
		//
		// // BELGICA - JUPILER LEAGUE
		// populateDatabase(em, "B1.csv", "0001", 0);
		// populateDatabase(em, "B1.csv", "0102", 1);
		// populateDatabase(em, "B1.csv", "0203", 2);
		// populateDatabase(em, "B1.csv", "0304", 3);
		// populateDatabase(em, "B1.csv", "0405", 4);
		// populateDatabase(em, "B1.csv", "0506", 5);
		// populateDatabase(em, "B1.csv", "0607", 6);
		// populateDatabase(em, "B1.csv", "0708", 7);
		// populateDatabase(em, "B1.csv", "0809", 8);
		// populateDatabase(em, "B1.csv", "0910", 9);
		// populateDatabase(em, "B1.csv", "1011", 10);
		// populateDatabase(em, "B1.csv", "1112", 11);
		// populateDatabase(em, "B1.csv", "1213", 12);
		// populateDatabase(em, "B1.csv", "1314", 13);
		// populateDatabase(em, "B1.csv", "1415", 14);
		//
		// // PORTUGAL - PRIMEIRA LIGA
		// populateDatabase(em, "P1.csv", "0001", 0);
		// populateDatabase(em, "P1.csv", "0102", 1);
		// populateDatabase(em, "P1.csv", "0203", 2);
		// populateDatabase(em, "P1.csv", "0304", 3);
		// populateDatabase(em, "P1.csv", "0405", 4);
		// populateDatabase(em, "P1.csv", "0506", 5);
		// populateDatabase(em, "P1.csv", "0607", 6);
		// populateDatabase(em, "P1.csv", "0708", 7);
		// populateDatabase(em, "P1.csv", "0809", 8);
		// populateDatabase(em, "P1.csv", "0910", 9);
		// populateDatabase(em, "P1.csv", "1011", 10);
		// populateDatabase(em, "P1.csv", "1112", 11);
		// populateDatabase(em, "P1.csv", "1213", 12);
		// populateDatabase(em, "P1.csv", "1314", 13);
		// populateDatabase(em, "P1.csv", "1415", 14);
		//
		// // TURQUIA - FUTBOL LIGI 1
		// populateDatabase(em, "T1.csv", "0001", 0);
		// populateDatabase(em, "T1.csv", "0102", 1);
		// populateDatabase(em, "T1.csv", "0203", 2);
		// populateDatabase(em, "T1.csv", "0304", 3);
		// populateDatabase(em, "T1.csv", "0405", 4);
		// populateDatabase(em, "T1.csv", "0506", 5);
		// populateDatabase(em, "T1.csv", "0607", 6);
		// populateDatabase(em, "T1.csv", "0708", 7);
		// populateDatabase(em, "T1.csv", "0809", 8);
		// populateDatabase(em, "T1.csv", "0910", 9);
		// populateDatabase(em, "T1.csv", "1011", 10);
		// populateDatabase(em, "T1.csv", "1112", 11);
		// populateDatabase(em, "T1.csv", "1213", 12);
		// populateDatabase(em, "T1.csv", "1314", 13);
		// populateDatabase(em, "T1.csv", "1415", 14);
		//
		// // GRECIA - ETHNIKI KATIGORIA
		// populateDatabase(em, "G1.csv", "0001", 0);
		// populateDatabase(em, "G1.csv", "0102", 1);
		// populateDatabase(em, "G1.csv", "0203", 2);
		// populateDatabase(em, "G1.csv", "0304", 3);
		// populateDatabase(em, "G1.csv", "0405", 4);
		// populateDatabase(em, "G1.csv", "0506", 5);
		// populateDatabase(em, "G1.csv", "0607", 6);
		// populateDatabase(em, "G1.csv", "0708", 7);
		// populateDatabase(em, "G1.csv", "0809", 8);
		// populateDatabase(em, "G1.csv", "0910", 9);
		// populateDatabase(em, "G1.csv", "1011", 10);
		// populateDatabase(em, "G1.csv", "1112", 11);
		// populateDatabase(em, "G1.csv", "1213", 12);
		// populateDatabase(em, "G1.csv", "1314", 13);
		// populateDatabase(em, "G1.csv", "1415", 14);

	}

	private static void createExcels_A_B(EntityManager em) {

		SeasonController sc = new SeasonController(em);

		String dir = "/Users/Ricardo/Desktop/";

		// // INGLATERRA - PREMIER LEAGUE
		// Season e14 = sc.createSeason("E0", 14);
		// generateExcelFiles_PlanA(e14, em, dir, true, true);
		// generateExcelFiles_PlanB(e14, em, dir);
		//
		// Season e13 = sc.createSeason("E0", 13);
		// generateExcelFiles_PlanA(e13, em, dir, true, true);
		// generateExcelFiles_PlanB(e13, em, dir);
		//
		// Season e12 = sc.createSeason("E0", 12);
		// generateExcelFiles_PlanA(e12, em, dir, true, true);
		// generateExcelFiles_PlanB(e12, em, dir);
		//
		// Season e11 = sc.createSeason("E0", 11);
		// generateExcelFiles_PlanA(e11, em, dir, true, true);
		// generateExcelFiles_PlanB(e11, em, dir);
		//
		// Season e10 = sc.createSeason("E0", 10);
		// generateExcelFiles_PlanA(e10, em, dir, true, true);
		// generateExcelFiles_PlanB(e10, em, dir);
		//
		// // INGLATERRA - PREMIERSHIP
		// Season ee14 = sc.createSeason("E1", 14);
		// generateExcelFiles_PlanA(ee14, em, dir, true, true);
		// generateExcelFiles_PlanB(ee14, em, dir);
		//
		// Season ee13 = sc.createSeason("E1", 13);
		// generateExcelFiles_PlanA(ee13, em, dir, true, true);
		// generateExcelFiles_PlanB(ee13, em, dir);
		//
		// Season ee12 = sc.createSeason("E1", 12);
		// generateExcelFiles_PlanA(ee12, em, dir, true, true);
		// generateExcelFiles_PlanB(ee12, em, dir);
		//
		// Season ee11 = sc.createSeason("E1", 11);
		// generateExcelFiles_PlanA(ee11, em, dir, true, true);
		// generateExcelFiles_PlanB(ee11, em, dir);
		//
		// Season ee10 = sc.createSeason("E1", 10);
		// generateExcelFiles_PlanA(ee10, em, dir, true, true);
		// generateExcelFiles_PlanB(ee10, em, dir);
		//
		// // INGLATERRA - LEAGUE 1
		// Season eee14 = sc.createSeason("E2", 14);
		// generateExcelFiles_PlanA(eee14, em, dir, true, true);
		// generateExcelFiles_PlanB(eee14, em, dir);
		//
		// Season eee13 = sc.createSeason("E2", 13);
		// generateExcelFiles_PlanA(eee13, em, dir, true, true);
		// generateExcelFiles_PlanB(eee13, em, dir);
		//
		// Season eee12 = sc.createSeason("E2", 12);
		// generateExcelFiles_PlanA(eee12, em, dir, true, true);
		// generateExcelFiles_PlanB(eee12, em, dir);
		//
		// Season eee11 = sc.createSeason("E2", 11);
		// generateExcelFiles_PlanA(eee11, em, dir, true, true);
		// generateExcelFiles_PlanB(eee11, em, dir);
		//
		// Season eee10 = sc.createSeason("E2", 10);
		// generateExcelFiles_PlanA(eee10, em, dir, true, true);
		// generateExcelFiles_PlanB(eee10, em, dir);
		//
		// // ESCOCIA - PREMIER LEAGUE
		// Season sc14 = sc.createSeason("SC0", 14);
		// generateExcelFiles_PlanA(sc14, em, dir, true, true);
		// generateExcelFiles_PlanB(sc14, em, dir);
		//
		// Season sc13 = sc.createSeason("SC0", 13);
		// generateExcelFiles_PlanA(sc13, em, dir, true, true);
		// generateExcelFiles_PlanB(sc13, em, dir);
		//
		// Season sc12 = sc.createSeason("SC0", 12);
		// generateExcelFiles_PlanA(sc12, em, dir, true, true);
		// generateExcelFiles_PlanB(sc12, em, dir);
		//
		// Season sc11 = sc.createSeason("SC0", 11);
		// generateExcelFiles_PlanA(sc11, em, dir, true, true);
		// generateExcelFiles_PlanB(sc11, em, dir);
		//
		// Season sc10 = sc.createSeason("SC0", 10);
		// generateExcelFiles_PlanA(sc10, em, dir, true, true);
		// generateExcelFiles_PlanB(sc10, em, dir);
		//
		// // ALEMANHA - BUNDESLIGA1
		// Season d14 = sc.createSeason("D1", 14);
		// generateExcelFiles_PlanA(d14, em, dir, true, true);
		// generateExcelFiles_PlanB(d14, em, dir);
		//
		// Season d13 = sc.createSeason("D1", 13);
		// generateExcelFiles_PlanA(d13, em, dir, true, true);
		// generateExcelFiles_PlanB(d13, em, dir);
		//
		// Season d12 = sc.createSeason("D1", 12);
		// generateExcelFiles_PlanA(d12, em, dir, true, true);
		// generateExcelFiles_PlanB(d12, em, dir);
		//
		// Season d11 = sc.createSeason("D1", 11);
		// generateExcelFiles_PlanA(d11, em, dir, true, true);
		// generateExcelFiles_PlanB(d11, em, dir);
		//
		// Season d10 = sc.createSeason("D1", 10);
		// generateExcelFiles_PlanA(d10, em, dir, true, true);
		// generateExcelFiles_PlanB(d10, em, dir);
		//
		// // ALEMANHA - BUNDESLIGA2
		// Season dd14 = sc.createSeason("D2", 14);
		// generateExcelFiles_PlanA(dd14, em, dir, true, true);
		// generateExcelFiles_PlanB(dd14, em, dir);
		//
		// Season dd13 = sc.createSeason("D2", 13);
		// generateExcelFiles_PlanA(dd13, em, dir, true, true);
		// generateExcelFiles_PlanB(dd13, em, dir);
		//
		// Season dd12 = sc.createSeason("D2", 12);
		// generateExcelFiles_PlanA(dd12, em, dir, true, true);
		// generateExcelFiles_PlanB(dd12, em, dir);
		//
		// Season dd11 = sc.createSeason("D2", 11);
		// generateExcelFiles_PlanA(dd11, em, dir, true, true);
		// generateExcelFiles_PlanB(dd11, em, dir);
		//
		// Season dd10 = sc.createSeason("D2", 10);
		// generateExcelFiles_PlanA(dd10, em, dir, true, true);
		// generateExcelFiles_PlanB(dd10, em, dir);
		//
		// // ITALIA - SERIE A
		Season i14 = sc.createSeason("I1", 14);
		generateExcelFiles_PlanA(i14, em, dir, true, true);
		// generateExcelFiles_PlanB(i14, em, dir);
		//
		Season i13 = sc.createSeason("I1", 13);
		generateExcelFiles_PlanA(i13, em, dir, true, true);
		// generateExcelFiles_PlanB(i13, em, dir);
		//
		Season i12 = sc.createSeason("I1", 12);
		generateExcelFiles_PlanA(i12, em, dir, true, true);
		// generateExcelFiles_PlanB(i12, em, dir);
		//
		Season i11 = sc.createSeason("I1", 11);
		generateExcelFiles_PlanA(i11, em, dir, true, true);
		// generateExcelFiles_PlanB(i11, em, dir);
		//
		Season i10 = sc.createSeason("I1", 10);
		generateExcelFiles_PlanA(i10, em, dir, true, true);
		// generateExcelFiles_PlanB(i10, em, dir);
		//
		Season i9 = sc.createSeason("I1", 9);
		generateExcelFiles_PlanA(i9, em, dir, true, true);
		// generateExcelFiles_PlanB(i9, em, dir);
		//
		Season i8 = sc.createSeason("I1", 8);
		generateExcelFiles_PlanA(i8, em, dir, true, true);
		// generateExcelFiles_PlanB(i8, em, dir);
		//
		Season i7 = sc.createSeason("I1", 7);
		generateExcelFiles_PlanA(i7, em, dir, true, true);
		// generateExcelFiles_PlanB(i7, em, dir);
		//
		Season i6 = sc.createSeason("I1", 6);
		generateExcelFiles_PlanA(i6, em, dir, true, true);
		// generateExcelFiles_PlanB(i6, em, dir);
		//
		Season i5 = sc.createSeason("I1", 5);
		generateExcelFiles_PlanA(i5, em, dir, true, true);
		// generateExcelFiles_PlanB(i5, em, dir);
		//
		// // ITALIA - SERIE B
		// Season ii14 = sc.createSeason("I2", 14);
		// generateExcelFiles_PlanA(ii14, em, dir, true, true);
		// generateExcelFiles_PlanB(ii14, em, dir);
		//
		// Season ii13 = sc.createSeason("I2", 13);
		// generateExcelFiles_PlanA(ii13, em, dir, true, true);
		// generateExcelFiles_PlanB(ii13, em, dir);
		//
		// Season ii12 = sc.createSeason("I2", 12);
		// generateExcelFiles_PlanA(ii12, em, dir, true, true);
		// generateExcelFiles_PlanB(ii12, em, dir);
		//
		// Season ii11 = sc.createSeason("I2", 11);
		// generateExcelFiles_PlanA(ii11, em, dir, true, true);
		// generateExcelFiles_PlanB(ii11, em, dir);
		//
		// Season ii10 = sc.createSeason("I2", 10);
		// generateExcelFiles_PlanA(ii10, em, dir, true, true);
		// generateExcelFiles_PlanB(ii10, em, dir);
		//
		// // ESPANHA - LA LIGA PRIMERA DIVISION
		// Season sp14 = sc.createSeason("SP1", 14);
		// generateExcelFiles_PlanA(sp14, em, dir, true, true);
		// generateExcelFiles_PlanB(sp14, em, dir);
		//
		// Season sp13 = sc.createSeason("SP1", 13);
		// generateExcelFiles_PlanA(sp13, em, dir, true, true);
		// generateExcelFiles_PlanB(sp13, em, dir);
		//
		// Season sp12 = sc.createSeason("SP1", 12);
		// generateExcelFiles_PlanA(sp12, em, dir, true, true);
		// generateExcelFiles_PlanB(sp12, em, dir);
		//
		// Season sp11 = sc.createSeason("SP1", 11);
		// generateExcelFiles_PlanA(sp11, em, dir, true, true);
		// generateExcelFiles_PlanB(sp11, em, dir);
		//
		// Season sp10 = sc.createSeason("SP1", 10);
		// generateExcelFiles_PlanA(sp10, em, dir, true, true);
		// generateExcelFiles_PlanB(sp10, em, dir);
		//
		// // ESPANHA - LA LIGA SEGUNDA DIVISION
		// Season spsp14 = sc.createSeason("SP2", 14);
		// generateExcelFiles_PlanA(spsp14, em, dir, true, true);
		// generateExcelFiles_PlanB(spsp14, em, dir);
		//
		// Season spsp13 = sc.createSeason("SP2", 13);
		// generateExcelFiles_PlanA(spsp13, em, dir, true, true);
		// generateExcelFiles_PlanB(spsp13, em, dir);
		//
		// Season spsp12 = sc.createSeason("SP2", 12);
		// generateExcelFiles_PlanA(spsp12, em, dir, true, true);
		// generateExcelFiles_PlanB(spsp12, em, dir);
		//
		// Season spsp11 = sc.createSeason("SP2", 11);
		// generateExcelFiles_PlanA(spsp11, em, dir, true, true);
		// generateExcelFiles_PlanB(spsp11, em, dir);
		//
		// Season spsp10 = sc.createSeason("SP2", 10);
		// generateExcelFiles_PlanA(spsp10, em, dir, true, true);
		// generateExcelFiles_PlanB(spsp10, em, dir);
		//
		// // FRANÇA - CHAMPIONNAT
		// Season f14 = sc.createSeason("F1", 14);
		// generateExcelFiles_PlanA(f14, em, dir, true, true);
		// generateExcelFiles_PlanB(f14, em, dir);
		//
		// Season f13 = sc.createSeason("F1", 13);
		// generateExcelFiles_PlanA(f13, em, dir, true, true);
		// generateExcelFiles_PlanB(f13, em, dir);
		//
		// Season f12 = sc.createSeason("F1", 12);
		// generateExcelFiles_PlanA(f12, em, dir, true, true);
		// generateExcelFiles_PlanB(f12, em, dir);
		//
		// Season f11 = sc.createSeason("F1", 11);
		// generateExcelFiles_PlanA(f11, em, dir, true, true);
		// generateExcelFiles_PlanB(f11, em, dir);
		//
		// Season f10 = sc.createSeason("F1", 10);
		// generateExcelFiles_PlanA(f10, em, dir, true, true);
		// generateExcelFiles_PlanB(f10, em, dir);
		//
		// // FRANÇA - DIVISION2
		// Season ff14 = sc.createSeason("F2", 14);
		// generateExcelFiles_PlanA(ff14, em, dir, true, true);
		// generateExcelFiles_PlanB(ff14, em, dir);
		//
		// Season ff13 = sc.createSeason("F2", 13);
		// generateExcelFiles_PlanA(ff13, em, dir, true, true);
		// generateExcelFiles_PlanB(ff13, em, dir);
		//
		// Season ff12 = sc.createSeason("F2", 12);
		// generateExcelFiles_PlanA(ff12, em, dir, true, true);
		// generateExcelFiles_PlanB(ff12, em, dir);
		//
		// Season ff11 = sc.createSeason("F2", 11);
		// generateExcelFiles_PlanA(ff11, em, dir, true, true);
		// generateExcelFiles_PlanB(ff11, em, dir);
		//
		// Season ff10 = sc.createSeason("F2", 10);
		// generateExcelFiles_PlanA(ff10, em, dir, true, true);
		// generateExcelFiles_PlanB(ff10, em, dir);
		//
		// // HOLANDA - EREDIVISE
		// Season n14 = sc.createSeason("N1", 14);
		// generateExcelFiles_PlanA(n14, em, dir, true, true);
		// generateExcelFiles_PlanB(n14, em, dir);
		//
		// Season n13 = sc.createSeason("N1", 13);
		// generateExcelFiles_PlanA(n13, em, dir, true, true);
		// generateExcelFiles_PlanB(n13, em, dir);
		//
		// Season n12 = sc.createSeason("N1", 12);
		// generateExcelFiles_PlanA(n12, em, dir, true, true);
		// generateExcelFiles_PlanB(n12, em, dir);
		//
		// Season n11 = sc.createSeason("N1", 11);
		// generateExcelFiles_PlanA(n11, em, dir, true, true);
		// generateExcelFiles_PlanB(n11, em, dir);
		//
		// Season n10 = sc.createSeason("N1", 10);
		// generateExcelFiles_PlanA(n10, em, dir, true, true);
		// generateExcelFiles_PlanB(n10, em, dir);
		//
		// // BÉLGICA - JUPILER LEAGUE
		// Season b14 = sc.createSeason("B1", 14);
		// generateExcelFiles_PlanA(b14, em, dir, true, true);
		// generateExcelFiles_PlanB(b14, em, dir);
		//
		// Season b13 = sc.createSeason("B1", 13);
		// generateExcelFiles_PlanA(b13, em, dir, true, true);
		// generateExcelFiles_PlanB(b13, em, dir);
		//
		// Season b12 = sc.createSeason("B1", 12);
		// generateExcelFiles_PlanA(b12, em, dir, true, true);
		// generateExcelFiles_PlanB(b12, em, dir);
		//
		// Season b11 = sc.createSeason("B1", 11);
		// generateExcelFiles_PlanA(b11, em, dir, true, true);
		// generateExcelFiles_PlanB(b11, em, dir);
		//
		// Season b10 = sc.createSeason("B1", 10);
		// generateExcelFiles_PlanA(b10, em, dir, true, true);
		// generateExcelFiles_PlanB(b10, em, dir);
		//
		// // PORTUGAL - PRIMEIRA LIGA
		// Season p14 = sc.createSeason("P1", 14);
		// generateExcelFiles_PlanA(p14, em, dir, true, true);
		// generateExcelFiles_PlanB(p14, em, dir);
		//
		// Season p13 = sc.createSeason("P1", 13);
		// generateExcelFiles_PlanA(p13, em, dir, true, true);
		// generateExcelFiles_PlanB(p13, em, dir);
		//
		// Season p12 = sc.createSeason("P1", 12);
		// generateExcelFiles_PlanA(p12, em, dir, true, true);
		// generateExcelFiles_PlanB(p12, em, dir);
		//
		// Season p11 = sc.createSeason("P1", 11);
		// generateExcelFiles_PlanA(p11, em, dir, true, true);
		// generateExcelFiles_PlanB(p11, em, dir);
		//
		// Season p10 = sc.createSeason("P1", 10);
		// generateExcelFiles_PlanA(p10, em, dir, true, true);
		// generateExcelFiles_PlanB(p10, em, dir);
		//
		// // TURQUIA - FUTBOL LIGI 1
		// Season t14 = sc.createSeason("T1", 14);
		// generateExcelFiles_PlanA(t14, em, dir, true, true);
		// generateExcelFiles_PlanB(t14, em, dir);
		//
		// Season t13 = sc.createSeason("T1", 13);
		// generateExcelFiles_PlanA(t13, em, dir, true, true);
		// generateExcelFiles_PlanB(t13, em, dir);
		//
		// Season t12 = sc.createSeason("T1", 12);
		// generateExcelFiles_PlanA(t12, em, dir, true, true);
		// generateExcelFiles_PlanB(t12, em, dir);
		//
		// Season t11 = sc.createSeason("T1", 11);
		// generateExcelFiles_PlanA(t11, em, dir, true, true);
		// generateExcelFiles_PlanB(t11, em, dir);
		//
		// Season t10 = sc.createSeason("T1", 10);
		// generateExcelFiles_PlanA(t10, em, dir, true, true);
		// generateExcelFiles_PlanB(t10, em, dir);
		//
		// // GRÉCIA - ETHNIKI KATIGORIA
		// Season g14 = sc.createSeason("G1", 14);
		// generateExcelFiles_PlanA(g14, em, dir, true, true);
		// generateExcelFiles_PlanB(g14, em, dir);
		//
		// Season g13 = sc.createSeason("G1", 13);
		// generateExcelFiles_PlanA(g13, em, dir, true, true);
		// generateExcelFiles_PlanB(g13, em, dir);
		//
		// Season g12 = sc.createSeason("G1", 12);
		// generateExcelFiles_PlanA(g12, em, dir, true, true);
		// generateExcelFiles_PlanB(g12, em, dir);
		//
		// Season g11 = sc.createSeason("G1", 11);
		// generateExcelFiles_PlanA(g11, em, dir, true, true);
		// generateExcelFiles_PlanB(g11, em, dir);
		//
		// Season g10 = sc.createSeason("G1", 10);
		// generateExcelFiles_PlanA(g10, em, dir, true, true);
		// generateExcelFiles_PlanB(g10, em, dir);

	}

	private static void generateExcelFiles_PlanA(Season s, EntityManager em, String filePath, boolean generateProf,
			boolean generateOurs) {

		WriteToExcel writeToExcelObj = new WriteToExcel(filePath);

		List<ExcelRow> OurWinDataList = new ArrayList<ExcelRow>();
		List<ExcelRow> OurDrawDataList = new ArrayList<ExcelRow>();
		List<ExcelRow> OurLoseDataList = new ArrayList<ExcelRow>();

		List<String> historicos = new ArrayList<String>();

		// INGLATERRA
		historicos.add("Chelsea");
		historicos.add("Arsenal");
		historicos.add("Liverpool");
		historicos.add("Man City");
		historicos.add("Man United");

		// ESCOCIA
		historicos.add("Rangers");
		historicos.add("Celtic");

		// ALEMANHA
		historicos.add("Bayern Munich");
		historicos.add("Dortmund");
		historicos.add("Schalke 04");
		// historicos.add("Leverkusen");

		// ITALIA
		historicos.add("Juventus");
		historicos.add("Roma");
		historicos.add("Milan");
		historicos.add("Inter");
		historicos.add("Napoli");

		// ESPANHA
		historicos.add("Ath Madrid");
		historicos.add("Barcelona");
		historicos.add("Real Madrid");
		historicos.add("Valencia");

		// FRANÇA
		historicos.add("Paris SG");
		historicos.add("Marseille");
		historicos.add("Lyon");

		// HOLANDA
		historicos.add("Ajax");
		historicos.add("PSV Eindhoven");

		// BELGICA
		historicos.add("Anderlecht");
		historicos.add("Standard");

		// PORTUGAL
		historicos.add("Benfica");
		historicos.add("Porto");
		historicos.add("Sp Lisbon");

		// TURQUIA
		historicos.add("Fenerbahce");
		historicos.add("Besiktas");
		historicos.add("Galatasaray");

		// GRECIA
		historicos.add("Olympiakos");
		historicos.add("Panathinaikos");
		// historicos.add("PAOK");
		// historicos.add("AEK");

		List<Double> ratings = new ArrayList<Double>();
		ratings.add(0.3);
		ratings.add(0.25);
		ratings.add(0.2);
		ratings.add(0.15);
		ratings.add(0.1);

		List<Double> ratingsH2H = new ArrayList<Double>();
		ratingsH2H.add(0.2);
		ratingsH2H.add(0.19);
		ratingsH2H.add(0.17);
		ratingsH2H.add(0.14);
		ratingsH2H.add(0.1);
		ratingsH2H.add(0.06);
		ratingsH2H.add(0.05);
		ratingsH2H.add(0.04);
		ratingsH2H.add(0.03);
		ratingsH2H.add(0.02);
		ratingsH2H.add(0.01);

		double interval = 0.15;

		int numberOfFixtures = 5;

		for (Fixture f : s.getFixtures()) {

			logger.debug(f.toString());

			if (f.getDate().before(formatter.parseDateTime("23/11/" + (s.getYear() + 2000)).toDate()))
				continue;

			List<ExcelRow> rowList = new ArrayList<ExcelRow>();

			ExcelRow winRow = new ExcelRow();
			ExcelRow drawRow = new ExcelRow();
			ExcelRow loseRow = new ExcelRow();

			ExcelColumnsCalculation homeTeam = new ExcelColumnsCalculation(f.getHomeTeam(), em);
			ExcelColumnsCalculation awayTeam = new ExcelColumnsCalculation(f.getAwayTeam(), em);

			rowList = calculateGeneralVariables(f, s, homeTeam, awayTeam, winRow, drawRow, loseRow);
			winRow = rowList.get(0);
			drawRow = rowList.get(1);
			loseRow = rowList.get(2);
			rowList = calculateCycleVariables(f, homeTeam, awayTeam, historicos, ratings, winRow, drawRow, loseRow);
			winRow = rowList.get(0);
			drawRow = rowList.get(1);
			loseRow = rowList.get(2);
			rowList = calculateFRVariables(f, homeTeam, awayTeam, ratings, historicos, winRow, drawRow, loseRow,
					numberOfFixtures, interval);
			winRow = rowList.get(0);
			drawRow = rowList.get(1);
			loseRow = rowList.get(2);
			rowList = calculateQLTVariables(f, homeTeam, awayTeam, interval, winRow, drawRow, loseRow);
			winRow = rowList.get(0);
			drawRow = rowList.get(1);
			loseRow = rowList.get(2);
			rowList = calculateH2HVariables(f, ratingsH2H, homeTeam, awayTeam, winRow, drawRow, loseRow);
			winRow = rowList.get(0);
			drawRow = rowList.get(1);
			loseRow = rowList.get(2);
			rowList = calculateGoalVariables(f, s, homeTeam, awayTeam, winRow, drawRow, loseRow);
			winRow = rowList.get(0);
			drawRow = rowList.get(1);
			loseRow = rowList.get(2);

			OurWinDataList.add(winRow);
			OurDrawDataList.add(drawRow);
			OurLoseDataList.add(loseRow);

		}

		Workbook NossoWorkbook = writeToExcelObj.newWorkbook();

		writeToExcelObj.writeOurDataExcelTable(OurWinDataList, NossoWorkbook, "Vitoria");
		writeToExcelObj.writeOurDataExcelTable(OurDrawDataList, NossoWorkbook, "Empate");
		writeToExcelObj.writeOurDataExcelTable(OurLoseDataList, NossoWorkbook, "Derrota");
		writeToExcelObj.writeWorkbookToExcelFile(s.getName() + "_A" + s.getYear(), NossoWorkbook);

	}

	private static List<ExcelRow> calculateGeneralVariables(Fixture f, Season s, ExcelColumnsCalculation homeTeam,
			ExcelColumnsCalculation awayTeam, ExcelRow winRow, ExcelRow drawRow, ExcelRow loseRow) {

		List<ExcelRow> excelRows = new ArrayList<ExcelRow>();

		winRow = generalVenue(f, s, homeTeam, awayTeam, winRow);
		drawRow = generalVenue(f, s, homeTeam, awayTeam, drawRow);
		loseRow = generalVenue(f, s, homeTeam, awayTeam, loseRow);
		winRow = generalSwitchedVenue(f, s, homeTeam, awayTeam, winRow);
		drawRow = generalSwitchedVenue(f, s, homeTeam, awayTeam, drawRow);
		loseRow = generalSwitchedVenue(f, s, homeTeam, awayTeam, loseRow);
		winRow = generalOverall(f, s, homeTeam, awayTeam, winRow, "1");
		drawRow = generalOverall(f, s, homeTeam, awayTeam, drawRow, "X");
		loseRow = generalOverall(f, s, homeTeam, awayTeam, loseRow, "2");

		excelRows.add(winRow);
		excelRows.add(drawRow);
		excelRows.add(loseRow);

		return excelRows;
	}

	private static List<ExcelRow> calculateCycleVariables(Fixture f, ExcelColumnsCalculation homeTeam,
			ExcelColumnsCalculation awayTeam, List<String> historicos, List<Double> ratings, ExcelRow winRow,
			ExcelRow drawRow, ExcelRow loseRow) {

		List<ExcelRow> excelRows = new ArrayList<ExcelRow>();

		winRow = cycleVenue(f, homeTeam, awayTeam, historicos, "1", winRow);
		drawRow = cycleVenue(f, homeTeam, awayTeam, historicos, "X", drawRow);
		loseRow = cycleVenue(f, homeTeam, awayTeam, historicos, "2", loseRow);
		winRow = cycleSwitchedVenue(f, homeTeam, awayTeam, historicos, "1", winRow);
		drawRow = cycleSwitchedVenue(f, homeTeam, awayTeam, historicos, "X", drawRow);
		loseRow = cycleSwitchedVenue(f, homeTeam, awayTeam, historicos, "2", loseRow);
		winRow = cycleOverall(f, homeTeam, awayTeam, historicos, ratings, "1", winRow);
		drawRow = cycleOverall(f, homeTeam, awayTeam, historicos, ratings, "X", drawRow);
		loseRow = cycleOverall(f, homeTeam, awayTeam, historicos, ratings, "2", loseRow);

		excelRows.add(winRow);
		excelRows.add(drawRow);
		excelRows.add(loseRow);

		return excelRows;
	}

	private static List<ExcelRow> calculateFRVariables(Fixture f, ExcelColumnsCalculation homeTeam,
			ExcelColumnsCalculation awayTeam, List<Double> ratings, List<String> historicos, ExcelRow winRow,
			ExcelRow drawRow, ExcelRow loseRow, Integer numberOfFixtures, Double interval) {

		List<ExcelRow> excelRows = new ArrayList<ExcelRow>();

		winRow = FRVenue(f, homeTeam, awayTeam, ratings, historicos, "1", winRow, numberOfFixtures, interval);
		drawRow = FRVenue(f, homeTeam, awayTeam, ratings, historicos, "X", drawRow, numberOfFixtures, interval);
		loseRow = FRVenue(f, homeTeam, awayTeam, ratings, historicos, "2", loseRow, numberOfFixtures, interval);
		winRow = FRSwitchedVenue(f, homeTeam, awayTeam, ratings, historicos, "1", winRow, numberOfFixtures, interval);
		drawRow = FRSwitchedVenue(f, homeTeam, awayTeam, ratings, historicos, "X", drawRow, numberOfFixtures, interval);
		loseRow = FRSwitchedVenue(f, homeTeam, awayTeam, ratings, historicos, "2", loseRow, numberOfFixtures, interval);
		winRow = FROverall(f, homeTeam, awayTeam, ratings, historicos, "1", winRow, numberOfFixtures, interval);
		drawRow = FROverall(f, homeTeam, awayTeam, ratings, historicos, "X", drawRow, numberOfFixtures, interval);
		loseRow = FROverall(f, homeTeam, awayTeam, ratings, historicos, "2", loseRow, numberOfFixtures, interval);

		excelRows.add(winRow);
		excelRows.add(drawRow);
		excelRows.add(loseRow);

		return excelRows;
	}

	private static List<ExcelRow> calculateQLTVariables(Fixture f, ExcelColumnsCalculation homeTeam,
			ExcelColumnsCalculation awayTeam, Double interval, ExcelRow winRow, ExcelRow drawRow, ExcelRow loseRow) {

		List<ExcelRow> excelRows = new ArrayList<ExcelRow>();

		winRow = QLTVenue(f, homeTeam, awayTeam, interval, "1", winRow);
		drawRow = QLTVenue(f, homeTeam, awayTeam, interval, "X", drawRow);
		loseRow = QLTVenue(f, homeTeam, awayTeam, interval, "2", loseRow);
		winRow = QLTSwitchedVenue(f, homeTeam, awayTeam, interval, "1", winRow);
		drawRow = QLTSwitchedVenue(f, homeTeam, awayTeam, interval, "X", drawRow);
		loseRow = QLTSwitchedVenue(f, homeTeam, awayTeam, interval, "2", loseRow);
		winRow = QLTOverall(f, homeTeam, awayTeam, interval, "1", winRow);
		drawRow = QLTOverall(f, homeTeam, awayTeam, interval, "X", drawRow);
		loseRow = QLTOverall(f, homeTeam, awayTeam, interval, "2", loseRow);

		excelRows.add(winRow);
		excelRows.add(drawRow);
		excelRows.add(loseRow);

		return excelRows;
	}

	private static List<ExcelRow> calculateH2HVariables(Fixture f, List<Double> ratingsH2H,
			ExcelColumnsCalculation homeTeam, ExcelColumnsCalculation awayTeam, ExcelRow winRow, ExcelRow drawRow,
			ExcelRow loseRow) {

		List<ExcelRow> excelRows = new ArrayList<ExcelRow>();

		winRow = H2HVenue(f, ratingsH2H, homeTeam, awayTeam, "1", winRow);
		drawRow = H2HVenue(f, ratingsH2H, homeTeam, awayTeam, "X", drawRow);
		loseRow = H2HVenue(f, ratingsH2H, homeTeam, awayTeam, "2", loseRow);
		winRow = H2HSwitchedVenue(f, ratingsH2H, homeTeam, awayTeam, "1", winRow);
		drawRow = H2HSwitchedVenue(f, ratingsH2H, homeTeam, awayTeam, "X", drawRow);
		loseRow = H2HSwitchedVenue(f, ratingsH2H, homeTeam, awayTeam, "2", loseRow);
		winRow = H2HOverall(f, ratingsH2H, homeTeam, awayTeam, "1", winRow);
		drawRow = H2HOverall(f, ratingsH2H, homeTeam, awayTeam, "X", drawRow);
		loseRow = H2HOverall(f, ratingsH2H, homeTeam, awayTeam, "2", loseRow);

		excelRows.add(winRow);
		excelRows.add(drawRow);
		excelRows.add(loseRow);

		return excelRows;
	}

	private static List<ExcelRow> calculateGoalVariables(Fixture f, Season s, ExcelColumnsCalculation homeTeam,
			ExcelColumnsCalculation awayTeam, ExcelRow winRow, ExcelRow drawRow, ExcelRow loseRow) {

		List<ExcelRow> excelRows = new ArrayList<ExcelRow>();

		winRow = goalVenue(f, s, homeTeam, awayTeam, winRow);
		drawRow = goalVenue(f, s, homeTeam, awayTeam, drawRow);
		loseRow = goalVenue(f, s, homeTeam, awayTeam, loseRow);
		winRow = goalSwitchedVenue(f, s, homeTeam, awayTeam, winRow);
		drawRow = goalSwitchedVenue(f, s, homeTeam, awayTeam, drawRow);
		loseRow = goalSwitchedVenue(f, s, homeTeam, awayTeam, loseRow);
		winRow = goalOverall(f, s, homeTeam, awayTeam, winRow);
		drawRow = goalOverall(f, s, homeTeam, awayTeam, drawRow);
		loseRow = goalOverall(f, s, homeTeam, awayTeam, loseRow);

		excelRows.add(winRow);
		excelRows.add(drawRow);
		excelRows.add(loseRow);

		return excelRows;
	}

	private static ExcelRow generalVenue(Fixture f, Season s, ExcelColumnsCalculation homeTeam,
			ExcelColumnsCalculation awayTeam, ExcelRow row) {

		row.setQualidadeVisitadoCasa(homeTeam.getHomeTeamQuality(s, f.getDate()));
		row.setQualidadeVisitanteFora(awayTeam.getAwayTeamQuality(s, f.getDate()));

		return row;
	}

	private static ExcelRow generalSwitchedVenue(Fixture f, Season s, ExcelColumnsCalculation homeTeam,
			ExcelColumnsCalculation awayTeam, ExcelRow row) {

		row.setQualidadeVisitadoFora(awayTeam.getHomeTeamQuality(s, f.getDate()));
		row.setQualidadeVisitanteCasa(homeTeam.getAwayTeamQuality(s, f.getDate()));

		return row;
	}

	private static ExcelRow generalOverall(Fixture f, Season s, ExcelColumnsCalculation homeTeam,
			ExcelColumnsCalculation awayTeam, ExcelRow row, String resultado) {

		row.setData(f.getDate());
		row.setHomeTeamName(f.getHomeTeam().getName());
		row.setAwayTeamName(f.getAwayTeam().getName());
		row.setIdVisitado(f.getHomeTeam().getId());
		row.setIdVisitante(f.getAwayTeam().getId());
		row.setQualidadeVisitado(homeTeam.getTeamQuality(s, f.getDate()));
		row.setQualidadeVisitante(awayTeam.getTeamQuality(s, f.getDate()));

		ResultType result = homeTeam.getResultType(f);

		if (resultado.equals("1")) {
			row.setResult(result.equals(ResultType.WIN) ? 1 : 0);
		} else if (resultado.equals("X")) {
			row.setResult(result.equals(ResultType.DRAW) ? 1 : 0);
		} else if (resultado.equals("2")) {
			row.setResult(result.equals(ResultType.LOSE) ? 1 : 0);
		} else {
			// INVALIDO
			return null;
		}

		row.setClassificacaoVisitado(homeTeam.getClassification(s, f.getDate()));
		row.setClassificacaoVisitante(awayTeam.getClassification(s, f.getDate()));

		return row;
	}

	private static ExcelRow cycleVenue(Fixture f, ExcelColumnsCalculation homeTeam, ExcelColumnsCalculation awayTeam,
			List<String> historicos, String result, ExcelRow row) {

		ResultType homeResult;
		ResultType awayResult;

		if (result.equals("1")) {
			homeResult = ResultType.WIN;
			awayResult = ResultType.LOSE;
		} else if (result.equals("X")) {
			homeResult = ResultType.DRAW;
			awayResult = ResultType.DRAW;
		} else if (result.equals("2")) {
			homeResult = ResultType.LOSE;
			awayResult = ResultType.WIN;
		} else {
			// INVALID RESULT
			return null;
		}

		ResultCycle homeTeamCycle = homeTeam.getTeamCycle(f, Venue.HOME, homeResult);
		ResultCycle awayTeamCycle = awayTeam.getTeamCycle(f, Venue.AWAY, awayResult);
		ResultCycle homeWinCyclePerna = homeTeam.getTeamCyclePerna(f, Venue.HOME, homeResult);
		ResultCycle awayLoseCyclePerna = awayTeam.getTeamCyclePerna(f, Venue.AWAY, awayResult);

		row.setCiclo_numeroJogosVisitado(homeTeamCycle.getCycle());
		row.setCiclo_numeroJogosVisitante(awayTeamCycle.getCycle());
		row.setCiclo_dificuldadeVisitado(homeTeam.getCycleOpponentAverageQuality(homeTeamCycle));
		row.setCiclo_dificuldadeVisitante(awayTeam.getCycleOpponentAverageQuality(awayTeamCycle));
		row.setCiclo_HistoricosVisitado(homeTeam.getCycleHardGamesNumber(homeTeamCycle, historicos));
		row.setCiclo_HistoricosVisitante(awayTeam.getCycleHardGamesNumber(awayTeamCycle, historicos));

		row.setCicloPerna_numeroJogosVisitado(homeWinCyclePerna.getCycle());
		row.setCicloPerna_numeroJogosVisitante(awayLoseCyclePerna.getCycle());
		row.setCicloPerna_dificuldadeVisitado(homeTeam.getCycleOpponentAverageQuality(homeWinCyclePerna));
		row.setCicloPerna_dificuldadeVisitante(awayTeam.getCycleOpponentAverageQuality(awayLoseCyclePerna));
		row.setCicloPerna_HistoricosVisitado(homeTeam.getCycleHardGamesNumber(homeWinCyclePerna, historicos));
		row.setCicloPerna_HistoricosVisitante(awayTeam.getCycleHardGamesNumber(awayLoseCyclePerna, historicos));

		return row;
	}

	private static ExcelRow cycleSwitchedVenue(Fixture f, ExcelColumnsCalculation homeTeam,
			ExcelColumnsCalculation awayTeam, List<String> historicos, String result, ExcelRow row) {

		return row;
	}

	private static ExcelRow cycleOverall(Fixture f, ExcelColumnsCalculation homeTeam, ExcelColumnsCalculation awayTeam,
			List<String> historicos, List<Double> ratings, String result, ExcelRow row) {

		ResultType homeResult;
		ResultType awayResult;

		if (result.equals("1")) {
			homeResult = ResultType.WIN;
			awayResult = ResultType.LOSE;
		} else if (result.equals("X")) {
			homeResult = ResultType.DRAW;
			awayResult = ResultType.DRAW;
		} else if (result.equals("2")) {
			homeResult = ResultType.LOSE;
			awayResult = ResultType.WIN;
		} else {
			// INVALID RESULT
			return null;
		}

		ResultCycle homeCycleTotal = homeTeam.getTeamCycle(f, null, homeResult);
		ResultCycle awayCycleTotal = awayTeam.getTeamCycle(f, null, awayResult);
		ResultCycle homeCyclePernaTotal = homeTeam.getTeamCyclePerna(f, null, homeResult);
		ResultCycle awayCyclePernaTotal = awayTeam.getTeamCyclePerna(f, null, awayResult);

		row.setCiclo_numeroJogosVisitadoTotal(homeCycleTotal.getCycle());
		row.setCiclo_numeroJogosVisitanteTotal(awayCycleTotal.getCycle());
		row.setCiclo_dificuldadeVisitadoTotal(homeTeam.getCycleOpponentAverageQuality(homeCycleTotal));
		row.setCiclo_dificuldadeVisitanteTotal(awayTeam.getCycleOpponentAverageQuality(awayCycleTotal));
		row.setCiclo_HistoricosVisitadoTotal(homeTeam.getCycleHardGamesNumber(homeCycleTotal, historicos));
		row.setCiclo_HistoricosVisitanteTotal(awayTeam.getCycleHardGamesNumber(awayCycleTotal, historicos));

		row.setCicloPerna_numeroJogosVisitadoTotal(homeCyclePernaTotal.getCycle());
		row.setCicloPerna_numeroJogosVisitanteTotal(awayCyclePernaTotal.getCycle());
		row.setCicloPerna_dificuldadeVisitadoTotal(homeTeam.getCycleOpponentAverageQuality(homeCyclePernaTotal));
		row.setCicloPerna_dificuldadeVisitanteTotal(awayTeam.getCycleOpponentAverageQuality(awayCyclePernaTotal));
		row.setCicloPerna_HistoricosVisitadoTotal(homeTeam.getCycleHardGamesNumber(homeCyclePernaTotal, historicos));
		row.setCicloPerna_HistoricosVisitanteTotal(awayTeam.getCycleHardGamesNumber(awayCyclePernaTotal, historicos));

		return row;
	}

	private static ExcelRow FRVenue(Fixture f, ExcelColumnsCalculation homeTeam, ExcelColumnsCalculation awayTeam,
			List<Double> ratings, List<String> historicos, String result, ExcelRow row, Integer numberOfFixtures,
			Double interval) {

		ResultType homeResult;
		ResultType awayResult;

		if (result.equals("1")) {
			homeResult = ResultType.WIN;
			awayResult = ResultType.LOSE;
		} else if (result.equals("X")) {
			homeResult = ResultType.DRAW;
			awayResult = ResultType.DRAW;
		} else if (result.equals("2")) {
			homeResult = ResultType.LOSE;
			awayResult = ResultType.WIN;
		} else {
			// INVALID RESULT
			return null;
		}

		Double[] percFRResultadoVisitado_5jogos = homeTeam.getRecentFormResultPercentage(f, null, numberOfFixtures,
				homeResult);
		Double[] percFRResultadoVisitante_5jogos = awayTeam.getRecentFormResultPercentage(f, null, numberOfFixtures,
				awayResult);

		TeamRating homeTR_5jogos = homeTeam.getResultPercentage(f, Venue.HOME, homeResult, interval, numberOfFixtures);
		TeamRating awayTR_5jogos = awayTeam.getResultPercentage(f, Venue.AWAY, awayResult, interval, numberOfFixtures);

		row.setFR_dificuldadeVisitadoHome(
				homeTeam.getLastFixturesOpponentAverageQuality(f, Venue.HOME, numberOfFixtures));
		row.setFR_dificuldadeVisitanteAway(
				awayTeam.getLastFixturesOpponentAverageQuality(f, Venue.AWAY, numberOfFixtures));
		row.setFR_HistoricosVisitadoHome(
				homeTeam.getLastHardGamesFixturesNumber(f, Venue.HOME, numberOfFixtures, historicos));
		row.setFR_HistoricosVisitanteAway(
				awayTeam.getLastHardGamesFixturesNumber(f, Venue.AWAY, numberOfFixtures, historicos));
		row.setFR_ratingResultadoVisitadoHome(
				homeTeam.getLastFixturesRating(f, Venue.HOME, numberOfFixtures, ratings, homeResult));
		row.setFR_ratingResultadoVisitanteAway(
				awayTeam.getLastFixturesRating(f, Venue.AWAY, numberOfFixtures, ratings, awayResult));
		row.setFR_ResultadoVisitadoHome(
				homeTeam.getLastFixturesResults(f, Venue.HOME, numberOfFixtures, ratings, homeResult));
		row.setFR_ResultadoVisitanteAway(
				awayTeam.getLastFixturesResults(f, Venue.AWAY, numberOfFixtures, ratings, awayResult));

		row.setPercFRResultadoVisitadoCasa(percFRResultadoVisitado_5jogos[0]);
		row.setPercFRResultadoVisitanteFora(percFRResultadoVisitante_5jogos[1]);

		row.setQLT_percentagemResultadoVisitadoVenue_5jogos(homeTR_5jogos.getResultPercentage());
		row.setQLT_percentagemResultadoVisitanteVenue_5jogos(awayTR_5jogos.getResultPercentage());
		row.setQLT_dificuldadeResultadoVisitadoVenue_5jogos(homeTR_5jogos.getQualityAverage());
		row.setQLT_dificuldadeResultadoVisitanteVenue_5jogos(awayTR_5jogos.getQualityAverage());
		row.setQLT_percentagemResultadoVisitadoNoIntervaloVenue_5jogos(homeTR_5jogos.getResultIntervalPercentage());
		row.setQLT_percentagemResultadoVisitanteNoIntervaloVenue_5jogos(awayTR_5jogos.getResultIntervalPercentage());
		row.setQLT_numeroJogosVisitadoVenue_5jogos(homeTR_5jogos.getResultIntervalGames());
		row.setQLT_numeroJogosVisitanteVenue_5jogos(awayTR_5jogos.getResultIntervalGames());

		return row;
	}

	private static ExcelRow FRSwitchedVenue(Fixture f, ExcelColumnsCalculation homeTeam,
			ExcelColumnsCalculation awayTeam, List<Double> ratings, List<String> historicos, String result,
			ExcelRow row, Integer numberOfFixtures, Double interval) {

		ResultType homeResult;
		ResultType awayResult;

		if (result.equals("1")) {
			homeResult = ResultType.WIN;
			awayResult = ResultType.LOSE;
		} else if (result.equals("X")) {
			homeResult = ResultType.DRAW;
			awayResult = ResultType.DRAW;
		} else if (result.equals("2")) {
			homeResult = ResultType.LOSE;
			awayResult = ResultType.WIN;
		} else {
			// INVALID RESULT
			return null;
		}

		TeamRating homeTR_5jogos = homeTeam.getResultPercentage(f, Venue.AWAY, homeResult, interval, numberOfFixtures);
		TeamRating awayTR_5jogos = awayTeam.getResultPercentage(f, Venue.HOME, awayResult, interval, numberOfFixtures);

		Double[] percFRResultadoVisitado_5jogos = homeTeam.getRecentFormResultPercentage(f, null, numberOfFixtures,
				homeResult);
		Double[] percFRResultadoVisitante_5jogos = awayTeam.getRecentFormResultPercentage(f, null, numberOfFixtures,
				awayResult);

		row.setQLT_homeTeamNumeroJogosTrocado(homeTeam.getNumberOfFixtures(f, Venue.AWAY));
		row.setQLT_awayTeamNumeroJogosTrocado(awayTeam.getNumberOfFixtures(f, Venue.HOME));

		row.setQLT_percentagemResultadoVisitadoTrocado_5jogos(homeTR_5jogos.getResultPercentage());
		row.setQLT_percentagemResultadoVisitanteTrocado_5jogos(awayTR_5jogos.getResultPercentage());
		row.setQLT_dificuldadeResultadoVisitadoTrocado_5jogos(homeTR_5jogos.getQualityAverage());
		row.setQLT_dificuldadeResultadoVisitanteTrocado_5jogos(awayTR_5jogos.getQualityAverage());
		row.setQLT_percentagemResultadoVisitadoNoIntervaloTrocado_5jogos(homeTR_5jogos.getResultIntervalPercentage());
		row.setQLT_percentagemResultadoVisitanteNoIntervaloTrocado_5jogos(awayTR_5jogos.getResultIntervalPercentage());
		row.setQLT_numeroJogosVisitadoTrocado_5jogos(homeTR_5jogos.getResultIntervalGames());
		row.setQLT_numeroJogosVisitanteTrocado_5jogos(awayTR_5jogos.getResultIntervalGames());

		row.setPercFRResultadoVisitadoFora(percFRResultadoVisitado_5jogos[1]);
		row.setPercFRResultadoVisitanteCasa(percFRResultadoVisitante_5jogos[0]);

		return row;
	}

	private static ExcelRow FROverall(Fixture f, ExcelColumnsCalculation homeTeam, ExcelColumnsCalculation awayTeam,
			List<Double> ratings, List<String> historicos, String result, ExcelRow row, Integer numberOfFixtures,
			Double interval) {

		ResultType homeResult;
		ResultType awayResult;

		if (result.equals("1")) {
			homeResult = ResultType.WIN;
			awayResult = ResultType.LOSE;
		} else if (result.equals("X")) {
			homeResult = ResultType.DRAW;
			awayResult = ResultType.DRAW;
		} else if (result.equals("2")) {
			homeResult = ResultType.LOSE;
			awayResult = ResultType.WIN;
		} else {
			// INVALID RESULT
			return null;
		}

		row.setFR_diasDescansoVisitado(homeTeam.getRestingDays(f));
		row.setFR_diasDescansoVisitante(awayTeam.getRestingDays(f));
		row.setFR_ratingResultadoVisitado(
				homeTeam.getLastFixturesRating(f, null, numberOfFixtures, ratings, homeResult));
		row.setFR_ratingResultadoVisitante(
				awayTeam.getLastFixturesRating(f, null, numberOfFixtures, ratings, awayResult));
		row.setFR_ResultadosVisitado(homeTeam.getLastFixturesResults(f, null, numberOfFixtures, ratings, homeResult));
		row.setFR_ResultadosVisitante(awayTeam.getLastFixturesResults(f, null, numberOfFixtures, ratings, awayResult));
		row.setFR_dificuldadeVisitado(homeTeam.getLastFixturesOpponentAverageQuality(f, null, numberOfFixtures));
		row.setFR_dificuldadeVisitante(awayTeam.getLastFixturesOpponentAverageQuality(f, null, numberOfFixtures));
		row.setFR_HistoricosVisitado(homeTeam.getLastHardGamesFixturesNumber(f, null, numberOfFixtures, historicos));
		row.setFR_HistoricosVisitante(awayTeam.getLastHardGamesFixturesNumber(f, null, numberOfFixtures, historicos));
		row.setFR_ratingQualidadeResultadoVisitado(
				homeTeam.getLastFixturesRatingQuality(f, null, numberOfFixtures, ratings, homeResult));
		row.setFR_ratingQualidadeResultadoVisitante(
				awayTeam.getLastFixturesRatingQuality(f, null, numberOfFixtures, ratings, awayResult));

		return row;
	}

	private static ExcelRow QLTVenue(Fixture f, ExcelColumnsCalculation homeTeam, ExcelColumnsCalculation awayTeam,
			Double interval, String result, ExcelRow row) {

		ResultType homeResult;
		ResultType awayResult;

		if (result.equals("1")) {
			homeResult = ResultType.WIN;
			awayResult = ResultType.LOSE;
		} else if (result.equals("X")) {
			homeResult = ResultType.DRAW;
			awayResult = ResultType.DRAW;
		} else if (result.equals("2")) {
			homeResult = ResultType.LOSE;
			awayResult = ResultType.WIN;
		} else {
			// INVALID RESULT
			return null;
		}

		TeamRating homeTR = homeTeam.getResultPercentage(f, Venue.HOME, homeResult, interval, null);
		TeamRating awayTR = awayTeam.getResultPercentage(f, Venue.AWAY, awayResult, interval, null);

		row.setQLT_numeroJogosVisitadoVenue(homeTeam.getNumberOfFixtures(f, Venue.HOME));
		row.setQLT_numeroJogosVisitanteVenue(awayTeam.getNumberOfFixtures(f, Venue.AWAY));
		row.setQLT_numeroJogosVisitadoDaPercentagem(homeTeam.getNumberOfFixtures(f, Venue.HOME)); // PARA
																									// CORRIGIR
		row.setQLT_numeroJogosVisitanteDaPercentagem(awayTeam.getNumberOfFixtures(f, Venue.AWAY)); // PARA
																									// CORRIGIR
		row.setQLT_percentagemResultadoVisitadoVenue(homeTR.getResultPercentage());
		row.setQLT_percentagemResultadoVisitanteVenue(awayTR.getResultPercentage());
		row.setQLT_dificuldadeResultadoVisitadoVenue(homeTR.getQualityAverage());
		row.setQLT_dificuldadeResultadoVisitanteVenue(awayTR.getQualityAverage());
		row.setQLT_percentagemResultadoVisitadoNoIntervaloVenue(homeTR.getResultIntervalPercentage());
		row.setQLT_percentagemResultadoVisitanteNoIntervaloVenue(awayTR.getResultIntervalPercentage());
		row.setQLT_numeroJogosVisitadoNoIntervaloVenue(homeTR.getResultIntervalGames());
		row.setQLT_numeroJogosVisitanteNoIntervaloVenue(awayTR.getResultIntervalGames());

		return row;
	}

	private static ExcelRow QLTSwitchedVenue(Fixture f, ExcelColumnsCalculation homeTeam,
			ExcelColumnsCalculation awayTeam, Double interval, String result, ExcelRow row) {

		ResultType homeResult;
		ResultType awayResult;

		if (result.equals("1")) {
			homeResult = ResultType.WIN;
			awayResult = ResultType.LOSE;
		} else if (result.equals("X")) {
			homeResult = ResultType.DRAW;
			awayResult = ResultType.DRAW;
		} else if (result.equals("2")) {
			homeResult = ResultType.LOSE;
			awayResult = ResultType.WIN;
		} else {
			// INVALID RESULT
			return null;
		}

		TeamRating homeTR = homeTeam.getResultPercentage(f, Venue.AWAY, homeResult, interval, null);
		TeamRating awayTR = awayTeam.getResultPercentage(f, Venue.HOME, awayResult, interval, null);

		row.setQLT_numeroJogosVisitadoTrocado(homeTeam.getNumberOfFixtures(f, Venue.AWAY));
		row.setQLT_numeroJogosVisitanteTrocado(awayTeam.getNumberOfFixtures(f, Venue.HOME));
		row.setQLT_percentagemResultadoVisitadoTrocado(homeTR.getResultPercentage());
		row.setQLT_percentagemResultadoVisitanteTrocado(awayTR.getResultPercentage());
		row.setQLT_dificuldadeResultadoVisitadoTrocado(homeTR.getQualityAverage());
		row.setQLT_dificuldadeResultadoVisitanteTrocado(awayTR.getQualityAverage());
		row.setQLT_percentagemResultadoVisitadoNoIntervaloTrocado(homeTR.getResultIntervalPercentage());
		row.setQLT_percentagemResultadoVisitanteNoIntervaloTrocado(awayTR.getResultIntervalPercentage());
		row.setQLT_numeroJogosVisitadoNoIntervaloTrocado(homeTR.getResultIntervalGames());
		row.setQLT_numeroJogosVisitanteNoIntervaloTrocado(awayTR.getResultIntervalGames());

		return row;
	}

	private static ExcelRow QLTOverall(Fixture f, ExcelColumnsCalculation homeTeam, ExcelColumnsCalculation awayTeam,
			Double interval, String result, ExcelRow row) {

		ResultType homeResult;
		ResultType awayResult;

		if (result.equals("1")) {
			homeResult = ResultType.WIN;
			awayResult = ResultType.LOSE;
		} else if (result.equals("X")) {
			homeResult = ResultType.DRAW;
			awayResult = ResultType.DRAW;
		} else if (result.equals("2")) {
			homeResult = ResultType.LOSE;
			awayResult = ResultType.WIN;
		} else {
			// INVALID RESULT
			return null;
		}

		TeamRating homeTR = homeTeam.getResultPercentage(f, null, homeResult, interval, null);
		TeamRating awayTR = awayTeam.getResultPercentage(f, null, awayResult, interval, null);

		row.setQLT_numeroJogosVisitado(homeTeam.getNumberOfFixtures(f, Venue.HOME));
		row.setQLT_numeroJogosVisitante(awayTeam.getNumberOfFixtures(f, Venue.AWAY));
		row.setQLT_percentagemResultadoVisitado(homeTR.getResultPercentage());
		row.setQLT_percentagemResultadoVisitante(awayTR.getResultPercentage());
		row.setQLT_dificuldadeResultadoVisitado(homeTR.getQualityAverage());
		row.setQLT_dificuldadeResultadoVisitante(awayTR.getQualityAverage());
		row.setQLT_percentagemResultadoVisitadoNoIntervalo(homeTR.getResultIntervalPercentage());
		row.setQLT_percentagemResultadoVisitanteNoIntervalo(awayTR.getResultIntervalPercentage());
		row.setQLT_numeroJogosVisitadoNoIntervalo(homeTR.getResultIntervalGames());
		row.setQLT_numeroJogosVisitanteNoIntervalo(awayTR.getResultIntervalGames());

		return row;

	}

	private static ExcelRow H2HVenue(Fixture f, List<Double> ratingsH2H, ExcelColumnsCalculation homeTeam,
			ExcelColumnsCalculation awayTeam, String result, ExcelRow row) {

		ResultType homeResult;
		ResultType awayResult;

		if (result.equals("1")) {
			homeResult = ResultType.WIN;
			awayResult = ResultType.LOSE;
		} else if (result.equals("X")) {
			homeResult = ResultType.DRAW;
			awayResult = ResultType.DRAW;
		} else if (result.equals("2")) {
			homeResult = ResultType.LOSE;
			awayResult = ResultType.WIN;
		} else {
			// INVALID RESULT
			return null;
		}

		H2H h2hRatings = homeTeam.getH2HRating(f, ratingsH2H, Venue.HOME, homeResult);

		row.setH2H_ratingResultado(h2hRatings.getRating());
		row.setH2H_numeroJogos(h2hRatings.getSize());
		row.setH2h_Results(h2hRatings.getNumberResults());

		return row;
	}

	private static ExcelRow H2HSwitchedVenue(Fixture f, List<Double> ratingsH2H, ExcelColumnsCalculation homeTeam,
			ExcelColumnsCalculation awayTeam, String result, ExcelRow row) {

		return row;

	}

	private static ExcelRow H2HOverall(Fixture f, List<Double> ratingsH2H, ExcelColumnsCalculation homeTeam,
			ExcelColumnsCalculation awayTeam, String result, ExcelRow row) {

		return row;
	}

	private static ExcelRow goalVenue(Fixture f, Season s, ExcelColumnsCalculation homeTeam,
			ExcelColumnsCalculation awayTeam, ExcelRow row) {

		Double[] percentagensGolosFRVisitado_5jogos = homeTeam.averageGoalsLastGames(f, null, 5);
		Double[] percentagensGolosFRVisitante_5jogos = awayTeam.averageGoalsLastGames(f, null, 5);

		row.setGolosVisitadoCasa(homeTeam.getHomeGoals(s, f.getDate()));
		row.setGolosSofridosVisitadoCasa(homeTeam.getHomeGoalsAgainst(s, f.getDate()));
		row.setDiferencaGolosVisitadoCasa(
				homeTeam.getHomeGoals(s, f.getDate()) - homeTeam.getHomeGoalsAgainst(s, f.getDate()));
		row.setGolosVisitanteFora(awayTeam.getAwayGoals(s, f.getDate()));
		row.setGolosSofridosVisitanteFora(awayTeam.getAwayGoalsAgainst(s, f.getDate()));
		row.setDiferencaGolosVisitanteFora(
				awayTeam.getAwayGoals(s, f.getDate()) - awayTeam.getAwayGoalsAgainst(s, f.getDate()));
		row.setMediaGolosMarcadosVisitadoCasa(percentagensGolosFRVisitado_5jogos[2]);
		row.setMediaGolosSofridosVisitadoCasa(percentagensGolosFRVisitado_5jogos[3]);
		row.setMediaGolosMarcadosVisitanteFora(percentagensGolosFRVisitante_5jogos[4]);
		row.setMediaGolosSofridosVisitanteFora(percentagensGolosFRVisitante_5jogos[5]);

		return row;
	}

	private static ExcelRow goalSwitchedVenue(Fixture f, Season s, ExcelColumnsCalculation homeTeam,
			ExcelColumnsCalculation awayTeam, ExcelRow row) {

		Double[] percentagensGolosFRVisitado_5jogos = homeTeam.averageGoalsLastGames(f, null, 5);
		Double[] percentagensGolosFRVisitante_5jogos = awayTeam.averageGoalsLastGames(f, null, 5);

		row.setMediaGolosMarcadosVisitadoFora(percentagensGolosFRVisitado_5jogos[4]);
		row.setMediaGolosSofridosVisitadoFora(percentagensGolosFRVisitado_5jogos[5]);
		row.setMediaGolosMarcadosVisitanteCasa(percentagensGolosFRVisitante_5jogos[2]);
		row.setMediaGolosSofridosVisitanteCasa(percentagensGolosFRVisitante_5jogos[3]);

		return row;
	}

	private static ExcelRow goalOverall(Fixture f, Season s, ExcelColumnsCalculation homeTeam,
			ExcelColumnsCalculation awayTeam, ExcelRow row) {

		Double[] percentagensGolosFRVisitado_5jogos = homeTeam.averageGoalsLastGames(f, null, 5);
		Double[] percentagensGolosFRVisitante_5jogos = awayTeam.averageGoalsLastGames(f, null, 5);

		row.setGolosVisitado(homeTeam.getGoals(s, f.getDate()));
		row.setGolosSofridosVisitado(homeTeam.getGoalsAgainst(s, f.getDate()));
		row.setDiferencaGolosVisitado(homeTeam.getGoals(s, f.getDate()) - homeTeam.getGoalsAgainst(s, f.getDate()));
		row.setGolosVisitante(awayTeam.getGoals(s, f.getDate()));
		row.setGolosSofridosVisitante(awayTeam.getGoalsAgainst(s, f.getDate()));
		row.setDiferencaGolosVisitante(awayTeam.getGoals(s, f.getDate()) - awayTeam.getGoalsAgainst(s, f.getDate()));
		row.setMediaGolosMarcadosVisitadoTotal(percentagensGolosFRVisitado_5jogos[0]);
		row.setMediaGolosSofridosVisitadoTotal(percentagensGolosFRVisitado_5jogos[1]);
		row.setMediaGolosMarcadosVisitanteTotal(percentagensGolosFRVisitante_5jogos[0]);
		row.setMediaGolosSofridosVisitanteTotal(percentagensGolosFRVisitante_5jogos[1]);

		return row;
	}

	private static void generateExcelFiles_PlanB(Season s, EntityManager em, String filePath) {

		SeasonController sc = new SeasonController(em);

		WriteToExcel writeToExcelObj = new WriteToExcel(filePath);

		List<String> historicos = new ArrayList<String>();

		// INGLATERRA
		historicos.add("Chelsea");
		historicos.add("Arsenal");
		historicos.add("Liverpool");
		historicos.add("Man City");
		historicos.add("Man United");

		// ESCOCIA
		historicos.add("Rangers");
		historicos.add("Celtic");

		// ALEMANHA
		historicos.add("Bayern Munich");
		historicos.add("Dortmund");
		historicos.add("Schalke 04");
		// historicos.add("Leverkusen");

		// ITALIA
		historicos.add("Juventus");
		historicos.add("Roma");
		historicos.add("Milan");
		historicos.add("Inter");
		historicos.add("Napoli");

		// ESPANHA
		historicos.add("Ath Madrid");
		historicos.add("Barcelona");
		historicos.add("Real Madrid");
		historicos.add("Valencia");

		// FRANÇA
		historicos.add("Paris SG");
		historicos.add("Marseille");
		historicos.add("Lyon");

		// HOLANDA
		historicos.add("Ajax");
		historicos.add("PSV Eindhoven");

		// BELGICA
		historicos.add("Anderlecht");
		historicos.add("Standard");

		// PORTUGAL
		historicos.add("Benfica");
		historicos.add("Porto");
		historicos.add("Sp Lisbon");

		// TURQUIA
		historicos.add("Fenerbahce");
		historicos.add("Besiktas");
		historicos.add("Galatasaray");

		// GRECIA
		historicos.add("Olympiakos");
		historicos.add("Panathinaikos");
		// historicos.add("PAOK");
		// historicos.add("AEK");

		List<Double> ratings = new ArrayList<Double>();
		ratings.add(0.3);
		ratings.add(0.25);
		ratings.add(0.2);
		ratings.add(0.15);
		ratings.add(0.1);

		List<Double> ratingsH2H = new ArrayList<Double>();
		ratingsH2H.add(0.2);
		ratingsH2H.add(0.19);
		ratingsH2H.add(0.17);
		ratingsH2H.add(0.14);
		ratingsH2H.add(0.1);
		ratingsH2H.add(0.06);
		ratingsH2H.add(0.05);
		ratingsH2H.add(0.04);
		ratingsH2H.add(0.03);
		ratingsH2H.add(0.02);
		ratingsH2H.add(0.01);

		double interval = 0.15;

		List<PlanBRow> planBDataList = new ArrayList<PlanBRow>();

		for (Fixture f : s.getFixtures()) {

			if (f.getDate().before(formatter.parseDateTime("23/11/" + (s.getYear() + 2000)).toDate()))
				continue;

			ExcelColumnsCalculation exHomeTeam = new ExcelColumnsCalculation(f.getHomeTeam(), em);
			ExcelColumnsCalculation exAwayTeam = new ExcelColumnsCalculation(f.getAwayTeam(), em);

			// data
			Date date = f.getDate();

			// Visitado e visitante
			String homeTeam = f.getHomeTeam().getName();
			String awayTeam = f.getAwayTeam().getName();

			// Classificações
			Integer class_home = exHomeTeam.getClassification(s, date);
			Integer class_away = exAwayTeam.getClassification(s, date);

			// Resultado
			Integer res_win, res_draw, res_lose;
			Result res = f.getResult();
			if (res.getResultType().getValue() == 0) {
				res_win = 1;
				res_draw = 0;
				res_lose = 0;
			} else if (res.getResultType().getValue() == 1) {
				res_win = 0;
				res_draw = 1;
				res_lose = 0;
			} else {
				res_win = 0;
				res_draw = 0;
				res_lose = 1;
			}

			// Qlt Top Visitado
			List<Integer> topHomeGames = exHomeTeam.getTopNumberOfGames(f.getHomeTeam(), s, date, Venue.HOME);
			Integer Qlt_Top_Visitado_W = topHomeGames.get(0);
			Integer Qlt_Top_Visitado_D = topHomeGames.get(1);
			Integer Qlt_Top_Visitado_L = topHomeGames.get(2);

			// Qlt Ms Visitado
			List<Integer> msHomeGames = exHomeTeam.getMsNumberOfGames(f.getHomeTeam(), s, date, Venue.HOME);
			Integer Qlt_Ms_Visitado_W = msHomeGames.get(0);
			Integer Qlt_Ms_Visitado_D = msHomeGames.get(1);
			Integer Qlt_Ms_Visitado_L = msHomeGames.get(2);

			// Qlt Mi Visitado
			List<Integer> miHomeGames = exHomeTeam.getMiNumberOfGames(f.getHomeTeam(), s, date, Venue.HOME);
			Integer Qlt_Mi_Visitado_W = miHomeGames.get(0);
			Integer Qlt_Mi_Visitado_D = miHomeGames.get(1);
			Integer Qlt_Mi_Visitado_L = miHomeGames.get(2);

			// Qlt Fraco Visitado
			List<Integer> fracoHomeGames = exHomeTeam.getFracoNumberOfGames(f.getHomeTeam(), s, date, Venue.HOME);
			Integer Qlt_Fraco_Visitado_W = fracoHomeGames.get(0);
			Integer Qlt_Fraco_Visitado_D = fracoHomeGames.get(1);
			Integer Qlt_Fraco_Visitado_L = fracoHomeGames.get(2);

			// Qlt Top Visitante
			List<Integer> topAwayGames = exAwayTeam.getTopNumberOfGames(f.getAwayTeam(), s, date, Venue.AWAY);
			Integer Qlt_Top_Visitante_W = topAwayGames.get(0);
			Integer Qlt_Top_Visitante_D = topAwayGames.get(1);
			Integer Qlt_Top_Visitante_L = topAwayGames.get(2);

			// Qlt Ms Visitante
			List<Integer> msAwayGames = exAwayTeam.getMsNumberOfGames(f.getAwayTeam(), s, date, Venue.AWAY);
			Integer Qlt_Ms_Visitante_W = msAwayGames.get(0);
			Integer Qlt_Ms_Visitante_D = msAwayGames.get(1);
			Integer Qlt_Ms_Visitante_L = msAwayGames.get(2);

			// Qlt Mi Visitante
			List<Integer> miAwayGames = exAwayTeam.getMiNumberOfGames(f.getAwayTeam(), s, date, Venue.AWAY);
			Integer Qlt_Mi_Visitante_W = miAwayGames.get(0);
			Integer Qlt_Mi_Visitante_D = miAwayGames.get(1);
			Integer Qlt_Mi_Visitante_L = miAwayGames.get(2);

			// Qlt Fraco Visitante
			List<Integer> fracoAwayGames = exAwayTeam.getFracoNumberOfGames(f.getAwayTeam(), s, date, Venue.AWAY);
			Integer Qlt_Fraco_Visitante_W = fracoAwayGames.get(0);
			Integer Qlt_Fraco_Visitante_D = fracoAwayGames.get(1);
			Integer Qlt_Fraco_Visitante_L = fracoAwayGames.get(2);

			// Ciclo visitado
			Integer cicloTopHome_W = 0;
			Integer cicloTopHome_D = 0;
			Integer cicloTopHome_L = 0;
			Integer cicloMsHome_W = 0;
			Integer cicloMsHome_D = 0;
			Integer cicloMsHome_L = 0;
			Integer cicloMiHome_W = 0;
			Integer cicloMiHome_D = 0;
			Integer cicloMiHome_L = 0;
			Integer cicloFracoHome_W = 0;
			Integer cicloFracoHome_D = 0;
			Integer cicloFracoHome_L = 0;

			List<Integer> mListHome_W = exHomeTeam.getCycleInfo(f, Venue.HOME, ResultType.WIN, s, date);
			for (int i = 0; i < mListHome_W.size(); i++) {
				if (i == 0) {
					cicloTopHome_W = mListHome_W.get(i);
				}
				if (i == 1) {
					cicloMsHome_W = mListHome_W.get(i);
				}
				if (i == 2) {
					cicloMiHome_W = mListHome_W.get(i);
				}
				if (i == 3) {
					cicloFracoHome_W = mListHome_W.get(i);
				}
			}

			List<Integer> mListHome_D = exHomeTeam.getCycleInfo(f, Venue.HOME, ResultType.DRAW, s, date);
			for (int i = 0; i < mListHome_D.size(); i++) {
				if (i == 0) {
					cicloTopHome_D = mListHome_D.get(i);
				}
				if (i == 1) {
					cicloMsHome_D = mListHome_D.get(i);
				}
				if (i == 2) {
					cicloMiHome_D = mListHome_D.get(i);
				}
				if (i == 3) {
					cicloFracoHome_D = mListHome_D.get(i);
				}
			}

			List<Integer> mListHome_L = exHomeTeam.getCycleInfo(f, Venue.HOME, ResultType.LOSE, s, date);
			for (int i = 0; i < mListHome_L.size(); i++) {
				if (i == 0) {
					cicloTopHome_L = mListHome_L.get(i);
				}
				if (i == 1) {
					cicloMsHome_L = mListHome_L.get(i);
				}
				if (i == 2) {
					cicloMiHome_L = mListHome_L.get(i);
				}
				if (i == 3) {
					cicloFracoHome_L = mListHome_L.get(i);
				}
			}

			// Ciclo visitante
			Integer cicloTopAway_W = 0;
			Integer cicloTopAway_D = 0;
			Integer cicloTopAway_L = 0;
			Integer cicloMsAway_W = 0;
			Integer cicloMsAway_D = 0;
			Integer cicloMsAway_L = 0;
			Integer cicloMiAway_W = 0;
			Integer cicloMiAway_D = 0;
			Integer cicloMiAway_L = 0;
			Integer cicloFracoAway_W = 0;
			Integer cicloFracoAway_D = 0;
			Integer cicloFracoAway_L = 0;

			List<Integer> mListAway_W = exAwayTeam.getCycleInfo(f, Venue.AWAY, ResultType.WIN, s, date);
			for (int i = 0; i < mListAway_W.size(); i++) {
				if (i == 0) {
					cicloTopAway_W = mListAway_W.get(i);
				}
				if (i == 1) {
					cicloMsAway_W = mListAway_W.get(i);
				}
				if (i == 2) {
					cicloMiAway_W = mListAway_W.get(i);
				}
				if (i == 3) {
					cicloFracoAway_W = mListAway_W.get(i);
				}
			}

			List<Integer> mListAway_D = exAwayTeam.getCycleInfo(f, Venue.AWAY, ResultType.DRAW, s, date);
			for (int i = 0; i < mListAway_D.size(); i++) {
				if (i == 0) {
					cicloTopAway_D = mListAway_D.get(i);
				}
				if (i == 1) {
					cicloMsAway_D = mListAway_D.get(i);
				}
				if (i == 2) {
					cicloMiAway_D = mListAway_D.get(i);
				}
				if (i == 3) {
					cicloFracoAway_D = mListAway_D.get(i);
				}
			}

			List<Integer> mListAway_L = exAwayTeam.getCycleInfo(f, Venue.AWAY, ResultType.LOSE, s, date);
			for (int i = 0; i < mListAway_L.size(); i++) {
				if (i == 0) {
					cicloTopAway_L = mListAway_L.get(i);
				}
				if (i == 1) {
					cicloMsAway_L = mListAway_L.get(i);
				}
				if (i == 2) {
					cicloMiAway_L = mListAway_L.get(i);
				}
				if (i == 3) {
					cicloFracoAway_L = mListAway_L.get(i);
				}
			}

			// FR Visitado
			Integer frHomeTop1_W = 0;
			Integer frHomeTop1_D = 0;
			Integer frHomeTop1_L = 0;
			Integer frHomeMs1_W = 0;
			Integer frHomeMs1_D = 0;
			Integer frHomeMs1_L = 0;
			Integer frHomeMi1_W = 0;
			Integer frHomeMi1_D = 0;
			Integer frHomeMi1_L = 0;
			Integer frHomeFraco1_W = 0;
			Integer frHomeFraco1_D = 0;
			Integer frHomeFraco1_L = 0;
			Integer frHomeTop2_W = 0;
			Integer frHomeTop2_D = 0;
			Integer frHomeTop2_L = 0;
			Integer frHomeMs2_W = 0;
			Integer frHomeMs2_D = 0;
			Integer frHomeMs2_L = 0;
			Integer frHomeMi2_W = 0;
			Integer frHomeMi2_D = 0;
			Integer frHomeMi2_L = 0;
			Integer frHomeFraco2_W = 0;
			Integer frHomeFraco2_D = 0;
			Integer frHomeFraco2_L = 0;
			Integer frHomeTop3_W = 0;
			Integer frHomeTop3_D = 0;
			Integer frHomeTop3_L = 0;
			Integer frHomeMs3_W = 0;
			Integer frHomeMs3_D = 0;
			Integer frHomeMs3_L = 0;
			Integer frHomeMi3_W = 0;
			Integer frHomeMi3_D = 0;
			Integer frHomeMi3_L = 0;
			Integer frHomeFraco3_W = 0;
			Integer frHomeFraco3_D = 0;
			Integer frHomeFraco3_L = 0;
			Integer frHomeTop4_W = 0;
			Integer frHomeTop4_D = 0;
			Integer frHomeTop4_L = 0;
			Integer frHomeMs4_W = 0;
			Integer frHomeMs4_D = 0;
			Integer frHomeMs4_L = 0;
			Integer frHomeMi4_W = 0;
			Integer frHomeMi4_D = 0;
			Integer frHomeMi4_L = 0;
			Integer frHomeFraco4_W = 0;
			Integer frHomeFraco4_D = 0;
			Integer frHomeFraco4_L = 0;
			Integer frHomeTop5_W = 0;
			Integer frHomeTop5_D = 0;
			Integer frHomeTop5_L = 0;
			Integer frHomeMs5_W = 0;
			Integer frHomeMs5_D = 0;
			Integer frHomeMs5_L = 0;
			Integer frHomeMi5_W = 0;
			Integer frHomeMi5_D = 0;
			Integer frHomeMi5_L = 0;
			Integer frHomeFraco5_W = 0;
			Integer frHomeFraco5_D = 0;
			Integer frHomeFraco5_L = 0;

			FixtureController fixC = new FixtureController(em);
			List<Fixture> fixListH = fixC.getTeamBeforeFixtures(f.getHomeTeam(), s, date, null, 5);
			int ho = 1;
			for (Fixture fixt : fixListH) {
				if (fixt != null) {
					Team opponent = new Team();
					if (fixt.getAwayTeam().getName().equals(homeTeam)) {
						opponent = fixt.getHomeTeam();
					} else {
						opponent = fixt.getAwayTeam();
					}

					ExcelColumnsCalculation exOpponent = new ExcelColumnsCalculation(opponent, em);
					Integer posOp = exOpponent.getClassification(s, date);
					if (fixt.getResult().getResultType().getValue() == 0) {
						if (posOp <= 5) {
							if (ho == 1) {
								frHomeTop1_W = 1;
								frHomeTop1_D = 0;
								frHomeTop1_L = 0;
							}
							if (ho == 2) {
								frHomeTop2_W = 1;
								frHomeTop2_D = 0;
								frHomeTop2_L = 0;
							}
							if (ho == 3) {
								frHomeTop3_W = 1;
								frHomeTop3_D = 0;
								frHomeTop3_L = 0;
							}
							if (ho == 4) {
								frHomeTop4_W = 1;
								frHomeTop4_D = 0;
								frHomeTop4_L = 0;
							}
							if (ho == 5) {
								frHomeTop5_W = 1;
								frHomeTop5_D = 0;
								frHomeTop5_L = 0;
							}
						} else if (5 < posOp && posOp <= 10) {
							if (ho == 1) {
								frHomeMs1_W = 1;
								frHomeMs1_D = 0;
								frHomeMs1_L = 0;
							}
							if (ho == 2) {
								frHomeMs2_W = 1;
								frHomeMs2_D = 0;
								frHomeMs2_L = 0;
							}
							if (ho == 3) {
								frHomeMs3_W = 1;
								frHomeMs3_D = 0;
								frHomeMs3_L = 0;
							}
							if (ho == 4) {
								frHomeMs4_W = 1;
								frHomeMs4_D = 0;
								frHomeMs4_L = 0;
							}
							if (ho == 5) {
								frHomeMs5_W = 1;
								frHomeMs5_D = 0;
								frHomeMs5_L = 0;
							}
						} else if (10 < posOp && posOp <= 15) {
							if (ho == 1) {
								frHomeMi1_W = 1;
								frHomeMi1_D = 0;
								frHomeMi1_L = 0;
							}
							if (ho == 2) {
								frHomeMi2_W = 1;
								frHomeMi2_D = 0;
								frHomeMi2_L = 0;
							}
							if (ho == 3) {
								frHomeMi3_W = 1;
								frHomeMi3_D = 0;
								frHomeMi3_L = 0;
							}
							if (ho == 4) {
								frHomeMi4_W = 1;
								frHomeMi4_D = 0;
								frHomeMi4_L = 0;
							}
							if (ho == 5) {
								frHomeMi5_W = 1;
								frHomeMi5_D = 0;
								frHomeMi5_L = 0;
							}
						} else if (15 < posOp) {
							if (ho == 1) {
								frHomeFraco1_W = 1;
								frHomeFraco1_L = 0;
								frHomeFraco1_D = 0;
							}
							if (ho == 2) {
								frHomeFraco2_W = 1;
								frHomeFraco2_D = 0;
								frHomeFraco2_L = 0;
							}
							if (ho == 3) {
								frHomeFraco3_W = 1;
								frHomeFraco3_D = 0;
								frHomeFraco3_L = 0;
							}
							if (ho == 4) {
								frHomeFraco4_W = 1;
								frHomeFraco4_D = 0;
								frHomeFraco4_L = 0;
							}
							if (ho == 5) {
								frHomeFraco5_W = 1;
								frHomeFraco5_D = 0;
								frHomeFraco5_L = 0;
							}
						}
					}
					if (fixt.getResult().getResultType().getValue() == 1) {
						if (posOp <= 5) {
							if (ho == 1) {
								frHomeTop1_W = 0;
								frHomeTop1_D = 1;
								frHomeTop1_L = 0;
							}
							if (ho == 2) {
								frHomeTop2_W = 0;
								frHomeTop2_D = 1;
								frHomeTop2_L = 0;
							}
							if (ho == 3) {
								frHomeTop3_W = 0;
								frHomeTop3_D = 1;
								frHomeTop3_L = 0;
							}
							if (ho == 4) {
								frHomeTop4_W = 0;
								frHomeTop4_D = 1;
								frHomeTop4_L = 0;
							}
							if (ho == 5) {
								frHomeTop5_W = 0;
								frHomeTop5_D = 1;
								frHomeTop5_L = 0;
							}
						} else if (5 < posOp && posOp <= 10) {
							if (ho == 1) {
								frHomeMs1_W = 0;
								frHomeMs1_D = 1;
								frHomeMs1_L = 0;
							}
							if (ho == 2) {
								frHomeMs2_W = 0;
								frHomeMs2_D = 1;
								frHomeMs2_L = 0;
							}
							if (ho == 3) {
								frHomeMs3_W = 0;
								frHomeMs3_D = 1;
								frHomeMs3_L = 0;
							}
							if (ho == 4) {
								frHomeMs4_W = 0;
								frHomeMs4_D = 1;
								frHomeMs4_L = 0;
							}
							if (ho == 5) {
								frHomeMs5_W = 0;
								frHomeMs5_D = 1;
								frHomeMs5_L = 0;
							}
						} else if (10 < posOp && posOp <= 15) {
							if (ho == 1) {
								frHomeMi1_W = 0;
								frHomeMi1_D = 1;
								frHomeMi1_L = 0;
							}
							if (ho == 2) {
								frHomeMi2_W = 0;
								frHomeMi2_D = 1;
								frHomeMi2_L = 0;
							}
							if (ho == 3) {
								frHomeMi3_W = 0;
								frHomeMi3_D = 1;
								frHomeMi3_L = 0;
							}
							if (ho == 4) {
								frHomeMi4_W = 0;
								frHomeMi4_D = 1;
								frHomeMi4_L = 0;
							}
							if (ho == 5) {
								frHomeMi5_W = 0;
								frHomeMi5_D = 1;
								frHomeMi5_L = 0;
							}
						} else if (15 < posOp) {
							if (ho == 1) {
								frHomeFraco1_W = 0;
								frHomeFraco1_L = 1;
								frHomeFraco1_D = 0;
							}
							if (ho == 2) {
								frHomeFraco2_W = 0;
								frHomeFraco2_D = 1;
								frHomeFraco2_L = 0;
							}
							if (ho == 3) {
								frHomeFraco3_W = 0;
								frHomeFraco3_D = 1;
								frHomeFraco3_L = 0;
							}
							if (ho == 4) {
								frHomeFraco4_W = 0;
								frHomeFraco4_D = 1;
								frHomeFraco4_L = 0;
							}
							if (ho == 5) {
								frHomeFraco5_W = 0;
								frHomeFraco5_D = 1;
								frHomeFraco5_L = 0;
							}
						}
					}
					if (fixt.getResult().getResultType().getValue() == 2) {
						if (posOp <= 5) {
							if (ho == 1) {
								frHomeTop1_W = 0;
								frHomeTop1_D = 0;
								frHomeTop1_L = 1;
							}
							if (ho == 2) {
								frHomeTop2_W = 0;
								frHomeTop2_D = 0;
								frHomeTop2_L = 1;
							}
							if (ho == 3) {
								frHomeTop3_W = 0;
								frHomeTop3_D = 0;
								frHomeTop3_L = 1;
							}
							if (ho == 4) {
								frHomeTop4_W = 0;
								frHomeTop4_D = 0;
								frHomeTop4_L = 1;
							}
							if (ho == 5) {
								frHomeTop5_W = 0;
								frHomeTop5_D = 0;
								frHomeTop5_L = 1;
							}
						} else if (5 < posOp && posOp <= 10) {
							if (ho == 1) {
								frHomeMs1_W = 0;
								frHomeMs1_D = 0;
								frHomeMs1_L = 1;
							}
							if (ho == 2) {
								frHomeMs2_W = 0;
								frHomeMs2_D = 0;
								frHomeMs2_L = 1;
							}
							if (ho == 3) {
								frHomeMs3_W = 0;
								frHomeMs3_D = 0;
								frHomeMs3_L = 1;
							}
							if (ho == 4) {
								frHomeMs4_W = 0;
								frHomeMs4_D = 0;
								frHomeMs4_L = 1;
							}
							if (ho == 5) {
								frHomeMs5_W = 0;
								frHomeMs5_D = 0;
								frHomeMs5_L = 1;
							}
						} else if (10 < posOp && posOp <= 15) {
							if (ho == 1) {
								frHomeMi1_W = 0;
								frHomeMi1_D = 0;
								frHomeMi1_L = 1;
							}
							if (ho == 2) {
								frHomeMi2_W = 0;
								frHomeMi2_D = 0;
								frHomeMi2_L = 1;
							}
							if (ho == 3) {
								frHomeMi3_W = 0;
								frHomeMi3_D = 0;
								frHomeMi3_L = 1;
							}
							if (ho == 4) {
								frHomeMi4_W = 0;
								frHomeMi4_D = 0;
								frHomeMi4_L = 1;
							}
							if (ho == 5) {
								frHomeMi5_W = 0;
								frHomeMi5_D = 0;
								frHomeMi5_L = 1;
							}
						} else if (15 < posOp) {
							if (ho == 1) {
								frHomeFraco1_W = 0;
								frHomeFraco1_L = 0;
								frHomeFraco1_D = 1;
							}
							if (ho == 2) {
								frHomeFraco2_W = 0;
								frHomeFraco2_D = 0;
								frHomeFraco2_L = 1;
							}
							if (ho == 3) {
								frHomeFraco3_W = 0;
								frHomeFraco3_D = 0;
								frHomeFraco3_L = 1;
							}
							if (ho == 4) {
								frHomeFraco4_W = 0;
								frHomeFraco4_D = 0;
								frHomeFraco4_L = 1;
							}
							if (ho == 5) {
								frHomeFraco5_W = 0;
								frHomeFraco5_D = 0;
								frHomeFraco5_L = 1;
							}
						}
					}
					ho++;
				}
			}

			// FR Visitante
			Integer frAwayTop1_W = 0;
			Integer frAwayTop1_D = 0;
			Integer frAwayTop1_L = 0;
			Integer frAwayMs1_W = 0;
			Integer frAwayMs1_D = 0;
			Integer frAwayMs1_L = 0;
			Integer frAwayMi1_W = 0;
			Integer frAwayMi1_D = 0;
			Integer frAwayMi1_L = 0;
			Integer frAwayFraco1_W = 0;
			Integer frAwayFraco1_D = 0;
			Integer frAwayFraco1_L = 0;
			Integer frAwayTop2_W = 0;
			Integer frAwayTop2_D = 0;
			Integer frAwayTop2_L = 0;
			Integer frAwayMs2_W = 0;
			Integer frAwayMs2_D = 0;
			Integer frAwayMs2_L = 0;
			Integer frAwayMi2_W = 0;
			Integer frAwayMi2_D = 0;
			Integer frAwayMi2_L = 0;
			Integer frAwayFraco2_W = 0;
			Integer frAwayFraco2_D = 0;
			Integer frAwayFraco2_L = 0;
			Integer frAwayTop3_W = 0;
			Integer frAwayTop3_D = 0;
			Integer frAwayTop3_L = 0;
			Integer frAwayMs3_W = 0;
			Integer frAwayMs3_D = 0;
			Integer frAwayMs3_L = 0;
			Integer frAwayMi3_W = 0;
			Integer frAwayMi3_D = 0;
			Integer frAwayMi3_L = 0;
			Integer frAwayFraco3_W = 0;
			Integer frAwayFraco3_D = 0;
			Integer frAwayFraco3_L = 0;
			Integer frAwayTop4_W = 0;
			Integer frAwayTop4_D = 0;
			Integer frAwayTop4_L = 0;
			Integer frAwayMs4_W = 0;
			Integer frAwayMs4_D = 0;
			Integer frAwayMs4_L = 0;
			Integer frAwayMi4_W = 0;
			Integer frAwayMi4_D = 0;
			Integer frAwayMi4_L = 0;
			Integer frAwayFraco4_W = 0;
			Integer frAwayFraco4_D = 0;
			Integer frAwayFraco4_L = 0;
			Integer frAwayTop5_W = 0;
			Integer frAwayTop5_D = 0;
			Integer frAwayTop5_L = 0;
			Integer frAwayMs5_W = 0;
			Integer frAwayMs5_D = 0;
			Integer frAwayMs5_L = 0;
			Integer frAwayMi5_W = 0;
			Integer frAwayMi5_D = 0;
			Integer frAwayMi5_L = 0;
			Integer frAwayFraco5_W = 0;
			Integer frAwayFraco5_D = 0;
			Integer frAwayFraco5_L = 0;

			List<Fixture> fixListA = fixC.getTeamBeforeFixtures(f.getAwayTeam(), s, date, null, 5);
			int aw = 1;
			for (Fixture fixt : fixListH) {
				if (fixt != null) {
					Team opponent = new Team();
					if (fixt.getAwayTeam().getName().equals(awayTeam)) {
						opponent = fixt.getHomeTeam();
					} else {
						opponent = fixt.getAwayTeam();
					}
					ExcelColumnsCalculation exOpponent = new ExcelColumnsCalculation(opponent, em);
					Integer posOp = exOpponent.getClassification(s, date);
					if (fixt.getResult().getResultType().getValue() == 0) {
						if (posOp <= 5) {
							if (aw == 1) {
								frAwayTop1_W = 1;
								frAwayTop1_D = 0;
								frAwayTop1_L = 0;
							}
							if (aw == 2) {
								frAwayTop2_W = 1;
								frAwayTop2_D = 0;
								frAwayTop2_L = 0;
							}
							if (aw == 3) {
								frAwayTop3_W = 1;
								frAwayTop3_D = 0;
								frAwayTop3_L = 0;
							}
							if (aw == 4) {
								frAwayTop4_W = 1;
								frAwayTop4_D = 0;
								frAwayTop4_L = 0;
							}
							if (aw == 5) {
								frAwayTop5_W = 1;
								frAwayTop5_D = 0;
								frAwayTop5_L = 0;
							}
						} else if (5 < posOp && posOp <= 10) {
							if (aw == 1) {
								frAwayMs1_W = 1;
								frAwayMs1_D = 0;
								frAwayMs1_L = 0;
							}
							if (aw == 2) {
								frAwayMs2_W = 1;
								frAwayMs2_D = 0;
								frAwayMs2_L = 0;
							}
							if (aw == 3) {
								frAwayMs3_W = 1;
								frAwayMs3_D = 0;
								frAwayMs3_L = 0;
							}
							if (aw == 4) {
								frAwayMs4_W = 1;
								frAwayMs4_D = 0;
								frAwayMs4_L = 0;
							}
							if (aw == 5) {
								frAwayMs5_W = 1;
								frAwayMs5_D = 0;
								frAwayMs5_L = 0;
							}
						} else if (10 < posOp && posOp <= 15) {
							if (aw == 1) {
								frAwayMi1_W = 1;
								frAwayMi1_D = 0;
								frAwayMi1_L = 0;
							}
							if (aw == 2) {
								frAwayMi2_W = 1;
								frAwayMi2_D = 0;
								frAwayMi2_L = 0;
							}
							if (aw == 3) {
								frAwayMi3_W = 1;
								frAwayMi3_D = 0;
								frAwayMi3_L = 0;
							}
							if (aw == 4) {
								frAwayMi4_W = 1;
								frAwayMi4_D = 0;
								frAwayMi4_L = 0;
							}
							if (aw == 5) {
								frAwayMi5_W = 1;
								frAwayMi5_D = 0;
								frAwayMi5_L = 0;
							}
						} else if (15 < posOp) {
							if (aw == 1) {
								frAwayFraco1_W = 1;
								frAwayFraco1_L = 0;
								frAwayFraco1_D = 0;
							}
							if (aw == 2) {
								frAwayFraco2_W = 1;
								frAwayFraco2_D = 0;
								frAwayFraco2_L = 0;
							}
							if (aw == 3) {
								frAwayFraco3_W = 1;
								frAwayFraco3_D = 0;
								frAwayFraco3_L = 0;
							}
							if (aw == 4) {
								frAwayFraco4_W = 1;
								frAwayFraco4_D = 0;
								frAwayFraco4_L = 0;
							}
							if (aw == 5) {
								frAwayFraco5_W = 1;
								frAwayFraco5_D = 0;
								frAwayFraco5_L = 0;
							}
						}
					}
					if (fixt.getResult().getResultType().getValue() == 1) {
						if (posOp <= 5) {
							if (aw == 1) {
								frAwayTop1_W = 0;
								frAwayTop1_D = 1;
								frAwayTop1_L = 0;
							}
							if (aw == 2) {
								frAwayTop2_W = 0;
								frAwayTop2_D = 1;
								frAwayTop2_L = 0;
							}
							if (aw == 3) {
								frAwayTop3_W = 0;
								frAwayTop3_D = 1;
								frAwayTop3_L = 0;
							}
							if (aw == 4) {
								frAwayTop4_W = 0;
								frAwayTop4_D = 1;
								frAwayTop4_L = 0;
							}
							if (aw == 5) {
								frAwayTop5_W = 0;
								frAwayTop5_D = 1;
								frAwayTop5_L = 0;
							}
						} else if (5 < posOp && posOp <= 10) {
							if (aw == 1) {
								frAwayMs1_W = 0;
								frAwayMs1_D = 1;
								frAwayMs1_L = 0;
							}
							if (aw == 2) {
								frAwayMs2_W = 0;
								frAwayMs2_D = 1;
								frAwayMs2_L = 0;
							}
							if (aw == 3) {
								frAwayMs3_W = 0;
								frAwayMs3_D = 1;
								frAwayMs3_L = 0;
							}
							if (aw == 4) {
								frAwayMs4_W = 0;
								frAwayMs4_D = 1;
								frAwayMs4_L = 0;
							}
							if (aw == 5) {
								frAwayMs5_W = 0;
								frAwayMs5_D = 1;
								frAwayMs5_L = 0;
							}
						} else if (10 < posOp && posOp <= 15) {
							if (aw == 1) {
								frAwayMi1_W = 0;
								frAwayMi1_D = 1;
								frAwayMi1_L = 0;
							}
							if (aw == 2) {
								frAwayMi2_W = 0;
								frAwayMi2_D = 1;
								frAwayMi2_L = 0;
							}
							if (aw == 3) {
								frAwayMi3_W = 0;
								frAwayMi3_D = 1;
								frAwayMi3_L = 0;
							}
							if (aw == 4) {
								frAwayMi4_W = 0;
								frAwayMi4_D = 1;
								frAwayMi4_L = 0;
							}
							if (aw == 5) {
								frAwayMi5_W = 0;
								frAwayMi5_D = 1;
								frAwayMi5_L = 0;
							}
						} else if (15 < posOp) {
							if (aw == 1) {
								frAwayFraco1_W = 0;
								frAwayFraco1_L = 1;
								frAwayFraco1_D = 0;
							}
							if (aw == 2) {
								frAwayFraco2_W = 0;
								frAwayFraco2_D = 1;
								frAwayFraco2_L = 0;
							}
							if (aw == 3) {
								frAwayFraco3_W = 0;
								frAwayFraco3_D = 1;
								frAwayFraco3_L = 0;
							}
							if (aw == 4) {
								frAwayFraco4_W = 0;
								frAwayFraco4_D = 1;
								frAwayFraco4_L = 0;
							}
							if (aw == 5) {
								frAwayFraco5_W = 0;
								frAwayFraco5_D = 1;
								frAwayFraco5_L = 0;
							}
						}
					}
					if (fixt.getResult().getResultType().getValue() == 2) {
						if (posOp <= 5) {
							if (aw == 1) {
								frAwayTop1_W = 0;
								frAwayTop1_D = 0;
								frAwayTop1_L = 1;
							}
							if (aw == 2) {
								frAwayTop2_W = 0;
								frAwayTop2_D = 0;
								frAwayTop2_L = 1;
							}
							if (aw == 3) {
								frAwayTop3_W = 0;
								frAwayTop3_D = 0;
								frAwayTop3_L = 1;
							}
							if (aw == 4) {
								frAwayTop4_W = 0;
								frAwayTop4_D = 0;
								frAwayTop4_L = 1;
							}
							if (aw == 5) {
								frAwayTop5_W = 0;
								frAwayTop5_D = 0;
								frAwayTop5_L = 1;
							}
						} else if (5 < posOp && posOp <= 10) {
							if (aw == 1) {
								frAwayMs1_W = 0;
								frAwayMs1_D = 0;
								frAwayMs1_L = 1;
							}
							if (aw == 2) {
								frAwayMs2_W = 0;
								frAwayMs2_D = 0;
								frAwayMs2_L = 1;
							}
							if (aw == 3) {
								frAwayMs3_W = 0;
								frAwayMs3_D = 0;
								frAwayMs3_L = 1;
							}
							if (aw == 4) {
								frAwayMs4_W = 0;
								frAwayMs4_D = 0;
								frAwayMs4_L = 1;
							}
							if (aw == 5) {
								frAwayMs5_W = 0;
								frAwayMs5_D = 0;
								frAwayMs5_L = 1;
							}
						} else if (10 < posOp && posOp <= 15) {
							if (aw == 1) {
								frAwayMi1_W = 0;
								frAwayMi1_D = 0;
								frAwayMi1_L = 1;
							}
							if (aw == 2) {
								frAwayMi2_W = 0;
								frAwayMi2_D = 0;
								frAwayMi2_L = 1;
							}
							if (aw == 3) {
								frAwayMi3_W = 0;
								frAwayMi3_D = 0;
								frAwayMi3_L = 1;
							}
							if (aw == 4) {
								frAwayMi4_W = 0;
								frAwayMi4_D = 0;
								frAwayMi4_L = 1;
							}
							if (aw == 5) {
								frAwayMi5_W = 0;
								frAwayMi5_D = 0;
								frAwayMi5_L = 1;
							}
						} else if (15 < posOp) {
							if (aw == 1) {
								frAwayFraco1_W = 0;
								frAwayFraco1_L = 0;
								frAwayFraco1_D = 1;
							}
							if (aw == 2) {
								frAwayFraco2_W = 0;
								frAwayFraco2_D = 0;
								frAwayFraco2_L = 1;
							}
							if (aw == 3) {
								frAwayFraco3_W = 0;
								frAwayFraco3_D = 0;
								frAwayFraco3_L = 1;
							}
							if (aw == 4) {
								frAwayFraco4_W = 0;
								frAwayFraco4_D = 0;
								frAwayFraco4_L = 1;
							}
							if (aw == 5) {
								frAwayFraco5_W = 0;
								frAwayFraco5_D = 0;
								frAwayFraco5_L = 1;
							}
						}
					}
					aw++;
				}
			}

			// H2H
			Integer h2hRes1_W = 0;
			Integer h2hRes1_D = 0;
			Integer h2hRes1_L = 0;
			Integer h2hRes2_W = 0;
			Integer h2hRes2_D = 0;
			Integer h2hRes2_L = 0;
			Integer h2hRes3_W = 0;
			Integer h2hRes3_D = 0;
			Integer h2hRes3_L = 0;
			Integer h2hRes4_W = 0;
			Integer h2hRes4_D = 0;
			Integer h2hRes4_L = 0;
			Integer h2hRes5_W = 0;
			Integer h2hRes5_D = 0;
			Integer h2hRes5_L = 0;
			Integer h2hRes6_W = 0;
			Integer h2hRes6_D = 0;
			Integer h2hRes6_L = 0;
			Integer h2hRes7_W = 0;
			Integer h2hRes7_D = 0;
			Integer h2hRes7_L = 0;
			Integer h2hRes8_W = 0;
			Integer h2hRes8_D = 0;
			Integer h2hRes8_L = 0;
			Integer h2hRes9_W = 0;
			Integer h2hRes9_D = 0;
			Integer h2hRes9_L = 0;
			Integer h2hRes10_W = 0;
			Integer h2hRes10_D = 0;
			Integer h2hRes10_L = 0;

			List<Fixture> h2h = f.getH2h();
			if (h2h.size() != 0) {
				for (int i = 0; i < h2h.size(); i++) {
					Fixture mF = h2h.get(h2h.size() - 1 - i);
					if (i == 0) {
						if (mF.getResult().getResultType().equals(ResultType.WIN)) {
							h2hRes1_W = 1;
							h2hRes1_D = 0;
							h2hRes1_L = 0;
						}

						else if (mF.getResult().getResultType().equals(ResultType.DRAW)) {
							h2hRes1_W = 0;
							h2hRes1_D = 1;
							h2hRes1_L = 0;
						}

						else { // (mF.getResult().equals(ResultType.LOSE)) {
							h2hRes1_W = 0;
							h2hRes1_D = 0;
							h2hRes1_L = 1;
						}

					}
					if (i == 1) {
						if (mF.getResult().getResultType().equals(ResultType.WIN)) {
							h2hRes2_W = 1;
							h2hRes2_D = 0;
							h2hRes2_L = 0;
						} else if (mF.getResult().getResultType().equals(ResultType.DRAW)) {
							h2hRes2_W = 0;
							h2hRes2_D = 1;
							h2hRes2_L = 0;
						} else { // (mF.getResult().equals(ResultType.LOSE)) {
							h2hRes2_W = 0;
							h2hRes2_D = 0;
							h2hRes2_L = 1;
						}
					}
					if (i == 2) {
						if (mF.getResult().getResultType().equals(ResultType.WIN)) {
							h2hRes3_W = 1;
							h2hRes3_D = 0;
							h2hRes3_L = 0;
						} else if (mF.getResult().getResultType().equals(ResultType.DRAW)) {
							h2hRes3_W = 0;
							h2hRes3_D = 1;
							h2hRes3_L = 0;
						} else { // (mF.getResult().equals(ResultType.LOSE)) {
							h2hRes3_W = 0;
							h2hRes3_D = 0;
							h2hRes3_L = 1;
						}

					}
					if (i == 3) {
						if (mF.getResult().getResultType().equals(ResultType.WIN)) {
							h2hRes4_W = 1;
							h2hRes4_D = 0;
							h2hRes4_L = 0;
						} else if (mF.getResult().getResultType().equals(ResultType.DRAW)) {
							h2hRes4_W = 0;
							h2hRes4_D = 1;
							h2hRes4_L = 0;
						} else { // (mF.getResult().equals(ResultType.LOSE)) {
							h2hRes4_W = 0;
							h2hRes4_D = 0;
							h2hRes4_L = 1;
						}
					}
					if (i == 4) {
						if (mF.getResult().getResultType().equals(ResultType.WIN)) {
							h2hRes5_W = 1;
							h2hRes5_D = 0;
							h2hRes5_L = 0;
						} else if (mF.getResult().getResultType().equals(ResultType.DRAW)) {
							h2hRes5_W = 0;
							h2hRes5_D = 1;
							h2hRes5_L = 0;
						} else { // (mF.getResult().equals(ResultType.LOSE)) {
							h2hRes5_W = 0;
							h2hRes5_D = 0;
							h2hRes5_L = 1;
						}
					}
					if (i == 5) {
						if (mF.getResult().getResultType().equals(ResultType.WIN)) {
							h2hRes6_W = 1;
							h2hRes6_D = 0;
							h2hRes6_L = 0;
						} else if (mF.getResult().getResultType().equals(ResultType.DRAW)) {
							h2hRes6_W = 0;
							h2hRes6_D = 1;
							h2hRes6_L = 0;
						} else { // (mF.getResult().equals(ResultType.LOSE)) {
							h2hRes6_W = 0;
							h2hRes6_D = 0;
							h2hRes6_L = 1;
						}
					}
					if (i == 6) {
						if (mF.getResult().getResultType().equals(ResultType.WIN)) {
							h2hRes7_W = 1;
							h2hRes7_D = 0;
							h2hRes7_L = 0;
						} else if (mF.getResult().getResultType().equals(ResultType.DRAW)) {
							h2hRes7_W = 0;
							h2hRes7_D = 1;
							h2hRes7_L = 0;
						} else { // (mF.getResult().equals(ResultType.LOSE)) {
							h2hRes7_W = 0;
							h2hRes7_D = 0;
							h2hRes7_L = 1;
						}
					}
					if (i == 7) {
						if (mF.getResult().getResultType().equals(ResultType.WIN)) {
							h2hRes8_W = 1;
							h2hRes8_D = 0;
							h2hRes8_L = 0;
						} else if (mF.getResult().getResultType().equals(ResultType.DRAW)) {
							h2hRes8_W = 0;
							h2hRes8_D = 1;
							h2hRes8_L = 0;
						} else { // (mF.getResult().equals(ResultType.LOSE)) {
							h2hRes8_W = 0;
							h2hRes8_D = 0;
							h2hRes8_L = 1;
						}
					}
					if (i == 8) {
						if (mF.getResult().getResultType().equals(ResultType.WIN)) {
							h2hRes9_W = 1;
							h2hRes9_D = 0;
							h2hRes9_L = 0;
						} else if (mF.getResult().getResultType().equals(ResultType.DRAW)) {
							h2hRes9_W = 0;
							h2hRes9_D = 1;
							h2hRes9_L = 0;
						} else { // (mF.getResult().equals(ResultType.LOSE)) {
							h2hRes9_W = 0;
							h2hRes9_D = 0;
							h2hRes9_L = 1;
						}
					}
					if (i == 9) {
						if (mF.getResult().getResultType().equals(ResultType.WIN)) {
							h2hRes10_W = 1;
							h2hRes10_D = 0;
							h2hRes10_L = 0;
						} else if (mF.getResult().getResultType().equals(ResultType.DRAW)) {
							h2hRes10_W = 0;
							h2hRes10_D = 1;
							h2hRes10_L = 0;
						} else { // (mF.getResult().equals(ResultType.LOSE)) {
							h2hRes10_W = 0;
							h2hRes10_D = 0;
							h2hRes10_L = 1;
						}
						break;
					}
				}
			}

			// Odds
			Double oddFv_W = f.getOdd().getBackHomeWin();
			Double oddCnt_W = f.getOdd().getLayHomeWin();
			Double oddFv_D = f.getOdd().getBackDraw();
			Double oddCnt_D = f.getOdd().getLayDraw();
			Double oddFv_L = f.getOdd().getBackAwayWin();
			Double oddCnt_L = f.getOdd().getLayAwayWin();

			if (oddCnt_W == null) {
				oddCnt_W = oddFv_W + 0.1;
			}
			if (oddCnt_D == null) {
				oddCnt_D = oddFv_D + 0.1;
			}
			if (oddCnt_L == null) {
				oddCnt_L = oddFv_L + 0.1;
			}

			PlanBRow planBRow_Win = new PlanBRow(date, homeTeam, awayTeam, class_home, class_away, res_win,
					Qlt_Top_Visitado_W, Qlt_Ms_Visitado_W, Qlt_Mi_Visitado_W, Qlt_Fraco_Visitado_W, Qlt_Top_Visitante_L,
					Qlt_Ms_Visitante_L, Qlt_Mi_Visitante_L, Qlt_Fraco_Visitante_L, cicloTopHome_W, cicloMsHome_W,
					cicloMiHome_W, cicloFracoHome_W, cicloTopAway_L, cicloMsAway_L, cicloMiAway_L, cicloFracoAway_L,
					frHomeTop1_W, frHomeTop2_W, frHomeTop3_W, frHomeTop4_W, frHomeTop5_W, frHomeMs1_W, frHomeMs2_W,
					frHomeMs3_W, frHomeMs4_W, frHomeMs5_W, frHomeMi1_W, frHomeMi2_W, frHomeMi3_W, frHomeMi4_W,
					frHomeMi5_W, frHomeFraco1_W, frHomeFraco2_W, frHomeFraco3_W, frHomeFraco4_W, frHomeFraco5_W,
					frAwayTop1_L, frAwayTop2_L, frAwayTop3_L, frAwayTop4_L, frAwayTop5_L, frAwayMs1_L, frAwayMs2_L,
					frAwayMs3_L, frAwayMs4_L, frAwayMs5_L, frAwayMi1_L, frAwayMi2_L, frAwayMi3_L, frAwayMi4_L,
					frAwayMi5_L, frAwayFraco1_L, frAwayFraco2_L, frAwayFraco3_L, frAwayFraco4_L, frAwayFraco5_L,
					h2hRes1_W, h2hRes2_W, h2hRes3_W, h2hRes4_W, h2hRes5_W, h2hRes6_W, h2hRes7_W, h2hRes8_W, h2hRes9_W,
					h2hRes10_W, oddFv_W, oddCnt_W);
			PlanBRow planBRow_Draw = new PlanBRow(date, homeTeam, awayTeam, class_home, class_away, res_draw,
					Qlt_Top_Visitado_D, Qlt_Ms_Visitado_D, Qlt_Mi_Visitado_D, Qlt_Fraco_Visitado_D, Qlt_Top_Visitante_D,
					Qlt_Ms_Visitante_D, Qlt_Mi_Visitante_D, Qlt_Fraco_Visitante_D, cicloTopHome_D, cicloMsHome_D,
					cicloMiHome_D, cicloFracoHome_D, cicloTopAway_D, cicloMsAway_D, cicloMiAway_D, cicloFracoAway_D,
					frHomeTop1_D, frHomeTop2_D, frHomeTop3_D, frHomeTop4_D, frHomeTop5_D, frHomeMs1_D, frHomeMs2_D,
					frHomeMs3_D, frHomeMs4_D, frHomeMs5_D, frHomeMi1_D, frHomeMi2_D, frHomeMi3_D, frHomeMi4_D,
					frHomeMi5_D, frHomeFraco1_D, frHomeFraco2_D, frHomeFraco3_D, frHomeFraco4_D, frHomeFraco5_D,
					frAwayTop1_D, frAwayTop2_D, frAwayTop3_D, frAwayTop4_D, frAwayTop5_D, frAwayMs1_D, frAwayMs2_D,
					frAwayMs3_D, frAwayMs4_D, frAwayMs5_D, frAwayMi1_D, frAwayMi2_D, frAwayMi3_D, frAwayMi4_D,
					frAwayMi5_D, frAwayFraco1_D, frAwayFraco2_D, frAwayFraco3_D, frAwayFraco4_D, frAwayFraco5_D,
					h2hRes1_D, h2hRes2_D, h2hRes3_D, h2hRes4_D, h2hRes5_D, h2hRes6_D, h2hRes7_D, h2hRes8_D, h2hRes9_D,
					h2hRes10_D, oddFv_D, oddCnt_D);
			PlanBRow planBRow_Lose = new PlanBRow(date, homeTeam, awayTeam, class_home, class_away, res_lose,
					Qlt_Top_Visitado_L, Qlt_Ms_Visitado_L, Qlt_Mi_Visitado_L, Qlt_Fraco_Visitado_L, Qlt_Top_Visitante_W,
					Qlt_Ms_Visitante_W, Qlt_Mi_Visitante_W, Qlt_Fraco_Visitante_W, cicloTopHome_L, cicloMsHome_L,
					cicloMiHome_L, cicloFracoHome_L, cicloTopAway_W, cicloMsAway_W, cicloMiAway_W, cicloFracoAway_W,
					frHomeTop1_L, frHomeTop2_L, frHomeTop3_L, frHomeTop4_L, frHomeTop5_L, frHomeMs1_L, frHomeMs2_L,
					frHomeMs3_L, frHomeMs4_L, frHomeMs5_L, frHomeMi1_L, frHomeMi2_L, frHomeMi3_L, frHomeMi4_L,
					frHomeMi5_L, frHomeFraco1_L, frHomeFraco2_L, frHomeFraco3_L, frHomeFraco4_L, frHomeFraco5_L,
					frAwayTop1_W, frAwayTop2_W, frAwayTop3_W, frAwayTop4_W, frAwayTop5_W, frAwayMs1_W, frAwayMs2_W,
					frAwayMs3_W, frAwayMs4_W, frAwayMs5_W, frAwayMi1_W, frAwayMi2_L, frAwayMi3_W, frAwayMi4_W,
					frAwayMi5_W, frAwayFraco1_W, frAwayFraco2_W, frAwayFraco3_W, frAwayFraco4_W, frAwayFraco5_W,
					h2hRes1_L, h2hRes2_L, h2hRes3_L, h2hRes4_L, h2hRes5_L, h2hRes6_L, h2hRes7_L, h2hRes8_L, h2hRes9_L,
					h2hRes10_L, oddFv_L, oddCnt_L);

			planBDataList.add(planBRow_Win);
			planBDataList.add(planBRow_Draw);
			planBDataList.add(planBRow_Lose);

		}

		Workbook PlanBWorkbook = writeToExcelObj.newWorkbook();

		writeToExcelObj.writePlanBToExcel(planBDataList, PlanBWorkbook, "PlanoB");
		writeToExcelObj.writeWorkbookToExcelFile(/* "Nosso" + */s.getName() + "_B" + s.getYear(), PlanBWorkbook);

	}

	// A população da base de dados varia conforme o campeonato pois o CSV não é
	// sempre igual, é necessário ver isto
	private static Season populateDatabase(EntityManager em, String competitionFile, String season, Integer year) {
		SeasonController seasonController = new SeasonController(em);
		TeamController teamController = new TeamController(em);
		FixtureController fixtureController = new FixtureController(em);
		StandingController standingController = new StandingController(em);
		OddController oddController = new OddController();

		List<String[]> list = CSVReader
				.readFromCSV("http://www.football-data.co.uk/mmz4281/" + season + "/" + competitionFile);

		Season s = null;
		if (competitionFile.contains("E0") || competitionFile.contains("E1") || competitionFile.contains("E2")
				|| competitionFile.contains("SC0")) {
			for (String[] arr : list) {
				s = seasonController.createSeason(arr[0], year);
				Team t1 = teamController.createTeam(arr[2]);
				Team t2 = teamController.createTeam(arr[3]);
				seasonController.addTeam(s, t1);
				seasonController.addTeam(s, t2);

				Result result = null;
				try {
					// result = new Result(Integer.valueOf(arr[4]),
					// Integer.valueOf(arr[5]), Integer.valueOf(arr[7]),
					// Integer.valueOf(arr[8]));

					result = new Result(Integer.valueOf(arr[4]), Integer.valueOf(arr[5]), null, null);

				} catch (NumberFormatException e) {
					e.printStackTrace(System.out);
					logger.debug(arr[0]);
					logger.debug(arr[1]);
					logger.debug(arr[2]);
					logger.debug(arr[3]);
					logger.debug(arr[4]);
					logger.debug(arr[5]);
					// logger.debug(arr[7]);
					// logger.debug(arr[8]);
					logger.debug(arr[23]);
					logger.debug(arr[24]);
					logger.debug(arr[25]);
					result = new Result(Integer.valueOf(arr[4]), Integer.valueOf(arr[5]), 0, 0);
				}

				Odd odd = null;

				try {
					odd = new Odd(Double.valueOf(arr[23]), Double.valueOf(arr[24]), Double.valueOf(arr[25]));
				} catch (NumberFormatException e) {
					e.printStackTrace(System.out);
					odd = new Odd(0.0, 0.0, 0.0);
				}

				Fixture fixture = fixtureController.createFixture(formatter.parseDateTime(arr[1]).toDate(), s, t1, t2,
						result, odd);
				if (fixture.getStatus().equals(FixtureStatus.FINISHED)) {
					standingController.createStanding(t1, s, fixture);
					standingController.createStanding(t2, s, fixture);
				}
			}
			return s;

		} else if (competitionFile.contains("D1") || competitionFile.contains("I1") || competitionFile.contains("SP1")
				|| competitionFile.contains("F1")) {
			for (String[] arr : list) {
				s = seasonController.createSeason(arr[0], year);
				Team t1 = teamController.createTeam(arr[2]);
				Team t2 = teamController.createTeam(arr[3]);
				seasonController.addTeam(s, t1);
				seasonController.addTeam(s, t2);

				Result result = null;
				try {
					// result = new Result(Integer.valueOf(arr[4]),
					// Integer.valueOf(arr[5]), Integer.valueOf(arr[7]),
					// Integer.valueOf(arr[8]));

					result = new Result(Integer.valueOf(arr[4]), Integer.valueOf(arr[5]), null, null);

				} catch (NumberFormatException e) {
					e.printStackTrace(System.out);
					logger.debug(arr[0]);
					logger.debug(arr[1]);
					logger.debug(arr[2]);
					logger.debug(arr[3]);
					logger.debug(arr[4]);
					logger.debug(arr[5]);
					// logger.debug(arr[7]);
					// logger.debug(arr[8]);
					logger.debug(arr[22]);
					logger.debug(arr[23]);
					logger.debug(arr[24]);
					result = new Result(Integer.valueOf(arr[4]), Integer.valueOf(arr[5]), 0, 0);
				}

				Odd odd = null;

				try {
					odd = new Odd(Double.valueOf(arr[22]), Double.valueOf(arr[23]), Double.valueOf(arr[24]));
				} catch (NumberFormatException e) {
					e.printStackTrace(System.out);
					odd = new Odd(0.0, 0.0, 0.0);
				}

				Fixture fixture = fixtureController.createFixture(formatter.parseDateTime(arr[1]).toDate(), s, t1, t2,
						result, odd);
				if (fixture.getStatus().equals(FixtureStatus.FINISHED)) {
					standingController.createStanding(t1, s, fixture);
					standingController.createStanding(t2, s, fixture);
				}
			}
			return s;

		} else if (competitionFile.contains("D2") || competitionFile.contains("I2") || competitionFile.contains("SP2")
				|| competitionFile.contains("F2") || competitionFile.contains("N1") || competitionFile.contains("B1")
				|| competitionFile.contains("P1") || competitionFile.contains("T1") || competitionFile.contains("G1")) {
			for (String[] arr : list) {
				s = seasonController.createSeason(arr[0], year);
				Team t1 = teamController.createTeam(arr[2]);
				Team t2 = teamController.createTeam(arr[3]);
				seasonController.addTeam(s, t1);
				seasonController.addTeam(s, t2);

				Result result = null;
				try {
					// result = new Result(Integer.valueOf(arr[4]),
					// Integer.valueOf(arr[5]), Integer.valueOf(arr[7]),
					// Integer.valueOf(arr[8]));

					result = new Result(Integer.valueOf(arr[4]), Integer.valueOf(arr[5]), null, null);

				} catch (NumberFormatException e) {
					e.printStackTrace(System.out);
					logger.debug(arr[0]);
					logger.debug(arr[1]);
					logger.debug(arr[2]);
					logger.debug(arr[3]);
					logger.debug(arr[4]);
					logger.debug(arr[5]);
					// logger.debug(arr[7]);
					// logger.debug(arr[8]);
					logger.debug(arr[10]);
					logger.debug(arr[11]);
					logger.debug(arr[12]);
					result = new Result(Integer.valueOf(arr[4]), Integer.valueOf(arr[5]), 0, 0);
				}

				Odd odd = null;

				try {
					odd = new Odd(Double.valueOf(arr[10]), Double.valueOf(arr[11]), Double.valueOf(arr[12]));
				} catch (NumberFormatException e) {
					e.printStackTrace(System.out);
					odd = new Odd(0.0, 0.0, 0.0);
				}

				Fixture fixture = fixtureController.createFixture(formatter.parseDateTime(arr[1]).toDate(), s, t1, t2,
						result, odd);
				if (fixture.getStatus().equals(FixtureStatus.FINISHED)) {
					standingController.createStanding(t1, s, fixture);
					standingController.createStanding(t2, s, fixture);
				}
			}
			return s;
		} else {
			logger.debug("NAO PREENCHEU BEM, MANDA TUDO A BAIXO!!!");
		}
		return s;
	}

}