package foolbin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.NumberFormatter;

public class SettingsGUI {

	private Settings settings;
    private JFrame frame;
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
	
	public SettingsGUI(Settings s){
		this.settings=s;
		 createGUI();
	}

	
	private void addTextField(Container pane, int row, String label, String value, DocumentListener dl){
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridy = row;
	    
	    c.gridx = 0;
	    JLabel jl = new JLabel(label);
	    pane.add(jl, c);
	    
	    c.gridx = 2;
	    JTextField tf = new JTextField(value, 20);
	    pane.add(tf, c);
	    tf.getDocument().addDocumentListener(dl);
	}

	private void addPasswordField(Container pane, int row, String label, String value, DocumentListener dl){
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridy = row;
	    
	    c.gridx = 0;
	    JLabel jl = new JLabel(label);
	    pane.add(jl, c);
	    
	    c.gridx = 2;
	    JPasswordField tf = new JPasswordField(value, 20);
	    pane.add(tf, c);
	    tf.getDocument().addDocumentListener(dl);
	}
	
	private void addNumberField(Container pane, int row, String label, String value, DocumentListener dl, int min, int max){
	    NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(min);
	    formatter.setMaximum(max);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
	    
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridy = row;
	    
	    c.gridx = 0;
	    JLabel jl = new JLabel(label);
	    pane.add(jl, c);
	    
	    c.gridx = 2;
	    JFormattedTextField tf = new JFormattedTextField(formatter);
	    tf.setText(value);
	    pane.add(tf, c);
	    tf.getDocument().addDocumentListener(dl);
	}
	
    private void createGUI() {
        //Create and set up the window.
        frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        Container pane = frame.getContentPane();
        pane.setLayout(new GridBagLayout());
                
        addTextField(pane, 0, "Username", settings.getUsername(), new DocumentListener(){
    			public void insertUpdate(DocumentEvent e) {
    				try {settings.setUsername(e.getDocument().getText(0, e.getDocument().getLength()));
					} catch (BadLocationException e1) {System.err.print("Error on insert update!");}
    			}
    			public void removeUpdate(DocumentEvent e) {
    				try {settings.setUsername(e.getDocument().getText(0, e.getDocument().getLength()));
    				} catch (BadLocationException e1) {System.err.print("Error on remove update!");}
    			}
    			public void changedUpdate(DocumentEvent e) {
    				try {settings.setUsername(e.getDocument().getText(0, e.getDocument().getLength()));
    				} catch (BadLocationException e1) {System.err.print("Error on remove update!");}
    			}
    		});
        
        addPasswordField(pane, 1, "Password", settings.getPassword(), new DocumentListener(){
    			public void insertUpdate(DocumentEvent e) {
    				try {settings.setPassword(e.getDocument().getText(0, e.getDocument().getLength()));
					} catch (BadLocationException e1) {System.err.print("Error on insert update!");}
    			}
    			public void removeUpdate(DocumentEvent e) {
    				try {settings.setPassword(e.getDocument().getText(0, e.getDocument().getLength()));
    				} catch (BadLocationException e1) {System.err.print("Error on remove update!");}
    			}
    			public void changedUpdate(DocumentEvent e) {
    				try {settings.setPassword(e.getDocument().getText(0, e.getDocument().getLength()));
    				} catch (BadLocationException e1) {System.err.print("Error on remove update!");}
    			}
    		});

        addNumberField(pane, 2, "Time to Live", Integer.toString(settings.getTTL()),new DocumentListener(){
    			public void insertUpdate(DocumentEvent e) {
    				try {settings.setTTL(e.getDocument().getText(0, e.getDocument().getLength()));
					} catch (BadLocationException e1) {System.err.print("Error on insert update!");}
    			}
    			public void removeUpdate(DocumentEvent e) {
    				try {settings.setTTL(e.getDocument().getText(0, e.getDocument().getLength()));
    				} catch (BadLocationException e1) {System.err.print("Error on remove update!");}
    			}
    			public void changedUpdate(DocumentEvent e) {
    				try {settings.setTTL(e.getDocument().getText(0, e.getDocument().getLength()));
    				} catch (BadLocationException e1) {System.err.print("Error on remove update!");}
    			}
    		}, 5, 864000);

        addNumberField(pane, 3, "Update/Refresh Interval", Integer.toString(settings.getRefreshInterval()), new DocumentListener(){
    			public void insertUpdate(DocumentEvent e) {
    				try {settings.setRefreshInterval(e.getDocument().getText(0, e.getDocument().getLength()));
					} catch (BadLocationException e1) {System.err.print("Error on insert update!");}
    			}
    			public void removeUpdate(DocumentEvent e) {
    				try {settings.setRefreshInterval(e.getDocument().getText(0, e.getDocument().getLength()));
    				} catch (BadLocationException e1) {System.err.print("Error on remove update!");}
    			}
    			public void changedUpdate(DocumentEvent e) {
    				try {settings.setRefreshInterval(e.getDocument().getText(0, e.getDocument().getLength()));
    				} catch (BadLocationException e1) {System.err.print("Error on remove update!");}
    			}
    		}, 5, 864000);
        
        
        GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
	    c.gridx = 0;
	    c.gridy = 4;
	    c.gridwidth = 3;
        JList<Integer> jl = new JList<Integer>(settings.getIDs());
        pane.add(jl,c);
        
        c.gridwidth=1;
        c.gridy=6;
        c.gridx=0;
        JButton addBtn = new JButton("Add ID");
        pane.add(addBtn, c);
        
        c.gridwidth=1;
        c.gridy=6;
        c.gridx=1;
        JButton delBtn = new JButton("Del ID");
        pane.add(delBtn, c);
        
        //Hide the window and set to hide on close
        frame.pack();
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	hideGui();
            }
        });
    }
	
    public void showGui(){
    	frame.setVisible(true);
    }
    
    public void hideGui(){
    	frame.setVisible(false);
    }
	
}
