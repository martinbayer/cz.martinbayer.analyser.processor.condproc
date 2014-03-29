package cz.martinbayer.analyser.processor.condproc.processor;

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
public class ConditionProcessorModel extends ObservableModelObject {

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
