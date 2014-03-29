package cz.martinbayer.analyser.processor.condproc.processor;

import java.util.List;

import cz.martinbayer.analyser.impl.ConcreteData;
import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;
import cz.martinbayer.analyser.processors.IProcessorLogic;
import cz.martinbayer.analyser.processors.types.LogProcessor;

public class ConditionProcLogic implements IProcessorLogic<ConcreteData> {

	private ConditionProcessor processor;
	private ConditionProcessorModel model;

	public ConditionProcLogic() {
		this.model = new ConditionProcessorModel();
		this.processor = new ConditionProcessor(this.model);
	}

	@Override
	public LogProcessor<ConcreteData> getProcessor() {
		return this.processor;
	}

	public boolean addCondDescriptorToModel(ConditionDescriptor condDescriptor) {
		return this.model.addCondDescriptor(condDescriptor);
	}

	public boolean removeProcedureFromModel(ConditionDescriptor condDescriptor) {
		return this.model.removeCondDescriptor(condDescriptor);
	}

	public List<ConditionDescriptor> getCondDescriptors() {
		return this.model.getCondDesciptors();
	}
}
