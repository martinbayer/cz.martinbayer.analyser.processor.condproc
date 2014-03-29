package cz.martinbayer.analyser.processor.condproc.gui;

import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.jface.viewers.ColumnLabelProvider;

import cz.martinbayer.analyser.procedures.exception.UnsupportedOperandsException;
import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;
import cz.martinbayer.e4.analyser.LoggerFactory;

public class CondOperandLabelProvider extends ColumnLabelProvider {

	private Logger logger = LoggerFactory.getInstance(getClass());
	private int operandNo;

	public CondOperandLabelProvider(int operandNo) {
		this.operandNo = operandNo;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof ConditionDescriptor
				&& ((ConditionDescriptor) element).getSelectedProcOperands() != null) {
			try {
				return ((ConditionDescriptor) element)
						.getSelectedProcOperands().getValue(this.operandNo)
						.toString();
			} catch (UnsupportedOperandsException e) {
				// ignore the error if selected operator doesn't support this
				// operand (e.g. this is second operand and operator supports
				// only one operand)
				if (((ConditionDescriptor) element).getSelectedProcOperator()
						.getOperandsCount() > this.operandNo) {
					logger.debug("Unable to display value for element", e);
				}
			}
		}
		return "";
	}
}
