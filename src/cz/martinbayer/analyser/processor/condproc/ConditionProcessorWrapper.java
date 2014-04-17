package cz.martinbayer.analyser.processor.condproc;

import org.eclipse.swt.events.MouseEvent;

import cz.martinbayer.analyser.impl.ConcreteE4LogsisLog;
import cz.martinbayer.analyser.processor.condproc.paletteitem.ConditionPaletteItem;
import cz.martinbayer.analyser.processor.condproc.processor.ConditionProcLogic;
import cz.martinbayer.analyser.processors.IProcessorItemWrapper;
import cz.martinbayer.analyser.processors.IProcessorLogic;
import cz.martinbayer.analyser.processors.IProcessorsPaletteItem;

public class ConditionProcessorWrapper implements
		IProcessorItemWrapper<ConcreteE4LogsisLog> {

	private ConditionProcLogic logic;
	private ConditionPaletteItem item;

	public ConditionProcessorWrapper() {
		logic = new ConditionProcLogic();
		item = new ConditionPaletteItem();
	}

	@Override
	public IProcessorLogic<ConcreteE4LogsisLog> getProcessorLogic() {
		return logic;
	}

	@Override
	public IProcessorsPaletteItem getProcessorPaletteItem() {
		return item;
	}

	@Override
	public void mouseDoubleClicked(MouseEvent e) {
		item.openConfigDialog(logic);
	}

	@Override
	public IProcessorItemWrapper<ConcreteE4LogsisLog> getInstance() {
		return new ConditionProcessorWrapper();
	}

}
