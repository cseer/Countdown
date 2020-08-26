import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ini4j.Wini;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class ConfDialog extends JDialog {

	public String getPhrase() {
		return tPhrase;
	}

	public void setPrase(String countDesc) {
		txtPhrase.setText(countDesc); 		
		this.tPhrase = countDesc;
	}

	
	public String getDueDate() {
		return tDueDate;
	}

	public void setDueDate(String dueDate) {	
		txtDueDate.setText(dueDate);
		this.tDueDate = dueDate;
	}



	String tPhrase = ""; 
	String tDueDate = "";
	//public boolean SaveSelected = false ;
 
	private final JPanel contentPanel = new JPanel();
	private JTextField txtPhrase;
	private JTextField txtDueDate;
	
	void saveIni() {
		try{
			File f = new File("countdown.ini");
			if (!f.exists()) {
				System.out.println("ERROR in ConfDialog: countdown.ini do not exist");
				System.out.println("Creating the file");
				f.createNewFile();
		   	}
        	Wini ini = new Wini(f);
        	ini.put("config", "duedate", txtDueDate.getText());
        	ini.put("config", "phrase", txtPhrase.getText());
            ini.store();
            f = null;
        // To catch basically any error related to writing to the file
        // (The system cannot find the file specified)
        }catch(Exception e){
            System.err.println(e.getStackTrace());
        }
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConfDialog dialog = new ConfDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConfDialog() {
		setFont(new Font("DejaVu Serif", Font.PLAIN, 12));
		setTitle("Baby's Information");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 246);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPhrase = new JLabel("Phrase:");
		lblPhrase.setBounds(54, 93, 60, 15);
		contentPanel.add(lblPhrase);	
	
		txtPhrase = new JTextField();
		txtPhrase.setBounds(165, 87, 222, 27);
		txtPhrase.setColumns(10);
		txtPhrase.setText(tPhrase); 				
		contentPanel.add(txtPhrase);

		
		JLabel lblDueDate = new JLabel("Due Date:");
		lblDueDate.setBounds(54, 54, 99, 15);		
		contentPanel.add(lblDueDate);		
		
		txtDueDate = new JTextField();
		txtDueDate.setBounds(165, 48, 222, 27);
		txtDueDate.setToolTipText("Enter baby's due date");
		txtDueDate.setColumns(10);
		txtDueDate.setText(tDueDate);	
		contentPanel.add(txtDueDate);	

			
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setFont(new Font("SansSerif", Font.PLAIN, 12));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
			{
				JButton btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Click Ok (Save)");
						saveIni();
						dispose();
					}
				});
				btnOk.setActionCommand("Ok");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
		}
	}
}
