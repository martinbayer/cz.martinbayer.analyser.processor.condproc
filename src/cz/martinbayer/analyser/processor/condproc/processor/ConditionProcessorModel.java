package cz.martinbayer.analyser.processor.condproc.processor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;
import cz.martinbayer.utils.model.ObservableModelObject;

/**
 * Holds the information about actual procedures assigned to the conditional
 * processor
 * 
 * @author Martin
 * 
 */
public class ConditionProcessorModel extends ObservableModelObject implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4423080845457977950L;
	private List<ConditionDescriptor> condDescriptors = new ArrayList<>();

	public final List<ConditionDescriptor> getCondDesciptors() {
		return condDescriptors;
	}

	public final boolean removeCondDescriptor(ConditionDescriptor proc) {
		return this.condDescriptors.remove(proc);
	}

	public final boolean addCondDescriptor(ConditionDescriptor proc) {
		return this.condDescriptors.add(proc);
	}

}
