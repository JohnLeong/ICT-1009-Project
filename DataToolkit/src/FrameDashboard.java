import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.CardLayout;

public class FrameDashboard extends JFrame {


	private JPanel contentPane;

	private Image img_logo = new ImageIcon(FrameDashboard.class.getResource("resource/sit.png")).getImage().getScaledInstance(200, 90, Image.SCALE_SMOOTH);
	private Image img_facebook = new ImageIcon(FrameDashboard.class.getResource("resource/facebook.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_instagram = new ImageIcon(FrameDashboard.class.getResource("resource/instagram.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_twitter = new ImageIcon(FrameDashboard.class.getResource("resource/twitter.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_analysis = new ImageIcon(FrameDashboard.class.getResource("resource/eye.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_visualise = new ImageIcon(FrameDashboard.class.getResource("resource/search.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	
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

	/**
	 * Create the frame.
	 */
	public FrameDashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1040, 703);
		//setUndecorated(true);
		
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
		pnlOptions.setBounds(0, 133, 236, 699);
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
		
		JPanel pnlFacebook = new JPanel();
		pnlFacebook.setLayout(null);
		pnlFacebook.setBorder(null);
		pnlFacebook.setBackground(new Color(51, 51, 51));
		pnlFacebook.setBounds(0, 104, 236, 53);
		pnlOptions.add(pnlFacebook);
		
		JLabel lblFacebookMode = new JLabel("Facebook");
		lblFacebookMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblFacebookMode.setForeground(Color.WHITE);
		lblFacebookMode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFacebookMode.setBackground(Color.WHITE);
		lblFacebookMode.setBounds(60, 15, 117, 25);
		pnlFacebook.add(lblFacebookMode);
		
		JLabel lblIconFacebook = new JLabel("");
		lblIconFacebook.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconFacebook.setForeground(Color.WHITE);
		lblIconFacebook.setBackground(Color.WHITE);
		lblIconFacebook.setBounds(18, 7, 39, 41);
		lblIconFacebook.setIcon(new ImageIcon(img_facebook));
		pnlFacebook.add(lblIconFacebook);
		
		JPanel pnlDataAnalysis = new JPanel();
		pnlDataAnalysis.setLayout(null);
		pnlDataAnalysis.setBorder(null);
		pnlDataAnalysis.setBackground(new Color(51, 51, 51));
		pnlDataAnalysis.setBounds(0, 157, 236, 53);
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
		pnlVisualisation.setBounds(0, 209, 236, 53);
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
		
		JPanel pnlInfo = new JPanel();
		pnlInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.setBounds(236, 0, 780, 639);
		pnlInfo.setLayout(new CardLayout(0, 0));
		contentPane.add(pnlInfo);
		//CardLayout cardInfo = (CardLayout)pnlInfo.getLayout();
		
		JPanel pnlInstagramInfo = new JPanel();
		pnlInstagramInfo.setLayout(null);
		pnlInstagramInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.add(pnlInstagramInfo, "pnlInstagramInfo");

		JButton btnInstagramScrape = new JButton("Launch instagram scraper");
		btnInstagramScrape.setBounds(37, 100, 252, 31);
		pnlInstagramInfo.add(btnInstagramScrape);
		
		JLabel headerInstagramPanel = new JLabel("Instagram Panel");
		headerInstagramPanel.setBounds(360, 9, 145, 23);
		pnlInstagramInfo.add(headerInstagramPanel);
		
		JPanel pnlTwitterInfo = new JPanel();
		pnlTwitterInfo.setLayout(null);
		pnlTwitterInfo.setBackground(SystemColor.controlHighlight);
		pnlInfo.add(pnlTwitterInfo, "pnlTwitterInfo");
		
		JButton btnTwitterScrape = new JButton("Launch twitter scraper");
		btnTwitterScrape.setBounds(37, 100, 252, 31);
		pnlTwitterInfo.add(btnTwitterScrape);
		
		JLabel headerTwitterPanel = new JLabel("Twitter Panel test");
		headerTwitterPanel.setBounds(360, 9, 145, 23);
		pnlTwitterInfo.add(headerTwitterPanel);
		
		//Side panel button click
		pnlTwitter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CardLayout card = (CardLayout)pnlInfo.getLayout();
				card.show(pnlInfo, "pnlTwitterInfo");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlTwitter.setBackground(new Color(0.3f, 0.3f, 0.3f));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlTwitter.setBackground(new Color(0.2f, 0.2f, 0.2f));
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
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlInstagram.setBackground(new Color(0.2f, 0.2f, 0.2f));
			}
		});
		
		//Twitter info panel
		btnTwitterScrape.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ScrapeUtility twitterScraper = new TwitterScraper("test");
				twitterScraper.launchScrapeProcedure("hehebongesh", "Password12345", "#hearthstone"
						, 50L, Paths.get("").toAbsolutePath().toString() + "/twitter_data.txt");
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
				ScrapeUtility scrapper = new InstagramScraper("https://www.instagram.com/accounts/login/");
				ReturnCode result = scrapper.launchScrapeProcedure("hehebongesh", "Password12345", "sit"
						, 10L, "C:\\Users\\User\\Desktop\\Export.JSON");
				System.out.println(result.getDescription());
				System.out.println("Done");
			}
		});

	}
}
