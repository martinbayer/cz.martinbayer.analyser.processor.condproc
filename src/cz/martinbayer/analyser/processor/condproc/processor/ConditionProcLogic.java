package cz.martinbayer.analyser.processor.condproc.processor;

import java.util.List;

import cz.martinbayer.analyser.impl.ConcreteXMLog;
import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;
import cz.martinbayer.analyser.processors.IProcessorLogic;
import cz.martinbayer.analyser.processors.types.LogProcessor;

public class ConditionProcLogic implements IProcessorLogic<ConcreteXMLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1555192448880937105L;
	private ConditionProcessor processor;
	private ConditionProcessorModel<ConcreteXMLog> model;

	public ConditionProcLogic() {
		this.model = new ConditionProcessorModel<>();
		this.processor = new ConditionProcessor(this.model);
	}

	@Override
	public LogProcessor<ConcreteXMLog> getProcessor() {
		return this.processor;
	}

	public boolean addCondDescriptorToModel(
			ConditionDescriptor<ConcreteXMLog> condDescriptor) {
		return this.model.addCondDescriptor(condDescriptor);
	}

	public boolean removeProcedureFromModel(
			ConditionDescriptor<ConcreteXMLog> condDescriptor) {
		return this.model.removeCondDescriptor(condDescriptor);
	}

	public List<ConditionDescriptor<ConcreteXMLog>> getCondDescriptors() {
		return this.model.getCondDesciptors();
	}
}
