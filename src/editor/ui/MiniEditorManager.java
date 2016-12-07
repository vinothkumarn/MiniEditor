package editor.ui;

import editor.core.Buffer;
import editor.core.MiniEditorReceiver;
import editor.core.commands.*;

public class MiniEditorManager {
	
	MiniEditorReceiver miniEditorReceiver;
	CommandInvoker copyCommandInvoker;
	CommandInvoker cutCommandInvoker;
	CommandInvoker insertCommandInvoker;
	CommandInvoker pasteCommandInvoker;
	CommandInvoker selectCommandInvoker;
	CommandInvoker updateCommandInvoker;
	CopyCommand copyCommand;
	CutCommand cutCommand;
	InsertCommand insertCommand;
	PasteCommand pasteCommand;
	SelectCommand selectCommand;
	UpdateCommand updateCommand;
	Buffer buffer;
	String str;
	StringBuffer strBuffer;
	
	public MiniEditorManager()
	{
		str = new String();
		strBuffer = new StringBuffer(str);
		buffer = new Buffer(strBuffer);
		miniEditorReceiver = new MiniEditorReceiver(buffer);
		copyCommand = new CopyCommand(miniEditorReceiver);
		cutCommand = new CutCommand(miniEditorReceiver);
		insertCommand = new InsertCommand(miniEditorReceiver);
		pasteCommand = new PasteCommand(miniEditorReceiver);
		selectCommand = new SelectCommand(miniEditorReceiver);
		updateCommand = new UpdateCommand(miniEditorReceiver);
		copyCommandInvoker = new CommandInvoker(copyCommand);
		cutCommandInvoker = new CommandInvoker(cutCommand);
		insertCommandInvoker = new CommandInvoker(insertCommand);
		pasteCommandInvoker = new CommandInvoker(pasteCommand);
		selectCommandInvoker = new CommandInvoker(selectCommand);
		updateCommandInvoker = new CommandInvoker(updateCommand);
	}

	public void insert(char text,int position)
	{
		System.out.println("inserting...");
		insertCommand.setInsertString(Character.toString(text));
		insertCommand.setPosition(position);
		insertCommandInvoker.execute();
	}
	
	public void cut(int start,int end)
	{
		System.out.println("cut");
		cutCommand.setStart(start);
		cutCommand.setEnd(end);
		cutCommandInvoker.execute();
	}
	
	public void copy(int start,int end)
	{
		System.out.println("copy");
		copyCommand.setStart(start);
		copyCommand.setEnd(end);
		copyCommandInvoker.execute();
	}

	public void paste(int position)
	{
		System.out.println("paste");
		pasteCommand.setPosition(position);
		pasteCommandInvoker.execute();
	}
	
	public void updateBuffer(String text)
	{
		updateCommand.setText(text);
		updateCommandInvoker.execute();
	}
	
	public String getBuffer()
	{
		return miniEditorReceiver.getBuffer();
	}
	
}
