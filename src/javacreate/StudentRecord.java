package javacreate;

import java.awt.EventQueue;

import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import net.proteanit.sql.DbUtils;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;

//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import java.awt.Font;
//import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StudentRecord {

	private JFrame frame;
	private JTextField txtsname;
	private JTextField txtid;
	private JTextField txtbatchno;
	private JTable table;
	private JTextField txtsid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentRecord window = new StudentRecord();
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
	public StudentRecord() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	
	public void Connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/javacreate", "root","");
        }
        catch (ClassNotFoundException ex) 
        {
          ex.printStackTrace();
        }
        catch (SQLException ex) 
        {
        	   ex.printStackTrace();
        }

    }
	
	
	public void table_load()
    {
    	try 
    	{
	    pst = con.prepareStatement("select * from record");
	    rs = pst.executeQuery();
	    table.setModel(DbUtils.resultSetToTableModel(rs));
	} 
    	catch (SQLException e) 
    	 {
    		e.printStackTrace();
	  } 
    }
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 622, 352);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Record");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(162, 11, 171, 39);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(0, 0, 0)));
		panel.setBounds(10, 61, 295, 118);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Student Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 11, 100, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Student ID");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(10, 44, 77, 27);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Batch No");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(10, 78, 69, 27);
		panel.add(lblNewLabel_1_2);
		
		txtsname = new JTextField();
		txtsname.setBounds(113, 15, 172, 20);
		panel.add(txtsname);
		txtsname.setColumns(10);
		
		txtid = new JTextField();
		txtid.setColumns(10);
		txtid.setBounds(113, 48, 172, 20);
		panel.add(txtid);
		
		txtbatchno = new JTextField();
		txtbatchno.setColumns(10);
		txtbatchno.setBounds(113, 82, 172, 20);
		panel.add(txtbatchno);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sname,SR,batchno;
				
				sname = txtsname.getText();
				SR = txtid.getText();
				batchno = txtbatchno.getText();
				
				 try {
						pst = con.prepareStatement("insert into record(Name,Roll_No,Batch)values(?,?,?)");
						pst.setString(1, sname);
						pst.setString(2, SR);
						pst.setString(3, batchno);
						pst.executeUpdate();
					    JOptionPane.showMessageDialog(null, "Record Added");
						
					    table_load();
							           
						txtsname.setText("");
						txtid.setText("");
						txtbatchno.setText("");
						txtsname.requestFocus();
					   }
				 
					catch (SQLException e1) 
				        {
										
					e1.printStackTrace();
					}
				
				
				
				
			}
		});
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
		btnNewButton.setBounds(20, 210, 90, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
		btnExit.setBackground(Color.RED);
		btnExit.setBounds(127, 210, 79, 29);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
		btnClear.setBackground(Color.LIGHT_GRAY);
		btnClear.setBounds(216, 210, 89, 29);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(315, 61, 284, 176);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 4, true), "Search", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(60, 179, 113)));
		panel_1.setBounds(10, 246, 295, 63);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Student ID");
		lblNewLabel_1_1_1.setBounds(10, 26, 71, 18);
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_1.add(lblNewLabel_1_1_1);
		
		txtsid = new JTextField();
		txtsid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				 try {
			          
			            String id = txtsid.getText();

			                pst = con.prepareStatement("select Name,Roll_No,Batch from record where id = ?");
			                pst.setString(1, id);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true)
			            {
			              
			                String Name = rs.getString(1);
			                String SR = rs.getString(2);
			                String Batch = rs.getString(3);
			                
			                txtsname.setText(Name);
			                txtid.setText(SR);
			                txtbatchno.setText(Batch);
			                
			                
			            }   
			            else
			            {
			            	txtsname.setText("");
			            	txtid.setText("");
			                txtbatchno.setText("");
			                 
			            }
			            


			        } 
				
				 catch (SQLException ex) {
			           
			        }
				
				
			}
					
				
			
		});
		txtsid.setBounds(100, 26, 174, 20);
		txtsid.setColumns(10);
		panel_1.add(txtsid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
	String sname,SR,batchno,sid;
				
				sname = txtsname.getText();
				SR = txtid.getText();
				batchno = txtbatchno.getText();
				sid = txtsid.getText();				
				 try {
						pst = con.prepareStatement("update record set Name=?,Roll_No=?,Batch=? where ID=?");
						pst.setString(1, sname);
						pst.setString(2, SR);
						pst.setString(3, batchno);
						pst.setString(4, sid);
						
						pst.executeUpdate();
					    JOptionPane.showMessageDialog(null, "Record Updated");
						
					    table_load();
							           
						txtsname.setText("");
						txtid.setText("");
						txtbatchno.setText("");
						txtsname.requestFocus();
					   }
				 
					catch (SQLException e1) 
				        {
										
					e1.printStackTrace();
					}
				
				
				
				
				
			}
		});
		btnUpdate.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
		btnUpdate.setBackground(Color.GREEN);
		btnUpdate.setBounds(337, 251, 105, 29);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
		btnDelete.setBackground(Color.RED);
		btnDelete.setBounds(465, 251, 99, 29);
		frame.getContentPane().add(btnDelete);
	}
}
