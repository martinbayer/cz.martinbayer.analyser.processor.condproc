package cz.martinbayer.analyser.processor.condproc.gui;

import java.util.Date;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.e4.core.services.log.Logger;

import cz.martinbayer.analyser.procedures.ProcOperand;
import cz.martinbayer.analyser.procedures.TypeProcOperand;
import cz.martinbayer.analyser.procedures.exception.UnsupportedOperandsException;
import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;
import cz.martinbayer.e4.analyser.LoggerFactory;

public class DateToProcParamUpdateStrategy extends UpdateValueStrategy {

	private Logger logger = LoggerFactory.getInstance(getClass());

	public DateToProcParamUpdateStrategy(final int operandNo,
			final ConditionDescriptor descriptor) {
		setConverter(new IConverter() {

			@Override
			public Object getToType() {
				return ProcOperand.class;
			}

			@Override
			public Object getFromType() {
				return Date.class;
			}

			@Override
			public Object convert(Object fromObject) {
				if (fromObject instanceof Date) {
					TypeProcOperand<Date> operand = (TypeProcOperand<Date>) descriptor
							.getSelectedProcOperands();
					try {
						operand.setValue(fromObject, operandNo);
					} catch (UnsupportedOperandsException e) {
						logger.error("Unable to set value to position "
								+ operandNo, e);
					}
					return operand;
				}
				return null;
			}
		});
	}
}
