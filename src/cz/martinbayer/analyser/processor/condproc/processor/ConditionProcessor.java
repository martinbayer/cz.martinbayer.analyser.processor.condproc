package cz.martinbayer.analyser.processor.condproc.processor;

import cz.martinbayer.analyser.impl.ConcreteXMLog;
import cz.martinbayer.analyser.procedures.exception.ProcedureException;
import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;
import cz.martinbayer.analyser.processors.types.ConditionalProcessor;

public class ConditionProcessor extends ConditionalProcessor<ConcreteXMLog> {

	private ConditionProcessorModel model;

	public ConditionProcessor(ConditionProcessorModel model) {
		this.model = model;
	}

	@Override
	protected void process() {
		for (ConditionDescriptor desc : this.model.getCondDesciptors()) {
			try {
				desc.getSelectedProcedure().setData(logData);
				desc.getSelectedProcedure().runProcedure();
			} catch (ProcedureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void init() {
		// nothing is needed to be initialized
	}

}
