import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.SystemColor;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JEditorPane;
import java.awt.ScrollPane;
import java.awt.TextArea;

public class FrameDashboard extends JFrame {


	private JPanel contentPane;

	private Image img_logo = new ImageIcon(FrameDashboard.class.getResource("resource/sit.png")).getImage().getScaledInstance(200, 90, Image.SCALE_SMOOTH);
	private Image img_facebook = new ImageIcon(FrameDashboard.class.getResource("resource/facebook.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_instagram = new ImageIcon(FrameDashboard.class.getResource("resource/instagram.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_instagram_hover = new ImageIcon(FrameDashboard.class.getResource("resource/instagram_hover.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_twitter = new ImageIcon(FrameDashboard.class.getResource("resource/twitter.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_twitter_hover = new ImageIcon(FrameDashboard.class.getResource("resource/twitter_hover.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_analysis = new ImageIcon(FrameDashboard.class.getResource("resource/eye.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_visualise = new ImageIcon(FrameDashboard.class.getResource("resource/search.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_json = new ImageIcon(FrameDashboard.class.getResource("resource/json.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_json_hover = new ImageIcon(FrameDashboard.class.getResource("resource/json_hover.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

	private JTextField textFieldInstagramUsername;
	private JPasswordField textFieldInstagramPassword;
	private JTextArea textFieldInstagramHashtags;
	private JTextField textFieldNumPosts;
	private JTextArea textAreaInstagramConsole;
	private JTextArea textAreaTwitterConsole;

	private String exportPath = "";
	private JTextField textFieldTwitterNumPosts;

	/**
	 * Launch the application.
	 */
	public static void launchGui(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
	
	private void msgbox(String s){
		JOptionPane.showMessageDialog(null, s);
	}

	/**
	 * Create the frame.
	 */
	public FrameDashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1040, 703);
//		setUndecorated(true);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlSideMenu = new JPanel();
		pnlSideMenu.setBounds(0, 0, 237, 832);
		contentPane.add(pnlSideMenu);
		pnlSideMenu.setLayout(null);

		JLabel lblIconSit = new JLabel("");
		lblIconSit.setBounds(15, 19, 205, 86);
		lblIconSit.setIcon(new ImageIcon(img_logo));
		pnlSideMenu.add(lblIconSit);

		JPanel pnlOptions = new JPanel();
		pnlOptions.setBackground(new Color(102, 102, 102));
		pnlOptions.setBounds(0, 133, 236, 905);
		pnlSideMenu.add(pnlOptions);
		pnlOptions.setLayout(null);

		JPanel pnlInstagram = new JPanel();
		pnlInstagram.setBorder(null);
		pnlInstagram.setBackground(new Color(51, 51, 51));
		pnlInstagram.setBounds(0, 0, 236, 53);
		pnlOptions.add(pnlInstagram);
		pnlInstagram.setLayout(null);

		JLabel lblInstagramMode = new JLabel("Instagram");
		lblInstagramMode.setForeground(new Color(255, 255, 255));
		lblInstagramMode.setBackground(new Color(255, 255, 255));
		lblInstagramMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstagramMode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInstagramMode.setBounds(60, 15, 117, 25);
		pnlInstagram.add(lblInstagramMode);

		JLabel lblIconInstagram = new JLabel("");
		lblIconInstagram.setBounds(18, 7, 39, 41);
		pnlInstagram.add(lblIconInstagram);
		lblIconInstagram.setForeground(new Color(255, 255, 255));
		lblIconInstagram.setBackground(new Color(255, 255, 255));
		lblIconInstagram.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconInstagram.setIcon(new ImageIcon(img_instagram));

		JPanel pnlTwitter = new JPanel();
		pnlTwitter.setLayout(null);
		pnlTwitter.setBorder(null);
		pnlTwitter.setBackground(new Color(51, 51, 51));
		pnlTwitter.setBounds(0, 51, 236, 53);
		pnlOptions.add(pnlTwitter);

		JLabel lblTwitterMode = new JLabel("Twitter");
		lblTwitterMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblTwitterMode.setForeground(Color.WHITE);
		lblTwitterMode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTwitterMode.setBackground(Color.WHITE);
		lblTwitterMode.setBounds(60, 15, 117, 25);
		pnlTwitter.add(lblTwitterMode);

		JLabel lblIconTwitter = new JLabel("");
		lblIconTwitter.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconTwitter.setForeground(Color.WHITE);
		lblIconTwitter.setBackground(Color.WHITE);
		lblIconTwitter.setBounds(18, 7, 39, 41);
		lblIconTwitter.setIcon(new ImageIcon(img_twitter));
		pnlTwitter.add(lblIconTwitter);

		JPanel pnlDataAnalysis = new JPanel();
		pnlDataAnalysis.setLayout(null);
		pnlDataAnalysis.setBorder(null);
		pnlDataAnalysis.setBackground(new Color(51, 51, 51));
		pnlDataAnalysis.setBounds(0, 154, 236, 53);
		pnlOptions.add(pnlDataAnalysis);

		JLabel lblDataAnalysisMode = new JLabel("Data Analysis");
		lblDataAnalysisMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataAnalysisMode.setForeground(Color.WHITE);
		lblDataAnalysisMode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDataAnalysisMode.setBackground(Color.WHITE);
		lblDataAnalysisMode.setBounds(60, 15, 131, 25);
		pnlDataAnalysis.add(lblDataAnalysisMode);

		JLabel lblIconDataAnalysis = new JLabel("");
		lblIconDataAnalysis.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconDataAnalysis.setForeground(Color.WHITE);
		lblIconDataAnalysis.setBackground(Color.WHITE);
		lblIconDataAnalysis.setBounds(18, 7, 39, 41);
		lblIconDataAnalysis.setIcon(new ImageIcon(img_analysis));
		pnlDataAnalysis.add(lblIconDataAnalysis);

		JPanel pnlVisualisation = new JPanel();
		pnlVisualisation.setLayout(null);
		pnlVisualisation.setBorder(null);
		pnlVisualisation.setBackground(new Color(51, 51, 51));
		pnlVisualisation.setBounds(0, 205, 236, 53);
		pnlOptions.add(pnlVisualisation);

		JLabel lblVisualisationMode = new JLabel("Visualisation");
		lblVisualisationMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualisationMode.setForeground(Color.WHITE);
		lblVisualisationMode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVisualisationMode.setBackground(Color.WHITE);
		lblVisualisationMode.setBounds(60, 15, 117, 25);
		pnlVisualisation.add(lblVisualisationMode);

		JLabel lblIconVisualisation = new JLabel("");
		lblIconVisualisation.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconVisualisation.setForeground(Color.WHITE);
		lblIconVisualisation.setBackground(Color.WHITE);
		lblIconVisualisation.setBounds(18, 7, 39, 41);
		lblIconVisualisation.setIcon(new ImageIcon(img_visualise));
		pnlVisualisation.add(lblIconVisualisation);		

		JPanel pnlDisplayJson = new JPanel();
		pnlDisplayJson.setLayout(null);
		pnlDisplayJson.setBorder(null);
		pnlDisplayJson.setBackground(new Color(51, 51, 51));
		pnlDisplayJson.setBounds(0, 103, 236, 53);
		pnlOptions.add(pnlDisplayJson);

		JLabel lblDisplayJsonMode = new JLabel("Display JSON");
		lblDisplayJsonMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplayJsonMode.setForeground(Color.WHITE);
		lblDisplayJsonMode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDisplayJsonMode.setBackground(Color.WHITE);
		lblDisplayJsonMode.setBounds(60, 15, 117, 25);
		pnlDisplayJson.add(lblDisplayJsonMode);

		JLabel lblIconJson = new JLabel("");
		lblIconJson.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconJson.setForeground(Color.WHITE);
		lblIconJson.setBackground(Color.WHITE);
		lblIconJson.setBounds(18, 7, 39, 41);
		lblIconJson.setIcon(new ImageIcon(img_json));
		pnlDisplayJson.add(lblIconJson);

		JPanel pnlInfo = new JPanel();
		pnlInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.setBounds(236, 0, 804, 703);
		pnlInfo.setLayout(new CardLayout(0, 0));
		contentPane.add(pnlInfo);
		//CardLayout cardInfo = (CardLayout)pnlInfo.getLayout();

		JPanel pnlInstagramInfo = new JPanel();
		pnlInstagramInfo.setLayout(null);
		pnlInstagramInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.add(pnlInstagramInfo, "pnlInstagramInfo");

		JButton btnInstagramScrape = new JButton("Launch instagram scraper");
		btnInstagramScrape.setBounds(37, 397, 252, 31);
		pnlInstagramInfo.add(btnInstagramScrape);

		JLabel headerInstagramPanel = new JLabel("Instagram Scraper");
		headerInstagramPanel.setFont(new Font("Tahoma", Font.BOLD, 30));
		headerInstagramPanel.setBounds(37, 19, 300, 40);
		pnlInstagramInfo.add(headerInstagramPanel);

		textFieldInstagramUsername = new JTextField();
		textFieldInstagramUsername.setText("hehebongesh");
		textFieldInstagramUsername.setBounds(128, 78, 166, 29);
		pnlInstagramInfo.add(textFieldInstagramUsername);
		textFieldInstagramUsername.setColumns(10);

		JLabel lblInstagramUsername = new JLabel("Username");
		lblInstagramUsername.setToolTipText("The username of the instagram account to scrape with");
		lblInstagramUsername.setBounds(37, 78, 100, 23);
		pnlInstagramInfo.add(lblInstagramUsername);

		textFieldInstagramPassword = new JPasswordField();
		textFieldInstagramPassword.setColumns(10);
		textFieldInstagramPassword.setBounds(128, 120, 166, 29);
		textFieldInstagramPassword.setText("Password12345");
		pnlInstagramInfo.add(textFieldInstagramPassword);

		JLabel lblInstagramPassword = new JLabel("Password");
		lblInstagramPassword.setBounds(37, 120, 100, 23);
		pnlInstagramInfo.add(lblInstagramPassword);

		JLabel lblInstagramHashtags = new JLabel("Hashtags");
		lblInstagramHashtags.setBounds(37, 171, 82, 23);
		pnlInstagramInfo.add(lblInstagramHashtags);

		textFieldInstagramHashtags = new JTextArea();
		textFieldInstagramHashtags.setRows(3);
		textFieldInstagramHashtags.setLineWrap(true);
		textFieldInstagramHashtags.setBounds(37, 201, 365, 74);
		pnlInstagramInfo.add(textFieldInstagramHashtags);
		textFieldInstagramHashtags.setColumns(10);

		JScrollPane scrollPaneInstagramHashTags = new JScrollPane(textFieldInstagramHashtags);
		scrollPaneInstagramHashTags.setBounds(38, 200, 350, 80);
		//scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pnlInstagramInfo.add(scrollPaneInstagramHashTags);

		JLabel lblInstagramExport = new JLabel("Export location");
		lblInstagramExport.setBounds(37, 294, 133, 23);
		pnlInstagramInfo.add(lblInstagramExport);

		JLabel lblInstagramNumPosts = new JLabel("Number of posts");
		lblInstagramNumPosts.setBounds(443, 171, 194, 23);
		pnlInstagramInfo.add(lblInstagramNumPosts);

		JLabel lblInstagramExportPath = new JLabel("Path: none");
		lblInstagramExportPath.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblInstagramExportPath.setBounds(37, 319, 707, 23);
		pnlInstagramInfo.add(lblInstagramExportPath);

		JButton btnInstagramFile = new JButton("Select path");
		btnInstagramFile.setBounds(37, 347, 131, 29);
		pnlInstagramInfo.add(btnInstagramFile);

		textFieldNumPosts = new JTextField();
		textFieldNumPosts.setBounds(443, 198, 88, 29);
		pnlInstagramInfo.add(textFieldNumPosts);
		textFieldNumPosts.setColumns(10);

		textAreaInstagramConsole = new JTextArea();
		textAreaInstagramConsole.setBackground(Color.WHITE);
		textAreaInstagramConsole.setEditable(false);
		textAreaInstagramConsole.setColumns(2);
		textAreaInstagramConsole.setLineWrap(true);
		textAreaInstagramConsole.setRows(4);
		textAreaInstagramConsole.setBounds(0, 0, 100, 50);
		pnlInstagramInfo.add(textAreaInstagramConsole);

		JScrollPane scrollPaneInstagramConsole = new JScrollPane(textAreaInstagramConsole);
		scrollPaneInstagramConsole.setBounds(38, 480, 706, 140);
		pnlInstagramInfo.add(scrollPaneInstagramConsole);

		JLabel lblInstagramConsole = new JLabel("Output");
		lblInstagramConsole.setBounds(37, 453, 82, 23);
		pnlInstagramInfo.add(lblInstagramConsole);

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

		JTextArea textFieldTwitterHashtags = new JTextArea();
		textFieldTwitterHashtags.setRows(3);
		textFieldTwitterHashtags.setLineWrap(true);
		textFieldTwitterHashtags.setColumns(10);
		textFieldTwitterHashtags.setBounds(0, 0, 348, 78);
		pnlTwitterInfo.add(textFieldTwitterHashtags);

		JScrollPane scrollPaneTwitterHashTags = new JScrollPane(textFieldTwitterHashtags);
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

		textFieldTwitterNumPosts = new JTextField();
		textFieldTwitterNumPosts.setColumns(10);
		textFieldTwitterNumPosts.setBounds(443, 105, 88, 29);
		pnlTwitterInfo.add(textFieldTwitterNumPosts);

		textAreaTwitterConsole = new JTextArea();
		textAreaTwitterConsole.setRows(4);
		textAreaTwitterConsole.setLineWrap(true);
		textAreaTwitterConsole.setEditable(false);
		textAreaTwitterConsole.setColumns(2);
		textAreaTwitterConsole.setBackground(Color.WHITE);
		textAreaTwitterConsole.setBounds(0, 0, 704, 138);
		pnlTwitterInfo.add(textAreaTwitterConsole);

		JScrollPane scrollPaneTwitterConsole = new JScrollPane(textAreaTwitterConsole);
		scrollPaneTwitterConsole.setBounds(38, 480, 706, 140);
		pnlTwitterInfo.add(scrollPaneTwitterConsole);

		JLabel lblTwitterConsole = new JLabel("Output");
		lblTwitterConsole.setBounds(37, 453, 82, 23);
		pnlTwitterInfo.add(lblTwitterConsole);

		//Side panel button click events
		pnlTwitter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CardLayout card = (CardLayout)pnlInfo.getLayout();
				card.show(pnlInfo, "pnlTwitterInfo");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlTwitter.setBackground(new Color(0.3f, 0.3f, 0.3f));
				lblIconTwitter.setIcon(new ImageIcon(img_twitter_hover));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlTwitter.setBackground(new Color(0.2f, 0.2f, 0.2f));
				lblIconTwitter.setIcon(new ImageIcon(img_twitter));
			}
		});
		pnlInstagram.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CardLayout card = (CardLayout)pnlInfo.getLayout();
				card.show(pnlInfo, "pnlInstagramInfo");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlInstagram.setBackground(new Color(0.3f, 0.3f, 0.3f));
				lblIconInstagram.setIcon(new ImageIcon(img_instagram_hover));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlInstagram.setBackground(new Color(0.2f, 0.2f, 0.2f));
				lblIconInstagram.setIcon(new ImageIcon(img_instagram));
			}
		});
		pnlDisplayJson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CardLayout card = (CardLayout)pnlInfo.getLayout();
				card.show(pnlInfo, "pnlDisplayDataInfo");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlDisplayJson.setBackground(new Color(0.3f, 0.3f, 0.3f));
				lblIconJson.setIcon(new ImageIcon(img_json_hover));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlDisplayJson.setBackground(new Color(0.2f, 0.2f, 0.2f));
				lblIconJson.setIcon(new ImageIcon(img_json));
			}
		});

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

		btnInstagramScrape.addMouseListener(new MouseAdapter() {
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
				if(textFieldInstagramUsername.getText().length() < 1) {
					textAreaInstagramConsole.append("*Please enter a username\n");
					return;
				}
				if(textFieldInstagramPassword.getPassword().length < 1) {
					textAreaInstagramConsole.append("*Please enter a password\n");
					return;
				}
				if(exportPath.length() < 1) {
					textAreaInstagramConsole.append("*Please choose an export path\n");
					return;				
				}

				//Check for number of posts
				int numPosts;
				try {
					numPosts = Integer.parseInt(textFieldNumPosts.getText());
				}
				catch (Exception e) {
					textAreaInstagramConsole.append("*Invalid amount of posts to scrape\n");
					return;
				}

				//Check for hashtags
				String[] hashTags = textFieldInstagramHashtags.getText().split("\\s+");

				for (String hashTag : hashTags) {
					textAreaInstagramConsole.append("*Scraping for hashtag: " + hashTag + "\n");
					ScrapeUtility scrapper = new InstagramScraper("https://www.instagram.com/accounts/login/");
					ReturnCode result = scrapper.launchScrapeProcedure(textFieldInstagramUsername.getText()
							, textFieldInstagramPassword.getText()
							, hashTag
							, numPosts
							, exportPath);
					textAreaInstagramConsole.append("*" + result.getDescription());
				}


				/*ScrapeUtility scrapper = new InstagramScraper("https://www.instagram.com/accounts/login/");
				ReturnCode result = scrapper.launchScrapeProcedure("hehebongesh", "Password12345", "apple"
						, 10L, "C:\\Users\\User\\Desktop\\Export2.JSON");
				System.out.println(result.getDescription());
				System.out.println("Done");	*/
			}
		});
		btnTwitterScrape.addMouseListener(new MouseAdapter() {
			@Override		
			public void mouseClicked(MouseEvent arg0) {
				//Check for valid fields
				if(exportPath.length() < 1) {
					textAreaTwitterConsole.append("*Please choose an export path\n");
					return;				
				}

				//Check for number of posts
				int numPosts;
				try {
					numPosts = Integer.parseInt(textFieldTwitterNumPosts.getText());
				}
				catch (Exception e) {
					textAreaTwitterConsole.append("*Invalid amount of posts to scrape\n");
					return;
				}

				//Check for hashtags
				String[] hashTags = textFieldTwitterHashtags.getText().split("\\s+");

				for (String hashTag : hashTags) {
					textAreaTwitterConsole.append("*Scraping for hashtag: " + hashTag + "\n");
					ScrapeUtility scrapper = new TwitterScraper("test");
					ReturnCode result = scrapper.launchScrapeProcedure("nil"
							, "nil"
							, hashTag
							, numPosts
							, exportPath);
					textAreaTwitterConsole.append("*" + result.getDescription());
				}
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
					String displayOutput = JSONUtility.prettifyJSON(JSONUtility.parseJSONToString(file.getPath()));
					txaJsonContent.setText(displayOutput);
					lblJsonFilePath.setText("File loaded: " + file.getPath());
				}


			}
		});
		btnJsonSaveAs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					FileWriter.writeStringToPath(file.getPath(), JSONUtility.unPrettifyJSON(txaJsonContent.getText()));
					msgbox("Successfully saved text to: " + file.getPath());
				}
			}
		});

	}
}
