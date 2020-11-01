import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
import java.awt.Choice;
import java.awt.Label;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import java.util.ArrayList;
import java.awt.Scrollbar;
import javax.swing.JTextPane;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.event.*;
import javax.swing.text.*;



public class UI extends JFrame {
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public class BackgroundColorAction extends StyledEditorKit.StyledTextAction {

	    private Color color;

	    public BackgroundColorAction(Color color) {
	        super(StyleConstants.Background.toString());
	        this.color = color;
	    }

	    @Override
	    public void actionPerformed(ActionEvent ae) {
	        JEditorPane editor = getEditor(ae);
	        if (editor == null) {
	            return;
	        }
	        
	        //Add span Tag
	        String htmlStyle = "background-color:" + getHTMLColor(color);
	        SimpleAttributeSet attr = new SimpleAttributeSet();
	        attr.addAttribute(HTML.Attribute.STYLE, htmlStyle);
	        MutableAttributeSet outerAttr = new SimpleAttributeSet();
	        outerAttr.addAttribute(HTML.Tag.SPAN, attr);
	        //Next line is just an instruction to editor to change color
	        StyleConstants.setBackground(outerAttr, this.color);
	        setCharacterAttributes(editor, outerAttr, false);
	    }
	}
	
	public static String getHTMLColor(Color color) {
	    if (color == null) {
	        return "#000000";
	    }
	    return "#" +  Integer.toHexString(color.getRGB()).substring(2).toUpperCase();
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	class MyDocument extends DefaultStyledDocument {
	    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
	        super.insertString(offs, str, a);
	        System.out.println("insert str = " + str);
	    }

	}
	
	class MyListener implements DocumentListener {  		//textpane listener
	    public void changedUpdate(DocumentEvent e) {
	        System.out.println("update");
	        
	    }
	    public void insertUpdate(DocumentEvent e) {
	        System.out.println("insert");
	    }
	    public void removeUpdate(DocumentEvent e) {
	        System.out.println("remove");
	    }
	}
	

	//////////////////////////////////////////////////////////////////////////
	

	
	
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public UI() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 725);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MyDocument doc;
		DocumentListener listener;
		
		ImageIcon Saveicon = new ImageIcon("pic\\SaveHK.png");   // toolbar pics
		ImageIcon Openicon = new ImageIcon("pic\\OpenHK.png");
		ImageIcon Boldicon = new ImageIcon("pic\\BoldHK.png");
		ImageIcon Copyicon = new ImageIcon("pic\\CopyHK.png");
		ImageIcon Pasteicon = new ImageIcon("pic\\PasteHK.png");
		ImageIcon Cuticon = new ImageIcon("pic\\CutHK.png");
		ImageIcon Italicicon = new ImageIcon("pic\\ItalicHK.png");
		ImageIcon Printicon = new ImageIcon("pic\\PrintHK.png");
		ImageIcon Underlineicon = new ImageIcon("pic\\UnderlineHK.png");
		ImageIcon Newicon = new ImageIcon("pic\\NewHK.png");
		
		
		
		
		
		
		JMenuBar menuBar = new JMenuBar(); //second toolbar, idk we gonna need last three pic or not, so i just keep it
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuBar.setBounds(0, 21, 563, 23);
		contentPane.add(menuBar);
		
		JButton savebutton = new JButton(Saveicon);
		savebutton.setBounds(100,100,100,80);
		menuBar.add(savebutton);
		
		JButton openbutton = new JButton(Openicon);
		menuBar.add(openbutton);
		
		JButton Newbutton = new JButton(Newicon);
		menuBar.add(Newbutton);
		
		JButton printbutton = new JButton(Printicon);
		menuBar.add(printbutton);
		
		JButton cutbutton = new JButton(Cuticon);
		menuBar.add(cutbutton);
		
		JButton copybutton = new JButton(Copyicon);
		menuBar.add(copybutton);
		
		JButton pastebutton = new JButton(Pasteicon);
		menuBar.add(pastebutton);
		savebutton.setBounds(100,100,100,80);
		savebutton.setBounds(100,100,100,80);
		savebutton.setBounds(100,100,100,80);
		
		JButton boldbutton = new JButton(Boldicon);
		menuBar.add(boldbutton);
		
		JButton Italicbutton = new JButton(Italicicon);
		menuBar.add(Italicbutton);
		
		JButton underlinebutton = new JButton(Underlineicon);
		menuBar.add(underlinebutton);
		
		
		
		
		
		
		JMenuBar menuBar_2 = new JMenuBar();
		menuBar_2.setBackground(Color.LIGHT_GRAY);
		menuBar_2.setBounds(0, 0, 563, 23);
		contentPane.add(menuBar_2);
		
		menuBar_2.setFont(menuBar_2.getFont().deriveFont(12f));
        // 設置「檔案」選單
        JMenu fileMenu = new JMenu("檔案");
        
        JMenuItem menuOpen = new JMenuItem("開啟舊檔");
        // 快速鍵設置
        menuOpen.setAccelerator(
                    KeyStroke.getKeyStroke(
                            KeyEvent.VK_O, 
                            InputEvent.CTRL_MASK));
        JMenuItem menuSave = new JMenuItem("儲存檔案");
        menuSave.setAccelerator(
                    KeyStroke.getKeyStroke(
                            KeyEvent.VK_S, 
                            InputEvent.CTRL_MASK));
        JMenuItem menuSaveAs = new JMenuItem("另存新檔");
        JMenuItem menuClose = new JMenuItem("關閉");
        menuClose.setAccelerator(
                    KeyStroke.getKeyStroke(
                            KeyEvent.VK_Q, 
                            InputEvent.CTRL_MASK));
        fileMenu.add(menuOpen);
        fileMenu.addSeparator(); // 分隔線
        fileMenu.add(menuSave);
        fileMenu.add(menuSaveAs);        
        fileMenu.addSeparator(); // 分隔線
        fileMenu.add(menuClose);
        // 設置「編輯」選單        
        JMenu editMenu = new JMenu("編輯");
        JMenuItem menuCut = new JMenuItem("剪下");
        menuCut.setAccelerator(
                    KeyStroke.getKeyStroke(KeyEvent.VK_X, 
                            InputEvent.CTRL_MASK));
        JMenuItem menuCopy = new JMenuItem("複製");
        menuCopy.setAccelerator(
                    KeyStroke.getKeyStroke(KeyEvent.VK_C, 
                            InputEvent.CTRL_MASK));
        JMenuItem menuPaste = new JMenuItem("貼上");
        menuPaste.setAccelerator(
                    KeyStroke.getKeyStroke(KeyEvent.VK_V, 
                            InputEvent.CTRL_MASK));
        editMenu.add(menuCut);
        editMenu.add(menuCopy);
        editMenu.add(menuPaste);
        menuBar_2.add(fileMenu);
        menuBar_2.add(editMenu);
        
        
        
        JMenu word_color = new JMenu("\u5B57\u9AD4\u984F\u8272");  // word_clolr
        menuBar_2.add(word_color);
        
        JMenuItem word_black = new JMenuItem("\u9ED1\u8272");
        word_color.add(word_black);
        
        JMenuItem word_blue = new JMenuItem("\u85CD\u8272");
        word_color.add(word_blue);
        
        JMenuItem word_red = new JMenuItem("\u7D05\u8272");
        word_color.add(word_red);
        
        JMenuItem word_yellow = new JMenuItem("\u9EC3\u8272");
        word_color.add(word_yellow);
        
        
        
        
        
        JMenu backround = new JMenu("\u80CC\u666F");  // backround_change
        menuBar_2.add(backround);
        
        JMenuItem background_white = new JMenuItem("\u767D\u8272");
        backround.add(background_white);
        
        JMenuItem background_blue = new JMenuItem("\u85CD\u8272");
        backround.add(background_blue);
        
        JMenuItem background_red = new JMenuItem("\u7D05\u8272");
        backround.add(background_red);
        
        JMenuItem background_yellow = new JMenuItem("\u9EC3\u8272");
        backround.add(background_yellow);
        
        
        
        JMenu platform = new JMenu("\u5E73\u53F0"); //platform_change
        menuBar_2.add(platform);
        
        
        
        JMenuItem windows = new JMenuItem("Windows");
        platform.add(windows);
        windows.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae)  
        	{ 
        		String lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
        		contentPane.setBackground(Color.lightGray);
        		menuBar_2.setBackground(Color.lightGray);
        		menuBar.setBackground(Color.lightGray);
        		try {
					UIManager.setLookAndFeel(lookAndFeel);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        	} 
 
        });
        
        
        
        JMenuItem linux = new JMenuItem("Linux");
        platform.add(linux);
        linux.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae)  
        	{ 
        		String lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
        		contentPane.setBackground(Color.darkGray);
        		menuBar_2.setBackground(Color.darkGray);
        		menuBar.setBackground(Color.darkGray);
        		menuBar.setForeground(Color.white);
        		try {
        			UIManager.setLookAndFeel(lookAndFeel);
        			UIManager.put("MenuItem.background", Color.CYAN);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
        		
        	} 
 
        });
        
        JMenu insert_button = new JMenu("\u63D2\u5165");
        menuBar_2.add(insert_button);
        
        JMenuItem insert_pic = new JMenuItem("\u5716\u7247");
        insert_button.add(insert_pic);
        
        
        
        
		
		
		
		
		
		
		
		
		JEditorPane word_editorPane = new JEditorPane(); //word_editpane
		JScrollPane scrollPane1 = new JScrollPane(word_editorPane);
		scrollPane1.setBounds(12, 83, 525, 237);
		word_editorPane.setContentType("text/html");
		scrollPane1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(scrollPane1);
		
		
		
		
		
		
		
		
		JTextPane html_textPane = new JTextPane(); //html_textpane
	    doc = new MyDocument();
        listener = new MyListener(); //listener
        doc.addDocumentListener(listener);
        html_textPane.setDocument(doc);
	    JScrollPane scrollPane2 = new JScrollPane(html_textPane);
	    scrollPane2.setBounds(12, 404, 525, 237);
	    scrollPane2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(scrollPane2);
		
		
		

		
		
		
		
		JButton submit = new JButton("submit"); 	//submit html button
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data = word_editorPane.getText();
				html_textPane.setText(data);
			}
		});
		submit.setBounds(212, 340, 116, 45);
		contentPane.add(submit);
		
		
		
		
		 background_white.addActionListener(  	//change backround listener
	                new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                    	
	                    	Highlighter h = word_editorPane.getHighlighter(); 
	                    	int start = word_editorPane.getSelectionStart();
	                    	int end = word_editorPane.getSelectionEnd();
	                    	String selectedText = word_editorPane.getSelectedText();
	                    	try {
								h.addHighlight(start, end, new DefaultHighlighter.DefaultHighlightPainter(
								      Color.white));
							} catch (BadLocationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	                        

	                    }
	                }
	            );
		
        background_blue.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	Highlighter h = word_editorPane.getHighlighter(); 
                    	int start = word_editorPane.getSelectionStart();
                    	int end = word_editorPane.getSelectionEnd();
                    	String selectedText = word_editorPane.getSelectedText();
                    	try {
							h.addHighlight(start, end, new DefaultHighlighter.DefaultHighlightPainter(
							      Color.blue));
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
                    }
                }
            );
        
        background_red.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	Highlighter h = word_editorPane.getHighlighter(); 
                    	int start = word_editorPane.getSelectionStart();
                    	int end = word_editorPane.getSelectionEnd();
                    	String selectedText = word_editorPane.getSelectedText();
                    	DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
                    	try {
                    		word_editorPane.getHighlighter().addHighlight(start, end, highlightPainter);
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
                    }
                }
            );
        
        background_yellow.addActionListener(new ActionListener() {
            
        	public void actionPerformed(ActionEvent e) { 
            	int start = word_editorPane.getSelectionStart();
            	int end = word_editorPane.getSelectionEnd();
            	int number  = end - start;
            	StyledDocument doc = (StyledDocument) word_editorPane.getDocument();
            	SimpleAttributeSet background = new SimpleAttributeSet();
            	DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
            	try {
					word_editorPane.getHighlighter().addHighlight(start, end, highlightPainter);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        }
        
        		);
        
        
        
        
        word_black.addActionListener(new StyledEditorKit.ForegroundAction("Black", Color.black));
        
        word_blue.addActionListener(new StyledEditorKit.ForegroundAction("Blue", Color.blue));
        
        word_yellow.addActionListener(new StyledEditorKit.ForegroundAction("Yellow", Color.yellow));
        
        word_red.addActionListener(new StyledEditorKit.ForegroundAction("Red", Color.red));
        
        
        underlinebutton.addActionListener(new StyledEditorKit.UnderlineAction());
        
        
        Italicbutton.addActionListener(new StyledEditorKit.ItalicAction());
        
        
        boldbutton.addActionListener(new StyledEditorKit.BoldAction());
        
        
        
        insert_pic.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae)  
        	{ 
        	
        		JFileChooser fileChooser = new JFileChooser();//宣告filechooser 
        		int returnValue = fileChooser.showOpenDialog(null);//叫出filechooser 
        		Document doc = word_editorPane.getDocument();
        		if (returnValue == JFileChooser.APPROVE_OPTION) //判斷是否選擇檔案 
        		{ 
        		File selectedFile = fileChooser.getSelectedFile();//指派給File 
        		String selectPath = fileChooser.getSelectedFile().getPath();
        		System.out.println( selectPath);
        		word_editorPane.setText("<html><img src='"+selectPath+"' ></img>");


        		}
        		
        		
        	} 
 
        });
        
        
        
        
        
        
        
		
		
		
		
		
		
		
	}
}















