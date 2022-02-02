/**
 *
 */
package com.objetdirect.gwt.umldrawer.client.test;

import com.objetdirect.gwt.umldrawer.client.helpers.SimilarityManager;
import com.objetdirect.gwt.umldrawer.server.dao.Dao;

/**
 * @author tanaka
 *
 */
public class SimularityManagerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {



	}

	boolean nameSimTest(){
		SimilarityManager sm = new SimilarityManager();
		String str1 = "あいうえお";
		String str2 = "あいうえお";
		String str3 = "かきくけこ";
		String str4 = "あいうけこ";
		String str5 = "あいうけこここ";

		System.out.println("nameSimTest");
		System.out.println("str1 and str2 = "+sm.nameSim(str1, str2));
		System.out.println("str1 and str3 = "+sm.nameSim(str1, str3));
		System.out.println("str1 and str4 = "+sm.nameSim(str1, str4));
		System.out.println("str1 and str5 = "+sm.nameSim(str1, str5));
		System.out.println("nameSimTest end");
		return true;
	}

	boolean makeRelationPairListTest(){
		Dao dao = new Dao();


		return true;
	}

}
