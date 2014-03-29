package cz.martinbayer.analyser.processor.condproc.gui;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;

public class CondParamLabelProvider extends ColumnLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof ConditionDescriptor
				&& ((ConditionDescriptor) element).getSelectedProcParam() != null) {
			return ((ConditionDescriptor) element).getSelectedProcParam()
					.toString();
		}
		return "";
	}
}
