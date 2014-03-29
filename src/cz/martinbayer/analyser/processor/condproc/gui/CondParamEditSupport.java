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

import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;

public class CondParamEditSupport extends EditingSupport {

	private ColumnViewer tableViewer;
	private ComboBoxViewerCellEditor cellEditor;

	public CondParamEditSupport(ColumnViewer tableViewer) {
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
		// if (desc != null && desc.getProcedure() != null) {
		// ((ComboBoxViewerCellEditor) cellEditor).setInput(desc
		// .getProcedure().getPossibleParameters());
		if (element instanceof ConditionDescriptor
				&& ((ConditionDescriptor) element).getSelectedProcedure() != null) {
			HashSet<Object> values = ((ConditionDescriptor) element)
					.getSelectedProcedure().getPossibleParameters().getParams();
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
			return ((ConditionDescriptor) element).getSelectedProcParam();
		}
		return null;
	}

	@Override
	protected void setValue(Object element, Object value) {
		if (value != null) {
			((ConditionDescriptor) element).setSelectedProcParam(value);
			tableViewer.update(element, null);
		}
	}

}
