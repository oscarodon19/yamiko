package ar.edu.ungs.garules;

import java.util.HashMap;
import java.util.Map;

/**
 * Constantes y parámetro de la ejecución del proceso de Censo.
 * @author ricardo
 *
 */
public class Constants {

	/**
	 * Valores máximos posibles para cada campo. Se utiliza para calcular el módulo del valor que viene en los campos "Valor" de cada fórmula en los individuos.
	 */
	public static final int[] CENSUS_FIELDS_MAX_VALUE=new int[]{
		2,3,91,25,2,2,2,2,2,2,6,4,8,17,3,3,555,6,2001,4,555,3,3,3,3,3,3,3,3,6,5,2,2,2,2,2,2,2,2,2,3,
		16,7,9,556,13,7,169,121,4,4,4,4,5,3,998,998,10,3,53,100,176,127,51,19,14,13,53,38,5001,311,502
	};

	/**
	 * Enum con los atributos relevantes del arhivo
	 * @author ricardo
	 *
	 */
	public static enum CENSUS_FIELDS{
		OC,SEX,AGE,HISPAN ,WHITE ,
		BLACK ,AIAN ,ASIAN ,
		NHPI ,OTHER , MARSTAT ,
		ENROLL ,
		GRADE ,EDUC ,
		SPEAK ,ENGABIL ,POB5 ,
		CITIZEN ,YR2US ,MOB ,
		MIGST5 ,SENSORY ,PHYSCL ,
		MENTAL ,SLFCARE ,ABGO ,
		ABWORK ,GRANDC ,
		RSPNSBL ,HOWLONG ,
		MILTARY ,VPS1 ,
		VPS2 ,
		VPS3 ,
		VPS4 ,
		VPS5 ,
		VPS6 ,
		VPS7 ,
		VPS8 ,
		VPS9,
		MILYRS ,VPSR ,
		ESR,ESP,
		POWST5 ,TRVMNS ,
		CARPOOL,LVTIME ,TRVTIME ,
		LAYOFF ,ABSENT ,RECALL ,
		LOOKWRK ,BACKWRK ,LASTWRK ,INDCEN ,OCCCEN5 ,
		CLWKR  ,WRKLYR ,WEEKS ,HOURS ,
		INCWS ,INCSE ,INCINT ,
		INCSS ,INCSSI ,
		INCPA ,INCRET ,
		INCOTH ,INCTOT ,
		EARNS ,POVERTY
	}
	
	/**
	 * Descripciones de los campos
	 */
	public static final String[] CENSUS_FIELDS_DESCRIPTIONS=new String[]{
		"Own Child Indicator","Sex","Age","Hispanic or Latino Origin","White recode",
		"Black or African American","American Indian and Alaska Native","Asian",
		"Native Hawaiian and Other Pacific Islander","Some other race","Marital Status",
		"School Enrollment; Attended since February 1, 2000",
		"School Enrollment: Grade Level Attending","Educational Attainment",
		"Non-English Language","English Ability","Place of Birth",
		"Citizenship Status", "Year of Entry to United States", "Residence 5 Years Ago",
		"Migration State or Foreign Country Code", "Sensory Disability","Physical Disability",
		"Mental Disability", "Self-Care Disability", "Able to Go Out Disability",
		"Employment Disability", "Presence of Grandchildren under 18 years",
		"Responsible for Grandchildren","Length of Responsibility for Grandchildren",
		"Military Service", "Veteran’s Period of Service 1: On active duty April 1995 or later",
		"Veteran’s Period of Service 2: On active duty August 1990 to March 1995 (including Persian Gulf War)",
		"Veteran’s Period of Service 3: On active duty September 1980 to July 1990",
		"Veteran’s Period of Service 4: On active duty May 1975 to August 1980",
		"Veteran’s Period of Service 5: On active duty during the Vietnam Era (August 1964 to April 1975)",
		"Veteran’s Period of Service 6: On active duty February 1955 to July 1964",
		"Veteran’s Period of Service 7: On active duty during the Korean War (June 1950 to January 1955)",
		"Veteran’s Period of Service 8: On active duty during World War II (September 1940 to July 1947)",
		"Veteran’s Period of Service 9: On active duty any other time"	,
		"Years of Military Service", "Veteran’s Period of Service",
		"Employment Status Recode", "Employment Status of Parent(s)",
		"Place of Work State or Foreign Country Code","Means of Transportation to Work",
		"Vehicle Occupancy","Time Leaving for Work","Travel Time to Work",
		"Layoff from Job","Absent from Work","Return-to-Work Recall",
		"Looking for Work","Back to Work","Year Last Worked","Industry","Occupation",
		"Class of Worker", "Worked in 1999","Weeks Worked in 1999","Hours Per Week in 1999",
		"Wage/Salary Income in 1999","Self-Employment Income in 1999","Interest Income in 1999",
		"Social Security Income in 1999","Supplemental Security Income in 1999",
		"Public Assistance Income in 1999","Retirement Income in 1999",
		"Other Income in 1999","Person’s Total Income in 1999",
		"Person’s Total Earnings in 1999","Person’s Poverty Status"
	};

	/**
	 * Starting position de cada campo
	 */
	public static final int[] CENSUS_FIELDS_POS_FROM=new int[]{
		20,23,25,28,32,33,34,35,36,37,44,49,51,53,64,70,72,76,78,83,85,119,121,123,125,127,129,132,134,136,138,140,141,142,143,144,145,146,147,148,150,
		152,154,156,157,191,194,196,200,204,205,206,207,208,209,211,223,234,236,238,241,244,251,258,265,271,277,283,290,297,305,312
	};
	
	/**
	 * Longitud de cada campo
	 */
	public static final int[] CENSUS_FIELDS_LENGTH=new int[]{
		1,1,2,2,1,1,1,1,1,1,1,1,1,2,1,1,2,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
		2,1,1,2,2,1,2,3,1,1,1,1,1,1,3,3,1,1,2,2,6,6,6,5,5,5,6,6,7,7,3
	};
	
	/**
	 * Diccionario de datos
	 */
	public static final Map<Integer,String[]> CENSUS_FIELDS_VALUES=new HashMap<Integer, String[]>()
			{
				private static final long serialVersionUID = 1L;
			{
			     put(CENSUS_FIELDS.OC.ordinal(), new String[]{"No","Yes"});
			     put(CENSUS_FIELDS.SEX.ordinal(), new String[]{null,"Male","Female"});
			     put(CENSUS_FIELDS.HISPAN.ordinal(), new String[]{null,"Not Hispanic or Latino",
			    	 "Mexican",
			    	 "Puerto Rican",
			    	 "Cuban",
			    	 "Dominican",
			    	 "Costa Rican",
			    	 "Guatemalan",
			    	 "Honduran",
			    	 "Nicaraguan",
			    	 "Panamanian",
			    	 "Salvadoran",
			    	 "Other Central American",
			    	 "Argentinean",
			    	 "Bolivian",
			    	 "Chilean",
			    	 "Colombian",
			    	 "Ecuadorian",
			    	 null,
			    	 "Peruvian",
			    	 "Uruguayan",
			    	 "Venezuelan",
			    	 "Other South American",
			    	 "Spaniard",
			    	 "Other Spanish or Latino"});
			     put(CENSUS_FIELDS.WHITE.ordinal(), new String[]{"No","Yes"});
			     put(CENSUS_FIELDS.BLACK.ordinal(), new String[]{"No","Yes"});
			     put(CENSUS_FIELDS.AIAN.ordinal(), new String[]{"No","Yes"});
			     put(CENSUS_FIELDS.ASIAN.ordinal(), new String[]{"No","Yes"});
			     put(CENSUS_FIELDS.NHPI.ordinal(), new String[]{"No","Yes"});
			     put(CENSUS_FIELDS.OTHER.ordinal(), new String[]{"No","Yes"});
			     put(CENSUS_FIELDS.MARSTAT.ordinal(), new String[]{null,"Now married","Widowed","Divorced","Separated","Never married (includes under 15 years)"});
			     put(CENSUS_FIELDS.ENROLL.ordinal(), new String[]{"Not in universe (Under 3 years)",
				     "No, has not attended since February 1",
				     "Yes, public school or college",
				     "Yes, private school or college"});
			     put(CENSUS_FIELDS.GRADE.ordinal(), new String[]{"Not in universe (Under 3 years)",
				     "Nursery school, preschool",
				     "Kindergarten",
				     "Grade 1 to grade 4",
				     "Grade 5 to grade 8",
				     "Grade 9 to grade 12",
				     "College undergraduate",
				     "Graduate or professional school"});
			     put(CENSUS_FIELDS.EDUC.ordinal(), new String[]{"Not in universe (Under 3 years)",
				     "No schooling completed",
						"Nursery school to 4th grade",
						"5th grade or 6th grade",
						"7th grade or 8th grade",
						"9th grade",
						"10th grade",
						"11th grade",
						"12th grade, no diploma",
						"High school graduate",
						"Some college, but less than 1 year",
						"One or more years of college, no degree",
						"Associate degree",
						"Bachelor’s degree",
						"Master’s degree",
						"Professional degree",
						"Doctorate degree"});
			     put(CENSUS_FIELDS.SPEAK.ordinal(), new String[]{"Not in universe (Under 5 years)","Yes","No" });
			     put(CENSUS_FIELDS.CITIZEN.ordinal(), new String[]{null,			     
				     "Yes, born in the United States",
				     "Yes, born in Puerto Rico, Guam, U.S. Virgin Islands, American Samoa, or Northern Marianas",
				     "Yes, born abroad of American parent or parents",
				     "Yes, U.S. citizen by naturalization",
				     "No, not a citizen of the United States"});
			     put(CENSUS_FIELDS.MOB.ordinal(), new String[]{"Not in universe (Under 5 years)",
				     "Yes, same house",
				     "No, outside the U.S. (outside the U.S. or Puerto Rico if Puerto Rico is state of residence)",
				     "No, different house in the U.S. (different house in Puerto Rico or the U.S. if Puerto Rico is the state of residence)"});
			     put(CENSUS_FIELDS.SENSORY.ordinal(), new String[]{"Not in universe (Under 5 years)","Yes","No" });
			     put(CENSUS_FIELDS.PHYSCL.ordinal(), new String[]{"Not in universe (Under 5 years)","Yes","No" });
			     put(CENSUS_FIELDS.MENTAL.ordinal(), new String[]{"Not in universe (Under 5 years)","Yes","No" });
			     put(CENSUS_FIELDS.SLFCARE.ordinal(), new String[]{"Not in universe (Under 5 years)","Yes","No" });
			     put(CENSUS_FIELDS.ABGO.ordinal(), new String[]{"Not in universe (Under 16 years)","Yes","No" });
			     put(CENSUS_FIELDS.ABWORK.ordinal(), new String[]{"Not in universe (Under 16 years)","Yes","No" });
			     put(CENSUS_FIELDS.GRANDC.ordinal(), new String[]{"Not in universe (Under 15 years)","Yes","No" });
			     put(CENSUS_FIELDS.RSPNSBL.ordinal(), new String[]{"Not in universe (Under 15 years)","Yes","No" });
			     put(CENSUS_FIELDS.HOWLONG.ordinal(), new String[]{"Not in universe (Under 15 years)",
				     "Less than 6 months",
				     "6 to 11 months",
				     "1 or 2 years",
				     "3 or 4 years",
				     "5 years or more"});
			     put(CENSUS_FIELDS.MILTARY.ordinal(), new String[]{"Not in universe (Under 17 years)",
				     "Yes, now on active duty",
				     "Yes, on active duty in the past, but not now",
				     "No, training for reserves or National Guard only",
				     "No active duty service"});
			     put(CENSUS_FIELDS.VPS1.ordinal(), new String[]{"Did not serve in this period or under 17 years", "Served in this period"});
			     put(CENSUS_FIELDS.VPS2.ordinal(), new String[]{"Did not serve in this period or under 17 years", "Served in this period"});
			     put(CENSUS_FIELDS.VPS3.ordinal(), new String[]{"Did not serve in this period or under 17 years", "Served in this period"});
			     put(CENSUS_FIELDS.VPS4.ordinal(), new String[]{"Did not serve in this period or under 17 years", "Served in this period"});
			     put(CENSUS_FIELDS.VPS5.ordinal(), new String[]{"Did not serve in this period or under 17 years", "Served in this period"});
			     put(CENSUS_FIELDS.VPS6.ordinal(), new String[]{"Did not serve in this period or under 17 years", "Served in this period"});
			     put(CENSUS_FIELDS.VPS7.ordinal(), new String[]{"Did not serve in this period or under 17 years", "Served in this period"});
			     put(CENSUS_FIELDS.VPS8.ordinal(), new String[]{"Did not serve in this period or under 17 years", "Served in this period"});
			     put(CENSUS_FIELDS.VPS9.ordinal(), new String[]{"Did not serve in this period or under 17 years", "Served in this period"});			     
			     put(CENSUS_FIELDS.MILYRS.ordinal(), new String[]{"Not in universe (Under 17 years)", "Less than 2 years","2 years or more"});
			     put(CENSUS_FIELDS.VPSR.ordinal(), new String[]{"Not in universe (Under 18 years or no active duty military service)",
			    		 "August 1990 or later (including Persian Gulf War); Served in Vietnam era",
			    		 "August 1990 or later (including Persian Gulf War); No Vietnam era service; September 1980 or later only; Served under 2 years",
			    		 "August 1990 or later (including Persian Gulf War); No Vietnam era service; September 1980 or later only; Served 2 years or more",
			    		 "August 1990 or later (including Persian Gulf War); No Vietnam era service; September 1980 or later only; Served prior to September 1980",
			    		 "May 1975 to July 1990 only: September 1980 to July 1990 only: Served under 2 years",
			    		 "May 1975 to July 1990 only: September 1980 to July 1990 only: Served 2 years or more",
						"May 1975 to July 1980 only: September 1980 to July 1990 only; Other May 1975 to August 1980 service",
						"Vietnam era, no Korean War, no WWII, no August 1990 or later",
						"Vietnam era, Korean War, no WWII",
						"Vietnam era, Korean War, and WWII",
						"February 1955 to July 1964 only",
						"Korean War, no Vietnam era, no WWII",
						"Korean War and WWII, no Vietnam era",
						"WWII, no Korean War, no Vietnam era",
						"Other service only"});
			     put(CENSUS_FIELDS.ESR.ordinal(), new String[]{"Not in universe (Under 16 years)",
					     "Employed, at work",
					     "Employed, with a job but not at work",
					     "Unemployed",
					     "Armed Forces, at work",
					     "Armed Forces, with a job but not at work",
					     "Not in labor force"});
			     put(CENSUS_FIELDS.ESP.ordinal(), new String[]{"Not in universe (not own child in family or child in subfamily)",
			    		 "Living with 2 parents, both parents in labor force",
			    		 "Living with 2 parents, father only in labor force",
			    		 "Living with 2 parents, mother only in labor force",
			    		 "Living with 2 parents, neither parent in labor force",
			    		 "Living with one parent: living with father; father in labor force",
			    		 "Living with one parent; living with father; father not in labor force",
			    		 "Living with one parent: living with mother; mother in labor force",
			    		 "Living with one parent; living"});
			     put(CENSUS_FIELDS.TRVMNS.ordinal(), new String[]{"Not in universe (Under 16 years)",
			    		 "Car, truck, or van",
			    		 "Bus or trolley bus",
			    		 "Streetcar or trolley car (público in Puerto Rico)",
			    		 "Subway or elevated",
			    		 "Railroad",
			    		 "Ferryboat",
			    		 "Taxicab",
			    		 "Motorcycle",
			    		 "Bicycle",
			    		 "Walked", "Worked at home","Other"});
			     put(CENSUS_FIELDS.CARPOOL.ordinal(), new String[]{"Not in universe (Under 16 years)",
			     		 "Drove alone",
			    		 "2 people",
			    		 "3 people",
			    		 "4 people",
			    		 "5 or 6 people",
			    		 "7 or more people"});
			     put(CENSUS_FIELDS.LVTIME.ordinal(), new String[]{"Not in universe (Under 16 years)",
			    		 "12:00 am to 12:29 am",
			    		 "12:30 am to 12:59 am",
			    		 "1:00 am to 1:29 am",
			    		 "1:30 am to 1:59 am",
			    		 "2:00 am to 2:29 am",
			    		 "2:30 am to 2:59 am",
			    		 "3:00 am to 3:09 am",
			    		 "3:10 am to 3:19 am",
			    		 "3:20 am to 3:29 am",
			    		 "3:30 am to 3:39 am",
			    		 "3:40 am to 3:49 am",
			    		 "3:50 am to 3:59 am",
			    		 "4:00 am to 4:09 am",
			    		 "4:10 am to 4:19 am",
			    		 "4:20 am to 4:29 am",
			    		 "4:30 am to 4:39 am",
			    		 "4:40 am to 4:49 am",
			    		 "4:50 am to 4:59 am",
			    		 "5:00 am to 5:04 am",
			    		 "5:05 am to 5:09 am",
			    		 "5:10 am to 5:14 am",
			    		 "5:15 am to 5:19 am",
			    		 "5:20 am to 5:24 am",
			    		 "5:25 am to 5:29 am",
			    		 "5:30 am to 5:34 am",
			     "5:35 am to 5:39 am",
			     "5:40 am to 5:44 am",
			     "5:45 am to 5:49 am",
			     "5:50 am to 5:54 am",
			     "5:55 am to 5:59 am",
			     "6:00 am to 6:04 am",
			     "6:05 am to 6:09 am",
			     "6:10 am to 6:14 am",
			     "6:15 am to 6:19 am",
			     "6:20 am to 6:24 am",
			     "6:25 am to 6:29 am",
			     "6:30 am to 6:34 am",
			     "6:35 am to 6:39 am",
			     "6:40 am to 6:44 am",
			     "6:45 am to 6:49 am",
			     "6:50 am to 6:54 am",
			     "6:55 am to 6:59 am",
			     "7:00 am to 7:04 am",
			     "7:05 am to 7:09 am",
			     "7:10 am to 7:14 am",
			     "7:15 am to 7:19 am",
			     "7:20 am to 7:24 am",
			     "7:25 am to 7:29 am",
			     "7:30 am to 7:34 am",
			     "7:35 am to 7:39 am",
			     "7:40 am to 7:44 am",
			     "7:45 am to 7:49 am",
			     "7:50 am to 7:54 am",
			     "7:55 am to 7:59 am",
			     "8:00 am to 8:04 am",
			     "8:05 am to 8:09 am",
			     "8:10 am to 8:14 am",
			     "8:15 am to 8:19 am",
			     "8:20 am to 8:24 am",
			     "8:25 am to 8:29 am",
			     "8:30 am to 8:34 am",
			     "8:35 am to 8:39 am",
			     "8:40 am to 8:44 am",
			     "8:45 am to 8:49 am",
			     "8:50 am to 8:54 am",
			     "8:55 am to 8:59 am",
			     "9:00 am to 9:04 am",
			     "9:05 am to 9:09 am",
			     "9:10 am to 9:14 am",
			     "9:15 am to 9:19 am",
			     "9:20 am to 9:24 am",
			     "9:25 am to 9:29 am",
			     "9:30 am to 9:34 am",
			     "9:35 am to 9:39 am",
			     "9:40 am to 9:44 am",
			     "9:45 am to 9:49 am",
			     "9:50 am to 9:54 am",
			     "9:55 am to 9:59 am",
			     "10:00 am to 10:09 am",
			     null,
			     "10:10 am to 10:19 am",
			     null,
			     "10:20 am to 10:29 am",
			     null,
			     "10:30 am to 10:39 am",
			     null,
			     "10:40 am to 10:49 am",
			     null,
			     "10:50 am to 10:59 am",
			     null,
			     "11:00 am to 11:09 am",
			     "11:10 am to 11:19 am",
			     "11:20 am to 11:29 am",
			     "11:30 am to 11:39 am",
			     "11:40 am to 11:49 am",
			     "11:50 am to 11:59 am",
			     "12:00 pm to 12:09 pm",
			     "12:10 pm to 12:19 pm",
			     "12:20 pm to 12:29 pm",
			     "12:30 pm to 12:39 pm",
			     "12:40 pm to 12:49 pm",
			     "12:50 pm to 12:59 pm",
			     "1:00 pm to 1:09 pm",
			     "1:10 pm to 1:19 pm",
			     "1:20 pm to 1:29 pm",
			     "1:30 pm to 1:39 pm",
			     "1:40 pm to 1:49 pm",
			     "1:50 pm to 1:59 pm",
			     "2:00 pm to 2:09 pm",
			     "2:10 pm to 2:19 pm",
			     "2:20 pm to 2:29 pm",
			     "2:30 pm to 2:39 pm",
			     "2:40 pm to 2:49 pm",
			     "2:50 pm to 2:59 pm",
			     "3:00 pm to 3:09 pm",
			     "3:10 pm to 3:19 pm",
			     "3:20:pm to 3:29 pm",
			     "3:30 pm to 3:39 pm",
			     "3:40 pm to 3:49 pm",
			     "3:50 pm to 3:59 pm",
			     "4:00 pm to 4:09 pm",
			     "4:10 pm to 4:19 pm",
			     "4:20 pm to 4:29 pm",
			     "4:30 pm to 4:39 pm",
			     "4:40 pm to 4:49 pm",
			     "4:50 pm to 4:59 pm",
			     "5:00 pm to 5:09 pm",
			     "5:10 pm to 5:19 pm",
			     "5:20 pm to 5:29 pm",
			     "5:30 pm to 5:39 pm",
			     "5:40 pm to 5:49 pm",
			     "5:50 pm to 5:59 pm",
			     "6:00 pm to 6:09 pm",
			     "6:10 pm to 6:19 pm",
			     "6:20 pm to 6:29 pm",
			     "6:30 pm to 6:39 pm",
			     "6:40 pm to 6:49 pm",
			     "6:50 pm to 6:59 pm",
			     "7:00 pm to 7:09 pm",
			     "7:10 pm to 7:29 pm",
			     null,
			     "7:30 pm to 7:39 pm",
			     "7:40 pm to 7:49 pm",
			     "7:50 pm to 7:59 pm",
			     "8:00 pm to 8:09 pm",
			     "8:10 pm to 8:29 pm",
			     null,
			     "8:30 pm to 8:39 pm",
			     "8:40 pm to 8:59 pm",
			     null,
			     "9:00 pm to 9:09 pm",
			     "9:10 pm to 9:19 pm",
			     "9:20 pm to 9:29 pm",
			     "9:30 pm to 9:39 pm",
			     "9:40 pm to 9:49 pm",
			     "9:50 pm to 9:59 pm",
			     "10:00 pm to 10:09 pm",
			     "10:10 pm to 10:19 pm",
			     "10:20 pm to 10:29 pm",
			     "10:30 pm to 10:39 pm",
			     "10:40 pm to 10:49 pm",
			     "10:50 pm to 10:59 pm",
			     "11:00 pm to 11:09 pm",
			     "11:10 pm to 11:19 pm",
			     "11:20 pm to 11:29 pm",
			     "11:30 pm to 11:39 pm",
			     "11:40 pm to 11:59 pm",
			     "Not used"});
			     put(CENSUS_FIELDS.LAYOFF.ordinal(), new String[]{"Not in universe (Under 16 years)",
			    		 "Yes, on layoff",
			    		 "No",
			    		 "Not reported"});
			     put(CENSUS_FIELDS.ABSENT.ordinal(), new String[]{"Not in universe (Under 16 years)",
					     "Yes, temporarily absent from work",
					     "No",
					     "Not reported"});			     
			     put(CENSUS_FIELDS.RECALL.ordinal(), new String[]{"Not in universe (Under 16 years)",
				     "Yes",
				     "No",
				     "Not reported"});
			     put(CENSUS_FIELDS.LOOKWRK.ordinal(), new String[]{"Not in universe (Under 16 years)",
				     "Yes",
				     "No",
				     "Not reported"});			     
			     put(CENSUS_FIELDS.BACKWRK .ordinal(), new String[]{"Not in universe (Under 16 years)",
				     "Yes",
				     "No, because of temporary illness",
				     "No, because of other reasons (in school, etc.)",
				     "Not reported"});			     
			     put(CENSUS_FIELDS.LASTWRK .ordinal(), new String[]{"Not in universe (Under 16 years)",
				     "1995 to 2000",
			     	 "1994 or earlier, or never worked"});
			     put(CENSUS_FIELDS.CLWKR .ordinal(), new String[]{"Not in universe (Under 16 years)",
				     "Employee of private for-profit company",
					"Employee of private not-for- profit company",
					"Employee of local government",
					"Employee of state government",
					"Employee of federal government",
					"Self-employed in unincorporated business or company",
					"Self-employed in incorporated business or company",
					"Unpaid family worker",
					"Unemployed, no work experience since 1995"});
			     put(CENSUS_FIELDS.WRKLYR.ordinal(), new String[]{"Not in universe (Under 16 years)","Yes","No"});			     		     
			}};	
}
