import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
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

public class FrameDashboard extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private Image img_logo = new ImageIcon(FrameDashboard.class.getResource("resource/sit.png")).getImage().getScaledInstance(200, 90, Image.SCALE_SMOOTH);
	private Image img_facebook = new ImageIcon(FrameDashboard.class.getResource("resource/facebook.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_instagram = new ImageIcon(FrameDashboard.class.getResource("resource/instagram.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_instagram_hover = new ImageIcon(FrameDashboard.class.getResource("resource/instagram_hover.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_twitter = new ImageIcon(FrameDashboard.class.getResource("resource/twitter.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_twitter_hover = new ImageIcon(FrameDashboard.class.getResource("resource/twitter_hover.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_analysis = new ImageIcon(FrameDashboard.class.getResource("resource/eye.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_analysis_hover = new ImageIcon(FrameDashboard.class.getResource("resource/eye_hover.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_visualise = new ImageIcon(FrameDashboard.class.getResource("resource/search.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_visualise_hover = new ImageIcon(FrameDashboard.class.getResource("resource/search_hover.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_json = new ImageIcon(FrameDashboard.class.getResource("resource/json.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_json_hover = new ImageIcon(FrameDashboard.class.getResource("resource/json_hover.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	
	private Image img_about = new ImageIcon(FrameDashboard.class.getResource("resource/about.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_about_hover = new ImageIcon(FrameDashboard.class.getResource("resource/about_hover.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	
	private JTextField txtInstagramUsername;
	private JPasswordField txtInstagramPassword;
	private JTextArea txtInstagramHashtags;
	private JTextField txtHashTagNumPosts;
	private static JTextArea txaInstagramConsole;
	private JTextArea txaTwitterConsole;

	private String exportPath = "";
	private JTextField txtTwitterNumPosts;
	private JTextField txtProfileNumberOfPosts;

	private JLabel lblIconSit;

	private JPanel pnlSideMenu;	private JPanel pnlOptions;
	private JPanel pnlInstagram;
	private JPanel pnlTwitter;
	private JPanel pnlDisplayJson;
	private JPanel pnlDataAnalysis;
	private JPanel pnlVisualisation;
	private JPanel pnlAbout;

	private JLabel lblInstagramMode; private JLabel lblIconInstagram;
	private JLabel lblTwitterMode; private JLabel lblIconTwitter;
	private JLabel lblDisplayJsonMode; private JLabel lblIconJson;
	private JLabel lblDataAnalysisMode;	private JLabel lblIconDataAnalysis;
	private JLabel lblVisualisationMode; private JLabel lblIconVisualisation;

	private JLabel lblAbout; private JLabel lblIconAbout;


	private JPanel pnlInfo;
	
	private JPanel selectedSidePanel;
	private JTable tableDataAnalysisRelatedHashtags;
	
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


	private void changeSelectedPanelColor(JPanel panel) {
		panel.setBackground(new Color(0.3f, 0.3f, 0.3f)); //Set to light grey		
	}

	private void resetSidePanelsColor(JPanel[] panels) {
		for (int i = 0; i < panels.length; ++i) {
			if (panels[i] != selectedSidePanel) {
				panels[i].setBackground(new Color(0.2f, 0.2f, 0.2f));
			}
		}
	}
	
	private void msgbox(String s){
		JOptionPane.showMessageDialog(null, s);
	}

	public static void appendInstagramConsole(String message) {
		txaInstagramConsole.append(message);
	}

	
	private void resetAllPanelIcons() {
		lblIconInstagram.setIcon(new ImageIcon(img_instagram));
		lblIconTwitter.setIcon(new ImageIcon(img_twitter));
		lblIconJson.setIcon(new ImageIcon(img_json));
		lblIconVisualisation.setIcon(new ImageIcon(img_visualise));
		lblIconDataAnalysis.setIcon(new ImageIcon(img_analysis));
		lblIconAbout.setIcon(new ImageIcon(img_about));


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
		

		pnlAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedSidePanel = pnlAbout;
				resetSidePanelsColor(sidePanels);
				resetAllPanelIcons();
				
				changeSelectedPanelColor(pnlAbout);
				lblIconAbout.setIcon(new ImageIcon(img_about_hover));
				
				//Change after the panel done
//				CardLayout card = (CardLayout)pnlInfo.getLayout();
//				card.show(pnlInfo, "pnlVisualisationInfo");
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
	 * Create the frame.
	 */
	public FrameDashboard() {
//		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1041, 764);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pnlSideMenu = new JPanel();
		pnlSideMenu.setBounds(0, 0, 237, 868);
		contentPane.add(pnlSideMenu);
		pnlSideMenu.setLayout(null);

		lblIconSit = new JLabel("");
		lblIconSit.setBounds(15, 19, 205, 86);
		lblIconSit.setIcon(new ImageIcon(img_logo));
		pnlSideMenu.add(lblIconSit);

		pnlOptions = new JPanel();
		pnlOptions.setBackground(new Color(102, 102, 102));
		pnlOptions.setBounds(25, 138, 236, 753);
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
		pnlInformation.setBounds(0, 301, 236, 44);
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
		pnlAbout.setBounds(0, 345, 236, 53);
		pnlOptions.add(pnlAbout);
		
		lblAbout = new JLabel("About");
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setForeground(Color.WHITE);
		lblAbout.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAbout.setBackground(Color.WHITE);
		lblAbout.setBounds(58, 15, 97, 25);
		pnlAbout.add(lblAbout);
		
		lblIconAbout = new JLabel("");
		lblIconAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconAbout.setForeground(Color.WHITE);
		lblIconAbout.setBackground(Color.WHITE);
		lblIconAbout.setBounds(18, 7, 39, 41);
		lblIconAbout.setIcon(new ImageIcon(img_about));
		pnlAbout.add(lblIconAbout);

		pnlInfo = new JPanel();
		pnlInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.setBounds(236, 0, 799, 721);
		pnlInfo.setLayout(new CardLayout(0, 0));
		contentPane.add(pnlInfo);
		//CardLayout cardInfo = (CardLayout)pnlInfo.getLayout();

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

		JPanel pnlDisplayJsonInfo = new JPanel();
		pnlDisplayJsonInfo.setLayout(null);
		pnlDisplayJsonInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.add(pnlDisplayJsonInfo, "pnlDisplayDataInfo");

		JLabel lblDataDisplayHeader = new JLabel("Data display header here");
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
		
		JPanel pnlDataAnalysisInfo = new JPanel();
		pnlDataAnalysisInfo.setLayout(null);
		pnlDataAnalysisInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.add(pnlDataAnalysisInfo, "pnlDataAnalysisInfo");

		JLabel lblDataAnalysisHeader = new JLabel("Data analysis header here");
		lblDataAnalysisHeader.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblDataAnalysisHeader.setBounds(37, 19, 384, 40);
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
			Class[] columnTypes = new Class[] {
				String.class, Float.class, Integer.class
			};
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
		//pnlDataAnalysisInfo.add(tableDataAnalysisRelatedHashtags)		
		JLabel lblWordmap = new JLabel("");
		lblWordmap.setHorizontalAlignment(SwingConstants.CENTER);
		lblWordmap.setForeground(Color.BLACK);
		lblWordmap.setBackground(Color.GREEN);
		lblWordmap.setBounds(526, 360, 256, 256);
		pnlDataAnalysisInfo.add(lblWordmap);
;
		
		JScrollPane scrollPaneDataAnalysisRelatedHashtags = new JScrollPane(tableDataAnalysisRelatedHashtags);
		scrollPaneDataAnalysisRelatedHashtags.setBounds(18, 360, 486, 141);
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

		JPanel pnlTwitterInfo = new JPanel();
		pnlTwitterInfo.setLayout(null);
		pnlTwitterInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.add(pnlTwitterInfo, "pnlTwitterInfo");

		JButton btnTwitterScrape = new JButton("Launch twitter scraper");
		btnTwitterScrape.setBounds(37, 397, 252, 31);
		pnlTwitterInfo.add(btnTwitterScrape);

		JLabel headerTwitterPanel = new JLabel("Twitter Scraper");
		headerTwitterPanel.setFont(new Font("Tahoma", Font.BOLD, 30));
		headerTwitterPanel.setBounds(37, 19, 300, 40);
		pnlTwitterInfo.add(headerTwitterPanel);

		JLabel lblTwitterHashtags = new JLabel("Hashtags");
		lblTwitterHashtags.setBounds(37, 78, 82, 23);
		pnlTwitterInfo.add(lblTwitterHashtags);

		JTextArea txtTwitterHashtags = new JTextArea();
		txtTwitterHashtags.setRows(3);
		txtTwitterHashtags.setLineWrap(true);
		txtTwitterHashtags.setColumns(10);
		txtTwitterHashtags.setBounds(0, 0, 348, 78);
		pnlTwitterInfo.add(txtTwitterHashtags);

		JScrollPane scrollPaneTwitterHashTags = new JScrollPane(txtTwitterHashtags);
		scrollPaneTwitterHashTags.setBounds(38, 107, 350, 80);
		pnlTwitterInfo.add(scrollPaneTwitterHashTags);

		JLabel lblTwitterExport = new JLabel("Export location");
		lblTwitterExport.setBounds(37, 201, 133, 23);
		pnlTwitterInfo.add(lblTwitterExport);

		JLabel lblTwitterNumPosts = new JLabel("Number of posts");
		lblTwitterNumPosts.setBounds(443, 78, 194, 23);
		pnlTwitterInfo.add(lblTwitterNumPosts);

		JLabel lblTwitterExportPath = new JLabel("Path: none");
		lblTwitterExportPath.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblTwitterExportPath.setBounds(37, 226, 707, 23);
		pnlTwitterInfo.add(lblTwitterExportPath);

		JButton btnTwitterFile = new JButton("Select path");
		btnTwitterFile.setBounds(37, 254, 131, 29);
		pnlTwitterInfo.add(btnTwitterFile);
		
		JLabel lblHashtagModeTwitter = new JLabel("HashTag Mode");
		lblHashtagModeTwitter.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblHashtagModeTwitter.setBounds(42, 0, 300, 40);
		pnlTwitterInfo.add(lblHashtagModeTwitter);

		txtTwitterNumPosts = new JTextField();
		txtTwitterNumPosts.setColumns(10);
		txtTwitterNumPosts.setBounds(443, 105, 88, 29);
		pnlTwitterInfo.add(txtTwitterNumPosts);

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
				
		/* start of visualisation panel*/
		JPanel pnlVisualisationInfo = new JPanel();
		pnlVisualisationInfo.setLayout(null);
		pnlVisualisationInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.add(pnlVisualisationInfo, "pnlVisualisationInfo");
		
		JLabel lblVisualisationHeader = new JLabel("Data visualisation");
		lblVisualisationHeader.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblVisualisationHeader.setBounds(37, 19, 384, 40);
		pnlVisualisationInfo.add(lblVisualisationHeader);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(65, 152, 48, 14);
		pnlVisualisationInfo.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ngtregtr");
		lblNewLabel_1.setBounds(95, 212, 48, 14);
		pnlVisualisationInfo.add(lblNewLabel_1);
		/* end of visualisation panel*/
		
		/* start of credits panel*/
		JPanel pnlCreditsInfo = new JPanel();
		pnlCreditsInfo.setLayout(null);
		pnlCreditsInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.add(pnlCreditsInfo, "pnlCreditsInfo");
		
		JLabel lblCreditsHeader = new JLabel("Credits");
		lblCreditsHeader.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblCreditsHeader.setBounds(37, 19, 384, 40);
		pnlCreditsInfo.add(lblCreditsHeader);
		/* end of credits panel*/
		
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
				/**
				 * Final Gui shld have the following:
				 * -Label to explain scraping process
				 * [Login] id(txtBox), pwd(txtBox) 	<Check if fields are empty before executing>
				 * [Hashtag] keyword(txtBox) 		<Check if fields are empty before executing>
				 * [JSON]	ExportPath(txtBox with placeholder)	<check if empty and valid path>
				 * -Label for precautions (such as need FireFox, and valid instagram acc)   
				 */

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
				ReturnCode result = scrapper.scrapeByHashTags(txtInstagramUsername.getText()
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

				ReturnCode result = scrapper.scrapeByProfiles(txtInstagramUsername.getText()
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

				//Check for hashtags
				//String[] hashTags = txtTwitterHashtags.getText().split("\\s+");

				txaTwitterConsole.append("*Scraping for hashtags: " + txtTwitterHashtags.getText() + "\n");
				ScrapeUtility scrapper = new TwitterScraper("test");
				ReturnCode result = scrapper.scrapeByHashTags("nil"
						, "nil"
						, txtTwitterHashtags.getText()
						, numPosts
						, exportPath);
//				ReturnCode result = scrapper.scrapeByProfiles("nil"
//						, "nil"
//						, txtTwitterHashtags.getText()
//						, numPosts
//						, exportPath);
				txaTwitterConsole.append("*" + result.getDescription());
			}
		});

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
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						msgbox("Unable to load file\n");
						e.printStackTrace();
					}

				}


			}
		});
	}
}
