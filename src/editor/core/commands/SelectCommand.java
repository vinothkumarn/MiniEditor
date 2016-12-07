package editor.core.commands;

import editor.core.MiniEditorReceiver;

public class SelectCommand implements Command {

    MiniEditorReceiver miniEditorReceiver;
	
	public SelectCommand(MiniEditorReceiver miniEditorReceiver)
	{
		this.miniEditorReceiver = miniEditorReceiver;
	}

	@Override
	public void execute() {
		miniEditorReceiver.editorSelect(0, 0);
	}


}
