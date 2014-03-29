package cz.martinbayer.analyser.processor.condproc.gui;

import java.util.Date;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.nebula.jface.cdatetime.CDateTimeCellEditor;
import org.eclipse.nebula.jface.cdatetime.CDateTimeObservableValue;
import org.eclipse.nebula.widgets.cdatetime.CDT;
import org.eclipse.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import cz.martinbayer.analyser.procedures.ProcOperand;
import cz.martinbayer.analyser.procedures.TypeProcOperand;
import cz.martinbayer.analyser.procedures.exception.UnsupportedOperandsException;
import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;
import cz.martinbayer.e4.analyser.LoggerFactory;
import cz.martinbayer.utils.DateUtils;

public class CondOperandEditSupport extends EditingSupport {
	private Logger logger = LoggerFactory.getInstance(getClass());
	private ColumnViewer tableViewer;
	private CellEditor cellEditor;
	private int operandNo;

	public CondOperandEditSupport(ColumnViewer tableViewer, int operandNo) {
		super(tableViewer);
		this.operandNo = operandNo;
		this.tableViewer = tableViewer;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		if (element instanceof ConditionDescriptor
				&& ((ConditionDescriptor) element).getSelectedProcedure() != null) {
			ConditionDescriptor descriptor = (ConditionDescriptor) element;

			ProcOperand operand = descriptor.getSelectedProcedure()
					.getSelectableOperands();
			if (operand instanceof TypeProcOperand) {
				if (((TypeProcOperand<?>) operand).getType() == Date.class) {
					cellEditor = createDateTimeEditor(descriptor);
				} else {
					cellEditor = new TextCellEditor((Composite) getViewer()
							.getControl(), SWT.BORDER);
				}
			} else if (operand instanceof ProcOperand) {
				cellEditor = new ComboBoxViewerCellEditor(
						(Composite) getViewer().getControl(), SWT.READ_ONLY);
				((ComboBoxViewerCellEditor) cellEditor)
						.setContentProvider(new ArrayContentProvider());
				((ComboBoxViewerCellEditor) cellEditor).setInput(operand
						.getSelectableOperands());
			}
		}
		return cellEditor;
	}

	private CellEditor createDateTimeEditor(final ConditionDescriptor descriptor) {
		DataBindingContext ctx = new DataBindingContext();
		CDateTimeCellEditor editor = new CDateTimeCellEditor(
				(Composite) getViewer().getControl(), CDT.DATE_SHORT
						| CDT.TIME_SHORT) {
			@Override
			protected Control createControl(Composite parent) {
				final Control c = super.createControl(parent);
				((CDateTime) c).addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						tableViewer.refresh();
					}
				});
				((CDateTime) c).setPattern(DateUtils.DEFAULT_FORMAT_WITH_TIME);
				return c;
			}

		};

		ctx.bindValue(
				new CDateTimeObservableValue((CDateTime) editor.getControl()),
				BeansObservables.observeValue(descriptor,
						ConditionDescriptor.SELECTED_PROC_OPERANDS),
				new DateToProcParamUpdateStrategy(operandNo, descriptor), null);
		return editor;
	}

	@Override
	protected boolean canEdit(Object element) {
		if (element instanceof ConditionDescriptor
				&& ((ConditionDescriptor) element).getSelectedProcOperator() != null) {
			return this.operandNo < ((ConditionDescriptor) element)
					.getSelectedProcOperator().getOperandsCount();
		}
		return false;
	}

	@Override
	protected Object getValue(Object element) {
		if (element instanceof ConditionDescriptor) {
			try {
				return ((ConditionDescriptor) element)
						.getSelectedProcOperands().getValue(operandNo);
			} catch (UnsupportedOperandsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	protected void setValue(Object element, Object value) {

	}

}
