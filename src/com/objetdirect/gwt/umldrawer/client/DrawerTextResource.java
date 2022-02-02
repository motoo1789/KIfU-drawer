package com.objetdirect.gwt.umldrawer.client;

import com.objetdirect.gwt.umlapi.client.helpers.Session;

/**
 *
 */


/**
 * @author tanaka
 *
 */
public enum DrawerTextResource {
	LOG_VIEWER("Process Viewer", "プロセス閲覧"),
	ANALYSIS_VIEWER("Analisys Viewer","分析結果閲覧"),
	VIEWER_FOR_STUDENT("Process Viewer for Students","プロセス閲覧（学生用）"),
	ADD_USER("Add User","ユーザ登録"),
	CHANGE_PASSWORD("Change Password","パスワード変更"),
	DIFFICULTY("Difficulty","難しさ（４段階）"),
	FOWERD("Fowerd","進む"),
	BACK("Back","戻る"),
	CLASS("Class","クラス"),
	ATTRIBUTE("Attribute","属性"),
	METHOD("Method","メソッド"),
	RELATIONSHIP("Relationship","関連"),
	START_NEW_UML("Edit UML",""),
	DIAGRAM(" diagram...","図を編集"),
	CLASS_BUTTON("New Class","新しいクラス"),
	RELATION_BUTTON("New Association","関連を追加"),
	DELETE_BUTTON("Delete","削除"),
	SELECT_EXERCISE("Select Exercise","演習を選択"),
	ADD_REFLECTION("Add SC","見直しを追加"),
	REMOVE_REFLECTION("Remove SC","見直しを削除"),
	REFLECTION_COMMENT("SC/Comment","見直し/コメント"),
	ADD_CLASS("Add Class","クラスを追加"),
	SAVE("Save","保存"),
	SUBMIT("Submit","提出"),
	LEARNER_LIST("Learner List","学習者リスト"),
	NEXT_LEARNER("Go to next learner","次の学生へ"),
	SC_BUTTON("SC Button","見直し"),
	SC_REASON("Reasons","ここで見直しした理由"),
	SC_CHECK_ITEM("Checked Items","見直し項目"),
	SC_THINKINGS("Thinkings","考え方"),
	SC_ERROR_DETECTION("Error detection: ","問題の有無: "),
	SC_ERROR_CORRECTION_EVENT("Error-correction event","修正したイベント"),
	SC_TARGET_ELEMENTS("Target elements","見直し対象"),
	SC_EDITOR("SC Editor","見直し入力画面"),
	SAVE_AND_CLOSE("Save and close","保存して閉じる"),
	JUST_CLOSE("Just close","保存せず閉じる"),
	WHY_SC("Why did you SC at the timing?","ここで見直しをした理由（きっかけ）はなんですか？"),
	WHY_SC_OTHER("","その他の場合は「その他」をチェックし、理由を書いてください"),
	ERROR_DETECTION_MESSAGE("Did you detect errors?", "見直しの結果、問題は見つかりましたか？"),
	ERROR_CONTENT_MESSAGE("Describe the errors", "問題が見つかった場合は、その内容を書いてください。"),
	YES("Yes", "問題あり"),
	NO("No", "問題なし"),
	DESCRIBE_THINKINGS("Describe the thinkings when you did the SC.", "見直しした時の考え方を書いてください"),
	CHECK_ITEMS_MESSAGE("Which items did you checked in the SC?", "ここで見直しをした項目はなんですか？"),
	RECORD("Submit", "登録"),
	CLOSE("Close", "閉じる"),
	SET_TO_INVISIBLE("Set to invisible", "非表示にする"),
	SET_TO_VISIBLE("Set to visible", "表示する"),

	;

	private String english;
	private String japanese;
	private DrawerTextResource(String english, String japanese) {
		this.english = english;
		this.japanese = japanese;
	}

	public String getMessage() {
		if(Session.language.equals("jp")){
			return japanese;
		}
		else{
			return english;
		}
	}
}