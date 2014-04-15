package cz.martinbayer.analyser.processor.condproc.processor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;
import cz.martinbayer.analyser.processors.model.IXMLog;
import cz.martinbayer.utils.model.ObservableModelObject;

/**
 * Holds the information about actual procedures assigned to the conditional
 * processor
 * 
 * @author Martin
 * 
 */
public class ConditionProcessorModel<T extends IXMLog> extends
		ObservableModelObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4423080845457977950L;
	private List<ConditionDescriptor<T>> condDescriptors = new ArrayList<>();

	public final List<ConditionDescriptor<T>> getCondDesciptors() {
		return condDescriptors;
	}

	public final List<ConditionDescriptor<T>> getRunnableCondDesciptors() {
		if (this.condDescriptors != null) {
			List<ConditionDescriptor<T>> runnableCondDesc = new ArrayList<>();
			for (ConditionDescriptor<T> desc : this.condDescriptors) {
				if (desc.isRunnable()) {
					runnableCondDesc.add(desc);
				}
			}
			return runnableCondDesc;
		}
		return null;
	}

	public final boolean removeCondDescriptor(ConditionDescriptor<T> proc) {
		return this.condDescriptors.remove(proc);
	}

	public final boolean addCondDescriptor(ConditionDescriptor<T> proc) {
		return this.condDescriptors.add(proc);
	}

}
