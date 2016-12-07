package editor.core.commands;

import editor.core.MiniEditorReceiver;

public class PasteCommand implements Command {

    MiniEditorReceiver miniEditorReceiver;
    int position;
    
	public PasteCommand(MiniEditorReceiver miniEditorReceiver)
	{
		this.miniEditorReceiver = miniEditorReceiver;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public void execute() {
		miniEditorReceiver.editorSelect(position,position);
		miniEditorReceiver.editorPaste();
	}


}
