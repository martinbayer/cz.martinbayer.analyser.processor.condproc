package cz.martinbayer.analyser.processor.condproc.processor;

import java.util.List;

import cz.martinbayer.analyser.impl.ConcreteE4LogsisLog;
import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;
import cz.martinbayer.analyser.processors.IProcessorLogic;
import cz.martinbayer.analyser.processors.types.LogProcessor;

public class ConditionProcLogic implements IProcessorLogic<ConcreteE4LogsisLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1555192448880937105L;
	private ConditionProcessor processor;
	private ConditionProcessorModel<ConcreteE4LogsisLog> model;

	public ConditionProcLogic() {
		this.model = new ConditionProcessorModel<>();
		this.processor = new ConditionProcessor(this.model);
	}

	@Override
	public LogProcessor<ConcreteE4LogsisLog> getProcessor() {
		return this.processor;
	}

	public boolean addCondDescriptorToModel(
			ConditionDescriptor<ConcreteE4LogsisLog> condDescriptor) {
		return this.model.addCondDescriptor(condDescriptor);
	}

	public boolean removeProcedureFromModel(
			ConditionDescriptor<ConcreteE4LogsisLog> condDescriptor) {
		return this.model.removeCondDescriptor(condDescriptor);
	}

	public List<ConditionDescriptor<ConcreteE4LogsisLog>> getCondDescriptors() {
		return this.model.getCondDesciptors();
	}
}
