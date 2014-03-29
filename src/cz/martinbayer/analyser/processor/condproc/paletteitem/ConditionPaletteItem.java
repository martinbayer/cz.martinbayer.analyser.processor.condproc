package cz.martinbayer.analyser.processor.condproc.paletteitem;

import cz.martinbayer.analyser.processor.condproc.gui.ConditionConfigDialog;
import cz.martinbayer.analyser.processor.condproc.processor.ConditionProcLogic;
import cz.martinbayer.analyser.processors.BasicProcessorPaletteItem;

public class ConditionPaletteItem extends BasicProcessorPaletteItem {

	private static final String LABEL = "Condition processor";

	public ConditionPaletteItem() {
		imagePath = "images/icon.png";
		disabledImagePath = "images/icon_dis.png";
	}

	@Override
	public String getLabel() {
		return LABEL;
	}

	public void openConfigDialog(ConditionProcLogic logic) {
		ConditionConfigDialog dialog = new ConditionConfigDialog(null, logic);
		dialog.open();
	}
}
