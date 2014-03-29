package cz.martinbayer.analyser.processor.condproc.gui;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;

public class CondFlwProcLabelProvider extends ColumnLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof ConditionDescriptor
				&& ((ConditionDescriptor) element).getSelectedProcessor() != null) {
			return ((ConditionDescriptor) element).getSelectedProcessor()
					.getName();
		}
		return "-";
	}
}
