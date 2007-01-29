package org.gwm.samples.gwmdemo.client;

import java.util.Date;

import org.gwm.client.GInternalFrame;
import org.gwm.client.impl.GwtInternalFrame;
import org.gwm.client.util.GwmUtilities;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class GwtWindowManagerTest implements EntryPoint {
	private static final String COOKIE_VISITOR = "gwm1";

	private static final String COOKIE_NICKNAME = "gwm2";

	private static final String COOKIE_LAST_VISIT = "gwm3";

	private Object windowManager;

	protected GInternalFrame myWindow;

	public void onModuleLoad() {

		//windowManager = new FramesManagerFactory().createFramesManager();

		TabPanel tabPanel = new TabPanel();
		Hyperlink introductionLink = new Hyperlink("Welcome", "welcome");
		introductionLink.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				displayWelcome();

			}

		});
		Hyperlink documentationLink = new Hyperlink("Javadoc API",
				"documentation");
		documentationLink.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				displayDocumentation();

			}

		});
		Hyperlink gettingStartedLink = new Hyperlink("Getting started",
				"starter");
		gettingStartedLink.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				displayGettingStarted();

			}

		});

		Hyperlink partnersAndSponsorsLink = new Hyperlink(
				"Sponsors & Partners", "partners");
		partnersAndSponsorsLink.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				displayPartners();

			}

		});

		Hyperlink windowEditorLink = new Hyperlink("WindowEditor",
				"windoweditor");
		windowEditorLink.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				displayWindowEditor();

			}

		});

		HorizontalPanel samples = new HorizontalPanel();
		samples.add(new SimpleWindowWithURLScenarii(null).getLink());
		samples.add(new SimpleWindowWithTextScenarii(null).getLink());
		samples
				.add(new SimpleWindowWithWidgetScenarii(null)
						.getLink());
		samples.add(new ThemesScenarii(null).getLink());
		samples.add(windowEditorLink);
		// samples.setCellWidth(windowEditorLink, "450px");

		HorizontalPanel documentations = new HorizontalPanel();

		documentations.add(gettingStartedLink);
		documentations.setCellWidth(gettingStartedLink, "110px");
		documentations.add(documentationLink);

		HorizontalPanel screenshots = new HorizontalPanel();

		Widget linkScreen1 = buildLinkToScreenShot("Screen1", "images/sc1.png");
		screenshots.add(linkScreen1);
		screenshots.setCellWidth(linkScreen1, "60px");
		Widget linkScreen2 = buildLinkToScreenShot("Screen2", "images/sc2.png");
		screenshots.add(linkScreen2);
		screenshots.setCellWidth(linkScreen2, "60px");
		Widget linkScreen3 = buildLinkToScreenShot("Screen3", "images/sc3.png");
		screenshots.add(linkScreen3);

		tabPanel.add(introductionLink, "Home");
		tabPanel.add(samples, "Demo");
		tabPanel.add(documentations, "Documentation");
		tabPanel.add(screenshots, "Screenshots");
		// tabPanel.add(partnersAndSponsorsLink, "Sponsors & Partners");

		tabPanel.selectTab(0);

		displayWelcome();

		RootPanel.get("menubar").add(tabPanel);

		Button closeAllBtn = new Button("Close all") {

		};
		closeAllBtn.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				//windowManager.closeAllFrames();

			}

		});
		setWindowManagerButtons();
		setUpUserBanner();
		testSiteVisit();
		retrieveStats();
		Timer updateStats = new Timer() {
			public void run() {
				retrieveStats();
			}

		};
		updateStats.scheduleRepeating(60000 * 5);

	}

	private void setWindowManagerButtons() {
		Widget listBtn = getListAllWindowsButton();
		listBtn.setStyleName("gwm-buttons");
		Widget arrangeBtn = getArrangeAllWindowsButton();
		arrangeBtn.setStyleName("gwm-buttons");
		Widget closeBtn = getCloseAllWindowsButton();
		closeBtn.setStyleName("gwm-buttons");
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(listBtn);
		vPanel.add(arrangeBtn);
		vPanel.add(closeBtn);
		vPanel.setSpacing(2);
		RootPanel.get("gwmb").add(vPanel);

	}

	private Widget getCloseAllWindowsButton() {
		Button closeAllButton = new Button("Close opened windows");
		closeAllButton.setWidth("160px");
		closeAllButton.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				//windowManager.closeAllFrames();

			}

		});
		return closeAllButton;
	}

	private Widget getArrangeAllWindowsButton() {
		Button arrangeAllButton = new Button("Arrange opened windows");
		arrangeAllButton.setWidth("160px");
		arrangeAllButton.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
//				GInternalFrame[] windows = windowManager.getAllFrames();
//				VerticalPanel vPanel = new VerticalPanel();
//				int xStart = 30;
//				int yStart = 60;
//
//				for (int i = 0; i < windows.length; i++) {
//					windows[i].setLocation(yStart, xStart);
//					windows[i].toFront();
//					xStart += 25;
//					yStart += 25;
//				}
			}

		});
		return arrangeAllButton;
	}

	private String getColorBit() {
		return (int) (Math.random() * 10) + "";
	}

	private String getRandomColor() {
		String color = "#";
		for (int i = 0; i < 6; i++) {
			color += getColorBit() + "";
		}
		return color;
	}

	private Widget getListAllWindowsButton() {
		Button listAllButton = new Button("List opened Windows ...");
		listAllButton.setWidth("160px");
		listAllButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
//				GInternalFrame[] windows = windowManager.getAllFrames();
//				VerticalPanel vPanel = new VerticalPanel();
//				for (int i = 0; i < windows.length; i++) {
//					Label label = new Label((i + 1) + " - "
//							+ windows[i].getTitle());
//					vPanel.add(label);
//					DOM.setStyleAttribute(label.getElement(),
//							"backgroundColor", getRandomColor());
//					DOM.setStyleAttribute(label.getElement(), "color", "white");
//					DOM.setStyleAttribute(label.getElement(), "padding", "3px");
//
//				}
//				vPanel.setSpacing(2);
//				GInternalFrame listWindow = windowManager.newFrame();
//				listWindow.setTitle("Windows list ...");
//				listWindow.setWidth(300);
//				listWindow.setHeight(350);
//				listWindow.showCenter(false);
//				listWindow.setContent(vPanel);
			}

		});
		return listAllButton;
	}

	private void retrieveStats() {
		ServerFacade.get().getRemoteService().getStats(new AsyncCallback() {
			public void onFailure(Throwable caught) {
				displayError(caught.getMessage());
			}

			public void onSuccess(Object result) {
				RootPanel.get("visits").clear();
				Stats stats = (Stats) result;
				VerticalPanel vpanel = new VerticalPanel();
				HorizontalPanel hpanel = new HorizontalPanel();
				Image image = new Image("images/puceblue.jpg");
				hpanel.add(image);
				hpanel.add(new Label(stats.getVisitorCount() + " Visitors"));
				HorizontalPanel hpanel1 = new HorizontalPanel();
				Image image2 = new Image("images/pucev.jpg");
				hpanel1.add(image2);
				hpanel1.add(new Label(stats.getVisitCount() + " Visits"));
				//vpanel.add(hpanel);
				vpanel.add(hpanel1);
				vpanel.add(getResgisterForNewsWidget());
				RootPanel.get("visits").add(vpanel);

			}

		});

	}

	public Widget getResgisterForNewsWidget() {
		VerticalPanel registerPanel = new VerticalPanel();
		registerPanel
				.add(new HTML(
						"<br/><span style=color:red>Please notify me for news.</span><br/><br/>"));
		HorizontalPanel nicknamePanel = new HorizontalPanel();
		Label nicknameLbl = new Label("Nickname : ");
		nicknameLbl.setWidth("60");
		Image image = new Image("images/pucer.jpg");
		image.setSize("16px", "16px");
		nicknamePanel.add(image);
		nicknamePanel.add(nicknameLbl);

		final TextBox nickname = new TextBox();
		nickname.setWidth("117px");
		DOM.setStyleAttribute(nickname.getElement(), "backgroundColor",
				"#aaFFaa");
		nicknamePanel.add(nickname);
		HorizontalPanel emailPanel = new HorizontalPanel();
		Image image1 = new Image("images/pucer.jpg");
		image1.setSize("16px", "16px");
		emailPanel.add(image1);
		Label emailLabel = new Label("E-mail : ");
		emailPanel.add(emailLabel);
		// emailLabel.setWidth("60px");
		final TextBox email = new TextBox();
		DOM.setStyleAttribute(email.getElement(), "backgroundColor", "#aaFFaa");
		email.setWidth("135px");
		emailPanel.add(email);
		Button registerButton = new Button("Register");
		registerButton.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				final String nicknameValue = nickname.getText();
				String emailValue = email.getText();
				if (nicknameValue.trim().equals("")
						|| emailValue.trim().equals("")) {
					displayError("Please fill all fields if you want be notified for news!");
					return;
				}
				ServerFacade.get().getRemoteService().registerMailingList(
						nicknameValue, emailValue, new AsyncCallback() {

							public void onFailure(Throwable caught) {
								displayError(caught.getMessage());

							}

							public void onSuccess(Object result) {
								if (result == null) {
									displayInfo("Thank you << "
											+ nicknameValue
											+ " >> for your interest to Gwt Window Manager.");
									Date expirationDate = new Date();
									expirationDate.setYear(2008);
									Cookies.setCookie(COOKIE_NICKNAME,
											nicknameValue, expirationDate);
									setUpUserBanner();
								} else {
									displayError(result.toString());
								}

							}

						});

			}

		});
		registerPanel.add(nicknamePanel);
		registerPanel.add(emailPanel);
		registerPanel.add(registerButton);
		registerPanel.setCellHorizontalAlignment(registerButton,
				HasHorizontalAlignment.ALIGN_CENTER);
		return registerPanel;

	}

	private void displayInfo(String info) {
		displayMessage(info, "blue");
	}

	private void displayError(String errorMsg) {
		displayMessage(errorMsg, "red");
	}

	private void displayMessage(String errorMsg, String color) {
		final GInternalFrame window = new GwtInternalFrame("");
		window.setTheme("alphacube");
		window.setSize(300, 50);
		window.setResizable(false);
		window.setMaximizable(false);
		window.setMinimizable(false);
        GwmUtilities.displayAtParentCenter(window);
		VerticalPanel panel = new VerticalPanel();
		Label msgLbl = new Label(errorMsg);
		DOM.setStyleAttribute(msgLbl.getElement(), "color", color);
		DOM.setStyleAttribute(msgLbl.getElement(), "fontWeight", "bold");
		panel.add(msgLbl);

		window.setContent(panel);
	}

	private void registerVisit(boolean newVisitor) {
		Date expirationDate = new Date();
		expirationDate.setYear(2008);
		Cookies.setCookie(COOKIE_LAST_VISIT, System.currentTimeMillis()+"", expirationDate);
		ServerFacade.get().getRemoteService().registerVisit(newVisitor,
				new AsyncCallback() {
					public void onFailure(Throwable caught) {
						displayError(caught.getMessage());

					}

					public void onSuccess(Object result) {
						Date expirationDate = new Date();
						expirationDate.setYear(2008);
						Cookies.setCookie(COOKIE_VISITOR, "welcome",
								expirationDate);

					}

				});

	}

	private void setUpUserBanner() {
		String cookie = Cookies.getCookie(COOKIE_NICKNAME);
		if (cookie != null) {
			RootPanel.get("usr").clear();
			Label usrLbl = new Label("Welcome " + cookie);
			usrLbl.setStyleName("usr");
			RootPanel.get("usr").add(
					new HTML(
							"<span style=background-color:blue;color:white;font-family:Arial;padding:3px>"
									+ "Welcome " + cookie + "</span/>"));
		}

	}

	private void testSiteVisit() {

		String cookie = Cookies.getCookie(COOKIE_VISITOR);
		if (cookie == null) {
			registerVisit(true);
		} else {
			String lastVisitCookie = Cookies.getCookie(COOKIE_LAST_VISIT);
			if (lastVisitCookie != null) {
				long lastVisit = Long.parseLong(lastVisitCookie);
				long elapsedTime = (System.currentTimeMillis() - lastVisit) /1000;
				if(elapsedTime < 60 ){
					return;
				}
				registerVisit(false);
			}
		}

		ServerFacade.get().getRemoteService().echo(new AsyncCallback() {

			public void onFailure(Throwable caught) {
			}

			public void onSuccess(Object result) {
			}

		});

	}

	private Hyperlink buildLinkToScreenShot(final String linkCaption,
			final String url) {
		Hyperlink linkToScreenshot = new Hyperlink(linkCaption, null);
		linkToScreenshot.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				GInternalFrame window = new GwtInternalFrame("");
				window.setCaption(linkCaption);
				window.setSize(800, Window.getClientHeight() - 140);
				window.setTheme("spread");
				window.setUrl(url);
				window.setVisible(true);
				myWindow = window;
			}

		});
		return linkToScreenshot;
	}

	protected void displayGettingStarted() {
		GInternalFrame window = new GwtInternalFrame("");
		window.setTheme("mac_os_x");
		window.setCaption("Getting Started");
		window.setUrl("site/gettingstarted.html");
		window.setWidth(700);
		window.setHeight(Window.getClientHeight() - 100);
		window.setVisible(true);

	}

	private void displayDocumentation() {

		// GInternalFrame window = getNewWindow("Documentation");
		GInternalFrame window = new GwtInternalFrame("");
		window.setTheme("mac_os_x");
		window.setUrl("doc/index.html");
		window.setCaption("Documentation");
		window.setWidth(900);
		window.setHeight(500);
		window.setVisible(true);

	}

	private void displayPartners() {
		GInternalFrame window = getNewWindow("Sponsors & Partners");
		window.setUrl("site/partners.html");
		window.setWidth(800);
		window.setHeight(450);
		window.setVisible(true);

	}

	private void displayWelcome() {
		GInternalFrame window = getNewWindow("Welcome");
		window.setTheme("alphacube");
		window.setWidth(650);
		window.setHeight(250);
		window.setResizable(false);
		window.setDraggable(true);
		window.setUrl("site/introduction.html");
         GwmUtilities.displayAtParentCenter(window);
	}

	private GInternalFrame getNewWindow(String title) {
		GInternalFrame window = new GwtInternalFrame("");
		window.setTheme("mac_os_x");
		window.setDraggable(false);
		window.setMaximizable(false);
		window.setMinimizable(false);

		window.setCaption(title);
		return window;
	}

	private void displayWindowEditor() {

		WindowEditor editor = new WindowEditor(windowManager);
		GInternalFrame editorWindow =new GwtInternalFrame("");
		editorWindow.setTheme("mac_os_x");
		editorWindow.setWidth(280);
		editorWindow.setHeight(460);
		editorWindow.setCaption("Window editor");
		editorWindow.setTop(60);
		editorWindow.setLeft(10);
        editorWindow.setContent(editor);
		editorWindow.setVisible(true);
		
	}

}
