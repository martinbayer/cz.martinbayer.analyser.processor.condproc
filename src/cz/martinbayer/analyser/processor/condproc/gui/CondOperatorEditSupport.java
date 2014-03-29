package cz.martinbayer.analyser.processor.condproc.gui;

import java.util.HashSet;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import cz.martinbayer.analyser.procedures.EOperator;
import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;

public class CondOperatorEditSupport extends EditingSupport {

	private ColumnViewer tableViewer;
	private ComboBoxViewerCellEditor cellEditor;

	public CondOperatorEditSupport(ColumnViewer tableViewer) {
		super(tableViewer);
		this.tableViewer = tableViewer;
		cellEditor = new ComboBoxViewerCellEditor((Composite) getViewer()
				.getControl(), SWT.READ_ONLY);
		cellEditor.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				if (element != null) {
					return element.toString();
				}
				return "n/a";
			}
		});
		cellEditor.setContentProvider(new ArrayContentProvider());
	}

	@Override
	protected CellEditor getCellEditor(Object element) {

		if (element instanceof ConditionDescriptor
				&& ((ConditionDescriptor) element).getSelectedProcedure() != null) {
			HashSet<EOperator> values = ((ConditionDescriptor) element)
					.getSelectedProcedure().getOperators().getOperators();
			cellEditor.setInput(values.toArray());
		}
		return cellEditor;
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		if (element instanceof ConditionDescriptor) {
			return ((ConditionDescriptor) element).getSelectedProcOperator();
		}
		return null;
	}

	@Override
	protected void setValue(Object element, Object value) {
		if (value != null) {
			((ConditionDescriptor) element)
					.setSelectedProcOperator((EOperator) value);
			tableViewer.update(element, null);
		}
	}

}
