package editor.ui;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.*;
import java.util.ArrayList;

import editor.core.*;
import editor.memento.*;

public class EditorUI implements ActionListener {
	JFrame f;
	JMenuBar mb;
	JMenu file, edit, help;
	JMenuItem cut, copy, paste, selectAll , record , replay,redo,undo;
	JTextArea ta;
	CareTaker careTaker = new CareTaker();
	Originator originator = new Originator();
	int startIndex = 0;
	int endIndex = 0;
	int currentIndex = -1;
	int counter = 0;
	MiniEditorManager miniEditorManager;

	EditorUI() {
		f = new JFrame();

		cut = new JMenuItem("cut");
		copy = new JMenuItem("copy");
		paste = new JMenuItem("paste");
		record = new JMenuItem("record");
		replay = new JMenuItem("replay");
		redo = new JMenuItem("redo");
		undo = new JMenuItem("undo");
		miniEditorManager = new MiniEditorManager();
		
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		record.addActionListener(this);
		replay.addActionListener(this);
		redo.addActionListener(this);
		undo.addActionListener(this);

		mb = new JMenuBar();
		

		edit = new JMenu("Edit");

		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(record);
		edit.add(redo);
		edit.add(undo);
		undo.setEnabled(false);
		redo.setEnabled(false);
		mb.add(edit);

		ta = new JTextArea();
		addtoMemento("insert","",0,-1,"");

		f.add(mb,BorderLayout.NORTH);
		f.add(ta,BorderLayout.CENTER);

		f.setSize(500, 500);
		f.setVisible(true);
		
		ta.addKeyListener(new KeyListener(){
		    
		    @Override
		    public void keyTyped(KeyEvent e) {
		    	undo.setEnabled(true);
		    	if((int)e.getKeyChar() > 31 && (int)e.getKeyChar() < 127)
		    	{
		        int position = ta.getCaretPosition();
		    	miniEditorManager.insert(e.getKeyChar(),position);
		    	addtoMemento("insert",miniEditorManager.getBuffer(),position,-1,Character.toString(e.getKeyChar()));
		    	
		    	}
		    	else if((int)e.getKeyChar() == 10)  //new line event
		    	{
		    		int position = ta.getCaretPosition();
		    		miniEditorManager.insert(e.getKeyChar(),position);
		    		addtoMemento("insert",miniEditorManager.getBuffer(),position,-1,Character.toString(e.getKeyChar()));
		    	}
		    	else if((int)e.getKeyChar() == 8)
		    	{
		    		int position = ta.getCaretPosition();
		    		miniEditorManager.updateBuffer(ta.getText());
		    		addtoMemento("delete",miniEditorManager.getBuffer(),position,-1,null);	
		    	}
		    	else
		    	{
		    		System.out.println("you enterd somethingelse"+e.getKeyCode()+(int)e.getKeyChar());
		    	}
		    }

		    @Override
		    public void keyReleased(KeyEvent e) {
		    }

			@Override
			public void keyPressed(KeyEvent e) {
				
				
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cut)
		{
			int start = ta.getSelectionStart();
			int end = ta.getSelectionEnd();
			miniEditorManager.cut(start,end);
			ta.setText(miniEditorManager.getBuffer());
			addtoMemento("cut",miniEditorManager.getBuffer(),start,end,null);

		}
		if (e.getSource() == paste)
		{
			try{
			int start = ta.getCaretPosition();
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Clipboard clipboard = toolkit.getSystemClipboard();
			String result = (String) clipboard.getData(DataFlavor.stringFlavor);
			miniEditorManager.paste(start);
			ta.setText(miniEditorManager.getBuffer());
			addtoMemento("paste",miniEditorManager.getBuffer(),start,-1,result);
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
		}
		if (e.getSource() == copy)
		{
			int start = ta.getSelectionStart();
			int end = ta.getSelectionEnd();
			miniEditorManager.copy(start,end);
			addtoMemento("copy",miniEditorManager.getBuffer(),start,end,null);
			
		}
		if(e.getSource() == record)
		{
			edit.remove(record);
			edit.add(replay);
			mb.updateUI();
			ArrayList<Memento> mementoList = (ArrayList<Memento>) careTaker.getMementoList(); 
			startIndex = mementoList.size() - 1;
			System.out.println("startindex:"+startIndex);
			
		}
		if(e.getSource() == replay)
		{
			ArrayList<Memento> mementoList = (ArrayList<Memento>) careTaker.getMementoList();
			endIndex = mementoList.size() -1;
			System.out.println("endindex:"+endIndex);
			//Memento mementoTemp = careTaker.get(mementoList.size()-1);
			//MementoState mementoStateTemp = mementoTemp.getState();
			replayCommands(startIndex,endIndex);
			edit.remove(replay);
			edit.add(record);
			mb.updateUI();
		}
		if(e.getSource() == undo)
		{
			ArrayList<Memento> mementoList = (ArrayList<Memento>) careTaker.getMementoList(); 
			if(currentIndex < 0)
			{
			currentIndex = mementoList.size() - 1;
			}
			if(currentIndex > 0)
			{
			currentIndex--;
			Memento memento = careTaker.get(currentIndex);
			MementoState mementoState = memento.getState();
			ta.setText(mementoState.getBuffer().getBuffer().toString());
			redo.setEnabled(true);
			}
			if(currentIndex == 0)
			{
				undo.setEnabled(false);
			}
		}
		if(e.getSource() == redo)
		{
			ArrayList<Memento> mementoList = (ArrayList<Memento>) careTaker.getMementoList(); 
			counter = mementoList.size() - 1;
			if(counter >= (currentIndex+1))
			{
			currentIndex++;
			Memento memento = careTaker.get(currentIndex);
			MementoState mementoState = memento.getState();
			ta.setText(mementoState.getBuffer().getBuffer().toString());
			}
			if(currentIndex == counter)
			{
				redo.setEnabled(false);
			}
		}
	}
	
	public void replayCommands(int startIndex,int endIndex)
	{
		for(int i = startIndex; i <= endIndex; i++)
		{
			Memento memento = careTaker.get(i);
			MementoState mementoState = memento.getState();
			System.out.println("Replaying :"+mementoState.getBuffer().getBuffer().toString());
			ta.setText(mementoState.getBuffer().getBuffer().toString());
			
			/*if(command.getCommand() == "copy")
			{
				System.out.println(command.getCommand());
				ta.setSelectionStart(command.getStart());
				ta.setSelectionEnd(command.getEnd());
				ta.copy();
				addtoMemento(command.getCommand(),ta.getText(),command.getStart(),command.getEnd(),command.getClipboard());
			}
			else if(command.getCommand() == "cut")
			{
				System.out.println(command.getCommand());
				ta.setSelectionStart(command.getStart());
				ta.setSelectionEnd(command.getEnd());
				ta.cut();
				addtoMemento(command.getCommand(),ta.getText(),command.getStart(),command.getEnd(),command.getClipboard());
			}
			else if(command.getCommand() == "paste")
			{
				System.out.println(command.getCommand());
				ta.setCaretPosition(command.getStart());
				ta.paste();
				addtoMemento(command.getCommand(),ta.getText(),command.getStart(),command.getEnd(),command.getClipboard());
			}
			else if(command.getCommand() == "insert")
			{
				System.out.println(command.getCommand());
				ta.setCaretPosition(command.getStart());
				ta.insert(command.getClipboard(),ta.getCaretPosition());
				addtoMemento(command.getCommand(),ta.getText(),command.getStart(),command.getEnd(),command.getClipboard());
			}
			else if(command.getCommand() == "delete")
			{
				System.out.println(command.getCommand());	
				ta.setCaretPosition(command.getStart());
				ta.remove(ta.getCaretPosition());
				addtoMemento(command.getCommand(),ta.getText(),command.getStart(),command.getEnd(),command.getClipboard());
			}  */
		}
	}
	
	public void addtoMemento(String operation,String text,int start,int end,String clipboard)
	{
		MementoState mementoState = new MementoState();
		mementoState.setStart(start);
		mementoState.setEnd(end);
		mementoState.setCommand(operation);
		Buffer buffer = new Buffer(new StringBuffer(text));
		mementoState.setBuffer(buffer);
		originator.setState(mementoState);
		careTaker.add(originator.saveStateToMemento());	
	}

	public static void main(String[] args) {
		new EditorUI();
	}
}