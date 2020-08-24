import java.awt.EventQueue;
import java.awt.Font;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class countdown {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					countdown window = new countdown();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public countdown() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		LocalDate dueDate = LocalDate.of(2021,4,6);
		LocalDate now = LocalDate.now();
		//LocalDate now = LocalDate.of(2020,8,22);
		long lWeeks = ChronoUnit.WEEKS.between(now, dueDate);
		long lDays = ChronoUnit.DAYS.between(now, dueDate);
		int days = (int) (lDays % lWeeks);
		
//		***DEBUG***			
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY"); 
//        System.out.println(formatter.format(dueDate)); 
//        System.out.println(formatter.format(now)); 
//        System.out .println(lWeeks);
//        System.out.println(days);
   
		
		//String dueDate = "4/6/2021";
		String weeks =  lWeeks + " weeks";
		String sDays = days + " days";
		String countDesc = "for Baby Seer to arrive";
		
		
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Keraleeyam", Font.PLAIN, 12));
		frame.getContentPane().setBackground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
		frame.setBounds(100, 100, 410, 247);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("BABY COUNTDOWN");		
		lblTitle.setFont(new Font("Keraleeyam", Font.PLAIN, 40));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 12, 400, 52);
		frame.getContentPane().add(lblTitle);		
		
		JLabel lblWeeks = new JLabel(weeks);
		lblWeeks.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeeks.setFont(new Font("Keraleeyam", Font.PLAIN, 33));
		lblWeeks.setBounds(137, 71, 116, 38);
		frame.getContentPane().add(lblWeeks);
		
		
		JLabel lblDays = new JLabel(sDays);
		lblDays.setHorizontalAlignment(SwingConstants.CENTER);
		lblDays.setFont(new Font("Keraleeyam", Font.PLAIN, 33));
		lblDays.setBounds(137, 110, 116, 38);
		frame.getContentPane().add(lblDays);
		
		JLabel lblForBabySeer = new JLabel(countDesc);
		lblForBabySeer.setHorizontalAlignment(SwingConstants.CENTER);
		lblForBabySeer.setFont(new Font("Keraleeyam", Font.PLAIN, 24));
		lblForBabySeer.setBounds(67, 160, 282, 38);
		frame.getContentPane().add(lblForBabySeer);
	}
}
