package cz.martinbayer.analyser.processor.condproc;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.events.MouseEvent;

import cz.martinbayer.analyser.impl.ConcreteE4LogsisLog;
import cz.martinbayer.analyser.processor.condproc.paletteitem.ConditionPaletteItem;
import cz.martinbayer.analyser.processor.condproc.processor.ConditionProcLogic;
import cz.martinbayer.analyser.processors.IProcessorItemWrapper;
import cz.martinbayer.analyser.processors.IProcessorLogic;
import cz.martinbayer.analyser.processors.IProcessorsPaletteItem;

public class ConditionProcessorWrapper implements
		IProcessorItemWrapper<ConcreteE4LogsisLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1671167876429781418L;
	private ConditionProcLogic logic;
	private ConditionPaletteItem item;

	public ConditionProcessorWrapper() {
	}

	@Override
	public IProcessorLogic<ConcreteE4LogsisLog> getProcessorLogic() {
		if (logic == null) {
			logic = new ConditionProcLogic();
		}
		return logic;
	}

	@Override
	public IProcessorsPaletteItem getProcessorPaletteItem() {
		if (item == null) {
			item = new ConditionPaletteItem();

		}
		return item;
	}

	@Override
	public void mouseDoubleClicked(MouseEvent e) {
		((ConditionPaletteItem) getProcessorPaletteItem())
				.openConfigDialog(logic);
	}

	@Override
	public IProcessorItemWrapper<ConcreteE4LogsisLog> getInstance() {
		return new ConditionProcessorWrapper();
	}

	@Override
	public void setContext(IEclipseContext ctx) {
		Activator.setEclipseContext(ctx);
	}

}
