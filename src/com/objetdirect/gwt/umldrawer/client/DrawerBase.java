package com.objetdirect.gwt.umldrawer.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.objetdirect.gwt.umlapi.client.artifacts.UMLArtifact;
import com.objetdirect.gwt.umlapi.client.helpers.Session;
import com.objetdirect.gwt.umlapi.client.helpers.UMLCanvas;
import com.objetdirect.gwt.umlapi.client.mylogger.MyLoggerExecute;
import com.objetdirect.gwt.umlapi.client.umlcomponents.UMLDiagram;
import com.objetdirect.gwt.umlapi.client.umlcomponents.UMLLink.LinkKind;
import com.objetdirect.gwt.umldrawer.client.beans.EditEvent;
import com.objetdirect.gwt.umldrawer.client.beans.Exercise;
import com.objetdirect.gwt.umldrawer.client.canvas.CanvasService;
import com.objetdirect.gwt.umldrawer.client.canvas.CanvasServiceAsync;
import com.objetdirect.gwt.umldrawer.client.drawerparts.CheckPanel;
import com.objetdirect.gwt.umldrawer.client.exercise.ExerciseService;
import com.objetdirect.gwt.umldrawer.client.exercise.ExerciseServiceAsync;
import com.objetdirect.gwt.umldrawer.client.helpers.DrawerSession;

public class DrawerBase extends DockPanel {
	private SimplePanel centerPanel;
	private VerticalPanel leftSideBar;
	private VerticalPanel rightSideBar;
	private HorizontalPanel northBar;
	private HorizontalPanel southBar;
	private UMLCanvas uMLCanvas;
	private SingleSelectionModel<String> selectionModel	= new SingleSelectionModel<String>();

	private EditEventPanel editEventPanel;
	private Exercise exercise;
	private DeckPanel mainPanel = new DeckPanel();
	private String DEFAULT_MODEL = "PDA+XUNsYXNzJCg1OCwxMTUpIeODpuODvOOCtiEhLeODpuODvOOCtklEJS3jg5"
			+ "Hjgrnjg6/jg7zjg4klLeawj+WQjSUt5L2P5omAJS3nmbvpjLLml6XmmYIlITs8MT5dQ2xhc3MkKDM2MCw3Mykh5"
			+ "ZWG5ZOBISEt5ZWG5ZOB5ZCNJS3ljZjkvqElLeiqrOaYjiUt55m76Yyy5pel5pmCJS3llYblk4Hnlarlj7clLeWcqOW6qy"
			+ "Ut5rOo5paH55Wq5Y+3JS3ms6jmlofml6XmmYIlLeWAi+aVsCUhOzwyPl1DbGFzcyQoMjA0LDM0OCkh44Os44OT44"
			+ "Ol44O844GZ44KLISEt44Os44OT44Ol44O8JS3ngrnmlbAlLeODrOODk+ODpeODvOipleS+oSUhOzwzPl1DbGFzc1Jlb"
			+ "GF0aW9uTGluayQ8Mj4hPDA+IVNpbXBsZVJlbGF0aW9uISFTb2xpZCFOb25lITAuLjEhISFOb25lITEhITs8ND5dQ2xhc3"
			+ "NSZWxhdGlvbkxpbmskPDI+ITwxPiFTaW1wbGVSZWxhdGlvbiEhU29saWQhTm9uZSEwLi4qISEhTm9uZSExISE7PDU+"
			+ "XUNsYXNzUmVsYXRpb25MaW5rJDwxPiE8MD4hU2ltcGxlUmVsYXRpb24hIVNvbGlkIU5vbmUhMC4uKiEhIU5vbmUhMSEhOw==";

	private String DEFAULT_MODEL2 = "PDA+XUNsYXNzJCgxNDUsMTM0KSHnpL7lk6EhIS3npL7lk6FJRCUt44OR44K544Ov44O844OJJS3jg"
			+ "6bjg7zjgrbjgr/jgqTjg5clITs8MT5dQ2xhc3MkKDQzMywxMDgpIeODl+ODreOCuOOCp+OCr+ODiOeZu+mMsiEhLeODl+ODreOCu"
			+ "OOCp+OCr+ODiOWQjSUt6ZaL5aeL5pel5pmCJS3jg57jg43jg7zjgrjjg6MlLeODl+ODreOCsOODqeODniUt44OH44K244Kk44OKJS3"
			+ "jg4bjgrnjgr/jg7wlITs8NT5dQ2xhc3NSZWxhdGlvbkxpbmskPDA+ITwxPiFTaW1wbGVSZWxhdGlvbiEhU29saWQhTm9uZSEwLi4qIS"
			+ "EhTm9uZSExISE7";



	public DrawerBase() {
		super();
		//		this.sinkEvents(Event.ONCONTEXTMENU);
		//	    this.addHandler(
		//	      new ContextMenuHandler() {
		//	        @Override
		//	        public void onContextMenu(ContextMenuEvent event) {
		//	          event.preventDefault();
		//	          event.stopPropagation();
		//	    }
		//	    }, ContextMenuEvent.getType());
		this.setHorizontalAlignment(ALIGN_LEFT);
		this.centerPanel = new SimplePanel();
		this.leftSideBar = new VerticalPanel();
		this.rightSideBar = new VerticalPanel();
		this.northBar = new HorizontalPanel();
		this.southBar = new HorizontalPanel();
		leftSideBar.setWidth("100px");


		//クラス図用ボタン群
		if(Session.diagramType == UMLDiagram.Type.CLASS){
			//*********************************************************************************************************
			Button addNewClass = new Button(DrawerTextResource.ADD_CLASS.getMessage());
			addNewClass.setSize("95px", "44px");
			addNewClass.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					uMLCanvas.addNewClass();
				}
			});
			leftSideBar.add(addNewClass);


		}


		Button addNewRelation = new Button(DrawerTextResource.RELATION_BUTTON.getMessage());
		addNewRelation.setSize("95px", "44px");
		addNewRelation.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				uMLCanvas.toLinkMode(LinkKind.getRelationKindFromName("SimpleRelation"));

			}
		});
		leftSideBar.add(addNewRelation);

		Button undo = new Button("Undo");
		undo.setSize("95px", "44px");
		undo.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String canvasUrl=null;
				int idCount=0;
				CanvasServiceAsync async = (CanvasServiceAsync)GWT.create(CanvasService.class);
				ServiceDefTarget entryPoint = (ServiceDefTarget) async;
				String entryURL = GWT.getModuleBaseURL() + "undo";
				entryPoint.setServiceEntryPoint(entryURL);

				@SuppressWarnings("rawtypes")
				AsyncCallback callback = new AsyncCallback(){
					@SuppressWarnings({ "unchecked", "deprecation" })
					public void onSuccess(Object result){

						if( !( (EditEvent) result == null) ){
							Session.setMode("load");
							Session.getActiveCanvas().clearCanvas();
							Session.getActiveCanvas().fromURL( ((EditEvent) result).getCanvasUrl() , false);
							uMLCanvas = Session.getActiveCanvas();
							Session.setMode("drawer");
							//							int preEventId, String editEvent, String eventType,
							//							String targetType, int targetId, String linkKind, int rightObjectId, int leftObjectId,
							//							String targetPart, String beforeEdit, String afterEdit, String canvasUrl
							MyLoggerExecute.registEditEvent(-1, ""+((EditEvent) result ).getEditEventId(), "Undo",
									null, -1, null, -1, -1,
									null, null, null, Session.getActiveCanvas().toUrl(), UMLArtifact.getIdCount());
						}
					}

					public void onFailure(Throwable caught){
						System.out.println(caught);
					}
				};

				async.undo(Session.studentId, Session.exerciseId, callback);
			}
		});
		leftSideBar.add(undo);

		Button checkedButton = new Button(DrawerTextResource.SC_BUTTON.getMessage());
		checkedButton.setSize("95px", "88px");
		checkedButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
					CheckPanel checkPanel = new CheckPanel();
					checkPanel.setPopupPosition(10, 180);
					checkPanel.show();
				//				int preEventId, String editEvent, String eventType,
				//				String targetType, int targetId, String linkKind, int rightObjectId, int leftObjectId,
				//				String targetPart, String beforeEdit, String afterEdit, String canvasUrl
				//					MyLoggerExecute.registEditEvent(-1, "Check", "Check",
				//							null, -1, null, -1, -1,
				//							null, null, null,Session.getActiveCanvas().toUrl(), UMLArtifact.getIdCount());
			}
		});
		leftSideBar.add(checkedButton);

		leftSideBar.setSpacing(10);
		//**************************************************************************************************
		//クラス図用ボタン終わり

		Button save = new Button(DrawerTextResource.SAVE.getMessage());
		save.setSize("95px", "88px");
		save.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				//				int preEventId, String editEvent, String eventType,
				//				String targetType, int targetId, String linkKind, int rightObjectId, int leftObjectId,
				//				String targetPart, String beforeEdit, String afterEdit, String canvasUrl
				MyLoggerExecute.registEditEvent(-1, "Save", "Save",
						null, -1, null, -1, -1,
						null, null, null,Session.getActiveCanvas().toUrl(), UMLArtifact.getIdCount());
				Window.alert("図を保存しました！");

			}
		});
		rightSideBar.add(save);

		Button submit = new Button(DrawerTextResource.SUBMIT.getMessage());
		submit.setSize("95px", "88px");
		submit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				//				int preEventId, String editEvent, String eventType,
				//				String targetType, int targetId, String linkKind, int rightObjectId, int leftObjectId,
				//				String targetPart, String beforeEdit, String afterEdit, String canvasUrl
				MyLoggerExecute.registEditEvent(-1, "Submit", "Submit",
						null, -1, null, -1, -1,
						null, null, null,Session.getActiveCanvas().toUrl(), UMLArtifact.getIdCount());
				Window.alert("提出を受け付けました！");
			}
		});
		rightSideBar.add(submit);
		leftSideBar.add(submit);


		leftSideBar.add(submit);

		//教授者用
		if(DrawerSession.student.getType()==1){
			Button saveAsAnswer = new Button("解答例として保存");
			saveAsAnswer.setSize("100px", "88px");
			saveAsAnswer.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Window.alert("Save as an answer");
					CanvasServiceAsync async = (CanvasServiceAsync)GWT.create(CanvasService.class);
					ServiceDefTarget entryPoint = (ServiceDefTarget) async;
					String entryURL = GWT.getModuleBaseURL() + "saveCanvasAsAnswer";
					entryPoint.setServiceEntryPoint(entryURL);

					@SuppressWarnings("rawtypes")
					AsyncCallback callback = new AsyncCallback(){
						@SuppressWarnings({ "unchecked", "deprecation" })
						public void onSuccess(Object result){
							if((Boolean)result)
								Window.alert("Saved as an answer");
						}

						public void onFailure(Throwable caught){
							System.out.println(caught);
						}
					};

					async.saveCanvasAsAnswer(Session.studentId, Session.exerciseId, uMLCanvas.toUrl() , callback);
				}

			});
			leftSideBar.add(saveAsAnswer);
		}
		rightSideBar.setSpacing(10);

		Button button1 = new Button("Drawer");
		button1.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mainPanel.showWidget(0);
				MyLoggerExecute.registEditEvent(-1, "LookDrawer", "LookDrawer",
						null, -1, null, -1, -1,
						null, null, null, null, UMLArtifact.getIdCount());
			}
		});

		Button button2 = new Button("Task");
		button2.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mainPanel.showWidget(1);
				MyLoggerExecute.registEditEvent(-1, "LookTask", "LookTask",
						null, -1, null, -1, -1,
						null, null, null, null, UMLArtifact.getIdCount());
			}
		});
		//northBar.add(button1);
		//northBar.add(button2);

		//Button setting END

		getExercise(Session.exerciseId);

		mainPanel.add(new DrawerPanel(240, 50));

		mainPanel.showWidget(0);

		this.add(mainPanel, DockPanel.CENTER);
		this.add(leftSideBar, DockPanel.WEST);
		//this.add(rightSideBar, DockPanel.EAST);
		this.add(northBar, DockPanel.NORTH);
		this.add(southBar, DockPanel.SOUTH);

		editEventPanel = new EditEventPanel(DrawerSession.student.getStudentId(),Session.exerciseId); //student_id, exercise_id


		CanvasInit();

	}

	private void getExercise(int exerciseId) {
		ExerciseServiceAsync async = (ExerciseServiceAsync)GWT.create(ExerciseService.class);
		ServiceDefTarget entryPoint = (ServiceDefTarget) async;
		String entryURL = GWT.getModuleBaseURL() + "getExercise";
		entryPoint.setServiceEntryPoint(entryURL);

		@SuppressWarnings("rawtypes")
		AsyncCallback callback = new AsyncCallback(){
			@SuppressWarnings({ "unchecked", "deprecation" })
			public void onSuccess(Object result){

				if( !( (Exercise) result == null) ){
					exercise = (Exercise) result;
					//mainPanel.add(new ExerciseViewPanel(exercise));
				}
				else{
					Window.alert("No exercise was selected (+_+)");
				}
			}

			public void onFailure(Throwable caught){
				Window.alert("Error (+_+)");
				System.out.println(caught);
			}
		};

		async.getExercise(exerciseId, callback);
	}

	private void CanvasInit(){
		//Canvas init
		CanvasServiceAsync async = (CanvasServiceAsync)GWT.create(CanvasService.class);
		ServiceDefTarget entryPoint = (ServiceDefTarget) async;
		String entryURL = GWT.getModuleBaseURL() + "loadCanvas";
		entryPoint.setServiceEntryPoint(entryURL);

		@SuppressWarnings("rawtypes")
		AsyncCallback callback = new AsyncCallback(){
			@SuppressWarnings({ "unchecked", "deprecation" })
			public void onSuccess(Object result){

				if( !( (EditEvent) result == null) ){
					Session.setMode("load");
					Session.getActiveCanvas().fromURL( ((EditEvent) result).getCanvasUrl() , false);
					uMLCanvas = Session.getActiveCanvas();
					UMLArtifact.setIdCount( ((EditEvent) result).getUmlArtifactId() );
					Session.setMode("drawer");
				}
				else{
					if(Session.exerciseId == 16){
						UMLArtifact.setIdCount(6);
						MyLoggerExecute.registEditEvent(-1, "Start", "Start",
								null, -1, null, -1, -1,
								null, null, null, DEFAULT_MODEL, 6);

						Session.setMode("load");
						Session.getActiveCanvas().fromURL( DEFAULT_MODEL , false);
						uMLCanvas = Session.getActiveCanvas();
						Session.setMode("drawer");
					}
					else if(Session.exerciseId == 11){
						UMLArtifact.setIdCount(6);
						MyLoggerExecute.registEditEvent(-1, "Start", "Start",
								null, -1, null, -1, -1,
								null, null, null, DEFAULT_MODEL2, 6);

						Session.setMode("load");
						Session.getActiveCanvas().fromURL( DEFAULT_MODEL2 , false);
						uMLCanvas = Session.getActiveCanvas();
						Session.setMode("drawer");
					}
					else{
						UMLArtifact.setIdCount(0);
						MyLoggerExecute.registEditEvent(-1, "Start", "Start",
								null, -1, null, -1, -1,
								null, null, null, "AA==", 0);
					}
					//TODO

				}
			}

			public void onFailure(Throwable caught){
				System.out.println(caught);
			}
		};

		async.loadCanvas(Session.studentId, Session.exerciseId, callback);
	}
	/**
	 * @return centerPanel
	 */
	public SimplePanel getCenterPanel() {
		return centerPanel;
	}
	/**
	 * @param centerPanel セットする centerPanel
	 */
	public void setCenterPanel(SimplePanel centerPanel) {
		this.centerPanel = centerPanel;
	}
	/**
	 * @return leftSideBar
	 */
	public VerticalPanel getLeftSideBar() {
		return leftSideBar;
	}
	/**
	 * @param leftSideBar セットする leftSideBar
	 */
	public void setLeftSideBar(VerticalPanel leftSideBar) {
		this.leftSideBar = leftSideBar;
	}
	/**
	 * @return rightSideBar
	 */
	public VerticalPanel getRightSideBar() {
		return rightSideBar;
	}
	/**
	 * @param rightSideBar セットする rightSideBar
	 */
	public void setRightSideBar(VerticalPanel rightSideBar) {
		this.rightSideBar = rightSideBar;
	}
	/**
	 * @return northBar
	 */
	public HorizontalPanel getNorthBar() {
		return northBar;
	}
	/**
	 * @param northBar セットする northBar
	 */
	public void setNorthBar(HorizontalPanel northBar) {
		this.northBar = northBar;
	}
	/**
	 * @return southBar
	 */
	public HorizontalPanel getSouthBar() {
		return southBar;
	}
	/**
	 * @param southBar セットする southBar
	 */
	public void setSouthBar(HorizontalPanel southBar) {
		this.southBar = southBar;
	}



}
