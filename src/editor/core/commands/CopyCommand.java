package editor.core.commands;

import editor.core.*;

public class CopyCommand implements Command {
	
	MiniEditorReceiver miniEditorReceiver;
	int start;
	int end;
	
	public CopyCommand(MiniEditorReceiver miniEditorReceiver)
	{
		this.miniEditorReceiver = miniEditorReceiver;
	}

	
	public void setStart(int start) {
		this.start = start;
	}


	public void setEnd(int end) {
		this.end = end;
	}


	@Override
	public void execute() {
		miniEditorReceiver.editorSelect(start,end);
		miniEditorReceiver.editorCopy();
	}

}
