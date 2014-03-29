package cz.martinbayer.analyser.processor.condproc.gui;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.SWT;

import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;

public class CondEnableEditSupport extends EditingSupport {

	private ColumnViewer tableViewer;

	public CondEnableEditSupport(ColumnViewer tableViewer) {
		super(tableViewer);
		this.tableViewer = tableViewer;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return new CheckboxCellEditor(null, SWT.CHECK | SWT.READ_ONLY);
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		ConditionDescriptor desc = (ConditionDescriptor) element;
		return desc.isEnabled();
	}

	@Override
	protected void setValue(Object element, Object value) {
		ConditionDescriptor desc = (ConditionDescriptor) element;
		desc.setEnabled((boolean) value);
		tableViewer.update(element, null);
	}

}
