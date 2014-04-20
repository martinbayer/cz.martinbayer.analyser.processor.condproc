package cz.martinbayer.analyser.processor.condproc.processor;

import cz.martinbayer.analyser.impl.ConcreteE4LogsisLog;
import cz.martinbayer.analyser.procedures.exception.ProcedureException;
import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;
import cz.martinbayer.analyser.processors.types.ConditionalProcessor;

public class ConditionProcessor extends
		ConditionalProcessor<ConcreteE4LogsisLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1700380590013855537L;
	private ConditionProcessorModel<ConcreteE4LogsisLog> model;

	public ConditionProcessor(ConditionProcessorModel<ConcreteE4LogsisLog> model) {
		this.model = model;
	}

	@Override
	protected void process() {
		for (ConditionDescriptor<ConcreteE4LogsisLog> desc : this.model
				.getRunnableCondDesciptors()) {
			try {
				desc.getSelectedProcedure().setData(logData);
				desc.getSelectedProcedure().runProcedure();
				if (desc.getSelectedProcedure().getResult()) {
					/* stop on first passed condition */
					nextSelectedProcessor = desc.getSelectedProcessor();
					break;
				} else {
					logger.info("Procedure {} didn't pass", desc
							.getSelectedProcedure().getName());
				}
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
