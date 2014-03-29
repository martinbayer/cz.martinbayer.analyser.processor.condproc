package cz.martinbayer.analyser.processor.condproc.gui;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import cz.martinbayer.analyser.procedures.IProcedure;
import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;

public class CondProcLabelProvider extends ColumnLabelProvider {
	@Override
	public String getText(Object element) {
		if (element instanceof ConditionDescriptor
				&& ((ConditionDescriptor) element).getSelectedProcedure() != null) {
			return ((ConditionDescriptor) element).getSelectedProcedure()
					.getName();
		} else if (element instanceof IProcedure) {
			return ((IProcedure) element).getName();
		}
		return "N/A";
	}
}
