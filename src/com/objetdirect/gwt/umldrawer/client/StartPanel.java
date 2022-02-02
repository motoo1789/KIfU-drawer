/*
 * This file is part of the GWTUML project and was written by Mounier Florian <mounier-dot-florian.at.gmail'dot'com> for Objet Direct
 * <http://wwww.objetdirect.com>
 *
 * Copyright © 2009 Objet Direct Contact: gwtuml@googlegroups.com
 *
 * GWTUML is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * GWTUML is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with GWTUML. If not, see <http://www.gnu.org/licenses/>.
 */
package com.objetdirect.gwt.umldrawer.client;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.objetdirect.gwt.umlapi.client.helpers.OptionsManager;
import com.objetdirect.gwt.umlapi.client.helpers.QualityLevel;
import com.objetdirect.gwt.umlapi.client.helpers.ThemeManager;
import com.objetdirect.gwt.umlapi.client.helpers.ThemeManager.Theme;
import com.objetdirect.gwt.umlapi.client.umlcomponents.UMLDiagram;
import com.objetdirect.gwt.umlapi.client.umlcomponents.UMLDiagram.Type;

/**
 * This class is the index panel displaying drawer options and logo
 *
 * @author Florian Mounier (mounier-dot-florian.at.gmail'dot'com)
 */
public class StartPanel extends VerticalPanel {

	final Label					crossLbl				= new Label("x");
	final Label					geometryStyleLbl		= new Label("Geometry Style : ");
	final ListBox				geometryStyleListBox	= new ListBox();
	final HorizontalPanel		geometryStylePanel		= new HorizontalPanel();
	final Label					gfxEngineLbl			= new Label("Graphics Engine : ");
	final ListBox				gfxEngineListBox		= new ListBox();
	final HorizontalPanel		gfxEnginePanel			= new HorizontalPanel();
	final TextBox				heightTxtBox			= new TextBox();
	final CheckBox				isResolutionAutoChkBox	= new CheckBox(" Auto Resolution");
	final Image					logoImg					= new Image("gwtumllogo_rev-ed.png");
	final Label					qualityLbl				= new Label("Quality : ");
	final ListBox				qualityListBox			= new ListBox();
	final HorizontalPanel		qualityPanel			= new HorizontalPanel();
	final HorizontalPanel		resolutionAutoPanel		= new HorizontalPanel();
	final Label					resolutionLbl			= new Label("Resolution : ");
	final HorizontalPanel		resolutionPanel			= new HorizontalPanel();

	final Button				startDemoBtn			= new Button("... Or start the Demoooooooo ...");
	final Button				startAnimateDemoBtn		= new Button("... Or start the Animated Demo !");
	final Button				logViewer				= new Button("... Or start the Viewer !");
	final Label					themeLbl				= new Label("Theme : ");
	final ListBox				themeListBox			= new ListBox();
	final HorizontalPanel		themePanel				= new HorizontalPanel();
	final TextBox				widthTxtBox				= new TextBox();
	private final LoadingScreen	loadingScreen;

	/**
	 * Constructor of the {@link StartPanel}
	 *
	 * @param isFromHistory
	 */
	public StartPanel(final boolean isFromHistory) {
		super();
		this.loadingScreen = new LoadingScreen();
		this.loadingScreen.show();
		Log.trace("Starting App");
		System.out.println("Starting@StartPanel");

		this.setWidth("100%");
		this.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.setSpacing(20);

		this.gfxEnginePanel.setSpacing(5);
		this.geometryStylePanel.setSpacing(5);
		this.themePanel.setSpacing(5);
		this.resolutionPanel.setSpacing(5);
		this.gfxEngineListBox.addItem("Tatami GFX");
		this.gfxEngineListBox.addItem("Incubator Canvas GFX");
		this.gfxEngineListBox.addItem("GWT Canvas GFX");
		this.geometryStyleListBox.addItem("Linear");
		this.geometryStyleListBox.addItem("Shape Based");

		for (final Theme theme : Theme.values()) {
			this.themeListBox.addItem(ThemeManager.getThemeName(theme));
		}
		this.isResolutionAutoChkBox.setValue(true);
		this.widthTxtBox.setEnabled(false);
		this.heightTxtBox.setEnabled(false);
		this.isResolutionAutoChkBox.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				StartPanel.this.widthTxtBox.setEnabled(!StartPanel.this.isResolutionAutoChkBox.getValue());
				StartPanel.this.heightTxtBox.setEnabled(!StartPanel.this.isResolutionAutoChkBox.getValue());

			}
		});

		this.widthTxtBox.setText("" + (Window.getClientWidth() - 50));
		this.heightTxtBox.setText("" + (Window.getClientHeight() - 50));

		this.widthTxtBox.setWidth("50px");
		this.heightTxtBox.setWidth("50px");
		for (final QualityLevel qlvl : QualityLevel.values()) {
			this.qualityListBox.addItem(qlvl.toString());
		}
		this.qualityListBox.setSelectedIndex(1); // High quality

		this.add(this.logoImg);
		for (final UMLDiagram.Type type : UMLDiagram.Type.values()) {
			final Button startBtn = new Button("Start new UML " + type.getName() + " diagram...");
			this.add(startBtn);
			startBtn.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(final ClickEvent event) {
					StartPanel.this.setOptions("Drawer", type);
				}
			});
		}
		this.add(this.startDemoBtn);
		this.startDemoBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				StartPanel.this.setOptions("Demo", UMLDiagram.Type.HYBRID);
			}
		});

		this.add(this.startDemoBtn);
		this.startDemoBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				StartPanel.this.setOptions("Demo", UMLDiagram.Type.HYBRID);
			}
		});

		this.add(this.startAnimateDemoBtn);
		this.startAnimateDemoBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				StartPanel.this.setOptions("AnimatedDemo", UMLDiagram.Type.HYBRID);
			}
		});
//TODO
		this.add(this.logViewer);
		logViewer.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				StartPanel.this.setOptions("Viewer", UMLDiagram.Type.CLASS);
			}
		});

		if (OptionsManager.get("Advanced") == 1) {
			this.gfxEnginePanel.add(this.gfxEngineLbl);
			this.gfxEnginePanel.add(this.gfxEngineListBox);
			this.add(this.gfxEnginePanel);
			this.geometryStylePanel.add(this.geometryStyleLbl);
			this.geometryStylePanel.add(this.geometryStyleListBox);
			this.add(this.geometryStylePanel);
		}

		this.themePanel.add(this.themeLbl);
		this.themePanel.add(this.themeListBox);
		this.add(this.themePanel);
		if (OptionsManager.get("Advanced") == 1) {
			this.resolutionAutoPanel.add(this.isResolutionAutoChkBox);
			this.add(this.resolutionAutoPanel);

			this.resolutionPanel.add(this.resolutionLbl);
			this.resolutionPanel.add(this.widthTxtBox);
			this.resolutionPanel.add(this.crossLbl);
			this.resolutionPanel.add(this.heightTxtBox);
			this.add(this.resolutionPanel);
		}
		this.qualityPanel.add(this.qualityLbl);
		this.qualityPanel.add(this.qualityListBox);
		this.add(this.qualityPanel);
		this.loadingScreen.hide();
	}

	private void setOptions(final String newHistoryToken, final Type type) {
		OptionsManager.set("DiagramType", type.getIndex());
		OptionsManager.set("Theme", this.themeListBox.getSelectedIndex());
		OptionsManager.set("QualityLevel", this.qualityListBox.getSelectedIndex());
		if (OptionsManager.get("Advanced") == 1) {
			OptionsManager.set("GraphicEngine", this.gfxEngineListBox.getSelectedIndex());
			OptionsManager.set("GeometryStyle", this.geometryStyleListBox.getSelectedIndex());
			OptionsManager.set("AutoResolution", this.isResolutionAutoChkBox.getValue() ? 1 : 0);

			int w;
			int h;
			try {
				w = Integer.parseInt(this.widthTxtBox.getText());
				h = Integer.parseInt(this.heightTxtBox.getText());
			} catch (final Exception ex) {
				Log.warn("Unreadable resolution " + this.widthTxtBox.getText() + "x" + this.heightTxtBox.getText() + "!");
				w = 800;
				h = 600;
			}
			OptionsManager.set("Width", w);
			OptionsManager.set("Height", h);
		}
		History.newItem(newHistoryToken + "?" + OptionsManager.toURL());

	}
}
