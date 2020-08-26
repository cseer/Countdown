import java.awt.EventQueue;
import java.awt.Font;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import org.ini4j.Wini;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import javax.swing.JToolBar;

public class Countdown {


	public String getPhrase() {
		return strPhrase;
	}

	public void setPhrase(String phrase) {
		this.strPhrase = phrase;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	private JFrame frmBabyCountdown;
	String strPhrase = "for Baby to arrive";
	LocalDate dueDate = LocalDate.now();
	String strWeeks =  "0 weeks";
	String strDays = "0 days";	
	
	
	static boolean configOK = false;
	
 	private void readIni() {
		//DEBUG
		System.out.println("Ejecutando readIni");
		try {
			File f = new File("countdown.ini");
			if (!f.exists()) {
				f = null;
				System.out.println("ERROR in Countdown: countdown.ini do not exist");
				displayConfigDialog();
				//TODO Validar si se creo exitosament archivo INI
		   	} 
			// [Por ahora] asumo que si no existia, se creo exitosamente archivo INI
			f = new File("countdown.ini");
			//Parse values form INI file
			Wini ini = new Wini(f);
            strPhrase= ini.get("config", "phrase");
            String sDueDate = ini.get("config", "duedate");           
			dueDate = LocalDate.parse(sDueDate);
			// Calculate period and update value of strWeeks and strDays
			LocalDate now = LocalDate.now();
			//LocalDate now = LocalDate.of(2020,8,22);
			long lWeeks = ChronoUnit.WEEKS.between(now, dueDate);
			long lDays = ChronoUnit.DAYS.between(now, dueDate);
			int days = 0;
			if (lWeeks > 0) {
				days = (int) (lDays % lWeeks);
			}
			//String dueDate = "4/6/2021";
			strWeeks =  lWeeks + " weeks";
			strDays = days + " days";	
			//TODO
			System.out.println(sDueDate);		   	
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private void displayConfigDialog() {
		ConfDialog confDialog = new ConfDialog();	
		confDialog.setDueDate(dueDate.toString());
		confDialog.setPrase(strPhrase);				
		confDialog.setVisible(true);
		//TODO
		System.out.println("Despues de cerrar ConfigDialog");
		
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//leer ini file						
				try {
					Countdown window = new Countdown();
					window.frmBabyCountdown.setVisible(true);								
				} catch (Exception e) {
					e.printStackTrace();
				}								

			}
		});
	}

	/**
	 * Create the application.
	 */
	public Countdown() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		readIni();					
		frmBabyCountdown = new JFrame();
		frmBabyCountdown.setIconImage(Toolkit.getDefaultToolkit().getImage(Countdown.class.getResource("/resources/012_026_newborn_infant_child_baby-256.png")));
		frmBabyCountdown.setTitle("Baby Countdown");
		frmBabyCountdown.getContentPane().setFont(new Font("SansSerif", Font.PLAIN, 12));
		frmBabyCountdown.setBounds(100, 100, 410, 290);
		frmBabyCountdown.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBabyCountdown.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("BABY COUNTDOWN");		
		lblTitle.setFont(new Font("SansSerif", Font.PLAIN, 34));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(6, 37, 404, 52);
		frmBabyCountdown.getContentPane().add(lblTitle);		
		
		JLabel lblWeeks = new JLabel(strWeeks);
		lblWeeks.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeeks.setFont(new Font("SansSerif", Font.PLAIN, 29));
		lblWeeks.setBounds(100, 102, 193, 38);
		frmBabyCountdown.getContentPane().add(lblWeeks);
		
		
		JLabel lblDays = new JLabel(strDays);
		lblDays.setHorizontalAlignment(SwingConstants.CENTER);
		lblDays.setFont(new Font("SansSerif", Font.PLAIN, 29));
		lblDays.setBounds(100, 141, 193, 38);
		frmBabyCountdown.getContentPane().add(lblDays);
		
		JLabel lblPhrase = new JLabel(strPhrase);
		lblPhrase.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhrase.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblPhrase.setBounds(67, 191, 282, 38);
		frmBabyCountdown.getContentPane().add(lblPhrase);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(6, 0, 398, 35);
		frmBabyCountdown.getContentPane().add(toolBar);
		
		JButton btnSetup = new JButton("");
		btnSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				displayConfigDialog();
				readIni();	
				lblPhrase.setText(strPhrase);
				lblWeeks.setText(strWeeks);
				lblDays.setText(strDays);
				frmBabyCountdown.revalidate();
				frmBabyCountdown.repaint();		
			}
		});
		btnSetup.setIcon(new ImageIcon(Countdown.class.getResource("/SmallIcons/Settings24.png")));
		toolBar.add(btnSetup);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AboutProgram about = new AboutProgram ();				
				about.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(Countdown.class.getResource("/SmallIcons/Information24.png")));
		toolBar.add(btnNewButton);
		

	}
}
