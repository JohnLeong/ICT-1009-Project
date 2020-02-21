package com.ict1009.userinterface;

import java.awt.EventQueue;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.SystemColor;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.TextArea;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.ui.RefineryUtilities;
import org.json.JSONException;

import com.ict1009.analysis.AnalysedDataProperties;
import com.ict1009.analysis.DataAnalyser;
import com.ict1009.ocr.OCRUtility;
import com.ict1009.returnvalues.ReturnCodes;
import com.ict1009.scrapper.InstagramScraper;
import com.ict1009.scrapper.ScrapeUtility;
import com.ict1009.scrapper.TwitterScraper;
import com.ict1009.stanfordcorenlp.InstagramSentimentAnalyzer;
import com.ict1009.utilities.FileHelper;
import com.ict1009.utilities.JSONUtility;
import com.ict1009.visualisation.InstagramBarChart;
import com.ict1009.visualisation.InstagramLineGraph;
import com.ict1009.visualisation.InstagramPieChart;
import com.ict1009.visualisation.TwitterBarChart;

import javax.swing.UIManager;
import javax.swing.JCheckBox;


public class FrameDashboard extends JFrame implements ReturnCodes {

	/* The current serial version UID*/
	private static final long serialVersionUID = 1L;
	
	/* Cache of images that will be displated on the GUI*/	
	private Image img_logo = new ImageIcon(FrameDashboard.class.getResource("../../../resource/sit.png")).getImage().getScaledInstance(200, 90, Image.SCALE_SMOOTH);
	private Image img_instagram = new ImageIcon(FrameDashboard.class.getResource("../../../resource/instagram.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_instagram_hover = new ImageIcon(FrameDashboard.class.getResource("../../../resource/instagram_hover.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_twitter = new ImageIcon(FrameDashboard.class.getResource("../../../resource/twitter.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_twitter_hover = new ImageIcon(FrameDashboard.class.getResource("../../../resource/twitter_hover.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_analysis = new ImageIcon(FrameDashboard.class.getResource("../../../resource/eye.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_analysis_hover = new ImageIcon(FrameDashboard.class.getResource("../../../resource/eye_hover.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_visualise = new ImageIcon(FrameDashboard.class.getResource("../../../resource/report.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_visualise_hover = new ImageIcon(FrameDashboard.class.getResource("../../../resource/report_hover.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_json = new ImageIcon(FrameDashboard.class.getResource("../../../resource/json.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_json_hover = new ImageIcon(FrameDashboard.class.getResource("../../../resource/json_hover.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_about = new ImageIcon(FrameDashboard.class.getResource("../../../resource/about.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_about_hover = new ImageIcon(FrameDashboard.class.getResource("../../../resource/about_hover.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_ocr = new ImageIcon(FrameDashboard.class.getResource("../../../resource/ocr1.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_ocr_hover = new ImageIcon(FrameDashboard.class.getResource("../../../resource/ocr_hover1.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);	
	private Image img_javaLogo = new ImageIcon(FrameDashboard.class.getResource("../../../resource/java_logo.png")).getImage().getScaledInstance(200, 90, Image.SCALE_SMOOTH);
	private Image img_jSoup = new ImageIcon(FrameDashboard.class.getResource("../../../resource/jsoup.png")).getImage().getScaledInstance(200, 90, Image.SCALE_SMOOTH);
	private Image img_javaSwing = new ImageIcon(FrameDashboard.class.getResource("../../../resource/javaSwing.png")).getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
	private Image img_selenium = new ImageIcon(FrameDashboard.class.getResource("../../../resource/selenium.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	private Image img_standford = new ImageIcon(FrameDashboard.class.getResource("../../../resource/standfordCoreNlp.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	private Image img_twitter4j = new ImageIcon(FrameDashboard.class.getResource("../../../resource/twitter4j.png")).getImage().getScaledInstance(300, 90, Image.SCALE_SMOOTH);
	private Image img_maven = new ImageIcon(FrameDashboard.class.getResource("../../../resource/maven.png")).getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH);
	private Image img_tesseract = new ImageIcon(FrameDashboard.class.getResource("../../../resource/tesseractOCR.png")).getImage().getScaledInstance(150, 90, Image.SCALE_SMOOTH);
	private Image img_profilePic = new ImageIcon(FrameDashboard.class.getResource("../../../resource/profilePic.png")).getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
	private Image img_profile_john = new ImageIcon(FrameDashboard.class.getResource("../../../resource/john.png")).getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
	private Image img_profile_aaron = new ImageIcon(FrameDashboard.class.getResource("../../../resource/aaron.png")).getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
	private Image img_profile_damon = new ImageIcon(FrameDashboard.class.getResource("../../../resource/damon.png")).getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
	
	
	/* Various GUI components */
	private JTextField txtInstagramUsername;
	private JPasswordField txtInstagramPassword;
	private JTextArea txtInstagramHashtags;
	private JTextField txtHashTagNumPosts;
	private static JTextArea txaInstagramConsole;
	private JTextArea txaTwitterConsole;
	private JTextField txtTwitterNumPosts;
	private JTextField txtTwitterNumPostsProfile;
	private JTextField txtProfileNumberOfPosts;
	private JTable tableDataAnalysisRelatedHashtags;
	
	/* String to store current export path */
	private String exportPath = "";
	
	/* The main content panel that parents the rest of the GUI */
	private JPanel contentPane;

	/* Side panel GUI */
	private JPanel pnlSideMenu;
	private JPanel pnlOptions;
	private JPanel pnlInstagram;
	private JPanel pnlTwitter;
	private JPanel pnlDisplayJson;
	private JPanel pnlDataAnalysis;
	private JPanel pnlVisualisation;
	private JPanel pnlAbout;
	private JPanel pnlOcr;
	private JPanel pnlInfo;
	
	/* Side panel icons */
	private JLabel lblIconSit;
	private JLabel lblIconInstagram;
	private JLabel lblIconTwitter;
	private JLabel lblIconJson;
	private JLabel lblIconDataAnalysis;
	private JLabel lblIconVisualisation;
	private JLabel lblIconAbout;
	private JLabel lblIconOcr;
	
	/* Side panel text */
	private JLabel lblInstagramMode; 
	private JLabel lblTwitterMode; 
	private JLabel lblDisplayJsonMode; 
	private JLabel lblDataAnalysisMode;	
	private JLabel lblVisualisationMode; 
	private JLabel lblAbout; 
	private JLabel lblOcr;

	/* The current panel that is being displayed */
	private JPanel selectedSidePanel;
	
	/* Boolean to keep track if the wordmap has been generated */
	private boolean wordmapGenerated = false;
	
	
	/**
	 * Launch the application.
	 */
	public static void launchGui(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FrameDashboard frame = new FrameDashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Changes the currently displayed panel
	 * 
	 * @param panel 	The new panel to display
	 */
	private void changeSelectedPanelColor(JPanel panel) {
		panel.setBackground(new Color(0.3f, 0.3f, 0.3f)); //Set to light grey		
	}

	/**
	 * Resets the color of all side panel buttons
	 * 
	 * @param panels 	The array of panels to reset
	 */
	private void resetSidePanelsColor(JPanel[] panels) {
		for (int i = 0; i < panels.length; ++i) {
			if (panels[i] != selectedSidePanel) {
				panels[i].setBackground(new Color(0.2f, 0.2f, 0.2f));
			}
		}
	}
	
	/**
	 * Generates and displays a message box with the provided string
	 * 
	 * @param s 		The message to display
	 */
	private void msgbox(String s){
		JOptionPane.showMessageDialog(null, s);
	}

	/**
	 * Adds a new message to the instagram console GUI
	 * 
	 * @param message	The message to append to the console
	 */
	public static void appendInstagramConsole(String message) {
		txaInstagramConsole.append(message);
	}

	/**
	 * Resets all the side panel icons to the "non-selected" state
	 */
	private void resetAllPanelIcons() {
		lblIconInstagram.setIcon(new ImageIcon(img_instagram));
		lblIconTwitter.setIcon(new ImageIcon(img_twitter));
		lblIconJson.setIcon(new ImageIcon(img_json));
		lblIconVisualisation.setIcon(new ImageIcon(img_visualise));
		lblIconDataAnalysis.setIcon(new ImageIcon(img_analysis));
		lblIconAbout.setIcon(new ImageIcon(img_about));
		lblIconOcr.setIcon(new ImageIcon(img_ocr));


	}
	
	/**
	 *Side panel button click events 
	 */
	private void addSidePanelEvents() {			
		JPanel[] sidePanels = new JPanel[] { pnlInstagram
				, pnlTwitter
				, pnlDisplayJson
				, pnlDataAnalysis
				, pnlVisualisation
				, pnlOcr
				, pnlAbout };

		
		pnlInstagram.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedSidePanel = pnlInstagram;
				resetSidePanelsColor(sidePanels);
				resetAllPanelIcons();
				
				changeSelectedPanelColor(pnlInstagram);
				lblIconInstagram.setIcon(new ImageIcon(img_instagram_hover));
				
				CardLayout card = (CardLayout)pnlInfo.getLayout();
				card.show(pnlInfo, "pnlInstagramInfo");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				changeSelectedPanelColor(pnlInstagram);
				lblIconInstagram.setIcon(new ImageIcon(img_instagram_hover));
			}
			@Override
			public void mouseExited(MouseEvent e) {		
				if (pnlInstagram != selectedSidePanel) {
					resetSidePanelsColor(sidePanels);
					lblIconInstagram.setIcon(new ImageIcon(img_instagram));
				}
				
			}
		});
		pnlTwitter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedSidePanel = pnlTwitter;
				resetSidePanelsColor(sidePanels);
				resetAllPanelIcons();
				
				changeSelectedPanelColor(pnlTwitter);
				lblIconTwitter.setIcon(new ImageIcon(img_twitter_hover));
				
				CardLayout card = (CardLayout)pnlInfo.getLayout();
				card.show(pnlInfo, "pnlTwitterInfo");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				changeSelectedPanelColor(pnlTwitter);
				lblIconTwitter.setIcon(new ImageIcon(img_twitter_hover));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (pnlTwitter != selectedSidePanel) {
					resetSidePanelsColor(sidePanels);
					lblIconTwitter.setIcon(new ImageIcon(img_twitter));
				}				
			}
		});
		
		pnlDisplayJson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedSidePanel = pnlDisplayJson;
				resetSidePanelsColor(sidePanels);
				resetAllPanelIcons();
				
				changeSelectedPanelColor(pnlDisplayJson);
				lblIconJson.setIcon(new ImageIcon(img_json_hover));
				
				CardLayout card = (CardLayout)pnlInfo.getLayout();
				card.show(pnlInfo, "pnlDisplayDataInfo");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				changeSelectedPanelColor(pnlDisplayJson);
				lblIconJson.setIcon(new ImageIcon(img_json_hover));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (pnlDisplayJson != selectedSidePanel) {
					resetSidePanelsColor(sidePanels);
					lblIconJson.setIcon(new ImageIcon(img_json));
				}				
			}
		});
		
		pnlDataAnalysis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedSidePanel = pnlDataAnalysis;
				resetSidePanelsColor(sidePanels);
				resetAllPanelIcons();
				
				changeSelectedPanelColor(pnlDataAnalysis);
				lblIconDataAnalysis.setIcon(new ImageIcon(img_analysis_hover));
				
				CardLayout card = (CardLayout)pnlInfo.getLayout();
				card.show(pnlInfo, "pnlDataAnalysisInfo");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				changeSelectedPanelColor(pnlDataAnalysis);
				lblIconDataAnalysis.setIcon(new ImageIcon(img_analysis_hover));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (pnlDataAnalysis != selectedSidePanel) {
					resetSidePanelsColor(sidePanels);
					lblIconDataAnalysis.setIcon(new ImageIcon(img_analysis));
				}				
			}
		});
		
		pnlVisualisation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedSidePanel = pnlVisualisation;
				resetSidePanelsColor(sidePanels);
				resetAllPanelIcons();
				
				changeSelectedPanelColor(pnlVisualisation);
				lblIconVisualisation.setIcon(new ImageIcon(img_visualise_hover));
				
				CardLayout card = (CardLayout)pnlInfo.getLayout();
				card.show(pnlInfo, "pnlVisualisationInfo");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				changeSelectedPanelColor(pnlVisualisation);
				lblIconVisualisation.setIcon(new ImageIcon(img_visualise_hover));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (pnlVisualisation != selectedSidePanel) {
					resetSidePanelsColor(sidePanels);
					lblIconVisualisation.setIcon(new ImageIcon(img_visualise));
				}				
			}
		});
		
		pnlOcr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedSidePanel = pnlOcr;
				resetSidePanelsColor(sidePanels);
				resetAllPanelIcons();
				
				changeSelectedPanelColor(pnlOcr);
				lblIconOcr.setIcon(new ImageIcon(img_ocr_hover));
				
				CardLayout card = (CardLayout)pnlInfo.getLayout();
				card.show(pnlInfo, "pnlOcrInfo");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				changeSelectedPanelColor(pnlOcr);
				lblIconOcr.setIcon(new ImageIcon(img_ocr_hover));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (pnlOcr != selectedSidePanel) {
					resetSidePanelsColor(sidePanels);
					lblIconOcr.setIcon(new ImageIcon(img_ocr));
				}				
			}
		});
		
		

		pnlAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedSidePanel = pnlAbout;
				resetSidePanelsColor(sidePanels);
				resetAllPanelIcons();
				
				changeSelectedPanelColor(pnlAbout);
				lblIconAbout.setIcon(new ImageIcon(img_about_hover));
						
				CardLayout card = (CardLayout)pnlInfo.getLayout();
				card.show(pnlInfo, "pnlCreditsInfo");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				changeSelectedPanelColor(pnlAbout);
				lblIconAbout.setIcon(new ImageIcon(img_about_hover));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (pnlAbout != selectedSidePanel) {
					resetSidePanelsColor(sidePanels);
					lblIconAbout.setIcon(new ImageIcon(img_about));
				}				
			}
		});

	}
	
	/**
	 * The constructor function of FrameDashboard
	 * Initializes and creates the main GUI
	 */
	public FrameDashboard() {
		//setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1041, 764);
		setTitle("ICT1009 Project - Group 34");

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/* start of side panel----------------------------------------------------------------- */
		pnlSideMenu = new JPanel();
		pnlSideMenu.setBounds(0, 0, 237, 868);
		contentPane.add(pnlSideMenu);
		pnlSideMenu.setLayout(null);

		lblIconSit = new JLabel("");
		lblIconSit.setBounds(12, 33, 205, 86);
		lblIconSit.setIcon(new ImageIcon(img_logo));
		pnlSideMenu.add(lblIconSit);

		pnlOptions = new JPanel();
		pnlOptions.setBackground(new Color(102, 102, 102));
		pnlOptions.setBounds(0, 138, 261, 753);
		pnlSideMenu.add(pnlOptions);
		pnlOptions.setLayout(null);

		pnlInstagram = new JPanel();
		pnlInstagram.setBorder(null);
		pnlInstagram.setBackground(new Color(51, 51, 51));
		pnlInstagram.setBounds(0, 43, 236, 53);
		pnlOptions.add(pnlInstagram);
		pnlInstagram.setLayout(null);

		lblInstagramMode = new JLabel("Instagram");
		lblInstagramMode.setForeground(new Color(255, 255, 255));
		lblInstagramMode.setBackground(new Color(255, 255, 255));
		lblInstagramMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstagramMode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInstagramMode.setBounds(60, 15, 117, 25);
		pnlInstagram.add(lblInstagramMode);

		lblIconInstagram = new JLabel("");
		lblIconInstagram.setBounds(18, 7, 39, 41);
		pnlInstagram.add(lblIconInstagram);
		lblIconInstagram.setForeground(new Color(255, 255, 255));
		lblIconInstagram.setBackground(new Color(255, 255, 255));
		lblIconInstagram.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconInstagram.setIcon(new ImageIcon(img_instagram));

		pnlTwitter = new JPanel();
		pnlTwitter.setLayout(null);
		pnlTwitter.setBorder(null);
		pnlTwitter.setBackground(new Color(51, 51, 51));
		pnlTwitter.setBounds(0, 94, 236, 53);
		pnlOptions.add(pnlTwitter);

		lblTwitterMode = new JLabel("Twitter");
		lblTwitterMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblTwitterMode.setForeground(Color.WHITE);
		lblTwitterMode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTwitterMode.setBackground(Color.WHITE);
		lblTwitterMode.setBounds(60, 15, 117, 25);
		pnlTwitter.add(lblTwitterMode);

		lblIconTwitter = new JLabel("");
		lblIconTwitter.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconTwitter.setForeground(Color.WHITE);
		lblIconTwitter.setBackground(Color.WHITE);
		lblIconTwitter.setBounds(18, 7, 39, 41);
		lblIconTwitter.setIcon(new ImageIcon(img_twitter));
		pnlTwitter.add(lblIconTwitter);

		pnlDataAnalysis = new JPanel();
		pnlDataAnalysis.setLayout(null);
		pnlDataAnalysis.setBorder(null);
		pnlDataAnalysis.setBackground(new Color(51, 51, 51));
		pnlDataAnalysis.setBounds(0, 197, 236, 53);
		pnlOptions.add(pnlDataAnalysis);

		lblDataAnalysisMode = new JLabel("Data Analysis");
		lblDataAnalysisMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataAnalysisMode.setForeground(Color.WHITE);
		lblDataAnalysisMode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDataAnalysisMode.setBackground(Color.WHITE);
		lblDataAnalysisMode.setBounds(60, 15, 131, 25);
		pnlDataAnalysis.add(lblDataAnalysisMode);

		lblIconDataAnalysis = new JLabel("");
		lblIconDataAnalysis.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconDataAnalysis.setForeground(Color.WHITE);
		lblIconDataAnalysis.setBackground(Color.WHITE);
		lblIconDataAnalysis.setBounds(18, 7, 39, 41);
		lblIconDataAnalysis.setIcon(new ImageIcon(img_analysis));
		pnlDataAnalysis.add(lblIconDataAnalysis);

		pnlVisualisation = new JPanel();
		pnlVisualisation.setLayout(null);
		pnlVisualisation.setBorder(null);
		pnlVisualisation.setBackground(new Color(51, 51, 51));
		pnlVisualisation.setBounds(0, 248, 236, 53);
		pnlOptions.add(pnlVisualisation);

		lblVisualisationMode = new JLabel("Visualisation");
		lblVisualisationMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualisationMode.setForeground(Color.WHITE);
		lblVisualisationMode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVisualisationMode.setBackground(Color.WHITE);
		lblVisualisationMode.setBounds(60, 15, 117, 25);
		pnlVisualisation.add(lblVisualisationMode);

		lblIconVisualisation = new JLabel("");
		lblIconVisualisation.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconVisualisation.setForeground(Color.WHITE);
		lblIconVisualisation.setBackground(Color.WHITE);
		lblIconVisualisation.setBounds(18, 7, 39, 41);
		lblIconVisualisation.setIcon(new ImageIcon(img_visualise));
		pnlVisualisation.add(lblIconVisualisation);

		pnlDisplayJson = new JPanel();
		pnlDisplayJson.setLayout(null);
		pnlDisplayJson.setBorder(null);
		pnlDisplayJson.setBackground(new Color(51, 51, 51));
		pnlDisplayJson.setBounds(0, 146, 236, 53);
		pnlOptions.add(pnlDisplayJson);

		lblDisplayJsonMode = new JLabel("Display JSON");
		lblDisplayJsonMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplayJsonMode.setForeground(Color.WHITE);
		lblDisplayJsonMode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDisplayJsonMode.setBackground(Color.WHITE);
		lblDisplayJsonMode.setBounds(60, 15, 117, 25);
		pnlDisplayJson.add(lblDisplayJsonMode);

		lblIconJson = new JLabel("");
		lblIconJson.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconJson.setForeground(Color.WHITE);
		lblIconJson.setBackground(Color.WHITE);
		lblIconJson.setBounds(18, 7, 39, 41);
		lblIconJson.setIcon(new ImageIcon(img_json));
		pnlDisplayJson.add(lblIconJson);
		
		JPanel pnlFunctionalities = new JPanel();
		pnlFunctionalities.setLayout(null);
		pnlFunctionalities.setBorder(null);
		pnlFunctionalities.setBackground(new Color(51, 51, 51));
		pnlFunctionalities.setBounds(0, 0, 236, 44);
		pnlOptions.add(pnlFunctionalities);
		
		JLabel lblFunctionalities = new JLabel("Functionalities");
		lblFunctionalities.setHorizontalAlignment(SwingConstants.CENTER);
		lblFunctionalities.setForeground(Color.LIGHT_GRAY);
		lblFunctionalities.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblFunctionalities.setBackground(Color.WHITE);
		lblFunctionalities.setBounds(12, 10, 117, 25);
		pnlFunctionalities.add(lblFunctionalities);
		
		JPanel pnlInformation = new JPanel();
		pnlInformation.setLayout(null);
		pnlInformation.setBorder(null);
		pnlInformation.setBackground(new Color(51, 51, 51));
		pnlInformation.setBounds(0, 351, 236, 44);
		pnlOptions.add(pnlInformation);
		
		JLabel lblInformation = new JLabel("Information");
		lblInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformation.setForeground(Color.LIGHT_GRAY);
		lblInformation.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblInformation.setBackground(Color.WHITE);
		lblInformation.setBounds(12, 10, 93, 25);
		pnlInformation.add(lblInformation);
		
		pnlAbout = new JPanel();
		pnlAbout.setLayout(null);
		pnlAbout.setBorder(null);
		pnlAbout.setBackground(new Color(51, 51, 51));
		pnlAbout.setBounds(0, 395, 236, 53);
		pnlOptions.add(pnlAbout);
		
		lblAbout = new JLabel("About");
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setForeground(Color.WHITE);
		lblAbout.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAbout.setBackground(Color.WHITE);
		lblAbout.setBounds(65, 15, 97, 25);
		pnlAbout.add(lblAbout);
		
		lblIconAbout = new JLabel("");
		lblIconAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconAbout.setForeground(Color.WHITE);
		lblIconAbout.setBackground(Color.WHITE);
		lblIconAbout.setBounds(18, 7, 39, 41);
		lblIconAbout.setIcon(new ImageIcon(img_about));
		pnlAbout.add(lblIconAbout);
		
		pnlOcr = new JPanel();
		pnlOcr.setLayout(null);
		pnlOcr.setBorder(null);
		pnlOcr.setBackground(new Color(51, 51, 51));
		pnlOcr.setBounds(0, 297, 236, 53);
		pnlOptions.add(pnlOcr);
		
		lblOcr = new JLabel("Image OCR");
		lblOcr.setHorizontalAlignment(SwingConstants.CENTER);
		lblOcr.setForeground(Color.WHITE);
		lblOcr.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOcr.setBackground(Color.WHITE);
		lblOcr.setBounds(50, 17, 137, 25);
		pnlOcr.add(lblOcr);
		
		lblIconOcr = new JLabel("");
		lblIconOcr.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconOcr.setForeground(Color.WHITE);
		lblIconOcr.setBackground(Color.WHITE);
		lblIconOcr.setBounds(18, 7, 39, 41);
		lblIconOcr.setIcon(new ImageIcon(img_ocr));
		pnlOcr.add(lblIconOcr);

		pnlInfo = new JPanel();
		pnlInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.setBounds(236, 0, 799, 749);
		pnlInfo.setLayout(new CardLayout(0, 0));
		contentPane.add(pnlInfo);
		/* end of side panel------------------------------------------------------------------- */
		
		/* start of instagram panel------------------------------------------------------------ */
		JPanel pnlInstagramInfo = new JPanel();
		pnlInstagramInfo.setLayout(null);
		pnlInstagramInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.add(pnlInstagramInfo, "pnlInstagramInfo");

		JButton btnInstagramHashTagScrape = new JButton("Execute HashTag Scrape");
		btnInstagramHashTagScrape.setBounds(65, 466, 252, 31);
		pnlInstagramInfo.add(btnInstagramHashTagScrape);

		JLabel headerInstagramPanel = new JLabel("Instagram Scraper");
		headerInstagramPanel.setFont(new Font("Tahoma", Font.BOLD, 30));
		headerInstagramPanel.setBounds(37, 19, 300, 40);
		pnlInstagramInfo.add(headerInstagramPanel);

		txtInstagramUsername = new JTextField();
		txtInstagramUsername.setText("hehebongesh");
		txtInstagramUsername.setBounds(128, 78, 166, 29);
		pnlInstagramInfo.add(txtInstagramUsername);
		txtInstagramUsername.setColumns(10);

		JLabel lblInstagramUsername = new JLabel("Username");
		lblInstagramUsername.setToolTipText("The username of the instagram account to scrape with");
		lblInstagramUsername.setBounds(37, 78, 100, 23);
		pnlInstagramInfo.add(lblInstagramUsername);

		txtInstagramPassword = new JPasswordField();
		txtInstagramPassword.setColumns(10);
		txtInstagramPassword.setBounds(416, 78, 166, 29);
		txtInstagramPassword.setText("Password12345");
		pnlInstagramInfo.add(txtInstagramPassword);

		JLabel lblInstagramPassword = new JLabel("Password");
		lblInstagramPassword.setBounds(325, 78, 100, 23);
		pnlInstagramInfo.add(lblInstagramPassword);

		JLabel lblInstagramHashtags = new JLabel("Hashtags");
		lblInstagramHashtags.setBounds(41, 348, 82, 23);
		pnlInstagramInfo.add(lblInstagramHashtags);

		txtInstagramHashtags = new JTextArea();
		txtInstagramHashtags.setRows(3);
		txtInstagramHashtags.setLineWrap(true);

		txtInstagramHashtags.setBounds(37, 180, 365, 74);
		pnlInstagramInfo.add(txtInstagramHashtags);
		txtInstagramHashtags.setColumns(9);

		JScrollPane scrInstagramHashTags = new JScrollPane(txtInstagramHashtags);
		scrInstagramHashTags.setBounds(41, 376, 305, 80);
		//scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pnlInstagramInfo.add(scrInstagramHashTags);

		JLabel lblInstagramExport = new JLabel("Export location");
		lblInstagramExport.setBounds(37, 121, 133, 23);
		pnlInstagramInfo.add(lblInstagramExport);

		JLabel lblHashTagNumPosts = new JLabel("Number of posts");
		lblHashTagNumPosts.setBounds(42, 278, 194, 23);
		pnlInstagramInfo.add(lblHashTagNumPosts);

		JLabel lblInstagramExportPath = new JLabel("Path: none");
		lblInstagramExportPath.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblInstagramExportPath.setBounds(37, 146, 707, 23);
		pnlInstagramInfo.add(lblInstagramExportPath);

		JButton btnInstagramFile = new JButton("Select path");
		btnInstagramFile.setBounds(37, 174, 131, 29);
		pnlInstagramInfo.add(btnInstagramFile);

		txtHashTagNumPosts = new JTextField();
		txtHashTagNumPosts.setBounds(42, 309, 300, 29);
		pnlInstagramInfo.add(txtHashTagNumPosts);
		txtHashTagNumPosts.setColumns(10);

		txaInstagramConsole = new JTextArea();
		txaInstagramConsole.setBackground(Color.WHITE);
		txaInstagramConsole.setEditable(false);
		txaInstagramConsole.setColumns(2);
		txaInstagramConsole.setLineWrap(true);
		txaInstagramConsole.setRows(4);
		txaInstagramConsole.setBounds(0, 0, 100, 50);
		pnlInstagramInfo.add(txaInstagramConsole);

		JScrollPane scrollPaneInstagramConsole = new JScrollPane(txaInstagramConsole);
		scrollPaneInstagramConsole.setBounds(38, 550, 706, 140);
		pnlInstagramInfo.add(scrollPaneInstagramConsole);

		JLabel lblInstagramConsole = new JLabel("Output");
		lblInstagramConsole.setBounds(37, 523, 82, 23);
		pnlInstagramInfo.add(lblInstagramConsole);

		JSeparator separator = new JSeparator();
		separator.setBounds(31, 222, 736, 23);
		pnlInstagramInfo.add(separator);

		JLabel lblHashtagMode = new JLabel("HashTag Mode");
		lblHashtagMode.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblHashtagMode.setBounds(42, 236, 300, 40);
		pnlInstagramInfo.add(lblHashtagMode);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(401, 226, 24, 301);
		pnlInstagramInfo.add(separator_1);

		JLabel lblProfileMode = new JLabel("Profile Mode");
		lblProfileMode.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblProfileMode.setBounds(432, 234, 300, 40);
		pnlInstagramInfo.add(lblProfileMode);

		JLabel lblProfileNumPosts = new JLabel("Number of posts");
		lblProfileNumPosts.setBounds(432, 278, 194, 23);
		pnlInstagramInfo.add(lblProfileNumPosts);

		txtProfileNumberOfPosts = new JTextField();
		txtProfileNumberOfPosts.setColumns(10);
		txtProfileNumberOfPosts.setBounds(432, 309, 300, 29);
		pnlInstagramInfo.add(txtProfileNumberOfPosts);

		JLabel lblInstagramProfileNames = new JLabel("Profile Names");
		lblInstagramProfileNames.setBounds(432, 348, 82, 23);
		pnlInstagramInfo.add(lblInstagramProfileNames);

		JScrollPane scrInstagramProfiles = new JScrollPane();
		scrInstagramProfiles.setBounds(429, 377, 303, 78);
		pnlInstagramInfo.add(scrInstagramProfiles);

		JTextArea txtInstagramProfiles = new JTextArea();
		scrInstagramProfiles.setViewportView(txtInstagramProfiles);
		txtInstagramProfiles.setRows(3);
		txtInstagramProfiles.setLineWrap(true);
		txtInstagramProfiles.setColumns(9);

		JButton btnInstagramProfileScrape = new JButton("Start");

		btnInstagramProfileScrape.setBounds(452, 466, 252, 31);
		pnlInstagramInfo.add(btnInstagramProfileScrape);
		/* end of instagram panel-------------------------------------------------------------- */
		
		/* start of data display panel--------------------------------------------------------- */
		JPanel pnlDisplayJsonInfo = new JPanel();
		pnlDisplayJsonInfo.setLayout(null);
		pnlDisplayJsonInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.add(pnlDisplayJsonInfo, "pnlDisplayDataInfo");

		JLabel lblDataDisplayHeader = new JLabel("Display JSON");
		lblDataDisplayHeader.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblDataDisplayHeader.setBounds(37, 19, 384, 40);
		pnlDisplayJsonInfo.add(lblDataDisplayHeader);

		TextArea txaJsonContent = new TextArea();
		txaJsonContent.setBackground(Color.WHITE);
		txaJsonContent.setBounds(10, 178, 771, 499);
		pnlDisplayJsonInfo.add(txaJsonContent);

		JLabel lblJsonFilePath = new JLabel("File loaded: none");
		lblJsonFilePath.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblJsonFilePath.setBounds(18, 117, 707, 23);
		pnlDisplayJsonInfo.add(lblJsonFilePath);

		JLabel lblJsonFileLocation = new JLabel("File location");
		lblJsonFileLocation.setBounds(18, 92, 133, 23);
		pnlDisplayJsonInfo.add(lblJsonFileLocation);

		JButton btnJsonSelectFile = new JButton("Select file");

		btnJsonSelectFile.setBounds(18, 145, 131, 29);
		pnlDisplayJsonInfo.add(btnJsonSelectFile);

		JButton btnJsonSaveAs = new JButton("Save as");
		btnJsonSaveAs.setBounds(209, 145, 131, 29);
		pnlDisplayJsonInfo.add(btnJsonSaveAs);
		/* end of data display panel----------------------------------------------------------- */
		
		/* start of data analysis panel-------------------------------------------------------- */
		JPanel pnlDataAnalysisInfo = new JPanel();
		pnlDataAnalysisInfo.setLayout(null);
		pnlDataAnalysisInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.add(pnlDataAnalysisInfo, "pnlDataAnalysisInfo");

		JLabel lblDataAnalysisHeader = new JLabel("Data Analysis");
		lblDataAnalysisHeader.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblDataAnalysisHeader.setBounds(37, 19, 507, 40);
		pnlDataAnalysisInfo.add(lblDataAnalysisHeader);
		
		JLabel lblDataAnalysisNumPosts = new JLabel("Number of posts : ");
		lblDataAnalysisNumPosts.setBounds(18, 209, 289, 23);
		pnlDataAnalysisInfo.add(lblDataAnalysisNumPosts);

		JLabel lblDataAnalysisHashtag = new JLabel("Target hashtag : ");
		lblDataAnalysisHashtag.setBounds(423, 209, 359, 23);
		pnlDataAnalysisInfo.add(lblDataAnalysisHashtag);
		
		JLabel lblDataAnalysisAvgLikes = new JLabel("Avg. likes : ");
		lblDataAnalysisAvgLikes.setBounds(18, 251, 264, 23);
		pnlDataAnalysisInfo.add(lblDataAnalysisAvgLikes);

		JLabel lblDataAnalysisAvgHashtags = new JLabel("Avg. no. of hashtags : ");
		lblDataAnalysisAvgHashtags.setBounds(423, 251, 302, 23);
		pnlDataAnalysisInfo.add(lblDataAnalysisAvgHashtags);
		
		JLabel lblDataAnalysisAvgWords = new JLabel("Avg. no. words per post : ");
		lblDataAnalysisAvgWords.setBounds(18, 293, 321, 23);
		pnlDataAnalysisInfo.add(lblDataAnalysisAvgWords);
		
		JLabel lblDataAnalysisAvgChars = new JLabel("Avg. post length : ");
		lblDataAnalysisAvgChars.setBounds(423, 293, 315, 23);
		pnlDataAnalysisInfo.add(lblDataAnalysisAvgChars);

		JLabel lblDataAnalysisRelatedHashtags = new JLabel("Related hashtags");
		lblDataAnalysisRelatedHashtags.setBounds(18, 335, 174, 23);
		pnlDataAnalysisInfo.add(lblDataAnalysisRelatedHashtags);
		
		tableDataAnalysisRelatedHashtags = new JTable();
		tableDataAnalysisRelatedHashtags.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tableDataAnalysisRelatedHashtags.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Hashtag", "Avg. frequency", "Total frequency"
			}
		) {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, Float.class, Integer.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableDataAnalysisRelatedHashtags.getColumnModel().getColumn(0).setResizable(false);
		tableDataAnalysisRelatedHashtags.getColumnModel().getColumn(0).setPreferredWidth(263);
		tableDataAnalysisRelatedHashtags.getColumnModel().getColumn(0).setMinWidth(30);
		tableDataAnalysisRelatedHashtags.getColumnModel().getColumn(1).setResizable(false);
		tableDataAnalysisRelatedHashtags.getColumnModel().getColumn(1).setPreferredWidth(140);
		tableDataAnalysisRelatedHashtags.getColumnModel().getColumn(2).setResizable(false);
		tableDataAnalysisRelatedHashtags.getColumnModel().getColumn(2).setPreferredWidth(145);
		tableDataAnalysisRelatedHashtags.setBounds(18, 318, 373, 141);	
		JLabel lblWordmap = new JLabel("");
		lblWordmap.setHorizontalAlignment(SwingConstants.CENTER);
		lblWordmap.setForeground(Color.BLACK);
		lblWordmap.setBackground(Color.GREEN);
		lblWordmap.setBounds(526, 360, 256, 256);
		pnlDataAnalysisInfo.add(lblWordmap);
		
		JScrollPane scrollPaneDataAnalysisRelatedHashtags = new JScrollPane(tableDataAnalysisRelatedHashtags);
		scrollPaneDataAnalysisRelatedHashtags.setBounds(18, 360, 486, 300);
		pnlDataAnalysisInfo.add(scrollPaneDataAnalysisRelatedHashtags);

		JLabel lblDataAnalysisFilePath = new JLabel("File loaded: none");
		lblDataAnalysisFilePath.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblDataAnalysisFilePath.setBounds(18, 117, 707, 23);
		pnlDataAnalysisInfo.add(lblDataAnalysisFilePath);

		JLabel lblDataAnalysisFileLocation = new JLabel("File location");
		lblDataAnalysisFileLocation.setBounds(18, 92, 133, 23);
		pnlDataAnalysisInfo.add(lblDataAnalysisFileLocation);

		JButton btnDataAnalysisSelectFile = new JButton("Select file");
		btnDataAnalysisSelectFile.setBounds(18, 145, 131, 29);
		pnlDataAnalysisInfo.add(btnDataAnalysisSelectFile);
		
		JLabel lblDataAnalysisView = new JLabel("Click image to view");
		lblDataAnalysisView.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataAnalysisView.setBounds(526, 623, 256, 23);
		pnlDataAnalysisInfo.add(lblDataAnalysisView);
		lblDataAnalysisView.setVisible(false);
		/* end of data analysis panel---------------------------------------------------------- */
		
		/* start of twitter panel-------------------------------------------------------------- */
		JPanel pnlTwitterInfo = new JPanel();
		pnlTwitterInfo.setLayout(null);
		pnlTwitterInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.add(pnlTwitterInfo, "pnlTwitterInfo");

		JButton btnTwitterScrape = new JButton("Scrape by hashtags");
		btnTwitterScrape.setBounds(425, 269, 252, 31);
		pnlTwitterInfo.add(btnTwitterScrape);
		
		JButton btnTwitterScrapeProfile = new JButton("Scrape by profiles");
		btnTwitterScrapeProfile.setBounds(425, 423, 252, 31);
		pnlTwitterInfo.add(btnTwitterScrapeProfile);

		JLabel headerTwitterPanel = new JLabel("Twitter Scraper");
		headerTwitterPanel.setFont(new Font("Tahoma", Font.BOLD, 30));
		headerTwitterPanel.setBounds(37, 19, 300, 40);
		pnlTwitterInfo.add(headerTwitterPanel);

		JLabel lblTwitterHashtags = new JLabel("Hashtags");
		lblTwitterHashtags.setLocation(37, 191);
		lblTwitterHashtags.setSize(100, 20);
		pnlTwitterInfo.add(lblTwitterHashtags);
		
		JLabel lblTwitterProfiles = new JLabel("Profiles");
		lblTwitterProfiles.setLocation(37, 345);
		lblTwitterProfiles.setSize(100, 20);
		pnlTwitterInfo.add(lblTwitterProfiles);

		JTextArea txtTwitterHashtags = new JTextArea();
		txtTwitterHashtags.setRows(3);
		txtTwitterHashtags.setLineWrap(true);
		txtTwitterHashtags.setColumns(10);
		txtTwitterHashtags.setBounds(223, 370, 348, 78);
		pnlTwitterInfo.add(txtTwitterHashtags);
		
		JTextArea txtTwitterProfiles = new JTextArea();
		txtTwitterProfiles.setRows(3);
		txtTwitterProfiles.setLineWrap(true);
		txtTwitterProfiles.setColumns(10);
		txtTwitterProfiles.setBounds(223, 370, 348, 78);
		pnlTwitterInfo.add(txtTwitterProfiles);

		JScrollPane scrollPaneTwitterHashTags = new JScrollPane(txtTwitterHashtags);
		scrollPaneTwitterHashTags.setBounds(38, 220, 350, 80);
		pnlTwitterInfo.add(scrollPaneTwitterHashTags);
		
		JScrollPane scrollPaneTwitterProfiles = new JScrollPane(txtTwitterProfiles);
		scrollPaneTwitterProfiles.setBounds(38, 374, 350, 80);
		pnlTwitterInfo.add(scrollPaneTwitterProfiles);

		JLabel lblTwitterExport = new JLabel("Export location");
		lblTwitterExport.setBounds(37, 64, 133, 23);
		pnlTwitterInfo.add(lblTwitterExport);

		JLabel lblTwitterNumPosts = new JLabel("Number of posts");
		lblTwitterNumPosts.setBounds(425, 190, 194, 23);
		pnlTwitterInfo.add(lblTwitterNumPosts);

		JLabel lblTwitterExportPath = new JLabel("Path: none");
		lblTwitterExportPath.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblTwitterExportPath.setBounds(37, 89, 707, 23);
		pnlTwitterInfo.add(lblTwitterExportPath);

		JButton btnTwitterFile = new JButton("Select path");
		btnTwitterFile.setBounds(37, 117, 131, 29);
		pnlTwitterInfo.add(btnTwitterFile);
		
		JLabel lblHashtagModeTwitter = new JLabel("HashTag Mode");
		lblHashtagModeTwitter.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblHashtagModeTwitter.setBounds(37, 152, 300, 40);
		pnlTwitterInfo.add(lblHashtagModeTwitter);
		
		JLabel lblProfileModeTwitter = new JLabel("Profile Mode");
		lblProfileModeTwitter.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblProfileModeTwitter.setBounds(37, 310, 300, 40);
		pnlTwitterInfo.add(lblProfileModeTwitter);
		
		JLabel lblTwitterNumPosts_1 = new JLabel("Number of posts");
		lblTwitterNumPosts_1.setBounds(425, 344, 194, 23);
		pnlTwitterInfo.add(lblTwitterNumPosts_1);

		txtTwitterNumPosts = new JTextField();
		txtTwitterNumPosts.setColumns(10);
		txtTwitterNumPosts.setBounds(425, 215, 88, 29);
		pnlTwitterInfo.add(txtTwitterNumPosts);
		
		txtTwitterNumPostsProfile = new JTextField();
		txtTwitterNumPostsProfile.setColumns(10);
		txtTwitterNumPostsProfile.setBounds(425, 370, 88, 29);
		pnlTwitterInfo.add(txtTwitterNumPostsProfile);

		txaTwitterConsole = new JTextArea();
		txaTwitterConsole.setRows(4);
		txaTwitterConsole.setLineWrap(true);
		txaTwitterConsole.setEditable(false);
		txaTwitterConsole.setColumns(2);
		txaTwitterConsole.setBackground(Color.WHITE);
		txaTwitterConsole.setBounds(0, 0, 704, 138);
		pnlTwitterInfo.add(txaTwitterConsole);

		JScrollPane scrTwitterConsole = new JScrollPane(txaTwitterConsole);
		scrTwitterConsole.setBounds(38, 550, 706, 140);
		pnlTwitterInfo.add(scrTwitterConsole);

		JLabel lblTwitterConsole = new JLabel("Output");
		lblTwitterConsole.setBounds(37, 523, 82, 23);
		pnlTwitterInfo.add(lblTwitterConsole);
		/* end of twitter panel-------------------------------------------------------------- */
		
		/* start of visualization panel------------------------------------------------------ */
		JPanel pnlVisualisationInfo = new JPanel();
		pnlVisualisationInfo.setLayout(null);
		pnlVisualisationInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.add(pnlVisualisationInfo, "pnlVisualisationInfo");
		
		JLabel lblVisualisationHeader = new JLabel("Data visualisation");
		lblVisualisationHeader.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblVisualisationHeader.setBounds(37, 19, 384, 40);
		pnlVisualisationInfo.add(lblVisualisationHeader);
		
		JLabel lblVisulationFileLocation = new JLabel("File Location");
		lblVisulationFileLocation.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVisulationFileLocation.setBounds(8, 69, 160, 40);
		pnlVisualisationInfo.add(lblVisulationFileLocation);
			
		JLabel lblVisulationFilePath = new JLabel("Upload Json File Types");
		lblVisulationFilePath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlVisualisationInfo.add(lblVisulationFilePath);
		
		JButton btnVisulationPlotByLocation = new JButton("Visulation The Number Of Post Based On Location");
		btnVisulationPlotByLocation.setBackground(UIManager.getColor("InternalFrame.borderLight"));
		btnVisulationPlotByLocation.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVisulationPlotByLocation.setBounds(8,167,440,21);
		pnlVisualisationInfo.add(btnVisulationPlotByLocation);
		
		JButton btnVisulationPlotByMonth = new JButton("Visulation On One Hash Begin Used On Monthly Bases");
		btnVisulationPlotByMonth.setBackground(UIManager.getColor("InternalFrame.borderLight"));
		btnVisulationPlotByMonth.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVisulationPlotByMonth.setBounds(8,223,440,21);
		pnlVisualisationInfo.add(btnVisulationPlotByMonth);
		
		JButton btnVisulationSentimentAnalysis = new JButton("Sentiment Analysis against User Interactions");
		btnVisulationSentimentAnalysis.setBackground(UIManager.getColor("InternalFrame.borderLight"));
		btnVisulationSentimentAnalysis.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVisulationSentimentAnalysis.setBounds(8,279,440,21);
		pnlVisualisationInfo.add(btnVisulationSentimentAnalysis);
		
		JButton btnVisulationisSelectFile2 = new JButton("Generate One HashTag Used On Per-Day bases");
		btnVisulationisSelectFile2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVisulationisSelectFile2.setBackground(UIManager.getColor("InternalFrame.borderLight"));
		btnVisulationisSelectFile2.setBounds(8,373,440,21);
		pnlVisualisationInfo.add(btnVisulationisSelectFile2);

		JLabel lblVisualisationFilePath = new JLabel("File loaded: none");
		lblVisualisationFilePath.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVisualisationFilePath.setBounds(8, 108, 707, 23);
		pnlVisualisationInfo.add(lblVisualisationFilePath);
		
		JLabel lblNewLabel = new JLabel("Instagram:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(8, 136, 146, 21);
		pnlVisualisationInfo.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Twitter:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(8, 338, 119, 21);
		pnlVisualisationInfo.add(lblNewLabel_1);
		
		JCheckBox chkParseOcr = new JCheckBox("Use OCR Text for Analysis");
		chkParseOcr.setBackground(Color.LIGHT_GRAY);
		chkParseOcr.setBounds(465, 268, 267, 49);
		pnlVisualisationInfo.add(chkParseOcr);
		/* end of visualization panel-------------------------------------------------------- */
		
		/* start of about panel---------------------------------------------------------------*/
		JPanel pnlCreditsInfo = new JPanel();
		pnlCreditsInfo.setLayout(null);
		pnlCreditsInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.add(pnlCreditsInfo, "pnlCreditsInfo");
		
		JLabel lblCreditsHeader = new JLabel("Technologies Used");
		lblCreditsHeader.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblCreditsHeader.setBounds(200, 28, 418, 40);
		pnlCreditsInfo.add(lblCreditsHeader);
			
		JLabel lblIconJava = new JLabel("");
		lblIconJava.setBounds(16, 40, 300, 200);
		lblIconJava.setIcon(new ImageIcon(img_javaLogo));
		pnlCreditsInfo.add(lblIconJava);
		
		JLabel lblIconjSoup = new JLabel("");
		lblIconjSoup.setBounds(196, 40, 300, 200);
		lblIconjSoup.setIcon(new ImageIcon(img_jSoup));
		pnlCreditsInfo.add(lblIconjSoup);
		
		JLabel lblIconjSwing = new JLabel("");
		lblIconjSwing.setBounds(426, 33, 135, 200);
		lblIconjSwing.setIcon(new ImageIcon(img_javaSwing));
		pnlCreditsInfo.add(lblIconjSwing);
		
		
		JLabel lblIconSelenium = new JLabel("Selenium");
		lblIconSelenium.setBounds(596, 40, 300, 200);
		lblIconSelenium.setIcon(new ImageIcon(img_selenium));
		pnlCreditsInfo.add(lblIconSelenium);
		
		JLabel lblIconStandfordCoreNlp = new JLabel("");
		lblIconStandfordCoreNlp.setBounds(56, 155, 122, 200);
		lblIconStandfordCoreNlp.setIcon(new ImageIcon(img_standford));
		pnlCreditsInfo.add(lblIconStandfordCoreNlp);
		JLabel lblStandford = new JLabel("Standford CoreNLP");
		lblStandford.setLocation(60, 300);
		lblStandford.setSize(200, 20);
		pnlCreditsInfo.add(lblStandford);
			
		JLabel lblIconTwitter4j = new JLabel("");
		lblIconTwitter4j.setBounds(146, 155, 300, 200);
		lblIconTwitter4j.setIcon(new ImageIcon(img_twitter4j));
		pnlCreditsInfo.add(lblIconTwitter4j);
		
		JLabel lblIconMaven = new JLabel("");
		lblIconMaven.setBounds(416, 155, 200, 200);
		lblIconMaven.setIcon(new ImageIcon(img_maven));
		pnlCreditsInfo.add(lblIconMaven);
		
		JLabel lblIconTesseract = new JLabel("");
		lblIconTesseract.setBounds(596, 155, 200, 200);
		lblIconTesseract.setIcon(new ImageIcon(img_tesseract));
		pnlCreditsInfo.add(lblIconTesseract);
		
		JLabel lblCreditsHeader2 = new JLabel("Members");
		lblCreditsHeader2.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblCreditsHeader2.setBounds(315, 338, 192, 50);
		pnlCreditsInfo.add(lblCreditsHeader2);
		
		JLabel lblGroup = new JLabel("Group 34");
		lblGroup.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblGroup.setBounds(347, 401, 135, 40);
		pnlCreditsInfo.add(lblGroup);
		
		JLabel lblIconProfile = new JLabel("");
		lblIconProfile.setBounds(60, 453, 200, 200);
		lblIconProfile.setIcon(new ImageIcon(img_profilePic));
		pnlCreditsInfo.add(lblIconProfile);
		JLabel lblPatrick = new JLabel("PATRICK KANG (1902132)");
		lblPatrick.setLocation(60, 670);
		lblPatrick.setSize(200, 20);
		pnlCreditsInfo.add(lblPatrick);
		
		JLabel lblIconProfile1 = new JLabel("");
		lblIconProfile1.setBounds(240, 453, 200, 200);
		lblIconProfile1.setIcon(new ImageIcon(img_profile_john));
		pnlCreditsInfo.add(lblIconProfile1);
		JLabel lblJohn = new JLabel("JOHN LEONG (1902605)");
		lblJohn.setLocation(240, 670);
		lblJohn.setSize(200, 20);
		pnlCreditsInfo.add(lblJohn);
			
		JLabel lblIconProfile2 = new JLabel("");
		lblIconProfile2.setBounds(420, 453, 200, 200);
		lblIconProfile2.setIcon(new ImageIcon(img_profile_damon));
		pnlCreditsInfo.add(lblIconProfile2);
		JLabel lblDamon = new JLabel("DAMON ANG (1902707)");
		lblDamon.setLocation(420, 670);
		lblDamon.setSize(200, 20);
		pnlCreditsInfo.add(lblDamon);
		
		JLabel lblIconProfile3 = new JLabel("");
		lblIconProfile3.setBounds(600, 453, 200, 200);
		lblIconProfile3.setIcon(new ImageIcon(img_profile_aaron));
		pnlCreditsInfo.add(lblIconProfile3);
		JLabel lblAaron = new JLabel("AARON CHUA (1902146)");
		lblAaron.setLocation(600, 670);
		lblAaron.setSize(200, 20);
		pnlCreditsInfo.add(lblAaron);
		/* end of about panel-----------------------------------------------------------------*/
		
		/* start of OCR panel-----------------------------------------------------------------*/
		JPanel pnlOcrInfo = new JPanel();
		pnlOcrInfo.setLayout(null);
		pnlOcrInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.add(pnlOcrInfo, "pnlOcrInfo");
		
		JLabel lblOcrHeader = new JLabel("Optimal Character Recognition");
		lblOcrHeader.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblOcrHeader.setBounds(37, 19, 583, 40);
		pnlOcrInfo.add(lblOcrHeader);	
		
		JButton btnOcrSelectJson = new JButton("Select file");	
		btnOcrSelectJson.setBounds(37, 254, 131, 29);
		pnlOcrInfo.add(btnOcrSelectJson);
		
		JLabel lblOcrJsonFilePath = new JLabel("File loaded: none");
		lblOcrJsonFilePath.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblOcrJsonFilePath.setBounds(37, 218, 707, 23);
		pnlOcrInfo.add(lblOcrJsonFilePath);
		
		JLabel lblOcrJsonFileLocation = new JLabel("File location");
		lblOcrJsonFileLocation.setBounds(37, 193, 133, 23);
		pnlOcrInfo.add(lblOcrJsonFileLocation);
		
		JLabel lblOcrDescription = new JLabel("<html>*This function is currently only compatible with <font color='red'>Instagram </font>scraped Data. For every post in the JSON file, an ocr_text field will be appended to it whenever text extraction is possible. </html>");
		lblOcrDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblOcrDescription.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblOcrDescription.setBounds(37, 64, 688, 96);
		pnlOcrInfo.add(lblOcrDescription);
		
		JButton btnAppendOcr = new JButton("Execute");
		btnAppendOcr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (lblOcrJsonFilePath.getText() == "File loaded: none") {
					msgbox("Please choose JSON file before executing\n");
				} else {
					try {
						OCRUtility ocr = new OCRUtility();
						String filePath = lblOcrJsonFilePath.getText().replace("File loaded: ", "");
						long res = ocr.parseJsonAndAppendImageText(filePath);
						System.out.println(res);
						msgbox(res + " text images has appended to JSON file.\n");
					} catch (JSONException | IOException e1) {
						e1.printStackTrace();
						msgbox("Error reading JSON file.\n");
					}
				}
			}
		});
		btnAppendOcr.setBounds(37, 300, 197, 47);
		pnlOcrInfo.add(btnAppendOcr);
		/* end of OCR panel-------------------------------------------------------------------*/
		
		/* Add all button events--------------------------------------------------------------*/
		this.addSidePanelEvents();
		btnInstagramFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser(); 
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Select export path");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);			    
				chooser.showSaveDialog(null);

				lblInstagramExportPath.setText("Path: " + chooser.getSelectedFile().getAbsolutePath());
				lblTwitterExportPath.setText("Path: " + chooser.getSelectedFile().getAbsolutePath());
				exportPath = chooser.getSelectedFile().getAbsolutePath();
			}
		});

		btnTwitterFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser(); 
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Select export path");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);			    
				chooser.showSaveDialog(null);

				lblInstagramExportPath.setText("Path: " + chooser.getSelectedFile().getAbsolutePath());
				lblTwitterExportPath.setText("Path: " + chooser.getSelectedFile().getAbsolutePath());
				exportPath = chooser.getSelectedFile().getAbsolutePath();
			}
		});

		btnInstagramHashTagScrape.addMouseListener(new MouseAdapter() {
			@Override		
			public void mouseClicked(MouseEvent arg0) {
				//Check for valid fields
				if(txtInstagramUsername.getText().length() < 1) {
					txaInstagramConsole.append("*Please enter a username\n");
					return;
				}
				if(txtInstagramPassword.getPassword().length < 1) {
					txaInstagramConsole.append("*Please enter a password\n");
					return;
				}
				if(exportPath.length() < 1) {
					txaInstagramConsole.append("*Please choose an export path\n");
					return;				
				}

				//Check for number of posts
				int numPosts;
				try {
					numPosts = Integer.parseInt(txtHashTagNumPosts.getText());
				}
				catch (Exception e) {
					txaInstagramConsole.append("*Invalid amount of posts to scrape\n");
					return;
				}

				txaInstagramConsole.append("*Scraping HashTags procedure started\n");
				ScrapeUtility scrapper = new InstagramScraper("https://www.instagram.com/accounts/login/");
				@SuppressWarnings("deprecation")
				ScrapeCode result = scrapper.scrapeByHashTags(txtInstagramUsername.getText()
						, txtInstagramPassword.getText()
						, txtInstagramHashtags.getText()
						, numPosts
						, exportPath);
				txaInstagramConsole.append("*" + result.getDescription());
			}
		});

		btnInstagramProfileScrape.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(txtInstagramUsername.getText().length() < 1) {
					txaInstagramConsole.append("*Please enter a username\n");
					return;
				}
				if(txtInstagramPassword.getPassword().length < 1) {
					txaInstagramConsole.append("*Please enter a password\n");
					return;
				}
				if(exportPath.length() < 1) {
					txaInstagramConsole.append("*Please choose an export path\n");
					return;				
				}

				//Check for number of posts
				int numPosts;
				try {
					numPosts = Integer.parseInt(txtProfileNumberOfPosts.getText());
				}
				catch (Exception e) {
					txaInstagramConsole.append("*Invalid amount of profiles to scrape\n");
					return;
				}

				//				textAreaInstagramConsole.append("*Scraping for profile details: " + hashTag + "\n");
				txaInstagramConsole.append("*Scraping profiles procedure started\n");

				InstagramScraper scrapper = new InstagramScraper("https://www.instagram.com/accounts/login/");

				@SuppressWarnings("deprecation")
				ScrapeCode result = scrapper.scrapeByProfiles(txtInstagramUsername.getText()
						, txtInstagramPassword.getText()
						, txtInstagramProfiles.getText()
						, numPosts
						, exportPath);

				txaInstagramConsole.append("*" + result.getDescription());				
			}
		});

		btnTwitterScrape.addMouseListener(new MouseAdapter() {
			@Override		
			public void mouseClicked(MouseEvent arg0) {
				//Check for valid fields
				if(exportPath.length() < 1) {
					txaTwitterConsole.append("*Please choose an export path\n");
					return;				
				}

				//Check for number of posts
				int numPosts;
				try {
					numPosts = Integer.parseInt(txtTwitterNumPosts.getText());
				}
				catch (Exception e) {
					txaTwitterConsole.append("*Invalid amount of posts to scrape\n");
					return;
				}

				txaTwitterConsole.append("*Scraping for hashtags: " + txtTwitterHashtags.getText() + "\n");
				ScrapeUtility scrapper = new TwitterScraper("test");
				ScrapeCode result = scrapper.scrapeByHashTags("nil"
						, "nil"
						, txtTwitterHashtags.getText()
						, numPosts
						, exportPath);
				txaTwitterConsole.append("*" + result.getDescription());
			}
		});
		btnTwitterScrapeProfile.addMouseListener(new MouseAdapter() {
			@Override		
			public void mouseClicked(MouseEvent arg0) {
				//Check for valid fields
				if(exportPath.length() < 1) {
					txaTwitterConsole.append("*Please choose an export path\n");
					return;				
				}

				//Check for number of posts
				int numPosts;
				try {
					numPosts = Integer.parseInt(txtTwitterNumPostsProfile.getText());
				}
				catch (Exception e) {
					txaTwitterConsole.append("*Invalid amount of posts to scrape\n");
					return;
				}

				txaTwitterConsole.append("*Scraping for profiles: " + txtTwitterProfiles.getText() + "\n");
				ScrapeUtility scrapper = new TwitterScraper("test");
				ScrapeCode result = scrapper.scrapeByProfiles("nil"
						, "nil"
						, txtTwitterProfiles.getText()
						, numPosts
						, exportPath);
				txaTwitterConsole.append("*" + result.getDescription());
			}
		});
		
		/* Start of visualization button events */
		// Visulation The Number Of Post Based on Location Instagram PieChart.
		btnVisulationPlotByLocation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("Data files", "JSON", "json", "txt", "TXT"));
				// optionally set chooser options ...
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					try {
						lblVisualisationFilePath.setText("File loaded: " + file.getPath());
						
						
						InstagramPieChart jsonf = new InstagramPieChart("");
						jsonf.ReadingJson(file.getPath());
						
						InstagramPieChart jChart = new InstagramPieChart("Instagram Post Based On Location");
						jChart.setSize( 560 , 367 );
						RefineryUtilities.centerFrameOnScreen( jChart );    
					    jChart.setVisible( true );
					    
					} catch (Exception e) {
						msgbox("Unable to load file\n");
						e.printStackTrace();
					}

				}
			}
		});
		//Visulation Base on One HashTag Used On Per-Day bases. Twitter Barchart
		btnVisulationisSelectFile2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("Data files", "JSON", "json", "txt", "TXT"));
				// optionally set chooser options ...
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					try {
						lblVisualisationFilePath.setText("File loaded: " + file.getPath());
						TwitterBarChart jsonf = new TwitterBarChart("","");
						jsonf.ReadingJson(file.getPath());
					    TwitterBarChart chart = new TwitterBarChart("Twitter HashTag Statistics", 
					 	         "Weekly Base HashTag Statistics"); 	      
				 	    chart.pack();        
				 	    RefineryUtilities.centerFrameOnScreen(chart);        
					 	chart.setVisible( true ); 
					} catch (Exception e) {
						msgbox("Unable to load file\n");
						e.printStackTrace();
					}

				}
			}
		});
		//Visulation Base on how many times one hash begin used on a monthly bases. Instagram BarChart
		btnVisulationPlotByMonth.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("Data files", "JSON", "json", "txt", "TXT"));
				// optionally set chooser options ...
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					try {
						lblVisualisationFilePath.setText("File loaded: " + file.getPath());
						  InstagramBarChart rJson = new InstagramBarChart(" "," ");
						  rJson.ReadingJson(file.getPath());
					      InstagramBarChart chart = new InstagramBarChart("Instagram Monthly Base HashTag Statistics", 
					         "Instagram HashTag Statistics"); 	
					      chart.pack();        
					      RefineryUtilities.centerFrameOnScreen( chart );        
					      chart.setVisible( true );
					      
					} catch (Exception e) {
						msgbox("Unable to load file\n");
						e.printStackTrace();
					}

				}
			}
		});
		//Visulation to check Positive/Neutural/Negative base on comment.
		btnVisulationSentimentAnalysis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("Data files", "JSON", "json", "txt", "TXT"));
				// optionally set chooser options ...
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					try {
						InstagramSentimentAnalyzer obj = new InstagramSentimentAnalyzer();
						HashMap<String, Integer> results = obj.getInstagramSentimentResults(file.getPath(), chkParseOcr.isSelected());
						// Edit from here onwards
								
						lblVisualisationFilePath.setText("File loaded: " + file.getPath());
						InstagramLineGraph jsonf = new InstagramLineGraph("");
						jsonf.ReadingJson(file.getPath());
					    SwingUtilities.invokeLater(() -> {  
					      InstagramLineGraph example = new InstagramLineGraph("Line Chart");  
					      example.setAlwaysOnTop(true);  
					      example.pack();  
					      example.setSize(600, 400);    
					      example.setVisible(true);  
					    });  
					} catch (Exception e) {
						msgbox("Unable to load file\n");
						e.printStackTrace();
					}

				}
			}
		});
		/* End of visualization button events */
		
		btnJsonSelectFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("Data files", "JSON", "json", "txt", "TXT"));
				// optionally set chooser options ...
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					String displayOutput;
					try {
						displayOutput = JSONUtility
								.prettifyJSON(JSONUtility.parseJSONToString(file.getPath()));
						txaJsonContent.setText(displayOutput);
						lblJsonFilePath.setText("File loaded: " + file.getPath());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						msgbox("Unable to load file\n");
						e.printStackTrace();
					}

				}


			}
		});
		btnJsonSaveAs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {						
						FileHelper.writeStringToPath(file.getPath(), JSONUtility
								.unPrettifyJSON(txaJsonContent.getText()));
					} catch (FileNotFoundException e) {
						msgbox(e.getMessage());
						return;
					} finally {
						msgbox("Successfully saved text to: " + file.getPath());
					}					
				}
			}
		});

		//Data analysis select file
		btnDataAnalysisSelectFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("Data files", "JSON", "json", "txt", "TXT"));
				// optionally set chooser options ...
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					try {
						AnalysedDataProperties analysedData = DataAnalyser.AnalyseData(file.getPath());
						lblDataAnalysisFilePath.setText("File loaded: " + file.getPath());
						
						//Set analyzed properties to GUI
						lblDataAnalysisNumPosts.setText("Number of posts : " + analysedData.getNumberOfPosts());
						if (analysedData.getScrapeType().equals("hashtags"))
							lblDataAnalysisHashtag.setText("Target hashtag(s) : " + analysedData.getTargetHashtag());
						else
							lblDataAnalysisHashtag.setText("Target profile(s) : " + analysedData.getTargetHashtag());
						lblDataAnalysisAvgHashtags.setText("Avg. no. of hashtags : " + analysedData.getAvgHashtags());
						lblDataAnalysisAvgLikes.setText("Avg. likes : " + analysedData.getAvgLikes());
						lblDataAnalysisAvgWords.setText("Avg. words per post : " + analysedData.getAvgWords());
						lblDataAnalysisAvgChars.setText("Avg. post length : " + analysedData.getAvgCharacters() + " characters");
						
						DefaultTableModel hashtagTableModel = (DefaultTableModel)tableDataAnalysisRelatedHashtags.getModel();
						
						// Clear old data
						for (int i = hashtagTableModel.getRowCount() - 1; i > -1; --i) {
							hashtagTableModel.removeRow(i);
						}

						// Sort related hashtag hashmap
						List<HashMap.Entry<String, Integer>> relatedHashtags = new ArrayList<HashMap.Entry<String, Integer>>(
								analysedData.getRelatedHashtags().entrySet());
						Collections.sort(relatedHashtags, new Comparator<HashMap.Entry<String, Integer>>() {
							@Override
							public int compare(HashMap.Entry<String, Integer> a, HashMap.Entry<String, Integer> b) {
								return Integer.compare(b.getValue(), a.getValue());
							}
						});
						
						//Populate table with analyzed data
						for (HashMap.Entry<String, Integer> entry : relatedHashtags) {
							hashtagTableModel.addRow(new Object[] {entry.getKey(), (float)entry.getValue() / analysedData.getNumberOfPosts(), entry.getValue()});
						}

						Image wordMapImg = new ImageIcon(Paths.get("").toAbsolutePath().toString() + "/wordmap.png").getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH);
						lblWordmap.setIcon(new ImageIcon(wordMapImg));
						lblDataAnalysisView.setVisible(true);
						wordmapGenerated = true;
						
					} catch (Exception e) {
						msgbox("Unable to load file\n");
						e.printStackTrace();
					}

				}
			}
		});
		
		btnOcrSelectJson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("Data files", "JSON", "json", "txt", "TXT"));
				// optionally set chooser options ...
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					lblOcrJsonFilePath.setText("File loaded: " + chooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		
		
		lblWordmap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!wordmapGenerated)
					return;
				try {
					Desktop dsk = Desktop.getDesktop();
					dsk.open(new File(Paths.get("").toAbsolutePath().toString() + "/wordmap.png"));
				} catch (Exception e) {
					msgbox("Unable to view wordmap\n");
					e.printStackTrace();
				}
			}
		});
		
		//Set initial selected panel
		selectedSidePanel = pnlInstagram;
		changeSelectedPanelColor(pnlInstagram);
		lblIconInstagram.setIcon(new ImageIcon(img_instagram_hover));
	}
}
