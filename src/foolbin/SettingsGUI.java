package foolbin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.NumberFormatter;

public class SettingsGUI {

	private Settings settings;
    private JFrame frame;
    private Boolean hidden=true;
    
	public SettingsGUI(Settings s){
		this.settings=s;
	}

	
	private JTextField addTextField(Container pane, int row, String label, String value, DocumentListener dl){
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
	    return tf;
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
	
	/* I never got these working right so this field type doesn't work */
	private void addNumberField(Container pane, int row, String label, String value, DocumentListener dl, int min, int max){
	    NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(min);
	    formatter.setMaximum(max);
	    formatter.setAllowsInvalid(true);
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
        frame = new JFrame("Sitelutions Foolbin Updater");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        Container pane = frame.getContentPane();
        pane.setLayout(new GridBagLayout());
                
        addTextField(pane, 0, "E-mail Address", settings.getUsername(), new DocumentListener(){
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

        addTextField(pane, 2, "Time to Live", Integer.toString(settings.getTTL()),new DocumentListener(){
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
    		});

        addTextField(pane, 3, "Update/Refresh Interval", Integer.toString(settings.getRefreshInterval()), new DocumentListener(){
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
    		});
        
        
        GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
	    c.gridx = 0;
	    c.gridy = 4;
	    c.gridwidth = 3;
	    final DefaultListModel<Integer> ids = new DefaultListModel<Integer>();
	    int i=0;
	    for (Integer id:settings.getIDs()){
	    	ids.add(i++, id);
	    }
        final JList<Integer> jl = new JList<Integer>(ids);
        final JScrollPane jlp = new JScrollPane(jl);
        jl.setPreferredSize(new Dimension(300,70));
        pane.add(jlp,c);
        
        //See the documentation on IDStuff at the file's bottom
        final IDStuff NewID=new IDStuff();
        final JTextField jtf=addTextField(pane, 5, "New ID Number", "", new DocumentListener(){
			public void insertUpdate(DocumentEvent e) {
				try {NewID.id=Integer.parseInt(e.getDocument().getText(0, e.getDocument().getLength()).replaceAll("[^\\d.]", ""));
				} catch (Exception e1) {System.err.print("Error on insert update!");}
			}
			public void removeUpdate(DocumentEvent e) {
				try {NewID.id=Integer.parseInt(e.getDocument().getText(0, e.getDocument().getLength()).replaceAll("[^\\d.]", ""));
				} catch (Exception e1) {System.err.print("Error on remove update!");}
			}
			public void changedUpdate(DocumentEvent e) {
				try {NewID.id=Integer.parseInt(e.getDocument().getText(0, e.getDocument().getLength()).replaceAll("[^\\d.]", ""));
				} catch (Exception e1) {System.err.print("Error on remove update!");}
			}
		});
        
        
        c.gridwidth=1;
        c.gridy=6;
        c.gridx=0;
        JButton addBtn = new JButton("Add ID");
        addBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (!settings.hasID(NewID.id)){
					ids.addElement(NewID.id);
					settings.addID(NewID.id);	
					jtf.setText("");
				}
			}
        });
        pane.add(addBtn, c);
        
        c.gridwidth=1;
        c.gridy=6;
        c.gridx=1;
        JButton delBtn = new JButton("Del ID");
        delBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int val=jl.getSelectedValue();
				int idx=jl.getSelectedIndex();
				settings.removeID(val);
				ids.remove(idx);
			}
        });
        pane.add(delBtn, c);
        
        c.gridwidth=1;
        c.gridy=7;
        c.gridx=0;
        JButton saveCfgBtn = new JButton("Save Config");
        saveCfgBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				FileDialog df = new FileDialog(frame, "Save Configuration", FileDialog.SAVE);
				df.setVisible(true);
				//TODO: Should use getFiles to deal with recent docs better
				if (df.getFile() != null) settings.save(df.getDirectory()+df.getFile());
			}
        });
        pane.add(saveCfgBtn, c);
        
        c.gridwidth=1;
        c.gridy=7;
        c.gridx=1;
        JButton loadCfgBtn = new JButton("Load Config");
        loadCfgBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				FileDialog df = new FileDialog(frame, "Select Configuration", FileDialog.LOAD);
				df.setVisible(true);
				//TODO: Should use getFiles to deal with recent docs better
				if (df.getFile() != null) settings.load(df.getDirectory()+df.getFile());
				/* The easy(lazy) way to refresh all fields with the new values from settings
				 * the better way would be to update the data in each element */
				hideGui();
				showGui();
			}
        });
        pane.add(loadCfgBtn, c);
        
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
	
    /* Show the GUI. If hidden, unhide. If uncreated, create it */
    public void showGui(){
    	if (frame==null){
    		this.createGUI();
    	}
    	frame.setVisible(true);
    	hidden=false;
    	frame.pack();
    }
    
    /* Hide the gui by hiding it then destroying the GUI */
    public void hideGui(){
    	frame.setVisible(false);
    	hidden=true;
    	if (frame !=null) frame.dispose();
    	frame=null;
    }
    
    /* toggle GUI between visible and invisible 
     * Remember that invisible = destroyed (dispose) */
    public void toggleGui(){
    	if(hidden){
    		showGui();
    	} else {
    		hideGui();
    	}

    }
	
}

/* This is a wrapper class that lets me store an integer in 
 * an object that's final. We need to do this to save integer values
 * which we use in action listeners. 
 */
class IDStuff{
	public Integer id;
}