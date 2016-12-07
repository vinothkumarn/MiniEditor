package editor.core.commands;

import editor.core.MiniEditorReceiver;

public class InsertCommand implements Command {

    MiniEditorReceiver miniEditorReceiver;
    String insertString;
    int position;
	
	public InsertCommand(MiniEditorReceiver miniEditorReceiver)
	{
		this.miniEditorReceiver = miniEditorReceiver;
	}

	public void setInsertString(String insertString) {
		this.insertString = insertString;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public void execute() {
		miniEditorReceiver.editorInsert(insertString,position);
	}


}
