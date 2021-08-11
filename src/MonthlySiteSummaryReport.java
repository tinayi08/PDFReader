import XML.ConfigXMLParser;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
//import java.awt.Panel;
//import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;

//import XML.XML.ConfigXMLParser;

import java.awt.Color;
//import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
//import javax.swing.Action;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MonthlySiteSummaryReport {

	private JFrame frmGoRentalsMonthlySummary;
	private JTextField strReportMonthYYYY;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private JFileChooser chooser;
	private static String reportingMonth;
	private static ConfigXMLParser config;
	private static String outFileMainName;
	private static String workDirName; 
	private static String warningString;
	private String pdfOutputFile = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reportingMonth = null;
					config = new ConfigXMLParser("configuration.xml");
					
					// Get configured prefix for PDF output file name,  it will be appended with montly.
					outFileMainName = config.getPostfix();  //  This postfix is main part of PDF output file name.
					if (outFileMainName == null) {
						outFileMainName = "";
					}
					System.out.println("outFilePostfix is >" + outFileMainName + "<");
					
					//  Get configured working directory where site details are stored to be processed.
					workDirName = config.getWorkingDirectory();
					System.out.println("XML's initial workDirName is " + workDirName);
					
					//  Get preset warning string...
					warningString = config.getWarning();
					
					if (warningString == null) {
						warningString = "";
						System.out.println("warning string is not configured properly in configuration.xml file!");
					}
					System.out.println("Warning String is >" + warningString + "<");
					MonthlySiteSummaryReport window = new MonthlySiteSummaryReport();
					window.frmGoRentalsMonthlySummary.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MonthlySiteSummaryReport() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		reportingMonth = null;
		
		frmGoRentalsMonthlySummary = new JFrame();
		frmGoRentalsMonthlySummary.setTitle("GoRentals Monthly Summary Report Generator");
		frmGoRentalsMonthlySummary.setBounds(100, 100, 589, 307);
		frmGoRentalsMonthlySummary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(240, 255, 240));
		frmGoRentalsMonthlySummary.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		final JButton btnRunReport = new JButton("Run Report");
		JLabel lblReportMonthYYYY = new JLabel("Report Month (Month and Year):");
		final JLabel textDirectorySelected = new JLabel(workDirName);
		strReportMonthYYYY = new JTextField();
		strReportMonthYYYY.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				reportingMonth = strReportMonthYYYY.getText();
				pdfOutputFile = reportingMonth + "_" + outFileMainName + ".pdf";
				//JOptionPane.showMessageDialog(null, reportingMonth + " report month entered");
				//if (((reportingMonth != null) && (!reportingMonth.isEmpty())) && (directoryPath != null)) {
				if (reportingMonth != null) {  // There is already a default directory...
					//JOptionPane.showMessageDialog(null, "Directory and report month entered!");
					btnRunReport.setEnabled(true);
					
				} else {
					btnRunReport.setEnabled(false);
				}
			}
		});
		strReportMonthYYYY.setBackground(new Color(72, 209, 204));
		strReportMonthYYYY.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(238, 232, 170));
		
		JButton btnCancel = new JButton("Cancel & Quit");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Cancel button pressed!");
				JOptionPane.showMessageDialog(null, "A Cancel button is pressed,  Application will terminate with no report!");
				frmGoRentalsMonthlySummary.dispose();
				System.exit(0);
			}
		});
		
		//final JButton btnRunReport = new JButton("Run Report");
		btnRunReport.setEnabled(false);
		btnRunReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Run Report pressed!   Dir:" + workDirName); 
				//JOptionPane.showMessageDialog(null, "Running Report:  Dir: >" + workDirName + "<  postfix: >" + reportingMonth + "<");
				JOptionPane.showMessageDialog(null, "This will take bit of time to complete!\n working directory: \n" + workDirName, "ALERT MESSAGE",  JOptionPane.WARNING_MESSAGE);
				   String outFileName = reportingMonth + "_" + outFileMainName;
				   
				   //pdfOutputFile = outFileMainName + "All site Summery Report Report.pdf";
				   //outFileName.len
				   //PDF2Text pdfFolder = null;
				   //FileWriter fw = null;
//				   try {
//					   	System.out.println("warningString is " + warningString);
//						pdfFolder = new PDF2Text(outFileMainName, reportingMonth, warningString);   /// USE THIS LINE AS REFERENCE
//						fw = new FileWriter(outFileName);
//				   } catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//				   }
//				   int numberOfFilesRead = pdfFolder.readFromPath(workDirName, fw, null);

//				   System.out.println(numberOfFilesRead + " files read... Output is in " + outFileName);
//				   JOptionPane.showMessageDialog(null, "Report Created!  \nThere are " + numberOfFilesRead +
//						   " sites included", "INFORMATION",  JOptionPane.INFORMATION_MESSAGE);
				try {
					RunFiles run = new RunFiles(workDirName, reportingMonth);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				btnRunReport.setEnabled(false);
				}
			});
		GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
		gl_mainPanel.setHorizontalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_mainPanel.createSequentialGroup()
							.addGap(67)
							.addComponent(btnCancel)
							.addGap(77)
							.addComponent(btnRunReport))
						.addGroup(gl_mainPanel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_mainPanel.createSequentialGroup()
									.addComponent(lblReportMonthYYYY)
									.addGap(18)
									.addComponent(strReportMonthYYYY, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
								.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
							.addGap(22)))
					.addContainerGap())
		);
		gl_mainPanel.setVerticalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblReportMonthYYYY)
						.addComponent(strReportMonthYYYY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnRunReport))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		
		JLabel lblFolderLocagtion = new JLabel("Run Report Folder Location:");
		
		
		
		JButton btnBrowsFolder = new JButton("Browse Folder");
		btnBrowsFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser(".");
				chooser.setDialogTitle("Choose directory to run report from");
	
				File rootDir = new File(workDirName);
				chooser.setCurrentDirectory(rootDir);
				System.out.println("directoryPath is >" + workDirName + "<");
				
				//System.out.println("directory is >" + rootDir.getAbsolutePath() + "< JFileChooser().current = >" + chooser.getCurrentDirectory()+ "<");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				
			
				if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					System.out.println("just found a save button press!!!!");
					//workDirName = chooser.getCurrentDirectory().getAbsolutePath();
					workDirName = chooser.getSelectedFile().getAbsolutePath();
					System.out.println("File Directory:" + workDirName);
					textDirectorySelected.setText(workDirName);
					JOptionPane.showMessageDialog(null, workDirName + " is selected");
					if (((reportingMonth != null) && (!reportingMonth.isEmpty())) && (reportingMonth != null)) {
					btnRunReport.setEnabled(true);
					}
				}
				
			}
			
		});
		
		
		textDirectorySelected.setBackground(Color.WHITE);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(268, Short.MAX_VALUE)
					.addComponent(btnBrowsFolder)
					.addGap(18))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFolderLocagtion)
					.addContainerGap(230, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(46)
					.addComponent(textDirectorySelected, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(65, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(12)
					.addComponent(lblFolderLocagtion)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textDirectorySelected)
					.addGap(22)
					.addComponent(btnBrowsFolder)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		mainPanel.setLayout(gl_mainPanel);
	}
	
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
